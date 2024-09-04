package service;

import dao.ResultadoDAO;
import model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoDAO resultadoDAO;

    // Obtener todos los resultados
    public List<Resultado> getAllResultados() {
        return resultadoDAO.findAll();
    }

    // Obtener un resultado por su ID
    public Optional<Resultado> getResultadoById(int id) {
        return resultadoDAO.findById(id);
    }

    // Crear un nuevo resultado
    public Resultado createResultado(Resultado resultado) {
        return resultadoDAO.save(resultado);
    }

    // Actualizar un resultado existente
    public Resultado updateResultado(int id, Resultado resultadoDetails) {
        Optional<Resultado> resultadoOptional = resultadoDAO.findById(id);
        if (resultadoOptional.isPresent()) {
            Resultado resultado = resultadoOptional.get();
            resultado.setPuntuacion(resultadoDetails.getPuntuacion());
            resultado.setIdUsuario(resultadoDetails.getIdUsuario());
            resultado.setIdQuiz(resultadoDetails.getIdQuiz());
            return resultadoDAO.update(resultado);
        } else {
            return null; // O lanzar una excepción si prefieres manejarlo así
        }
    }

    // Eliminar un resultado
    public void deleteResultado(int id) {
        resultadoDAO.deleteById(id);
    }

    // Obtener resultados por usuario
    public List<Resultado> getResultadosByUsuario(int idUsuario) {
        return resultadoDAO.findByUsuario(idUsuario);
    }

    // Obtener resultados por quiz
    public List<Resultado> getResultadosByQuiz(int idQuiz) {
        return resultadoDAO.findByQuiz(idQuiz);
    }
}
