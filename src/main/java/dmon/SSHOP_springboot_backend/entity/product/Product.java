package dmon.SSHOP_springboot_backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dmon.SSHOP_springboot_backend.entity.account.Seller;
import dmon.SSHOP_springboot_backend.entity.base.BaseEntity;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE product_id=?")
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

    String status;

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

    Float basePrice;

    Float weight;

    String location;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<Attribute> attributes;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private ArrayList<TierVariation> tierVariations;

    //THE NESTED CLASS//
    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Attribute {
        String name; String value; String link;
    }

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class TierVariation {
        String name; ArrayList<String> options; ArrayList<String> photos;
    }
}
