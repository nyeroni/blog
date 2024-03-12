package yerong.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
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
import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;
import yerong.blog.repository.PostsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //MockMvc 생성 및 자동 구성
class PostsApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;


    @Autowired
    protected ObjectMapper objectMapper; // 직렬화, 역직렬화를 위한 클래스 (자바객채 <-> JSON 변환)

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void mockSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        postsRepository.deleteAll();
    }

    @DisplayName("글 추가 성공")
    @Test
    public void addPost() throws Exception{
        //given
        final String url = "/api/posts";
        final String title = "hi";
        final String content = "merong";
        final PostsRequestDto postsRequestDto = new PostsRequestDto(title, content);

        //객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(postsRequestDto);

        //when
        //요청 전송
        ResultActions resultActions = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        //then
        resultActions.andExpect(status().isCreated());

        List<Posts> posts = postsRepository.findAll();

        assertThat(posts.size()).isEqualTo(1);
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }
}