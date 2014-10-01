package breakingbricks;

import java.awt.Image;
import java.awt.Toolkit;

public class Meth1 extends Base {

    private int hits;
    
    // La metanfetamina 1 comienza teinendo 2 golpes antes de desaparecer
    public Meth1 (int posX, int posY) {
        super(posX, posY);
        Image cristal = Toolkit.getDefaultToolkit().getImage(this.getClass()
                .getResource("cristal 21.png"))
                .getScaledInstance(40, 32, 1);
        animacion = new Animacion();
        animacion.sumaCuadro(cristal, 100);
        hits = 2;
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
    
    //Cambios de imagenes dependiendo cuantos golpes le quedan
    public void Quebrar(int h) {
        if (h == 1) {
            Image cristal1;
            cristal1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                    .getResource("cristal 22.png"))
                    .getScaledInstance(40, 32, 1);
            animacion = new Animacion();
            animacion.sumaCuadro(cristal1, 100);
        }
    }
}
