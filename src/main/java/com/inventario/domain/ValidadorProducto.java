/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventario.domain;

import jakarta.enterprise.context.Dependent;

/**
 * Bean CDI para validar reglas de negocio de Producto.
 * Scope @Dependent: se crea junto al bean que lo consume (ProductoFacade).
 */
@Dependent
public class ValidadorProducto {

    /**
     * Valida que el código tenga al menos 3 caracteres.
     */
    public void validarCodigo(String codigo) throws Exception {
        if (codigo == null || codigo.trim().length() < 3) {
            throw new Exception("El código debe tener al menos 3 caracteres");
        }
    }

    /**
     * Valida que el nombre tenga al menos 5 caracteres.
     */
    public void validarNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().length() < 5) {
            throw new Exception("El nombre debe tener al menos 5 caracteres");
        }
    }

    /**
     * Valida que el precio sea mayor a 0.
     */
    public void validarPrecio(Double precio) throws Exception {
        if (precio == null || precio <= 0) {
            throw new Exception("El precio debe ser mayor a 0");
        }
    }

    /**
     * Valida que el stock no sea negativo.
     */
    public void validarStock(Integer stock) throws Exception {
        if (stock == null || stock < 0) {
            throw new Exception("El stock no puede ser negativo");
        }
    }
}
