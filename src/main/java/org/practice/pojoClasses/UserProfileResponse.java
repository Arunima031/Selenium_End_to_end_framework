package org.practice.pojoClasses;

import java.util.List;

public class UserProfileResponse {
        private String name;
        private String job;
        private Address address;
        private List<String> skills;
        private String id;
        private String createdAt;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getJob() { return job; }
        public void setJob(String job) { this.job = job; }

        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }

        public List<String> getSkills() { return skills; }
        public void setSkills(List<String> skills) { this.skills = skills; }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    }
