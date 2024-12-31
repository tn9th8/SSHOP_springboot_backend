package dmon.SSHOP_springboot_backend.entity.product;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.*;

@Entity
@Table(name = "ProductTemplates")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE product_templates SET deleted = true WHERE category_id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTemplate {
    @Id
    String id;

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", updatable = false, nullable = false)
    Category category;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Product.Attribute attribute;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    Sku.Specification specification;
}
