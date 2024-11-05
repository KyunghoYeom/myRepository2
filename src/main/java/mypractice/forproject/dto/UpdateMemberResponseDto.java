package mypractice.forproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mypractice.forproject.domain.Member;

@Data
public class UpdateMemberResponseDto {
    private Long id;
    private String name;

    public UpdateMemberResponseDto(Member member) {
        id = member.getId();
        name = member.getName();
    }
}
