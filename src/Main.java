import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class Main extends JPanel implements KeyListener {

    //cuadro
    int x = 400;
    int y = 450;

    int velocidad = 11;

    //cir
    int circuloX;
    int circuloY =0;

    int velocidadCaida = 18;

    Random random6 = new Random();

    public Main() {
        setFocusable(true);
        addKeyListener(this);
        //aleaorio
        circuloX = random6.nextInt(600);
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //e

        g.setColor(Color.decode("#000000"));
        g.fillRect(0, 0, getWidth(), getHeight());

        // piso
        g.setColor(Color.GRAY);
        g.fillRect(0, 500, getWidth(), 100);

        //j
        g.setColor(Color.decode("#f71d78"));
        g.fillRect(x, y, 50, 50);

        //c
        g.setColor(Color.WHITE);
        g.fillOval(circuloX, circuloY, 30, 30);
    }

    //m6ov5im6iento

    public void actualizar() {
        circuloY += velocidadCaida;

        if(circuloY > 500) {
            circuloY = 0;
            circuloX = random6.nextInt(600);
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D) {
            x += velocidad;
        } 

        if (e.getKeyCode() == KeyEvent.VK_A) {
            x -= velocidad;
        }

        repaint();
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }




    public static void main(String[] args) {
        JFrame ventana = new JFrame("Vendehumo");

        Main juego = new Main();

        ventana.add(juego);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        juego.requestFocusInWindow();



        Timer timer = new Timer(100, e -> {
            juego.actualizar();
           
        });

        timer.start();
    }

}