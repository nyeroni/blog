package yerong.blog.service;

import yerong.blog.domain.member.Member;
import yerong.blog.auth.dto.request.LoginRequestDto;

public interface MemberService {
    Long save(LoginRequestDto loginRequest);
    Member findById(Long memberId);
    Member findByEmail(String email);
}
