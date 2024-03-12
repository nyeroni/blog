package yerong.blog.service;

import yerong.blog.domain.Posts;
import yerong.blog.dto.request.PostsRequestDto;
import yerong.blog.dto.request.UpdatePostRequestDto;

import java.util.List;

public interface PostsService {

    Posts save(PostsRequestDto postsRequestDto);
    List<Posts> findAll ();
    Posts findById(Long id);
    void delete(Long id);
    Posts update(Long id, UpdatePostRequestDto requestDto);
}
