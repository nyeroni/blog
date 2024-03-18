package yerong.blog.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yerong.blog.auth.jwt.TokenProvider;
import yerong.blog.domain.member.Member;
import yerong.blog.service.MemberService;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("유효하지 않은 refresh token 입니다");
        }
        Long memberId = refreshTokenService.findByRefreshToken(refreshToken).getMemberId();
        Member member = memberService.findById(memberId);;

        return  tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}
