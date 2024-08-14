<%--
  Created by IntelliJ IDEA.
  User: Alexander Tibanta
  Date: 14/8/2024
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="dao.PerroDAO" %>
<%@ page import="entity.Perro" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actualizar Perro</title>
</head>
<body>

<%
    String mensaje = request.getParameter("mensaje");
    String idParam = request.getParameter("id");
    int id = Integer.parseInt(idParam);
    PerroDAO perroDAO = new PerroDAO();
    Perro perro = perroDAO.obtenerProductoPorId(id);
%>

<--Mensajes de Validaciones-->

<% if ("actualizacionExitosa".equals(mensaje)) { %>
<div class="alert alert-success">Actualización Exitosa</div>
<% } else if ("errorActualizacion".equals(mensaje)) { %>
<div class="alert alert-danger">Error en la Actualización</div>
<% } else if ("errorFormatoDatos".equals(mensaje)) { %>
<div class="alert alert-danger">Error en el Formato de Datos</div>
<% } %>

<h1>Actualizar Perro</h1>

<--Formulario-->
<form id="actualizarPerroForm" action="actualizarPerro" method="post">
    <label for="id">ID:</label>
    <input type="number" id="id" name="id" value="<%= perro.getId() %>" readonly required><br>
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" value="<%= perro.getNombre() %>" required><br>
    <label for="raza">Raza:</label>
    <input type="text" id="raza" name="raza" value="<%= perro.getRaza() %>" required><br>
    <label for="edad">Edad:</label>
    <input type="number" id="edad" name="edad" value="<%= perro.getEdad() %>" required><br>
    <label for="estadoSalud">Estado de Salud:</label>
    <select id="estadoSalud" name="estadoSalud" required>
        <option value="bueno" <%= "bueno".equals(perro.getEstadoSalud()) ? "selected" : "" %>>Bueno</option>
        <option value="regular" <%= "regular".equals(perro.getEstadoSalud()) ? "selected" : "" %>>Regular</option>
        <option value="malo" <%= "malo".equals(perro.getEstadoSalud()) ? "selected" : "" %>>Malo</option>
    </select><br>
    <label for="fechaIngreso">Fecha de Ingreso:</label>
    <input type="date" id="fechaIngreso" name="fechaIngreso" value="<%= perro.getFechaIngreso() %>" required><br>
    <label for="fechaUltimoControl">Fecha del Último Control:</label>
    <input type="date" id="fechaUltimoControl" name="fechaUltimoControl" value="<%= perro.getFechaUltimoControl() %>"><br>
    <label for="observaciones">Observaciones:</label>
    <textarea id="observaciones" name="observaciones"><%= perro.getObservaciones() %></textarea><br>
    <button type="submit">Actualizar</button>
</form>

</body>
</html>
