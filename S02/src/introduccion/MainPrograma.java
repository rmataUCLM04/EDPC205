package introduccion;

import java.io.FileNotFoundException;
import utils.SequentialFile;

public class MainPrograma {

    public static void main(String[] args) {
        Satelite sat = new Satelite();

        try {
            SequentialFile<Satelite> sf = new SequentialFile<>("weather.csv", ",");
            sf.skip();

            while (!sf.isEndOfFile()) {
                sf.read(sat);
                if (sat.getMeanMotion() >= 10) { 
                    System.out.println(sat);
                }
            }

            sf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
