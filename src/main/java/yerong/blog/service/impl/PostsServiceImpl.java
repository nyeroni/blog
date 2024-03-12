package yerong.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.blog.domain.Posts;
import yerong.blog.dto.PostsRequestDto;
import yerong.blog.repository.PostsRepository;
import yerong.blog.service.PostsService;

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
}
