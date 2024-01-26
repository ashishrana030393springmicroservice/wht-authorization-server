package com.user.auth.security;

import com.user.auth.config.AppConfig;
import com.user.auth.service.SHAService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProjectConfig {
    private final AppConfig appConfig;

    @PostConstruct
    public void log(){
        log.debug(appConfig.toString());
    }
    @Bean
    @Order(1)
    SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception{
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .authorizationEndpoint(e->e.authenticationProviders(getAuthenticationProviders()))
                .oidc(Customizer.withDefaults());

        http.cors(corsConfigurer-> {
            CorsConfigurationSource source= (s)->{
                CorsConfiguration cc= new CorsConfiguration();
                cc.setAllowedOrigins(appConfig.getAuthServer().getAllowedOrigins());
                cc.setAllowedMethods(List.of("*"));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowCredentials(true);
                return cc;
            };
            corsConfigurer.configurationSource(source);
        });
        http.exceptionHandling(c-> c.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
        ));
        return http.build();
    }

    private Consumer<List<AuthenticationProvider>> getAuthenticationProviders() {
        return authenticationProviders -> {
            for(var authenticationProvider: authenticationProviders){
                if(authenticationProvider instanceof OAuth2AuthorizationCodeRequestAuthenticationProvider provider){
                    CustomRequestValidator validator = new CustomRequestValidator();
                    provider.setAuthenticationValidator(validator);
                }
            }
        };
    }

    @Bean
    @Order(2)
    SecurityFilterChain appSecurityFilterChain(HttpSecurity http) throws Exception{

        http.cors(customizer-> {
            CorsConfigurationSource source= (s)->{
                CorsConfiguration cc= new CorsConfiguration();
                cc.setAllowedOrigins(appConfig.getAuthServer().getAllowedOrigins());
                cc.setAllowedMethods(List.of("*"));
                cc.setAllowedHeaders(List.of("*"));
                cc.setAllowCredentials(true);
                return cc;
            };
            customizer.configurationSource(source);

        });

        http.formLogin(f-> {
            f.usernameParameter("username");
            f.passwordParameter("password");
            f.loginPage("/login").permitAll();
        })
                .csrf(cc->cc.disable());
        http.authorizeHttpRequests(req-> req
                .requestMatchers(HttpMethod.POST, "oauth2/token","/test","/validatepersonaldetails","/validatebasicinfo","/validateusername","/sign-up").permitAll()
                .requestMatchers(HttpMethod.GET, "password-policy").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationServerSettings  authorizationServerSettings(){
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> auth2TokenCustomizer(){
        return context-> {
            context.getClaims().claim("authorities", context.getAuthorizedScopes());
        };
    }

//    @Bean
//    public RegisteredClientRepository registeredClientRepository(){
//        return new InMemoryRegisteredClientRepository(
//                RegisteredClient.withId(UUID.randomUUID().toString())
//                        .clientId("axkdw-skdsfs-ksdfks-akdfks")
//                        .clientSecret("jskex-kwics-iecmw-aziec")
//                        .clientAuthenticationMethod( ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                        .redirectUri("http://127.0.0.1:4200/portfolio")
//                        .build()
//        );
//    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails u = User.withUsername("kamal").password("password")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(u);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public CommandLineRunner commandLineRunner(SHAService service){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String verifier = service.verifier();
                System.out.println("Verifier: "+ verifier);
                String challenge = service.challenge(verifier);
                System.out.println("Challenge: " +challenge);
            }
        };
    }

}
