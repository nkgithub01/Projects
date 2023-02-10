/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.concurrent.*;
import java.awt.event.*;

/**
 *
 * @author BINS10235
 */
public class TetrisGame
{

    public static JFrame frame = new JFrame();

    private static TetrisBlock block;
    public static boolean gameOver;
    private static Color[] colors = {Color.GREEN, Color.RED, Color.CYAN, Color.MAGENTA, Color.ORANGE};
    private static int timesTried;
    private static int blockID = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        gameOver = false;
        TetrisBoard board = new TetrisBoard(10,15);
        System.out.println("");
        block = new TetrisBlock(1,1,board, blockID);
        blockID++;
        block.putOnBoard();
        graph(board);
        timesTried = 0;
        //frame.setFocusable(true);
        frame.addKeyListener(new KeyListener(){
        public void keyPressed(KeyEvent e)
        {
           if(!gameOver)
           {
               if(e.getKeyCode() == KeyEvent.VK_LEFT)
               {
                   System.out.println("left  " + block.getID());
                   block.move(-1);
               }
               else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
               {
                   System.out.println("right  " + block.getID());
                   block.move(1);
               }
               graph(board);
           }
        }
    
        public void keyReleased(KeyEvent e)
        {
        	
        }
    
        public void keyTyped(KeyEvent e)
        {
        
        }
        });
        
    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            step(board);
        }
    }, 0, 100, TimeUnit.MILLISECONDS);
       
        while(!gameOver)
        {
            if(timesTried >= 2)
            {
            	gameOver = true;
            	System.out.println("gameOver");
            }
            System.out.print("");
        }
    }
    
    public static void step(TetrisBoard board)
    {
        if(!gameOver)
        {
        	if(!block.drop())
        	{
        		int row = board.fullRow();
        		while(row >= 0)
        			{
        				board.deleteRow(row);
        				row = board.fullRow();
        			}
        		int color = (int)(Math.random()*5);
        		block = new TetrisBlock(1,1,board, color,blockID);
        		System.out.println("new block. ID = " + blockID);
        		blockID++;
        		block.putOnBoard();
        		timesTried ++;
        	}
        	else timesTried = 0;
        	
            graph(board);
        }
    }
    
    
    
    
    public static void draw(Graphics g, TetrisBoard b){
        
        g.setColor(new Color(0,0,200));  
        g.fillRect(0, 0, 800, 800);
        final int gridWidth = 40;
        
        g.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i < b.w; i++)
        {
            for(int j = 0; j < b.h; j++)
            {
                if(b.getStatus(i,j) == 1)
                {
                    g.setColor(colors[b.getColor(i, j)]);
                    g.fillRect(i*gridWidth, j *gridWidth, gridWidth, gridWidth);
                }
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i*gridWidth, j*gridWidth, gridWidth, gridWidth);
            }
        }
    }
    
    
    public static void graph(TetrisBoard board)
    {

        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 800;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent component = new JComponent()
        {
            public void paintComponent(Graphics graph)
            {
                draw(graph, board);
            }
        };
      
        frame.add(component);
        frame.setVisible(true);
    }
    

}





// Unused code (possibly useful later)
/* for(int i = 0; i < 16; i++)
        {
            block1 = new TetrisBlock(1,1,board);
            block1.putOnBoard();
            graph(board);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            while(block1.drop())
            {
                graph(board);
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
            }
        }*/