package vn.shop.mapper;

import org.mapstruct.Mapper;
import vn.shop.dto.AccountImageDto;
import vn.shop.entity.AccountImage;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountImageMapper {

    AccountImageDto accountImageToAccountImageDto(AccountImage accountImage);
    AccountImage accountImageDtoToAccountImage(AccountImageDto accountImageDto);

    List<AccountImageDto> accountImageListToAccountImageDtoList(List<AccountImage> accountImageList);
    List<AccountImage> accountImageDtoListToAccountImageList(List<AccountImageDto> accountImageDtoList);

}
