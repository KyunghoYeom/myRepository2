package mypractice.forproject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateMemberRequestDto {
    @NotEmpty
    private String name;

    @NotEmpty
    private String city;

    private String street;
    private String zipcode;

}
