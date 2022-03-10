import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Enemy extends JFrame{

    GridBagConstraints constraints = new GridBagConstraints();
    private Header headerProject;
    private JPanel panelBarcos,panelMiTablero,panelTableroPc,panelTableroPc2, MiBase, baseEnemiga;
    private  ImageIcon image;
    private JButton ayuda,salir,tableroPc,quitarTablero;
    private JButton [][] btEnemy;
    private Escucha escucha;

    public GUI_Enemy(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        // this.setPreferredSize(new Dimension(1095,528));
        this.setBackground(new Color(255,255,255));
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void llenarBase(){
        int x=10,y=10;
        btEnemy=new JButton[10][10];
        for (int j=0;j<btEnemy.length;j++){
            for (int i=0;i<btEnemy[j].length;i++){
                btEnemy[i][j]=new JButton();
                btEnemy[i][j].setBackground(Color.cyan);
                btEnemy[i][j].setBounds(x,y,35,33);
                baseEnemiga.add(btEnemy[i][j],BorderLayout.CENTER);
                x+=35;
            }
            x=10;
            y+=33;
        }
    }
    private void initGUI(){
        escucha=new Escucha();
        this.getContentPane().setLayout(new GridBagLayout());
        Container contentPane = this.getContentPane();
        baseEnemiga =new JPanel();
        baseEnemiga.setLayout(null);
        baseEnemiga.setPreferredSize(new Dimension(370,350));
        baseEnemiga.setBackground(Color.BLUE);

        headerProject = new Header("Tablero Enemigo", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=4;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        salir = new JButton(" X ");

        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        salir.addActionListener(escucha);
        this.add(salir,constraints);

        panelMiTablero=new JPanel();
        panelMiTablero.setPreferredSize(new Dimension(440, 400));
        panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"MAPA",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        llenarBase();
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


