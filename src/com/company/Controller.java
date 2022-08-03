package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

// MVC based controller class for GUI game version
public class Controller implements ActionListener {
    Model model;        // Model class Member
    GUIview gui;        // GUIview class Member
    KeyListener keylistener;    // keylistener
    // static int for players tries and current grid index
    static int Rindex = 0, Cindex = 0;
    static int []keys = new int[5];

    // constructor for Controller class
    Controller(GUIview g) {
        gui = g;  // assign g object of Type GUIview to gui object
        model = new Model();  // craeting/ initializig object/instance of model class
        gui.keylistener = new KeyListener() {     // initializing keylistener and defining/overiding repective function
            @Override
            public void keyTyped(KeyEvent e) {
                // do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {    // cheking if key pressed
                int in = Arrays.stream(gui.keys).toList().indexOf(String.valueOf(e.getKeyChar()).toUpperCase());
                if (in != -1) {
                    gui.keyBtns[in].doClick();
                } else {
                    if (e.getKeyCode() == 10) {
                        gui.keyBtns[19].doClick();
                    } else if (e.getKeyCode() == 8) {
                        gui.keyBtns[27].doClick();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //do Nothing
            }
        };
    }


    /*
        function defination of changeColor function
        it will change the keys and TextField Color
     */
    boolean changeColor(String str) {
        int[] arr = model.checkWords(str);
        if (model.flage1) {      // checking the flage1
            boolean flage = false;
            for (int i = 0; i < 5; i++) {
                if (arr[i] == 0 || arr[i] == 1) {
                    flage = false;
                    break;
                }
                flage = true;
            }
            if (flage) {
                JOptionPane msg = new JOptionPane();
                msg.showMessageDialog(gui.frame, "No Match!!");
                return false;
            }
        }

        // changing the colors relatively
        for (int i = 0; i < 5; i++) {
            if (arr[i] == -1) {
                gui.txtfield[Rindex][i].setBackground(Color.gray);
                gui.keyBtns[keys[i]].setBackground(Color.gray);
                keys[i] = -1;
            } else if (arr[i] == 0) {
                gui.txtfield[Rindex][i].setBackground(Color.green);
                gui.keyBtns[keys[i]].setBackground(Color.green);
                keys[i] = -1;
            } else {
                gui.txtfield[Rindex][i].setBackground(new Color(255,204,51));
                gui.keyBtns[keys[i]].setBackground(new Color(255,204,51));
                keys[i] = -1;
            }
        }
        return true;
    }


    // getter function to get
    // the most recent entered word by the player
    String getEnteredWord() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            str += gui.txtfield[Rindex][i].getText();
        }
        return str;
    }

    // function to restart the game
    void restart() {
        for (int i = 0; i <= Rindex; i++) {
            for (int j = 0; j < 5; j++) {
                int in = Arrays.stream(gui.keys).toList().indexOf(String.valueOf(gui.txtfield[i][j].getText()));
                gui.keyBtns[in].setBackground(new Color(211, 214, 218));
                gui.txtfield[i][j].setText("\0");
                gui.txtfield[i][j].setBackground(Color.white);
            }
        }
        model.loadFiles();
        Rindex = 0;
        Cindex = 0;
        gui.startbtn.setEnabled(false);
    }

    // Implementing the listener function
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();    // storing the action command
        if (command.equals("ENTER")) {    // cheing if command equals to 'ENTER'
            if (Cindex == 5) {      // checking is the user Entered all the 5 letters
                if (changeColor(getEnteredWord())) {
                    if (model.isWin(getEnteredWord()) || Rindex == 5) {     // checking is Player won or lost
                        gui.startbtn.setEnabled(true);      // setting start button enable
                        gui.startbtn.setText("ReStart");
                    } else {
                        Rindex++;       // adding tries done by player
                    }
                    Cindex = 0;
                }
            }


        } else if (command.equals("✕")) {      // checking if player requested for backspace
            if (Cindex > 0) {       // checking is the textfield are empty or not
                gui.txtfield[Rindex][--Cindex].setText("\0");
            }
        } else if (command.equals("Start")) {       // if player ask for restarting game
            restart();
        }
        else if (command.equals("ReStart")) {       // if player ask for restarting game
            restart();
        }
        else if (Cindex == 5) {     // do nothing if player did or click a
            // nything after entering 5 letter except backspace and enter
            // do nothing
        } else {           // else assign/ set text to next textfield
            keys[Cindex] = Arrays.stream(gui.keys).toList().indexOf(command);
            gui.txtfield[Rindex][Cindex++].setText(command);

        }


    }

    public static void main(String[] args) {
        GUIview GUI = new GUIview();
    }


}
