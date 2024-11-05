package mypractice.forproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private Long memberId;
    @NotNull
    private Long itemId;
    private int count;
}
