import java.util.collections;
import java.util.list;
import java.util.ArrayLists;
public class Principal{



public static void main(String[]args){
    
    List<Artista> MainStageDia1 =new ArrayLists<>();
    List<Artista> MainStageDia2 =new ArrayLists<>();
    List<Artista> MainStageDia3 =new ArrayLists<>();
    List<Artista> RiverStageDia1 =new ArrayLists<>();
    List<Artista> RiverStageDia2 =new ArrayLists<>();
    List<Artista> RiverStageDia3 =new ArrayLists<>();
    procesarFile(MainStageDia1, MainStageDia2, MainStageDia3, RiverStageDia1, RiverStageDia2, RiverStageDia3);
    int horaIniMain = 14 * 60;      // el escenario main inicia a las 14:00
    int horaIniRiver = 13 * 60 + 30; // el escenario river inicia a las 13:30
    int PAUSA = 30;





}

private static String aHora(int minutos) {
    int h = minutos / 60;
    int m = minutos % 60;
    return String.format("%02d:%02d", h, m);
}


public static void procesarFile(List <Artista> Main1,List <Artista> Main2;List<Artista> Main3;List<Artista>River1;List<Artista>River2,List<Artista>River3){
 String separador;
String listado= "artists.csv";
try{
FicheroSecuencial<Artista> fs;
fs = new FicheroSecuencial<Artista>(listado,separador);
Artista artistaActual;

while(!fs.isEndOfFile){
    artistaActual =new Artista();
    fs.read(artistaActual);
    System.out.println(artistaActual.toString())
    
if (artistaActual.getDia() ==1){
    if(artistaActual.getEscenario() = "Main"){
        Main1.add(artistaActual);
    }
    else if(artistaActual.getEscenario() = "River")
    River1.add(artistaActual);

    }else if(artistaActual.getDia()== 2){
        if(artistaActual.getEscenario = "Main"){
            Main2.add(artistaActual);

        }else if(artistaActual.getEscenario() = "River"){
            River2.add(artistaActual);

        }

    }
    else if(artistaActual.getDia() == 3){
        if(artistaActual.getEscenario = "Main"){
            Main3.add(artistaActual);

        }else if(artistaActual.getEscenario() = "River"){
            River3.add(artistaActual);

        }


    }

    
}

}catch(FileNotFoundException e){
    System.out.println(e.getMessage());
}
catch(IOException r){
    System.out.println(r.getMessage());
}
}



}
}