package dmon.SSHOP_springboot_backend.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "categories")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "categoryId", updatable = false, nullable = false)
    String id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch =  FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    List<Product> products;

    @Column(length = 40, nullable = false)
    String name;

    String description;
}
