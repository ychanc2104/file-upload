package pine.demo.fileupload;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import pine.demo.fileupload.base.WarningException;
import pine.demo.fileupload.controller.dto.FileUploadRequestDto;
import pine.demo.fileupload.service.FileUploadService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UploadTests {
    @Mock
    private MultipartFile file;

    @InjectMocks
    private FileUploadService fileUploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowWarningExceptionWhenFileIsNull() {
        FileUploadRequestDto input = new FileUploadRequestDto();
        input.setFile(null);

        WarningException exception = assertThrows(WarningException.class, () -> fileUploadService.request(input));
        assertEquals("file.not.exist", exception.i18nKey());
        assertEquals(HttpStatus.BAD_REQUEST, exception.status());
    }

    @Test
    void shouldThrowWarningExceptionWhenFileTypeIsInvalid() {
        when(file.getContentType()).thenReturn("application/unknown");
        FileUploadRequestDto input = new FileUploadRequestDto();
        input.setFile(file);

        WarningException exception = assertThrows(WarningException.class, () -> fileUploadService.request(input));
        assertEquals("file.type.invalid", exception.i18nKey());
        assertEquals(HttpStatus.BAD_REQUEST, exception.status());
    }

}

