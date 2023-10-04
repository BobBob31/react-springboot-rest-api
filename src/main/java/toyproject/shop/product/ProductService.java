package toyproject.shop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Transactional
    public Long saveProduct(Product product) {
        validateDuplicateProduct(product);
        Product savedProduct = productRepository.save(product);
//        productRepository.saveProduct(product);
        return savedProduct.getProductId();
    }

    public void validateDuplicateProduct(Product product) {
        productRepository.findById(product.getProductId())
                .ifPresent(p -> {
                    throw new IllegalStateException("이미 존재하는 상품입니다.");
                });
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

//    public Optional<Product> findByName(String name) {
//        return productRepository.findByName(name);
//    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

//    public void deleteByName(String name) {
//        productRepository.deleteByName(name);
//    }


}
