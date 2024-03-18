package yerong.blog.controller;

import org.springframework.security.test.context.support.WithSecurityContext;
import yerong.blog.domain.member.Role;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserSecurityContextFactory.class)
public @interface WithCustomMockUser {

    String nickname() default "yerong";
    String email () default "user@naver.com";
    String password () default  "1234";
}
