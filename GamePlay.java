package classic_snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    
    /*score*/
    score sc=new score();
    
    private int[] snakexlength = new int [750]; 
    private int[] snakeylength = new int [750]; 
    
    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    
    private ImageIcon rightface;
    private ImageIcon leftface;
    private ImageIcon upface;
    private ImageIcon downface;
    private ImageIcon snakeimage;
    
    // default positions
    private int[] enemyxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    
    private ImageIcon enemyimage;
    
    private Random random = new Random();
    
    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);
    
    private Timer timer;
    private int delay = 100;
    
    private int lengthofsnake = 3;
    private int moves = 0;
    private int scores = 0;
    
    private ImageIcon titleImage;
    
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        
        if(moves==0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;
            
            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
        }
        
        //border of title image
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        
        titleImage = new ImageIcon("images/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        
        //border of gameplay
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        
        //draw the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 20));
        g.drawString("SCORES: "+scores, 740, 44);
        
        rightface = new ImageIcon("images/rightface.png");
        rightface.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        
        //draw the right, left, up, down images of the snake
        for(int a=0; a<lengthofsnake; a++){
            if(a==0 && right){
                rightface = new ImageIcon("images/rightface.png");
                rightface.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && left){
                leftface = new ImageIcon("images/leftface.png");
                leftface.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && up){
                upface = new ImageIcon("images/upface.png");
                upface.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a==0 && down){
                downface = new ImageIcon("images/downface.png");
                downface.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
            
            if(a!=0){
                snakeimage = new ImageIcon("images/snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }
        
        /* Here I need to detect if the head of my snake is colliding with the ball */
        
        enemyimage = new ImageIcon("images/enemy.png");
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        if(enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]){
            // lenghts & scores increases
            lengthofsnake++;
            scores++;
            
            // generate random position
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        
        enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        for(int b=1; b<lengthofsnake; b++){
            
            /* Here we check that is the snake collide with itself */
            
            if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0]){
                
                // if collide then we make all the directions false
                
                right = false;
                left = false;
                up = false;
                down = false;
                
                // draw the Game Over, Restart & Save
                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over!", 300, 300);
                
                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("Space to RESTART", 350, 340);
                
                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString(" 'S' to SAVE", 380, 380);
                
            }
        }
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        if(right){
            for(int i=lengthofsnake-1; i>=0; i--){
                snakeylength[i+1] = snakeylength[i];
            }
            
            for(int i=lengthofsnake; i>=0; i--){
                if(i==0){
                    snakexlength[i] = snakexlength[i]+25;
                }
                else{
                    snakexlength[i] = snakexlength[i-1];
                }
                if(snakexlength[i]>850){
                    snakexlength[i]=25;
                }
            }
            
            repaint();
        }
        
        if(left){
            for(int i=lengthofsnake-1; i>=0; i--){
                snakeylength[i+1] = snakeylength[i];
            }
            
            for(int i=lengthofsnake; i>=0; i--){
                if(i==0){
                    snakexlength[i] = snakexlength[i]-25;
                }
                else{
                    snakexlength[i] = snakexlength[i-1];
                }
                if(snakexlength[i]<25){
                    snakexlength[i]=850;
                }
            }
            
            repaint();    
        }
        
        if(up){
            for(int i=lengthofsnake-1; i>=0; i--){
                snakexlength[i+1] = snakexlength[i];
            }
            
            for(int i=lengthofsnake; i>=0; i--){
                if(i==0){
                    snakeylength[i] = snakeylength[i]-25;
                }
                else{
                    snakeylength[i] = snakeylength[i-1];
                }
                if(snakeylength[i]<75){
                    snakeylength[i]=625;
                }
            }
            
            repaint();    
        }
        
        if(down){
            for(int i=lengthofsnake-1; i>=0; i--){
                snakexlength[i+1] = snakexlength[i];
            }
            
            for(int i=lengthofsnake; i>=0; i--){
                if(i==0){
                    snakeylength[i] = snakeylength[i]+25;
                }
                else{
                    snakeylength[i] = snakeylength[i-1];
                }
                if(snakeylength[i]>625){
                    snakeylength[i]=75;
                }
            }
            
            repaint();    
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            
            /* Check here that if its moving towards the right then it should not move towards the left*/
            
            moves++;
            if(!left){
                right=true;
            }
            else{
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            if(!right){
                left=true;
            }
            else{
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            if(!down){
                up=true;
            }
            else{
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            if(!up){
                down=true;
            }
            else{
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
        
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            lengthofsnake = 3;
            moves = 0;
            scores = 0;
            repaint();
        }
        
        if(e.getKeyCode()==KeyEvent.VK_S){
            sc.wscore=scores;
            sc.setVisible(true);
            //this.setVisible(false);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent arg0){
        //TODO Auto-generated method stub
    }
    
    @Override
    public void keyTyped(KeyEvent arg0){
       //TODO Auto-generated method stub 
    }
    
}
