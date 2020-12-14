package com.tax.service.dto;

import com.tax.service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    @Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="Invalid email form")
    private String email;
    @Size(min=6, message="Password must be at least 7 characters")
    private String password;
    private Role role;

}
