/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakingbricks;

import java.awt.Image;
import java.awt.Toolkit;

public class Meth2 extends Base {

    private int hits;

    // La segunda metanfetaminta comienza con 3 golpes
    public Meth2(int posX, int posY) {
        super(posX, posY);
        Image cristal = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("cristal 31.png"))
                .getScaledInstance(40, 32, 1);
        animacion = new Animacion();
        animacion.sumaCuadro(cristal, 100);

        hits = 3;
    }

    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";

    public static String getPausado() {
        return PAUSADO;
    }

    public static String getDesaparece() {
        return DESAPARECE;
    }

    public int getHits() {
        return hits;
    }

    public void RestaGolpe() {
        hits--;

    }

    public void Quebrar(int h) {
        if (h == 2) {
            Image cristal1;
            cristal1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("cristal 32.png"))
                    .getScaledInstance(40, 32, 1);
            animacion = new Animacion();
            animacion.sumaCuadro(cristal1, 100);
        }
        if (h == 1) {
            Image cristal2;
            cristal2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("cristal 33.png"))
                    .getScaledInstance(40, 32, 1);
            animacion = new Animacion();
            animacion.sumaCuadro(cristal2, 100);

        }
    }

}
