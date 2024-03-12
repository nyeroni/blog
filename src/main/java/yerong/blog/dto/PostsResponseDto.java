package yerong.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostsResponseDto {
    private final String title;
    private final String content;
}
