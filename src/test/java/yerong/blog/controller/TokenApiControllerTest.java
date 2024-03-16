package yerong.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import yerong.blog.auth.domain.RefreshToken;
import yerong.blog.auth.dto.request.CreateAccessTokenRequest;
import yerong.blog.auth.jwt.JwtFactory;
import yerong.blog.auth.jwt.JwtProperties;
import yerong.blog.auth.repository.RefreshTokenRepository;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.member.Role;
import yerong.blog.repository.member.MemberRepository;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        memberRepository.deleteAll();
    }

    @DisplayName("새로운 엑세스토큰 발급")
    @Test
    public void createNewAccessToken() throws Exception{
        //given
        final String url = "/api/token";

        Member savedMember = memberRepository.save(Member.builder()
                .email("user@email.com")
                .password("1234")
                .role(Role.USER)
                .build());

        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", savedMember.getId()))
                .build()
                .createToken(jwtProperties);

        refreshTokenRepository.save(new RefreshToken(savedMember.getId(), refreshToken));

        CreateAccessTokenRequest createAccessTokenRequest = new CreateAccessTokenRequest();
        createAccessTokenRequest.setRefreshToken(refreshToken);

        final String requestBody = objectMapper.writeValueAsString(createAccessTokenRequest);

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());

    }
}
