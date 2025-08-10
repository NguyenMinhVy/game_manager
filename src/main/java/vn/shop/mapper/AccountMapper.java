package vn.shop.mapper;

import org.mapstruct.Mapper;
import vn.shop.dto.AccountDto;
import vn.shop.entity.Account;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);

    List<AccountDto> accountListToAccountDtoList(List<Account> accountList);
    List<Account> accountDtoListToAccountList(List<AccountDto> accountDtoList);
}
