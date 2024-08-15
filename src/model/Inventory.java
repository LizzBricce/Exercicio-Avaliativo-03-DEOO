package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    private final List<Product> products;

    private Inventory() {
        products = new ArrayList<>();
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public synchronized List<Product> getStock() {
        return new ArrayList<>(products);
    }

    public synchronized Product getProductById(long id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public synchronized boolean addProduct(Product product) {
        if (product != null) {
            return products.add(product);
        }
        return false;
    }

    public synchronized boolean updateProductQuantity(long id, int quantity) {
        Product product = getProductById(id);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            return true;
        }
        return false;
    }
}
