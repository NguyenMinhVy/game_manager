package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.shop.config.CustomUserDetails;
import vn.shop.dto.AccountImageDto;
import vn.shop.entity.AccountImage;
import vn.shop.mapper.AccountImageMapper;
import vn.shop.repository.AccountImageRepository;

import java.util.List;

@Service
public class AccountImageService extends AbstractService<AccountImage> {

    @Autowired
    private AccountImageMapper accountImageMapper;

    @Autowired
    private AccountImageRepository accountImageRepository;

    public List<AccountImageDto> createAccountImageList(List<AccountImageDto> accountImageDtoList, CustomUserDetails customUserDetails) {
        List<AccountImage> accountImages = accountImageMapper.accountImageDtoListToAccountImageList(accountImageDtoList);
        accountImages.forEach(accountImage ->
                setInsertInfo(accountImage, customUserDetails.getUserId())
        );
        accountImageRepository.saveAll(accountImages);
        return accountImageMapper.accountImageListToAccountImageDtoList(accountImages);
    }

    public List<AccountImageDto> getAccountImageListByAccountIdList(List<Long> accountIdList) {
        List<AccountImage> accountImageList = accountImageRepository.findByAccountIdInAndDelFlagFalse(accountIdList);
        return accountImageMapper.accountImageListToAccountImageDtoList(accountImageList);
    }
}
