package dmon.SSHOP_springboot_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sellers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    private Long sellerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    @MapsId
    private Account account;

    private String shopName;
    private String shopCode;
    private String shopLogo;
    private String contactEmail;
    private String contactPhone;
    private String businessType;
    private String sellerType;
    //todo: impl an attribute for soft-delete or status
}
