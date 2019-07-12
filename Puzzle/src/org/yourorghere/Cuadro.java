/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import java.io.File;

/**
 *
 * @author Lil
 */
public class Cuadro {
    
    File img;
    Texture tex;
    int imagen;
    int indice;
    float cuadrado[] = new float[4];
    
    public Cuadro(){
        
    }
    
    public Cuadro (File i, Texture t, int im, int ind){
        img = i;
        tex = t;
        imagen = im;
        indice = ind;
    }
    
    
}
