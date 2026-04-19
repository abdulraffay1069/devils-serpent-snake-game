package snake_game;

import java.awt.*;
import java.util.Random;

public class Level5 extends GameLogic implements  gameInterface{

    public Level5(int speed){
        super(Color.green,Color.red,Color.white,Color.blue,Color.black,Color.black,new Color(240,235,200),Color.black,Color.cyan,5,20);
        super.speed=speed;
        barDrawer();
        startGame();
    }
    public void drawBoundary(Graphics g) {
        int i=0;
        int j=0;
        g.setColor(Color.white);
        while(i!=1000){
            if(j==0){
                j+=1;
                g.setColor(Color.yellow);}
            else if(j==1){
                g.setColor(Color.gray);
                j=0;
            }
            if(!(i>=410&&i<=580)) {
                g.fillRect(i, 0, 20, 22);
                g.fillRect(i, 645 - 33, 20, 30);
            }
            i+=20;
        }
        i=0;
        j=0;

        while(!(i>=620)){
            if(j==0){
                j+=1;
                g.setColor(Color.yellow);}
            else if(j==1){
                g.setColor(Color.gray);
                j=0;
            }
            if(!(i>=230&&i<=370)) {
                g.fillRect(0, i, 29, 20);
                g.fillRect(962, i, 34, 20);
            }
            if(i==360){
                i+=20;
            }
            i += 20;
        }
        g.setColor(Color.ORANGE);
        g.fillRect(395,207,230,202);
        progress.setValue(Score);
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score+40,5);
        frame.dispose();
    }


    public void GameOver() {
        if(Head_x <=412 && Head_y <= 17)
            running=false;
        else if((Head_x>=588&&Head_y<=17))
            running=false;
        else if(Head_x<=412 && Head_y>603)
            running=false;
        else if(Head_x >= 588 && Head_y>603)
            running=false;
        else if((Head_x<=15&&Head_y<=230))
            running=false;
        else if((Head_x<=15&&Head_y>=391))
            running=false;
        else if(Head_x >= 955 && Head_y <=229)
            running=false;
        else if(Head_x >= 955 && Head_y >=392)
            running=false;
        else if(Head_x >= 390 && Head_x <= 627 && Head_y >= 200 && Head_y <=409)
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
            new Level6(speed);
        }
    }

    public void randomGenerator(){
        rand = new Random();
        foodX = ((rand.nextInt((GAME_WIDTH - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        foodY = ((rand.nextInt((GAME_HEIGHT - BLOCK_SIZE) / BLOCK_SIZE)) * BLOCK_SIZE) + BOUNDARY;
        if(foodX <=412 && foodY <= 25)
            randomGenerator();
        else if((foodX>=578&&foodY<=25))
            randomGenerator();
        else if(foodX<=412&&foodY>599)
            randomGenerator();
        else if(foodX >= 578&&foodY>599)
            randomGenerator();
        else if((foodX<=31&&foodY<=232))
            randomGenerator();
        else if((foodX<=31&&foodY>=368))
            randomGenerator();
        else if(foodX >= 960&& foodY <=232)
            randomGenerator();
        else if(foodX >= 960&& foodY >=368)
            randomGenerator();
        else if(foodX >= 386 && foodX <= 623 && foodY >= 200 && foodY <=411)
            randomGenerator();
    }


    public static void main(String[] args) {
        new Level5(50);
    }
}
