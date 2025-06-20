package pe.edu.uni.educaapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.educaapi.service.ConsultasService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/consultas")
public class ConsultasRest {

    @Autowired
    private ConsultasService consultasService;

    /*
    @GetMapping("/curso/precio/{idCurso}")
    public String consultarPrecio(@PathVariable int idCurso){
        double precio = consultasService.obtenerPrecioCurso(idCurso);
        return "precio:" + precio;
    }
    */

    @GetMapping("/curso/precio/{idCurso}")
    public ResponseEntity<String> consultarPrecio(@PathVariable int idCurso) {
        try {
            double precio = consultasService.obtenerPrecioCurso(idCurso);
            return ResponseEntity.ok("" + precio);
        } catch (RuntimeException e) {
            // Aquí puedes personalizar el mensaje de error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso con id " + idCurso + " no encontrado.");
        }
    }

    @GetMapping("/cuota/{idCurso}/{idAlumno}")
    public Map<String,Object> datosCuota
            (@PathVariable int idCurso, @PathVariable int idAlumno) {
        return  consultasService.datosCuota(idCurso, idAlumno);
    }


}
