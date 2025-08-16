package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.shop.constant.Constant;
import vn.shop.dto.AccountDto;
import vn.shop.config.CustomUserDetails;
import vn.shop.dto.AccountImageDto;
import vn.shop.dto.ApiResponseDto;
import vn.shop.entity.Account;
import vn.shop.mapper.AccountMapper;
import vn.shop.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService extends AbstractService<Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountImageService accountImageService;


    public AccountDto createAccount(AccountDto accountDto, CustomUserDetails customUserDetails) {
        // insert Account
        Account account = accountMapper.accountDtoToAccount(accountDto);
        setInsertInfo(account, customUserDetails.getUserId());
        accountRepository.save(account);
        return accountMapper.accountToAccountDto(account);
    }

    public ResponseEntity<ApiResponseDto<Page<AccountDto>>> getAccountDtoList(Long gameId, Long minPrice, Long maxPrice, String accountCode, String sort, Pageable pageable) {
        Page<AccountDto> accountDtoPage = getAllAccounts(gameId, minPrice, maxPrice, accountCode, sort, pageable);
        ApiResponseDto<Page<AccountDto>> response = ApiResponseDto.<Page<AccountDto>>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountDtoPage)
                .build();
        return ResponseEntity.ok().body(response);
    }

    public Page<AccountDto> getAllAccounts(Long gameId, Long minPrice, Long maxPrice, String accountCode, String sort, Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAllByGameIdAndDelFlagFalseAAndDisplayTrueOrderByPriority(gameId, minPrice, maxPrice, accountCode, sort, pageable);
        if (accountPage.getContent().isEmpty()) {
            return Page.empty(pageable);
        }
        List<AccountDto> accountDtoList = accountMapper.accountListToAccountDtoList(accountPage.getContent());
        List<Long> accountIdList = accountPage.getContent().stream()
                .map(Account::getAccountId)
                .toList();
        List<AccountImageDto> accountImageDtoList = accountImageService.getAccountImageListByAccountIdList(accountIdList);
        accountDtoList.forEach(accountDto -> {
            List<AccountImageDto> images = accountImageDtoList.stream().filter(accountImageDto -> accountDto.getAccountId().equals(accountImageDto.getAccountId())).toList();
            // 1 image dau dai dien
            accountDto.setAccountImageDtoList(List.of(images.get(0)));
        });

        return new PageImpl<>(accountDtoList, pageable, accountPage.getTotalElements());
    }

    public ResponseEntity<ApiResponseDto<AccountDto>> getAccountDetailDto(Long accountId) {
        AccountDto accountDto = getAccountByAccountId(accountId);
        ApiResponseDto<AccountDto> response = ApiResponseDto.<AccountDto>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountDto)
                .build();
        return ResponseEntity.ok().body(response);
    }

    public AccountDto getAccountByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return null;
        }
        AccountDto accountDto = accountMapper.accountToAccountDto(account);
        List<AccountImageDto> accountImageDtoList = accountImageService.getAccountImageListByAccountIdList(List.of(accountId));
        accountDto.setAccountImageDtoList(accountImageDtoList);
        return accountDto;
    }
}
