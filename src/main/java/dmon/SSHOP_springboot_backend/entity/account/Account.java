package dmon.SSHOP_springboot_backend.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;


@Entity
@Table(name = "accounts")
@DynamicInsert //ignore null-value attributes
@DynamicUpdate
@SQLDelete(sql = "UPDATE accounts SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accountId", updatable = false, nullable = false)
    String id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String phone;

    @Column(nullable = false)
    Set<String> roles;
}
