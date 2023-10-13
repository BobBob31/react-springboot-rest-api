package toyproject.shop.productorder;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ORDER_ID")
    private Long orderId;
    //    @Column(name = "USER_ID")
    @Column(name = "userId")
    private Long userId;
    //    @Column(name = "PRODUCT_ID")
    @Column(name = "productId")
    private Long productId;

    private LocalDateTime orderDate;





//    private int orderPrice;
//    private String orderStatus;
}
