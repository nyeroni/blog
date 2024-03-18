package yerong.blog.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.post.Posts;
import yerong.blog.dto.response.post.PostListViewResponse;
import yerong.blog.dto.response.post.PostViewResponse;
import yerong.blog.service.MemberService;
import yerong.blog.service.PostsService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostViewController {

    private final PostsService postsService;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "redirect:/posts";
    }
    @GetMapping("/posts")
    public String getPosts(Model model){
        List<PostListViewResponse> posts = postsService.findAll().stream()
                .map(PostListViewResponse::new)
                .toList();
        model.addAttribute("posts", posts);

        return "postList";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal){
        Posts post = postsService.findById(id);
        String email = principal.getName();
        Member member = memberService.findByEmail(email);

        boolean canEdit = member.getId().equals(post.getMember().getId());
        model.addAttribute("post", new PostViewResponse(post, canEdit));
        return "post";
    }

    @GetMapping("/new-post")
    public String newPost(@RequestParam(required = false) Long id, Model model){ //id가 있을 수도 없을 수도
        if(id == null){ //id가 없다면 생성
            model.addAttribute("post", new PostViewResponse());
        }
        else{ //id가 있다면 수정
            Posts post = postsService.findById(id);
            model.addAttribute("post", new PostViewResponse(post));
        }
        return "newPost";
    }
}
