package snake_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFirstPage extends JFrame implements ActionListener {
    JButton button;
    JButton instructions;
    JButton level;
    JProgressBar bar;
    int speed=50;
    Timer timer;
    public static boolean music=true;
    int i=0;
    static MusicPlayer musicPlayer = new MusicPlayer();
    SnakeFirstPage(int speed){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Raja's SERPENT");
        this.setSize(1200,700);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.speed=speed;
        if(music) {
            musicPlayer.playMusic("audio\\snakeFirstPageAudio.wav");
            music = false;
        }



        JPanel bgImage = new JPanel(){
            final Image frontPage=new ImageIcon("images\\frontPage.png").getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(frontPage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgImage.setLayout(null);


        ImageIcon name = new ImageIcon("images\\gameName.png");
        JLabel label = new JLabel(name);
        label.setBounds(230,0,800,400);


        button = new JButton("Play");
        button.setBackground(Color.orange);
        button.setForeground(Color.white);
        button.setFont(new Font("Calibari",Font.BOLD,40));
        button.setFocusable(false);
        button.setBounds(450,450,300,60);
//        button.setBorder(BorderFactory.createLineBorder(Color.red,4));
        button.addActionListener(this);

        instructions = new JButton("Instructions");
        instructions.setBackground(Color.red);
        instructions.setForeground(Color.white);
        instructions.setFont(new Font("Calibari",Font.BOLD,40));
        instructions.setFocusable(false);
        instructions.setBounds(450,300,300,60);
//        instructions.setBorder(BorderFactory.createLineBorder(Color.green,4));
        instructions.addActionListener(this);

        level = new JButton("Level");
        level.setBackground(Color.blue);
        level.setForeground(Color.white);
        level.setFont(new Font("Calibari",Font.BOLD,40));
        level.setFocusable(false);
        level.setBounds(450,375,300,60);
//        level.setBorder(BorderFactory.createLineBorder(Color.red,4));
        level.addActionListener(this);

        bar= new JProgressBar(0,100);
        bar.setBounds(295,540,600,50);
        bar.setForeground(Color.green);
        bar.setBackground(Color.GRAY);
        bar.setVisible(false);


        this.setContentPane(bgImage);
        bgImage.add(bar);
        bgImage.add(instructions);
        bgImage.add(label);
        bgImage.add(button);
        bgImage.add(level);
        this.setVisible(true);
    }
    public void musicPlayer(){
        if(music) {
            musicPlayer.playMusic("audio\\levelsAudio.wav");
            music = false;
        }
    }

    public static void main(String[] args) {
        new SnakeFirstPage(40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            button.setEnabled(false);
            instructions.setEnabled(false);
            level.setEnabled(false);
            bar.setVisible(true);
            bar.setValue(0);
            i = 0;

            timer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (i < 100) {
                        bar.setValue(i);
                        i += 1;
                    } else {
                        timer.stop();
                        dispose();
                        musicPlayer.stopMusic();
                        new Level1(speed);
                    }
                }
            });
            timer.start();

        }
        else if(e.getSource()==instructions){
            new InstructionPage();
            this.dispose();
        }
        else if(e.getSource()==level){
            new DifficultyLevel();
            this.dispose();
        }

    }
}

