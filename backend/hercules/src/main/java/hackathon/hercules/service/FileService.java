package hackathon.hercules.service;

import hackathon.hercules.entity.File;
import hackathon.hercules.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    public FileRepository fileRepository;

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

    public boolean isFileExists(Long id) {
        return fileRepository.existsById(id);
    }

    public void saveFile(File file) {
        fileRepository.save(file);
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }
}
