package vn.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequestDto implements Serializable {

    private String phoneNumber;

    private String password;
}
