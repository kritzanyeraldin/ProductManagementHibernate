import controller.ProductController;
import dao.ProductDao;
import dao.ProductDaoImpl;
import dto.ProductCreateDto;
import dto.ProductResponseDto;
import dto.ProductUpdateDto;
import service.ProductService;
import util.HibernateSessionManager;

import java.util.List;
import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ProductDao productDao = new ProductDaoImpl();
            ProductService productService = new ProductService(productDao);
            ProductController controller = new ProductController(productService);

            int option;
            do {
                System.out.println("\n--- Sistema de Gestión de Productos ---");
                System.out.println("1. Agregar producto");
                System.out.println("2. Listar productos");
                System.out.println("3. Actualizar producto");
                System.out.println("4. Eliminar producto");
                System.out.println("5. Buscar producto por ID");
                System.out.println("0. Salir");
                System.out.print("Opción: ");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> addProduct(controller);
                    case 2 -> listProducts(controller);
                    case 3 -> updateProduct(controller);
                    case 4 -> deleteProduct(controller);
                    case 5 -> searchProduct(controller);
                    case 0 -> {
                        System.out.println("Saliendo...");
                        HibernateSessionManager.shutdown();
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } while (option != 0);

        } catch (Exception e) {
            System.out.println("Error de coneccion a la base de datos: " + e.getMessage());
        }
    }

    private static void addProduct(ProductController controller) {
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Precio: ");
        double price = scanner.nextDouble();
        System.out.print("Cantidad: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        controller.createProduct(new ProductCreateDto(name, price, quantity));
    }

    private static void listProducts(ProductController controller) {
        List<ProductResponseDto> products = controller.getAllProducts();
        for (ProductResponseDto p : products) {
            System.out.println(p);
        }
    }
    private static void updateProduct(ProductController controller) {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo nombre (deja vacío para no cambiar): ");
        String name = scanner.nextLine();
        System.out.print("Nuevo precio (deja vacío para no cambiar): ");
        String priceInput = scanner.nextLine();
        System.out.print("Nueva cantidad (deja vacío para no cambiar): ");
        String quantityInput = scanner.nextLine();

        String nameOpt = name.isBlank() ? null : name;
        Double priceOpt = priceInput.isBlank() ? null : Double.parseDouble(priceInput);
        Integer quantityOpt = quantityInput.isBlank() ? null : Integer.parseInt(quantityInput);

        controller.updateProduct(id, new ProductUpdateDto(nameOpt, priceOpt, quantityOpt));
    }

    private static void deleteProduct(ProductController controller) {
        System.out.print("ID del producto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.deleteProduct(id);
    }

    private static void searchProduct(ProductController controller) {
        System.out.print("ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ProductResponseDto product = controller.getProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
