package dmon.SSHOP_springboot_backend.entity.base;

import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    String createdBy;

    String updatedBy;

    boolean deleted = Boolean.FALSE;

    @PrePersist
    private void setBeforeCreate() {
        this.createdBy = this.updatedBy = SecurityUtil.getCurrentAccountLogin().isPresent()
                ? SecurityUtil.getCurrentAccountLogin().get()
                : null;
    }

    @PreUpdate
    public void setBeforeUpdate() {
        // this.updatedAt = Instant.now();
        this.updatedBy = SecurityUtil.getCurrentAccountLogin().isPresent()
                ? SecurityUtil.getCurrentAccountLogin().get()
                : null;
    }
}
