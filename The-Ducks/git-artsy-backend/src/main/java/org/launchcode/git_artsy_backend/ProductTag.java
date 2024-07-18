package org.launchcode.git_artsy_backend;

import jakarta.persistence.*;
import org.springframework.core.metrics.StartupStep;
import org.springframework.data.annotation.Id;


/**
 * Represents the association between Products and Tags.
 * A Product can have multiple Tags and a Tag can be associated with multiple Products.
 */
@Entity
public class ProductTag {
    // Primary key for the ProductTag table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productTagId;

    // Many-to-one relationship with the Product table
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Many-to-one relationship with the Tag table
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private StartupStep.Tag tag;

    public ProductTag(Long productTagId, Product product, StartupStep.Tag tag) {
        this.productTagId = productTagId;
        this.product = product;
        this.tag = tag;
    }

    //No arg const
    public ProductTag() {}

    // Getters and setters for all fields
    public Long getProductTagId() {
        return productTagId;
    }

    public void setProductTagId(Long productTagId) {
        this.productTagId = productTagId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tag getTag() {
        return (Tag) tag;
    }

    public void setTag(Tag tag) {
        this.tag = (StartupStep.Tag) tag;
    }
}

