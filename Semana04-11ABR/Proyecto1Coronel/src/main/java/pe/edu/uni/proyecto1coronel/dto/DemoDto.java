package pe.edu.uni.proyecto1coronel.dto;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class DemoDto {

	// Datos de entrada
	private int n; // Tamaño del vector
	private int opcion; // 1: Suma, 2: Vector invertido

	// Resultado
	private int[] vectorOrigen;
	private String resultado;

	public DemoDto() {
	}

	public DemoDto(int n, int opcion) {
		this.n = n;
		this.opcion = opcion;
		this.vectorOrigen = null;
		this.resultado = null;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public int[] getVectorOrigen() {
		return vectorOrigen;
	}

	public void setVectorOrigen(int[] vectorOrigen) {
		this.vectorOrigen = vectorOrigen;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
