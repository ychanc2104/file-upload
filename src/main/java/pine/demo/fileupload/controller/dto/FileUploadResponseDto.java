package pine.demo.fileupload.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import pine.demo.fileupload.base.BaseResponseDto;

@Data
public class FileUploadResponseDto extends BaseResponseDto {
    private static final long serialVersionUID = 247774194559345595L;

    @Schema(description = "檔案連結")
    @JsonProperty("file_url")
    private String fileUrl;
}


