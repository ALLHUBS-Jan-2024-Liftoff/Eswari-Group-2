package Controllers;

import org.launchcode.git_artsy_backend.Product;

import java.util.List;
import java.util.Optional;

public class ProductController {

    private final ProductRepository productRepository;

    // Constructor to inject the data.ProductRepository dependency
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get a product by its ID
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // Return null if product not found
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setProductId(id);
            return productRepository.save(product);
        } else {
            return null; // Return null if product with given ID does not exist
        }
    }

    // Delete a product by its ID
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}

