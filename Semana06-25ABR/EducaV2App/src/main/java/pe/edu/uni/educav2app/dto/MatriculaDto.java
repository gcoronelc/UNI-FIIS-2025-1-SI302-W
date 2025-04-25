package pe.edu.uni.educav2app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatriculaDto {

    // Datos de entrada
    private int cursoId;
    private int alumnoId;
    private int empleadoId;
    private String tipo;
    private int cuotas;

    // Datos salida
    private double precio;
    private int estado; // +1: Ok,  -1: Error
    private String mensaje;

}
