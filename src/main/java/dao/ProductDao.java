package dao;

import model.Product;

import java.util.List;

public interface ProductDao {
    void addProduct(Product product);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);
    Product getProductById(int id);
}
