package pe.edu.uni.saludoapi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saludo")
public class SaludoRest {
	
	@GetMapping
	public String saludo() {
		return "Hola todos";
	}
	
	@GetMapping("/personal/{nombre}")
	public String saludo(@PathVariable String nombre) {
		return "Hola " + nombre + ".";
	}
	
	@GetMapping("/calcula/edad/{anio}")
	public String edad(@PathVariable int anio) {
		int edad = 2025 - anio;
		String mensaje = "Tienes " + edad + " años.";
		return mensaje;
	}

}
