package yerong.blog.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import yerong.blog.dto.request.LoginRequestDto;
import yerong.blog.service.member.MemberService;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/user")
    public String signup(LoginRequestDto requestDto){
        memberService.save(requestDto);
        return "redirect:/login";
    }
}
