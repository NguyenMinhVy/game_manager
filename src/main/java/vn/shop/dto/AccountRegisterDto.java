package vn.shop.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class AccountRegisterDto implements Serializable {

    private String accountCode;

    @JsonIgnore
    private String userNameAccount;

    @JsonIgnore
    private String passwordAccount;

    private Long price;

    private String description;

    private Integer priority;

    private boolean isDisplay;

    private List<AccountImageDto> accountImageDtoList;
}
