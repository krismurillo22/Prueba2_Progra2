/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2_progr2;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(new PalindromoAir());
            ventana.setVisible(true);
        });
    }
}

class VentanaPrincipal extends JFrame {
    private PalindromoAir aerolinea;
    private JTextField campoNombre;
    private JTextField campoBuscar;
    private JList<String> listaPasajeros;
    private DefaultListModel<String> modeloLista;
    private JLabel mensajeLabel;

    public VentanaPrincipal(PalindromoAir aerolinea) {
        this.aerolinea = aerolinea;
        setTitle("Sistema de Venta de Tickets de PalindromoAir");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelEntradas = new JPanel();
        panelEntradas.setLayout(new GridLayout(2, 2, 10, 10));
        panelEntradas.setBorder(BorderFactory.createTitledBorder("Datos del Pasajero"));

        JLabel etiquetaNombre = new JLabel("Nombre del Pasajero:");
        campoNombre = new JTextField(20);

        JLabel etiquetaBuscar = new JLabel("Buscar Pasajero:");
        campoBuscar = new JTextField(20);

        panelEntradas.add(etiquetaNombre);
        panelEntradas.add(campoNombre);
        panelEntradas.add(etiquetaBuscar);
        panelEntradas.add(campoBuscar);

        panelPrincipal.add(panelEntradas, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1, 10, 10)); // Una columna con 6 filas
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        JButton botonVender = crearBoton("Vender Ticket");
        botonVender.addActionListener(e -> sellTicket());

        JButton botonImprimir = crearBoton("Imprimir Pasajeros");
        botonImprimir.addActionListener(e -> imprimirPasajeros());

        JButton botonDespachar = crearBoton("Despachar");
        botonDespachar.addActionListener(e -> dispatch());

        JButton botonCancelar = crearBoton("Cancelar Ticket");
        botonCancelar.addActionListener(e -> cancelTicket());

        JButton botonBuscar = crearBoton("Buscar");
        botonBuscar.addActionListener(e -> searchPassenger());

        panelBotones.add(botonVender);
        panelBotones.add(botonImprimir);
        panelBotones.add(botonDespachar);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonBuscar);

        panelPrincipal.add(panelBotones, BorderLayout.EAST); // Colocar los botones a la derecha

        modeloLista = new DefaultListModel<>();
        listaPasajeros = new JList<>(modeloLista);
        listaPasajeros.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(listaPasajeros);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Pasajeros"));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        mensajeLabel = new JLabel("Bienvenido al sistema de venta de tickets de PalindromoAir");
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(mensajeLabel, BorderLayout.PAGE_END);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(0, 102, 204)); // Azul
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        return boton;
    }

    private void sellTicket() {
        String nombrePasajero = campoNombre.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        aerolinea.sellTicket(nombrePasajero, mensajeLabel);
        campoNombre.setText("");
        actualizarListaPasajeros();
    }

    private void imprimirPasajeros() {
        actualizarListaPasajeros();
        mensajeLabel.setText("Lista de pasajeros actualizada.");
    }

    private void dispatch() {
        aerolinea.dispatch(mensajeLabel);
        modeloLista.clear();
    }

    private void cancelTicket() {
        String nombrePasajero = campoNombre.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        aerolinea.cancelTicket(nombrePasajero, mensajeLabel);
        campoNombre.setText("");
        actualizarListaPasajeros();
    }

    private void searchPassenger() {
        String nombrePasajero = campoBuscar.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        int posicion = aerolinea.searchPassenger(nombrePasajero);
        if (posicion != -1) {
            mensajeLabel.setText("Pasajero " + nombrePasajero + " encontrado en el asiento " + posicion + ".");
        } else {
            mensajeLabel.setText("Pasajero " + nombrePasajero + " no encontrado.");
        }
        campoBuscar.setText("");
    }

    private void actualizarListaPasajeros() {
        modeloLista.clear();
        for (String pasajero : aerolinea.obtenerListaPasajeros()) {
            modeloLista.addElement(pasajero);
        }
    }
}
