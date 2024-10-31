package pine.demo.fileupload.worker;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pine.demo.fileupload.base.WarningException;
import pine.demo.fileupload.controller.dto.FileUploadResponseDto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class StandardFileUploader extends AbstractFileUploader {

    @Override
    protected void checkFileSize(MultipartFile file) {
        long maxFileSize = 10 * 1024 * 1024; // 10 MB
        if (file.getSize() > maxFileSize) {
            throw new WarningException("file.size.exceeded", HttpStatus.BAD_REQUEST );
        }
    }

    @Override
    protected Path createDirectory(String uuidFolder) {
        Path storagePath = Paths.get("uploads", uuidFolder);
        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new WarningException("file.save.failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return storagePath;
    }

    @Override
    protected FileUploadResponseDto storeFile(MultipartFile file, String uuidFolder, Path storagePath) {
        String originalFilename = file.getOriginalFilename();
        Path filePath = storagePath.resolve(originalFilename);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            FileUploadResponseDto res = new FileUploadResponseDto();
            res.setStatus("Success");
            res.setMessage("File uploaded successfully");
            res.setFileUrl("http://localhost:8080/api/download/" + uuidFolder + "/" + originalFilename);

            return res;
        } catch (IOException e) {
            throw new WarningException("file.save.failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}