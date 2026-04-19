package snake_game;

import java.awt.*;
import java.util.Random;

public class Level4 extends GameLogic implements gameInterface{
    public Level4(int speed){
        super(Color.red,Color.green,Color.yellow,Color.blue,Color.red,Color.black,Color.green,Color.black,Color.red,4,35);
        super.speed=speed;
        barDrawer();
        startGame();
    }

    public void drawBoundary(Graphics g){
        int i=0;
        int j=0;
        g.setColor(Color.white);
        while(i!=1000){
            if(j==0){
                j+=1;
                g.setColor(Color.red);}
            else if(j==1){
                g.setColor(Color.white);
                j=0;
            }
            if(!(i>=342&&i<=640)) {
                g.fillRect(i, 0, 20, 22);
                g.fillRect(i, 645 - 33, 20, 30);
            }
            i+=20;
        }
        i=0;
        j=0;

        while(i!=620){
            if(j==0){
                j+=1;
                g.setColor(Color.red);}
            else if(j==1){
                g.setColor(Color.white);
                j=0;
            }

            g.fillRect(0, i, 29, 20);
            g.fillRect(962, i, 34, 20);
            i+=20;
        }
        i=0;
        j=0;
        while(i!=420){
            if(j==0){
                j+=1;
                g.setColor(Color.green);}
            else if(j==1){
                g.setColor(Color.white);
                j=0;
            }

            if(i<400){
                g.fillRect(320, 86 + i, 38, 20);
                g.fillRect(633, 86 + i, 38, 20);
            }
            else{
                g.fillRect(320, 86 + i, 38, 18);
                g.fillRect(633, 86 + i, 38, 18);
            }
            i+=20;
        }

        progress.setValue(Score);
    }

    public void randomGenerator(){
        rand = new Random();
        foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        if(foodX <=345 && foodY <= 25)
            randomGenerator();
        else if((foodX>=633&&foodY<=25))
            randomGenerator();
        else if(foodX<=30)
            randomGenerator();
        else if(foodX >= 960)
            randomGenerator();
        else if((foodX<=345&&foodY>=600))
            randomGenerator();
        else if((foodX>=633&&foodY>=600))
            randomGenerator();
        else if(foodX >= 308 && foodX <= 360 && foodY >= 83 && foodY <=490)
            randomGenerator();
        else if(foodX >= 630 && foodX <= 670 && foodY >= 83 && foodY <=490)
            randomGenerator();
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score+30,4);
        frame.dispose();
    }

    public void GameOver() {
        if(Head_x <=345 && Head_y <= 15)
            running=false;
        else if((Head_x>=652&&Head_y<=15))
            running=false;
        else if(Head_x<=15)
            running=false;
        else if(Head_x >= 960)
            running=false;
        else if((Head_x<=345&&Head_y>=600))
            running=false;
        else if((Head_x>=652&&Head_y>=600))
            running=false;
        else if(Head_x >= 315 && Head_x <= 350 && Head_y >= 83 && Head_y <=490)
            running=false;
        else if(Head_x >= 628 && Head_x <= 663 && Head_y >= 77 && Head_y <=490)
            running=false;

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
            Score=0;
            running=false;
            timer.stop();
            frame.dispose();
            new Level5(speed);
        }
    }


    public static void main(String[] args) {
        new Level4(50);
    }
}
