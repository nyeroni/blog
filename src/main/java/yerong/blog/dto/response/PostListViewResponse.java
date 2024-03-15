package yerong.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import yerong.blog.domain.Posts;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
public class PostListViewResponse {

    private Long id;
    private String title;
    private String content;
    public PostListViewResponse(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
    }
}
