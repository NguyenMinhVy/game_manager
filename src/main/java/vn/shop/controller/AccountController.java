package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.shop.dto.AccountDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/public/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponseDto<List<AccountDto>>> getAccountList(
            @RequestParam Long gameId,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String code,
            @RequestParam(defaultValue = "asc") String sort // asc hoặc desc
    ) {
        return accountService.getAccountDtoList(gameId, minPrice, maxPrice, code, sort);
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponseDto<AccountDto>> getAccountDetail(@RequestParam Long accountId) {
        return accountService.getAccountDetailDto(accountId);
    }
}
