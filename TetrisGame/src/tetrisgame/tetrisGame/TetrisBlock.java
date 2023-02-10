/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisGame;

/**
 *
 * @author BINS10235
 */
public class TetrisBlock {
    private int rotationVal;
    private int blockType;
    private TetrisBoard board;
    // x and y of top left corner of block
    private int x;
    private int y;
    private int c;
    private int ID;
    
    public TetrisBlock(int rotationVal, int blockVal, TetrisBoard board, int id){
        this.rotationVal = rotationVal;
        blockType = blockVal;
        this.board = board;
        x=4;
        y=0;
        c = 0;
        ID = id;
    }
    
    public TetrisBlock(int rotationVal, int blockVal, TetrisBoard board, int color, int id){
        this.rotationVal = rotationVal;
        blockType = blockVal;
        this.board = board;
        x=4;
        y=0;
        c = color;
        ID = id;
    }
    
    public int getID()
    {
    	return ID;
    }
    
    public void rotLeft(){
        rotationVal--;
    }
    
    public void rotRight(){
        rotationVal++;
    }
    
    public int getBlockType()
    {
        return blockType;
    }
    
    public void setPos(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void putOnBoard(){
        if(blockType == 1)
        {
            for(int i = 0; i < 5; i++)
            {
                board.setStatus(i+x,y,1,c);
            }
        }
    }
    
    public void rotate()
    {
        if(blockType == 1)
        {
            if(rotationVal == 0 || rotationVal == 2)
            {
                for(int i = 0; i < 5; i++)
                {
                    board.setStatus(i+x,y - 1,0,c);
                }
            }
            else
            {
                
            }
        }
    }
    
    public boolean drop(){
        boolean droppable = true;
        if(blockType == 1) // long straight block
        {
            if(rotationVal == 1 || rotationVal == 3)
            {
                //check if we can drop the block
                for(int i = 0; i < 5; i++)
                {
                    if(board.getStatus(i + x, y + 1) == 1)
                    {
                        droppable = false;
                        break;
                    }
                }
                
                
                //drop the block
                if(droppable) 
                {
                    y++;
                    for(int i = 0; i < 5; i++)
                    {
                        board.setStatus(i+x,y - 1,0,c);
                        board.setStatus(i+x,y,1,c);
                    }
                }
            }
            else
            {
                
            }
        }
        return droppable;
    }
    
    
    
    
    public void move(int dir)
    {
        if(blockType == 1)
        {
            if(dir == 1)
            {
                if(board.getStatus(x+5, y) == 0)
                {
                    for(int i = 0; i < 5; i++)
                    {
                        board.setStatus(i + x, y, 0, c);
                    }
                    for(int i = 0; i < 5; i++)
                    {
                       board.setStatus(i + x + 1, y, 1,c);
                    }
                    x++;
                }
            }
            else
            {
                if(board.getStatus(x-1, y) == 0)
                {
                    for(int i = 0; i < 5; i++)
                    {
                        board.setStatus(i + x, y, 0,c);
                    }
                    for(int i = 0; i < 5; i++)
                    {
                       board.setStatus(i + x - 1, y, 1,c);
                    }
                    x--;
                }
            }
        }
    }
    
    
    /**private void keyListen()
    {
    	JFrame frame = TetrisGame.frame;
    	frame.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e)
            {
               if(!TetrisGame.gameOver)
               {
                   if(e.getKeyCode() == KeyEvent.VK_LEFT)
                   {
                       System.out.println("left  " + getID());
                       move(-1);
                   }
                   else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                   {
                       System.out.println("right  " + getID());
                       move(1);
                   }
                   TetrisGame.graph(board);
               }
            }
        
            public void keyReleased(KeyEvent e)
            {
            	
            }
        
            public void keyTyped(KeyEvent e)
            {
            
            }
            });
    }*/
}
