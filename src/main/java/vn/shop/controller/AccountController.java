package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.shop.constant.Constant;
import vn.shop.dto.AccountDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.service.AccountService;


@RestController
@RequestMapping(Constant.API.PUBLIC + "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity
            <ApiResponseDto<Page<AccountDto>>> getAccountList(
            @RequestParam Long gameId,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String code,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return accountService.getAccountDtoList(gameId, minPrice, maxPrice, code, sort, pageable);
    }

    @GetMapping("/detail/{accountId}")
    public ResponseEntity<ApiResponseDto<AccountDto>> getAccountDetail(@PathVariable Long accountId) {
        return accountService.getAccountDetailDto(accountId);
    }
}
