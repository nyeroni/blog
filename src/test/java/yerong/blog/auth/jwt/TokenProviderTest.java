package yerong.blog.auth.jwt;

import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import yerong.blog.auth.princiapl.PrincipalDetails;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.member.Role;
import yerong.blog.repository.member.MemberRepository;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("유저 정보와 만료 기간을 전달하여 토큰 생성")
    @Test
    public void generateToken () throws Exception{
        //given
        Member savedMember = memberRepository.save(Member.builder()
                .email("user@naver.com")
                .password("1234")
                .role(Role.USER)
                .build());
        //when
        String token = tokenProvider.generateToken(savedMember, Duration.ofDays(14));

        //then
        Long memberId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(memberId).isEqualTo(savedMember.getId());
    }

    @DisplayName("만료된 토큰이면 유효성 검증 실패")
    @Test
    public void validToken_invalidToken() throws Exception{
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis())).build().createToken(jwtProperties);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();

    }

    @DisplayName("유효한 토큰이면 유효성 검증 성공")
    @Test
    public void validToken_validToken() throws Exception{
        //given
        String token = JwtFactory.withDefaultValues()
                .createToken(jwtProperties);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("토큰 기반으로 인증 정보 가져옴")
    @Test
    public void getAuthentication() throws Exception{
        //given
        Member member = Member.builder()
                .email("user@email.com")
                .password("1234") // 이 비밀번호는 실제로 사용되는 비밀번호여야 합니다.
                .role(Role.USER)
                .build();
        String token = JwtFactory.builder()
                .subject(member.getEmail())
                .build()
                .createToken(jwtProperties);
        //when
        Authentication authentication = tokenProvider.getAuthentication(token,member );

        //then
        assertThat(((PrincipalDetails) authentication.getPrincipal()).getUsername()).isEqualTo(member.getEmail());

    }

    @DisplayName("토큰으로 member Id 가져오기")
    @Test
    public void getMemberId() throws Exception{
        //given
        Long memberId = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("id", memberId))
                .build()
                .createToken(jwtProperties);
        //when
        Long memberIdByToken = tokenProvider.getMemberId(token);

        //then
        assertThat(memberIdByToken).isEqualTo(memberId);
    }
}
