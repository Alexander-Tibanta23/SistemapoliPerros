package servlet;

import com.google.gson.Gson;
import dao.PerroDAO;
import entity.Perro;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/obtenerPerros", "/buscarPerros"})
public class PerroService extends HttpServlet {
    private final PerroDAO perroDAO = new PerroDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filtro = request.getParameter("filtro");
        String terminoBusqueda = request.getParameter("terminoBusqueda");
        List<Perro> perros;

        if (filtro != null && terminoBusqueda != null && !filtro.isEmpty() && !terminoBusqueda.isEmpty()) {
            perros = perroDAO.buscarPoliPerros(filtro, terminoBusqueda);
        } else {
            perros = perroDAO.obtenerPoliPerro();
        }

        // Convertir la lista de perros a JSON
        String json = new Gson().toJson(perros);

        // Enviar la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
