package polvf1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import javax.swing.JOptionPane;

public final class Metodos {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Polvf1 pol1, pol2, pol3, polAux;

    public Metodos() throws IOException {
        crearPol1();
        crearPol2();
        mostrarMenu();
    }

    public void crearPol1() {
        int N;
        Random r = new Random();
        N = r.nextInt();
        N = (int) (Math.random() * 5 + 5);
        pol1 = new Polvf1("A", N);
        pol1.llenarPolinomio();
        System.out.println("");
        System.out.println(pol1);
    }

    public void crearPol2() {
        int N;
        N = (int) (Math.random() * 5 + 5);
        pol2 = new Polvf1("B", N);
        pol2.llenarPolinomio();
        System.out.println("");
        System.out.println(pol2);
    }

    public void insertarPol(int op) {
        String entrada;
        int coe = 0, exp = 0, dato = 0;
        do {
            entrada = JOptionPane.showInputDialog("ENTRE EL COEFICIENTE [-100 ,100 ]: "); // SE LE PIDE AL USUARIO QUE ENTRE N SE RECIBE COMO TEXTO
            try {

                dato = Integer.parseInt(entrada); // PARA CAMBIAR EL TEXTO ENTRADA A ENTERO
                if (dato < -100 || dato > 100) // SI EL VALOR DE N1 NO ES VALIDO SE MUESTRA EL MENSAJE
                {
                    JOptionPane.showMessageDialog(null, "EL COEFICIENTE DEBE ESTAR EN[-100 ,100 ]");
                } else {
                    coe = dato;
                }
            } catch (NumberFormatException e1) {
                if (entrada != null) {
                    JOptionPane.showMessageDialog(null, "EL COEFICIENTE DEBE ESTAR EN[-100 ,100 ]");
                }
            }

        } while ((dato < -100 || dato > 100) && entrada != null);
        if (entrada != null) {  //SI EL VALOR INGRESADO NO ES VALIDO SE PIDE NUEVAMENTE
            entrada = null;
            dato = 0;
            do {

                entrada = JOptionPane.showInputDialog("ENTRE EL EXPONENTE (1 - 15)");// PARA PEDIR EL VALOR DE N2 SE RECIBE COMO CADENA
                try {
                    dato = Integer.parseInt(entrada);  // SE COMBIERTE LA ENTRADA A ENTERO
                    if (dato < 0 || dato > 15) // SI EL VALOR DE M NO ES VALIDO SE INFORMA CON UN MENSAME
                    {
                        JOptionPane.showMessageDialog(null, "El EXPONENTE DEBE ESTAR EN (0 - 15)");
                    } else {
                        exp = dato;
                    }
                } catch (NumberFormatException e1) {
                    if (entrada != null) {
                        JOptionPane.showMessageDialog(null, "El EXPONENTE DEBE ESTAR EN (0 - 15)");
                    }
                }

            } while ((dato < 0 || dato > 15) && entrada != null); // SI EL VALOR DE M NO ES VALIDO SE PIDE NUEVAMENTE
            // COMO LOS VALORES DE M Y N SON VALIDOS SE CREA LA  MATRIZ
            // CON EL NUEVO TAMA�O N Y M 

            if (op == 4) {
                if (entrada != null) {
                    pol1.insertarTerm(coe, exp);
                    JOptionPane.showMessageDialog(null, "EL TERMINO  FUE INSERTADO EN LA ESTRUCTURA");
                    System.out.println(pol1);
                }
            }

            if (op == 5) {
                if (entrada != null) {
                    pol2.insertarTerm(coe, exp);
                    JOptionPane.showMessageDialog(null, "EL TERMINO  FUE INSERTADO EN LA ESTRUCTURA");
                    System.out.println(pol2);
                }
            }
        }

    }

    public void eliminarPol(int op) {
        if (op == 6) {
            polAux = pol1;
        }
        if (op == 7) {
            polAux = pol2;
        }
        int exp = 0;
        String entrada;
        int dato = 0;

        do {
            entrada = JOptionPane.showInputDialog("Entre el exponente del termino a eliminar [0-" + polAux.getGrado() + "]");
            try {
                dato = Integer.parseInt(entrada);
                if (dato < 0 || dato > polAux.getGrado()) {
                    JOptionPane.showMessageDialog(null, "El exponente debe estar en [0-" + polAux.getGrado() + "]");
                } else {
                    exp = dato;
                }
            } catch (NumberFormatException e) {
                if (entrada != null) {
                    JOptionPane.showMessageDialog(null, "El exponente debe estar en [0-" + polAux.getGrado() + "]");
                }
            }
        } while ((dato < 0 || dato > polAux.getGrado()) && entrada != null);
        if (entrada != null) {
            JOptionPane.showMessageDialog(null, "El termino con exponente = " + exp + "sera eliminado de la estruptura");
            polAux.eliminarTerm(exp);
            System.out.println(polAux);
        }
    }

    public void sumarPol() {
        pol3 = pol1.sumar(pol2);
        System.out.println(pol3);
    }

    public void multiplicarPol() {
        pol3 = pol1.mutiplicar(pol2);
        System.out.println(pol3);
    }

    public void dividirPol() {
        pol3 = pol1.dividir(pol2);
        System.out.println(pol3);
    }

    /**
     * Metodo caso para seleccionar las opciones que le pide el usuario
     *
     * @throws IOException
     */
    public void mostrarMenu() throws IOException {
        int op = 0;
        System.out.println("\n");
        System.out.println("Menu de Opciones");
        System.out.println("====================");
        System.out.println("1. Cambiar el Polinomio A");
        System.out.println("2. Cambiar el Polinomio B");
        System.out.println("3. Cambiar ambos polinomios");
        System.out.println("4. Insertar un termino el Polinomio A");
        System.out.println("5. Insertar un termino el Polinomio B");
        System.out.println("6. Eliminar un termino el Polinomio A");
        System.out.println("7. Eliminar un termino el Polinomio B");
        System.out.println("8. R = AX + BX ");
        System.out.println("9. R = AX * BX");
        System.out.println("10. R = AX / BX ");
        System.out.println("11. Salir");
        
        System.out.println("Opcion: ");
        op = Integer.parseInt(br.readLine());

        switch (op) {
            case 1:
                crearPol1();
                break;
            case 2:
                crearPol2();
                break;
            case 3:
                crearPol1();
                crearPol2();
                break;
            case 4:
                insertarPol(op);
                break;
            case 5:
                insertarPol(op);
                break;
            case 6:
                eliminarPol(op);
                break;
            case 7:
                eliminarPol(op);
                break;
            case 8:
                sumarPol();
                break;
            case 9:
                multiplicarPol();
                break;
            case 10:
                dividirPol();
                break;
            case 11:
                System.exit(0);
        }

        mostrarMenu();
    }

    public static void main(String[] args) throws IOException {
        Metodos mc = new Metodos();
    }

}
