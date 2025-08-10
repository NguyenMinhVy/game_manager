package vn.shop.service;

import org.springframework.stereotype.Service;
import vn.shop.entity.AbstractEntity;

import java.time.LocalDateTime;

@Service
public abstract class AbstractService<ENTITY extends AbstractEntity> {

    public void setInsertInfo(ENTITY entity, Long userId) {
        entity.setInsertId(userId);
        entity.setUpdateId(userId);
        entity.setInsertDate(LocalDateTime.now());
        entity.setUpdateDate(LocalDateTime.now());
    }

    public void setUpdateInfo(ENTITY entity, Long userId) {
        entity.setUpdateId(userId);
        entity.setUpdateDate(LocalDateTime.now());
    }
}
