package pine.demo.fileupload.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Accessors(fluent = true)
public class WarningException extends IllegalStateException {
    private static final long serialVersionUID = -3563739304187541301L;

    private final String i18nKey;
    private final HttpStatus status;

    public WarningException(String i18nKey, HttpStatus status) {
        this.i18nKey = i18nKey;
        this.status = status;
    }
}
