package pine.demo.fileupload.worker;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidationStrategy {
    boolean validate(MultipartFile file);
}
