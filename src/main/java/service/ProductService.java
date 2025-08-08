package service;

import dao.ProductDao;
import dto.ProductUpdateDto;
import model.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void addProduct(String name, double price, int quantity) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        productDao.addProduct(new Product(name, price, quantity));
        System.out.println("Producto agregado correctamente.");
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public void updateProductPartial(int id, ProductUpdateDto dto) {
        Product existing = productDao.getProductById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }

        if (dto.name() != null) existing.setName(dto.name());
        if (dto.price() != null) existing.setPrice(dto.price());
        if (dto.quantity() != null) existing.setQuantity(dto.quantity());

        productDao.updateProduct(existing);
    }


    public void deleteProduct(int id) {
        Product existing = productDao.getProductById(id);
        if (existing == null) {
            throw new IllegalArgumentException("No se puede eliminar: el producto con ID " + id + " no existe.");
        }
        productDao.deleteProduct(id);
    }


}
