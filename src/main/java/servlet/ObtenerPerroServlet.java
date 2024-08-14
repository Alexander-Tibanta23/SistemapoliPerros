package servlet;

import dao.PerroDAO;
import entity.Perro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@WebServlet("/obtenerPerro")
public class ObtenerPerroServlet extends HttpServlet {

    private final PerroDAO perroDAO = new PerroDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idPerro = req.getParameter("idPerro");
        res.setContentType("text/html");

        if (idPerro == null || idPerro.isEmpty()) {
            res.getWriter().write("<tr><td colspan='8' style='color: red;'>El parámetro 'idPerro' es obligatorio.</td></tr>");
            return;
        }

        try {
            Integer id = Integer.parseInt(idPerro);
            Perro perro = perroDAO.obtenerProductoPorId(id);

            if (perro == null) {
                res.getWriter().write("<tr><td colspan='8' style='color: red;'>Perro no encontrado.</td></tr>");
                return;
            }

            // Formateador para las fechas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            StringBuilder perroHtml = new StringBuilder();
            perroHtml.append("<tr data-id='").append(perro.getId()).append("'>");
            perroHtml.append("<td class='text-center'>").append(perro.getId()).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getNombre()).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getRaza()).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getEdad()).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getEstadoSalud()).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getFechaIngreso().format(formatter)).append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getFechaUltimoControl() != null ? perro.getFechaUltimoControl().format(formatter) : "N/A").append("</td>");
            perroHtml.append("<td class='text-center'>").append(perro.getObservaciones() != null ? perro.getObservaciones() : "N/A").append("</td>");
            perroHtml.append("<td class='text-center'><button onclick='eliminarFila(this)'>Eliminar</button></td>");
            perroHtml.append("</tr>");
            res.getWriter().write(perroHtml.toString());
        } catch (NumberFormatException e) {
            res.getWriter().write("<tr><td colspan='8' style='color: red;'>El parámetro 'idPerro' debe ser un número válido.</td></tr>");
        }
    }
}
