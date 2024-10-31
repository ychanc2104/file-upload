package pine.demo.fileupload.worker;

import org.springframework.web.multipart.MultipartFile;

public class PdfValidationStrategy implements FileValidationStrategy {
    @Override
    public boolean validate(MultipartFile file) {
        return "application/pdf".equals(file.getContentType());
    }
}