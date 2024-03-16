package yerong.blog.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yerong.blog.domain.post.Posts;

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
