package controller;

import model.Pregunta;
import model.Quiz;
import model.Resultado;
import model.Usuario;
import service.QuizService;
import service.ResultadoService;
import service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controller")
public class TriviaController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ResultadoService resultadoService;

    @Autowired
    private UsuarioService usuarioService;

    // Ruta para obtener todos los quizzes
    @GetMapping("/quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // Ruta para obtener un quiz por su ID
    @GetMapping("/quiz/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    // Ruta para crear un nuevo quiz
    @PostMapping("/quiz")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    // Ruta para agregar una pregunta a un quiz
    @PostMapping("/quiz/{id}/pregunta")
    public Quiz addPreguntaToQuiz(@PathVariable int id, @RequestBody Pregunta pregunta) {
        return quizService.addPreguntaToQuiz(id, pregunta);
    }

    // Ruta para obtener todas las preguntas de un quiz
    @GetMapping("/quiz/{id}/preguntas")
    public List<Pregunta> getPreguntasByQuiz(@PathVariable int id) {
        return quizService.getPreguntasByQuiz(id);
    }

    // Ruta para obtener el resultado de un usuario en un quiz
    @GetMapping("/resultado/{userId}/{quizId}")
    public Resultado getResultado(@PathVariable int userId, @PathVariable int quizId) {
        return resultadoService.getResultado(userId, quizId);
    }

    // Ruta para registrar un nuevo resultado
    @PostMapping("/resultado")
    public Resultado createResultado(@RequestBody Resultado resultado) {
        return resultadoService.createResultado(resultado);
    }

    // Ruta para registrar un nuevo usuario
    @PostMapping("/usuario")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    // Ruta para obtener todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
}
