package com.besstore.bmiService.model;

import lombok.Getter;
import lombok.Setter;


/**
 * Class used to recomend user our products.
 */
@Getter
@Setter
public class Item {
    /**
     * The unique identifier for the item.
     */
    private long productId;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * Image URL that shows the product.
     */
    private String imageUrl;

    /**
     * Constructor to initialize an Item with a product ID and image URL.
     */
    public Item(long productId, String imageUrl) {
        this.productId = productId;
        this.imageUrl = imageUrl;
    }
}
