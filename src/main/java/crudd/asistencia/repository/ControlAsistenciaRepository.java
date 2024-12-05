package crudd.asistencia.repository;

import crudd.asistencia.model.ControlAsistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ControlAsistenciaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public ControlAsistencia guardarConStoredProcedure (ControlAsistencia controlAsistencia){

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("AsignarTurno");


        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_id_empleado", controlAsistencia.getEmpleado().getIdEmpleado());
        inParams.put("p_fecha", controlAsistencia.getFecha());
        inParams.put("p_hora_entrada", controlAsistencia.getHoraEntrada());


        Map<String, Object> out = jdbcCall.execute(inParams);

        controlAsistencia.setId((Long) out.get("id_asistencia"));
        return controlAsistencia;
    }

    public ControlAsistencia guardarConStoredProcedureHoraSalida(ControlAsistencia controlAsistencia) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("RegistrarHoraSalidaYCalcularHoras");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_id_empleado", controlAsistencia.getEmpleado().getIdEmpleado());
        inParams.put("p_fecha", controlAsistencia.getFecha());
        inParams.put("p_hora_salida", controlAsistencia.getHoraSalida());

        try {

            Map<String, Object> outParams = jdbcCall.execute(inParams);
        } catch (Exception e) {
            throw new RuntimeException("Error al ejecutar el procedimiento almacenado: " + e.getMessage(), e);
        }

        return controlAsistencia;
    }
}
