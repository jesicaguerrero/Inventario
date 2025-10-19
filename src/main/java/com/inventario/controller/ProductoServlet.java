package com.inventario.controller;

import com.inventario.facade.ProductoFacade;
import com.inventario.model.Producto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

/**
 * Servlet controlador para Producto. Maneja las peticiones GET y POST
 * relacionadas con productos. En este taller, se usa una instancia directa de
 * ProductoFacade. En una versión CDI, se inyectaría con @Inject.
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/productos"})
public class ProductoServlet extends HttpServlet {

    // Fachada que encapsula la lógica de negocio y acceso a datos
    private final ProductoFacade facade = new ProductoFacade(); // En este taller, instancia directa

    /**
     * Maneja las peticiones GET. Obtiene la lista de productos desde la fachada
     * y la envía a la vista JSP.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtener lista de productos desde la fachada
            List<Producto> productos = facade.listar();

            // Guardar la lista como atributo para que JSP la pueda mostrar
            req.setAttribute("productos", productos);

            // Reenviar la petición a la vista productos.jsp
            req.getRequestDispatcher("productos.jsp").forward(req, resp);
        } catch (Exception e) {
            // En caso de error, mostrar mensaje genérico
            req.setAttribute("error", "Error al cargar productos: " + e.getMessage());
            req.getRequestDispatcher("productos.jsp").forward(req, resp);
        }
    }

    /**
     * Maneja las peticiones POST. Lee los datos del formulario, construye un
     * Producto y lo envía a la fachada para crear. Si hay errores de
     * validación, los muestra en la vista.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Leer parámetros del formulario
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        String categoria = req.getParameter("categoria");
        String precioStr = req.getParameter("precio");
        String stockStr = req.getParameter("stock");
        String activoStr = req.getParameter("activo");

        try {
            // Convertir parámetros a tipos adecuados
            Double precio = Double.parseDouble(precioStr);
            Integer stock = Integer.parseInt(stockStr);
            Boolean activo = "on".equalsIgnoreCase(activoStr); // checkbox

            // Construir el objeto Producto
            Producto p = new Producto(null, codigo, nombre, categoria, precio, stock, activo);

            // Llamar a la fachada para crear el producto
            facade.crear(p);

            // En éxito, redirigir a la lista de productos
            resp.sendRedirect("productos");
        } catch (Exception e) {
            // Capturar errores de validación o conversión
            req.setAttribute("error", "Error al crear producto: " + e.getMessage());

            // Reenviar a la misma vista para mostrar el mensaje
            req.getRequestDispatcher("productos.jsp").forward(req, resp);
        }
    }
}
