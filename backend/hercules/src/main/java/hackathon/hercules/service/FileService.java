package hackathon.hercules.service;

import hackathon.hercules.entity.FileEntity;
import hackathon.hercules.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    public FileEntity createFile(FileEntity file) {
        return repository.save(file);
    }

    public List<FileEntity> getAllFiles() {
        return repository.findAll();
    }

    public FileEntity getFileById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public FileEntity updateFile(FileEntity file) {
        return repository.save(file);
    }

    public void deleteFile(Long id) {
        repository.deleteById(id);
    }
}