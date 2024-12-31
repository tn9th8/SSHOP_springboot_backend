package dmon.SSHOP_springboot_backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import dmon.SSHOP_springboot_backend.entity.inventory.Inventory;
import dmon.SSHOP_springboot_backend.security.SecurityUtil;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.time.Instant;
import java.util.ArrayList;

@Entity
@Table(name = "skus")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE skus SET deleted = true WHERE sku_id=?")
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

    @OneToOne(mappedBy = "sku", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore @ToString.Exclude
    Inventory inventory;

    String status;

    @Column(nullable = false)
    String no;

    @Column(length = 40, nullable = false)
    String tierName;

    @Column(nullable = false)
    Integer[] tierIndex;

    Float productCost;

    Float basePrice;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<Sku.Specification> specifications;

    //THE NESTED CLASS//
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Specification {
        String name; String value;
    }

}
