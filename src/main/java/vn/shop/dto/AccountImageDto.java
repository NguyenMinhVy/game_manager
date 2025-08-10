package vn.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountImageDto {

    private Long accountImageId;

    private Long accountId;

    private String imageName;

    private String imageUrl;
}
