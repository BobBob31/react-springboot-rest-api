package toyproject.shop.user;

import jakarta.persistence.*;
import lombok.*;
import toyproject.shop.productorder.ProductOrder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String pw;

    public boolean matchPassword(String pw){
        return this.getPw().equals(pw);
    }
}
