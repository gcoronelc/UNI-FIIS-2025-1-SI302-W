package pe.edu.uni.educav2app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.edu.uni.educav2app.db.AccesoDB;

public class ConsultaService {
    
    public double obtenerPrecioCurso(int cursoId){
        // Variables
        double precio = 0.0;
        Connection cn = null;
        PreparedStatement pstm;
        ResultSet rs;
        // Datos
        String sql = "select cur_precio from CURSO where cur_id=?";
        // Proceso
        try {
            // Conexion
            cn = AccesoDB.getConnection();
            // Proceso
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cursoId);
            rs = pstm.executeQuery();
            if(!rs.next()){
                throw new SQLException("Codigo de curso no existe.");
            }
            precio = rs.getDouble(1);
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error en el proceso.");
        } finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        // Reporte
        return precio;        
    }
    
}
