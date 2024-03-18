package yerong.blog.controller.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yerong.blog.auth.dto.request.LoginRequestDto;
import yerong.blog.service.MemberService;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/user")
    public String signup(LoginRequestDto requestDto){
        memberService.save(requestDto);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false)String error, Model model){
        if (error != null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
