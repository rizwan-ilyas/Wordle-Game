package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

// view class of MVC based Gui version of game
public class GUIview {

    JFrame frame;
    JPanel mainPnl, gamePnl, headerPnl, keyPanel, keyRow1, keyRow2, keyRow3;
    JButton startbtn;
    JTextField[][] txtfield;
    JButton[] keyBtns;
    JLabel label;
    // string values for button of keys
    String[] keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "ENTER", "Z", "X", "C", "V", "B", "N", "M", "✕"};
    Controller controller;  // controller class member/variable
    KeyListener keylistener;

    // constructor for GUIview class
    public GUIview() {
        initGUI();  // initGUI function
    }

    // initGUI function defination that initialze all the components of GUI
    private void initGUI() {
        frame = new JFrame("Wordle Game");
        // GridBagLayout
        GridBagLayout gblayout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints c1 = new GridBagConstraints();

        // initializing the instance of Controller class
        controller = new Controller(this);


        frame.setLayout(new FlowLayout());

        // main panel
        mainPnl = new JPanel();
        mainPnl.setLayout(gblayout);
        mainPnl.setPreferredSize(new Dimension(900, 600));
        mainPnl.setBackground(Color.white);

        // header panel
        headerPnl = new JPanel();
        headerPnl.setLayout(new FlowLayout());
        headerPnl.setPreferredSize(new Dimension(900, 35));
        headerPnl.setBackground(Color.yellow);

        label = new JLabel("Wordle Game");
        label.setFont(new Font("SansSerif", Font.PLAIN, 25));

        startbtn = new JButton("Start");
        startbtn.setFont(new Font("SansSerif", Font.BOLD, 15));
        startbtn.setBackground(Color.green);
        startbtn.setFocusPainted(false);
        startbtn.setEnabled(false);
        startbtn.addActionListener(controller);
        headerPnl.add(label);
        headerPnl.add(startbtn);


        // game panel which include the textfieds
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(0, 5, 4, 4));
        gamePnl.setPreferredSize(new Dimension(400, 400));

        txtfield = new JTextField[6][5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                txtfield[i][j] = new JTextField();
                txtfield[i][j].setFont(new Font("SansSerif", Font.BOLD, 35));
                txtfield[i][j].setHorizontalAlignment(JTextField.CENTER);
                txtfield[i][j].setCaretColor(Color.white);
                txtfield[i][j].setFocusable(false);
                gamePnl.add(txtfield[i][j]);
            }
        }


        // key panel which includes the keyboard layout
        keyPanel = new JPanel(new FlowLayout());
        keyPanel.setPreferredSize(new Dimension(750, 150));

        keyBtns = new JButton[28];

        keyRow1 = new JPanel(new FlowLayout(0, 10, 1));
        keyRow1.setPreferredSize(new Dimension(730, 45));

        for (int i = 0; i < 10; i++) {
            keyBtns[i] = new JButton(keys[i]);
            keyBtns[i].setPreferredSize(new Dimension(60, 43));
            keyBtns[i].setFont(new Font("SansSerif", Font.BOLD, 30));
            keyBtns[i].setFocusPainted(false);
            keyBtns[i].setBackground(new Color(211, 214, 218));
            keyBtns[i].addActionListener(controller);   // adding action listener
            keyBtns[i].addKeyListener(keylistener);     // adding key listener
            keyRow1.add(keyBtns[i]);
        }
        keyBtns[1].setPreferredSize(new Dimension(65, 43));

        keyRow2 = new JPanel(new FlowLayout(1, 9, 1));
        keyRow2.setPreferredSize(new Dimension(690, 45));


        for (int i = 10; i < 19; i++) {
            keyBtns[i] = new JButton(keys[i]);
            keyBtns[i].setPreferredSize(new Dimension(60, 43));
            keyBtns[i].setFont(new Font("SansSerif", Font.BOLD, 30));
            keyBtns[i].setBackground(new Color(211, 214, 218));
            keyBtns[i].setFocusPainted(false);
            keyBtns[i].addActionListener(controller);
            keyRow2.add(keyBtns[i]);
        }

        keyRow3 = new JPanel(new FlowLayout(0, 9, 1));
        keyRow3.setPreferredSize(new Dimension(730, 45));

        for (int i = 19; i < 28; i++) {
            keyBtns[i] = new JButton(keys[i]);
            keyBtns[i].setPreferredSize(new Dimension(60, 43));
            keyBtns[i].setFont(new Font("SansSerif", Font.BOLD, 30));
            keyBtns[i].setBackground(new Color(211, 214, 218));
            keyBtns[i].setFocusPainted(false);
            keyBtns[i].addActionListener(controller);
            keyRow3.add(keyBtns[i]);
        }
        keyBtns[19].setPreferredSize(new Dimension(150, 43));


        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 10);
        keyPanel.add(keyRow1);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 10);
        keyPanel.add(keyRow2);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0, 0, 10);
        keyPanel.add(keyRow3);

        // adding header panel at specific location
        c1.gridx = 0;
        c1.gridy = 0;
        mainPnl.add(headerPnl, c1);

        // adding game panel at specific location
        c1.gridx = 0;
        c1.gridy = 1;
        c1.insets = new Insets(5, 0, 0, 0);
        mainPnl.add(gamePnl, c1);

        // adding key panel at specific location
        c1.gridx = 0;
        c1.gridy = 2;
        c1.insets = new Insets(7, 0, 0, 0);
        mainPnl.add(keyPanel, c1);

        // frame properties
        frame.add(mainPnl, BorderLayout.CENTER);
        frame.setSize(800, 650);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}


