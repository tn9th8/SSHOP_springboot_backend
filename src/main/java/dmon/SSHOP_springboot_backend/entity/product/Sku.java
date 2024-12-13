package dmon.SSHOP_springboot_backend.entity.product;

import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.time.Instant;

@Entity
@Table(name = "skus")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE skus SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sku extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "skuId", updatable = false, nullable = false)
    String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    Product product;

    @Column(nullable = false)
    String sku;

    @Column(length = 40, nullable = false)
    String name;

    @Column(nullable = false)
    int[] variationIndex;

    float weight;
}
