package pe.edu.uni.educaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.uni.educaapi.dto.MatriculaDto;
import pe.edu.uni.educaapi.dto.PagoCuotaDto;

import java.util.Map;

@Service
public class ProcesosService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConsultasService consultasService;

    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public MatriculaDto matricular(MatriculaDto dto){
        // Variables
        String sql;
        // Validaciones
        validarMatricula(dto.getIdCurso(), dto.getIdAlumno());
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


    @Transactional(propagation= Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
    public PagoCuotaDto pagarCuota(PagoCuotaDto bean){
        // Validar que matricula exista
        /*
        String sql = """
                select count(1) cont from MATRICULA
                where cur_id=? and alu_id=?
                """;
        int cont = jdbcTemplate.queryForObject(sql,Integer.class,bean.getIdCurso(),bean.getIdAlumno() );
        if(cont==0){
            throw new RuntimeException("ERROR: La matricua NO existe.");
        }
         */
        // Validar datos de la cuota
        Map<String,Object> rec = consultasService.datosCuota(bean.getIdCurso(), bean.getIdAlumno());
        int cuotaActual = Integer.parseInt(rec.get("cuotaActual").toString());
        double importe = Double.parseDouble(rec.get("importe").toString());
        if(cuotaActual!=bean.getCuota() || importe != bean.getImporte()){
            throw new RuntimeException("ERROR: Datos incorrectos, intentelo de nuevo.");
        }
        // Insertar el pago
        String sql = """
                insert into PAGO(cur_id,alu_id,pag_cuota,emp_id,pag_fecha,pag_importe)
                values(?,?,?,?,GETDATE(),?)
                """;
        Object[] parametros = {
                bean.getIdCurso(), bean.getIdAlumno(), bean.getCuota(),
                bean.getIdEmpleado(), bean.getImporte()
        };
        jdbcTemplate.update(sql,parametros);
        return bean;
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

    private void validarMatricula(int idCurso, int idAlumno) {
        String sql = """
                select count(1) cont from MATRICULA
                where cur_id=? and alu_id=?
                """;
        int cont = jdbcTemplate.queryForObject(sql,Integer.class,idCurso, idAlumno);
        if(cont==1){
            throw new RuntimeException("ERROR: Matricula ya existe.");
        }
    }


}
