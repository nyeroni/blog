package yerong.blog.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yerong.blog.domain.post.Posts;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class PostViewResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String email;
    private boolean canEdit;

    public PostViewResponse(Posts posts){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.createdAt = posts.getCreatedAt();
        this.email = posts.getMember().getEmail();
        this.canEdit = canEdit;

    }

    public PostViewResponse(Posts posts,boolean canEdit){
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.createdAt = posts.getCreatedAt();
        this.email = posts.getMember().getEmail();
        this.canEdit = canEdit;

    }
}
