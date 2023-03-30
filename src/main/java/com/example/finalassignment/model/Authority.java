package com.example.finalassignment.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "authorities")
@IdClass(AuthorityKey.class)
    public class Authority implements Serializable {

        @Id
        private String username;
        @Id
        private String authority;


        public Authority() {}

        public Authority(String username, String authority) {
            this.username = username;
            this.authority = authority;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }

