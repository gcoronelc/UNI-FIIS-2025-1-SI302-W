package pe.edu.uni.educaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.educaapi.dto.MatriculaDto;

@Service
public class ProcesosService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public MatriculaDto matricular(MatriculaDto dto){
        // Variables
        String sql;
        // Validaciones
        validarMatricula(dto);
        validarCurso(dto.getIdCurso());
        validarAlumno(dto.getIdAlumno());
        validarEmpleado(dto.getIdEmpleado());
        validarTipo(dto.getTipo());
        validarCuotas(dto);
        // Proceso
        sql ="select cur_precio from CURSO where cur_id=?";
        double precio = jdbcTemplate.queryForObject(sql,Double.class,dto.getIdCurso());
        sql = """
                insert into MATRICULA(cur_id,alu_id,emp_id,
                mat_tipo,mat_fecha,mat_precio,mat_cuotas)
                values(?,?,?,?,GETDATE(),?,?)
                """;
        Object[] datos = {
          dto.getIdCurso(), dto.getIdAlumno(), dto.getIdEmpleado(),
          dto.getTipo(), precio, dto.getCuotas()
        };
        jdbcTemplate.update(sql,datos);
        sql = """
                update curso
                set cur_matriculados = cur_matriculados + 1
                where cur_id = ?
                """;
        jdbcTemplate.update(sql,dto.getIdCurso());
        // Reporte
        dto.setPrecio(precio);
        return dto;
    }

    private void validarCuotas(MatriculaDto dto) {
        if (!dto.getTipo().equals("REGULAR") && dto.getCuotas() != 1) {
            throw new RuntimeException("ERROR: Numero de cuotas incorrecto.");
        }
        if (dto.getCuotas() < 1 || dto.getCuotas() > 3) {
            throw new RuntimeException("ERROR: Numero de cuotas incorrecto.");
        }
    }

    private void validarTipo(String tipo) {
        String tipos[]={"REGULAR","MEDIABECA","BECA"};
        int cont=0;
        for (String tipo2 :tipos) {
            cont += tipo2.equals(tipo)?1:0;
        }
        if(cont==0){
            throw new RuntimeException("ERROR:Tipo incorrecto");
        }

    }

    private void validarEmpleado(int idEmpleado) {
        String sql = """
                select count(1) cont from EMPLEADO
                where emp_id=?
                """;
        int cont = jdbcTemplate.queryForObject(sql, Integer.class, idEmpleado);
        if(cont==0){
            throw new RuntimeException("ERROR: Empleado no existe.");
        }
    }

    private void validarAlumno(int idAlumno) {
        String sql = """
                select count(1) cont from ALUMNO
                where alu_id=?
                """;
        int cont = jdbcTemplate.queryForObject(sql, Integer.class, idAlumno);
        if(cont==0){
            throw new RuntimeException("ERROR: Alumno no existe.");
        }
    }

    private void validarCurso(int idCurso) {
        // Si el curso existe
        String sql = "select count(1) cont from CURSO where cur_id=?";
        int cont = jdbcTemplate.queryForObject(sql,Integer.class,idCurso);
        if(cont==0){
            throw new RuntimeException("ERROR: Curso no existe.");
        }
        // Si el curso tiene vacantes
        sql = "select cur_vacantes - cur_matriculados cont from CURSO where cur_id=?";
        cont = jdbcTemplate.queryForObject(sql,Integer.class,idCurso);
        if(cont<=0){
            throw new RuntimeException("ERROR: Curso no tiene vacantes.");
        }
    }

    private void validarMatricula(MatriculaDto dto) {
        String sql = """
                select count(1) cont from MATRICULA
                where cur_id=? and alu_id=?
                """;
        int cont = jdbcTemplate.queryForObject(sql,Integer.class,dto.getIdCurso(), dto.getIdAlumno());
        if(cont==1){
            throw new RuntimeException("ERROR: Matricula ya existe.");
        }
    }


}
