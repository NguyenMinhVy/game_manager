package vn.shop.mapper;

import org.mapstruct.Mapper;
import vn.shop.dto.AccountSaleDto;
import vn.shop.entity.AccountSale;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountSaleMapper {
    AccountSaleDto toDto(AccountSale accountSale);
    AccountSale toEntity(AccountSaleDto accountSaleDto);

    List<AccountSaleDto> toDtoList(List<AccountSale> accountSaleList);
    List<AccountSale> toEntityList(List<AccountSaleDto> accountSaleDtoList);

}
