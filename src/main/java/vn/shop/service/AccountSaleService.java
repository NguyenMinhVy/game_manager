package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.config.CustomUserDetails;
import vn.shop.dto.AccountSaleDto;
import vn.shop.entity.AccountSale;
import vn.shop.mapper.AccountSaleMapper;
import vn.shop.repository.AccountSaleRepository;

@Service
public class AccountSaleService extends AbstractService<AccountSale> {
    @Autowired
    private AccountSaleMapper accountSaleMapper;

    @Autowired
    private AccountSaleRepository accountSaleRepository;

    public AccountSaleDto createAccountSale(AccountSaleDto requestDto, CustomUserDetails currentUser) {
        AccountSale accountSale = accountSaleMapper.toEntity(requestDto);
        setInsertInfo(accountSale, currentUser.getUserId());
        accountSaleRepository.save(accountSale);
        return accountSaleMapper.toDto(accountSale);
    }

    public AccountSaleDto updateAccountSale(AccountSaleDto requestDto, CustomUserDetails currentUser) {
        AccountSale existAccountSale = accountSaleRepository.findById(requestDto.getAccountSaleId()).orElse(null);
        if (existAccountSale == null) return null;
        AccountSale accountSale = accountSaleMapper.toEntity(requestDto);
        setUpdateInfo(accountSale, currentUser.getUserId());
        accountSaleRepository.save(accountSale);
        return accountSaleMapper.toDto(accountSale);
    }
}
