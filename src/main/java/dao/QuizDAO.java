package dao;

import dto.PreguntaDTO;
import dto.QuizDTO;
import common.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    // Método para obtener todos los quizzes de la base de datos
    public List<QuizDTO> obtenerTodosLosQuizzes() {
        List<QuizDTO> quizzes = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idQuiz, tematica FROM quiz";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                QuizDTO quiz = new QuizDTO();
                quiz.setIdQuiz(resultSet.getInt("idQuiz"));
                quiz.setTematica(resultSet.getString("tematica"));
                quiz.setPreguntas(obtenerPreguntasPorQuizId(resultSet.getInt("idQuiz")));

                quizzes.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return quizzes;
    }

    // Método para obtener un quiz por su ID
    public QuizDTO obtenerQuizPorId(int idQuiz) {
        QuizDTO quiz = null;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT idQuiz, tematica FROM quiz WHERE idQuiz = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idQuiz);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                quiz = new QuizDTO();
                quiz.setIdQuiz(resultSet.getInt("idQuiz"));
                quiz.setTematica(resultSet.getString("tematica"));
                quiz.setPreguntas(obtenerPreguntasPorQuizId(resultSet.getInt("idQuiz")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return quiz;
    }

    // Método para obtener las preguntas asociadas a un quiz por el ID del quiz
    private List<PreguntaDTO> obtenerPreguntasPorQuizId(int idQuiz) {
        List<PreguntaDTO> preguntas = new ArrayList<>();
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "SELECT p.idPregunta, p.texto, p.Opcion1, p.Opcion2, p.Opcion3, p.Opcion4, p.respuestaCorrecta, p.categoria " +
                         "FROM pregunta p JOIN quiz_pregunta qp ON p.idPregunta = qp.idPregunta WHERE qp.idQuiz = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idQuiz);
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

    // Método para agregar un nuevo quiz a la base de datos
    public boolean agregarQuiz(QuizDTO quiz) {
        boolean isAdded = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "INSERT INTO quiz (tematica) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, quiz.getTematica());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idQuiz = generatedKeys.getInt(1);
                    quiz.setIdQuiz(idQuiz);
                    isAdded = agregarPreguntasAlQuiz(quiz);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isAdded;
    }

    // Método para asociar preguntas a un quiz en la tabla de relación
    private boolean agregarPreguntasAlQuiz(QuizDTO quiz) {
        boolean isAdded = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "INSERT INTO quiz_pregunta (idQuiz, idPregunta) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (PreguntaDTO pregunta : quiz.getPreguntas()) {
                statement.setInt(1, quiz.getIdQuiz());
                statement.setInt(2, pregunta.getIdPregunta());
                statement.addBatch();
            }

            int[] rowsInserted = statement.executeBatch();
            isAdded = rowsInserted.length == quiz.getPreguntas().size();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isAdded;
    }

    // Método para actualizar un quiz existente
    public boolean actualizarQuiz(QuizDTO quiz) {
        boolean isUpdated = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "UPDATE quiz SET tematica = ? WHERE idQuiz = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, quiz.getTematica());
            statement.setInt(2, quiz.getIdQuiz());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                eliminarPreguntasDelQuiz(quiz.getIdQuiz());
                isUpdated = agregarPreguntasAlQuiz(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }

        return isUpdated;
    }

    // Método para eliminar las preguntas asociadas a un quiz en la tabla de relación
    private void eliminarPreguntasDelQuiz(int idQuiz) {
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            String sql = "DELETE FROM quiz_pregunta WHERE idQuiz = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idQuiz);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.deleteConnection(connection);
        }
    }

    // Método para eliminar un quiz por su ID
    public boolean eliminarQuiz(int idQuiz) {
        boolean isDeleted = false;
        Connector connector = new Connector();
        Connection connection = null;

        try {
            connection = connector.getConnection();
            eliminarPreguntasDelQuiz(idQuiz);
            String sql = "DELETE FROM quiz WHERE idQuiz = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idQuiz);

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
