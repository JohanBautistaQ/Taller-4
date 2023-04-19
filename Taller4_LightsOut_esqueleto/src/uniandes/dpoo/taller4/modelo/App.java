package uniandes.dpoo.taller4.modelo;

import javax.swing.SwingUtilities;

public class App {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PanelJuego panelJuego = new PanelJuego();
        });
    }
}
