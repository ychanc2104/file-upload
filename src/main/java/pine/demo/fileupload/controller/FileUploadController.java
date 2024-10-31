package pine.demo.fileupload.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pine.demo.fileupload.controller.dto.FileDownloadRequestDto;
import pine.demo.fileupload.controller.dto.FileUploadRequestDto;
import pine.demo.fileupload.controller.dto.FileUploadResponseDto;
import pine.demo.fileupload.service.FileDownloadService;
import pine.demo.fileupload.service.FileUploadService;

@RequiredArgsConstructor
@Tag(name = "file upload")
@RequestMapping("api")
@RestController("FileUploadController")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Operation(description = "上傳檔案")
    @PostMapping(path = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FileUploadResponseDto upload(
            @RequestPart(value = "file", required = false) MultipartFile file) {
        FileUploadRequestDto input = new FileUploadRequestDto();
        input.setFile(file);

        return fileUploadService.request(input);
    }

    private final FileDownloadService fileDownloadService;

    @GetMapping("/download/{uuid}/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String uuid, @PathVariable String filename) {
        FileDownloadRequestDto input = new FileDownloadRequestDto();
        input.setUuid(uuid);
        input.setFilename(filename);

        return fileDownloadService.request(input);
    }
}
