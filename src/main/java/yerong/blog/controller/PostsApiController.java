package yerong.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yerong.blog.domain.Posts;
import yerong.blog.dto.request.PostsRequestDto;
import yerong.blog.dto.response.PostsResponseDto;
import yerong.blog.service.PostsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> savePosts(@RequestBody PostsRequestDto dto){
        Posts savedPost = postsService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<?> findAllPosts(){

        List<PostsResponseDto> postsResponseDtos = postsService.findAll()
                .stream()
                .map(PostsResponseDto::new)
                .toList();

        return ResponseEntity.ok().body(postsResponseDtos);
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<?> findPost(@PathVariable Long id){
        Posts post = postsService.findById(id);

        return ResponseEntity.ok().body(new PostsResponseDto(post));
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
