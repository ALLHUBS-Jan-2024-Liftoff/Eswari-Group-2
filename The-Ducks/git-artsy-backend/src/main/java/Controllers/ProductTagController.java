package Controllers;

import org.launchcode.git_artsy_backend.ProductTag;

import java.util.List;
import java.util.Optional;

public class ProductTagController {

    private final ProductTagRepository productTagRepository;

    // Constructor to inject the data.ProductTagRepository dependency
    public ProductTagController(ProductTagRepository productTagRepository) {
        this.productTagRepository = productTagRepository;
    }

    // Get all product tags
    public List<ProductTag> getAllProductTags() {
        return productTagRepository.findAll();
    }

    // Get a product tag by its ID
    public ProductTag getProductTagById(Long id) {
        Optional<ProductTag> productTag = productTagRepository.findById(id);
        return productTag.orElse(null); // Return null if product tag not found
    }

    // Create a new product tag
    public ProductTag createProductTag(ProductTag productTag) {
        return productTagRepository.save(productTag);
    }

    // Delete a product tag by its ID
    public void deleteProductTag(Long id) {
        if (productTagRepository.existsById(id)) {
            productTagRepository.deleteById(id);
        }
    }
}
