package vn.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractDto implements Serializable {

    private Integer insertId;

    private LocalDateTime insertDate;

    private Integer updateId;

    private LocalDateTime updateDate;

    private boolean delFlag;
}
