package edu.co.ustavillavo.noxia.controller;

import edu.co.ustavillavo.noxia.dto.ApiResponse;
import edu.co.ustavillavo.noxia.model.Evaluacion;
import edu.co.ustavillavo.noxia.service.EvaluacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @PostMapping
    public ApiResponse<Evaluacion> guardar(@RequestBody Evaluacion evaluacion) {
        Evaluacion guardada = evaluacionService.guardar(evaluacion);
        return new ApiResponse<>(505, "Evaluación guardada correctamente", guardada);
    }

    @GetMapping
    public ApiResponse<List<Evaluacion>> listar() {
        return ApiResponse.ok(evaluacionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ApiResponse<Evaluacion> buscar(@PathVariable Long id) {
        Evaluacion evaluacion = evaluacionService.buscarPorId(id);
        if (evaluacion == null) {
            return ApiResponse.error(404, "Evaluación no encontrada");
        }
        return ApiResponse.ok(evaluacion);
    }

    @GetMapping("/cedula/{cedula}")
    public ApiResponse<List<Evaluacion>> buscarPorCedula(@PathVariable String cedula) {
        return ApiResponse.ok(evaluacionService.buscarPorCedula(cedula));
    }
}