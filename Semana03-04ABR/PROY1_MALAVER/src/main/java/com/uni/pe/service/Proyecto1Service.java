
package com.uni.pe.service;

public class Proyecto1Service {
   public boolean esBisiesto(int AA){
    if(AA%100 == 0){
        if(AA%400 != 0){
            return false;
        }
    }
    if(AA%4 != 0){
        return false;
    }
    return true;
   }
   public boolean getValidezAño(int AA){
       return AA >= 0;
   }
   public boolean getValidezMes(int MM){
       if(MM>0 && MM<13){
           return true;
       }
       return false;
   }
   public boolean getValidezDia(int AA, int MM, int DD){
       if(DD<=0){
           return false;
       }
       //Variables
       Proyecto1Service service = new Proyecto1Service();
       int Dias[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
       int ind = -1;
       for (int i = 0; i < MM; i++) {
           ind++;
           
       }
       //Proceso
       if(service.esBisiesto(AA)){
           if((MM == 2) && (DD<0 || DD>Dias[ind]+1)){
               return false;
           } 
       }
       else{
           if(DD<0 || DD>Dias[ind]){
               return false;
           }
       }
       return true;
   }
   public boolean getValidezFecha(int AA, int MM, int DD){
       Proyecto1Service service = new Proyecto1Service();
       if(service.getValidezAño(AA) && service.getValidezMes(MM)){
           if(service.getValidezDia(AA, MM, DD)){
             return true;  
           }
       }
       return false;
   }
   public String getTrimestre(int MM){
       int ind = -1;
       for (int i = 0; i < MM; i++) {
           ind++;
       }
       if(ind>=0 && ind<3){
           return "Primero";
       }
       if(ind>=3 && ind<6){
           return "Segundo";
       }
       if(ind>=6 && ind<9){
           return "Tercero";
       }
       return "Cuarto";
   }
   public String getEstacion(int MM, int DD){
       int nDias = 0;
       int ind = -1;
       int Dias[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
       for (int i = 0; i < MM; i++) {
           ind++;
       }
       for (int i = 0; i < ind; i++) {
           nDias+=Dias[i];
       }
       nDias+=DD;
       if(nDias>=79 && nDias<171){
           return "Otoño";
       }
       if(nDias>=171 && nDias<265){
           return "Invierno";
       }
       if(nDias>=265 && nDias<355){
           return "Primavera";
       }
       return "Verano";
   }
}
