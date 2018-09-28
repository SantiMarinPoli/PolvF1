package polvf1;

import java.awt.*;
import javax.swing.*;

public class Polvf1 {

    String nombre;
    int n, vec[];
    private Color colorDatos[];

    public Polvf1(int x) {
        n = x;
        vec = new int[x];
        colorDatos = new Color[x];
    }

    public void limpiar() {
        for (int i = 0; i < colorDatos.length; i++) {
            if (i == 0) {
                colorDatos[i] = Color.LIGHT_GRAY;
            } else if (i < vec[0] + 2) {
                colorDatos[i] = Color.yellow;
            } else {
                colorDatos[i] = Color.green;
            }
        }
    }

    public void llenarPolinomio() {
        int numero;
        vec[0] = n - 2;

        for (int i = 1; i < vec[0] + 2; i++) {
            if (i == 1) {
                numero = (int) (Math.random() * 10 + 5);
            } else {
                numero = (int) (Math.random() * 10);
            }

            if (Math.random() < 0.5) {
                numero = numero * -1;
            }
            if (Math.random() < 0.2) {
                numero = numero * -1;
            }
            vec[i] = numero;
        }
        limpiar();
    }

    //*******************************************
    // ESTE MÉTODO DEVUELVE UN STRING CON EL POLINOMIO
    //********************************************	
    public String toString() {
        String respuesta = nombre + " = ";
        int i = 0;

        int exp = 0;
        for (i = 1; i < vec[0] + 2; i++) {
            exp = vec[0] + 1 - i;
            if (vec[i] >= 0) {
                respuesta = respuesta + "  +  " + vec[i] + "X^" + exp;

            } else {
                respuesta = respuesta + "  -  " + (vec[i] * (-1)) + "X^" + exp;

            }

        }
        return respuesta;
    }

    /**
     * Metodo mostrar la consola el polinomio del vector de forma 1
     */
    public void mostrarPolF1Consola() {

        int i, j;
        char ch;
        String exp, dato;
        for (j = 0; j < nombre.length(); j++) {
            ch = nombre.charAt(j);
            System.out.print(" " + ch);
        }

        System.out.print(" = ");

        for (i = 1; i < vec[0] + 2; i++) {

            if (vec[i] != 0) {

                dato = "" + vec[i];
                //pintar el signo
                if (vec[i] > 0 && i > 1) {
                    System.out.print(" + ");
                }
                //pintar el coeficiente	   	
                for (j = 0; j < dato.length(); j++) {
                    ch = dato.charAt(j);
                    System.out.print(" " + ch);
                }

                //pintar La variable x   
                System.out.print(" X ");

                //pintando el exponente
                exp = "" + (vec[0] + 1 - i);
                for (j = 0; j < exp.length(); j++) {
                    ch = exp.charAt(j);
                    System.out.print(" " + ch);
                }
            }
        }
    }

    /**
     * Constructor de la clase que ricibe el nombre y el tamaño del vector
     *
     * @param nombre
     * @param x
     */
    public Polvf1(String nombre, int x) {
        this.nombre = nombre;
        n = x;
        vec = new int[x];
        colorDatos = new Color[x];
    }

    public void setDato(int pos, int dato) {
        vec[pos] = dato;
    }

    public int getDato(int pos) {
        return vec[pos];
    }

    public int getGrado() {
        return vec[0];
    }

    public int getTamano() {
        return n;
    }

    /**
     * Ajustar
     *
     * @param vec
     */
    public void ajustar(float vec[]) {
        int cont = 0, i;
        if (vec[1] == 0) {
            i = 1;
            while (i < vec[0] + 2 && vec[i] == 0) {
                cont = cont + 1;
                i = i + 1;
            }
            for (int k = i; k < vec[0] + 2; k++) {
                vec[k - cont] = vec[k];
            }
            vec[0] = vec[0] - cont;
        }
    }

    /**
     * Ajustar
     */
    public void ajustar() {
        int cont = 0, i;
        if (vec[1] == 0) {
            i = 1;
            while (i < vec[0] + 2 && vec[i] == 0) {
                cont = cont + 1;
                i = i + 1;
            }
            for (int k = i; k < vec[0] + 2; k++) {
                vec[k - cont] = vec[k];
            }
            vec[0] = vec[0] - cont;
        }
    }

    /**
     * Redimensionar
     *
     * @param exp
     * @param coe
     */
    public void redimensionar(int exp) {
        int k, pos;
        n = exp + 2;
        int aux[];
        aux = new int[n];
       
        for (k = 1; k < vec[0] + 2; k++) {
            pos = exp + k - vec[0];
            aux[pos] = vec[k];
        }
        vec = aux;
        colorDatos = new Color[exp + 2];
        limpiar();
    }

    /**
     * Eliminar Term
     *
     * @param exp
     */
    public void eliminarTerm(int exp) {
        if (exp == vec[0]) {
            vec[1] = 0;
            ajustar();
        } else {
            int pos = vec[0] + 1 - exp;
            vec[pos] = 0;
        }
    }

    /**
     * Insertar Termino
     *
     * @param coe
     * @param exp
     */
    public void insertarTerm(int coe, int exp) {

        int pos;
        if (exp < 0) {
            System.out.println("El exponente no es valido");
        } else if (exp <= vec[0]) {
            pos = vec[0] + 1 - exp;
            vec[pos] = vec[pos] + coe;
            this.ajustar();
        } else {
            redimensionar(exp);
            vec[0] = exp;
            vec[1] = coe;
        }

    }

    /**
     * Sumar Polvf1
     *
     * @param B
     * @return
     */
    public Polvf1 sumar(Polvf1 B) {
        int k = 1, j = 1, my, expA, expB, posR;

        if (vec[0] > B.getDato(0)) {
            my = vec[0];
        } else {
            my = B.getDato(0);
        }
        Polvf1 R = new Polvf1("R", my + 2);
        R.setDato(0, my);
        while (k < vec[0] + 2 && j < B.getDato(0) + 2) {
            expA = vec[0] + 1 - k;
            expB = B.getDato(0) + 1 - j;
            if (expA == expB) {
                posR = R.getDato(0) + 1 - expA;
                R.setDato(posR, vec[k] + B.getDato(j));
                k = k + 1;
                j = j + 1;
            } else if (expA > expB) {
                posR = R.getDato(0) + 1 - expA;
                R.setDato(posR, vec[k]);
                k = k + 1;
            } else {
                posR = R.getDato(0) + 1 - expB;
                R.setDato(posR, B.getDato(j));
                j = j + 1;
            }
        }

        R.ajustar();
        return R;
    }

    /**
     * Multiplicar Polvf1
     *
     * @param B
     * @return
     */
    public Polvf1 mutiplicar(Polvf1 B) {
        int expA, expB, expR, posR, coeR, k, j;
        Polvf1 R = new Polvf1("R", vec[0] + B.getDato(0) + 2);
        R.setDato(0, vec[0] + B.getDato(0));
        for (j = 1; j < B.getDato(0) + 2; j++) {
            expB = B.getDato(0) + 1 - j;
            for (k = 1; k < vec[0] + 2; k++) {
                expA = vec[0] + 1 - k;
                expR = expA + expB;
                coeR = vec[k] * B.getDato(j);
                posR = R.getDato(0) + 1 - expR;
                R.setDato(posR, R.getDato(posR) + coeR);
            }
        }
        R.ajustar();
        return R;
    }

    /**
     * Hacer Copia
     *
     * @return
     */
    public Polvf1 hacerCopia() {
        int k;
        Polvf1 copia = new Polvf1(vec[0] + 2);
        copia.setDato(0, vec[0]);
        for (k = 1; k < vec[0] + 2; k++) {
            copia.setDato(k, vec[k]);
        }
        return copia;
    }

    /**
     * Dividir Polvf1
     *
     * @param B
     * @return
     */
    public Polvf1 dividir(Polvf1 B) {
        int k, expT, expA, posR, posA;
        float coeT, coeA;
        Polvf1 R = null;
        if (vec[0] >= B.getDato(0)) {
            Polvf1 copia = this.hacerCopia();
            R = new Polvf1(vec[0] - B.getDato(0) + 2);
            R.setDato(0, vec[0] - B.getDato(0));
            while (copia.getDato(0) >= B.getDato(0)) {
                expT = copia.getDato(0) - B.getDato(0);
                coeT = copia.getDato(1) / B.getDato(1);
                posR = R.getDato(0) + 1 - expT;
                R.setDato(posR, (int) coeT);
                for (k = 1; k < B.getDato(0) + 2; k++) {
                    expA = expT + B.getDato(0) + 1 - k;
                    coeA = coeT * B.getDato(k);
                    posA = copia.getDato(0) + 1 - expA;
                    copia.setDato(posA, (int) (copia.getDato(posA) - coeA));
                }
                copia.ajustar();
            }
        } else {
            System.out.println("No se puede dividir");
        }
        return R;
    }
}
