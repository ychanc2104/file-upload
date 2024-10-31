package pine.demo.fileupload.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pine.demo.fileupload.base.WarningException;
import pine.demo.fileupload.controller.dto.FileDownloadRequestDto;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@RequiredArgsConstructor
@Service
public class FileDownloadService {

    public ResponseEntity<Resource> request(FileDownloadRequestDto input) {
        String uuid = input.getUuid();
        String filename = input.getFilename();

        try {
            Path filePath = Paths.get("uploads", uuid, filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new WarningException("file.download.error", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (IOException e) {
            throw new WarningException("file.download.error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
