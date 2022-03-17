import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used for ...
 *
 * @author Jhon Alexander Valencia Hilamo jhon.hilamo@correounivalle.edu.co
 * @author Janiert Sebastian Salas Castillo janiert.salas@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame implements Runnable {
  GridBagConstraints constraints = new GridBagConstraints();
  private String MENSAJE_INICIO = "                                                                  BIENVENIDOS A " +
          "BATALLA NAVAL          \n" + "\n" +
          "Este es un juego de estrategia, en el cual es objetivo es hundir todos los barcos de la flota enemiga.\n" +
          "Tienes a tu dispocision 10 barcos, 1 portaaviones, 2 submarinos, 3 destructores y 4 fragatas los cuales\n" +
          "ocupan 4, 3, 2 y 1 espacios respectivamente y los puedes ubicar tanto horizontales como verticales.\n" +
          "Cuando dispares al enemigo e impactes en el agua aparecera una x, si tocas uno de los barcos de 2 o mas " +
          "casillas\n" +
          "solo se destruira esa parte del barco y tendras que seguir lanzando para hundirlo, si tocas una fragata la" +
          " hundiras\n" +
          "y si destruyes todas las partes de un barco este contara como hunidido, o sea sera eliminado.\n" +
          "\n" +
          "El objetivo es hundir todos los barcos enemigos antes de que el enemigo hunda los tuyos, cada que hagas un" +
          " ataque\n" +
          "el enemigo lo devolvera para hundir tus barcos";
  private Header headerProject;
  private JLabel tiempo,mBarco;
  private JPanel panelBarcos, panelMiTablero, panelTableroPc, base, enemigo;
  private Escucha escucha;
  private Escucha2 escuchaMouseListener;
  private JButton ayuda, salir, tableroPc, empezar, botonP;
  private JButton[][] btBase;
  private JButton[][] btEnemy;
  private ImageIcon image;
  public static int barco, por = 1, sub = 2, des = 3, fra = 4;
  public static Boolean flag = true;
  private int fila,col;
  private boolean p, s, d, f, cronometroActivo;
  private Thread hilo;
  private ArrayList<JButton> btActivos = new ArrayList<JButton>();
  private ArrayList<JButton> btActivosM = new ArrayList<JButton>();

  /**
   * Constructor of GUI class
   */
  public GUI() {
    initGUI();
    barco = 4;
    p = true;
    //Default JFrame configuration
    this.setTitle("The Title app");
    //this.setPreferredSize(new Dimension(1095,528));
    this.setBackground(new Color(255, 255, 255));
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

  public void llenarBase() {
    int x = 10, y = 10;
    btBase = new JButton[10][10];
    for (int j = 0; j < btBase.length; j++) {
      for (int i = 0; i < btBase[j].length; i++) {
        btBase[i][j] = new JButton();
        btBase[i][j].setBackground(Color.CYAN);
        btBase[i][j].setBounds(x, y, 35, 33);
        btBase[i][j].addMouseListener(escuchaMouseListener);
        base.add(btBase[i][j], BorderLayout.CENTER);
        x += 35;
      }
      x = 10;
      y += 33;
    }
  }

  public void llenarEnemy() {
    int x = 10, y = 10;
    btEnemy = new JButton[10][10];
    for (int j = 0; j < btEnemy.length; j++) {
      for (int i = 0; i < btEnemy[j].length; i++) {
        btEnemy[i][j] = new JButton();
        btEnemy[i][j].setBackground(Color.CYAN);
        btEnemy[i][j].setBounds(x, y, 35, 33);
        enemigo.add(btEnemy[i][j], BorderLayout.SOUTH);
        x += 35;
      }
      x = 10;
      y += 33;
    }
  }

  public void iniciarCronometro() {
    cronometroActivo = true;
    hilo = new Thread(this);
    hilo.start();
  }


  private void initGUI() {
    this.getContentPane().setLayout(new GridBagLayout());

    tiempo = new JLabel("00:00");
    tiempo.setFont(new Font("Tahoma", 1, 25));
    tiempo.setHorizontalAlignment(JLabel.CENTER);
    tiempo.setForeground(Color.BLACK);
    tiempo.setOpaque(true);
    constraints.gridx = 0;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.PAGE_START;
    add(tiempo, constraints);

    base = new JPanel();
    base.setLayout(null);
    base.setPreferredSize(new Dimension(370, 350));
    base.setBackground(new Color(51, 51, 255));
    enemigo = new JPanel();
    enemigo.setLayout(null);
    enemigo.setPreferredSize(new Dimension(370, 350));
    enemigo.setBackground(new Color(51, 51, 255));
    escucha = new Escucha();
    escuchaMouseListener = new Escucha2();

    headerProject = new Header("Batalla Naval", Color.BLACK);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 4;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    this.add(headerProject, constraints);

    ayuda = new JButton(" ? ");
    ayuda.addActionListener(escucha);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.LINE_START;
    this.add(ayuda, constraints);

    salir = new JButton(" X ");
    salir.addActionListener(escucha);
    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.LINE_END;
    this.add(salir, constraints);



    panelBarcos = new JPanel();
    panelBarcos.setPreferredSize(new Dimension(165, 200));
    panelBarcos.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Mis Barcos",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", 1, 14)));
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    //constraints.gridheight=1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.CENTER;
    add(panelBarcos, constraints);
    mBarco=new JLabel();
    image = new ImageIcon(getClass().getResource("/resources/barcos.jpg"));
    mBarco.setIcon(image);
    panelBarcos.add(mBarco);


    tableroPc = new JButton("Mostrar Tablero");
    tableroPc.addActionListener(escucha);
    constraints.gridx = 2;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.LINE_END;
    this.add(tableroPc, constraints);

    empezar = new JButton("Empezar");
    empezar.addActionListener(escucha);
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.CENTER;
    this.add(empezar, constraints);

    panelMiTablero = new JPanel();
    panelMiTablero.setPreferredSize(new Dimension(440, 400));
    panelMiTablero.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "BASE",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", 1, 15)));
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.CENTER;
    llenarBase();
    panelMiTablero.add(base);
    add(panelMiTablero, constraints);

    panelTableroPc = new JPanel();
    panelTableroPc.setPreferredSize(new Dimension(440, 400));
    panelTableroPc.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "MAPA",
            TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", 1, 15)));
    constraints.gridx = 2;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.CENTER;
    llenarEnemy();
    panelTableroPc.add(enemigo);
    add(panelTableroPc, constraints);

  }



  /**
   * Main process of the Java program
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      GUI miProjectGUI = new GUI();
    });
  }

  public int cambiarTamBarco(int numero) {

    if (p == true) {

      this.barco = numero - 1;

      p = false;
      s = true;

    } else {
      if (s == true && sub != 0) {
        sub--;

        if (sub == 0) {
          this.barco = numero - 1;
          s = false;
          d = true;
        }

      } else {
        if (d == true && des != 0) {
          des--;
          if (des == 0) {
            this.barco = numero - 1;
            d = false;
            f = true;
          }
        } else {
          if (f == true && fra != 0) {
            fra--;
            if (fra == 0) {
              this.barco = numero - 1;
              f = false;
            }
          }
        }
      }
    }
    return barco;
  }

  public void colocarBarcos(){
    Random aleatorio= new Random();
    fila= aleatorio.nextInt(10);
    col= aleatorio.nextInt(10);


  }

  @Override
  public void run() {
    Integer minutos = 0, segundos = 0, milesimas = 0;

    String min = "", seg = "", mil = "";
    try {
      while (cronometroActivo) {
        Thread.sleep(4);
        milesimas += 4;
        if (milesimas == 1000) {
          milesimas = 0;
          segundos += 1;

          if (segundos == 60) {
            segundos = 0;
            minutos++;
          }
        }
        if (minutos < 10) min = "0" + minutos;
        else min = minutos.toString();
        if (segundos < 10) seg = "0" + segundos;
        else seg = segundos.toString();

        if (milesimas < 10) mil = "00" + milesimas;
        else if (milesimas < 100) mil = "0" + milesimas;
        tiempo.setText(min + ":" + seg);
      }
    } catch (Exception e) {

    }

  }

  public boolean verificarBarcoHorizontal(Object botonP) {
    boolean tieneBarco = false;

    for (int i = 0; i < btBase.length; i++) {
      for (int j = 0; j < btBase[i].length; j++) {
          if (i < 10 - barco && j >= 10 - barco) {
            if (botonP == btBase[i][j]) {
              for (int h = 0; h < barco; h++) {
              i++;
              if (btActivos.contains(botonP) || btActivos.contains(btBase[i][j])) {
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

    for (int i = 0; i < btBase.length; i++) {
      for (int j = 0; j < btBase[i].length; j++) {
        if (j < 10 - barco) {
          if (botonP == btBase[i][j]) {
            for (int h = 0; h < barco; h++) {
              j++;
              if (btActivos.contains(botonP) || btActivos.contains(btBase[i][j])) {
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

  /**
   * inner class that extends an Adapter Class or implements Listeners used by GUI class
   */
  private class Escucha implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == salir) {
        System.exit(0);
      }

      if (e.getSource() == ayuda) {
        JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
      }

      if (e.getSource() == tableroPc) {
        GUI_Enemy guiEnemy = new GUI_Enemy();
        guiEnemy.setVisible(true);
      }
      if (e.getSource() == empezar) {
        JOptionPane.showMessageDialog(null,"Colocando barcos enemigos...");
        iniciarCronometro();
      }

    }
  }

  private class Escucha2 implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getButton() == 1) {
        boolean tieneBarcoV, tieneBarcoH;

        tieneBarcoV = verificarBarcoVertical(e.getSource());
        tieneBarcoH = verificarBarcoHorizontal(e.getSource());

        if (tieneBarcoH == true || tieneBarcoV == true) {
          JOptionPane.showMessageDialog(null, "No puedes poner un barco sobre otro.");

        } else {
          if (barco == 1) {
            for (int h = 0; h < 1; h++) {
              for (int i = 0; i < btBase.length; i++) {
                for (int j = 0; j < btBase[i].length; j++) {
                  if (!(btActivos.contains(btBase[i][j]))) {
                    if (j < 10) {
                      if (e.getSource() == btBase[i][j]) {
                        btActivos.add(btBase[i][j]);

                      }
                    }
                  }
                }
              }
            }
            cambiarTamBarco(barco);
          } else {
            for (int h = 0; h < barco-1; h++) {
              for (int i = 0; i < btBase.length; i++) {
                for (int j = 0; j < btBase[i].length; j++) {
                  if (j < (10 - barco)+1) {
                    if (e.getSource() == btBase[i][j]) {
                      btActivos.add(btBase[i][j]);
                      j++;
                      btActivos.add(btBase[i][(h + j)]);
                    }
                  } else {
                    if (i < (10 - barco) + 1) {
                      if (e.getSource() == btBase[i][j]) {

                        btActivos.add(btBase[i][j]);
                        i++;

                        btActivos.add(btBase[(i + h)][j]);
                      }
                    } else {
                      if (e.getSource() == btBase[i][j]) {
                        btActivos.add(btBase[i][j]);
                        btActivos.add(btBase[(i - h) - 1][j]);
                      }
                    }
                  }
                }
              }
            }
            cambiarTamBarco(barco);
          }

        }
      } else {
        System.out.println("xd");
      }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

      if (barco == 0) {
        for (int h = 0; h < 1; h++) {
          for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
              if (!(btActivos.contains(btBase[i][j]))) {
                if (j < 10) {
                  if (e.getSource() == btBase[i][j]) {
                    image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                    btBase[i][j].setIcon(image);
                  }
                } else {

                }
              } else {
              }
            }
          }
        }
      } else {
        for (int h = 0; h < barco; h++) {
          for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
              if (!(btActivos.contains(btBase[i][j]))) {
                if (j < (10 - barco) + 1) {
                  if (e.getSource() == btBase[i][j]) {
                    image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                    btBase[i][j].setIcon(image);
                    j++;
                    btBase[i][(h + j) - 1].setIcon(image);
                  }
                } else {
                  if (i < (10 - barco) + 1) {
                    if (e.getSource() == btBase[i][j]) {
                      image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                      btBase[i][j].setIcon(image);
                      i++;
                      btBase[(i + h) - 1][j].setIcon(image);
                    }
                  } else {
                    if (e.getSource() == btBase[i][j]) {
                      image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                      btBase[i][j].setIcon(image);
                      btBase[i - h][j].setIcon(image);
                    }
                  }
                }
              }
            }
          }
        }
      }

    }


    @Override
    public void mouseExited(MouseEvent e) {
      if (barco == 0) {
        for (int h = 0; h < 1; h++) {
          for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
              if (j < 10 - barco) {
                if (e.getSource() == btBase[i][j]) {
                  if (btBase[i][j].isEnabled() == true) {
                    btBase[i][j].setIcon(null);
                  }

                }
              } else {

              }

            }
          }
        }
      } else {
        for (int h = 0; h < barco; h++) {
          for (int i = 0; i < btBase.length; i++) {
            for (int j = 0; j < btBase[i].length; j++) {
              if (!(btActivos.contains(btBase[i][j]))) {
                if (j < (10 - barco) + 1) {
                  if (e.getSource() == btBase[i][j]) {
                    image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                    btBase[i][j].setIcon(null);
                    j++;
                    btBase[i][(h + j) - 1].setIcon(null);
                  } else {

                  }
                } else {
                  if (i < (10 - barco) + 1) {
                    if (e.getSource() == btBase[i][j]) {
                      image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                      btBase[i][j].setIcon(null);
                      i++;
                      btBase[(i + h) - 1][j].setIcon(null);
                    }
                  } else {
                    if (e.getSource() == btBase[i][j]) {
                      image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
                      btBase[i][j].setIcon(null);
                      btBase[i - h][j].setIcon(null);
                    }
                  }
                }
              }
            }
          }
        }
      }

      for (JButton bt :
              btActivos) {
        image = new ImageIcon(getClass().getResource("/resources/battleship.png"));
        bt.setIcon(image);
      }

    }

    private class Escucha3 implements MouseMotionListener {

      @Override
      public void mouseDragged(MouseEvent e) {

      }

      @Override
      public void mouseMoved(MouseEvent e) {

      }
    }
  }
}

