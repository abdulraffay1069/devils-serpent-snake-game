package snake_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.sound.sampled.*;

public class GameLogic extends JPanel implements KeyListener, ActionListener, gameInterface {
    public JFrame frame;
    public Timer timer;
    public static boolean music=true;
    public char Direction = 'D';
    public int Head_x;
    public int Head_y;
    public int speed;
    protected int round;
    public Color snakeColor1;
    public Color foodColor;
    public Color barBgColor;
    public Color barFRColor;
    public Color snakeColor2;
    public Color snakeColor3;
    public Color bgColor;
    public Color LFColor;
    public Color panelColor;
    public int Score = 0;
    public JProgressBar progress;
    public ArrayList<Character> directions=new ArrayList<>();
    public LinkedList<Point> snakeBody = new LinkedList<>();
    public int size = 10;
    public boolean running = false;
    public static Random rand;
    public int foodX = 100;
    public int foodY = 100;
    public static final int FRAME_WIDTH = 990;
    public static final int FRAME_HEIGHT = 645;
    public static final int BLOCK_SIZE = 15;
    public static final int BOUNDARY = 15;
    public static final int GAME_WIDTH = FRAME_WIDTH - 2 * BOUNDARY;
    public static final int GAME_HEIGHT = FRAME_HEIGHT - 2 * BOUNDARY;
    static MusicPlayer player=new MusicPlayer();

    public void frameMaker() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1003, 720);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Raja's SERPENT");
    }

    GameLogic(Color c1, Color c2, Color c3, Color c4, Color c5, Color c6, Color c7, Color c8, Color c9, int level, int velocity) {
        snakeColor1 = c1;
        snakeColor2 = c2;
        snakeColor3 = c3;
        foodColor = c4;
        LFColor = c5;
        bgColor = c6;
        panelColor = c7;
        barBgColor = c8;
        barFRColor = c9;
        round = level;
        speed = velocity;
        directions.add('D');
        frameMaker();
        this.setBackground(bgColor);
        frame.addKeyListener(this);
        frame.setLocationRelativeTo(null);
        frame.add(this, BorderLayout.CENTER);
        if(music) {
            player.playMusic("audio\\levelsAudio.wav");
            music = false;
        }
    }


    public void barDrawer() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 50));
        topPanel.setBackground(panelColor);
        topPanel.setLayout(null);
        JLabel level = new JLabel();
        level.setText("Level " + round);
        level.setFont(new Font("Caliber", Font.BOLD, 30));
        level.setBounds(150, 10, 170, 40);
        level.setForeground(LFColor);

        frame.add(topPanel, BorderLayout.NORTH);
        progress = new JProgressBar(0, 10);
        progress.setValue(Score);
        progress.setBounds(400, 8, 250, 30);
        progress.setBackground(barBgColor);
        progress.setForeground(barFRColor);
        topPanel.add(level);
        topPanel.add(progress);
        frame.setVisible(true);
    }

    public void startGame() {
        running = true;
        for (int i = 0; i < size; i++) {
            snakeBody.add(new Point(600 - i * BLOCK_SIZE, 50));
        }
        timer = new Timer(speed, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        snakeDrawer(g);
        drawBoundary(g);
    }

    public void drawBoundary(Graphics g) {}

    public void gmOvrDisplay(Graphics g) {}

    public void GameOver() {}

    public void snakeDrawer(Graphics g) {
        if (running) {
            int i = 0;
            for (Point p : snakeBody) {
                if (i == 0) {
                    g.setColor(snakeColor1);
                    i += 1;
                } else if (i % 2 == 0) {
                    g.setColor(snakeColor2);
                    i += 1;
                } else {
                    g.setColor(snakeColor3);
                    i += 1;
                }
                g.fillRect(p.x, p.y, BLOCK_SIZE, BLOCK_SIZE);
            }
            foodMaker(g);
        } else {
            gmOvrDisplay(g);
        }
    }
    public void gamePauser(char c){
        if(directions.getLast()=='P'&&c=='P')
            return;
        if(directions.size()>=2){
            directions.removeFirst();
            directions.add(c);
        }
        else
            directions.add(c);
    }

    public void snakeRun() {
        Point head = snakeBody.getFirst();
        Head_x = head.x;
        Head_y = head.y;

        switch (Direction) {
            case 'D':
                Head_x = (Head_x > 955) ? 10 : Head_x + BLOCK_SIZE;
                break;
            case 'A':
                Head_x = (Head_x < 10) ? 955 : Head_x - BLOCK_SIZE;
                break;
            case 'W':
                Head_y = (Head_y < 20) ? 610 : Head_y - BLOCK_SIZE;
                break;
            case 'S':
                Head_y = (Head_y > 610) ? 20 : Head_y + BLOCK_SIZE;
                break;
        }
        if(Direction!='P'){
            snakeBody.addFirst(new Point(Head_x, Head_y));
            snakeBody.removeLast();
            repaint();}
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = Character.toUpperCase(e.getKeyChar());
        if (key == 'W' && Direction != 'S') Direction = 'W';
        else if (key == 'S' && Direction != 'W') Direction = 'S';
        else if (key == 'A' && Direction != 'D') Direction = 'A';
        else if (key == 'D' && Direction != 'A') Direction = 'D';
        else if(key=='P') Direction='P';
        else if(key=='R'&& Direction=='P') Direction=directions.getFirst();
        gamePauser(Direction);

    }


    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public void drawFood(Graphics g) {
        g.setColor(Score == 9 ? Color.white : foodColor);
        g.fillOval(foodX, foodY, BLOCK_SIZE, BLOCK_SIZE);
    }

    public void randomGenerator() {
        rand = new Random();
        foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
    }

    public void foodMaker(Graphics g) {
        if ((Head_x >= foodX - 10 && Head_x <= foodX + BLOCK_SIZE) &&
                (Head_y >= foodY - 10 && Head_y <= foodY + BLOCK_SIZE)) {

            randomGenerator();
            drawFood(g);
            snakeBody.addLast(new Point(snakeBody.getLast().x + BLOCK_SIZE + 50, snakeBody.getLast().y));
            Score += 1;
            playAppleSound(); // 🔊 play sound when apple is eaten
        } else {
            drawFood(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeRun();
        GameOver();
    }

    // 🔉 Sound method for apple
    private void playAppleSound() {
        new Thread(() -> {
            try {
                File soundFile = new File("audio\\apple.wav");
                if (!soundFile.exists()) {
                    System.out.println("apple.wav not found at: " + soundFile.getAbsolutePath());
                    return;
                }
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
