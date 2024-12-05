package crudd.asistencia.service;

import crudd.asistencia.model.ControlAsistencia;
import crudd.asistencia.repository.ControlAsistenciaRepository;
import crudd.asistencia.repository.IControlAsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ControlAsistenciaService {
    @Autowired
    IControlAsistenciaRepository controlAsistenciaRepository;
    @Autowired
    private ControlAsistenciaRepository controlAsistenciaRepositoryy;


    public List<ControlAsistencia> getControlAsistencias() {
        return controlAsistenciaRepository.findAll();
    }

    public ControlAsistencia saveControlAsistencia(ControlAsistencia controlAsistencia) {
        return controlAsistenciaRepositoryy.guardarConStoredProcedure(controlAsistencia);
    }

    public ControlAsistencia saveControlAsistenciaHoraSalida(ControlAsistencia controlAsistencia) {
        return controlAsistenciaRepositoryy.guardarConStoredProcedureHoraSalida(controlAsistencia);
    }

    public Optional<ControlAsistencia> getById(Long id){
        return controlAsistenciaRepository.findById(id);
    }
    public ControlAsistencia updateById(ControlAsistencia request, Long id) {
        ControlAsistencia controlAsistencia = controlAsistenciaRepository.findById(id).get();

        controlAsistencia.setTurno(request.getTurno());
        return controlAsistencia;
    }
    public Boolean deleteControlAsistencia(Long id){
        try {
            controlAsistenciaRepository.deleteById(id);
            return true;

        }catch(Exception e){
            return false;
        }
    }
}
