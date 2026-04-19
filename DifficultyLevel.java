package snake_game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyLevel implements ActionListener {
    JFrame frame;
    JButton button;
    static int speed;
    JRadioButton easy;
    JRadioButton medium;
    JRadioButton hard;

    public DifficultyLevel(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(990,645);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);


        JLabel label = new JLabel("LeveL");
        label.setBounds(410,50,300,100);
        label.setForeground(Color.green);
        label.setFont(new Font("Arial",Font.BOLD,70));

        easy = new JRadioButton("Easy");
        medium = new JRadioButton("Medium");
        hard = new JRadioButton("Hard");

        easy.setBounds(100,300,200,70);
        easy.setFocusable(false);
        easy.setFont(new Font("MV Boli",Font.BOLD,40));
        easy.setForeground(Color.white);
        easy.setBackground(new Color(16, 177, 110));
        easy.setBorder(new LineBorder(Color.black,4));

        medium.setBounds(400,300,200,70);
        medium.setFocusable(false);
        medium.setFont(new Font("MV Boli", Font.BOLD,40));
        medium.setForeground(Color.WHITE);
        medium.setBackground(Color.blue);
        medium.setBorder(new LineBorder(Color.black,4));

        hard.setBounds(700,300,200,70);
        hard.setFocusable(false);
        hard.setFont(new Font("MV Boli",Font.BOLD,40));
        hard.setForeground(Color.white );
        hard.setBackground(Color.RED);
        hard.setBorder(new LineBorder(Color.black,4));


        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(medium);
        group.add(hard);



        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        button = new JButton("Done");
        button.setBounds(385,475,250,70);
        button.setFocusable(false);
        button.setFont(new Font("Arial",Font.BOLD,40));
        button.setForeground(Color.red);
        button.setBackground(Color.yellow);
        button.setBorder(new LineBorder(Color.darkGray,4));
        button.addActionListener(this);

        JPanel bgImage = new JPanel(){
            final Image frontPage=new ImageIcon("images\\levelImage.jpg").getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(frontPage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgImage.setLayout(null);


        frame.add(bgImage);
        frame.setContentPane(bgImage);
        frame.add(easy);
        frame.add(button);
        frame.add(medium);
        frame.add(hard);
        frame.add(label);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DifficultyLevel();
    }





    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==easy){
            speed=50;
        }
        else if(e.getSource()==medium){
            speed=40;
        }
        else if(e.getSource()==hard){
            speed=30;
        }
        else if(e.getSource()==button){

            frame.dispose();
            new SnakeFirstPage(speed);
        }
    }
}
