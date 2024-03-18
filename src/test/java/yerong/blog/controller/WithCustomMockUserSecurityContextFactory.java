package yerong.blog.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import yerong.blog.auth.princiapl.PrincipalDetails;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.member.Role;

import java.util.ArrayList;
import java.util.List;
public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        if (annotation != null) {
            // WithCustomMockUser 어노테이션으로부터 값 가져오기
            String nickname = annotation.nickname();
            String email = annotation.email();
            String password = annotation.password();

            // 값이 null이 아닌지 확인하여 Member 객체 생성
            if (nickname != null && email != null && password != null) {
                Member principal = Member.builder()
                        .nickname(nickname)
                        .email(email)
                        .password(password)
                        .role(Role.USER)
                        .build();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, principal.getPassword());
                authenticationToken.setDetails(new PrincipalDetails(principal));

                context.setAuthentication(authenticationToken);
            } else {
                throw new IllegalArgumentException("WithCustomMockUser 어노테이션의 필드가 null입니다.");
            }
        }
        return context;
    }
}
