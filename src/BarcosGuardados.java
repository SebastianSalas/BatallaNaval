import javax.swing.*;
import java.util.ArrayList;

public class BarcosGuardados {

    public boolean barcosColocados;
    public static  int barco=0;
    public static  ArrayList<JButton> botonesUsados=new ArrayList<JButton>();
    public BarcosGuardados(){

    }

    public void getBarcos(ArrayList enemigo){

        botonesUsados=enemigo;

    }

    public ArrayList setBarcos(){

        return botonesUsados;
    }

    public int numero(int barco){
        this.barco=barco;
        return this.barco;
    }

    public int setNumero(){
        return  barco;
    }

    public boolean setcolocados(boolean barcosColocados){
        if (barcosColocados){
            barco++;
            numero(barco);
        }
        return barcosColocados;
    }

    public boolean get(){

        return barcosColocados;
    }
}
