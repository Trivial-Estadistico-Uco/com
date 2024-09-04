package servlet;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DoRegistration")
public class DoRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configurar la respuesta para enviar JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Obtener los datos del formulario de registro
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        // Validar que el usuario no exista ya en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO usuarioExistente = usuarioDAO.obtenerUsuarioPorCorreo(email);

        if (usuarioExistente != null) {
            // Si el usuario ya existe, devolver un mensaje de error
            out.print("{\"success\": false, \"message\": \"El correo electrónico ya está registrado.\"}");
        } else {
            // Crear un nuevo usuario y guardarlo en la base de datos
            UsuarioDTO nuevoUsuario = new UsuarioDTO(nombre, apellido, email, password, false);
            boolean registrado = usuarioDAO.agregarUsuario(nuevoUsuario);

            if (registrado) {
                // Responder con éxito si el registro fue correcto
                out.print("{\"success\": true}");
            } else {
                // Responder con un mensaje de error si hubo un problema al registrar
                out.print("{\"success\": false, \"message\": \"Hubo un error al registrarse. Inténtalo de nuevo.\"}");
            }
        }
        out.flush();
    }
}
