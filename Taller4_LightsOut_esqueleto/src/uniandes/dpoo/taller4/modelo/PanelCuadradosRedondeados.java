package uniandes.dpoo.taller4.modelo;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCuadradosRedondeados extends JPanel {
    private CuadradoRedondeado[][] matriz; // Matriz de CuadradosRedondeados
    private Tablero tableroActual;
    private boolean[][] col;

    public PanelCuadradosRedondeados(int filas, int columnas) {
        // Configurar el panel con un GridLayout para organizar los CuadradosRedondeados
        setLayout(new GridLayout(filas, columnas));
        // Inicializar la matriz con el tamaÃÂ±o especificado
        matriz = new CuadradoRedondeado[filas][columnas];
        // Crear y agregar los CuadradosRedondeados al panel
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new CuadradoRedondeado();
                add(matriz[i][j]);
            }
        }
    }

    public void crearNuevaMatriz(JLabel jugLabel, JPanel panel, int filas, int columnas, Tablero tablero) {
        // Eliminar todos los componentes del panel
        tableroActual = tablero;
        col = tableroActual.darTablero();

        panel.removeAll();

        // Crear nueva matriz de CuadradosRedondeados
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                CuadradoRedondeado cuadrado = new CuadradoRedondeado();
                matriz = new CuadradoRedondeado[filas][columnas];
                matriz[i][j] = cuadrado;
                cuadrado.setName("" + i + "-" + j);
                FunCuadrados(cuadrado, panel);
                panel.add(cuadrado);
            }
        }

        // Establecer nuevo layout para el panel
        panel.setLayout(new GridLayout(filas, columnas));

        // Actualizar la interfaz de usuario
        panel.revalidate();
        panel.repaint();
    }

    public void FunCuadrados(CuadradoRedondeado cuadrado, JPanel panel) {
        cuadrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CuadradoRedondeado casilla = (CuadradoRedondeado) e.getSource();
                String Location = casilla.getName();
                System.out.println(Location);
                String[] partes = Location.split("-");

                // Obtener los valores de i y j como enteros
                int fila2 = Integer.parseInt(partes[1]);
                int colunma2 = Integer.parseInt(partes[0]);
                tableroActual.jugar(fila2, colunma2);
                System.out.println("" + tableroActual.darJugadas());
                // casilla.cambiarColor();
                actualizarColores(tableroActual.darTablero(), panel);
                casilla.cambiarColor();

            }
        });
    }

    public void actualizarColores(boolean[][] tablero, JPanel panel) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {

                if (i >= 0 && i < matriz.length && j >= 0 && j < matriz[0].length) {
                    if (col[i][j] && matriz[i][j] != null) {
                        matriz[i][j].cambiarColor();
                    } else {
                        System.out.println("prueba actual");
                    }
                }

                // Solicitar una actualización de la GUI
            }
        }
        panel.revalidate();
        panel.repaint();
    }

}
