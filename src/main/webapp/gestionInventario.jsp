<%--
  Created by IntelliJ IDEA.
  User: Alexander Tibanta
  Date: 14/8/2024
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Perro" %>
<%@ page import="dao.PerroDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Inventario de Perros</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
        }
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<h1>Gestión de Perros</h1>

<h2>Buscar Perro</h2>
<form id="searchForm">
    <label for="filtro">Filtrar por:</label>
    <select id="filtro" name="filtro">
        <option value="nombre">Nombre</option>
        <option value="raza">Raza</option>
        <option value="edad">Edad</option>
        <option value="estadoSalud">Estado de Salud</option>
        <option value="fechaIngreso">Fecha de Ingreso</option>
        <option value="fechaUltimoControl">Fecha de Último Control</option>
    </select>
    <label for="terminoBusqueda">Término de búsqueda:</label>
    <input type="text" id="terminoBusqueda" name="terminoBusqueda">
    <button type="submit">Buscar</button>
</form>

<h2>Lista de Perros</h2>
<table id="perros">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Raza</th>
        <th>Edad</th>
        <th>Estado de Salud</th>
        <th>Fecha de Ingreso</th>
        <th>Fecha de Último Control</th>
        <th>Observaciones</th>
    </tr>
    <%
        PerroDAO perroDAO = new PerroDAO();
        List<Perro> perros = perroDAO.obtenerPoliPerro();

        for (Perro perro : perros) {
    %>
    <tr>
        <td><%= perro.getId() %></td>
        <td><%= perro.getNombre() %></td>
        <td><%= perro.getRaza() %></td>
        <td><%= perro.getEdad() %></td>
        <td><%= perro.getEstadoSalud() %></td>
        <td><%= perro.getFechaIngreso() %></td>
        <td><%= perro.getFechaUltimoControl() %></td>
        <td><%= perro.getObservaciones() %></td>
    </tr>
    <% } %>
</table>

<button id="agregarPerroBtn">Agregar Perro</button>

<!-- Modal for adding dog -->
<div id="modalAgregarPerro" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Ingresar Detalles del Perro</h2>
        <form id="agregarPerroForm" action="agregarPerro" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required><br>
            <label for="raza">Raza:</label>
            <input type="text" id="raza" name="raza" required><br>
            <label for="edad">Edad:</label>
            <input type="number" id="edad" name="edad" required><br>
            <label for="estadoSalud">Estado de Salud:</label>
            <select id="estadoSalud" name="estadoSalud" required>
                <option value="bueno">Bueno</option>
                <option value="regular">Regular</option>
                <option value="malo">Malo</option>
            </select>
            <label for="fechaIngreso">Fecha de Ingreso:</label>
            <input type="date" id="fechaIngreso" name="fechaIngreso" required><br>
            <label for="fechaUltimoControl">Fecha de Último Control:</label>
            <input type="date" id="fechaUltimoControl" name="fechaUltimoControl"><br>
            <label for="observaciones">Observaciones:</label>
            <textarea id="observaciones" name="observaciones"></textarea><br>
            <button type="submit">Agregar</button>
            <button type="button" onclick="window.close()">Cancelar</button>
        </form>
    </div>
</div>


<button id="actualizarPerroBtn">Actualizar Perro</button>

<button id="eliminarPerroBtn">Eliminar Perro</button>

<button type="button" onclick="window.location.href='index.jsp'">Volver</button>

<script>
    document.getElementById('searchForm').addEventListener('submit', function(event) {
        event.preventDefault();
        var filtro = document.getElementById('filtro').value;
        var terminoBusqueda = document.getElementById('terminoBusqueda').value;
        var url = 'buscarPerro?filtro=' + filtro + '&terminoBusqueda=' + terminoBusqueda;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                var table = document.getElementById('perros');
                table.innerHTML = '';
                var headerRow = table.insertRow();
                var headers = ['ID', 'Nombre', 'Raza', 'Edad', 'Estado de Salud', 'Fecha de Ingreso', 'Fecha de Último Control', 'Observaciones'];
                headers.forEach(headerText => {
                    var headerCell = document.createElement('th');
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });
                data.forEach(perro => {
                    var row = table.insertRow();
                    row.insertCell(0).textContent = perro.id;
                    row.insertCell(1).textContent = perro.nombre;
                    row.insertCell(2).textContent = perro.raza;
                    row.insertCell(3).textContent = perro.edad;
                    row.insertCell(4).textContent = perro.estadoSalud;
                    row.insertCell(5).textContent = perro.fechaIngreso;
                    row.insertCell(6).textContent = perro.fechaUltimoControl;
                    row.insertCell(7).textContent = perro.observaciones;
                });
            });
    });

    var modal = document.getElementById('modalAgregarPerro');
    var btn = document.getElementById('agregarPerroBtn');
    var span = document.getElementsByClassName('close')[0];

    btn.onclick = function() {
        modal.style.display = 'block';
    }

    span.onclick = function() {
        modal.style.display = 'none';
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }

    var modalAgregar = document.getElementById('modalAgregarPerro');
    var btnAgregar = document.getElementById('agregarPerroBtn');
    var btnActualizar = document.getElementById('actualizarPerroBtn');
    var btnEliminar = document.getElementById('eliminarPerroBtn');

    btnAgregar.onclick = function() {
        modalAgregar.style.display = 'block';
    }

    btnActualizar.onclick = function() {
        var id = prompt("Ingrese el ID del perro que desea actualizar:");
        if (id) {
            window.location.href = 'formularioActualizarPerro.jsp?id=' + id;
        }
    }

    btnEliminar.onclick = function() {
        var id = prompt("Ingrese el ID del perro que desea eliminar:");
        if (id) {
            window.location.href = 'formularioEliminarPerro.jsp?id=' + id;
        }
    }
</script>

</body>
</html>
