package com.mir00r.userlogin.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mir00r.userlogin.Utils.DateUtil;
import com.mir00r.userlogin.configuration.SecurityContext;
import com.mir00r.userlogin.users.models.UserAuth;
import com.mir00r.userlogin.users.models.entites.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author mir00r on 14/5/20
 * @project IntelliJ IDEA
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @OneToOne
    @CreatedBy
    private User createdBy;

    @OneToOne
    @LastModifiedBy
    private User updatedBy;


    @Column(name = "uuid_str", unique = true)
    private String uuid;

    private boolean deleted;

    @PrePersist
    private void onBasePersist() {
        this.created = new Date();
        this.lastUpdated = new Date();
        UserAuth auth = getCurrentUser();
        if (auth != null && this.createdBy == null)
            this.createdBy = new User(auth);
        if (this.uuid == null || this.uuid.isEmpty())
            this.uuid = UUID.randomUUID().toString();
    }

    @PreUpdate
    private void onBaseUpdate() {
        this.lastUpdated = new Date();
//        UserAuth auth = getCurrentUser();
//        if (auth != null)
        this.updatedBy = createdBy;
        if (this.uuid == null || this.uuid.isEmpty())
            this.uuid = UUID.randomUUID().toString();
    }

    @JsonIgnore
    public UserAuth getCurrentUser() {
        return SecurityContext.getCurrentUser();
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getReadableDate(Date date) {
        return DateUtil.getReadableDate(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreated() {
        return created;
    }


    public Date getLastUpdated() {
        return lastUpdated;
    }


    @JsonIgnore
    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @JsonIgnore
    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
