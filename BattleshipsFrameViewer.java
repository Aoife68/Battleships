/*
 Name:Aoife
 
 Date: 07/03/2014
 
 File:Battleships
*/

import javax.swing.JFrame;

public class BattleshipsFrameViewer 
{
   public static void main(String[]args)
   {
      JFrame frame = new BattleshipsFrame();
      
      frame.setTitle("Battleships");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
      
   }
}//end class