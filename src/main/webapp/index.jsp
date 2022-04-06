<%@page import="com.emergentes.modelo.Producto"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Producto> lista = (ArrayList<Producto>) session.getAttribute("listapro");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LISTADO DE PRODUCTOS</h1>
                <table style="text-align:center;">
            <tr>
                <td><h2>Primer parcial TEM-742</h2></td>
            </tr>
            <tr>
                <td><h3>Nombre: Primitivo Catari Limachi</h3></td>
            </tr>
            <tr>
                <td><h3>Carnet: 2521970</h3></td>
            </tr>
            <tr>
                <td></td>
            </tr>
        </table>
 
        <a href="MainControlador?op=nuevo">Nuevo Registro</a>
        
       
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <%  
                if (lista != null){
                    for (Producto item : lista) {
                        
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td><a href="MainControlador?op=editar&id=<%= item.getId() %>">Modificar</a></td>
                <td><a href="MainControlador?op=eliminar&id=<%= item.getId() %>"
                       onclick="return confirm('Esta seguro de eliminar el registro')">Eliminar</a></td>
            </tr>
            <%  
                    }
                }
            %>
        </table>
    </body>
</html>
