package yerong.blog.service;

import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;

import java.util.List;

public interface PostsService {

    Posts save(PostsRequestDto postsRequestDto);
    List<Posts> findAll ();
    Posts findById(Long id);
}
