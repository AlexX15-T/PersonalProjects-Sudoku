import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

class GraphicInterface extends JFrame implements ActionListener {

    Container con;
    JTextField t[][] = new JTextField[9][9];
    JMenuBar mbar;
    JMenu Action;
    JMenuItem Submit, Exit, Reset, Tips;

    int[][] cp = new int[9][9];
    int[][] ip = new int[9][9];


    GraphicInterface() {
        super(" Sudoku ");
        setSize(500, 500);

        con = getContentPane();

        con.setLayout(new GridLayout(9, 9));

        Mylogic ob1 = new Mylogic();
        ob1.complet_puzzle();
        ob1.puzzle();


        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                t[i][j] = new JTextField("" + ip[i][j]);
                t[i][j].setFont(new Font("ARIALBD", Font.BOLD, 20));
                t[i][j].setForeground(Color.BLUE);

                if (ip[i][j] == 0) {
                    t[i][j].setText("");
                    t[i][j].addActionListener(this);
                    t[i][j].setBackground(new Color(169,169,169));
                }

                else
                {
                    t[i][j].setEditable(false);
                    t[i][j].setBackground(Color.GRAY);
                }

                AbstractDocument d = (AbstractDocument) t[i][j].getDocument();

                d.setDocumentFilter( new MyDocumentFilter());

                t[i][j].setFont(new Font("ARIALBD", Font.BOLD, 25));
                t[i][j].setHorizontalAlignment(JTextField.CENTER);
                con.add(t[i][j]);


            }

        mbar = new JMenuBar();
        setJMenuBar(mbar);

        Tips = new JMenuItem("Tips");
        Action = new JMenu("Action");
        Reset = new JMenuItem("Reset");
        Submit = new JMenuItem("Submit");
        Exit = new JMenuItem("Exit");


        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int r = 0;

                for (int i = 0; i < 9; i++)
                    for (int j = 0; j < 9; j++)
                        if (cp[i][j] != Integer.parseInt(t[i][j].getText())) {
                            r = 1;
                            break;
                        }

                for (int i = 0; i < 9; i++) {
                    System.out.println();
                    for (int j = 0; j < 9; j++) {
                        System.out.print(cp[i][j]);
                        System.out.print(Integer.parseInt(t[i][j].getText()) + "  ");
                    }
                }

                if (r == 0) {
                    JOptionPane.showMessageDialog(GraphicInterface.this, "You won the Game");
                    System.out.println("You won the Game");
                }

                else {
                    System.out.println("You lose the Game");
                    JOptionPane.showMessageDialog(GraphicInterface.this, "You lose the Game");
                }

            }

        });

        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        Reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == Reset)
                {
                    dispose();
                    GraphicInterface game = new GraphicInterface();
                    game.setVisible(true);
                }
            }
        });

        Tips.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GraphicInterface.this,
                        "5 Tips for Sudoku Beginners\n" +
                                "\n" +
                                "  1.  Notice any definites. Definites are numbers that are definitely going to be in a certain box. ...\n" +
                                "  2.  Do this for all the numbers. ...\n" +
                                "  3.  When you get stuck, use trial and error. ...\n" +
                                "  4.  Start easy. ...\n" +
                                "  5.  Be patient.",
                        "Tips", JOptionPane.PLAIN_MESSAGE);
            }
        });

        Action.add(Reset);
        Action.add(Submit);
        Action.addSeparator();
        Action.add(Exit);
        mbar.add(Action);
        mbar.add(Tips);

        DrawLines();

        show();

        MyWindowAdapter mwa = new MyWindowAdapter();
        addWindowListener(mwa);

    }

    class Mylogic extends Logic {
        void complet_puzzle() {

            cp = save();

            for(int i=0;i<9;i++) { for(int j=0;j<9;j++)
                System.out.print(cp[i][j]+" ");
                System.out.println();
                }
            System.out.println();
        }


        void puzzle() {
            ip = hide();
        }

    }

    class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    void DrawLines()
    {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                t[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        for(int i = 0; i < 9; i++) {
            t[i][2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
            t[i][5].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 4, Color.BLACK));
            t[2][i].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
            t[5][i].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 1, Color.BLACK));
        }

        t[2][2].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        t[2][5].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        t[5][2].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));
        t[5][5].setBorder(BorderFactory.createMatteBorder(1, 1, 4, 4, Color.BLACK));


    }


    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == t[i][j]) {
                    String s = t[i][j].getText();
                    int c = Integer.parseInt(s);
                    if (0 < c && 10 > c) {
                        t[i][j].setText(s);
                        t[i][j].setFont(new Font("ARIALBD", Font.BOLD, 25));
                        t[i][j].setForeground(new Color(100,50,100));
                    }

                    break;
                }
            }
    }


    void recall() {
        GraphicInterface newGame = new GraphicInterface();
    }

}