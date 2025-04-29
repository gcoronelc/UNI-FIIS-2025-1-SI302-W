package pe.edu.uni.educav2app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.uni.educav2app.db.AccesoDB;
import pe.edu.uni.educav2app.dto.RepoCurso;
import pe.edu.uni.educav2app.dto.ResumenDto;

public class ConsultaService {

	public double obtenerPrecioCurso(int cursoId) {
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
			if (!rs.next()) {
				throw new SQLException("Codigo de curso no existe.");
			}
			precio = rs.getDouble(1);
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte
		return precio;
	}

	public List<ResumenDto> obtenerResumen() {
		// Variables
		List<ResumenDto> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		// Datos
		String sql = """
                     WITH
                     PA1 as (
                     	select cur_id CODIGO,  cur_nombre NOMBRE, cur_matriculados MATRICULADOS
                     	from CURSO where cur_matriculados>0
                     ),
                     PA2 as (
                     	select cur_id CODIGO, sum(mat_precio) ING_PROYECTADO
                     	from MATRICULA group by cur_id
                     ),
                     PA3 as (
                     	select cur_id CODIGO, sum(pag_importe) ING_REAL
                     	from PAGO group by cur_id
                     )
                     select 
                     	PA1.CODIGO, PA1.NOMBRE, PA1.MATRICULADOS,
                     	PA2.ING_PROYECTADO, ISNULL(PA3.ING_REAL,0.0) ING_REAL
                     from PA1
                     join PA2 on PA1.CODIGO = PA2.CODIGO
                     left join PA3 on PA1.CODIGO = PA3.CODIGO
                     """;
		// Proceso
		try {
			// Conexion
			cn = AccesoDB.getConnection();
			// Proceso
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ResumenDto bean = new ResumenDto();
				bean.setCodigo(rs.getInt("CODIGO"));
				bean.setNombre(rs.getString("NOMBRE"));
				bean.setMatriculados(rs.getInt("MATRICULADOS"));
				bean.setProyectado(rs.getDouble("ING_PROYECTADO"));
				bean.setReal(rs.getDouble("ING_REAL"));
				lista.add(bean);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte
		return lista;
	}

	public List<RepoCurso> obtenerRepoCurso(int codigo) {
		// Variables
		List<RepoCurso> lista = new ArrayList<>();
		Connection cn = null;
		PreparedStatement pstm;
		ResultSet rs;
		// Datos
		String sql = """
                     WITH
                     PA1 as (
                     	select 
                     		a.alu_id CODIGO, a.alu_nombre NOMBRE, m.mat_tipo TIPO,
                     		m.mat_precio PRECIO, m.mat_cuotas CUOTAS
                     	from MATRICULA m
                     	join ALUMNO a on m.alu_id = a.alu_id
                     	where m.cur_id = ?
                     	),
                     PA2 as (
                     	select alu_id CODIGO, sum(pag_importe) ABONO
                     	from PAGO
                     	where cur_id = ?
                     	group by alu_id
                     )
                     select 
                     	PA1.CODIGO, PA1.NOMBRE, PA1.TIPO, 
                     	PA1.PRECIO, PA1.CUOTAS, 
                     	ISNULL(PA2.ABONO,0) ABONO,
                     	PA1.PRECIO - ISNULL(PA2.ABONO,0) SALDO
                     from PA1 left join PA2 
                     on PA1.CODIGO = PA2.CODIGO
                     """;
		// Proceso
		try {
			// Conexion
			cn = AccesoDB.getConnection();
			// Proceso
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			pstm.setInt(2, codigo);
			rs = pstm.executeQuery();
			while (rs.next()) {
				RepoCurso bean = new RepoCurso();
				bean.setCodigo(rs.getInt("CODIGO"));
				bean.setNombre(rs.getString("NOMBRE"));
				bean.setTipo(rs.getString("TIPO"));
				bean.setPrecio(rs.getDouble("PRECIO"));
				bean.setCuotas(rs.getInt("CUOTAS"));
				bean.setAbono(rs.getDouble("ABONO"));
				bean.setSaldo(rs.getDouble("SALDO"));
				lista.add(bean);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("Error en el proceso.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		// Reporte
		return lista;
	}
}
