package com.tax.service.dto;

import com.tax.service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String lastName;

    @Pattern(regexp = "[A-Za-z А-Яа-я]{2,30}", message = "Name must be correct!")
    private String firstName;
    private String middleName;
    private String email;
    private String password;
    private Role role;

}
