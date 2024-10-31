package pine.demo.fileupload.worker;

import org.springframework.web.multipart.MultipartFile;

public class CsvValidationStrategy implements FileValidationStrategy {
    @Override
    public boolean validate(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().equals("text/csv");
    }
}