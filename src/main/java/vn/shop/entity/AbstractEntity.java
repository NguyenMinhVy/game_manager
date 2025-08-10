package vn.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @JsonIgnore
    @Column(name="insert_id")
    private Long insertId;

    @JsonIgnore
    @Column(name="insert_date")
    private LocalDateTime insertDate;

    @JsonIgnore
    @Column(name="update_id")
    private Long updateId;

    @JsonIgnore
    @Column(name="update_date")
    private LocalDateTime updateDate;

    @JsonIgnore
    @Column(name="del_flag")
    private boolean delFlag = false;
}
