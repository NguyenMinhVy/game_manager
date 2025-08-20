package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import vn.shop.config.CustomUserDetails;
import vn.shop.constant.Constant;
import vn.shop.dto.AccountSaleDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.service.AccountSaleService;


@RestController
@RequestMapping(value = Constant.API.ADMIN, method = {RequestMethod.GET, RequestMethod.POST})
public class AdminSaleController {

    @Autowired
    private AccountSaleService accountSaleService;

    @PostMapping(value = "/accountSale/create")
    public ResponseEntity<ApiResponseDto<AccountSaleDto>> createAccountSale(@RequestBody AccountSaleDto requestDto,
                                                                        @AuthenticationPrincipal CustomUserDetails currentUser) {
        AccountSaleDto accountSaleDto = accountSaleService.createAccountSale(requestDto, currentUser);
        ApiResponseDto<AccountSaleDto> successResponseDto = ApiResponseDto.<AccountSaleDto>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountSaleDto)
                .build();
        return ResponseEntity.ok().body(successResponseDto);
    }

    @PostMapping(value = "/accountSale/update")
    public ResponseEntity<ApiResponseDto<AccountSaleDto>> updateAccountSale(@RequestBody AccountSaleDto requestDto,
                                                                        @AuthenticationPrincipal CustomUserDetails currentUser) {
        AccountSaleDto accountSaleDto = accountSaleService.updateAccountSale(requestDto, currentUser);
        ApiResponseDto<AccountSaleDto> successResponseDto = ApiResponseDto.<AccountSaleDto>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountSaleDto)
                .build();
        return ResponseEntity.ok().body(successResponseDto);
    }
}


