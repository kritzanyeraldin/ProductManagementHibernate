package controller;

import dto.ProductCreateDto;
import dto.ProductResponseDto;
import dto.ProductUpdateDto;
import model.Product;
import service.ProductService;
import util.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    public void createProduct(ProductCreateDto dto) {
        try {
            service.addProduct(dto.getName(), dto.getPrice(), dto.getQuantity());
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorCode.VALIDATION_ERROR + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println(ErrorCode.DATABASE_ERROR + ": " + e.getMessage());
        }
    }

    public List<ProductResponseDto> getAllProducts() {
        try {
            return service.getAllProducts()
                    .stream()
                    .map(p -> new ProductResponseDto(
                            p.getId(), p.getName(), p.getPrice(), p.getQuantity()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(ErrorCode.DATABASE_ERROR + ": " + e.getMessage());
            return List.of();
        }
    }

    public ProductResponseDto getProductById(int id) {
        try {
            Product p = service.getProductById(id);
            if (p == null) {
                System.out.println(ErrorCode.PRODUCT_NOT_FOUND);
                return null;
            }
            return new ProductResponseDto(p.getId(), p.getName(), p.getPrice(), p.getQuantity());
        } catch (Exception e) {
            System.out.println(ErrorCode.DATABASE_ERROR + ": " + e.getMessage());
            return null;
        }
    }

    public void updateProduct(int id, ProductUpdateDto dto) {
        try {
            service.updateProductPartial(id, dto);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorCode.VALIDATION_ERROR + ": " + e.getMessage());
        } catch (Exception e) {
            System.out.println(ErrorCode.DATABASE_ERROR + ": " + e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        try {
            service.deleteProduct(id);
            System.out.println("Producto eliminado correctamente");
        } catch (Exception e) {
            System.out.println(ErrorCode.DATABASE_ERROR + ": " + e.getMessage());
        }
    }
}
