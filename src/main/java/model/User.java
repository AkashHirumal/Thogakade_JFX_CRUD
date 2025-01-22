package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private String username;
    private String email;
    private String password;
}
