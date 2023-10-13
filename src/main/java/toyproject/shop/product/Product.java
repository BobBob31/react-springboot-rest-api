package toyproject.shop.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "PRODUCT_ID")
    private Long productId;

    private String productName;
    private int productPrice;
}
