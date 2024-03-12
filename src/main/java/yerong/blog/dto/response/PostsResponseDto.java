package yerong.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yerong.blog.domain.Posts;

@Getter
@AllArgsConstructor
public class PostsResponseDto {
    private final String title;
    private final String content;

    public PostsResponseDto(Posts posts){
        this.title = posts.getTitle();
        this.content = posts.getContent();
    }
}
