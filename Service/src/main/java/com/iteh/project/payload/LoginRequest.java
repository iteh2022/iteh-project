package com.iteh.project.payload;

import lombok.Data;

        import javax.validation.constraints.NotBlank;
@Data
public class LoginRequest {
    @NotBlank(message = "{notblank.username}")
    private String username;
    @NotBlank(message = "{notblank.password}")
    private String password;
}

