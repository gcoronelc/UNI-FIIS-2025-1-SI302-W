package pe.edu.uni.educaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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


}
