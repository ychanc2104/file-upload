package pine.demo.fileupload.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pine.demo.fileupload.base.BaseResponseDto;
import pine.demo.fileupload.base.WarningException;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    private final MessageSource messageSource;

    @ExceptionHandler(WarningException.class)
    public ResponseEntity<BaseResponseDto> handleWarningException(WarningException warning) {
        return responseOf(
                warning.status(),
                messageSource.getMessage(warning.i18nKey(), null, LocaleContextHolder.getLocale()
                )
        );
    }

    private ResponseEntity<BaseResponseDto> responseOf(HttpStatus status, String message) {
        BaseResponseDto responseDto = new BaseResponseDto();
        responseDto.setStatus("Failure");
        responseDto.setMessage(message);

        return ResponseEntity.status(status).body(responseDto);
    }
}
