package yerong.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yerong.blog.domain.Posts;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostsRequestDto {

    private String title;
    private String content;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
