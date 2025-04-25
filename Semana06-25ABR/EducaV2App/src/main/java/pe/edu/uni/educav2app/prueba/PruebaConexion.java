package pe.edu.uni.educav2app.prueba;

import java.sql.Connection;
import pe.edu.uni.educav2app.db.AccesoDB;

public class PruebaConexion {
    
    public static void main(String[] args) {
        try {
            Connection cn = AccesoDB.getConnection();
            System.out.println("Conexion ok.");
            cn.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
