package toyproject.shop.productorder;

import jakarta.persistence.*;
import lombok.*;
import toyproject.shop.product.Product;
import toyproject.shop.user.User;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime date;





//    private int orderPrice;
//    private String orderStatus;
}
