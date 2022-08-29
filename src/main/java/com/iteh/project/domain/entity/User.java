package com.iteh.project.domain.entity;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


    public static class Builder {
        private String email;
        private String password;

        public Builder() {
        }

        public Builder createEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder createPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(
                    null,
                    email,
                    password
            );
        }
    }

}