package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;



/**
 * Puzzle.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Puzzle implements GLEventListener {
    
    public static Dimension size;
    public static float posicionX = 0;
    public static float posicionY = 0;
    public static Cuadro[] tablero = new Cuadro[10];
    public static int imagen ;
    public static Vector v = new Vector(9);
    
    public static void shuffle(Vector v) {
        Random random = new Random(System.currentTimeMillis());
        Object[] array = new Object[v.size()];
        for (int i = 0; i < v.size(); i++) {
            array[i] = v.elementAt(i);
        }

        int index;
        Object temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }

        for (int i = 0; i < v.size(); i++) {
            v.setElementAt(array[i], i);
        }
    }
    
    public static void moverPosicion(int i, int j){
        File f = tablero[i].img;//Cuadro aux = new Cuadro(tablero[8]);
        Texture t = tablero[i].tex;
        //float[] cuadrado = tablero[i].cuadrado;
        int imagen = tablero[i].imagen;
        int indice = tablero[i].indice;

        tablero[i].img = tablero[j].img;
        tablero[i].tex = tablero[j].tex;
        //tablero[i].cuadrado = tablero[j].cuadrado;
        tablero[i].imagen = tablero[j].imagen;
        tablero[i].indice = tablero[j].indice;

        tablero[j].img = f;
        tablero[j].tex = t;
        //tablero[j].cuadrado = cuadrado;
        tablero[j].imagen = imagen;
        tablero[j].indice = indice;
    }
    
    public static void main(String[] args) {
        Frame frame = new Frame("Puzzle :v");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Puzzle());
        
        tablero[0] = new Cuadro();//esta posicion no se utiliza
        
        v.addElement(1);
        v.addElement(2);
        v.addElement(3);
        v.addElement(4);
        v.addElement(5);
        v.addElement(6);
        v.addElement(7);
        v.addElement(8);
        v.addElement(9);
        shuffle(v);
         canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int indice = 0;
                posicionX = (float) (((e.getX() + 10) - size.width / 2) / 0.9) / 100;
                posicionY = (float) (-1.0 * (((e.getY() + 20) - size.height / 2) / 0.9) / 100);
                    
                
                if ((posicionX > -2.1 && posicionX < -0.7) && (posicionY < 2.1 && posicionY > 0.7)) {
                    indice = 1;
                    if(tablero[2].indice == 9){
                        moverPosicion(indice, 2);
                    }
                    else if(tablero[4].indice == 9){
                        moverPosicion(indice, 4);
                    }
                } else if ((posicionX > -0.7 && posicionX < 0.7) && (posicionY < 2.1 && posicionY > 0.7)) {
                    indice = 2;
                    if(tablero[1].indice == 9){
                        moverPosicion(indice, 1);
                    }
                    else if(tablero[3].indice == 9){
                        moverPosicion(indice, 3);
                    }
                    else if(tablero[5].indice == 9){
                        moverPosicion(indice, 5);
                    }
                    
                } else if ((posicionX > 0.7 && posicionX < 2.1) && (posicionY < 2.1 && posicionY > 0.7)) {
                    indice = 3;
                    if(tablero[2].indice == 9){
                        moverPosicion(indice, 2);
                    }
                    else if(tablero[6].indice == 9){
                        moverPosicion(indice, 6);
                    }
                    
                } else if ((posicionX > -2.1 && posicionX < -0.7) && (posicionY < 0.7 && posicionY > -0.7)) {
                    indice = 4;
                    if(tablero[1].indice == 9){
                        moverPosicion(indice, 1);
                    }
                    else if(tablero[5].indice == 9){
                        moverPosicion(indice, 5);
                    }
                    else if(tablero[7].indice == 9){
                        moverPosicion(indice, 7);
                    }
                    
                } else if ((posicionX > -0.7 && posicionX < 0.7) && (posicionY < 0.7 && posicionY > -0.7)) {
                    indice = 5;
                    if(tablero[8].indice == 9){
                        moverPosicion(indice, 8);
                    }
                    else if(tablero[6].indice == 9){
                        moverPosicion(indice, 6);
                    }
                    else if(tablero[4].indice == 9){
                        moverPosicion(indice, 4);
                    }
                    else if(tablero[2].indice == 9){
                        moverPosicion(indice, 2);
                    }
                    
                } else if ((posicionX > 0.7 && posicionX < 2.1) && (posicionY < 0.7 && posicionY > -0.7)) {
                    indice = 6;
                    if(tablero[9].indice == 9){
                        moverPosicion(indice, 9);
                    }
                    else if(tablero[5].indice == 9){
                        moverPosicion(indice, 5);
                    }
                    else if(tablero[3].indice == 9){
                        moverPosicion(indice, 3);
                    }
                    
                } else if ((posicionX > -2.1 && posicionX < -0.7) && (posicionY < -0.7 && posicionY > -2.1) ) {
                    indice = 7;
                    if(tablero[8].indice == 9){
                        moverPosicion(indice, 8);
                    }
                    else if(tablero[4].indice == 9){
                        moverPosicion(indice, 4);
                    }
                    
                } else if ((posicionX > -0.7 && posicionX < 0.7) && (posicionY < -0.7 && posicionY > -2.1)) {
                    indice = 8;
                    if(tablero[9].indice == 9){
                        moverPosicion(indice, 9);
                    }
                    else if(tablero[7].indice == 9){
                        moverPosicion(indice, 7);
                    }
                    else if(tablero[5].indice == 9){
                        moverPosicion(indice, 5);
                    }
                    
                } else if ((posicionX > 0.7 && posicionX < 2.1) && (posicionY < -0.7 && posicionY > -2.1)) {
                    indice = 9;
                    if(tablero[6].indice == 9){
                        moverPosicion(indice, 6);
                    }
                    else if(tablero[8].indice == 9){
                        moverPosicion(indice, 8);
                    }
                    
                }
                
                System.out.println("Posicion: "+indice);
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        frame.add(canvas);
        frame.setSize(640, 480);
        size = frame.getSize();
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }
    
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);
        
        File img;
        Texture tex;
        
        
        try {
            for(int i = 0; i<9 ; i++){
                int x = (Integer) v.get(i);
                String url = "src/img/"+x+".png";
                img = new File(url);
                tex = TextureIO.newTexture(img, true);
                imagen = tex.getTextureObject();
                Cuadro p = new Cuadro(img, tex, imagen, x, i+1);
                asignarPosicion(p);
                tablero[i+1] = p;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        gl.glEnable(GL.GL_BLEND);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        
        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public void asignarPosicion(Cuadro p){
        int x = p.posicion;
        switch (x) {
            case 1:
                p.cuadrado[0] = -2.1f;
                p.cuadrado[1] = -0.7f;
                p.cuadrado[2] = 2.1f;
                p.cuadrado[3] = 0.7f;
                break;
            case 2:
                p.cuadrado[0] = -0.7f;
                p.cuadrado[1] = 0.7f;
                p.cuadrado[2] = 2.1f;
                p.cuadrado[3] = 0.7f;
                break;
            case 3:
                p.cuadrado[0] = 0.7f;
                p.cuadrado[1] = 2.1f;
                p.cuadrado[2] = 2.1f;
                p.cuadrado[3] = 0.7f;
                break;
            case 4:
                p.cuadrado[0] = -2.1f;
                p.cuadrado[1] = -0.7f;
                p.cuadrado[2] = 0.7f;
                p.cuadrado[3] = -0.7f;
                break;
            case 5:
                p.cuadrado[0] = -0.7f;
                p.cuadrado[1] = 0.7f;
                p.cuadrado[2] = 0.7f;
                p.cuadrado[3] = -0.7f;
                break;
            case 6:
                p.cuadrado[0] = 0.7f;
                p.cuadrado[1] = 2.1f;
                p.cuadrado[2] = 0.7f;
                p.cuadrado[3] = -0.7f;
                break;
            case 7:
                p.cuadrado[0] = -2.1f;
                p.cuadrado[1] = -0.7f;
                p.cuadrado[2] = -0.7f;
                p.cuadrado[3] = -2.1f;
                break;
            case 8:
                p.cuadrado[0] = -0.7f;
                p.cuadrado[1] = 0.7f;
                p.cuadrado[2] = -0.7f;
                p.cuadrado[3] = -2.1f;
                break;
            case 9:
                p.cuadrado[0] = 0.7f;
                p.cuadrado[1] = 2.1f;
                p.cuadrado[2] = -0.7f;
                p.cuadrado[3] = -2.1f;
                break;
            default:
                break;
        }
    }
    
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
                
        gl.glColor3f(1, 1, 1);
        for (int i = 1; i<10 ;i++) {

            gl.glEnable(GL.GL_TEXTURE_2D);
            gl.glBindTexture(GL.GL_TEXTURE_2D, tablero[i].imagen);

            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3f(tablero[i].cuadrado[0], tablero[i].cuadrado[2], 0.0f);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(tablero[i].cuadrado[1], tablero[i].cuadrado[2], 0.0f);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(tablero[i].cuadrado[1], tablero[i].cuadrado[3], 0.0f);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(tablero[i].cuadrado[0], tablero[i].cuadrado[3], 0.0f);

            gl.glEnd();
            gl.glDisable(GL.GL_TEXTURE_2D);
        }
        gl.glEnd();
        
        gl.glColor3f(0,0,0);
        gl.glLineWidth(1);
            gl.glDisable(GL.GL_LINE_STIPPLE);
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex2d(-2.1, 2.1);
                gl.glVertex2d(2.1, 2.1);
                gl.glVertex2d(2.1, -2.1);
                gl.glVertex2d(-2.1, -2.1);
                gl.glVertex2d(-2.1, 2.1);
        gl.glEnd();
        
        gl.glLineWidth(1);
            gl.glDisable(GL.GL_LINE_STIPPLE);
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex2d(-0.7, 2.1);
                gl.glVertex2d(-0.7, -2.1);
                
        gl.glEnd();
             
        gl.glLineWidth(1);
            gl.glDisable(GL.GL_LINE_STIPPLE);
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex2d(0.7, 2.1);
                gl.glVertex2d(0.7, -2.1);  
        gl.glEnd();
        
        gl.glLineWidth(1);
            gl.glDisable(GL.GL_LINE_STIPPLE);
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex2d(2.1, 0.7);
                gl.glVertex2d(-2.1, 0.7);  
        gl.glEnd();
        
        gl.glLineWidth(1);
            gl.glDisable(GL.GL_LINE_STIPPLE);
            gl.glEnable(GL.GL_LINE_SMOOTH);
            gl.glColor3ub((byte) 0, (byte) 0, (byte) 0);
            gl.glBegin(GL.GL_LINE_STRIP);
                gl.glVertex2d(2.1, -0.7);
                gl.glVertex2d(-2.1, -0.7);  
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

