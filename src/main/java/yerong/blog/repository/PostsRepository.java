package yerong.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yerong.blog.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
