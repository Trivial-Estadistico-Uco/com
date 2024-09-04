package dao;

import dto.PreguntaDTO;
import common.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAO {

    // Método para obtener todas las preguntas de la base de datos
    public List<PreguntaDTO> obtenerTodasLasPreguntas() {
        List<PreguntaDTO> preguntas = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;
        
        try {
            connection = connector.getConnection();
            String sql = "SELECT idPregunta, texto, Opcion1, Opcion2, Opcion3, Opcion4, respuestaCorrecta, categoria FROM pregunta";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PreguntaDTO pregunta = new PreguntaDTO();
                pregunta.setIdPregunta(resultSet.getInt("idPregunta"));
                pregunta.setTexto(resultSet.getString("texto"));
                pregunta.setOpcion1(resultSet.getString("Opcion1"));
                pregunta.setOpcion2(resultSet.getString("Opcion2"));
                pregunta.setOpcion3(resultSet.getString("Opcion3"));
                pregunta.setOpcion4(resultSet.getString("Opcion4"));
                pregunta.setRespuestaCorrecta(resultSet.getString("respuestaCorrecta"));
                pregunta.setCategoria(resultSet.getString("categoria"));

                preguntas.add(pregunta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return preguntas;
    }

    // Método para obtener una pregunta por su ID
    public PreguntaDTO obtenerPreguntaPorId(int idPregunta) {
        PreguntaDTO pregunta = null;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idPregunta, texto, Opcion1, Opcion2, Opcion3, Opcion4, respuestaCorrecta, categoria FROM pregunta WHERE idPregunta = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPregunta);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pregunta = new PreguntaDTO();
                pregunta.setIdPregunta(resultSet.getInt("idPregunta"));
                pregunta.setTexto(resultSet.getString("texto"));
                pregunta.setOpcion1(resultSet.getString("Opcion1"));
                pregunta.setOpcion2(resultSet.getString("Opcion2"));
                pregunta.setOpcion3(resultSet.getString("Opcion3"));
                pregunta.setOpcion4(resultSet.getString("Opcion4"));
                pregunta.setRespuestaCorrecta(resultSet.getString("respuestaCorrecta"));
                pregunta.setCategoria(resultSet.getString("categoria"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return pregunta;
    }

    // Método para agregar una nueva pregunta a la base de datos
    public boolean agregarPregunta(PreguntaDTO pregunta) {
        boolean isAdded = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "INSERT INTO pregunta (texto, Opcion1, Opcion2, Opcion3, Opcion4, respuestaCorrecta, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pregunta.getTexto());
            statement.setString(2, pregunta.getOpcion1());
            statement.setString(3, pregunta.getOpcion2());
            statement.setString(4, pregunta.getOpcion3());
            statement.setString(5, pregunta.getOpcion4());
            statement.setString(6, pregunta.getRespuestaCorrecta());
            statement.setString(7, pregunta.getCategoria());

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

    // Método para actualizar una pregunta existente
    public boolean actualizarPregunta(PreguntaDTO pregunta) {
        boolean isUpdated = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "UPDATE pregunta SET texto = ?, Opcion1 = ?, Opcion2 = ?, Opcion3 = ?, Opcion4 = ?, respuestaCorrecta = ?, categoria = ? WHERE idPregunta = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pregunta.getTexto());
            statement.setString(2, pregunta.getOpcion1());
            statement.setString(3, pregunta.getOpcion2());
            statement.setString(4, pregunta.getOpcion3());
            statement.setString(5, pregunta.getOpcion4());
            statement.setString(6, pregunta.getRespuestaCorrecta());
            statement.setString(7, pregunta.getCategoria());
            statement.setInt(8, pregunta.getIdPregunta());

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

    // Método para eliminar una pregunta por su ID
    public boolean eliminarPregunta(int idPregunta) {
        boolean isDeleted = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "DELETE FROM pregunta WHERE idPregunta = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPregunta);

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