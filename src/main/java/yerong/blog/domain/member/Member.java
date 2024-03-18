package yerong.blog.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import yerong.blog.domain.BaseTimeEntity;
import yerong.blog.domain.post.Posts;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    @JsonIgnoreProperties({"member"})
    private List<Posts> postsList = new ArrayList<>();


    @Builder
    public Member(String email, String password, String nickname, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }

    public Member updateNickname(String nickname){
        this.nickname = nickname;
        return this;
    }
}
