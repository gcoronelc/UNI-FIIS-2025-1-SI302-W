package pe.edu.uni.educaapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.educaapi.dto.MatriculaDto;
import pe.edu.uni.educaapi.service.ProcesosService;

@RestController
@RequestMapping("/api/procesos")
public class ProcesosRest {

    @Autowired
    public ProcesosService procesosService;

    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestBody MatriculaDto bean){
        try {
            MatriculaDto result = procesosService.matricular(bean);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
