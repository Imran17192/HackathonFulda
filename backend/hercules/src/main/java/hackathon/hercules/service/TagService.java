package hackathon.hercules.service;

import hackathon.hercules.entity.Tag;
import hackathon.hercules.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;

    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    public boolean isTagExists(Long id) {
        return tagRepository.existsById(id);
    }

    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

}
