package dmon.SSHOP_springboot_backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "products")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE) //todo: cate, seller, product metrics
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "productId", updatable = false, nullable = false)
    String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId")
    Seller seller;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) //orphanRemoval: xóa các child mồ coi
    @ToString.Exclude
    @JsonIgnore
    List<Sku> skus;

    @Column(nullable = false)
    String spu;

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "text")
    String description;

    String slug;

    @Column(nullable = false)
    String thumb;

    List<String> photos;

    String video;

    String sizeChart;

    String status;
}
