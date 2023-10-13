package toyproject.shop.productorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping("/orders")
public class ProductOrderController {
    private final ProductOrderService orderService;

    @Autowired
    public ProductOrderController(ProductOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> saveOrder(@PathVariable Long userId, @RequestBody ProductOrderDto orderDto) {
        Long orderId = orderService.saveOrder(userId, orderDto.getProductId());
        URI uri = fromPath("/orders/{orderId}")
                .buildAndExpand(orderId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductOrder>> findAllOrders(@PathVariable Long userId) {
        List<ProductOrder> orders = orderService.findAllOrders(userId);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteAllOrders(@PathVariable Long userId) {
        orderService.deleteAllOrders(userId);
        return ResponseEntity.ok("전체 상품 제거 성공");
    }

    @DeleteMapping("/{userId}/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        orderService.deleteOrder(userId, orderId);
        return ResponseEntity.ok("상품 제거 성공");

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
