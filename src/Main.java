import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Main extends JPanel implements KeyListener {


    int x = 400;
    int y = 450;

    int velocidad = 11;

    public Main() {
        setFocusable(true);
        addKeyListener(this);
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //e
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        //j
        g.setColor(Color.decode("#f71d78"));
        g.fillRect(x, y, 50, 50);
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
    }

}