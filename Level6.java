package snake_game;

import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

public class Level6 extends GameLogic implements gameInterface {

    private int obstacleX1 = 200, obstacleY1 = 300;
    private int obstacleX2 = 500, obstacleY2 = 100;
    private int obstacleX3 = 300, obstacleY3 = 200;
    private int obstacleX4 = 270, obstacleY4 = 270;
    private int obstacleX5 = 700, obstacleY5 = 400;
    private int obstacleX6 = 150, obstacleY6 = 500;
    private int obstacleX7 = 600, obstacleY7 = 350;

    private boolean moveRight1 = true;
    private boolean moveDown2 = true;
    private boolean moveDiagRight = true, moveDiagDown = true;
    private boolean moveLeft = true;
    private boolean moveUp6 = true;
    private boolean zigzagRight = true, zigzagDown = true;

    public Level6(int speed) {
        super(new Color(0, 0, 0), Color.black, Color.white, Color.magenta, Color.darkGray,
                new Color(147, 211, 164), new Color(250, 152, 152), Color.black, Color.green, 6, 50);
        super.speed = speed;
        barDrawer();
        startGame();
        gameStarter();
    }

    public void drawBoundary(Graphics g) {
        g.setColor(Color.white);
        for (int i = 0; i <= 1000; i += 20) {
            g.fillRect(i, 0, 20, 20);  // Top
            g.fillRect(i, 620, 20, 20); // Bottom
        }
        for (int i = 20; i <= 620; i += 20) {
            g.fillRect(0, i, 20, 20);   // Left
            g.fillRect(980, i, 20, 20); // Right
        }

        // Obstacles
        g.setColor(Color.red);
        g.fillRect(obstacleX1, obstacleY1, 120, 20); // Horizontal Right

        g.setColor(Color.orange);
        g.fillRect(obstacleX2, obstacleY2, 20, 100); // Vertical Down

        g.setColor(Color.magenta);
        g.fillRect(obstacleX3, obstacleY3, 40, 40);  // Diagonal Square

        g.setColor(Color.green);
        g.fillRect(obstacleX4, obstacleY4, 40, 40);  // Static Square

        g.setColor(Color.blue);
        g.fillRect(obstacleX5, obstacleY5, 120, 20); // Horizontal Left

        g.setColor(Color.pink);
        g.fillRect(obstacleX6, obstacleY6, 20, 100); // Vertical Up

        g.setColor(Color.black);
        g.fillRect(obstacleX7, obstacleY7, 30, 30);  // Zigzag Box

        progress.setValue(Score);
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score + 50, 6);
        frame.dispose();
    }

    public void GameOver() {
        // Only check for the head collision with the walls
        if (Head_x <= 10 || Head_x >= 980 || Head_y <= 10 || Head_y >= 620) {
            running = false;
            return;
        }

        Rectangle[] obstacles = {
                new Rectangle(obstacleX1, obstacleY1, 120, 20),
                new Rectangle(obstacleX2, obstacleY2, 20, 100),
                new Rectangle(obstacleX3, obstacleY3, 40, 40),
                new Rectangle(obstacleX4, obstacleY4, 40, 40),
                new Rectangle(obstacleX5, obstacleY5, 120, 20),
                new Rectangle(obstacleX6, obstacleY6, 20, 100),
                new Rectangle(obstacleX7, obstacleY7, 30, 30)
        };

        // Check only for the head of the snake
        Rectangle head = new Rectangle(Head_x, Head_y, BLOCK_SIZE, BLOCK_SIZE);

        for (Rectangle obs : obstacles) {
            if (head.intersects(obs)) {
                Direction = 'b'; // Dead, stop the game
                running = false;
                return;
            }
        }
        int i=0;
        for(Point p:snakeBody){
            if(Head_x==p.x && Head_y==p.y)
                if(i==0){
                    i+=1;
                }
                else{
                    Direction='b';
                    running = false;
                }
        }

        // Level progression (example: Next level at score 20)
        if (Score ==10) {
            timer.stop();
            player.stopMusic();
            new SnakeLastPage(Score+50,6);
            frame.dispose();
        }
    }

    public void randomGenerator() {
        rand = new Random();
        while (true) {
            // Random food placement
            foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
            foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;

            Rectangle food = new Rectangle(foodX, foodY, BLOCK_SIZE, BLOCK_SIZE);
            Rectangle[] obstacles = {
                    new Rectangle(obstacleX1, obstacleY1, 120, 20),
                    new Rectangle(obstacleX2, obstacleY2, 20, 100),
                    new Rectangle(obstacleX3, obstacleY3, 40, 40),
                    new Rectangle(obstacleX4, obstacleY4, 40, 40),
                    new Rectangle(obstacleX5, obstacleY5, 120, 20),
                    new Rectangle(obstacleX6, obstacleY6, 20, 100),
                    new Rectangle(obstacleX7, obstacleY7, 30, 30)
            };

            boolean safe = true;
            for (Rectangle obs : obstacles) {
                if (food.intersects(obs)) {
                    safe = false;
                    break;
                }
            }
            // If food is not overlapping with obstacles, break the loop
            if (safe) break;
        }
    }

    public void updateObstacles() {
        // Horizontal right
        if (moveRight1) {
            obstacleX1 += 2;
            if (obstacleX1 >= 840) moveRight1 = false;
        } else {
            obstacleX1 -= 2;
            if (obstacleX1 <= 100) moveRight1 = true;
        }

        // Vertical down
        if (moveDown2) {
            obstacleY2 += 2;
            if (obstacleY2 >= 500) moveDown2 = false;
        } else {
            obstacleY2 -= 2;
            if (obstacleY2 <= 50) moveDown2 = true;
        }

        // Diagonal box
        if (moveDiagRight) {
            obstacleX3 += 2;
            if (obstacleX3 >= 920) moveDiagRight = false;
        } else {
            obstacleX3 -= 2;
            if (obstacleX3 <= 40) moveDiagRight = true;
        }
        if (moveDiagDown) {
            obstacleY3 += 2;
            if (obstacleY3 >= 540) moveDiagDown = false;
        } else {
            obstacleY3 -= 2;
            if (obstacleY3 <= 40) moveDiagDown = true;
        }

        // Horizontal left (opposite direction)
        if (moveLeft) {
            obstacleX5 -= 2;
            if (obstacleX5 <= 100) moveLeft = false;
        } else {
            obstacleX5 += 2;
            if (obstacleX5 >= 840) moveLeft = true;
        }

        // Vertical up (opposite direction)
        if (moveUp6) {
            obstacleY6 -= 2;
            if (obstacleY6 <= 60) moveUp6 = false;
        } else {
            obstacleY6 += 2;
            if (obstacleY6 >= 500) moveUp6 = true;
        }

        // Zig-zag movement
        if (zigzagRight) {
            obstacleX7 += 3;
            if (obstacleX7 >= 880) zigzagRight = false;
        } else {
            obstacleX7 -= 3;
            if (obstacleX7 <= 40) zigzagRight = true;
        }

        if (zigzagDown) {
            obstacleY7 += 3;
            if (obstacleY7 >= 540) zigzagDown = false;
        } else {
            obstacleY7 -= 3;
            if (obstacleY7 <= 40) zigzagDown = true;
        }
    }
    public void gameStarter(){
        new Timer(20, e -> {
            if (this.running) {
                this.updateObstacles();
                this.frame.repaint();
            }
        }).start();
    }

    public static void main(String[] args) {
        Level6 level = new Level6(50);
    }
}