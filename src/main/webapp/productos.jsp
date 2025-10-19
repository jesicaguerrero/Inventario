<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Inventario - Productos</title>
        <meta charset="UTF-8">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .error {
                color: red;
                font-weight: bold;
                margin: 10px 0;
            }
            .form-section {
                margin-top: 30px;
                padding: 20px;
                background-color: #f9f9f9;
            }
            input[type="text"], input[type="number"], select {
                width: 200px;
                padding: 5px;
            }
            button {
                padding: 10px 20px;
                background-color: #007cba;
                color: white;
                border: none;
                cursor: pointer;
            }
            button:hover {
                background-color: #005a82;
            }
        </style>
    </head>
    <body>
        <h1>Sistema de Inventario</h1>
        <h2>Lista de Productos</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty mensajeBean.textoError}">
            <div class="error">${mensajeBean.textoError}</div>
        </c:if>
        <p>Idioma actual: ${preferenciasBean.idioma}</p>
        <p>Filtro aplicado: ${preferenciasBean.filtros['categoria']}</p>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Categoría</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Activo</th>
                </tr>
            </thead>
            <tbody>
                <!-- TODO: iterar lista de productos: requestScope.productos usando c:forEach -->
                <tr>
                    <td colspan="7">No hay productos cargados (completar servlet)</td>
                </tr>
            </tbody>
        </table>

        <div class="form-section">
            <h3>Agregar Nuevo Producto</h3>
            <form method="post" action="${pageContext.request.contextPath}/productos">
                <table>
                    <tr>
                        <td><label for="codigo">Código:</label></td>
                        <td><input type="text" id="codigo" name="codigo" required minlength="3" 
                                   placeholder="Mínimo 3 caracteres"/></td>
                    </tr>
                    <tr>
                        <td><label for="nombre">Nombre:</label></td>
                        <td><input type="text" id="nombre" name="nombre" required minlength="5" 
                                   placeholder="Mínimo 5 caracteres"/></td>
                    </tr>
                    <tr>
                        <td><label for="categoria">Categoría:</label></td>
                        <td>
                            <select id="categoria" name="categoria" required>
                                <option value="">Seleccione...</option>
                                <option value="Electronicos">Electrónicos</option>
                                <option value="Accesorios">Accesorios</option>
                                <option value="Muebles">Muebles</option>
                                <option value="Ropa">Ropa</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="precio">Precio:</label></td>
                        <td><input type="number" id="precio" name="precio" step="0.01" min="0.01" required 
                                   placeholder="0.00"/></td>
                    </tr>
                    <tr>
                        <td><label for="stock">Stock:</label></td>
                        <td><input type="number" id="stock" name="stock" min="0" required placeholder="0"/></td>
                    </tr>
                    <tr>
                        <td><label for="activo">Activo:</label></td>
                        <td><input type="checkbox" id="activo" name="activo" checked/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit">Guardar Producto</button>
                            <button type="reset">Limpiar</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>