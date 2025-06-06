package pe.edu.uni.educaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultasService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public double obtenerPrecioCurso(int idCurso) {

        // Validación de entrada
        if (idCurso <= 0) {
            throw new IllegalArgumentException("El ID del curso debe ser mayor a 0");
        }

        // Sentencia
        String sql = "SELECT cur_precio FROM CURSO WHERE cur_id = ?";

        try {
            // Proceso
            Double precio = jdbcTemplate.queryForObject(sql, Double.class, idCurso);

            // Validación de resultado nulo
            if (precio == null) {
                return 0.0; // o lanzar excepción según requerimientos
            }

            // Reporte
            return precio;

        } catch (EmptyResultDataAccessException e) {
            // No se encontró el curso
            throw new RuntimeException("Curso no encontrado con ID: " + idCurso, e);

        } catch (DataAccessException e) {
            throw new RuntimeException("Error al acceder a la base de datos", e);

        } catch (Exception e) {
              throw new RuntimeException("Error interno del sistema", e);
        }
    }

    public Map<String,Object> datosCuota(int idCurso, int idAlumno){
        // Paso 1 validación
        String sql = "select count(1) cont from MATRICULA where cur_id=? and alu_id=?";
        int cont = jdbcTemplate.queryForObject(sql,Integer.class,idCurso,idAlumno);
        if(cont==0){
            throw new RuntimeException("ERROR: Matricula no existe.");
        }
        // Paso 2: Recuperar datos
        sql = """
                with
                -- paso 1
                MAT AS (
                	select mat_tipo, mat_precio, mat_cuotas
                	from MATRICULA where cur_id = ? and alu_id = ?
                ),
                -- Paso 2
                PAG AS (
                	select max(pag_cuota) ucuota, sum(pag_importe) pagado\s
                	from PAGO where cur_id = ? and alu_id = ?
                )
                -- Consulta final
                select
                	MAT.mat_tipo tipo, MAT.mat_precio precio,
                	MAT.mat_cuotas cuotas,
                	ISNULL(PAG.ucuota,0) ultcuota,
                	ISNULL(PAG.pagado,0.0) pagado
                from MAT cross join PAG
                """;
        Map<String,Object> rec1 = jdbcTemplate.queryForMap(sql, idCurso, idAlumno,idCurso, idAlumno);
        String tipo = rec1.get("tipo").toString();
        double precio = Double.parseDouble(rec1.get("precio").toString());
        int cuotas = Integer.parseInt(rec1.get("cuotas").toString());
        int ultcuota = Integer.parseInt(rec1.get("ultcuota").toString());
        double pagado = Double.parseDouble(rec1.get("pagado").toString());
        // Paso 3: Proceso
        Map<String,Object> rec2 = new HashMap<>();
        rec2.put("idCurso", idCurso);
        rec2.put("idAlumno", idAlumno);
        rec2.put("cuota", 0);
        rec2.put("importe", 0.0);
        if(ultcuota == cuotas){
            rec2.put("estado",0);
            return rec2;
        }
        ultcuota++;
        rec2.put("cuota", ultcuota);
        rec2.put("estado",1);
        if(ultcuota==cuotas){
            rec2.put("importe", precio - pagado);
            return rec2;
        }
        return rec2;
    }
}
