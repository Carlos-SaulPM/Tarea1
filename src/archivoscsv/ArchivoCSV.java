package archivoscsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class ArchivoCSV {
    //Facebook variables
    public static int fb_Seguidores = 0;
    public static int fb_Crecimiento = 1;
    public static int fb_PorcentajeCrecimiento = 2;
    public static int fb_Publicaciones = 3;
    public static int fb_MeGustaEnPublic = 4;
    public static int fb_PublicCompartidas = 5;
    public static int fb_Comentarios = 6;
    //Twitter Variables
    public static int tw_Seguidores = 7;
    public static int tw_Crecimiento = 8;
    public static int tw_PorcentajeCrecimiento = 9;
    public static int tw_Publicaciones = 10;
    public static int tw_Retuits = 11;
    public static int tw_MeGusta = 12;
    public static int tw_Impactos = 13;
    //Youtube Variables
    public static int yt_Videos = 14;
    public static int yt_Visualizaciones = 15;
    public static int yt_Comentarios = 16;
    public static int yt_Megusta = 17;

    private ArrayList<Double[]> datos;

    ArchivoCSV(ArrayList<Double[]> datos) {this.datos = datos;}
    public double obtenerDiferencia(int concepto, int mes1, int mes2) {
        mes1--;
        mes2--;
        if(mes1<0 || mes1>=12) return 0;
        if(mes2<0 || mes2>=12) return 0;
        double resultado = 0;
        resultado = Math.abs(getDatos().get(concepto)[mes1] - getDatos().get(concepto)[mes2]);
        return resultado;
    }
    public double obtenerPromedio(int concepto, int mesInicio, int mesFin) {
        mesInicio--;
        mesFin--;
        if(mesInicio<0 || mesInicio>=12) return 0;
        if(mesFin<0 || mesFin>=12) return 0;
        if (mesInicio > mesFin) return 0;
        double resultado = 0;
        int contador = 0;
        for(Double valor : datos.get(concepto)) {
            if (contador == (mesFin+1)) break;
            resultado += valor;
            contador++;

        }
        return resultado/(mesFin+1);
    }

    public static ArrayList<Double[]> readCSV(String path){
        ArrayList<Double[]> datos = new ArrayList<>();
        try {
            BufferedReader lector = new BufferedReader(new FileReader(path));
            String linea;
            while ((linea= lector.readLine()) != null){
                String[] elementos = linea.split(",");
                Double[] fila = new Double[12];
                int columna = 0;
                for (String elemento : elementos) {
                    if (esNumero(elemento)) {
                        double numero = Double.parseDouble(elemento.replace("%", ""));
                        if (numero != 2020) {
                            fila[columna] = numero;
                            columna++;
                            if (columna == 12) {
                                datos.add(fila);
                                fila = new Double[12];
                                columna = 0;
                            }
                        }
                    }
                }
            }
            //imprimirMatriz(datos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return datos;
    }
    static private boolean esNumero(String str) {
        try {
            Double.parseDouble(str.replace("%", ""));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public ArrayList<Double[]> getDatos() {
        return datos;
    }
}


