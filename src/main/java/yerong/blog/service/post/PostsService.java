package yerong.blog.service.post;

import yerong.blog.domain.post.Posts;
import yerong.blog.dto.request.post.PostsRequestDto;
import yerong.blog.dto.request.post.UpdatePostRequestDto;

import java.util.List;

public interface PostsService {

    Posts save(PostsRequestDto postsRequestDto);
    List<Posts> findAll ();
    Posts findById(Long id);
    void delete(Long id);
    Posts update(Long id, UpdatePostRequestDto requestDto);
}
