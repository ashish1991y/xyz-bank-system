package com.xyzbank.eob.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class ParentModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private String updatedBy;
    private String createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusMaster status;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        setCreateDate(now);
        setUpdateDate(now);
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdateDate(new Date());
    }

}
