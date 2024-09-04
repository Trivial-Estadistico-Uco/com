package servlets;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        // Autenticar usuario usando UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO usuario = usuarioDAO.obtenerUsuarioPorCorreo(correo);

        if (usuario != null && usuario.getContrasena().equals(password)) {
            // Inicio de sesión exitoso, guardar usuario en sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("usuario.html"); // Redirigir al menú de usuario
        } else {
            // Error en el inicio de sesión, reenviar a la página de inicio de sesión con mensaje de error
            request.setAttribute("error", "Email o contraseña incorrectos");
            RequestDispatcher dispatcher = request.getRequestDispatcher("iniciarsesion.html");
            dispatcher.forward(request, response);
        }
    }
}
