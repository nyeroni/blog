package yerong.blog.service.impl.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yerong.blog.domain.member.Member;
import yerong.blog.domain.post.Posts;
import yerong.blog.dto.request.post.PostsRequestDto;
import yerong.blog.dto.request.post.UpdatePostRequestDto;
import yerong.blog.repository.member.MemberRepository;
import yerong.blog.repository.post.PostsRepository;
import yerong.blog.service.PostsService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostsServiceImpl implements PostsService {
    private final PostsRepository postsRepository;
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Posts save(PostsRequestDto postsRequestDto, String email) {
        String eum = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("=====++" + eum);

        Posts post = postsRequestDto.toEntity();
        Member member = memberRepository.findByEmail(email).orElse(null);
        post.setMember(member);
        return postsRepository.save(post);
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
        Posts post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 회원 id입니다."));
        authorizePostMember(post);
        postsRepository.delete(post);
    }
    @Override
    @Transactional
    public Posts update(Long id, UpdatePostRequestDto requestDto){
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 Id 입니다."));
        authorizePostMember(post);
        post.update(requestDto.getTitle(), requestDto.getContent());
        return post;

    }

    private static void authorizePostMember(Posts post) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("=====++" + email);
        if(post.getMember().getEmail().equals(email)){
            throw new IllegalArgumentException("not authorized");
        }
    }
}
