package org.practice.pojoClasses.webPojo;


public class LoginScenario {
        private String scenario;
        private String username;
        private String password;
        private String role;
        private boolean acceptTerms;
        private boolean requiresModalOk;
        private String expectedPage;
        private String expectedToast;

        public void setScenario(String scenario){
                this.scenario=scenario;
        }

        public String getScenario() {
                return scenario;
        }

        public void setUsername(String username){
                this.username=username;
        }
        public String getUsername() {
                return username;
        }
        public void setPassword(String password){
                this.password=password;
        }

        public String getPassword() {
                return password;
        }
        public void setRole(String role){
                this.role=role;
        }

        public String getRole() {
                return role;
        }
        public void setAcceptTerms(Boolean acceptTerms){
                this.acceptTerms=acceptTerms;
        }
        public Boolean getAcceptTerms() {
                return acceptTerms;
        }
        public void setExpectedPage(String expectedPage){
                this.expectedPage=expectedPage;
        }

        public String getExpectedPage(){
                return expectedPage;
        }

        public void setExpectedToast(String expectedToast){
                this.expectedToast=expectedToast;
        }

        public String getExpectedToast(){
                return expectedToast;
        }
        public void setRequiresModalOk(boolean requiresModalOk){
                this.requiresModalOk=requiresModalOk;
        }

        public boolean isRequiresModalOk(){
                return requiresModalOk;
        }
        }

