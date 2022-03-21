import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI_Enemy extends JFrame{

    GridBagConstraints constraints = new GridBagConstraints();
    private Header headerProject;
    private JPanel panelMiTablero, baseEnemiga;
    private ImageIcon image;
    private JButton salir;
    private static JButton [][] btEnemy=new JButton[10][10];
    private Escucha escucha;
    BarcosGuardados setInfo=new BarcosGuardados();
    private static boolean portaaviones,submarino,destructores,fragatas;
    private static int porta=1,subma=2,destruc=3,fragata=4;
    public static ArrayList<JButton> enemigosColocados =new ArrayList<JButton>();
    public static ArrayList<String> direcciones =new ArrayList<String>();
    private int nbarcos;
    public static int s,s2;
    private ImageIcon fondos;
    private JLabel fondo;

    public boolean verificarBarcoHorizontal(Object botonP) {
        boolean tieneBarco = false;

        for (int i = 0; i < btEnemy.length; i++) {
            for (int j = 0; j < btEnemy[i].length; j++) {
                if (i < 10 - nbarcos && j >= 10 - nbarcos) {
                    if (botonP == btEnemy[i][j]) {
                        for (int h = 0; h < nbarcos; h++) {
                            i++;
                            if (enemigosColocados.contains(botonP) || enemigosColocados.contains(btEnemy[i][j])) {
                                tieneBarco = true;
                                break;
                            } else {
                                tieneBarco = false;
                            }
                        }
                    }
                }
            }
        }
        return tieneBarco;
    }

    public boolean verificarBarcoVertical(Object botonP) {
        boolean tieneBarco = false;

        for (int i = 0; i < btEnemy.length; i++) {
            for (int j = 0; j < btEnemy[i].length; j++) {
                if (j < 10 - nbarcos) {
                    if (botonP == btEnemy[i][j]) {
                        for (int h = 0; h < nbarcos; h++) {
                            j++;
                            if (enemigosColocados.contains(botonP) || enemigosColocados.contains(btEnemy[i][j])) {
                                tieneBarco = true;
                                break;
                            } else {
                                tieneBarco = false;
                            }
                        }
                    }
                }
            }
        }
        return tieneBarco;
    }
    public GUI_Enemy()  {
        initGUI();
        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        fondos= new ImageIcon("src/resources/fondo.jpg");
        fondo= new JLabel(fondos);
        // this.setPreferredSize(new Dimension(1095,528));
        this.setBackground(new Color(255,255,255));
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        llenar();
        if(setInfo.setNumero()<1){
            colocarBarcosEnemigos();
        }else{

            mostrarbarcos();
        }
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public void llenar(){
        int x=10,y=10;
        btEnemy=new JButton[10][10];
        for (int j=0;j<btEnemy.length;j++){
            for (int i=0;i<btEnemy[j].length;i++){
                btEnemy[i][j]=new JButton();
                btEnemy[i][j].setBackground(new Color(0,204,255));
                btEnemy[i][j].setBounds(x,y,35,33);
                baseEnemiga.add(btEnemy[i][j],BorderLayout.CENTER);
                x+=35;
            }
            x=10;
            y+=33;
        }
    }

    private void mostrarbarcos(){

        for (int j = 0; j< btEnemy.length; j++){
            for (int i = 0; i< btEnemy[j].length; i++){

                if(direcciones.contains(btEnemy[i][j].toString())){


                    image = new ImageIcon(getClass().getResource("/resources/battleship.png"));

                    btEnemy[i][j].setIcon(image);
                }

            }
        }

    }

    public int tamBarco(int num){
        if(portaaviones==true){

            this.nbarcos=num-1;

            portaaviones=false;
            submarino=true;
            colocarBarcosEnemigos();
        }else{
            if(submarino==true&&subma!=0){
                subma--;

                if(subma==0){
                    this.nbarcos=num-1;
                    submarino=false;
                    destructores=true;

                }
                colocarBarcosEnemigos();
            }else{
                if(destructores==true&&destruc!=0){
                    destruc--;
                    if(destruc==0){
                        this.nbarcos=num-1;
                        destructores=false;
                        fragatas=true;

                    }
                    colocarBarcosEnemigos();
                }else{
                    if(fragatas==true&&fragata!=0){
                        fragata--;
                        if(fragata==0){
                            this.nbarcos=num-1;
                            fragatas=false;

                        }else{
                            colocarBarcosEnemigos();
                        }

                    }
                }
            }
        }
        return  nbarcos;
    }

    public void colocarBarcosEnemigos(){
        Random aleatorio=new Random();
        int x,y;
        x=aleatorio.nextInt(9);
        y=aleatorio.nextInt(9);

        boolean BarcoV, BarcoH;

        BarcoV = verificarBarcoVertical(btEnemy[x][y]);
        BarcoH = verificarBarcoHorizontal(btEnemy[x][y]);
        if(BarcoH == true || BarcoV == true){
            //System.out.println("sobrepesto");
            colocarBarcosEnemigos();
        }else{
            if(portaaviones==true){
                System.out.println("1");
                for (int h = 0; h < nbarcos; h++) {
                    for (int i = 0; i < btEnemy.length; i++) {
                        for (int j = 0; j < btEnemy[i].length; j++) {
                            if (j < (10 - nbarcos)+1) {
                                if (btEnemy[x][y] == btEnemy[i][j]) {


                                    if(enemigosColocados.contains(btEnemy[i][j])){

                                    }else{

                                        enemigosColocados.add(btEnemy[i][j]);
                                        direcciones.add(btEnemy[i][j].toString());

                                        s=i;
                                        s2=j;
                                    }


                                    j++;
                                    if(enemigosColocados.contains(btEnemy[i][(h + j)-1])){

                                    }else{
                                        enemigosColocados.add(btEnemy[i][(h + j)-1]);
                                        direcciones.add(btEnemy[i][(h + j)-1].toString());
                                        s=i;
                                        s2=j;
                                    }


                                }


                            }else{
                                if (i <(10 - nbarcos)+1 ) {
                                    if (btEnemy[x][y]== btEnemy[i][j]) {


                                        image = new ImageIcon(getClass().getResource("/resources/battleship.png"));


                                        if(enemigosColocados.contains(btEnemy[i][j])){

                                        }else{
                                            enemigosColocados.add(btEnemy[i][j]);
                                            direcciones.add(btEnemy[i][j].toString());
                                            s=i;
                                            s2=j;
                                        }
                                        i++;
                                        if(enemigosColocados.contains(btEnemy[(i + h)-1 ][j])){

                                        }else{
                                            enemigosColocados.add(btEnemy[(i + h)-1 ][j]);
                                            direcciones.add(btEnemy[(i + h)-1 ][j].toString());
                                            s=i;
                                            s2=j;
                                        }
                                    }
                                } else {
                                    if (btEnemy[x][y]== btEnemy[i][j]) {
                                        enemigosColocados.add(btEnemy[i][j]);

                                        enemigosColocados.add(btEnemy[i - h][j]);
                                    }
                                }
                            }

                        }
                    }
                }
            }else{
                if(submarino==true){
                    //System.out.println("2");
                    for (int h = 0; h < nbarcos; h++) {
                        for (int i = 0; i < btEnemy.length; i++) {
                            for (int j = 0; j < btEnemy[i].length; j++) {
                                if (j < (10 - nbarcos)+1) {
                                    if (btEnemy[x][y] == btEnemy[i][j]) {


                                        if(enemigosColocados.contains(btEnemy[i][j])){

                                        }else{

                                            enemigosColocados.add(btEnemy[i][j]);
                                            direcciones.add(btEnemy[i][j].toString());

                                            s=i;
                                            s2=j;
                                        }


                                        j++;
                                        if(enemigosColocados.contains(btEnemy[i][(h + j)-1])){

                                        }else{
                                            enemigosColocados.add(btEnemy[i][(h + j)-1]);
                                            direcciones.add(btEnemy[i][(h + j)-1].toString());
                                            s=i;
                                            s2=j;
                                        }


                                    }


                                }else{
                                    if (i <(10 - nbarcos)+1 ) {
                                        if (btEnemy[x][y]== btEnemy[i][j]) {




                                            if(enemigosColocados.contains(btEnemy[i][j])){

                                            }else{
                                                enemigosColocados.add(btEnemy[i][j]);
                                                direcciones.add(btEnemy[i][j].toString());
                                                s=i;
                                                s2=j;
                                            }
                                            i++;
                                            if(enemigosColocados.contains(btEnemy[(i + h)-1 ][j])){

                                            }else{
                                                enemigosColocados.add(btEnemy[(i + h)-1 ][j]);
                                                direcciones.add(btEnemy[(i + h)-1 ][j].toString());
                                                s=i;
                                                s2=j;
                                            }
                                        }
                                    } else {
                                        if (btEnemy[x][y]== btEnemy[i][j]) {
                                            enemigosColocados.add(btEnemy[i][j]);


                                            enemigosColocados.add(btEnemy[i - h][j]);
                                        }
                                    }
                                }

                            }
                        }
                    }
                }else{
                    if(destructores==true){
                        //System.out.println("3");
                        for (int h = 0; h < nbarcos; h++) {
                            for (int i = 0; i < btEnemy.length; i++) {
                                for (int j = 0; j < btEnemy[i].length; j++) {
                                    if (j < (10 - nbarcos)+1) {
                                        if (btEnemy[x][y] == btEnemy[i][j]) {


                                            if(enemigosColocados.contains(btEnemy[i][j])){

                                            }else{

                                                enemigosColocados.add(btEnemy[i][j]);
                                                direcciones.add(btEnemy[i][j].toString());

                                                s=i;
                                                s2=j;
                                            }


                                            j++;
                                            if(enemigosColocados.contains(btEnemy[i][(h + j)-1])){

                                            }else{
                                                enemigosColocados.add(btEnemy[i][(h + j)-1]);
                                                direcciones.add(btEnemy[i][(h + j)-1].toString());
                                                s=i;
                                                s2=j;
                                            }


                                        }


                                    }else{
                                        if (i <(10 - nbarcos)+1 ) {
                                            if (btEnemy[x][y]== btEnemy[i][j]) {




                                                if(enemigosColocados.contains(btEnemy[i][j])){

                                                }else{
                                                    enemigosColocados.add(btEnemy[i][j]);
                                                    direcciones.add(btEnemy[i][j].toString());
                                                    s=i;
                                                    s2=j;
                                                }
                                                i++;
                                                if(enemigosColocados.contains(btEnemy[(i + h)-1 ][j])){

                                                }else{
                                                    enemigosColocados.add(btEnemy[(i + h)-1 ][j]);
                                                    direcciones.add(btEnemy[(i + h)-1 ][j].toString());
                                                    s=i;
                                                    s2=j;
                                                }
                                            }
                                        } else {
                                            if (btEnemy[x][y]== btEnemy[i][j]) {
                                                enemigosColocados.add(btEnemy[i][j]);


                                                enemigosColocados.add(btEnemy[i - h][j]);
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }else{
                        if(fragatas==true){
                            //System.out.println("4");
                            for (int h = 0; h < nbarcos; h++) {
                                for (int i = 0; i < btEnemy.length; i++) {
                                    for (int j = 0; j < btEnemy[i].length; j++) {
                                        if (j < (10 - nbarcos)+1) {
                                            if (btEnemy[x][y] == btEnemy[i][j]) {


                                                if(enemigosColocados.contains(btEnemy[i][j])){

                                                }else{

                                                    enemigosColocados.add(btEnemy[i][j]);
                                                    direcciones.add(btEnemy[i][j].toString());

                                                    s=i;
                                                    s2=j;
                                                }


                                                j++;
                                                if(enemigosColocados.contains(btEnemy[i][(h + j)-1])){

                                                }else{
                                                    enemigosColocados.add(btEnemy[i][(h + j)-1]);
                                                    direcciones.add(btEnemy[i][(h + j)-1].toString());
                                                    s=i;
                                                    s2=j;
                                                }


                                            }


                                        }else{
                                            if (i <(10 - nbarcos)+1 ) {
                                                if (btEnemy[x][y]== btEnemy[i][j]) {


                                                    if(enemigosColocados.contains(btEnemy[i][j])){

                                                    }else{
                                                        enemigosColocados.add(btEnemy[i][j]);
                                                        direcciones.add(btEnemy[i][j].toString());
                                                        s=i;
                                                        s2=j;
                                                    }
                                                    i++;
                                                    if(enemigosColocados.contains(btEnemy[(i + h)-1 ][j])){

                                                    }else{
                                                        enemigosColocados.add(btEnemy[(i + h)-1 ][j]);
                                                        direcciones.add(btEnemy[(i + h)-1 ][j].toString());
                                                        btEnemy[(i + h)-1 ][j].setIcon(image);
                                                        s=i;
                                                        s2=j;
                                                    }
                                                }
                                            } else {
                                                if (btEnemy[x][y]== btEnemy[i][j]) {
                                                    enemigosColocados.add(btEnemy[i][j]);


                                                    enemigosColocados.add(btEnemy[i - h][j]);
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (JButton bt:
                    enemigosColocados) {
                image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                bt.setIcon(image);
            }

            tamBarco(nbarcos);
            setInfo.getBarcos(enemigosColocados);
        }




    }

    private void initGUI(){

        escucha=new Escucha();
        this.getContentPane().setLayout(new GridBagLayout());
        Container contentPane = this.getContentPane();
        baseEnemiga =new JPanel();
        baseEnemiga.setLayout(null);
        nbarcos=4;
        portaaviones=true;
        baseEnemiga.setPreferredSize(new Dimension(370,350));
        baseEnemiga.setBackground(new Color(255,255,255,0));

        headerProject = new Header("Tablero Enemigo", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=4;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        salir = new JButton( );
        image=new ImageIcon("src/resources/power.png");
        salir.setIcon(image);
        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusable(false);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        salir.addActionListener(escucha);
        this.add(salir,constraints);

        panelMiTablero=new JPanel();
        panelMiTablero.setPreferredSize(new Dimension(410, 390));
        panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(255,255,255),3,true),"MAPA",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15),new Color(255,255,255)));
        panelMiTablero.setBackground(new Color(255,255,255,0));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        panelMiTablero.add(baseEnemiga);
        add(panelMiTablero,constraints);
    }

    private class Escucha implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==salir){
                setVisible(false);
            }
        }
    }
}


