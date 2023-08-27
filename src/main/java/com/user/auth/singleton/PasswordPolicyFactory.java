package com.user.auth.singleton;


public class PasswordPolicyFactory {
    private static PasswordPolicyDef passwordPolicyDef = null;

    private PasswordPolicyFactory(){}

    public static synchronized PasswordPolicyDef getInstance(){
        if(passwordPolicyDef == null){
            passwordPolicyDef = new PasswordPolicyDef();
        }
        return passwordPolicyDef;
    }

    public static class PasswordPolicyDef{
        private   int MIN_COMPLEX_RULES=3;
        private   int MIN_UPPER_CASE_CHARS = 1;
        private  int MIN_LOWER_CASE_CHARS=1;
        private  int MIN_DIGIT_CASE_CHARS=1;
        private  int MIN_SPECIAL_CASE_CHARS=1;
        private  int MAX_REPETATIVE_CHARS=3;
        private PasswordPolicyDef(){}

        public int getMaxRepetativeChars() {
            return this.MAX_REPETATIVE_CHARS;
        }

        public int getMIN_COMPLEX_RULES() {
            return MIN_COMPLEX_RULES;
        }

        public int getMIN_UPPER_CASE_CHARS() {
            return MIN_UPPER_CASE_CHARS;
        }

        public int getMIN_LOWER_CASE_CHARS() {
            return MIN_LOWER_CASE_CHARS;
        }

        public int getMIN_DIGIT_CASE_CHARS() {
            return MIN_DIGIT_CASE_CHARS;
        }

        public int getMIN_SPECIAL_CASE_CHARS() {
            return MIN_SPECIAL_CASE_CHARS;
        }

        public int getMAX_REPETATIVE_CHARS() {
            return MAX_REPETATIVE_CHARS;
        }
    }
}
