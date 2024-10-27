package hackathon.hercules.service;

import hackathon.hercules.entity.PostEntity;
import hackathon.hercules.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostEntity createPost(PostEntity post) {
        return repository.save(post);
    }

    public List<PostEntity> getAllPosts() {
        return repository.findAll();
    }

    public PostEntity getPostById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PostEntity updatePost(PostEntity post) {
        return repository.save(post);
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    public boolean isPostExists(Long id) {
        return postRepository.existsById(id);
    }
}