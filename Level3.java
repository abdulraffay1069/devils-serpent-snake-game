package snake_game;

import java.awt.*;
import java.util.Random;

public class Level3 extends GameLogic implements gameInterface{

    public Level3(int speed){
        super(Color.red,Color.white,Color.black,Color.blue,Color.red,Color.gray,Color.green,Color.black,Color.red,3,33);
        super.Direction='D';
        size=10;
        super.speed=speed;
        barDrawer();
        startGame();
    }

    public void drawBoundary(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(392,0,27,240);
        g.fillRect(0,222,420,26);
        g.fillRect(560,372,440,26);
        g.fillRect(560,398,27,240);
        progress.setValue(Score);
    }

    public void randomGenerator(){
        rand = new Random();
        foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        if(foodX >= 385 && foodX <= 425 && foodY <= 240)
            randomGenerator();
        else if((foodX>=0&&foodX<=420) && (foodY>=217&&foodY<=253))
            randomGenerator();
        else if((foodX>=560&&foodX<=990) && (foodY>=367&&foodY<=407))
            randomGenerator();
        else if(foodX >= 555 && foodX <= 593 && foodY >= 398)
            randomGenerator();
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score+20,3);
        frame.dispose();
    }

    public void GameOver() {
        if(Head_x>=388&&Head_x<=419 && Head_y<=240)
            running=false;
        else if((Head_x>=0&&Head_x<=420) && (Head_y>=216&&Head_y<=248))
            running=false;
        else if((Head_x>=560&&Head_x<=990) && (Head_y>=368&&Head_y<=398))
            running=false;
        else if((Head_x>=557&&Head_x<=580) && (Head_y>=398&&Head_y<=645))
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
            new Level4(speed);
        }
    }


    public static void main(String[] args) {

        new Level3(50);
    }
}

