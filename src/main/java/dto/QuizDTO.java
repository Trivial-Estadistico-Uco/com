package controller.dto;

import java.util.List;
import controller.dto.PreguntaDTO;  // Importar la clase PreguntaDTO

public class QuizDTO {

    // Atributos privados
    private int idQuiz;
    private List<PreguntaDTO> preguntas; // Lista de preguntas del quiz
    private String tematica; // Temática del quiz

    //Atributo publico
    public int  questionIndex = 0;

    // Constructor vacío
    public QuizDTO() {
    }

    // Constructor con parámetros
    public QuizDTO(int idQuiz, List<PreguntaDTO> preguntas, String tematica) {
        this.idQuiz = idQuiz;
        this.preguntas = preguntas;
        this.tematica = tematica;
    }

    // Métodos getter y setter para cada atributo

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public List<PreguntaDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

     /**
     * 
     * @returns {PreguntaDTO} the question found
     */
    public PreguntaDTO getQuestionIndex(){
        return this.questions[this.questionIndex];
    }

    /**
     * 
     * @returns {boolean} true if he finishes the questions
     */
    public boolean isEnded() {
        return this.questions.length === this.questionIndex
    }

    /**
     * 
     * @param {string} answer some text
     */
    public void guess(answer){

        if(this.getQuestionIndex().correctAnswer(answer)){
            this.score++;
        }

        this.questionIndex++;
    }
}