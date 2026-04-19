package snake_game;

import java.awt.*;
import java.util.Random;

public class Level1 extends GameLogic implements gameInterface{
    public Level1(int speed){
        super(Color.white,Color.green,Color.red,Color.blue,Color.black,Color.black,Color.pink,Color.black,Color.red,1,50);
        super.Direction='D';
        super.size=10;
        super.speed=speed;
        barDrawer();
        startGame();
    }

    public void drawBoundary(Graphics g){

        progress.setValue(Score);
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score,1);
        frame.dispose();
    }
    public void randomGenerator(){
        rand = new Random();
        foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
    }

    public void GameOver() {
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
        if(Score==10){
            running=false;
            timer.stop();
            frame.dispose();
            new Level2(speed);
        }
    }

    public static void main(String[] args) {
        new Level1(40);
    }
}

