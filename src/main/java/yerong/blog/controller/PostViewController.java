package yerong.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yerong.blog.domain.Posts;
import yerong.blog.dto.response.PostListViewResponse;
import yerong.blog.dto.response.PostViewResponse;
import yerong.blog.service.PostsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostViewController {

    private final PostsService postsService;

    @GetMapping("/posts")
    public String getPosts(Model model){
        List<PostListViewResponse> posts = postsService.findAll().stream()
                .map(PostListViewResponse::new)
                .toList();
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id,  Model model){
        Posts post = postsService.findById(id);
        model.addAttribute("post", new PostViewResponse(post));
        return "post";
    }
}
