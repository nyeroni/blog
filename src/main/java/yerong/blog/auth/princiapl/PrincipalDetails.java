package yerong.blog.auth.princiapl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import yerong.blog.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Getter
@Setter
public class PrincipalDetails implements UserDetails, OAuth2User {
    private Member member;
    Map<String, Object> attributes;
    public PrincipalDetails() {
    }
    public PrincipalDetails(Member member){
        this.member = member;
    }

    public PrincipalDetails (Member member,  Map<String, Object> attributes){
        this.member = member;
        this.attributes = attributes;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> member.getRole().getKey());
        return collector;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {

        if (this.member != null) {
            return this.member.getEmail(); // member가 null이 아닌 경우에만 getEmail() 호출
        } else {
            // member가 null인 경우 예외 처리 또는 기본값 반환
            throw new IllegalStateException("Member object is null");
        }
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return (String) attributes.get("nickname");
    }
}
