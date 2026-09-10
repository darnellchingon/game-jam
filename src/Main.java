import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class Main extends JPanel implements KeyListener {


    int puntaje = 0;
    int alturaBarra = 50;
    int vidas = 3;
    Image pasura;
    Image fondoUabcs;


    //cuadro
    int x = 400;
    int y = 450;

    int velocidad = 15;

    //cir
    int circuloX;
    int circuloY = alturaBarra;

    int velocidadCaida = 18;

    Random random6 = new Random();

    public Main() {
        setFocusable(true);
        addKeyListener(this);
        //aleaorio
        circuloX = random6.nextInt(600);

        pasura = new ImageIcon("src/imagenes/pasura.png").getImage();
        fondoUabcs = new ImageIcon("src/imagenes/uabcs.png").getImage();
    }

    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //e

        g.drawImage(fondoUabcs, 0, 0, getWidth(), getHeight(), this);

        //barra
        g.setColor(Color.decode("#f8f8f8"));
        g.fillRect(0, 0, getWidth(), alturaBarra);

        //ppuntaje
        g.setColor(Color.decode("#000000"));
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Puntaje: " + puntaje, 650, 32);

        //v5idas
    
        g.setColor(Color.decode("#000000"));
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Vidas: " + vidas, 10, 32);


        // piso
        g.setColor(Color.GRAY);
        g.fillRect(0, 500, getWidth(), 100);

        //j
        g.setColor(Color.decode("#f71d78"));
        g.fillRect(x, y, 50, 50);

        //c
        g.setColor(Color.WHITE);
        g.fillOval(circuloX, circuloY, 30, 30);

        // imagen encima del círculo
        g.drawImage(pasura, circuloX, circuloY, 30, 30, this);
    }

    //m6ov5im6iento

    public boolean actualizar() {
        circuloY += velocidadCaida;

        Rectangle jugador = new Rectangle(x, y, 50, 50);
        Rectangle circulo = new Rectangle(circuloX, circuloY, 30, 30);

        if(jugador.intersects(circulo)) {

            puntaje++;

            circuloY = 0;
            circuloX = random6.nextInt(600);
        }
        

        if(circuloY > 500) {
            circuloY = 0;
            circuloX = random6.nextInt(600);
            vidas--;

            if (vidas <= 0) {
                repaint();
                return true;
            }
        }

        repaint();
        return false;
    }

    public void reiniciarJuego() {
        puntaje = 0;
        vidas = 3;
        circuloY = alturaBarra;
        circuloX = random6.nextInt(600);
        x = 400;
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

    private static JPanel crearPresentacion(String texto) {
        return crearPresentacion(texto, null);
    }

    private static JPanel crearPresentacion(String texto, String rutaImagen) {
        Image imagenFondo = rutaImagen == null
                ? null
                : new ImageIcon(rutaImagen).getImage();

        JPanel presentacion = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        presentacion.setBackground(Color.BLACK);

        JLabel mensaje = new JLabel(texto);
        mensaje.setForeground(Color.WHITE);
        mensaje.setFont(new Font("Arial", Font.BOLD, 32));
        presentacion.add(mensaje);

        return presentacion;
    }




    public static void main(String[] args) {
        JFrame ventana = new JFrame("Vendehumo");

        Main juego = new Main();

        CardLayout tarjetas = new CardLayout();
        JPanel contenedor = new JPanel(tarjetas);

        JPanel colaboracion = crearPresentacion("En colaboración con UABCS");
        JPanel produccion = crearPresentacion("Vendehumos production");
        JPanel involucrado = crearPresentacion("DASC involved");
        JPanel presentamos = crearPresentacion("presentamos...");

        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(Color.BLACK);

        JPanel contenidoMenu = new JPanel();
        contenidoMenu.setBackground(Color.BLACK);
        contenidoMenu.setLayout(new BoxLayout(contenidoMenu, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("REcolecta basura uabcs simulator");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 42));

        JButton iniciar = new JButton("Iniciar juego");
        iniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        iniciar.setFont(new Font("Arial", Font.BOLD, 20));
        iniciar.addActionListener(e -> {
            tarjetas.show(contenedor, "juego");
            juego.requestFocusInWindow();
        });

        JButton salir = new JButton("Salir");
        salir.setAlignmentX(Component.CENTER_ALIGNMENT);
        salir.setFont(new Font("Arial", Font.BOLD, 20));
        salir.addActionListener(e -> System.exit(0));

        contenidoMenu.add(titulo);
        contenidoMenu.add(Box.createVerticalStrut(35));
        contenidoMenu.add(iniciar);
        contenidoMenu.add(Box.createVerticalStrut(15));
        contenidoMenu.add(salir);
        menu.add(contenidoMenu);

        contenedor.add(colaboracion, "colaboracion");
        contenedor.add(produccion, "produccion");
        contenedor.add(involucrado, "involucrado");
        contenedor.add(presentamos, "presentamos");
        contenedor.add(menu, "menu");
        contenedor.add(juego, "juego");

        ventana.add(contenedor);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        tarjetas.show(contenedor, "colaboracion");

        Timer segundaPresentacion = new Timer(2000, e -> {
            tarjetas.show(contenedor, "produccion");
            ((Timer) e.getSource()).stop();

            Timer terceraPresentacion = new Timer(2000, evento -> {
                tarjetas.show(contenedor, "involucrado");
                ((Timer) evento.getSource()).stop();

                Timer cuartaPresentacion = new Timer(2000, siguienteEvento -> {
                    tarjetas.show(contenedor, "presentamos");
                    ((Timer) siguienteEvento.getSource()).stop();

                    Timer mostrarMenu = new Timer(2000, ultimoEvento -> {
                        tarjetas.show(contenedor, "menu");
                        ((Timer) ultimoEvento.getSource()).stop();
                    });
                    mostrarMenu.setRepeats(false);
                    mostrarMenu.start();
                });
                cuartaPresentacion.setRepeats(false);
                cuartaPresentacion.start();
            });
            terceraPresentacion.setRepeats(false);
            terceraPresentacion.start();
        });
        segundaPresentacion.setRepeats(false);
        segundaPresentacion.start();

        Timer timer = new Timer(100, e -> {
            if (juego.isVisible()) {
                if (juego.actualizar()) {
                    Timer timerJuego = (Timer) e.getSource();
                    timerJuego.stop();

                    Object[] opciones = {"Jugar de nuevo", "Salir"};
                    int opcion = JOptionPane.showOptionDialog(
                            ventana,
                            "Game Over\nPuntaje final: " + juego.puntaje,
                            "Game Over",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]);

                    if (opcion == 0) {
                        juego.reiniciarJuego();
                        tarjetas.show(contenedor, "juego");
                        juego.requestFocusInWindow();
                        timerJuego.start();
                    } else {
                        System.exit(0);
                    }
                }
            }
           
        });

        timer.start();
    }

}