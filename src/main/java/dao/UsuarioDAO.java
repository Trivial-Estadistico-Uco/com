package dao;

import dto.UsuarioDTO;
import common.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para obtener todos los usuarios de la base de datos
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;
        
        try {
            connection = connector.getConnection();
            String sql = "SELECT id, nombre, apellido, correo, contrasena, admin FROM usuario";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                usuario.setAdmin(resultSet.getBoolean("admin"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return usuarios;
    }

    // Método para obtener un usuario por su ID
    public UsuarioDTO obtenerUsuarioPorId(int idUsuario) {
        UsuarioDTO usuario = null;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT id, nombre, apellido, correo, contrasena, admin FROM usuario WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new UsuarioDTO();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                usuario.setAdmin(resultSet.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return usuario;
    }

    // Método para obtener un usuario por su correo
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        UsuarioDTO usuario = null;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT id, nombre, apellido, correo, contrasena, admin FROM usuario WHERE correo = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new UsuarioDTO();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setContrasena(resultSet.getString("contrasena"));
                usuario.setAdmin(resultSet.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return usuario;
    }

    // Método para agregar un nuevo usuario a la base de datos
    public boolean agregarUsuario(UsuarioDTO usuario) {
        boolean isAdded = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "INSERT INTO usuario (nombre, apellido, correo, contrasena, admin) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getContrasena());
            statement.setBoolean(5, usuario.isAdmin());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                isAdded = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isAdded;
    }

    // Método para actualizar un usuario existente
    public boolean actualizarUsuario(UsuarioDTO usuario) {
        boolean isUpdated = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "UPDATE usuario SET nombre = ?, apellido = ?, correo = ?, contrasena = ?, admin = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, usuario.getContrasena());
            statement.setBoolean(5, usuario.isAdmin());
            statement.setInt(6, usuario.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isUpdated;
    }

    // Método para eliminar un usuario por su ID
    public boolean eliminarUsuario(int idUsuario) {
        boolean isDeleted = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                isDeleted = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isDeleted;
    }
}