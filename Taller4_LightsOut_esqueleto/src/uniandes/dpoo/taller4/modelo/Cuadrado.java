package uniandes.dpoo.taller4.modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

public class Cuadrado extends JButton {
    private boolean CONDICIONAL; // Agregar una variable para mantener el estado de color

    public Cuadrado() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(true);
        CONDICIONAL = false; // Inicializar el estado de color como falso
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (CONDICIONAL) { // Cambiar el color si el estado es CONDICIONAL
            g2d.setColor(Color.BLACK);
        } else {
            g2d.setColor(Color.YELLOW);
        }
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2d.dispose();
    }

    public void cambiarColor() {
        CONDICIONAL = !CONDICIONAL; // Cambiar el estado de color a su opuesto
        repaint(); // Solicitar una actualización de la GUI
    }
}
