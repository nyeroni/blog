package yerong.blog.dto.request.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yerong.blog.domain.post.Posts;

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
