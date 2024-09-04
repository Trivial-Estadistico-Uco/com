package service;

import dao.QuizDAO;
import model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDAO quizDAO;

    // Obtener todos los quizzes
    public List<Quiz> getAllQuizzes() {
        return quizDAO.findAll();
    }

    // Obtener un quiz por su ID
    public Optional<Quiz> getQuizById(int id) {
        return quizDAO.findById(id);
    }

    // Crear un nuevo quiz
    public Quiz createQuiz(Quiz quiz) {
        return quizDAO.save(quiz);
    }

    // Actualizar un quiz existente
    public Quiz updateQuiz(int id, Quiz quizDetails) {
        Optional<Quiz> quizOptional = quizDAO.findById(id);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            quiz.setTematica(quizDetails.getTematica());
            return quizDAO.update(quiz);
        } else {
            return null; // O lanzar una excepción si prefieres manejarlo así
        }
    }

    // Eliminar un quiz
    public void deleteQuiz(int id) {
        quizDAO.deleteById(id);
    }
}
