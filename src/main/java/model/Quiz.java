package model;
import model.Pregunta;

import java.util.List;

public class Quiz {
    private int idQuiz;
    private String tematica;
    private List<Pregunta> preguntas;  // Relación con Pregunta

    // Constructor vacío
    public Quiz() {
    }

    // Constructor con todos los campos
    public Quiz(int idQuiz, String tematica) {
        this.idQuiz = idQuiz;
        this.tematica = tematica;
    }

    // Getters y Setters
    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
