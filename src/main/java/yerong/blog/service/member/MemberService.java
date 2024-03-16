package yerong.blog.service.member;

import yerong.blog.domain.member.Member;
import yerong.blog.dto.request.LoginRequestDto;

public interface MemberService {
    Long save(LoginRequestDto loginRequest);
    Member findById(Long memberId);
}
