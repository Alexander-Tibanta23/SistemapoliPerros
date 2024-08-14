package servlet;

import com.google.gson.Gson;
import dao.PerroDAO;
import entity.Perro;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/buscarPerro")
public class BuscarPerroServlet extends HttpServlet {

    private final PerroDAO perroDAO = new PerroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("idPerro");
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"El ID del perro es requerido.\"}");
            return;
        }

        try {
            int perroId = Integer.parseInt(id);
            Perro perro = perroDAO.obtenerProductoPorId(perroId);
            if (perro == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Perro no encontrado.\"}");
            } else {
                String json = new Gson().toJson(perro);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"El ID del perro debe ser un número entero.\"}");
        }
    }
}
