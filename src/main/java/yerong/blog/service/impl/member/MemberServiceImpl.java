package yerong.blog.service.impl.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.member.Role;
import yerong.blog.dto.request.LoginRequestDto;
import yerong.blog.repository.member.MemberRepository;
import yerong.blog.service.member.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public Long save(LoginRequestDto loginRequest){
        return memberRepository.save(Member.builder()
                .email(loginRequest.getEmail())
                .password(passwordEncoder.encode(loginRequest.getPassword()))
                .role(Role.USER)
                .build()).getId();

    }

    @Override
    @Transactional
    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(()-> new IllegalArgumentException("찾을 수 없는 회원 id입니다."));
    }
}
