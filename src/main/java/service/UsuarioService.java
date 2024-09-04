package service;

import dao.UsuarioDAO;
import model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.findAll();
    }

    // Obtener un usuario por su ID
    public Optional<Usuario> getUsuarioById(int id) {
        return usuarioDAO.findById(id);
    }

    // Crear un nuevo usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    // Actualizar un usuario existente
    public Usuario updateUsuario(int id, Usuario usuarioDetails) {
        Optional<Usuario> usuarioOptional = usuarioDAO.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setContrasena(usuarioDetails.getContrasena());
            usuario.setAdmin(usuarioDetails.isAdmin());
            return usuarioDAO.update(usuario);
        } else {
            return null; // O lanzar una excepción si prefieres manejarlo así
        }
    }

    // Eliminar un usuario
    public void deleteUsuario(int id) {
        usuarioDAO.deleteById(id);
    }

    // Obtener un usuario por su correo electrónico
    public Optional<Usuario> getUsuarioByCorreo(String correo) {
        return usuarioDAO.findByCorreo(correo);
    }
}
