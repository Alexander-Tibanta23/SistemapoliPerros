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
import java.time.format.DateTimeParseException;

@WebServlet("/actualizarPerro")
public class ActualizarPerroServlet extends HttpServlet {
    private final PerroDAO perroDAO = new PerroDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recuperar parámetros de la solicitud
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String raza = request.getParameter("raza");
            Integer edad = Integer.parseInt(request.getParameter("edad"));
            String estadoSalud = request.getParameter("estadoSalud");
            LocalDate fechaIngreso = LocalDate.parse(request.getParameter("fechaIngreso"));
            LocalDate fechaUltimoControl = null;
            String fechaUltimoControlParam = request.getParameter("fechaUltimoControl");
            if (fechaUltimoControlParam != null && !fechaUltimoControlParam.isEmpty()) {
                fechaUltimoControl = LocalDate.parse(fechaUltimoControlParam);
            }
            String observaciones = request.getParameter("observaciones");

            // Buscar el perro en la base de datos
            Perro perro = perroDAO.obtenerProductoPorId(id);

            if (perro != null) {
                // Actualizar la información del perro
                perro.setNombre(nombre);
                perro.setRaza(raza);
                perro.setEdad(edad);
                perro.setEstadoSalud(estadoSalud);
                perro.setFechaIngreso(fechaIngreso);
                perro.setFechaUltimoControl(fechaUltimoControl);
                perro.setObservaciones(observaciones);

                // Guardar las actualizaciones
                perroDAO.actualizarPoliPerro(perro);

                // Redirigir con mensaje de éxito
                response.sendRedirect("gestionInventario.jsp?mensaje=actualizacionExitosa");
            } else {
                // Redirigir con mensaje de error
                response.sendRedirect("gestionInventario.jsp?mensaje=errorActualizacion");
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            // Manejo de errores de formato de datoss
            response.sendRedirect("gestionInventario.jsp?mensaje=errorFormatoDatos");
        }
    }
}
