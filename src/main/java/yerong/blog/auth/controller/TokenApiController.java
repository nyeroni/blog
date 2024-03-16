package yerong.blog.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yerong.blog.auth.dto.request.CreateAccessTokenRequest;
import yerong.blog.auth.dto.response.CreateAccessTokenResponse;
import yerong.blog.auth.service.TokenService;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;


    @PostMapping("/api/token")
    public ResponseEntity<?> createNewAccessToken(@RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }
}
