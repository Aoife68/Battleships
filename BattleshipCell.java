/*
Written by:	Maeve Carr
Date:
Desc: 
Filename:
*/

////NO COMMENTS////////////

public class BattleshipCell
{
	//instance variables (why not private?)
   boolean ship;
   boolean hit;
   
   //methods
   /**
      A getter method that returns a ship
   */	
   public boolean isShip()
   {
      return ship;
   }
   
   /**
      A getter method that returns a hit
   */
	
   public boolean isHit()
   {
      return hit;
   }
	
   public void setToShip()
   {
      ship = true;
   }

   public void setToHit()
   {
      hit = true;
   }
   
   /**
      A toString method that returns result
      Two elements, states the presence of a ship or empty to begin
      During the battle it declares if ship sunk or miss. 
   */
	
   public String toString()
   {
      String s;
   		
      if(ship)
         if(hit)
            s = "Sunk";
         else
            s = "Ship";
      else
         if(hit)
            s = "miss";
         else
            s = "empty";
   	
      return s;
   }


}