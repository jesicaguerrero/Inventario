package com.inventario.persistence;

import com.inventario.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
public class ProductoDAO {

    @Inject
    private DataSource ds;

    public List<Producto> listar() throws Exception {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY nombre ASC";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock"),
                    rs.getBoolean("activo")
                );
                productos.add(p);
            }
        }

        return productos;
    }

    public Producto buscarPorCodigo(String codigo) throws Exception {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getBoolean("activo")
                    );
                }
            }
        }
        return null;
    }

    public void crear(Producto p) throws Exception {
        String sql = "INSERT INTO productos (codigo, nombre, categoria, precio, stock, activo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getCategoria());
            stmt.setDouble(4, p.getPrecio());
            stmt.setInt(5, p.getStock());
            stmt.setBoolean(6, p.isActivo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        }
    }

    public void eliminar(int id) throws Exception {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
