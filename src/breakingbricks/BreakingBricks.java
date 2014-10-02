// Marcel Benítez 1139855
//Daniela Valdés Guerra 813724

package breakingbricks;

import breakingbricks.SoundClip;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;

public class BreakingBricks extends JFrame implements Runnable, KeyListener{

    // Se declaran las variables. 
    private Animacion aniPelota; // Animacion de pelota
    private Animacion aniCamion; // Animacion de camion
    private long tiempoActual;   // Tiempo actual del juego
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip sFondoM;    // Musica de fondo del juego
    private SoundClip sColision; // Sonido de colision de objetos
    private Pelota pelPelota;          // Objeto bola.
    private Meth1 metCrist1;        // Objeto Meth1
    private Meth2 metCrist2;      // Objeto Meth2
    private Camion camCamion;         // Objeto tipo Camion
    private int iVidas;          // Contador de vidas
    private Image imaGameOver;    // Imagen de derrota
    private Image imaVictoria;       // Imagen de victoria
    private Image imaInicio;    // Imagen de inicio con instrucciones
    private int iDireccion;      // Variable para la dirección del personaje
    private int iScore;          // Variable de puntuacion
    private boolean bMover;       // Variable para saber si se mueve
    private boolean bPausa;      // Booleano para pausar
    private boolean bMovBola;  // Booleano que indica si la bola se esta moviend
    private boolean bInicio; // Booleando que indica si el juego y ase inicio
    private LinkedList<Meth1> lnkCol1; // Listas de metanfetaminas
    private LinkedList<Meth2> lnkCol2;
    private LinkedList<Meth1> lnkCol3;
    private LinkedList<Meth2> lnkCol4;
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
        // Se crean las colleciones que tendran los cristales por fila
        lnkCol1 = new LinkedList();
        lnkCol2 = new LinkedList();
        lnkCol3 = new LinkedList();
        lnkCol4 = new LinkedList();
        // El juego empieza sin estar en pausa
        bPausa = false;
        // No se mueve la abarra
        bMover = false;
        // No se mueve la bola
        bMovBola = false;
        // Arranca con la pantalla de inicio
        bInicio = false;
        iDireccion = 0;
        //puntaje inicial
        iScore = 0;  
        //vidaas iniciales
        iVidas = 3;          
        // Se declara soundclip del fondo
        sFondoM = new SoundClip("Bsong.wav");
        // Se declara soundclip de las colisiones
        sColision = new SoundClip ("ping.wav");
        // Se crea el objeto camion
        camCamion = new Camion(getWidth() / 2, getHeight() - 30);
        camCamion.setPosX(getWidth() / 2 - camCamion.getAncho() / 2);
        addKeyListener(this);
        // Se crea objeto de Pelota
        pelPelota = new Pelota(camCamion.getPosX() + 25, camCamion.getPosY() 
                - 25);
        // Se crea la primera fila de cristales
        for (int i = 1; i < 16; i++){
            if (i == 1) {
                metCrist1 = new Meth1(40, 70);
                lnkCol1.add(metCrist1);
            } 
            else{
                Meth1 metCrist1aux = (Meth1) lnkCol1.get(i - 2);
                metCrist1 = new Meth1(metCrist1aux.getPosX() + 50
                        , metCrist1aux.getPosY());
                lnkCol1.add(metCrist1);
            }
        }
        // Se crea la segunda fila de cristales
        for (int i = 1; i < 16; i++){
            if (i == 1){
                metCrist2 = new Meth2(40, 120);
                lnkCol2.add(metCrist2);
            } 
            else{
                Meth2 metCrist1aux = (Meth2) lnkCol2.get(i - 2);
                metCrist2 = new Meth2(metCrist1aux.getPosX() + 50
                        , metCrist1aux.getPosY());
                lnkCol2.add(metCrist2);
            }

        }
        // Se crea la tercera fila de cristales
        for (int i = 1; i < 16; i++){
            if (i == 1){
                metCrist1 = new Meth1(40, 170);
                lnkCol3.add(metCrist1);
            } 
            else{
                Meth1 metCrist1aux = (Meth1) lnkCol3.get(i - 2);
                metCrist1 = new Meth1(metCrist1aux.getPosX() + 50, 
                        metCrist1aux.getPosY());
                lnkCol3.add(metCrist1);
            }
        }
        // Se crea la ultima fila de cristales
        for (int i = 1; i < 16; i++){
            if (i == 1){
                metCrist2 = new Meth2(40, 220);
                lnkCol4.add(metCrist2);
            } 
            else{
                Meth2 metCrist1aux = (Meth2) lnkCol4.get(i - 2);
                metCrist2 = new Meth2(metCrist1aux.getPosX() + 50
                        , metCrist1aux.getPosY());
                lnkCol4.add(metCrist2);
            }
        }
        // Se crean las imagenes que se utilizaran 
        URL goURL = this.getClass().getResource("game over.png");
        imaGameOver = Toolkit.getDefaultToolkit().getImage(goURL)
                .getScaledInstance(getWidth(), getHeight(), 1);
        URL fURL = this.getClass().getResource("fondo.jpg");
        fondo = Toolkit.getDefaultToolkit().getImage(fURL)
                .getScaledInstance(getWidth(), getHeight(), 1);
        URL aURL = this.getClass().getResource("ganaste.png");
        imaVictoria = Toolkit.getDefaultToolkit().getImage(aURL)
                .getScaledInstance(getWidth(), getHeight(), 1);
        URL iURL = this.getClass().getResource("inicio.png");
        imaInicio = Toolkit.getDefaultToolkit().getImage(iURL)
                .getScaledInstance(getWidth(), getHeight(), 1); 
        // Se crean imagenes para animacion del camion     
        Image camionsito1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv1.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv2.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito3 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv3.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito4 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv4.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito5 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv5.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito6 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv6.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        Image camionsito7 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("rv7.png"))
                .getScaledInstance(80, 18, camCamion.getPosY());
        // Se crea la animacion del camion
        aniCamion = new Animacion();
        aniCamion.sumaCuadro(camionsito1, 100);
        aniCamion.sumaCuadro(camionsito2, 100);
        aniCamion.sumaCuadro(camionsito3, 100);
        aniCamion.sumaCuadro(camionsito4, 100);
        aniCamion.sumaCuadro(camionsito5, 100);
        aniCamion.sumaCuadro(camionsito6, 100);
        aniCamion.sumaCuadro(camionsito7, 100);
        // Se crean las imagenes para la animacion de la bola 
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
        // Se crea la animacion de la bola  
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
        // Loop de la musica de fondo
        sFondoM.setLooping(true);
        sFondoM.play();
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
    public void run(){
        // Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();
        
        while (true){
            // Si el juego no esta pausado se actualiza y se checa colision
            if (!bPausa){
                actualiza();
                checaColision();
            }
            // Se actualiza el <code>Applet</code> repintando el contenido.
            repaint();    
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex){
                System.out.println("Error en " + ex.toString());
            }
        }
    }
    /**
     * Metodo <I>actualiza</I>.
     * <P>
     * Actualiza las posiciones de las animaciones y bojetos, asi como 
     * el tiempo actual del sistema
     */
    public void actualiza(){
        // Tiempo transcurrido desde que inicio el juego
        long tiempoTranscurrido=System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación de camion y pel en base al tiempo transcurrido
        aniCamion.actualiza(tiempoTranscurrido);
        aniPelota.actualiza(tiempoTranscurrido);
        
        // Si se presiono a las teclas de derecha o izquierda se le da velocidad
        // a la barra
        if (bMover){
            camCamion.setMoviendose(true);
            switch (iDireccion){
                case 3:{
                    //se mueve hacia la izquierda
                    camCamion.setPosX(camCamion.getPosX() - 5);
                    break;
                }
                case 4:{
                    //se mueve hacia la derecha
                    camCamion.setPosX(camCamion.getPosX() + 5);
                    break; 
                }
            }
        }
        // Si la vola se esta moviendo se le asigna velocidad
        if (bMovBola){
            pelPelota.setPosX(pelPelota.getPosX() + pelPelota.getVelX());
            pelPelota.setPosY(pelPelota.getPosY() + pelPelota.getVelY());
        } 
        // Si no se esta moviendo se queda pegada a la camCamionra
        else{
            pelPelota.setPosX(camCamion.getPosX() + 20);
            pelPelota.setPosY(camCamion.getPosY() - 30);
        }
    }

    /**
     * Metodo usado para checar las colisiones del objeto link con el objeto
     * armadura y además con las orillas del <code>Applet</code>.
     */
    public void checaColision(){
        // La barra no choca si colisiona con pared
        if (camCamion.getPosX() + camCamion.getAncho() > getWidth()){
            camCamion.setPosX(getWidth() - camCamion.getAncho());
        }
        // La camCamionra no choca si colisiona con pared
        if (camCamion.getPosX() < 0){
            camCamion.setPosX(0);
        }
        // Para cada cristal dependiendo desde donde pega la pelPelota es hacia
        // donde esta se dirigira ahora si no ha colisionado antes, se restan
        // golpes, se escucha el sonido, y desaparecen si ya no tienen mas 
        // golpes disponibles
        for (Meth1 i : lnkCol1){
            if (pelPelota.intersecta(i) && !i.getChoca()){
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < pelPelota.getPosY() + pelPelota.getAlto() || 
                        i.getPosY() + i.getAlto() > pelPelota.getPosY()){ 
                    pelPelota.setVelY(-pelPelota.getVelY());
                } 
                else{                          
                    pelPelota.setVelX(-pelPelota.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0){
                    lnkCol1.remove(i);
                    iScore += 20;
                    break;
                }
                i.Quebrar(i.getHits());
                iScore += 10;
            } 
            else{
                i.setChoca(false);
            }
        }
        // Para cada cristal dependiendo desde donde pega la pelPelota es hacia
        // donde esta se dirigira ahora si no ha colisionado antes, se restan
        // golpes, se escucha el sonido, y desaparecen si ya no tienen mas 
        // golpes disponibles
        for (Meth2 i : lnkCol2) {
            if (pelPelota.intersecta(i) && !i.getChoca()){
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < pelPelota.getPosY() + pelPelota.getAlto() || 
                        i.getPosY() + i.getAlto() > pelPelota.getPosY()){ 
                    pelPelota.setVelY(-pelPelota.getVelY());
                } 
                else{                        
                    pelPelota.setVelX(-pelPelota.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0){
                    lnkCol2.remove(i);
                    iScore += 20;
                    break;
                }
                i.Quebrar(i.getHits());
                iScore += 10;
            } 
            else if (!pelPelota.intersecta(i)){
                i.setChoca(false);
            }
        }
        // Para cada cristal dependiendo desde donde pega la pelPelota es hacia
        // donde esta se dirigira ahora si no ha colisionado antes, se restan
        // golpes, se escucha el sonido, y desaparecen si ya no tienen mas 
        // golpes disponibles
        for (Meth1 i : lnkCol3){
            if (pelPelota.intersecta(i) && !i.getChoca()){
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < pelPelota.getPosY() + pelPelota.getAlto() || 
                        i.getPosY() + i.getAlto() > pelPelota.getPosY()){
                    pelPelota.setVelY(-pelPelota.getVelY());
                } 
                else if (!pelPelota.intersecta(i)){                                                                                           //por la izquierda o la derecha
                    pelPelota.setVelX(-pelPelota.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0){
                    lnkCol3.remove(i);
                    iScore += 20;
                    break;
                }
                iScore += 10;
                i.Quebrar(i.getHits());
            } 
            else{
                i.setChoca(false);
            }
        }
        // Para cada cristal dependiendo desde donde pega la pelPelota es hacia
        // donde esta se dirigira ahora si no ha colisionado antes, se restan
        // golpes, se escucha el sonido, y desaparecen si ya no tienen mas 
        // golpes disponibles
        for (Meth2 i : lnkCol4){
            if (pelPelota.intersecta(i) && !i.getChoca()){
                sColision.play();
                i.setChoca(true);
                if (i.getPosY() < pelPelota.getPosY() + pelPelota.getAlto() || 
                        i.getPosY() + i.getAlto() > pelPelota.getPosY()) { 
                    pelPelota.setVelY(-pelPelota.getVelY());
                } 
                else{                                                
                    pelPelota.setVelX(-pelPelota.getVelX());
                }
                i.RestaGolpe();
                if (i.getHits() == 0){
                    lnkCol4.remove(i);
                    iScore += 20;
                    break;
                }
                iScore += 10;
                i.Quebrar(i.getHits());
            } 
            else if (!pelPelota.intersecta(i)){
                i.setChoca(false);  
            }
        }
        // Si la bola intersecta con la barra se cambia su direccion en Y
        // Y su velocidad en x dependiendo si llega la bola por derecha o izq
        if (pelPelota.intersecta(camCamion)){
            if (pelPelota.getPosY() + pelPelota.getAlto() / 2 < 
                    camCamion.getPosY() 
                    + camCamion.getAlto() / 2) {
                pelPelota.setVelY(-pelPelota.getVelY());
                sColision.play();
            }
            if (pelPelota.getPosX() + pelPelota.getAncho() / 2 > 
                    camCamion.getPosX() 
                    + camCamion.getAncho() / 2 && pelPelota.getVelX() < 0){
                pelPelota.setVelX(-pelPelota.getVelX());
                sColision.play();
            } 
            else if (pelPelota.getPosX() + pelPelota.getAncho() / 2
                    < camCamion.getPosX() 
                    + camCamion.getAncho() / 2 && pelPelota.getVelX() > 0){
                pelPelota.setVelX(-pelPelota.getVelX());
                sColision.play();
            }
        }
        // Colisiones con de la pelPelota con las paredes
        if (pelPelota.getPosX() < 5){
            pelPelota.setVelX(-pelPelota.getVelX());
            sColision.play();
        } 
        else if (pelPelota.getPosY() < 20) {
            pelPelota.setVelY(-pelPelota.getVelY());
            sColision.play();
        } 
        else if (pelPelota.getPosX() + pelPelota.getAncho() > getWidth()){
            pelPelota.setVelX(-pelPelota.getVelX());
            sColision.play();
        } 
        // Colision con la parte de abajo del frame que reduce vidas y reinicia
        // su estado
        else if (pelPelota.getPosY() > getHeight()){
            iVidas--;
            sColision.play();
            bMovBola = false;
            pelPelota.setPosX(camCamion.getPosX() + 20);
            pelPelota.setPosY(camCamion.getPosY() - 30);
            pelPelota.setVelX(0);
            while (pelPelota.getVelX() == 0){
                pelPelota.setVelX((int) (Math.random() * 10) - 5);
            }
            pelPelota.setVelY(-4);
        }
    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g){
        // Inicializan el DoubleBuffer
        if (dbImage == null){
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
        //Al presionar la flecha izquierda se mueve a la izquierda
            iDireccion = 3;
            bMover = true;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            iDireccion = 4; 
            //Al presionar la flecha derecha se mueve la barra a la derecha
            bMover = true;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_P){
            //Al presionar la P activa la Pausa del juego
            bPausa = !bPausa;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){ 
            //Al presionar la barra espaciadora lanza la pelota.
            if (!bMovBola) {
                bMovBola = true;
            }
            bInicio = true;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_C){ 
            //Al presionar la tecla R reinicia el juego. Se limpian todas
            // las variables y se vuelven a declarar
            bPausa = false;
            camCamion.setPosX(getWidth() / 2);
            camCamion.setPosY(getHeight() - 30);
            pelPelota.setPosX(camCamion.getPosX() + 25);
            pelPelota.setPosY(camCamion.getPosY() - 25);
            iVidas = 3;
            iScore=0;
            lnkCol1.clear();
            lnkCol2.clear();
            lnkCol3.clear();
            lnkCol4.clear();
            for (int i = 1; i < 16; i++){
                if (i == 1){
                    metCrist1 = new Meth1(40, 70);
                    lnkCol1.add(metCrist1);
                } 
                else{
                    Meth1 metCrist1aux = (Meth1) lnkCol1.get(i - 2);
                    metCrist1 = new Meth1(metCrist1aux.getPosX() + 50
                            , metCrist1aux.getPosY());
                    lnkCol1.add(metCrist1);
                }
            }
            
            for (int i = 1; i < 16; i++){
                if (i == 1){
                    metCrist2 = new Meth2(40, 120);
                    lnkCol2.add(metCrist2);
                } 
                else{
                    Meth2 metCrist1aux = (Meth2) lnkCol2.get(i - 2);
                    metCrist2 = new Meth2(metCrist1aux.getPosX() + 50
                            , metCrist1aux.getPosY());
                    lnkCol2.add(metCrist2);
                }
            }
            
            for (int i = 1; i < 16; i++){
                if (i == 1){
                    metCrist1 = new Meth1(40, 170);
                    lnkCol3.add(metCrist1);
                }
                else{
                    Meth1 metCrist1aux = (Meth1) lnkCol3.get(i - 2);
                    metCrist1 = new Meth1(metCrist1aux.getPosX() + 50
                            , metCrist1aux.getPosY());
                    lnkCol3.add(metCrist1);
                }
            }
            
            for (int i = 1; i < 16; i++){
                if (i == 1){
                    metCrist2 = new Meth2(40, 220);
                    lnkCol4.add(metCrist2);
                }
                else{
                    Meth2 metCrist1aux = (Meth2) lnkCol4.get(i - 2);
                    metCrist2 = new Meth2(metCrist1aux.getPosX() + 50
                            , metCrist1aux.getPosY());
                    lnkCol4.add(metCrist2);
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
     * liberada. 
     */
    public void keyReleased(KeyEvent e){
        // Se deja de mover la barra
        bMover = false;
        camCamion.setMoviendose(false);
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
        // Si no se le ha presionado space esta la pagina de inicio
        if (!bInicio){
            g.drawImage(imaInicio,0,0,this);
         }  
        else{
            //Cuando ganas se muestra la pantalla de imaVictoria
            if (iScore > 2080 && iVidas > 0){ 
                g.drawImage(imaVictoria,0,0,this);  
                    g.setColor(Color.black);
                    g.drawString("Puntos = " + iScore, 20, 150);
                    g.drawString("Vidas = " + iVidas, 20, 170);
            }
            // Si iVidas es menor a 0 se pierde el juego
            else if (iVidas > 0){
                g.drawImage(fondo, 0, 0, this);
                if (lnkCol1 != null && camCamion != null){
                    //Se pintan los cristales del juego
                    for (Meth1 i : lnkCol1){
                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY()
                                , this);
                    }
                    for (Meth2 i : lnkCol2){
                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY()
                                , this);
                    }
                    for (Meth1 i : lnkCol3) {

                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY()
                                , this);
                    }
                    for (Meth2 i : lnkCol4) {
                        g.drawImage(i.getImagenI(), i.getPosX(), i.getPosY()
                                , this);
                    }
                    // Se pinta la barra y la pelPelota
                    g.drawImage(aniPelota.getImagen(), pelPelota.getPosX()
                            , pelPelota.getPosY(), this);
                    g.drawImage(aniCamion.getImagen()
                            , camCamion.getPosX(), camCamion.getPosY(), this);
                    //Despliega los puntos y las iVidas 
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                    g.drawString("Puntos = " + iScore, 20, 50);
                    g.drawString("Vidas = " + iVidas, 20, 70);

                }
                else{
                    //Da un mensaje mientras se carga el dibujo	
                    g.drawString("No se cargo la imagen..", 20, 20);
                }

            } 
            else if (iVidas == 0) {
               //Cuando pierdes se despliega la pantalla de game over
               g.drawImage(imaGameOver, 0, 0, this); 
            }
        
        }
    }
}
