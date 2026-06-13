package pe.tecsup.carrito;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CartService - reglas de negocio del carrito")
class CartServiceTest {

    private CartService cart;

    @BeforeEach
    void setUp() {
        cart = new CartService();
    }

    @Test
    @DisplayName("Carrito vacío: total debe ser 0.0")
    void getTotal_carritoVacio_retornaCero() {
        double total = cart.getTotal();
        assertEquals(0.0, total);
    }

    @Test
    @DisplayName("Agregar producto: total refleja el precio del producto")
    void addProduct_unProducto_totalIgualAlPrecio() {
        Product laptop = new Product("Laptop", 850.0);
        cart.addProduct(laptop);
        assertEquals(850.0, cart.getTotal());
    }

    @Test
    @DisplayName("Agregar mismo producto dos veces: total es precio × 2")
    void addProduct_mismoProductoDosVeces_totalEsDoble() {
        Product mouse = new Product("Mouse", 25.0);
        cart.addProduct(mouse);
        cart.addProduct(mouse);
        assertEquals(50.0, cart.getTotal());
    }

    @Test
    @DisplayName("Precio negativo: debe lanzar IllegalArgumentException")
    void addProduct_precioNegativo_lanzaExcepcion() {
        Product invalido = new Product("Item inválido", -10.0);
        assertThrows(IllegalArgumentException.class, () -> cart.addProduct(invalido));
    }

    @Test
    @DisplayName("Producto nulo: debe lanzar NullPointerException")
    void addProduct_productoNulo_lanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> cart.addProduct(null));
    }

    @Test
    @DisplayName("Total mayor a 100: aplica descuento del 10%")
    void getTotalWithDiscount_totalMayorACien_aplicaDescuento() {
        cart.addProduct(new Product("TV", 120.0));
        double totalConDescuento = cart.getTotalWithDiscount();
        assertEquals(108.0, totalConDescuento);
    }

    @Test
    @DisplayName("Total igual a 100: NO aplica descuento")
    void getTotalWithDiscount_totalExactamenteCien_sinDescuento() {
        cart.addProduct(new Product("Audífonos", 100.0));
        double totalConDescuento = cart.getTotalWithDiscount();
        assertEquals(100.0, totalConDescuento);
    }

    @Test
    @DisplayName("Eliminar producto existente: total se reduce correctamente")
    void removeProduct_productoExistente_totalSeReduce() {
        // Arrange
        Product libro = new Product("Libro", 40.0);
        cart.addProduct(libro);
        cart.addProduct(new Product("Cuaderno", 15.0));

        // Act
        cart.removeProduct(libro);

        // Assert
        assertEquals(15.0, cart.getTotal());
    }

    @Test
    @DisplayName("Eliminar producto inexistente: no lanza excepción")
    void removeProduct_productoNoExiste_sinExcepcion() {
        // Arrange
        Product fantasma = new Product("Fantasma", 99.0);

        assertDoesNotThrow(() -> cart.removeProduct(fantasma));
    }

    @Test
    @DisplayName("R10: Más de 10 productos distintos lanza IllegalStateException")
    void addProduct_masDeDiezProductos_lanzaExcepcion() {
        for (int i = 1; i <= 10; i++) {
            cart.addProduct(new Product("Producto " + i, 10.0));
        }
        Product product11 = new Product("Producto 11", 10.0);
        assertThrows(IllegalStateException.class, () -> cart.addProduct(product11));
    }

    @Test
    @DisplayName("R11: getProductCount retorna la cantidad de productos en el carrito")
    void getProductCount_conProductos_retornaCantidad() {
        cart.addProduct(new Product("Mouse", 25.0));
        cart.addProduct(new Product("Teclado", 50.0));
    
        assertEquals(3, cart.getProductCount()); 
    }

    @Test
    @DisplayName("R12: clear vacía el carrito y deja el total en 0.0")
    void clear_conProductos_vaciaCarrito() {
        cart.addProduct(new Product("Monitor", 200.0));
        cart.addProduct(new Product("RAM", 50.0));
    
        cart.clear();
    
        assertEquals(100.0, cart.getTotal());
        assertEquals(0, cart.getProductCount()); 
    }
}