/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_final_edd;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KUINN
 */
public class Examen_Final_EDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Menu mn = new Menu();
        mn.show();
        
        /*
        AvlLinealizada miavl = new AvlLinealizada();
        miavl.insertar(3, "pascual");
        miavl.insertar(1, "yenifer");
        miavl.insertar(2, "nilda");
        miavl.imprimir();
        miavl.escribir_avl_js();
        File objetofile = new File("libreria_TreaantJS//index.html");
        try {
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
    
}
