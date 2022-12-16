/import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class gameFrame extends JFrame{
    ImageIcon logo = new ImageIcon(new ImageIcon("C:\\Users\\duama\\Documents\\Linear Relations BattleShip\\png-transparent-battleship-board-game-the-game-of-life-logo-game-logo-miscellaneous-game-text.png").getImage().getScaledInstance(350, 100, Image.SCALE_SMOOTH));
    ImageIcon background = new ImageIcon("C:\\Users\\duama\\Documents\\Linear Relations BattleShip\\Background.jpg");
    JButton play[][] = new JButton[10][10];
    JLabel logoLabel = new JLabel(logo);
    JLabel equationLabel = new JLabel("y = — x + ");
    JLabel errorLabel = new JLabel("Error, please try again");
    JTextField riseTextField = new JTextField();
    JTextField runTextField = new JTextField();
    JTextField yIntTextField = new JTextField();
    int rotate = 0;
    int rise, run, yInt;
    int shipCount;
    Color player = Color.GREEN;
    java.util.Timer timer = new java.util.Timer();
    gameFrame() {
        setVisible(true);
        setSize(1000, 750);
        setResizable(false);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Battleship");
        logoLabel.setBounds(325, 0, 350, 100);
        setContentPane(new JLabel(background));
        add(logoLabel);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_R){
                    rotate++;
                    for (int i = 0; i < play.length; i ++) {
                        for (int j = 0; j < play[0].length; j++) {
                            if(play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                        }
                    }
                }
            }
        });
        equationLabel.setBounds(600, 250, 300, 150);
        equationLabel.setFont(new Font("Verdana", Font.BOLD, 50));
        equationLabel.setForeground(Color.WHITE);
        add(equationLabel);

        riseTextField.setBounds(710, 270, 50, 50);
        riseTextField.setFocusable(false);
        riseTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    confirmEquation();
                }
            }
        });
        add(riseTextField);

        runTextField.setBounds(710, 340, 50, 50);
        runTextField.setFocusable(false);
        runTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    confirmEquation();
                }
            }
        });
        add(runTextField);

        yIntTextField.setBounds(880, 305, 50, 50);
        yIntTextField.setFocusable(false);
        yIntTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    confirmEquation();
                }
            }
        });
        add(yIntTextField);


        errorLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        errorLabel.setBounds(575, 130, 400, 150);
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);


        
        for (int i = 0; i < play.length; i ++) {
            for (int j = 0; j < play[0].length; j++) {
                play[i][j] = new JButton();
                play[i][j].setBounds(i * 50 + 50, j * 50 + 125, 50, 50);
                play[i][j].setFocusable(false);
                play[i][j].setVisible(true);
                play[i][j].setFont(new Font("Verdana", Font.BOLD, 0));               
                buttonPressed(i, j);
                play[i][j].setBackground(Color.lightGray);
                play[i][j].setText("");
                buttonSet(i, j);
                add(play[i][j]);  
            }
        }
        repaint();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
            }
          };
        timer.scheduleAtFixedRate(task, 0, 1);
    }


    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Stroke stroke = new BasicStroke(4f);
        g2d.setStroke(stroke);
        g2d.drawLine(307, 150, 307, 665);
        g2d.drawLine(50, 408, 565, 408);
    }



    public void buttonSet(int i, int j) {
        
        play[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (shipCount != 6) {
                    repaint();
                    if (rotate % 2 == 0) {
                        if (j < play.length - 2) {
                            if (play[i][j].getText().equals("") && play[i][j + 1].getText().equals("") && play[i][j + 2].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i][j + 1].setBackground(player);
                                play[i][j + 2].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i][j + 1].setBackground(Color.RED);
                                play[i][j + 2].setBackground(Color.RED);
                            }
                        }
                        if (j == play.length - 2) {
                            if (play[i][j].getText().equals("") && play[i][j + 1].getText().equals("") && play[i][j - 1].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i][j + 1].setBackground(player);
                                play[i][j - 1].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i][j + 1].setBackground(Color.RED);
                                play[i][j - 1].setBackground(Color.RED);
                            }
                        }
                        if (j == play.length - 1) {
                            if (play[i][j].getText().equals("") && play[i][j -2].getText().equals("") && play[i][j - 1].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i][j - 2].setBackground(player);
                                play[i][j - 1].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i][j - 2].setBackground(Color.RED);
                                play[i][j - 1].setBackground(Color.RED);
                            }
                        }
                    }
                    if (rotate % 2 == 1) {
                        if (i < play.length - 2) {
                            if (play[i][j].getText().equals("") && play[i + 1][j].getText().equals("") && play[i + 2][j].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i + 1][j].setBackground(player);
                                play[i + 2][j].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i + 1][j].setBackground(Color.RED);
                                play[i + 2][j].setBackground(Color.RED);
                            }
                        }
                        if (i == play.length - 2) {
                            if (play[i][j].getText().equals("") && play[i + 1][j].getText().equals("") && play[i - 1][j].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i + 1][j].setBackground(player);
                                play[i - 1][j].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i + 1][j].setBackground(Color.RED);
                                play[i - 1][j].setBackground(Color.RED);
                            }
                        }
                        if (i == play.length - 1) {
                            if (play[i][j].getText().equals("") && play[i - 2][j].getText().equals("") && play[i - 1][j].getText().equals("")){
                                play[i][j].setBackground(player);
                                play[i - 2][j].setBackground(player);
                                play[i - 1][j].setBackground(player);
                            }
                            else {
                                play[i][j].setBackground(Color.RED);
                                play[i - 2][j].setBackground(Color.RED);
                                play[i - 1][j].setBackground(Color.RED);
                            }
                        }
                    }
                }
            }
        
            public void mouseExited(java.awt.event.MouseEvent evt) {
                try {
                    
                    if (rotate % 2 == 0) {
                        if (j < play.length - 2) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i][j + 1].getText().equals("")) play[i][j + 1].setBackground(Color.lightGray);
                            if (play[i][j + 2].getText().equals("")) play[i][j + 2].setBackground(Color.lightGray);
                        }
                        if (j == play.length - 2) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i][j + 1].getText().equals("")) play[i][j + 1].setBackground(Color.lightGray);
                            if (play[i][j - 1].getText().equals("")) play[i][j - 1].setBackground(Color.lightGray);
                        }
                        if (j == play.length - 1) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i][j - 1].getText().equals("")) play[i][j-  1].setBackground(Color.lightGray);
                            if (play[i][j - 2].getText().equals("")) play[i][j - 2].setBackground(Color.lightGray);
                        }
                    }
                    if (rotate % 2 == 1) {
                        if (i < play.length - 2) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i + 1][j].getText().equals("")) play[i + 1][j].setBackground(Color.lightGray);
                            if (play[i + 2][j].getText().equals("")) play[i + 2][j].setBackground(Color.lightGray);
                        }
                        if (i == play.length - 2) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i + 1][j].getText().equals("")) play[i + 1][j].setBackground(Color.lightGray);
                            if (play[i - 1][j].getText().equals("")) play[i - 1][j].setBackground(Color.lightGray);
                        }
                        if (i == play.length - 1) {
                            if (play[i][j].getText().equals("")) play[i][j].setBackground(Color.lightGray);
                            if (play[i - 1][j].getText().equals("")) play[i - 1][j].setBackground(Color.lightGray);
                            if (play[i - 2][j].getText().equals("")) play[i - 2][j].setBackground(Color.lightGray);
                        }
                    }




                    if (shipCount != 6) {
                        repaint();
                        for (int i = 0; i < play.length; i ++) {
                            for (int j = 0; j < play[0].length; j++) {
                                if(!play[i][j].getText().equals("")) {
                                    if (Integer.valueOf(play[i][j].getText()) <= 3) play[i][j].setBackground(Color.GREEN);
                                    else play[i][j].setBackground(Color.BLUE);
                                } 
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e){}
            }
        });
    }




    public void buttonPressed(int i, int j) {
        play[i][j].addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isError = false;
                for (int a = 0; a < play.length; a++) {
                    for (int b = 0; b < play[0].length; b++) {
                        if (play[a][b].getBackground() == Color.RED) isError = true;
                    }
                }
                if (!isError) {
                    shipCount++;
                    if (rotate % 2 == 0) {
                        if (j < play.length - 2) {
                            play[i][j].setBackground(player);
                            play[i][j + 1].setBackground(player);
                            play[i][j + 2].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i][j + 1].setText(String.valueOf(shipCount));
                            play[i][j + 2].setText(String.valueOf(shipCount));
                        }
                        if (j == play.length - 2) {
                            play[i][j].setBackground(player);
                            play[i][j + 1].setBackground(player);
                            play[i][j - 1].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i][j + 1].setText(String.valueOf(shipCount));
                            play[i][j - 1].setText(String.valueOf(shipCount));
                        }
                        if (j == play.length - 1) {
                            play[i][j].setBackground(player);
                            play[i][j - 2].setBackground(player);
                            play[i][j - 1].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i][j - 1].setText(String.valueOf(shipCount));
                            play[i][j - 2].setText(String.valueOf(shipCount));
                        }
                    }
                    if (rotate % 2 == 1) {
                        if (i < play.length - 2) {
                            play[i][j].setBackground(player);
                            play[i + 1][j].setBackground(player);
                            play[i + 2][j].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i + 1][j].setText(String.valueOf(shipCount));
                            play[i + 2][j].setText(String.valueOf(shipCount));
                        }
                        if (i == play.length - 2) {
                            play[i][j].setBackground(player);
                            play[i + 1][j].setBackground(player);
                            play[i - 1][j].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i + 1][j].setText(String.valueOf(shipCount));
                            play[i - 1][j].setText(String.valueOf(shipCount));
                        }
                        if (i == play.length - 1) {
                            play[i][j].setBackground(player);
                            play[i - 2][j].setBackground(player);
                            play[i - 1][j].setBackground(player);
                            play[i][j].setText(String.valueOf(shipCount));
                            play[i - 2][j].setText(String.valueOf(shipCount));
                            play[i - 1][j].setText(String.valueOf(shipCount));
                        }
                    }
                    if (shipCount == 3) {
                        player = Color.BLUE;
                    }
                    if (shipCount == 6) {
                        for (int a = 0; a < play.length; a++) {
                            for (int b = 0; b < play[0].length; b++){
                                play[a][b].setEnabled(false);
                            }
                        }
                        riseTextField.setFocusable(true);
                        runTextField.setFocusable(true);
                        yIntTextField.setFocusable(true);
                        repaint();
                    }
                    
            }
        }
        });
    }

    public void confirmEquation() {
        try {
            errorLabel.setVisible(false);
            if (yIntTextField.getText().equals("")) yIntTextField.setText("0");
            if (riseTextField.getText().equals("")) riseTextField.setText("0");
            if (runTextField.getText().equals("")) runTextField.setText("0");
            rise = Integer.valueOf(riseTextField.getText());
            run = Integer.valueOf(runTextField.getText());
            yInt = Integer.valueOf(yIntTextField.getText());
            riseTextField.setText("");
            runTextField.setText("");
            yIntTextField.setText("");
        } catch (NumberFormatException e){
            errorLabel.setVisible(true);
        }
    }
}