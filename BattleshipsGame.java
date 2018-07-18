/*
Written by:	Maeve Carr
Date:
Desc: 
Filename:
*/

/////NO COMMENTS - FIGURE IT OUT


import java.util.*;

public class BattleshipsGame
{
   //instance variables
   private BattleshipCell[][]grid; 
   private int lives;
   private int hits; 
   private int noOfShips; 
	
	//constructor
   public BattleshipsGame()
   {
      grid = new BattleshipCell[4][4];
   	
      initGrid();   
      lives = 7;
      setNoOfShips(6);
   }
   
   //methods
   
   /**
      The method that plays the game. 
      It takes in the no of lives and ships.
   */
   public BattleshipsGame(int livesIn, int shipsIn)
   {
      grid = new BattleshipCell[4][4];
   	
      initGrid();   
      lives = livesIn;//sets the no of lives available
      setNoOfShips(shipsIn);//sets the no of ships to be played
   }
   
   /**
      This method places the ships 
      into 6 positions in the array
   */

   public void initGrid()  
   {
      for(int r = 0; r < 4; r++)
         for(int c = 0; c < 4; c++)
            grid[r][c] = new BattleshipCell();
   }
   
   /**
      This method prints the details of grid to screen
   */
	
   public void showGrid()
   {
      for(int r = 0; r < 4; r++)
      {
         for(int c = 0; c < 4; c++)
            System.out.print(grid[r][c] +" ");
         System.out.println();
      }
   }
   
   /**
      This method randomly generates 4 numbers to 
   */
	
	
   public void setNoOfShips(int noOfShips)
   {
      Random noGen = new Random();
      int count = 0;	
      do
      {
         int r = noGen.nextInt(4);
         int c = noGen.nextInt(4);
         if(!checkIfShip( r,  c))   
         {
            grid[r][c].setToShip(); 
            count++; 
         }
      
      }while(count < noOfShips);
   }
	
   /**
   
   */   
	
   public boolean checkIfShip(int r, int c)
   {
      return grid[r][c].isShip(); 
   }
   
   /**
      getter methods for lives and hits
   */
	
   public int getLives()
   {
      return lives;
   }
	
   public int getHits()
   {
      return hits;
   }	
   
   /**
      
   */
	
   public String shoot(int r, int c)  
   {												
      String s; 
      if(grid[r][c].isHit())
         s = "already chosen";  
      else
      {
         if(grid[r][c].isShip())
         {
            s = "HIT - ship sunk!";
            hits++;
         }
         else 
         {
            s = "Miss!";
            lives--;
         }
      
         grid[r][c].setToHit();
      }
      return s;
   		
   }
	
	

}//end class



