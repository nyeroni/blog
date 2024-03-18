package yerong.blog.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yerong.blog.auth.princiapl.PrincipalDetails;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.post.Posts;
import yerong.blog.dto.request.post.PostsRequestDto;
import yerong.blog.dto.request.post.UpdatePostRequestDto;
import yerong.blog.dto.response.post.PostsResponseDto;
import yerong.blog.service.MemberService;
import yerong.blog.service.PostsService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/posts")
    public ResponseEntity<?> savePosts(@RequestBody PostsRequestDto dto,  Principal principal){
        Posts savedPost = postsService.save(dto, principal.getName());

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

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequestDto dto){
        Posts updatedPost = postsService.update(id, dto);
        return ResponseEntity.ok().body(updatedPost);
    }
}
