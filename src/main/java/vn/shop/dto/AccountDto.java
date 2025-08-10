package vn.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long accountId;

    private String accountCode;

    @JsonIgnore
    private String userNameAccount;

    @JsonIgnore
    private String passwordAccount;

    private Long sellPrice;

    private String description;

    private Integer priority;

    private boolean isDisplay;

    private Long gameId;

    private List<AccountImageDto> accountImageDtoList;
}
