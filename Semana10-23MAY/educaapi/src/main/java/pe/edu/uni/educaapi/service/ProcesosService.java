package pe.edu.uni.educaapi.service;

import org.springframework.stereotype.Service;
import pe.edu.uni.educaapi.dto.MatriculaDto;

@Service
public class ProcesosService {

    public MatriculaDto matricular(MatriculaDto dto){

        if(dto.getIdCurso() < 20){
            throw new RuntimeException("Error en el proceso.");
        }

        return dto;
    }
}
