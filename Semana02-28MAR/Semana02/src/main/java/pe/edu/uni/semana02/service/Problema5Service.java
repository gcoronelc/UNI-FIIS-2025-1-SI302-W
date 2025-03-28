/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.semana02.service;

/**
 *
 * @author PCE
 */
public class Problema5Service {
    public boolean existeTriangulo(double lado1, double lado2, double lado3){
        if(lado1<=0 ||lado2<=0 ||lado3<=0){
            return false;
        }
        if( lado1>=(lado2+lado3) ){
            return false;
        }
        if( lado2>=(lado1+lado3) ){
            return false;
        }
        if( lado3>=(lado2+lado1) ){
            return false;
        }
        return true;
    }
    public double areaTriangulo(double lado1, double lado2, double lado3){
        double area;
        double s;
        s=(lado1+lado2+lado3)/2;
        area = Math.sqrt(s*(s-lado1)*(s-lado2)*(s-lado3));
        return area;
    }
}
