import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class BattleshipsFrame extends JFrame
{
   //Variables
   private JButton[][] buttonArray;
   private final String[] names = {"A1", "B1", "C1", "D1", "A2", "B2", "C2", "D2", "A3", "B3", "C3", "D3", "A4", "B4", "C4", "D4"};
  
  //The array object of BattleshipGame
   private  BattleshipsGame myGame;
   private JLabel livesLabel;
   private JLabel shipLabel;
      
   private int lives;
   private int ships;
            
   //An array to hold images
   private JLabel[] heartsArray;
   private JLabel[] boatsArray;
   
   //imageIcons
   private ImageIcon hitShip;
   private ImageIcon waterIcon; 
   private ImageIcon heart;
   private ImageIcon boat;
   
   //JMenuItems
   private JMenuItem easyItem;
   private JMenuItem intermediateItem;
   private JMenuItem difficultItem;
   
   //The result labels - Hit or Miss and Score  
   private JLabel targetLabel;
   private JLabel scoreLabel;
   
   private int lifePts;//set in createImplementGame()
   private int shipPts;//set in createImplementGame()
   private int totalScore;
   
   //set deimensions of frame
   private static final int FRAME_WIDTH = 650;
   private static final int FRAME_HEIGHT = 750;
   
   //Constructor
   public BattleshipsFrame()
   {
      setSize(FRAME_WIDTH,FRAME_HEIGHT);
      setResizable(false);
                  
      //helper methods
      createMenuBar();
      InstigateStdGame();
      add(createBattleshipBanner(), BorderLayout.NORTH);
      createButtons();
      add(createControlPanel(), BorderLayout.SOUTH);
   }
   
   //Methods   
   /*This method creates the button in the grid labelling them A1 to D4*/
   public void createButtons()
   {
      JPanel buttonPanel = new JPanel();
      
      //initialising the button array
      buttonArray = new JButton[4][4];
      
      //set layout of buttonPanel      
      buttonPanel.setLayout(new GridLayout(4,4));
            
      ////////////////inner class//////////////////
      class ButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            createImplementGame(event);
         }
      }
      //////////////////////////////////////////////
      
      //initialise litener
      ButtonListener listener = new ButtonListener();
     
     // Buttons into 2D array then into panel
      int i=0;
      for(int r=0; r<4; r++)
      {
         for(int c =0;c <4; c++)
         {
            buttonArray[r][c]= new JButton(names[i]);
            buttonArray[r][c].addActionListener(listener);
            buttonPanel.add(buttonArray[r][c]);
            i++;
         }
      }
      add(buttonPanel, BorderLayout.CENTER);
          
   }
   
   /*This initialises a standard game with seven lives and six ships*/
   public void InstigateStdGame()
   {
      myGame = new BattleshipsGame();
   }  
     
   /*Creating all the different elements for the menubar*/
   public void createMenuBar()
   {
      JMenuBar menuBar = new JMenuBar();
      
      menuBar.add(createFileMenu());
      menuBar.add(createOptionMenu());
      menuBar.add(createHelpMenu());
      //set menuBar in the frame
      setJMenuBar(menuBar);
   }
   /*The following three methods are menus found in menu bar*/
   public JMenu createFileMenu()
   {
      JMenu fileMenu = new JMenu("File");
      
      fileMenu.add(createNewGame());
      fileMenu.add(Quit());
      fileMenu.add(createExit());
      return fileMenu;
   }
   
   public JMenu createOptionMenu()
   {
      JMenu optionMenu = new JMenu("Options");
      
      optionMenu.add(createLevelMenu());
      return optionMenu;
   }
   
   public JMenu createHelpMenu()
   {
      JMenu helpMenu = new JMenu("Help");
      
      helpMenu.add(createInstruction());
      return helpMenu;
   }
   
   public JMenuItem createInstruction()
   {
      JMenuItem instructionItem = new JMenuItem("Instructions");
      
      /////////////inner class/////////////////////////////////////////////
      class InstructionListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            JOptionPane.showMessageDialog(null, "The aim of the game is to sink all the ships"
               +" before losing all your lives.\n\n"
               +"There are three levels to choose from:\n\n"+ "1. Easy:  7 lives and 6 ships."
               + "\n2. Intermediate:  6 lives 6 ships\n3. Difficult:  5 lives 6 ships\n\n"
               +"Each ship is worth 10 points while 5 bonus points are rewarded for each remaining life","Instructions",JOptionPane.INFORMATION_MESSAGE);
         }
      }
      
      ///////////////////////////////////////////////////////////////////////
      InstructionListener instructionListener = new InstructionListener();
      
      instructionItem.addActionListener(instructionListener);
      
      return instructionItem;
   }
   /*Method that creates the meni for different levels*/
   public JMenu createLevelMenu()
   {
      JMenu levelMenu = new JMenu("Level");
      
      levelMenu.add(createEasyLevel());
      levelMenu.add(createIntermediateLevel());
      levelMenu.add(createDifficultLevel());
      
      return levelMenu;
   }
   /*Method to create the new game item menu*/
   public JMenuItem createNewGame()
   {
      JMenuItem newGame = new JMenuItem("New Game");//exit may need another method???
               
     ////////////////inner class//////////////////
      class NewGameListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            newGame();           
         }
         
      }
      //////////////////////////////////////////////
      
      //initialise litener
      NewGameListener newGameListener = new NewGameListener();
      
      newGame.addActionListener(newGameListener);
      return newGame;
   }
   
   /*Method to create the exit item */
   
   public JMenuItem createExit()
   {
      JMenuItem exitItem = new JMenuItem("Exit");
      
      /////////////inner class///////////////////////////
      class ExitButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            int dialogExit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
            if(dialogExit == JOptionPane.YES_OPTION)
               System.exit(0);
         }
      }
   
      ///////////////////////////////////////////////////
      
      ExitButtonListener exitListener = new ExitButtonListener();
      
      exitItem.addActionListener(exitListener);
      return exitItem;
   }
   
   /*Method to create the menu item to quit the game*/
   
   public JMenuItem Quit()
   {
      JMenuItem quitItem = new JMenuItem("Quit");
      
      //////////////inner class ///////////
      class QuitListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
            int dialogQuit = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            if(dialogQuit == JOptionPane.YES_OPTION)
               createQuit(event);   
         }
      }
      ///////////////////////////////////////////////////// 
      
      QuitListener quitListener = new QuitListener();
      
      quitItem.addActionListener(quitListener);
      return quitItem;
   }
   
   /*The following three methods create the menu items that action the three
   levels in the game - easy, intermediate and difficult*/
   
   public JMenuItem createEasyLevel()
   {
      JMenuItem easyItem = new JMenuItem("Easy");
      
      //////////inner class/////////////////////////
      class EasyListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {   
            newGame();         
         }
      }
      ///////////////////////////////////////////////
      
      EasyListener easyListener = new EasyListener();
      
      easyItem.addActionListener(easyListener);
      
      return easyItem;
   }
   
   public JMenuItem createIntermediateLevel()
   {
      JMenuItem intermediateItem = new JMenuItem("Intermediate");
      
      //////////inner class////////////////////////////////
      class IntermediateListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {   
            intermediateNewGame();
         }
      }
      /////////////////////////////////////////////////////
      IntermediateListener intermediateListener = new IntermediateListener();
      
      intermediateItem.addActionListener(intermediateListener);
      return intermediateItem;
   }

   
   public JMenuItem createDifficultLevel()
   {
      JMenuItem difficultItem = new JMenuItem("Difficult");
      
      //////////////inner class////////////////////////////
      class DifficultListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {   
            difficultNewGame();         
         }
      }
      /////////////////////////////////////////////////////
      DifficultListener difficultListener = new DifficultListener();
      
      difficultItem.addActionListener(difficultListener);
      
      return difficultItem;
   }
         
   /*
      The control panel returns displayPanel and ResultPanel
   */
   public JPanel createControlPanel()
   {
      JPanel controlPanel = new JPanel();
      
      controlPanel.setLayout(new GridLayout(1,2));
      controlPanel.add(createDisplayPanel());
      controlPanel.add(createResultPanel());
      return controlPanel;
   }
   
   /*Method that displays heart and ship images (for lives and ships remaining*/   
   public JPanel createDisplayPanel()
   {
      JPanel displayPanel = new JPanel();
      displayPanel.setLayout(new GridLayout(2,2));
           
      //sub panels
      JPanel subPanel1 = new JPanel();
      JPanel subPanel2 = new JPanel();
      JPanel subPanel3 = new JPanel();
      JPanel subPanel4 = new JPanel();
      
      //Labels to identifying lives and ships remaining
      livesLabel = new JLabel("Lives Remaining: ");
      shipLabel = new JLabel("Ships Remaining: ");
            
      //intialise lives and ships variables
      lives = (myGame.getLives());
      ships = (6-myGame.getHits());
   
      //set number of elements in array as that of no of lives/hits
      heartsArray = new JLabel[lives];
      boatsArray = new JLabel[ships];
      
      //set image icons
      heart = new ImageIcon("images/weeHeart.jpg");
      boat = new ImageIcon("images/tinyBoat.jpg");
        
      //input elements into both arrays and add to subpanels    
      for(int i=0; i<lives; i++)
      {
         heartsArray[i] = new JLabel(heart);
         subPanel2.add(heartsArray[i]);
      }
      
      for(int j=0; j<ships; j++)
      {
         boatsArray[j] = new JLabel(boat);
         subPanel4.add(boatsArray[j]);
      }
   
      //add label to subpanel
      subPanel1.add(livesLabel);
      subPanel3.add(shipLabel);
      //add subpanel to display panel
      displayPanel.add(subPanel1);
      displayPanel.add(subPanel2);
      displayPanel.add(subPanel3);
      displayPanel.add(subPanel4);
      
      displayPanel.setBorder(new TitledBorder(new EtchedBorder(), "Display"));
      return displayPanel;      
   }
   
   /* Sets the heart icons in the heartArray to invisible*/
   public void removeHeart()
   {
      lives = (myGame.getLives());
      heartsArray[lives].setVisible(false);  
   }
   
   public void removeBoat()
   {
      ships = (myGame.getHits());
      boatsArray[ships-1].setVisible(false);
   }
   
            
            
   /*new game for easy level and new game on Menu - Default game*/
   public void newGame()
   {
      InstigateStdGame(); 
      reset();                 
      lives = (myGame.getLives());
      ships = (6-myGame.getHits());
      lifePts = 7;

      reinstateLivesAndShips();
      
   }
   
 
  /*Methods to set intermediate and difficult levels*/ 
   public void intermediateNewGame()
   {
      reset();
      myGame = new BattleshipsGame(6,6);
            
      lives = (myGame.getLives());
      ships = (6-myGame.getHits());
      
      lifePts = 6;
      reinstateLivesAndShips();
      
   }
   
   public void difficultNewGame()
   {
      reset();
      myGame = new BattleshipsGame(5,6);
      
      lives = (myGame.getLives());
      ships = (6-myGame.getHits());
      
      lifePts = 5;
         
      reinstateLivesAndShips();
      
   }
   /*The following to methods deal with the score of the game*/
   public void score()
   {
       lifePts = myGame.getLives();
       shipPts = myGame.getHits(); 
       totalScore = (myGame.getHits() * 10);
       String str = " " + totalScore;
       scoreLabel.setText (str);
   }
   
   public void winningScore()
   {
      totalScore = ((shipPts *10) + (lifePts *5));
      String str = " " + totalScore;
      scoreLabel.setText (str);
   
   }
      
   /* This method determines the outcome of the game */
   public void createImplementGame(ActionEvent e)
   {
      hitShip = new ImageIcon("images/Ship.jpg");
      waterIcon = new ImageIcon("images/Water.jpg");
                          
      for(int r=0; r<4; r++)
      {
         for(int c =0;c <4; c++)
         {
            if (e.getSource()== buttonArray[r][c])
            {
               if(myGame.getLives()>0 && myGame.getHits()<6)
               {
                  if(myGame.checkIfShip(r,c))
                  {
                     targetLabel.setText(myGame.shoot(r,c));
                     buttonArray[r][c].setIcon(hitShip);
                     removeBoat();
                     score();
                  }
                  else
                  {
                     targetLabel.setText(myGame.shoot(r,c));
                     buttonArray[r][c].setIcon(waterIcon);
                     removeHeart();
                     
                  }
               }
               if (myGame.getLives()== 0)
               {
                  JOptionPane.showMessageDialog(null, "Sorry. You Lose!!\n\n You Scored "+ totalScore  +" points. You hit " 
                  + myGame.getHits()+ " ships" , "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                  
                  for(int i=0; i<4; i++)
                  {
                     for(int j =0;j <4; j++)
                     {
                        if(myGame.checkIfShip(i,j))
                        {
                           buttonArray[i][j].setIcon(hitShip);
                        }
                        else
                        {
                           buttonArray[i][j].setIcon(waterIcon);
                        }
                        buttonArray[i][j].setEnabled(false);
                     }
                  }
               }
               else if(myGame.getHits()== 6)
               {
                  
                  winningScore();
                  
                  JOptionPane.showMessageDialog(null, "CONGRATULATIONS!!! YOU WIN!!\n\n You Scored "+
                  ((shipPts *10) + (lifePts *5)) + " points. You got "+(shipPts *10)+" standard points\nand "
                  +(lifePts * 5)+ " bonus points","GAME OVER", JOptionPane.PLAIN_MESSAGE);
                     
                  for(int z=0; z<4; z++)
                  {
                     for(int t =0;t <4; t++)
                     {
                     
                        if(myGame.checkIfShip(z,t))
                        {
                           buttonArray[z][t].setIcon(hitShip);
                        }
                        else
                        {
                           buttonArray[z][t].setIcon(waterIcon);
                        }
                        buttonArray[z][t].setEnabled(false);
                     
                     }
                  }
               }
            } 
         }
      }
   }
   
   public JPanel createResultPanel()
   {
      JPanel resultPanel = new JPanel();
      resultPanel.setLayout(new GridLayout(2,2));
                 
      resultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Results"));
      
      targetLabel = new JLabel();
      scoreLabel = new JLabel("0");
      
      resultPanel.add(new JLabel("Target: "));
      resultPanel.add(targetLabel);
      resultPanel.add(new JLabel("Score: "));
      resultPanel.add(scoreLabel);
      return resultPanel;
   }

   
    /*Method to reveal images and disable button */
   public void createQuit(ActionEvent e)
   {
      int i=0;
      
      for(int r=0; r<4; r++)
      {
         for(int c= 0; c<4; c++)
         {
            if(myGame.checkIfShip(r,c))
            {
               buttonArray[r][c].setIcon(hitShip);
            }
            else
            {
               buttonArray[r][c].setIcon(waterIcon);
            }
            buttonArray[r][c].setEnabled(false);
         }
      }
   }


   /*method to reset buttons, display panel and result label */
   public void reset()
   {
      //resets buttons 
      int i=0;
      for(int r=0; r<4; r++)
      {
         for(int c =0;c <4; c++)
         {
            buttonArray[r][c].setIcon(null);//clear any icons on buttonArray
            buttonArray[r][c].setText(names[i]);
            i++;
            buttonArray[r][c].setEnabled(true);
         }
      }
      
      //sets images in heartArray and boatArray to false
      for(int k=0; k< heartsArray.length; k++)
      {
         heartsArray[k].setVisible(false);
      }
      for(int j=0; j<ships; j++)
      {
         boatsArray[j].setVisible(false);
      }
      //resets resultLabel
      targetLabel.setText(" ");
      scoreLabel.setText("0");
   }
   
   public void reinstateLivesAndShips()
   {
      for(int i=0; i< lives; i++)
      {
         heartsArray[i].setVisible(true);
      }
      
      for(int j=0; j< ships; j++)
      {
         boatsArray[j].setVisible(true);
      }
   }
   
   /*
      This method may be used to display the Battleship Banner
   */
   
   public JLabel createBattleshipBanner()
   {
      ImageIcon battleshipIcon = new ImageIcon("images/BattleshipBanner.jpg");
   
      JLabel label = new JLabel(battleshipIcon);            
      return label;
   }

}//end class