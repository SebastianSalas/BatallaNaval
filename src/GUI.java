import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @author Jhon Alexander Valencia Hilamo jhon.hilamo@correounivalle.edu.co
 * @author Janiert Sebastian Salas Castillo janiert.salas@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
  GridBagConstraints constraints = new GridBagConstraints();
  private Header headerProject;
  private JPanel panelBarcos,panelMiTablero,panelTableroPc,panelTableroPc2,base,enemigo;
  private Escucha escucha;
  private JButton ayuda,salir,tableroPc,quitarTablero;

  /**
   * Constructor of GUI class
   */
  public GUI(){
    initGUI();

    //Default JFrame configuration
    this.setTitle("The Title app");
    //this.setPreferredSize(new Dimension(1095,528));
    this.setBackground(new Color(255,255,255));
    this.setUndecorated(true);
    this.pack();
    this.setResizable(true);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * This method is used to set up the default JComponent Configuration,
   * create Listener and control Objects used for the GUI class
   */
  private void initGUI() {
    this.getContentPane().setLayout(new GridBagLayout());


    escucha =new Escucha();

    headerProject = new Header("Batalla Naval", Color.BLACK);
    constraints.gridx=0;
    constraints.gridy=0;
    constraints.gridwidth=4;
    constraints.fill=GridBagConstraints.HORIZONTAL;
    this.add(headerProject,constraints);

    ayuda = new JButton(" ? ");
    ayuda.addActionListener(escucha);
    constraints.gridx=0;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_START;
    this.add(ayuda,constraints);

    salir = new JButton(" X ");
    salir.addActionListener(escucha);
    constraints.gridx=2;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_END;
    this.add(salir,constraints);

    panelBarcos=new JPanel();
    panelBarcos.setPreferredSize(new Dimension(165, 315));
    panelBarcos.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0,0,0),2,true),"Mis Barcos", TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 14)));
    constraints.gridx=0;
    constraints.gridy=2;
    constraints.gridwidth=1;
    //constraints.gridheight=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.PAGE_START;
    add(panelBarcos,constraints);

    tableroPc = new JButton("Mostrar Tablero");
    tableroPc.addActionListener(escucha);
    constraints.gridx=2;
    constraints.gridy=3;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.FIRST_LINE_START;
    this.add(tableroPc,constraints);

    quitarTablero = new JButton("Ocultar Tablero");
    quitarTablero.addActionListener(escucha);
    constraints.gridx=2;
    constraints.gridy=3;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.FIRST_LINE_END;
    this.add(quitarTablero,constraints);

    panelMiTablero=new JPanel();
    panelMiTablero.setPreferredSize(new Dimension(440, 400));
    panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"BASE",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
    constraints.gridx=1;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    add(panelMiTablero,constraints);

    panelTableroPc=new JPanel();
    panelTableroPc.setPreferredSize(new Dimension(440, 400));
    panelTableroPc.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"MAPA",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
    constraints.gridx=2;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    add(panelTableroPc,constraints);

  }

  /**
   * Main process of the Java program
   * @param args Object used in order to send input data from command line when
   *             the program is execute by console.
   */
  public static void main(String[] args){
    EventQueue.invokeLater(() -> {
      GUI miProjectGUI = new GUI();
    });
  }

  /**
   * inner class that extends an Adapter Class or implements Listeners used by GUI class
   */
  private class Escucha implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==salir){
        System.exit(0);
      }

      if(e.getSource()==tableroPc){
        JOptionPane.showMessageDialog(null,"Mostrar");
        panelTableroPc2=new JPanel();
        panelTableroPc2.setPreferredSize(new Dimension(440, 448));
        panelTableroPc2.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0),3,true),"MAPA",TitledBorder.CENTER,TitledBorder.TOP,new Font("Tahoma", 1, 15)));
        constraints.gridx=1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelTableroPc2,constraints);
      }
    }
  }
}
