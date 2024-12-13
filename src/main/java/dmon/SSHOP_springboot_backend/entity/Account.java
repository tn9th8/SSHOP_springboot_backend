package dmon.SSHOP_springboot_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;


@Entity
@Table(name = "accounts")
@DynamicInsert //ignore null-value attributes
@DynamicUpdate
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String accountId;

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    User user;

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    Seller seller;

    @Column(nullable = false, unique = true)
    String username;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String phone;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    Set<String> roles;
}
