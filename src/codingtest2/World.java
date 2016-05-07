/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codingtest2;

/**
 *
 * @author Pietro
 */
public class World {
    
    //dimensions
    static final int WIDTH = 8;
    static final int HEIGHT = 7;
    
    //rooms tiles
    static final String alphabet = "ABCEFGHIJKLMNOPQRSTUVXYZ";//without W & D
    
    //world elements
    private char[][] grid;
    private int numberOfRegions;
    private int numberOfDoors;
    
    /**
     * Create the world with his grid
     */
    public World()
    {
        grid = new char[HEIGHT][WIDTH];
        
        for(int i = 0; i < HEIGHT; i++)
            for(int j = 0; j < WIDTH; j++)
                grid[i][j] = '_';
        
        numberOfRegions = 0;
    }
    
    
    /**
     * Create a region surrounded by walls and add it to the world.
     * Warning: the rooms can only have the corners in common/intersecting.
     * @param x
     * @param y
     * @param width
     * @param height
     * @return true if successful
     */
    public boolean addRegion(int x, int y, int width, int height)
    {
        if(x < 0 || y < 0 || ((x + width) > WIDTH) || ((y + height) > HEIGHT))
            return false;
            
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(i == 0 || j == 0 || i == (height - 1) || j == (width - 1))
                    grid[y + i][x + j] = 'w';
                else
                    grid[y + i][x + j] = alphabet.charAt(numberOfRegions);
            }
        }
        
        // only if is a room and not just walls
        if(width > 2 && height > 2)
            numberOfRegions++;
        
        return true;
    }
    
    
    /**
     * Remove walls in the range given starting at the position given.
     * @param x starting x coordinate
     * @param y starting y coordinate
     * @param width range X-Axis.
     * @param height range Y-Axis.
     * @return true if successful.
     */
    public boolean removeWalls(int x, int y, int width, int height)
    {
        //check bounds
        if(x < 0 || y < 0 || ((x + width) > WIDTH) || ((y + height) > HEIGHT) || (width < 1) || (height < 1))
            return false;
            
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(grid[y+i][x+j] == 'D') 
                {
                    numberOfDoors--;
                    grid[y+i][x+j] = 'w';
                }
                
                if(grid[y+i][x+j] == 'w')
                    grid[y+i][x+j] = '_';
                    
            }
        }
        
        return true;
    }
    
    /**
     * Add a door on a wall.
     * @param x
     * @param y 
     */
    public boolean addDoor(int x, int y)
    {
        if(grid[y][x]=='w')
        {
            grid[y][x]='D';
            numberOfDoors++;
            return true;
        }
        else
            return false;
    }
    
    
    public char[][] getGrid(){ return grid; }
    public int getNumberOfRegions(){return numberOfRegions;}
    public int getNumberOfDoors(){return numberOfDoors;}
    
    
    
}
