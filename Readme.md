**clients**
- id
- client_id
- secret
- authentication
**redirect_urls**
- id
- url
- client
**scopes**
- id
- scope
- client
**grant_types**
- id
- grant_type
- client
**Code verifier and challenge**
- code verifier is a 32 byte piece of data which is base64 encoded without padding.
- code challenge is created using sha256 hash function over code verifier. It is also base64 encoded without padding.
http://localhost:8080/oauth2/authorize?response_type=code&client_id=axkdw-skdsfs-ksdfks-akdfks&scope=openid&redirect_uri=http://127.0.0.1:4200/portfolio&code_challenge=dDAsHCRCvEx7DZ4g4y_iXMF65fIVdJR8cUFDTAFB6Gc&code_challenge_method=S256
verifier=random()
challenge=sha256(verifier)
**docker mysql**
- docker run -it  --rm -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=projectdb -v ${PWD}/db:/var/lib/mysql --net db-network --name db mysql:5.7
