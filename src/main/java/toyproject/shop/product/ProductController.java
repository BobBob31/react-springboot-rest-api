package toyproject.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.util.UriComponentsBuilder.fromPath;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<Void> saveProduct(@RequestBody ProductDto productDto) {
        Long productId = productService.saveProduct(productDto);
        URI uri = fromPath("/products/{productId}")
                .buildAndExpand(productId)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable Long productId) {
        Optional<Product> product = productService.findById(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok("상품 제거 성공");
    }
}
