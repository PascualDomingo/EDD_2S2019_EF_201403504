package examen_final_edd;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AvlLinealizada {

    public Nodo[] estudiante = new Nodo[100];
    String graph = "";

    public void insertar(int carne, String nombre) {
        int pos = 0;
        if (estudiante[0] == null) {
            pos = 0;
        } else {
            while (estudiante[pos] != null) {
                if (carne > estudiante[pos].carne) {
                    pos = 2 * pos + 2;
                } else {
                    pos = 2 * pos + 1;
                }
            }
        }
        Nodo nuevo = new Nodo(pos, carne, nombre);
        estudiante[pos] = nuevo;
        balancear_avl();
    }

    public void imprimir() {
        for (int i = 0; i < estudiante.length; i++) {
            if (estudiante[i] != null) {
                System.out.println("FE: " + estudiante[i].FE + "  carne: " + estudiante[i].carne);
            }
        }
    }

    public void escribir_avl_js() {
        File f = new File("libreria_TreaantJS//ejemplo.js");
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
        String cuerpo = "var chart_config = { \n";
        cuerpo += "chart: { \n container: \"#basic-example\", \n";
        cuerpo += "connectors: { \n type: 'bCurve' \n }, \n";
        cuerpo += "animateOnInit: true, \n node: { \n collapsable: true,HTMLclass: 'nodeExample1' \n }, \n";
        cuerpo += "animation: { \n nodeAnimation: \"easeOutBounce\", \n nodeSpeed: 700, \n";
        cuerpo += "connectorsAnimation: \"bounce\", \n connectorsSpeed: 700 \n } \n }, \n";
        cuerpo += "nodeStructure: {  \n";
        cuerpo += recorrer_avl(0);
        cuerpo += "\n} \n\n";
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
            }
            if (estudiante[2 * indice + 2] != null) {
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

    public void preorden() throws IOException {
        String graphviz = "digraph G{ \n";
        graphviz += "graph[splines=ortho]; \n";
        graphviz += "node[shape=record]; \n";
        graphviz += "rankdir = LR; \n";
        graphviz += recorrer_preorden(0);
        graphviz += "} \n\n";

        try {
            PrintWriter write = new PrintWriter("reportes//preorden.dot", "UTF-8");
            write.println(graphviz);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*convertir dot a png*/
        String[] cmd = {"dot", "-Tpng", "-o", "reportes//preorden.png", "reportes//preorden.dot"};
        Runtime.getRuntime().exec(cmd);
        /*abrir archivo png*/
        File objetofile = new File("reportes//preorden.png");
        Desktop.getDesktop().open(objetofile);

    }

    private String recorrer_preorden(int indice) {
        String cuerpo = "\"" + estudiante[indice].carne + " \\n " + estudiante[indice].nombre+"\"" ;

        if (estudiante[2 * indice + 1] != null) {
            /*mandar a la izquierda*/
            cuerpo += " -> ";
            cuerpo += recorrer_preorden(2 * indice + 1);
            cuerpo += "\n";
        }
        if (estudiante[2 * indice + 2] != null) {
            /*nandar a la derecha*/
            cuerpo += " -> ";
            cuerpo += recorrer_preorden(2 * indice + 2);
            cuerpo += "\n";
        }
        return cuerpo;
    }

    public void inorden() throws IOException {
        graph = "";
        String graphviz = "digraph G{ \n";
        graphviz += "graph[splines=ortho]; \n";
        graphviz += "node[shape=record]; \n";
        graphviz += "rankdir = LR; \n";
        graphviz += recorrer_inorden(0);
        graphviz += "} \n\n";

        try {
            PrintWriter write = new PrintWriter("reportes//inorden.dot", "UTF-8");
            write.println(graphviz);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*convertir dot a png*/
        String[] cmd = {"dot", "-Tpng", "-o", "reportes//inorden.png", "reportes//inorden.dot"};
        Runtime.getRuntime().exec(cmd);
        /*abrir archivo png*/
        File objetofile = new File("reportes//inorden.png");
        Desktop.getDesktop().open(objetofile);

    }

    private String recorrer_inorden(int indice) {
        
        if (estudiante[indice] != null) {
            /*mandar a la izquierda*/
            recorrer_inorden(2 * indice + 1);
            if(graph.equals("")){
                graph += "\"" + estudiante[indice].carne + " \\n " + estudiante[indice].nombre+"\" \n" ;
            }else{
                graph += " -> \"" + estudiante[indice].carne + " \\n " + estudiante[indice].nombre+"\" \n" ;
            }
            recorrer_inorden(2 * indice + 2);
        }
        return graph;
    }
    
    public void posorden() throws IOException {
        graph = "";
        String graphviz = "digraph G{ \n";
        graphviz += "graph[splines=ortho]; \n";
        graphviz += "node[shape=record]; \n";
        graphviz += "rankdir = LR; \n";
        graphviz += recorrer_posorden(0);
        graphviz += "} \n\n";

        try {
            PrintWriter write = new PrintWriter("reportes//posorden.dot", "UTF-8");
            write.println(graphviz);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*convertir dot a png*/
        String[] cmd = {"dot", "-Tpng", "-o", "reportes//posorden.png", "reportes//posorden.dot"};
        Runtime.getRuntime().exec(cmd);
        /*abrir archivo png*/
        File objetofile = new File("reportes//posorden.png");
        Desktop.getDesktop().open(objetofile);

    }

    private String recorrer_posorden(int indice) {
        
        if (estudiante[indice] != null) {
            /*mandar a la izquierda*/
            recorrer_posorden(2 * indice + 1);
            recorrer_posorden(2 * indice + 2);
            if(graph.equals("")){
                graph += "\"" + estudiante[indice].carne + " \\n " + estudiante[indice].nombre+"\" \n" ;
            }else{
                graph += " -> \"" + estudiante[indice].carne + " \\n " + estudiante[indice].nombre+"\" \n" ;
            }
            
        }
        return graph;
    }

    private int calcular_altura(int indice) {
        if (estudiante[indice] == null) {
            return 0;
        } else {
            return 1 + Math.max(calcular_altura(2*indice+1), calcular_altura(2*indice +2));
        }
    }
    
    private void agregar_altura(int indice) {
        estudiante[indice].altura = calcular_altura(indice) - 1;
        if (estudiante[2*indice+1] != null) {
            agregar_altura(2*indice+1);
        }
        if (estudiante[2*indice+2] != null) {
            agregar_altura(2*indice+2);
        }
    }
    
    private void factor_equilibrio(int indice) {
        if (estudiante[2*indice+1] != null && estudiante[2*indice+2] != null) {
            estudiante[indice].FE = (estudiante[2*indice+2].altura + 1) - (estudiante[2*indice+1].altura + 1);
        } else if (estudiante[2*indice+1] != null && estudiante[2*indice+2] == null) {
            estudiante[indice].FE = 0 - (estudiante[2*indice+1].altura + 1);
        } else if (estudiante[2*indice+1] == null && estudiante[2*indice+2] != null) {
            estudiante[indice].FE = estudiante[2*indice+2].altura + 1;
        } else {
            estudiante[indice].FE = 0;
        }

        if (estudiante[2*indice+1] != null) {
            factor_equilibrio(2*indice+1);
        }
        if (estudiante[2*indice+2] != null) {
            factor_equilibrio(2*indice+2);
        }
    }
    
    private void rotacion_izquierda(int indice) {
        int NodoIz = 2*indice+1;//nodo_izquierda = root.izquierda;
        Nodo iz = estudiante[NodoIz];
        estudiante[NodoIz] = estudiante[2*NodoIz+2];//root.izquierda = nodo_izquierda.derecha;
        estudiante[2*NodoIz+2] = estudiante[indice];//nodo_izquierda.derecha = root;
        estudiante[indice] = iz;//root = nodo_izquierda;
        Nodo[] aux = estudiante;
        Nodo[] temp = new Nodo[100];
        estudiante = temp;
        int con = 0;
        for (int i = 0; i < aux.length; i++) {
            if(aux[i] != null){
                estudiante[con] = aux[i];
                con++;
            }
        }
    }
    
    private void rotacion_derecha(int indice) {
        int NodoDer = 2*indice+2;//nodo_izquierda = root.izquierda;
        Nodo der = estudiante[NodoDer];
        estudiante[NodoDer] = estudiante[2*NodoDer+1];//root.izquierda = nodo_izquierda.derecha;
        estudiante[2*NodoDer+1] = estudiante[indice];//nodo_izquierda.derecha = root;
        estudiante[indice] = der;//root = nodo_izquierda;
        Nodo[] aux = estudiante;
        Nodo[] temp = new Nodo[100];
        estudiante = temp;
        int con = 0;
        for (int i = 0; i < aux.length; i++) {
            if(aux[i] != null){
                estudiante[con] = aux[i];
                con++;
            }
        }
    }
    
    private void rotacion_doble_derecha(int indice) {
        //NodoAvl arbol = root;
        rotacion_izquierda(indice);
        rotacion_derecha(indice);
    }
    
    private void rotacion_doble_izquierda(int indice) {
        //NodoAvl arbol = root;
        rotacion_derecha(2*indice+1);
        rotacion_izquierda(indice);
    }
    
    
    private void equilibrar_avl(int indice) {
        if (estudiante[indice].FE == -2) {
            if (estudiante[2*indice+1].FE == 1) {
                rotacion_doble_izquierda(indice);
            } else if (estudiante[2*indice+1].FE == -1) {
                rotacion_izquierda(indice);
            }
        } else if (estudiante[indice].FE == 2) {
            if (estudiante[2*indice+2].FE == 1) {
                rotacion_derecha(indice);
            } else if (estudiante[2*indice+2].FE == -1) {
                rotacion_doble_derecha(indice);
            }
        } else {
            if (estudiante[2*indice+1] != null) {
                equilibrar_avl(2*indice+1);//root.izquierda = equilibrar_avl(root.izquierda);
            }
            if (estudiante[2*indice+2] != null) {
                equilibrar_avl(2*indice+2);//root.derecha = equilibrar_avl(root.derecha);
            }
            //return root;
        }
    }
    
    
    private void balancear_avl(){
        agregar_altura(0);
        factor_equilibrio(0);
        
        equilibrar_avl(0);
        
        agregar_altura(0);
        factor_equilibrio(0);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
