package yerong.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;
import yerong.blog.dto.PostsResponseDto;
import yerong.blog.service.PostsService;

import java.util.List;
import java.util.stream.Collectors;

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
}
