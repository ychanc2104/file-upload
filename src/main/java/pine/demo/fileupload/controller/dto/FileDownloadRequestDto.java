package pine.demo.fileupload.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class FileDownloadRequestDto implements Serializable {
    private static final long serialVersionUID = -8752586771959398831L;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("filename")
    private String filename;
}


