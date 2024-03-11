package yerong.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;
import yerong.blog.service.PostsService;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> savePosts(@RequestBody PostsRequestDto dto){
        Posts savedPost = postsService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);

    }
}
