package yerong.blog.auth.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import yerong.blog.auth.princiapl.PrincipalDetails;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.member.Role;
import yerong.blog.repository.member.MemberRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> userInfo = oAuth2User.getAttributes();
        String email = (String) userInfo.get("email");
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());


        Optional<Member> findMemberOptional = memberRepository.findByEmail(email);

        if(!findMemberOptional.isPresent()){ //회원가입
            Member member = Member.builder()
                    .email(email)
                    .password(password)
                    .role(Role.USER)
                    .build();
            return new PrincipalDetails(memberRepository.save(member), userInfo);
        }
        else{ //로그인
            return new PrincipalDetails(findMemberOptional.get(), userInfo);
        }
    }

}
