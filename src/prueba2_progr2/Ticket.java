/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_progr2;

/**
 *
 * @author User
 */
public class Ticket {
    public String nombrePasajero;
    public double totalPagado;

    public Ticket(String nombrePasajero, double totalPagado) {
        this.nombrePasajero = nombrePasajero;
        this.totalPagado = totalPagado;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public double getTotalPagado() {
        return totalPagado;
    }
    
    public void print(){
        String print= "Nombre del pasajero: "+ nombrePasajero+ "\n Total Pagado: "+totalPagado;
        System.out.println(print);
    }
}
