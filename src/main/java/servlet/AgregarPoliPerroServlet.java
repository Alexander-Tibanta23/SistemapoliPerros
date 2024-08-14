package servlet;

import dao.PerroDAO;
import entity.Perro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/agregarPerro")
public class AgregarPoliPerroServlet extends HttpServlet {

    private PerroDAO perroDAO = new PerroDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String raza = request.getParameter("raza");
        Integer edad = Integer.parseInt(request.getParameter("edad"));
        String estadoSalud = request.getParameter("estadoSalud");
        LocalDate fechaIngreso = LocalDate.parse(request.getParameter("fechaIngreso"));
        LocalDate fechaUltimoControl = request.getParameter("fechaUltimoControl") != null
                ? LocalDate.parse(request.getParameter("fechaUltimoControl"))
                : null;
        String observaciones = request.getParameter("observaciones");

        // Crear un objeto Perro
        Perro perro = new Perro();
        perro.setNombre(nombre);
        perro.setRaza(raza);
        perro.setEdad(edad);
        perro.setEstadoSalud(estadoSalud);
        perro.setFechaIngreso(fechaIngreso);
        perro.setFechaUltimoControl(fechaUltimoControl);
        perro.setObservaciones(observaciones);

        // Guardar el perro en la base de datos
        perroDAO.guardarPoliPerro(perro);

        // Redirigir a la página de gestión de inventario
        response.sendRedirect("gestionInventario.jsp");
    }

    public void setPerroDAO(PerroDAO perroDAO) {
        this.perroDAO = perroDAO;
    }
}