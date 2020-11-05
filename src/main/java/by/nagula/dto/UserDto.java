package by.nagula.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class UserDto {
    @NotBlank
    @NotEmpty
    private String login;
    @NotBlank
    @NotEmpty
    private String password;

}
