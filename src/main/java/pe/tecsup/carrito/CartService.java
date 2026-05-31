package pe.tecsup.carrito;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private static final double DISCOUNT_THRESHOLD = 100.0;
    private static final double DISCOUNT_RATE = 0.10;
    private static final int MAX_PRODUCTS = 10; 

    private List<Product> products = new ArrayList<>();

    public double getTotal() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double getTotalWithDiscount() {
        double total = getTotal();
        if (total > DISCOUNT_THRESHOLD) {
            return total * (1 - DISCOUNT_RATE);
        }
        return total;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new NullPointerException("No se puede agregar un producto nulo");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException(
                    "El precio del producto no puede ser negativo: " + product.getPrice());
        }
        // R10: Validación de límite de productos distintos
        if (products.size() >= MAX_PRODUCTS && !products.contains(product)) {
            throw new IllegalStateException("No se pueden agregar más de 10 productos distintos");
        }
        
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    // R11: Retornar cantidad de productos
    public int getProductCount() {
        return products.size();
    }

    // R12: Vaciar el carrito
    public void clear() {
        products.clear();
    }
}