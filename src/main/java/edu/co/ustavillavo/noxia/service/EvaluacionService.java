package edu.co.ustavillavo.noxia.service;

import edu.co.ustavillavo.noxia.model.Evaluacion;
import edu.co.ustavillavo.noxia.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionService(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public Evaluacion guardar(Evaluacion evaluacion) {
        // Calcular puntuaciones DASS-21
        int[] dassIndices = {2, 4, 9, 12, 15, 16, 20}; // Depresión
        int[] dassAnsiedad = {1, 3, 6, 8, 14, 18, 19};
        int[] dassEstres = {0, 5, 7, 10, 11, 13, 17};

        // Procesar DASS si hay respuestas
        if (evaluacion.getRespuestasDass() != null && !evaluacion.getRespuestasDass().isEmpty()) {
            int[] respuestas = parseRespuestas(evaluacion.getRespuestasDass());
            evaluacion.setPuntuacionDassDepresion(calcularSubescala(respuestas, dassIndices));
            evaluacion.setPuntuacionDassAnsiedad(calcularSubescala(respuestas, dassAnsiedad));
            evaluacion.setPuntuacionDassEstres(calcularSubescala(respuestas, dassEstres));
        }

        // Procesar GAD-7
        if (evaluacion.getRespuestasGad() != null && !evaluacion.getRespuestasGad().isEmpty()) {
            int[] respuestas = parseRespuestas(evaluacion.getRespuestasGad());
            evaluacion.setPuntuacionGad(calcularTotal(respuestas));
        }

        // Procesar PHQ-9
        if (evaluacion.getRespuestasPhq() != null && !evaluacion.getRespuestasPhq().isEmpty()) {
            int[] respuestas = parseRespuestas(evaluacion.getRespuestasPhq());
            evaluacion.setPuntuacionPhq(calcularTotal(respuestas));
        }

        // Calcular severidad y prioridad
        calcularSeveridadYPrioridad(evaluacion);

        return evaluacionRepository.save(evaluacion);
    }

    public List<Evaluacion> listarTodas() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    public List<Evaluacion> buscarPorCedula(String cedula) {
        return evaluacionRepository.findByCedula(cedula);
    }

    private int[] parseRespuestas(String respuestas) {
        String[] partes = respuestas.split(",");
        int[] resultado = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            try {
                resultado[i] = Integer.parseInt(partes[i].trim());
            } catch (NumberFormatException e) {
                resultado[i] = 0;
            }
        }
        return resultado;
    }

    private int calcularSubescala(int[] respuestas, int[] indices) {
        int total = 0;
        for (int idx : indices) {
            if (idx < respuestas.length) {
                total += respuestas[idx];
            }
        }
        return total;
    }

    private int calcularTotal(int[] respuestas) {
        int total = 0;
        for (int valor : respuestas) {
            total += valor;
        }
        return total;
    }

    private void calcularSeveridadYPrioridad(Evaluacion eval) {
        String severidad = "LEVE";
        String prioridad = "BAJA";

        int phq9 = eval.getPuntuacionPhq() != null ? eval.getPuntuacionPhq() : 0;
        int dassDep = eval.getPuntuacionDassDepresion() != null ? eval.getPuntuacionDassDepresion() : 0;
        int dassAns = eval.getPuntuacionDassAnsiedad() != null ? eval.getPuntuacionDassAnsiedad() : 0;
        int dassEst = eval.getPuntuacionDassEstres() != null ? eval.getPuntuacionDassEstres() : 0;
        int gad7 = eval.getPuntuacionGad() != null ? eval.getPuntuacionGad() : 0;

        // Determinar severidad (usando el peor resultado)
        if (phq9 >= 20 || dassDep >= 14 || dassAns >= 10 || dassEst >= 17) {
            severidad = "EXTREMADAMENTE_SEVERO";
        } else if (phq9 >= 15 || dassDep >= 11 || dassAns >= 8 || dassEst >= 13) {
            severidad = "SEVERO";
        } else if (phq9 >= 10 || dassDep >= 7 || dassAns >= 5 || dassEst >= 10) {
            severidad = "MODERADO";
        } else if (phq9 >= 5 || dassDep >= 6 || dassAns >= 4 || dassEst >= 9) {
            severidad = "LEVE";
        } else {
            severidad = "NINGUNO";
        }

        // Determinar prioridad
        if (phq9 >= 20 || dassDep >= 14 || dassAns >= 10 || dassEst >= 17 || gad7 >= 15) {
            prioridad = "ALTA";
        } else if (phq9 >= 10 || dassDep >= 7 || dassAns >= 5 || dassEst >= 10) {
            prioridad = "MEDIA";
        } else {
            prioridad = "BAJA";
        }

        eval.setNivelSeveridad(severidad);
        eval.setPrioridad(prioridad);
    }
}