package pe.edu.uni.educav2app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.uni.educav2app.db.AccesoDB;
import pe.edu.uni.educav2app.dto.MatriculaDto;

public class ProcesoService {


    public MatriculaDto procMatricula(MatriculaDto bean){
        // Variables
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        // Proceso
        try {
            // Imicio de TX
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false); // Inicia la TX
            // Proceso
            validarEmpleado(cn,bean.getEmpleadoId());
            validarAlumno(cn,bean.getAlumnoId());
            double precioLista = validarCurso(cn,bean.getCursoId());
            /*validarTipo(bean.getTipo());
            validarCuotas(bean.getTipo(),bean.getCuotas());
            double precioFinal = getPrecioFinal(bean.getTipo(),precioLista);
            bean.setPrecio(precioFinal);
            registrarMatricula(cn,bean);*/
            // Confirmar TX
            cn.commit();
            bean.setEstado(1);
            bean.setMensaje("Proceso ejecutado correctamente.");
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            bean.setEstado(-1);
            bean.setMensaje(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            bean.setEstado(-1);
            bean.setMensaje("Error en el proceso, intentelo nuevamente.");
        } finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        // Reporte
        return bean;        
    }

    private void validarEmpleado(Connection cn, int empleadoId) throws SQLException {
        String sql = "select count(1) cont from empleado where emp_id=?";
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1, empleadoId);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int cont = rs.getInt("cont");
        rs.close();
        pstm.close();
        if(cont==0){
            throw new SQLException("Empleado no existe.");
        }
    }

    private void validarAlumno(Connection cn, int alumnoId) throws SQLException {
        String sql = "select count(1) cont from alumno where alu_id=?";
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1, alumnoId);
        ResultSet rs = pstm.executeQuery();
        rs.next();
        int cont = rs.getInt("cont");
        rs.close();
        pstm.close();
        if(cont==0){
            throw new SQLException("Alumno no existe.");
        }
    }

    private double validarCurso(Connection cn, int cursoId) throws SQLException {
        String sql = """
                     select cur_vacantes, cur_matriculados, cur_precio
                     from curso where cur_id=?
                     """;
        PreparedStatement pstm = cn.prepareStatement(sql);
        pstm.setInt(1, cursoId);
        ResultSet rs = pstm.executeQuery();
        if(!rs.next()){
            throw new SQLException("No existe el curso.");
        }
        int vacantes = rs.getInt(1);
        int matriculados = rs.getInt(2);
        double precio = rs.getDouble(3);
        rs.close();
        pstm.close();
        if(matriculados>=vacantes){
            throw new SQLException("No hay vacantes disponibles.");
        }
        return precio;
    }
    
}
