package archivoscsv;

import java.io.IOException;
import java.util.Scanner;

public class operacionesCSV {
    private ArchivoCSV archivo;

    public operacionesCSV(String url) {
        this.archivo = new ArchivoCSV(ArchivoCSV.readCSV(url));
    }

    public void start() {
        cls();
        diferenciaDeSegTwitter();
        cls();
        diferenciaDeVisYoutube();
        cls();
        promCrecFacebookTwitter();
        cls();
        promMegustaYoutubeFacebookTwitter();
        cls();
        end();
    }

    private void end() {
        System.out.println("Que tenga buen dia!");
    }

    //Diferencia de seguidores de twitter ENERO / JUNIO
    private void diferenciaDeSegTwitter() {
        double resultado = getArchivo().obtenerDiferencia(ArchivoCSV.tw_Seguidores, 1, 6);
        imp(StringTemplate.STR."Diferencia de seguidores en Twitter entre Enero y Junio: \n\{resultado}");
    }

    //Diferencia de visualizaciones Youtube (Teclado)
    private void diferenciaDeVisYoutube() {
        boolean datosCorrectos = false;
        int mesInicio = 0, mesFinal = 0;

        imp("Permítame ayudarle a calcular la diferencia de visualizaciones de Youtube: ");
        imp("1.- Enero \n2.- Febrero \n3.- Marzo \n4.- Abril \n5.- Mayo \n6.- Junio");
        Scanner sc = new Scanner(System.in);

        do {
            imp("Ingrese el mes a partir del cual desea calcular: ");
            if (sc.hasNextInt()) {
                mesInicio = sc.nextInt();
            } else {
                imp("Ingrese una opción válida:");
                sc.next();
                cls();
                continue;
            }

            imp("Ingrese el segundo mes, que servirá como rango: ");
            if (sc.hasNextInt()) {
                mesFinal = sc.nextInt();
            } else {
                imp("Ingrese una opción válida:");
                sc.next();
                cls();
                continue;
            }

            if (mesInicio <= 0 || mesInicio >= 7) {
                imp("Opción inválida de mes de inicio");
                cls();
            } else if (mesFinal <= 0 || mesFinal >= 7) {
                imp("No seleccionó una opción válida para el mes de rango");
                cls();
            } else {
                datosCorrectos = true;
            }
        } while (!datosCorrectos);

        imp(String.valueOf(getArchivo().obtenerDiferencia(ArchivoCSV.yt_Visualizaciones, mesInicio, mesFinal)));
    }

    //Promedio de crecimiento de Facebook y Twitter
    private void promCrecFacebookTwitter (){
        double facebookPromedio =  getArchivo().obtenerPromedio(ArchivoCSV.fb_Crecimiento,1, 6 );
        double twitterPromedio = getArchivo().obtenerPromedio(ArchivoCSV.tw_Crecimiento,1, 6 );
        imp(STR."El promedio de crecimiento de Facebook y Twitter es de: \nFacebook:\{String.format("%.2f", facebookPromedio)} \nTwitter: \{String.format("%.2f", twitterPromedio)} ");

    }

    //Promedio de Me Gusta en Youtube, Facebook y Twitter.
    private void promMegustaYoutubeFacebookTwitter (){
        double facebookPromedioMegusta =  getArchivo().obtenerPromedio(ArchivoCSV.fb_MeGustaEnPublic,1, 12 );
        double twitterPromedioMegusta =  getArchivo().obtenerPromedio(ArchivoCSV.tw_MeGusta,1, 12 );
        double youtubePromedioMegusta =  getArchivo().obtenerPromedio(ArchivoCSV.yt_Megusta,1, 12 );
        imp(STR."El promedio de me gusta de cada Red Social: \nYoutube: \{String.format("%.2f", youtubePromedioMegusta)} \nTwitter: \{String.format("%.2f", twitterPromedioMegusta)} \nFacebook: \{String.format("%.2f", facebookPromedioMegusta)}");

    }

    private void imp(String texto) {
        System.out.println(texto);
    }
    private void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println();
            System.out.println();
            lineaSeparacion();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void lineaSeparacion(){
        System.out.println("_______________________________________________________________");
    }

    public ArchivoCSV getArchivo() {
        return archivo;
    }
}
