/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbricks;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Marcel Benitez
 */
public class Pelota extends Base {
        private int velX;
        private int velY;
        public Pelota(int posX, int posY) {
        super(posX, posY);
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
         
         
        animacion = new Animacion();
        animacion.sumaCuadro(bola1, 100);
        animacion.sumaCuadro(bola2, 100);
        animacion.sumaCuadro(bola3, 100);
        animacion.sumaCuadro(bola4, 100);
        animacion.sumaCuadro(bola5, 100);
        animacion.sumaCuadro(bola6, 100);
        animacion.sumaCuadro(bola7, 100);
        animacion.sumaCuadro(bola8, 100);
        animacion.sumaCuadro(bola9, 100);
        velX = 0;
        while (velX == 0) {
            velX = (int) (Math.random() * 10) - 5;
        }
        velY = -4;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelX(int v) {
        velX = v;
    }

    public void setVelY(int v) {
        velY = v;
    }
    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";

    public static String getPausado() {
        return PAUSADO;
    }

    public static String getDesaparece() {
        return DESAPARECE;
    }
    
}
