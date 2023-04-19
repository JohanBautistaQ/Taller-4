package uniandes.dpoo.taller4.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

public class PanelJuego extends JFrame implements ActionListener {

    private JLabel labelJugadas;
    private JLabel labelJugador;
    private JLabel labelJugadasint;
    private JButton buttonNuevo;
    private JButton buttonReiniciar;
    private JButton buttonTop10;
    private JButton buttonCambiarJugador;
    private JRadioButton radioButtonFacil;
    private JRadioButton radioButtonMedio;
    private JRadioButton radioButtonDificil;
    private Top10 TOP10 = new Top10();
    private int tamanioActual = 3;
    private int dificultad = 1;
    private Tablero tableroActivo = new Tablero(tamanioActual);
    private PanelCuadradosRedondeados panel;
    private JPanel panelOesteCentro2;

    public PanelJuego() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Panel de Juego");
        setLayout(new BorderLayout());

        // Panel Norte con JComboBox y JRadioButtons
        JPanel panelNorte = new JPanel();
        panelNorte.setBackground(Color.BLUE.darker());

        // Panel con BoxLayout para centrar componentes
        JPanel panelCentrado = new JPanel();
        panelCentrado.setBackground(Color.BLUE.darker());
        panelCentrado.setLayout(new BoxLayout(panelCentrado, BoxLayout.X_AXIS));

        // Label "Tamano"
        JLabel labelTamano = new JLabel("Tamanio:");
        labelTamano.setForeground(Color.WHITE);
        panelCentrado.add(labelTamano);

        panelOesteCentro2 = new JPanel();
        setVisible(true);
        panelOesteCentro2.setBackground(Color.BLACK);
        add(panelOesteCentro2, BorderLayout.CENTER);

        // JComboBox para seleccionar el tamano de la matriz
        String[] tamanos = { "3x3", "4x4", "5x5" };
        JComboBox<String> comboBoxTamano = new JComboBox<>(tamanos);
        comboBoxTamano.setBackground(Color.WHITE);
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSur.setBackground(Color.BLUE.darker());
        panel = new PanelCuadradosRedondeados(tamanioActual, tamanioActual);
        
        tableroActivo.desordenar(dificultad);
        labelJugadas = new JLabel("Jugadas: ");
        labelJugadas.setForeground(Color.WHITE);
        labelJugadasint = new JLabel(String.valueOf(tableroActivo.darJugadas()));
        labelJugadasint.setForeground(Color.WHITE);
        labelJugador = new JLabel("Jugador: ");
        labelJugador.setForeground(Color.WHITE);

        panelSur.add(labelJugadas);
        panelSur.add(labelJugadasint);
        panelSur.add(labelJugador);

        add(panelSur, BorderLayout.SOUTH);
        
        panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual, tableroActivo);
        panel.actualizarColores(tableroActivo.darTablero(), panel);
        

        comboBoxTamano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
                String tamanoSeleccionado = (String) comboBox.getSelectedItem();
                System.out.println("Tamanio seleccionado: " + tamanoSeleccionado);
                if (tamanoSeleccionado.equals("4x4")) {
                    tamanioActual = 4;
                    panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual,tableroActivo);
                    tableroActivo = new Tablero(tamanioActual);
                    tableroActivo.desordenar(dificultad);
                    panel.actualizarColores(tableroActivo.darTablero(), panel);
                    
                } else if (tamanoSeleccionado.equals("5x5")) {
                    tamanioActual = 5;
                    panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual,tableroActivo);
                    tableroActivo = new Tablero(tamanioActual);
                    tableroActivo.desordenar(dificultad);
                    panel.actualizarColores(tableroActivo.darTablero(), panel);
                   
                } else {
                    tamanioActual = 3;
                    panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual,tableroActivo);
                    tableroActivo = new Tablero(tamanioActual);
                    tableroActivo.desordenar(dificultad);
                    panel.actualizarColores(tableroActivo.darTablero(), panel);
                    
                }
            }
        });

        panelCentrado.add(comboBoxTamano);

        // Label "Dificultad"
        JLabel labelDificultad = new JLabel("Dificultad:");
        labelDificultad.setForeground(Color.WHITE);
        panelCentrado.add(labelDificultad);

        radioButtonFacil = new JRadioButton("Facil");
        radioButtonFacil.addActionListener(this);
        radioButtonMedio = new JRadioButton("Medio");
        radioButtonMedio.addActionListener(this);
        radioButtonDificil = new JRadioButton("Dificil");
        radioButtonDificil.addActionListener(this);

        ButtonGroup buttonGroupDificultad = new ButtonGroup();
        buttonGroupDificultad.add(radioButtonFacil);
        buttonGroupDificultad.add(radioButtonMedio);
        buttonGroupDificultad.add(radioButtonDificil);
        panelCentrado.add(radioButtonFacil);
        panelCentrado.add(radioButtonMedio);
        panelCentrado.add(radioButtonDificil);

        panelNorte.add(panelCentrado);
        add(panelNorte, BorderLayout.NORTH);

        JPanel panelEste = new JPanel();
        panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
        panelEste.setBackground(Color.BLUE.darker());

        buttonNuevo = new JButton("Nuevo");
        buttonReiniciar = new JButton("Reiniciar");
        buttonTop10 = new JButton("TOP-10");
        buttonCambiarJugador = new JButton("Cambiar Jugador");

        Dimension buttonSize = new Dimension(100, 50);
        buttonNuevo.setPreferredSize(buttonSize);
        buttonNuevo.addActionListener(this);
        buttonReiniciar.setPreferredSize(buttonSize);
        buttonReiniciar.addActionListener(this);
        buttonTop10.setPreferredSize(buttonSize);
        buttonTop10.addActionListener(this);
        buttonCambiarJugador.setPreferredSize(buttonSize);
        buttonCambiarJugador.addActionListener(this);

        buttonNuevo.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonReiniciar.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonTop10.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonCambiarJugador.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        panelEste.add(Box.createVerticalGlue());
        panelEste.add(buttonNuevo);
        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
        panelEste.add(buttonReiniciar);
        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
        panelEste.add(buttonTop10);
        panelEste.add(Box.createRigidArea(new Dimension(0, 10)));
        panelEste.add(buttonCambiarJugador);
        panelEste.add(Box.createVerticalGlue());

        add(panelEste, BorderLayout.EAST);

        // Panel Sur con labels
      
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PanelJuego panelJuego = new PanelJuego();
        });
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonTop10) {
            System.out.println("Top 10");
            TOP10.cargarRecords(obtenerArchivo("C:/Users/ASUS/OneDrive/Documentos/Uniandes/Taller4_LightsOut_esqueletoV1/Taller4_LightsOut_esqueleto/data/top10.csv"));
            JPanel top10 = disenartop10(TOP10.darRegistros());
            JOptionPane.showMessageDialog(null, top10, "Top-10", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == buttonCambiarJugador) {
            System.out.println("Cambiar");
        } else if (e.getSource() == buttonReiniciar) {
            System.out.println("Reiniciar");
            tableroActivo.reiniciar();
            panel.actualizarColores(tableroActivo.darTablero(), panel);
        } else if (e.getSource() == buttonNuevo) {
            System.out.println("nuevo");
            panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual, tableroActivo);
            tableroActivo = new Tablero(tamanioActual);
            tableroActivo.desordenar(dificultad);
            panel.actualizarColores(tableroActivo.darTablero(), panel);
            
        } else if (e.getSource() == radioButtonFacil) {
            System.out.println("Facil");
            dificultad = 5;
            panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual, tableroActivo);
            tableroActivo = new Tablero(tamanioActual);
            tableroActivo.desordenar(dificultad);
            panel.actualizarColores(tableroActivo.darTablero(), panel);
        } else if (e.getSource() == radioButtonMedio) {
            System.out.println("Medio");
            dificultad = 10;
            panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual, tableroActivo);
            tableroActivo = new Tablero(tamanioActual);
            tableroActivo.desordenar(dificultad);
            panel.actualizarColores(tableroActivo.darTablero(), panel);
        } else if (e.getSource() == radioButtonDificil) {
            System.out.println("Dificil");
            dificultad = 15;
            panel.crearNuevaMatriz(labelJugadasint, panelOesteCentro2, tamanioActual, tamanioActual, tableroActivo);
            tableroActivo = new Tablero(tamanioActual);
            tableroActivo.desordenar(dificultad);
            panel.actualizarColores(tableroActivo.darTablero(), panel);
        }
    }

    public File obtenerArchivo(String ruta) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            return archivo;
        } else {
            System.out.println("El archivo no existe: " + ruta);
            return null;
        }
    }

    public JPanel disenartop10(Collection<RegistroTop10> collection) {
        final JPanel top10 = new JPanel();
        Font font = new Font("Arial", Font.PLAIN, 14); // Fuente
        int contador = 1; // Contador para el nÃºmero de orden
        for (RegistroTop10 registro : collection) {
            // Crear un JLabel para mostrar los datos del registro
            JLabel label = new JLabel(contador + ". " + registro.darNombre() + " " + registro.darPuntos());
            label.setFont(font);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            top10.add(label);
            // Agregar el JLabel al panel
            contador++; // Incrementar el contador de orden
        }
        top10.setLayout(new BoxLayout(top10, BoxLayout.Y_AXIS));
        return top10;
    }

}
