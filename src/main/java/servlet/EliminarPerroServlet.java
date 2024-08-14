package servlet;

import dao.PerroDAO;
import entity.Perro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/eliminarPerro")
public class EliminarPerroServlet extends HttpServlet {
    private final PerroDAO perroDAO = new PerroDAO();
    //Eliminar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("formularioEliminarPerro.jsp?mensaje=errorIdRequerido");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Perro perro = perroDAO.obtenerProductoPorId(id);
            if (perro != null) {
                perroDAO.eliminarPoliPerro(perro);
                response.sendRedirect("formularioEliminarPerro.jsp?id=" + id + "&mensaje=eliminacionExitosa");
            } else {
                response.sendRedirect("formularioEliminarPerro.jsp?id=" + id + "&mensaje=errorEliminacion");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("formularioEliminarPerro.jsp?mensaje=errorFormatoId");
        }
    }
}
