<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 14/8/2024
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.PerroDAO" %>
<%@ page import="entity.Perro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Perro</title>
</head>
<body>

<%
    String mensaje = request.getParameter("mensaje");
    String idParam = request.getParameter("id");
    int id = Integer.parseInt(idParam);
    PerroDAO perroDAO = new PerroDAO();
    Perro perro = perroDAO.obtenerProductoPorId(id);
%>

<% if ("eliminacionExitosa".equals(mensaje)) { %>
<div class="alert alert-success">Eliminación Exitosa</div>
<% } else if ("errorEliminacion".equals(mensaje)) { %>
<div class="alert alert-danger">Error en la Eliminación</div>
<% } else if ("errorIdRequerido".equals(mensaje)) { %>
<div class="alert alert-danger">ID Requerido</div>
<% } else if ("errorFormatoId".equals(mensaje)) { %>
<div class="alert alert-danger">Formato de ID Inválido</div>
<% } %>

<h1>Eliminar Perro</h1>
<% if (perro != null) { %>
<p>¿Está seguro de que desea eliminar el siguiente perro?</p>
<p>ID: <%= perro.getId() %></p>
<p>Nombre: <%= perro.getNombre() %></p>
<p>Raza: <%= perro.getRaza() %></p>
<p>Edad: <%= perro.getEdad() %></p>
<p>Estado de Salud: <%= perro.getEstadoSalud() %></p>
<p>Fecha de Ingreso: <%= perro.getFechaIngreso() %></p>
<p>Fecha del Último Control: <%= perro.getFechaUltimoControl() %></p>
<p>Observaciones: <%= perro.getObservaciones() %></p>

<form id="eliminarPerroForm" action="eliminarPerro" method="post">
    <input type="hidden" id="id" name="id" value="<%= perro.getId() %>">
    <button type="submit">Eliminar</button>
    <button type="button" onclick="window.location.href='gestionInventario.jsp'">Cancelar</button>
</form>
<% } else { %>
<p>Perro no encontrado.</p>
<button type="button" onclick="window.location.href='gestionInventario.jsp'">Volver</button>
<% } %>

</body>
</html>