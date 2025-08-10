package vn.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Long gameId;

    private String gameName;

    private String description;

    private List<AccountDto> accountDtoList;
}
