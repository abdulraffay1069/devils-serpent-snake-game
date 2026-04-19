package snake_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeLastPage extends JFrame implements ActionListener {
    int score;
    int level;
    JButton playAgain;
    JButton mainMenu;
    JButton Exit;
    public static int highscore=0;
    MusicPlayer player=new MusicPlayer();
    public SnakeLastPage(int score, int level){
        if(score>highscore)
            highscore=score;
        this.score=score;
        this.level=level;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(990,645);
        this.setResizable(false);
        this.setLayout( new BorderLayout());
        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        player.playMusic("audio\\snakeLastPageAudio.wav");

        JPanel panel1= new JPanel();
        panel1.setBackground(Color.ORANGE);
        panel1.setPreferredSize(new Dimension(995,400));
        panel1.setLayout(null);
        this.add(panel1,BorderLayout.CENTER);

        JPanel panel2= new JPanel();
        panel2.setBackground(Color.green);
        panel2.setPreferredSize(new Dimension(995,250));
        panel2.setLayout(null);
        this.add(panel2,BorderLayout.SOUTH);

        JPanel panel3= new JPanel();
        panel3.setBackground(Color.black);
        panel3.setPreferredSize(new Dimension(995,100));
        panel3.setLayout(null);
        this.add(panel3,BorderLayout.NORTH);

        JLabel name = new JLabel();
        name.setText("Devil's SERPENT");
        name.setForeground(Color.white);
        name.setFont(new Font("MV Boli",Font.BOLD,42));
        name.setBounds(360,20,400,50);
        panel3.add(name);

        JLabel highskore=new JLabel("High Score :                  "+highscore);
        highskore.setFont(new Font("Calibri",Font.BOLD,40));
        highskore.setForeground(Color.red);
        highskore.setBounds(270,180,800,100);
        panel1.add(highskore);

        String scoreText = "<html>"
                + "<div style='font-family:Arial, sans-serif;'>"

                // Score Label
                + "<span style='color:#0D47A1; font-weight:bold; font-size:30px;'>Score: </span>"
                + "<span style='color:#2C3E50; font-family:SansSerif; font-size:35px;'>"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +"&nbsp;&nbsp;&nbsp;" +"&nbsp;&nbsp;&nbsp;" +"&nbsp;&nbsp;&nbsp;" +"&nbsp;&nbsp;&nbsp;" + score + "</span><br><br>"

                // Level Reached Label
                + "<span style='color:#4A148C; font-weight:bold; font-size:30px;'>Level Reached: </span>"
                + "<span style='color:#2C3E50; font-family:SansSerif; font-size:35px;'>"
                + "&nbsp;&nbsp;&nbsp;" +"&nbsp;&nbsp;" + level + "</span>"

                + "</div>"
                + "</html>";

        JLabel Score = new JLabel(scoreText);
        Score.setBounds(270,0,400,200);
        panel1.add(Score);


        playAgain = new JButton("Play Again");
        playAgain.setBackground(new Color(34, 34, 34));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFont(new Font("Arial", Font.BOLD, 18));
        playAgain.setFocusable(false);
        playAgain.setPreferredSize(new Dimension(100,50));
        playAgain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playAgain.setBounds(100,70,200,70);
        playAgain.addActionListener(this);
        panel2.add(playAgain);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBackground(new Color(34, 34, 34));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFont(new Font("Arial", Font.BOLD, 18));
        mainMenu.setFocusable(false);
        mainMenu.setPreferredSize(new Dimension(100,50));
        mainMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainMenu.setBounds(400,70,200,70);
        mainMenu.addActionListener(this);
        panel2.add(mainMenu);

        Exit = new JButton("Exit");
        Exit.setBackground(new Color(34, 34, 34));
        Exit.setForeground(Color.WHITE);
        Exit.setFont(new Font("Arial", Font.BOLD, 18));
        Exit.setFocusable(false);
        Exit.setPreferredSize(new Dimension(100,50));
        Exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        Exit.setBounds(700,70,200,70);
        Exit.addActionListener(this);
        panel2.add(Exit);

        this.setVisible(true);


    }
    public static void main(String[] args) {
        new SnakeLastPage(30,3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==playAgain){
            player.stopMusic();
            new Level1(40);
            this.dispose();
        }
        else if(e.getSource()==Exit){
            player.stopMusic();
            System.exit(0);
        }
        else if(e.getSource()==mainMenu){
            player.stopMusic();
            new SnakeFirstPage(40);
            this.dispose();
        }

    }
}
