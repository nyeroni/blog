package yerong.blog.service.impl.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.blog.domain.post.Posts;
import yerong.blog.dto.request.post.PostsRequestDto;
import yerong.blog.dto.request.post.UpdatePostRequestDto;
import yerong.blog.repository.post.PostsRepository;
import yerong.blog.service.post.PostsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    @Override
    @Transactional
    public Posts save(PostsRequestDto postsRequestDto) {
        return postsRepository.save(postsRequestDto.toEntity());
    }

    @Override
    @Transactional
    public List<Posts> findAll (){
        return postsRepository.findAll();
    }

    @Override
    @Transactional
    public Posts findById(Long id){
        return postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글 Id 입니다."));
    }

    @Override
    @Transactional
    public void delete(Long id){
        postsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Posts update(Long id, UpdatePostRequestDto requestDto){
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 Id 입니다."));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return post;

    }
}
