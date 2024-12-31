package dmon.SSHOP_springboot_backend.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "sellers")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE sellers SET deleted = true WHERE account_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seller extends BaseEntity {
    @Id
    String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", updatable = false, nullable = false)
    Account account;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore @ToString.Exclude
    List<Product> products;

    String shopName;

    String shopCode;

    String shopLogo;

    String contactEmail;

    String contactPhone;

    String businessType;

    String sellerType;

    String status;
}
