package pine.demo.fileupload.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponseDto implements Serializable {
    private static final long serialVersionUID = 2575040946072434654L;

    @Schema(description = "狀態")
    @JsonProperty("status")
    private String status;

    @Schema(description = "回傳訊息")
    @JsonProperty("message")
    private String message;
}
