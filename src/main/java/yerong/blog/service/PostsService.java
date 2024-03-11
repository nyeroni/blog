package yerong.blog.service;

import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;

public interface PostsService {

    Posts save(PostsRequestDto postsRequestDto);

}
