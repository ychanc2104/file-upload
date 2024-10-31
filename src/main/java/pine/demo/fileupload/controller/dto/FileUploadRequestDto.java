package pine.demo.fileupload.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class FileUploadRequestDto implements Serializable {
    private static final long serialVersionUID = 8505668008889172520L;

    @JsonProperty("file")
    private MultipartFile file;
}


