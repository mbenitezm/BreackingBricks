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
public class Camion extends Base{  

    public Camion (int posX,int posY){
        super(posX,posY);	
        Image camionsito1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv1.png")).getScaledInstance(80, 18, posY);
        Image camionsito2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv2.png")).getScaledInstance(80, 18, posY);
        Image camionsito3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv3.png")).getScaledInstance(80, 18, posY);
        Image camionsito4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv4.png")).getScaledInstance(80, 18, posY);
        Image camionsito5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv5.png")).getScaledInstance(80, 18, posY);
        Image camionsito6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv6.png")).getScaledInstance(80, 18, posY);
        Image camionsito7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("rv7.png")).getScaledInstance(80, 18, posY);

        animacion = new Animacion();
        animacion.sumaCuadro(camionsito1, 100);
        animacion.sumaCuadro(camionsito2, 100);
        animacion.sumaCuadro(camionsito3, 100);
        animacion.sumaCuadro(camionsito4, 100);
        animacion.sumaCuadro(camionsito5, 100);
        animacion.sumaCuadro(camionsito6, 100);
        animacion.sumaCuadro(camionsito7, 100);
    }
    
    private long tiempoActual = System.currentTimeMillis();
    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";
    
    public static String getPausado(){
        return PAUSADO;
    }
    
    public static String getDesaparece(){
        return DESAPARECE;
    }   
}
    

