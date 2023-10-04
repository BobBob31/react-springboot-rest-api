package toyproject.shop.product;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductMapRepository {
    private static Map<Long, Product> store = new HashMap<>();

    public void saveProduct(Product product) {
        store.put(product.getProductId(), product);
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Optional<Product> findByName(String name) {
        return store.values().stream()
                .filter(product -> product.getProductName().equals(name))
                .findAny();
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long productId) {
        store.remove(productId);
    }

//    public void deleteByName(String name) {
//        store.remove(store.values().stream()
//                .filter(product -> product.getProductName().equals(name))
//                .findAny());
//    }

    public void clear() {
        store.clear();
    }
}
