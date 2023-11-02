package toyproject.shop.productorder;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.shop.exception.CustomErrorCode;
import toyproject.shop.exception.CustomException;
import toyproject.shop.product.Product;
import toyproject.shop.product.ProductRepository;
import toyproject.shop.user.User;
import toyproject.shop.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

//    @Autowired
//    public ProductOrderService(ProductOrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    // order 정보 생성
    @Transactional
    public Long saveOrder(Long userId, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException(CustomErrorCode.PRODUCT_NOT_FOUND_ERROR));
//                IllegalArgumentException("상품을 찾을 수 없습니다"));
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(CustomErrorCode.EMAIL_NOT_FOUND_ERROR));
//                IllegalArgumentException("사용자를 찾을 수 없습니다."));
        ProductOrder order = ProductOrder.builder()
                .date(LocalDateTime.now())
                .product(product)
                .user(user)
                .build();
        return orderRepository.save(order).getId();
    }

    // 사용자에 대한 주문 전체 조회
    public List<ProductOrder> findAllOrders(Long userId) {
        List<ProductOrder> orders = orderRepository.findAll().stream()
                .filter(u -> u.getUser().getId().equals(userId))
                .toList();
        return orders;
    }

//    // 사용자에 대한 주문 개별 조회
//    public Optional<ProductOrder> findOrderById(Long userId) {
//        orderRepository.
//    }

    // 사용자 주문 목록 전체 삭제
    public void deleteAllOrders(Long userId) {
        List<ProductOrder> orders = orderRepository.findAll().stream()
                .filter(u -> u.getId().equals(userId))
                .toList();
        orderRepository.deleteAll(orders);
    }

    public void deleteOrder(Long userId, Long orderId) {
//        Optional<ProductOrder> order = orderRepository.findAll().stream()
//                .filter(u -> u.getUserId().equals(userId))
//                .filter(p -> p.getProductId().equals(productId))
//                .findAny();
//        orderRepository.delete(order.get());
        orderRepository.deleteById(orderId);
    }


    /*
    주문 전체 조회
Get: /orders
Get: /orders/{userId}

주문 개별 조회
Get: /orders/{id}
Get: /orders/{userId}/{orderId}

주문 전체 삭제
Delete: /orders
Delete: /orders/{userId}

주문 개별 삭제
Delete: /orders/{id}
Delete: /orders/{userId}/{orderId}
     */

}
