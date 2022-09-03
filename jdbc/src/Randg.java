import java.util.*;

import javax.swing.plaf.synth.SynthTabbedPaneUI;

public class Randg {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rn = (int) (1 + (random.nextFloat() * 1000000000) % 1000000000);
            System.out.println(rn);
        }
    }

}
