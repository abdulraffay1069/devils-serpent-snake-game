package snake_game;

import java.awt.*;
import java.util.Random;

public class Level2 extends GameLogic implements gameInterface{
    public Level2(int speed){
        super(Color.red,Color.black,Color.green,Color.blue,new Color(50,50,50),new Color(240,235,200),new Color(240,235,200),Color.black,Color.green,2,40);
        super.Direction='D';
        super.size=10;
        super.speed=speed;
        barDrawer();
        startGame();
    }

    public void drawBoundary(Graphics g) {
        g.setColor(new Color(90, 50, 20));
        g.fillRect(0, 0, FRAME_WIDTH, 20);
        g.fillRect(0, 612, FRAME_WIDTH, 20);
        g.fillRect(0, 10,20, GAME_HEIGHT);
        g.fillRect(970, 10,20, GAME_HEIGHT);
        progress.setValue(Score);
    }

    public void gmOvrDisplay(Graphics g) {
        timer.stop();
        player.stopMusic();
        new SnakeLastPage(Score+10,2);
        frame.dispose();
    }

    public void GameOver() {
        if ((Head_x < 15 || Head_x >= 963 || Head_y < 20 || Head_y >= 607)) {
            running = false;
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
        if(Score==10){
            Score=0;
            running=false;
            timer.stop();
            frame.dispose();
            new Level3(speed);
        }
    }
    public void randomGenerator(){
        rand = new Random();
        foodX = (rand.nextInt(930-50)+1) + 50;
        foodY = (rand.nextInt(590-50)+1) + 50;
    }


    public static void main(String[] args) {
        new Level2(40);
    }
}

