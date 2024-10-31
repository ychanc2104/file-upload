package pine.demo.fileupload.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pine.demo.fileupload.base.WarningException;
import pine.demo.fileupload.controller.dto.FileUploadRequestDto;
import pine.demo.fileupload.controller.dto.FileUploadResponseDto;
import pine.demo.fileupload.worker.*;

import java.util.List;


@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final StandardFileUploader standardFileUploader;

    public FileUploadResponseDto request(FileUploadRequestDto input) {
        MultipartFile file = input.getFile();

        if (file == null) {
            throw new WarningException("file.not.exist", HttpStatus.BAD_REQUEST);
        }

        if (getStrategies().stream().noneMatch(strategy -> strategy.validate(file))) {
            throw new WarningException("file.type.invalid", HttpStatus.BAD_REQUEST);
        }

        return standardFileUploader.saveFile(file);
    }

    private List<FileValidationStrategy> getStrategies() {
        return List.of(
                new PdfValidationStrategy(),
                new ImageValidationStrategy(),
                new CsvValidationStrategy()
        );
    }
}
