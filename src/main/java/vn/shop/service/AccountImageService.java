package vn.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.shop.config.CustomUserDetails;
import vn.shop.dto.AccountImageDto;
import vn.shop.entity.AccountImage;
import vn.shop.mapper.AccountImageMapper;
import vn.shop.repository.AccountImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<AccountImageDto> saveFiles(Long accountId, List<MultipartFile> files, CustomUserDetails currentUser) throws IOException {
        List<AccountImageDto> result = new ArrayList<>();
        for (MultipartFile file : files) {
            String uploadFolder = "/nas/account/";
            String uploadDir = "D:" + uploadFolder;

            // Tạo thư mục nếu chưa có
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try {
                String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(uniqueFileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                AccountImageDto dto = new AccountImageDto();
                dto.setAccountId(accountId);
                dto.setImageName(uniqueFileName);
                dto.setImageUrl(uploadFolder + uniqueFileName);
                result.add(dto);
            } catch (IOException e) {
                throw new RuntimeException("Không thể lưu file: " + file.getOriginalFilename(), e);
            }
        }
        if (!CollectionUtils.isEmpty(result)) {
            List<AccountImage> accountImageList = accountImageMapper.accountImageDtoListToAccountImageList(result);
            setInsertInfo(accountImageList, currentUser.getUserId());
            accountImageRepository.saveAll(accountImageList);
        }
        return result;
    }

}
