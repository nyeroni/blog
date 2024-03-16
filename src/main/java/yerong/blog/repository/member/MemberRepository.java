package yerong.blog.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import yerong.blog.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
