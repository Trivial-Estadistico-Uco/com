package service;

import dao.PreguntaDAO;
import model.Pregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaService {

    @Autowired
    private PreguntaDAO preguntaDAO;

    // Obtener todas las preguntas
    public List<Pregunta> getAllPreguntas() {
        return preguntaDAO.findAll();
    }

    // Obtener una pregunta por su ID
    public Optional<Pregunta> getPreguntaById(int id) {
        return preguntaDAO.findById(id);
    }

    // Crear una nueva pregunta
    public Pregunta createPregunta(Pregunta pregunta) {
        return preguntaDAO.save(pregunta);
    }

    // Actualizar una pregunta existente
    public Pregunta updatePregunta(int id, Pregunta preguntaDetails) {
        Optional<Pregunta> preguntaOptional = preguntaDAO.findById(id);
        if (preguntaOptional.isPresent()) {
            Pregunta pregunta = preguntaOptional.get();
            pregunta.setTexto(preguntaDetails.getTexto());
            pregunta.setOpcion1(preguntaDetails.getOpcion1());
            pregunta.setOpcion2(preguntaDetails.getOpcion2());
            pregunta.setOpcion3(preguntaDetails.getOpcion3());
            pregunta.setOpcion4(preguntaDetails.getOpcion4());
            pregunta.setRespuestaCorrecta(preguntaDetails.getRespuestaCorrecta());
            pregunta.setCategoria(preguntaDetails.getCategoria());
            return preguntaDAO.update(pregunta);
        } else {
            return null; // O lanzar una excepción si prefieres manejarlo así
        }
    }

    // Eliminar una pregunta
    public void deletePregunta(int id) {
        preguntaDAO.deleteById(id);
    }
}
