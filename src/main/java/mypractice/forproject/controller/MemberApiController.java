package mypractice.forproject.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import mypractice.forproject.domain.Address;
import mypractice.forproject.domain.Member;
import mypractice.forproject.dto.*;
import mypractice.forproject.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    @PostMapping("/api/members")
    public CreateMemberResponseDto saveMember(@RequestBody @Valid CreateMemberRequestDto dto){
        //valid검사시 내가 원하는 것으로 바꾸고 싶으면 advice잡아서 하면된다
        Member member = new Member(dto.getName(), new Address(dto.getCity(), dto.getStreet(), dto.getZipcode()));
        Long joinId = memberService.join(member);
        return new CreateMemberResponseDto(joinId);

    }
    @PutMapping("api/members/{id}")
    public UpdateMemberResponseDto updateMember(@PathVariable("id")Long id,
                                                @RequestBody @Valid UpdateMemberRequestDto dto){
        memberService.update(id, dto.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponseDto(findMember);

    }

    @GetMapping("api/members")
    public Result members(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> dtos = findMembers.stream().map(m -> new MemberDto(m.getName(), m.getAddress().getCity()))
                .collect(Collectors.toList());
        return new Result(dtos, 1L);

    }


    @Data
    static class Result<T>{
        private T data;
        private Long count;

        public Result(T data, Long count) {
            this.data = data;
            this.count = count;
        }
    }






}
