package pe.edu.uni.educaapi.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PagoCuotaDto {

    private int idCurso;
    private int idAlumno;
    private int idEmpleado;
    private int cuota;
    private double importe;
    private String mensaje;

}
