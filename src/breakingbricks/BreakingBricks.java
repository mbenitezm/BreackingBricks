package breakingbricks;

import breakingbricks.SoundClip;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;

public class BreakingBricks extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    // Se declaran las variables. 
    private Animacion aniPelota; // Animacion de pelota
    private Animacion aniCamion; // Animacion de camion
    private long tiempoActual;   // Tiempo actual del juego
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip fondoM;    // Musica de fondo del juego
    private SoundClip sColision; // Sonido de colision de objetos
    private int contbloques;    // Contador de bloques destruidos
    private Pelota bola;          // Objeto bola.
    private Meth1 pill;        // Objeto Bloque usado para inicializar las listas 1 y 3
    private Meth2 pillR;      // Objeto BloqueR usado para inicializar las listas 2 y 4
    private Camion bar;         // Objeto barra, es el movido por el jugador.
    private int vidas;          // Contador de vidas
    private Image game_over;    // Imagen de victoria
    private Image victoria;       // Imagen de derrota
    private Image imaInicio;    // Imagen de inicio con instrucciones
    private int direccion;      // Variable para la dirección del personaje
    private int score;          // Variable de puntuacion
    private boolean bMover;       // Variable utilizada para saber si el personaje se esta moviendo o no
    private boolean bPausa;      // Booleano para pausar
    private boolean bMovBola;  // Booleano que indica si la bola se esta moviendo
    private boolean bInicio; // Booleando que indica si el juego y ase inicio
    private LinkedList<Meth1> lista1; // Listas de metanfetaminas
    private LinkedList<Meth2> lista2;
    private LinkedList<Meth1> lista3;
    private LinkedList<Meth2> lista4;
    private Image fondo;        // Imagen de fondo

    /**
     * Constructor vacio de la clase <code>JFrameExamen</code>.
     */
    public BreakingBricks() {
        init();
        start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos a
     * usarse en el <code>Applet</code> y se definen funcionalidades.
     */
    public void init() {
        setSize(800, 640);
        //acabarjuego = false;
        contbloques = 0;//oli
        lista1 = new LinkedList();
        lista2 = new LinkedList();
        lista3 = new LinkedList();
        lista4 = new LinkedList();
        bPausa = false;
        bMover = false;
        bMovBola = false;
        bInicio = false;
        direccion = 0;
        score = 0;                    //puntaje inicial
        vidas = 3;                    //vidaas iniciales
        fondoM = new SoundClip("Bsong.wav");
        sColision = new SoundClip ("ping.wav");
        bar = new Camion(getWidth() / 2, getHeight() - 30);
        bar.setPosX(getWidth() / 2 - bar.getAncho() / 2);
        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        bola = new Pelota(bar.getPosX() + 25, bar.getPosY() - 25);
        for (int i = 1; i < 15; i++) {
            if (i == 1) {
                pill = new Meth1(40, 70);
                lista1.add(pill);
            } else {
                Meth1 pillaux = (Meth1) lista1.get(i - 2);
                pill = new Meth1(pillaux.getPosX() + 50, pillaux.getPosY());
                lista1.add(pill);
            }

        }
        for (int i = 1; i < 15; i++) {
            if (i == 1) {
                pillR = new Meth2(40, 120);
                lista2.add(pillR);
            } else {
                Meth2 pillaux = (Meth2) lista2.get(i - 2);
                pillR = new Meth2(pillaux.getPosX() + 50, pillaux.getPosY());
                lista2.add(pillR);
            }

        }
        for (int i = 1; i < 15; i++) {
            if (i == 1) {
                pill = new Meth1(40, 170);
                lista3.add(pill);
            } else {
                Meth1 pillaux = (Meth1) lista3.get(i - 2);
                pill = new Meth1(pillaux.getPosX() + 50, pillaux.getPosY());
                lista3.add(pill);
            }

        }
        for (int i = 1; i < 15; i++) {
            if (i == 1) {
                pillR = new Meth2(40, 220);
                lista4.add(pillR);
            } else {
                Meth2 pillaux = (Meth2) lista4.get(i - 2);
                pillR = new Meth2(pillaux.getPosX() + 50, pillaux.getPosY());
                lista4.add(pillR);
            }

        }

        URL goURL = this.getClass().getResource("game over.png");
        game_over = Toolkit.getDefaultToolkit().getImage(goURL).getScaledInstance(getWidth(), getHeight(), 1);
        URL fURL = this.getClass().getResource("fondo.jpg");
        fondo = Toolkit.getDefaultToolkit().getImage(fURL).getScaledInstance(getWidth(), getHeight(), 1);
        URL aURL = this.getClass().getResource("ganaste.png");
        victoria = Toolkit.getDefaultToolkit().getImage(aURL).getScaledInstance(getWidth(), getHeight(), 1);
        URL iURL = this.getClass().getResource("inicio.png");
        imaInicio = Toolkit.getDefaultToolkit().getImage(iURL).getScaledInstance(getWidth(), getHeight(), 1); 
        
        
        Image camionsito1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv1.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv2.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv3.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv4.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv5.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv6.png")).getScaledInstance(80, 18, bar.getPosY());
        Image camionsito7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv7.png")).getScaledInstance(80, 18, bar.getPosY());

        aniCamion = new Animacion();
        aniCamion.sumaCuadro(camionsito1, 100);
        aniCamion.sumaCuadro(camionsito2, 100);
        aniCamion.sumaCuadro(camionsito3, 100);
        aniCamion.sumaCuadro(camionsito4, 100);
        aniCamion.sumaCuadro(camionsito5, 100);
        aniCamion.sumaCuadro(camionsito6, 100);
        aniCamion.sumaCuadro(camionsito7, 100);
        
        Image bola1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota1.png")).getScaledInstance(25, 25, 1);
        Image bola2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota2.png")).getScaledInstance(25, 25, 1);
        Image bola3 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota3.png")).getScaledInstance(25, 25, 1);
        Image bola4 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota4.png")).getScaledInstance(25, 25, 1);
        Image bola5 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota5.png")).getScaledInstance(25, 25, 1);
        Image bola6 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota6.png")).getScaledInstance(25, 25, 1);
        Image bola7 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota7.png")).getScaledInstance(25, 25, 1);
        Image bola8 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota8.png")).getScaledInstance(25, 25, 1);
        Image bola9 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("pelota9.png")).getScaledInstance(25, 25, 1);   
         
         
        aniPelota = new Animacion();
        aniPelota.sumaCuadro(bola1, 100);
        aniPelota.sumaCuadro(bola2, 100);
        aniPelota.sumaCuadro(bola3, 100);
        aniPelota.sumaCuadro(bola4, 100);
        aniPelota.sumaCuadro(bola5, 100);
        aniPelota.sumaCuadro(bola6, 100);
        aniPelota.sumaCuadro(bola7, 100);
        aniPelota.sumaCuadro(bola8, 100);
        aniPelota.sumaCuadro(bola9, 100);
    }

    /**
     * Metodo <I>Start</I> sobrescrito de la clase <code>Thread</code>.<P>
     * Este metodo comienza la ejecucion del hilo. Esto llama al metodo
     * <code>run</code>
     */
    public void start() {
        // Declaras un hilo
        fondoM.setLooping(true);
        fondoM.play();
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }
    

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void run() {
        tiempoActual = System.currentTimeMillis();
        
        while (true) {
            if (!bPausa) {
                actualiza();
                checaColision();
            }
            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }
    /**
     * Metodo <I>actualiza</I>.
     * <P>
     * En este metodo se actualizan las posiciones de link como de la armadura,
     * ya sea por presionar una tecla o por bMoverrlos con el mouse.
     */
    public void actualiza() {
        long tiempoTranscurrido=System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        aniCamion.actualiza(tiempoTranscurrido);
        aniPelota.actualiza(tiempoTranscurrido);
        
        
        if (bMover) {
            bar.setMoviendose(true);
            switch (direccion) {
                case 3: {
                    bar.setPosX(bar.getPosX() - 6);
                    break; //se mueve hacia la izquierda
                }
                case 4: {

                    bar.setPosX(bar.getPosX() + 6);
                    break; //se mueve hacia la derecha
                }
            }
        }
        if (bMovBola) {
            bola.setPosX(bola.getPosX() + bola.getVelX());
            bola.setPosY(bola.getPosY() + bola.getVelY());
        } else {
            bola.setPosX(bar.getPosX() + 20);
            bola.setPosY(bar.getPosY() - 30);
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto link con el objeto
     * armadura y además con las orillas del <code>Applet</code>.
     */
    public void checaColision() {
        if (bar.getPosX() + bar.getAncho() > getWidth()) {
            bar.setPosX(getWidth() - bar.getAncho());
        }
        if (bar.getPosX() < 0) {
            bar.setPosX(0);
        }
        for (Meth1 i : lista1) {
            if (bola.intersecta(i) && !i.getChoca()) {
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < bola.getPosY() + bola.getAlto() || i.getPosY() + i.getAlto() > bola.getPosY()) { //por arriba o por abajo
                    bola.setVelY(-bola.getVelY());
                } else {                                                                                           //por la izquierda o la derecha
                    bola.setVelX(-bola.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0) {
                    lista1.remove(i);
                    contbloques++;
                    score += 20;
                    break;
                }
                i.Quebrar(i.getHits());
                score += 10;
            } else {
                i.setChoca(false);
            }
        }
        for (Meth2 i : lista2) {
            if (bola.intersecta(i) && !i.getChoca()) {
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < bola.getPosY() + bola.getAlto() || i.getPosY() + i.getAlto() > bola.getPosY()) { //por arriba o por abajo
                    bola.setVelY(-bola.getVelY());
                } else {                                                                                           //por la izquierda o la derecha
                    bola.setVelX(-bola.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0) {
                    contbloques++;
                    lista2.remove(i);
                    score += 20;
                    break;
                }
                i.Quebrar(i.getHits());
                score += 10;
            } else if (!bola.intersecta(i)) {
                i.setChoca(false);
            }
        }
        for (Meth1 i : lista3) {
            if (bola.intersecta(i) && !i.getChoca()) {
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < bola.getPosY() + bola.getAlto() || i.getPosY() + i.getAlto() > bola.getPosY()) { //por arriba o por abajo
                    bola.setVelY(-bola.getVelY());

                } else if (!bola.intersecta(i)) {                                                                                           //por la izquierda o la derecha
                    bola.setVelX(-bola.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0) {
                    contbloques++;
                    lista3.remove(i);
                    score += 20;
                    break;
                }
                score += 10;
                i.Quebrar(i.getHits());
            } else {
                i.setChoca(false);
            }
        }
        for (Meth2 i : lista4) {
            if (bola.intersecta(i) && !i.getChoca()) {
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < bola.getPosY() + bola.getAlto() || i.getPosY() + i.getAlto() > bola.getPosY()) { //por arriba o por abajo
                    bola.setVelY(-bola.getVelY());

                } else {                                                                                           //por la izquierda o la derecha
                    bola.setVelX(-bola.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0) {
                    contbloques++;
                    lista4.remove(i);
                    score += 20;
                    break;
                }
                score += 10;
                i.Quebrar(i.getHits());
            } else if (!bola.intersecta(i)) {
                i.setChoca(false);  
            }
        }

        if (bola.intersecta(bar)) {
            if (bola.getPosY() + bola.getAlto() / 2 < bar.getPosY() + bar.getAlto() / 2) {
                bola.setVelY(-bola.getVelY());
                sColision.play();
            }

            if (bola.getPosX() + bola.getAncho() / 2 > bar.getPosX() + bar.getAncho() / 2 && bola.getVelX() < 0) {
                bola.setVelX(-bola.getVelX());
                sColision.play();
            } else if (bola.getPosX() + bola.getAncho() / 2 < bar.getPosX() + bar.getAncho() / 2 && bola.getVelX() > 0) {
                bola.setVelX(-bola.getVelX());
                sColision.play();
            }

        }

        if (bola.getPosX() < 5) {
            bola.setVelX(-bola.getVelX());
            sColision.play();
        } else if (bola.getPosY() < 20) {
            bola.setVelY(-bola.getVelY());
            sColision.play();
        } else if (bola.getPosX() + bola.getAncho() > getWidth()) {
            bola.setVelX(-bola.getVelX());
            sColision.play();
        } else if (bola.getPosY() > getHeight()) {
            vidas--;
            sColision.play();
            bMovBola = false;
            bola.setPosX(bar.getPosX() + 20);
            bola.setPosY(bar.getPosY() - 30);
            bola.setVelX(0);
            while (bola.getVelX() == 0) {
                bola.setVelX((int) (Math.random() * 10) - 5);
            }
            bola.setVelY(-4);
        }
    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);

    }

    /**
     * Metodo <I>keypPressed</I> sobrescrito de la clase
     * <code>KeyEvent</code>.<P>
     * En este método se actualiza la variable de dirección dependiendo de la
     * tecla que haya sido precionado El parámetro e se usará cpara obtener la
     * acción de la tecla que fue presionada.
     *
     */
    public void keyPressed(KeyEvent e) {
       
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {//Al presionar la flecha izquierda se mueve a la izquierda
            direccion = 3;
            bMover = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direccion = 4; //Al presionar la flecha derecha se mueve la barra a la derecha
            bMover = true;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {//Al presionar la P activa la Pausa del juego
            bPausa = !bPausa;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) { //Al presionar la barra espaciadora lanza la pelota.
            if (!bMovBola) {
                bMovBola = true;
            }
            bInicio = true;
        } else if (e.getKeyCode() == KeyEvent.VK_C) { //Al presionar la tecla R reinicia el juego.
            bPausa = false;
            bar.setPosX(getWidth() / 2);
            bar.setPosY(getHeight() - 30);
            bola.setPosX(bar.getPosX() + 25);
            bola.setPosY(bar.getPosY() - 25);
            contbloques = 0;
            vidas = 3;
            score=0;
            lista1.clear();
            lista2.clear();
            lista3.clear();
            lista4.clear();
            for (int i = 1; i < 15; i++) {
                if (i == 1) {
                    pill = new Meth1(40, 70);
                    lista1.add(pill);
                } else {
                    Meth1 pillaux = (Meth1) lista1.get(i - 2);
                    pill = new Meth1(pillaux.getPosX() + 50, pillaux.getPosY());
                    lista1.add(pill);
                }

            }
            for (int i = 1; i < 15; i++) {
                if (i == 1) {
                    pillR = new Meth2(40, 120);
                    lista2.add(pillR);
                } else {
                    Meth2 pillaux = (Meth2) lista2.get(i - 2);
                    pillR = new Meth2(pillaux.getPosX() + 50, pillaux.getPosY());
                    lista2.add(pillR);
                }

            }
            for (int i = 1; i < 15; i++) {
                if (i == 1) {
                    pill = new Meth1(40, 170);
                    lista3.add(pill);
                } else {
                    Meth1 pillaux = (Meth1) lista3.get(i - 2);
                    pill = new Meth1(pillaux.getPosX() + 50, pillaux.getPosY());
                    lista3.add(pill);
                }

            }
            for (int i = 1; i < 15; i++) {
                if (i == 1) {
                    pillR = new Meth2(40, 220);
                    lista4.add(pillR);
                } else {
                    Meth2 pillaux = (Meth2) lista4.get(i - 2);
                    pillR = new Meth2(pillaux.getPosX() + 50, pillaux.getPosY());
                    lista4.add(pillR);
                }

            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la clase
     * <code>KeyEvent</code>.<P>
     * En este método se verifica si alguna tecla que haya sido presionada es
     * liberada. Si es liberada la booleana que controla el movimiento se
     * convierte en falsa.
     */
    public void keyReleased(KeyEvent e) {
        bMover = false;
        bar.setMoviendose(false);
    }

    /**
     * Metodo <I>mouseClicked</I> sobrescrito de la clase
     * <code>MouseEvent</code>.
     * <P>
     * Este metodo es invocado cuando se ha presionado un boton del mouse en un
     * componente.
     *
     * @param e es el evento generado al ocurrir lo descrito.
     */
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /**
     * Metodo <I>MousePressed</I> sobrescrito de la clase
     * <code>mouseEvent</code>.<P>
     * En este metodo se verifica si el mouse ha dado click sobre la imágen. Al
     * verificar que haya dado un click se actualizan las coordenadas de 'x' y
     * 'y' para ajustar el desfase que puede tener la imagen con el click
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Metodo <I>MouseReleased</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * En este método se verifica si el click del mouse ha sido liberado, si sí
     * entonces la booleana que l ocontrola se hace falsa, para marcar que ya no
     * está siendo presionadao.
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Metodo <I>mouseReleased</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * Este metodo es invocado cuando el cursor es movido dentro de un
     * componente sin presionar ningun boton.
     *
     * @param e es el evento generado al ocurrir lo descrito.
     */
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Metodo <I>mouseDragged</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * Este metodo es invocado cuando se presiona un boton en un componente, y
     * luego este es arrastrado.
     *
     * @param e es el evento generado al ocurrir lo descrito.
     */
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Metodo <I>paint1</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @paramg es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {       
         if (!bInicio){
            g.drawImage(imaInicio,0,0,this);
         }  
         else{
            if (score >= 1960 && vidas > 0) { //Cuando ganas se despliega la pantalla de creditos
                //acabarjuego = true;
                g.drawImage(victoria,0,0,this);  
                    g.setColor(Color.black);
                    g.drawString("Puntos = " + score, 20, 150);
                    g.drawString("Vidas = " + vidas, 20, 170);
            }
            else if (vidas > 0) {
                g.drawImage(fondo, 0, 0, this);
                if (lista1 != null && bar != null) {
                    //Se Pintan todas las pildoras del juego
                    for (Meth1 i : lista1) {
                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY(), this);
                    }
                    for (Meth2 i : lista2) {

                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY(), this);
                    }
                    for (Meth1 i : lista3) {

                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY(), this);
                    }
                    for (Meth2 i : lista4) {

                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY(), this);
                    }

                    g.drawImage(aniPelota.getImagen(), bola.getPosX(), bola.getPosY(), this);//Pinta la bola
                    g.drawImage(aniCamion.getImagen(), bar.getPosX(), bar.getPosY(), this);  //Pinta la Barra

                    g.setColor(Color.white);//Despliega los puntos, las vidas y el comando de Instrucciones
                    g.drawString("Puntos = " + score, 20, 50);
                    g.drawString("Vidas = " + vidas, 20, 70);

                } else {
                    //Da un mensaje mientras se carga el dibujo	
                    g.drawString("No se cargo la imagen..", 20, 20);
                }

            } 
            else if (vidas == 0) {
               g.drawImage(game_over, 0, 0, this); //Cuando pierdes se despliega la pantalla de perder
            }
        
        }
    }
}
