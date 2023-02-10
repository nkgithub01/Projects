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
public class TetrisBoard {
    
    private int[][][] board;
    public int w;
    public int h;
    
    public TetrisBoard(int width, int height){
        w = width + 2;
        h = height + 1;
        board = new int[w][h][2];
        for(int i = 0; i < w; i++)
        {
            for(int j = 0; j < h; j++)
            {
                if(i == 0 || i == w -1 || j == h -1) board[i][j][0] = 1;
                else board[i][j][0] = 0;
            }
        }
    }
    
    /**
     * returns whether or not there is a block in the indicated position
     */
    public int getStatus(int x, int y){
        return board[x][y][0];
    }
    
    public void setStatus(int x, int y, int status, int colorVal)
    {
    	board[x][y][0] = status;
    	board[x][y][1] = colorVal;
    }
    
    public void setStatus(int x, int y, int status){
        board[x][y][0] = status;
    }
    
    public int getColor(int x, int y)
    {
    	return board[x][y][1];
    }
    
    public int fullRow()
    {
    	boolean full = false;
    	boolean rowFull = true;
    	int i = 0;
    	for(i = h-2; i >= 0 && !full; i--)
    	{
    		rowFull = true;
    		for(int j = 1; j < w - 1 && rowFull; j++)
    		{
    			if(board[j][i][0] == 0) rowFull = false;
    		}
    		if(rowFull) break;
    	}
    	return i;
    }
    
    public void deleteRow(int row)
    {
    	for(int i = 1; i < w - 1; i++)
    	{
    		board[i][row][0] = 0;
    	}
    	
    	for(int i = 1; i < w - 1; i++)
    	{
    		for(int j = row; j > 0; j--)
    		{
    			board[i][j][0] = board[i][j-1][0];
    			board[i][j][1] = board[i][j-1][1];
    		}
    	}
    }
    
    public void printboard(){
        for(int i = 0; i < h; i++)
        {
            for(int j = 0; j < w; j++)
            {
                System.out.print(board[j][i][0]);
            }
            System.out.println("");
        }
    }
}
