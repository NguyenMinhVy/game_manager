package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
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

    public ResponseEntity<ApiResponseDto<List<AccountDto>>> getAccountDtoList(Long gameId) {
        List<AccountDto> accountDtoList = getAllAccounts(gameId);
        ApiResponseDto<List<AccountDto>> response = ApiResponseDto.<List<AccountDto>>builder()
                .status(Constant.RESPONSE_STATUS.SUCCESS)
                .message(Constant.RESPONSE_MESSAGE.SUCCESS)
                .data(accountDtoList)
                .build();
        return ResponseEntity.ok().body(response);
    }

    public List<AccountDto> getAllAccounts(Long gameId) {
        List<Account> accountList = accountRepository.findAllByGameIdAndDelFlagFalseAAndDisplayTrueOrderByPriority(gameId);
        if (accountList.isEmpty()) {
            return new ArrayList<>();
        }
        List<AccountDto> accountDtoList = accountMapper.accountListToAccountDtoList(accountList);
        List<Long> accountIdList = accountList.stream()
                .map(Account::getAccountId)
                .toList();
        List<AccountImageDto> accountImageDtoList = accountImageService.getAccountImageListByAccountIdList(accountIdList);
        accountDtoList.forEach(accountDto -> {
            List<AccountImageDto> images = accountImageDtoList.stream().filter(accountImageDto -> accountDto.getAccountId().equals(accountImageDto.getAccountId())).toList();
            // 1 image dau dai dien
            accountDto.setAccountImageDtoList(List.of(images.get(0)));
        });

        return accountDtoList;
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
