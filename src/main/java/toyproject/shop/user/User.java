package toyproject.shop.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USER_ID")
    private Long userId;

    private String userName;
    private String userEmail;
    private String userPw;

    public boolean matchPassword(String pw){
        return this.getUserPw().equals(pw);
    }
}
