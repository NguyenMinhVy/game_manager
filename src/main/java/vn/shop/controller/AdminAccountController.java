package vn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import vn.shop.config.CustomUserDetails;
import vn.shop.constant.Constant;
import vn.shop.dto.AccountDto;
import vn.shop.dto.AccountImageDto;
import vn.shop.dto.AccountRegisterDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.entity.Account;
import vn.shop.mapper.AccountMapper;
import vn.shop.repository.AccountRepository;
import vn.shop.service.AccountImageService;
import vn.shop.service.AccountService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = Constant.API.ADMIN, method = {RequestMethod.GET, RequestMethod.POST})
public class AdminAccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountImageService accountImageService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;


    @GetMapping("/account/list")
    public ResponseEntity<ApiResponseDto<Page<AccountDto>>>  getListAccount(
            @RequestParam Long gameId,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String code,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    )  {
        Pageable pageable = PageRequest.of(page, size);
        return accountService.getAccountDtoList(gameId, minPrice, maxPrice, code, sort, pageable);
    }

    @GetMapping("/account/detail")
    public ResponseEntity<ApiResponseDto<AccountDto>> getAccountDetail(@RequestParam Long accountId) {
        return accountService.getAccountDetailDto(accountId);
    }

    @PostMapping(value = "/account/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponseDto<AccountDto>> createAccount(@ModelAttribute AccountRegisterDto requestDto,
                                                                    @AuthenticationPrincipal CustomUserDetails currentUser) throws IOException {

        Account existAccount = accountRepository.findByAccountCode(requestDto.getAccountCode());
        if (existAccount != null) {
            ApiResponseDto<AccountDto> errorResponse = ApiResponseDto.<AccountDto>builder()
                    .status(Constant.RESPONSE_STATUS.BAD_REQUEST)
                    .message(Constant.RESPONSE_MESSAGE.Account_already_exists)
                    .data(accountMapper.accountToAccountDto(existAccount))
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        AccountDto accountResponseDto = accountService.createAccountFromRequestDto(requestDto, currentUser);
        if (requestDto.getFiles() != null && !requestDto.getFiles().isEmpty()) {
            List<AccountImageDto> accountImageResponseDtoList = accountImageService.saveFiles(
                    accountResponseDto.getAccountId(),
                    requestDto.getFiles(),
                    currentUser
            );
            accountResponseDto.setAccountImageDtoList(accountImageResponseDtoList);
        }
        ApiResponseDto<AccountDto> successResponseDto = ApiResponseDto.<AccountDto>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountResponseDto)
                .build();
        return ResponseEntity.ok().body(successResponseDto);
    }
}
