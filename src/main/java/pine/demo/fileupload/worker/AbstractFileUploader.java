package pine.demo.fileupload.worker;

import org.springframework.web.multipart.MultipartFile;
import pine.demo.fileupload.controller.dto.FileUploadResponseDto;

import java.nio.file.Path;
import java.util.UUID;

public abstract class AbstractFileUploader {
    public FileUploadResponseDto saveFile(MultipartFile file) {
        checkFileSize(file);
        String uuidFolder = generateUUID();
        Path storagePath = createDirectory(uuidFolder);

        return storeFile(file, uuidFolder, storagePath);
    }

    protected abstract void checkFileSize(MultipartFile file);
    protected abstract Path createDirectory(String uuidFolder);
    protected abstract FileUploadResponseDto storeFile(MultipartFile file, String uuidFolder, Path storagePath);
    protected String generateUUID() {
        return UUID.randomUUID().toString();
    }
}