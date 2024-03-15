package yerong.blog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import yerong.blog.domain.Posts;

@Getter
@AllArgsConstructor
@Setter
public class PostListViewResponse {

    private final Long id;
    private final String title;
    private final String content;

    public PostListViewResponse(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
    }
}
