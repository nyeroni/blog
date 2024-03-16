package yerong.blog.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import yerong.blog.domain.post.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
