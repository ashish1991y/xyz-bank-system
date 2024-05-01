package com.xyzbank.authenticationservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "status_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private String updatedBy;
    private String createdBy;

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
