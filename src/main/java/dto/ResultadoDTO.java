package controller.dto;

import java.time.LocalDate;

public class ResultadoDTO {

    // Atributos privados
    private int idQuiz;          // Clave foránea de Quiz
    private int idUsuario;       // Clave foránea de UsuarioDTO
    private int puntuacion;      // Puntuación obtenida en el quiz
    private LocalDate fechaRealizacion; // Fecha en la que se realizó el quiz

    // Constructor vacío
    public ResultadoDTO() {
    }

    // Constructor con parámetros
    public ResultadoDTO(int idQuiz, int idUsuario, int puntuacion, LocalDate fechaRealizacion) {
        this.idQuiz = idQuiz;
        this.idUsuario = idUsuario;
        this.puntuacion = puntuacion;
        this.fechaRealizacion = fechaRealizacion;
    }

    // Métodos getter y setter para cada atributo

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @Override
    public String toString() {
        return "ResultadoDTO{" +
                "idQuiz=" + idQuiz +
                ", idUsuario=" + idUsuario +
                ", puntuacion=" + puntuacion +
                ", fechaRealizacion=" + fechaRealizacion +
                '}';
    }
}