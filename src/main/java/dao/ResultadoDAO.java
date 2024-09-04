package dao;

import dto.ResultadoDTODTO;
import common.Connector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDAO {

    // Método para obtener todos los resultados de la base de datos
    public List<ResultadoDTO> obtenerTodosLosResultados() {
        List<ResultadoDTO> resultados = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idResultado, idQuiz, idUsuario, puntuacion, fechaRealizacion FROM resultado";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ResultadoDTO resultado = new ResultadoDTO();
                resultado.setIdResultado(resultSet.getInt("idResultado"));
                resultado.setIdQuiz(resultSet.getInt("idQuiz"));
                resultado.setIdUsuario(resultSet.getInt("idUsuario"));
                resultado.setPuntuacion(resultSet.getInt("puntuacion"));
                resultado.setFechaRealizacion(resultSet.getDate("fechaRealizacion").toLocalDate());

                resultados.add(resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return resultados;
    }

    // Método para obtener un resultado por su ID
    public ResultadoDTO obtenerResultadoPorId(int idResultado) {
        ResultadoDTO resultado = null;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idResultado, idQuiz, idUsuario, puntuacion, fechaRealizacion FROM resultado WHERE idResultado = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idResultado);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultado = new ResultadoDTO();
                resultado.setIdResultado(resultSet.getInt("idResultado"));
                resultado.setIdQuiz(resultSet.getInt("idQuiz"));
                resultado.setIdUsuario(resultSet.getInt("idUsuario"));
                resultado.setPuntuacion(resultSet.getInt("puntuacion"));
                resultado.setFechaRealizacion(resultSet.getDate("fechaRealizacion").toLocalDate());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return resultado;
    }

    // Método para obtener todos los resultados de un usuario específico
    public List<ResultadoDTO> obtenerResultadosPorUsuario(int idUsuario) {
        List<ResultadoDTO> resultados = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idResultado, idQuiz, idUsuario, puntuacion, fechaRealizacion FROM resultado WHERE idUsuario = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ResultadoDTO resultado = new ResultadoDTO();
                resultado.setIdResultado(resultSet.getInt("idResultado"));
                resultado.setIdQuiz(resultSet.getInt("idQuiz"));
                resultado.setIdUsuario(resultSet.getInt("idUsuario"));
                resultado.setPuntuacion(resultSet.getInt("puntuacion"));
                resultado.setFechaRealizacion(resultSet.getDate("fechaRealizacion").toLocalDate());

                resultados.add(resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return resultados;
    }

    // Método para agregar un nuevo resultado a la base de datos
    public boolean agregarResultado(ResultadoDTO resultado) {
        boolean isAdded = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "INSERT INTO resultado (idQuiz, idUsuario, puntuacion, fechaRealizacion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resultado.getIdQuiz());
            statement.setInt(2, resultado.getIdUsuario());
            statement.setInt(3, resultado.getPuntuacion());
            statement.setDate(4, Date.valueOf(resultado.getFechaRealizacion()));

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

    // Método para actualizar un resultado existente
    public boolean actualizarResultado(ResultadoDTO resultado) {
        boolean isUpdated = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "UPDATE resultado SET idQuiz = ?, idUsuario = ?, puntuacion = ?, fechaRealizacion = ? WHERE idResultado = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, resultado.getIdQuiz());
            statement.setInt(2, resultado.getIdUsuario());
            statement.setInt(3, resultado.getPuntuacion());
            statement.setDate(4, Date.valueOf(resultado.getFechaRealizacion()));
            statement.setInt(5, resultado.getIdResultado());

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

    // Método para eliminar un resultado por su ID
    public boolean eliminarResultado(int idResultado) {
        boolean isDeleted = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "DELETE FROM resultado WHERE idResultado = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idResultado);

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