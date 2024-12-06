package crudd.asistencia.controller;

import crudd.asistencia.model.ControlAsistencia;
import crudd.asistencia.service.ControlAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlAsistencia")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ControlAsistenciaController {


    @Autowired
    private ControlAsistenciaService controlAsistenciaService;


    @PostMapping("/save")
    public ControlAsistencia saveControlAsistencia(@RequestBody ControlAsistencia controlAsistencia) {
        return controlAsistenciaService.saveControlAsistencia(controlAsistencia);
    }

    @PostMapping("/saveSalida")
    public ControlAsistencia saveControlAsistenciaSalida(@RequestBody ControlAsistencia controlAsistencia) {
        return controlAsistenciaService.saveControlAsistenciaHoraSalida(controlAsistencia);
    }
    @GetMapping("/getAll")
    public List<ControlAsistencia> getAllControlAsistencia() {
        return controlAsistenciaService.getControlAsistencias();
    }
}
