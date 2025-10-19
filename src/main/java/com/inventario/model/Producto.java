package com.inventario.model;

import java.io.Serializable;

/**
 * Entidad Producto para el sistema de inventario. Representa un producto con
 * sus atributos básicos y estado. Implementa Serializable para permitir su uso
 * en sesiones y transporte.
 *
 * @author Estudiante
 */
public class Producto implements Serializable {

    // Identificador único del producto en la base de datos
    private Integer id;

    // Código interno del producto (mínimo 3 caracteres según validación)
    private String codigo;

    // Nombre del producto (mínimo 5 caracteres según validación)
    private String nombre;

    // Categoría a la que pertenece el producto (ej. alimentos, tecnología)
    private String categoria;

    // Precio unitario del producto (debe ser mayor a 0)
    private Double precio;

    // Cantidad disponible en inventario (no puede ser negativa)
    private Integer stock;

    // Estado del producto: activo (true) o inactivo (false)
    private Boolean activo;

    // Constructor vacío requerido por frameworks y CDI
    public Producto() {
    }

    // Constructor parcial para pruebas o carga inicial
    public Producto(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Constructor completo para inicializar todos los atributos
    public Producto(Integer id, String codigo, String nombre, String categoria,
            Double precio, Integer stock, Boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo;
    }

    // Getters y setters para todos los atributos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    // Método toString para mostrar información clave del producto
    @Override
    public String toString() {
        return "Producto{"
                + "codigo='" + codigo + '\''
                + ", nombre='" + nombre + '\''
                + ", stock=" + stock
                + '}';
    }

    /**
     * Método helper para saber si el producto está disponible para venta. Un
     * producto está disponible si tiene stock > 0 y está activo.
     */
    public boolean isDisponible() {
        return stock != null && stock > 0 && Boolean.TRUE.equals(activo);
    }
}
