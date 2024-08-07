/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_progr2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class PalindromoAir {
    Ticket[] asientos = new Ticket[30];

    public int firstAvailable( ) {
        return firstAvailableRecursivo(0);
    }

    private int firstAvailableRecursivo(int indice) {
        if (indice >= asientos.length) {
            return -1;
        }
        if (asientos[indice] == null) {
            return indice;
        }
        return firstAvailableRecursivo(indice + 1);
    }

    public int searchPassenger(String name) {
        return searchPassengerRecursivo(name, 0);
    }

    private int searchPassengerRecursivo(String name, int num) {
        if (num >= asientos.length) {
            return -1;
        }
        if (asientos[num] != null && asientos[num].getNombrePasajero().equals(name)) {
            return num;
        }
        return searchPassengerRecursivo(name, num + 1);
    }

    public boolean isPalindromo(String nombre) {
        return isPalindromoRecursivo(nombre, 0, nombre.length() - 1);
    }

    private boolean isPalindromoRecursivo(String nombre, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return true;
        }
        if (nombre.charAt(izquierda) != nombre.charAt(derecha)) {
            return false;
        }
        return isPalindromoRecursivo(nombre, izquierda + 1, derecha - 1);
    }

    public void printPassengers(JTextArea areaSalida) {
        String salida = "";
        for (int i = 0; i < 30; i++) {
            if (asientos[i] != null) {
                salida += "Asiento " + i + ": " + asientos[i].getNombrePasajero() + " - $" + asientos[i].getTotalPagado() + "\n";
            }
        }
        areaSalida.setText(salida);
    }

    public double income() {
        return incomeRecursivo(0);
    }

    private double incomeRecursivo(int indice) {
        if (indice >= asientos.length) {
            return 0;
        }
        if (asientos[indice] == null) {
            return incomeRecursivo(indice + 1);
        }
        return asientos[indice].getTotalPagado() + incomeRecursivo(indice + 1);
    }

    public void reset() {
        resetRecursivo(0);
    }

    private void resetRecursivo(int indice) {
        if (indice >= asientos.length) {
            return;
        }
        asientos[indice] = null;
        resetRecursivo(indice + 1);
    }

    public void sellTicket(String name, JLabel mensajeLabel) {
        int asientoDisponible = firstAvailable();
        if (asientoDisponible == -1) {
            mensajeLabel.setText("No hay asientos disponibles.");
            return;
        }
        double precioTicket = 800;
        if (isPalindromo(name)) {
            precioTicket *= 0.8;
        }
        asientos[asientoDisponible] = new Ticket(name, precioTicket);
        mensajeLabel.setText("Ticket vendido a " + name + " por $" + precioTicket);
    }

    public boolean cancelTicket(String name, JLabel mensajeLabel) {
        int indicePasajero = searchPassenger(name);
        if (indicePasajero == -1) {
            mensajeLabel.setText("Pasajero " + name + " no encontrado.");
            return false;
        }
        asientos[indicePasajero] = null;
        mensajeLabel.setText("Ticket de " + name + " cancelado.");
        return true;
    }

    public void dispatch(JLabel mensajeLabel) {
        double ingresosTotales = income();
        mensajeLabel.setText("Ingresos totales: $" + ingresosTotales + ". Todos los asientos reiniciados.");
        reset();
    }
//Visual para que salga
    public List<String> obtenerListaPasajeros() {
        List<String> listaPasajeros = new ArrayList<>();
        for (Ticket ticket : asientos) {
            if (ticket != null) {
                listaPasajeros.add(ticket.getNombrePasajero());
            }
        }
        return listaPasajeros;
    }
}
