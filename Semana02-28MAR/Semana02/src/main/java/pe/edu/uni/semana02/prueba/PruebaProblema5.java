/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.semana02.prueba;

import pe.edu.uni.semana02.service.Problema07Service;
import pe.edu.uni.semana02.service.Problema5Service;

/**
 *
 * @author PCE
 */
public class PruebaProblema5 {
    public static void main(String[] args) {
        //Datos
        double lado1 =3;
        double lado2 = 4;
        double lado3 = 9;
        //Proceso
        Problema5Service service = new Problema5Service();
        boolean existe = service.existeTriangulo(lado1, lado2, lado3);
        double area = service.areaTriangulo(lado1, lado2, lado3);
        String report = (existe)?String.valueOf(area):"No existe.";
        //Reporte
        System.out.println("Lado 1: " + lado1);
        System.out.println("Lado 2: " + lado2);
        System.out.println("Lado 3: " + lado3);
        System.out.println("Area: " + report);
     
    }
}
