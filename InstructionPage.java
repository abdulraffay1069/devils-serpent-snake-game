package snake_game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionPage implements ActionListener {
    JFrame frame;
    JButton button;

    InstructionPage(){
        frame = new JFrame();
        frame.setSize(990,645);
        frame.setTitle("Instructions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.pink);



        JLabel label = new JLabel();
        label.setText("Instructions");
        label.setBounds(350,50,400,100);
        label.setForeground(Color.cyan);
        label.setFont(new Font("Calibri",Font.BOLD,50));

        JLabel up = new JLabel();
        up.setText("1- Press 'W' for upward movement");
        up.setBounds(100,150,600,50);
        up.setForeground(Color.yellow);
        up.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel down = new JLabel();
        down.setText("2- Press 'S' for downward movement");
        down.setBounds(100,200,600,50);
        down.setForeground(Color.green);
        down.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel left = new JLabel();
        left.setText("3- Press 'A' to move snake left side");
        left.setBounds(100,250,600,50);
        left.setForeground(Color.yellow);
        left.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel right = new JLabel();
        right.setText("4- Press 'D' to move snake right side");
        right.setBounds(100,300,600,50);
        right.setForeground(Color.green);
        right.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel boundary = new JLabel();
        boundary.setText("5- Protect the snake to hit from the boundary");
        boundary.setBounds(100,350,600,50);
        boundary.setForeground(Color.yellow);
        boundary.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel food = new JLabel();
        food.setText("6- Eat the food to grow the snake");
        food.setBounds(100,400,600,50);
        food.setForeground(Color.green);
        food.setFont(new Font("Calibri",Font.BOLD,25));

        JLabel loading = new JLabel();
        loading.setText("7- Next level will get started on completion of loading running above the game");
        loading.setBounds(100,450,900,50);
        loading.setForeground(Color.yellow);
        loading.setFont(new Font("Calibri",Font.BOLD,25));

        button = new JButton("Done");
        button.setBounds(400,520,200,70);
        button.setForeground(Color.green);
        button.setBackground(Color.darkGray);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.black,5));
        button.setFont(new Font("Arial",Font.BOLD,30));
        button.addActionListener(this);

        JPanel bgImage = new JPanel(){
            final Image frontPage=new ImageIcon("images\\instructionPage.png").getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(frontPage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgImage.setLayout(null);

        frame.add(bgImage);
        frame.setContentPane(bgImage);
        frame.add(label);
        frame.add(up);
        frame.add(button);
        frame.add(down);
        frame.add(left);
        frame.add(right);
        frame.add(food);
        frame.add(boundary);
        frame.add(loading);

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new InstructionPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){

            button.setEnabled(false);
            new SnakeFirstPage(40);
            frame.dispose();
        }
    }
}
