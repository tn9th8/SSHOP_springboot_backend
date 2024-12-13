package dmon.SSHOP_springboot_backend.entity.account;

import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", updatable = false, nullable = false)
    Account account;

    String name;

    String photo;

    Date dob;

    String gender;

    String status;
}
