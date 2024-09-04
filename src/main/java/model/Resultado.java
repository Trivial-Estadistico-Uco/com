package model;
import model.Usuario;
import model.Quiz;

public class Resultado {
    private int idResultado;
    private int puntuacion;
    private Usuario usuario;  // Relación con Usuario
    private Quiz quiz;        // Relación con Quiz

    // Constructor vacío
    public Resultado() {
    }

    // Constructor con todos los campos
    public Resultado(int idResultado, int puntuacion, Usuario usuario, Quiz quiz) {
        this.idResultado = idResultado;
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.quiz = quiz;
    }

    // Getters y Setters
    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
