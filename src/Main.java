
import archivoscsv.operacionesCSV;


public class Main {
    public static void main(String[] args) {
        operacionesCSV operacionesCSV = new operacionesCSV("src/resources/redessociales.csv");
        operacionesCSV.start();
    }
}
