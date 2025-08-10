package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.shop.dto.AccountDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<List<AccountDto>>> getAccountList(@RequestParam Long gameId) {
        return accountService.getAccountDtoList(gameId);
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponseDto<AccountDto>> getAccountDetail(@RequestParam Long accountId) {
        return accountService.getAccountDetailDto(accountId);
    }
}
