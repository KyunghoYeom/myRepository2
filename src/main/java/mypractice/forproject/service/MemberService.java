package mypractice.forproject.service;

import lombok.RequiredArgsConstructor;
import mypractice.forproject.domain.Member;
import mypractice.forproject.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        System.out.println("hello");
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(String name) {

        if (!memberRepository.findByName(name).isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    @Transactional
    public void update(Long id, String name){
        Member member = memberRepository.findById(id).get();
        member.changeMemberName(name);
    }

    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }






}
