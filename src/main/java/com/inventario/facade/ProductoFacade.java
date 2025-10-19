package com.inventario.facade;

import com.inventario.model.Producto;
import com.inventario.persistence.ProductoDAO;
import com.inventario.domain.ValidadorProducto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;


/**
 * Fachada de negocio para Producto Refactorizada como Managed Bean CDI
 *
 * @author Estudiante
 */
@Named("productoFacade")
@ApplicationScoped
public class ProductoFacade {

    @Inject
    private ProductoDAO dao;

    @Inject
    private ValidadorProducto validador;

    /**
     * Valida las reglas de negocio del producto.
     *
     * @param p Producto a validar
     * @throws Exception si alguna regla falla
     */
    private void validar(Producto p) throws Exception {
        validador.validarCodigo(p.getCodigo());
        validador.validarNombre(p.getNombre());
        validador.validarPrecio(p.getPrecio());
        validador.validarStock(p.getStock());
    }

    /**
     * Lista todos los productos disponibles.
     *
     * @return lista de productos
     * @throws Exception si ocurre un error en la consulta
     */
    public List<Producto> listar() throws Exception {
        return dao.listar(); // delega al DAO
    }

    /**
     * Crea un nuevo producto después de validar y verificar unicidad.
     *
     * @param p Producto a crear
     * @throws Exception si el producto ya existe o hay error de validación
     */
    public void crear(Producto p) throws Exception {
        validar(p);

        // Verificar unicidad de código
        Producto existente = dao.buscarPorCodigo(p.getCodigo());
        if (existente != null) {
            throw new Exception("Ya existe un producto con el código: " + p.getCodigo());
        }

        dao.crear(p); // insertar
    }

    /**
     * Elimina un producto por ID.
     *
     * @param id identificador del producto
     * @throws Exception si ocurre un error en la eliminación
     */
    public void eliminar(int id) throws Exception {
        dao.eliminar(id); // delega al DAO
    }
}
