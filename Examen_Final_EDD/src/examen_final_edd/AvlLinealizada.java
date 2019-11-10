package examen_final_edd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AvlLinealizada {

    public Nodo[] estudiante = new Nodo[100];

    public void insertar(int carne, String nombre) {
        int pos = 0;
        if (estudiante[0] == null) {
            pos = 0;
        } else {
            while (estudiante[pos] != null) {
                if (carne > estudiante[pos].carne) {
                    pos = 2 * estudiante[pos].indice + 2;
                } else {
                    pos = 2 * estudiante[pos].indice + 1;
                }
            }
        }
        Nodo nuevo = new Nodo(pos, carne, nombre);
        estudiante[pos] = nuevo;
    }

    public void imprimir() {
        for (int i = 0; i < estudiante.length; i++) {
            if (estudiante[i] != null) {
                System.out.println("indice: " + estudiante[i].indice + "  carne: " + estudiante[i].carne);
            }
        }
    }

    public void escribir_avl_js() {
        File f = new File("avl.js");
        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(avl_js());
            wr.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("error: \n" + e);
        };
    }

    private String avl_js() {
        String cuerpo = "ar chart_config = { \n";
        cuerpo += "chart: { \n container: \"#basic-example\", \n";
        cuerpo += "connectors: { \n type: 'bCurve' \n }, \n";
        cuerpo += "animateOnInit: true, \n node: { \n collapsable: true,HTMLclass: 'nodeExample1' \n }, \n";
        cuerpo += "animation: { \n nodeAnimation: \"easeOutBounce\", \n nodeSpeed: 700, \n";
        cuerpo += "connectorsAnimation: \"bounce\", \n connectorsSpeed: 700 \n } \n },";
        cuerpo += "nodeStructure: {  \n";
        cuerpo += recorrer_avl(0);
        cuerpo += "\n}";
        cuerpo += "};";

        return cuerpo;
    }

    private String recorrer_avl(int indice) {
        String cuerpo = "text: {\n";
        cuerpo += "name: \"" + estudiante[indice].nombre + "\", \n";
        cuerpo += "title: \"" + estudiante[indice].carne + "\", \n";
        cuerpo += "}";
        if (estudiante[2 * indice + 1] != null || estudiante[2 * indice + 2] != null) {
            cuerpo += ", \n collapsed: true, \n";
            cuerpo += "children: [ \n";
            if (estudiante[2 * indice + 1] != null) {
                /*mandar a la izquierda*/
                cuerpo += "{ \n";
                cuerpo += recorrer_avl(2 * indice + 1);
                cuerpo += "} \n";
            } else {
                /*nandar a la derecha*/
                if (estudiante[2 * indice + 2] != null && estudiante[2 * indice + 1] != null) {
                    cuerpo += ", \n";
                }
                cuerpo += "{ \n";
                cuerpo += recorrer_avl(2 * indice + 2);
                cuerpo += "} \n";
            }
            cuerpo += "] \n";
        }
        return cuerpo;
    }

}
