package vn.shop.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AccountRegisterDto implements Serializable {

    private String accountCode;

    private Long sellPrice;

    private String description;

    private Integer priority;

    private boolean isDisplay;

    private Integer gameId;

    private List<MultipartFile> files;

    private String oldImages;
}
