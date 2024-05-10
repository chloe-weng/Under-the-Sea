import java.awt.*;
import java.awt.RenderingHints.Key;

import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class underTheSea
{
    static File file;
    static AudioInputStream stream;
    static Clip music;

    public static void main(String...args) throws Exception
    {
        /*
        file = new File("music.wav");//File must be .WAV, .AU, or .AIFF
        stream = AudioSystem.getAudioInputStream(file);
        music = AudioSystem.getClip();
        music.open(stream);
        music.start(); //Start the music
        music.loop(Clip.LOOP_CONTINUOUSLY); //   Loop the music
         */

        JFrame j = new JFrame();  //JFrame is the window; window is a depricated class
        MyPanelb m = new MyPanelb();
        j.setSize(m.getSize());
        j.add(m); //adds the panel to the frame so that the picture will be drawn
        //use setContentPane() sometimes works better then just add b/c of greater efficiency.

        j.setVisible(true); //allows the frame to be shown.
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the dialog box exit when you click the "x" button.
    }


}


class MyPanelb extends JPanel implements ActionListener, KeyListener, MouseListener
{
    private Timer time;
    private int argoX, argoY;
    private Image argo;
    private int addlvl1;

    // waves bg variables
    private int wave1X, wave1Y, wave2X, wave2Y, wave3Y, wave4Y;
    private Image wave1_1, wave1_2, wave2_1, wave2_2, wave3_1, wave3_2, wave4_1, wave4_2;

    //powerup variables
    private int shieldX, shieldY, heartpX, heartpY;
    private boolean shieldVisible, heartVisible, argowithShield;
    private long shieldTime;
    private Image powerup;
    private Image argoWithShield;
    
    // intro variables
    private Image[] nextArray;
    private int nextIndex=0;
    private double timeDec=60;
    private int mouseX, mouseY;
    private boolean timeStart=false,timeEnd=false;
    private int numLives=3;
    private Image cover, intro, instr1, lvl1, button;
    
    private Image[] nextArray2;
    private int nextIndex2=0;
    private double timeDec2=60;
    private boolean timeStart2=false,timeEnd2=false;
    private int numLives2=3;
    private Image instr2, lvl2,button2;

    // monster variables
    private int monster1X, monster1Y, monster1Type;
    private int monster2X, monster2Y, monster2Type;
    private boolean resetMonster1, resetMonster2;
    
    // winlose
    private Image loseScreen,winScreen;
    
    // lives
    private Image life1, life2, life3, life4;

    MyPanelb()
    {
        time = new Timer(5, this); //sets delay to 15 millis and calls the actionPerformed of this class.
        setSize(1500, 700);
        setVisible(true); // calls the paintComponent method

        time.start();

        // initialize variables
        addlvl1 = 5;
        argoX = 650;
        argoY = 250;

        // image initialization
        try {
            // intro images
        	cover = ImageIO.read(new File("Cover.png"));
            intro = ImageIO.read(new File("Introduction.png"));
            instr1 = ImageIO.read(new File("Instructions Lvl 1.png"));
            lvl1 = ImageIO.read(new File("Level 1.png"));
            button = ImageIO.read(new File("nextSprite.png"));
            
            instr2 = ImageIO.read(new File("Instructions Lvl 2.png"));
            lvl2 = ImageIO.read(new File("Level 2.png"));
            button2 = ImageIO.read(new File("nextSprite.png"));

            // wave images
            wave1_1 = ImageIO.read(new File("wavesSprite.png"));
            wave1_2 = ImageIO.read(new File("wavesSprite.png"));
            wave2_1 = ImageIO.read(new File("wavesSprite.png"));
            wave2_2 = ImageIO.read(new File("wavesSprite.png"));
            wave3_1 = ImageIO.read(new File("wavesSprite.png"));
            wave3_2 = ImageIO.read(new File("wavesSprite.png"));
            wave4_1 = ImageIO.read(new File("wavesSprite.png"));
            wave4_2 = ImageIO.read(new File("wavesSprite.png"));

            // argo
            argo = ImageIO.read(new File("argoSprite.png"));

            // powerups
            powerup = ImageIO.read(new File("powerupSprite.png"));
            argoWithShield = ImageIO.read(new File("argoWithShieldSprite.png"));

            // lives
            life1 = ImageIO.read(new File("livesSprite.png"));
            life2 = ImageIO.read(new File("livesSprite.png"));
            life3 = ImageIO.read(new File("livesSprite.png"));
            life4 = ImageIO.read(new File("livesSprite.png"));
            
            //winlose
            loseScreen = ImageIO.read(new File("Lose Screen.png"));
            winScreen = ImageIO.read(new File("Win Screen.png"));

        }
        catch (Exception e) {}

        // waves bg variables
        wave1X = 0;
        wave1Y = 480;
        wave2X = 0;
        wave2Y = 320;
        wave3Y = 160;
        wave4Y = 0;


        //powerup variables
        shieldX = 0;
        shieldY = 0;
        heartpX = 0; 
        heartpY = 0;
        shieldVisible = false;
        heartVisible = false;
        argowithShield = false;

        // monster variables
        monster1X = 1300;
        monster2X = 1450;
        monster1Y = monster2Y = 0;
        monster1Type = monster2Type = 0;
        resetMonster1 = resetMonster2 = true;


        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
    }


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1500,700);

        drawIntro1(g);
        drawButton(g);
        
        if(timeStart)
        {
            // draw background waves
        	drawWave4(g);
        	drawWave3(g);
        	drawWave2(g);
        	drawWave1(g);
        	
        	// draw lives
        	if(numLives==3)
        	{
        		drawLife1(g);
        		drawLife2(g);
            	drawLife3(g);
        	}
        	else if(numLives==2)
        	{
        		drawLife1(g);
            	drawLife2(g);
        	}
        	else if(numLives==1)
        	
        	{
        		drawLife1(g);
        	}
        	else if(numLives>=4)
        	{
        		drawLife1(g);
        		drawLife2(g);
            	drawLife3(g);
        		drawLife4(g);
        	}
        	
            // draw kraken and siren
            drawMonster1(g);
            drawMonster2(g);
            
        	// scarlett
        	if(argowithShield)
        	{
        		drawArgoWithShield(g);
        	}
         	else drawArgo(g);

             if(shieldVisible){drawShield(g);}
    
             // drawHeart (2)
            if(heartVisible){drawHeart(g);}

        }
        if(timeEnd)
        {
        	if(numLives<=0)
        	{
        		drawLose(g);
        	}
        	else
        	{
        		drawIntro2(g);
        		drawButton2(g);
        		if(timeStart2)
        		{
        			// draw background waves
                	drawWave4(g);
                	drawWave3(g);
                	drawWave2(g);
                	drawWave1(g);
                	
                	// draw lives
                	if(numLives==3)
                	{
                		drawLife1(g);
                		drawLife2(g);
                    	drawLife3(g);
                	}
                	else if(numLives==2)
                	{
                		drawLife1(g);
                    	drawLife2(g);
                	}
                	else if(numLives==1)
                	
                	{
                		drawLife1(g);
                	}
                	else if(numLives>=4)
                	{
                		drawLife1(g);
                		drawLife2(g);
                    	drawLife3(g);
                		drawLife4(g);
                	}
                	
                    // draw kraken and siren
                    drawMonster1(g);
                    drawMonster2(g);
                    
                	// scarlett
                	if(argowithShield)
                	{
                		drawArgoWithShield(g);
                	}
                 	else drawArgo(g);

                     if(shieldVisible){drawShield(g);}
            
                     // drawHeart (2)
                    if(heartVisible){drawHeart(g);}
        		}
        	}
        }
    }
    //chloe workspace
    
    	//intro
    	//new timer
    	//reset lives
    	//music
    	//sound effects
    
    public void drawIntro2(Graphics g)
    {
        nextArray2 = new Image[2];
       	nextArray2[0] = instr2.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
       	nextArray2[1] = lvl2.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
       	
       	g.drawImage(button2.getScaledInstance(100,100,Image.SCALE_DEFAULT),1350,500,null);
        	
        	
       	if(nextIndex2==0)
       	{
       		g.drawImage(nextArray2[0],0,0,null);
    	}
        else if(nextIndex2==1)
       	{
       		g.drawImage(nextArray2[1],0,0,null);
       	}
        else
        {
        	timeStart2 = true;
        	timeStart=false;
        }
    	
    }
    
    //jessica workspace
    
    	//monsters (2 more)
    	//increase speed of monsters
    	//add boulder?
    	//call waves again (make faster)
    	//sound effects
    
    //scarlett workspace
    
    	//call argo again
    	//powerups + bad powerup
    	//make argo faster
    	//sound effects
    
    
    public void drawIntro1(Graphics g)
    {
        	nextArray = new Image[4];
        	nextArray[0] = cover.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[1] = intro.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[2] = instr1.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	nextArray[3] = lvl1.getScaledInstance(1500,700,Image.SCALE_DEFAULT);
        	
        	g.drawImage(button.getScaledInstance(100,100,Image.SCALE_DEFAULT),1350,500,null);
        	
        	
        	if(nextIndex==0)
        	{
        		g.drawImage(nextArray[0],0,0,null);
        	}
        	else if(nextIndex==1)
        	{
        		g.drawImage(nextArray[1],0,0,null);
        	}
        	else if(nextIndex==2)
        	{
        		g.drawImage(nextArray[2],0,0,null);
        	}
        	else if(nextIndex==3)
        	{
        		g.drawImage(nextArray[3],0,0,null);
        	}
        	else
        	{
        		timeStart = true;
        	}
    	
    }
    public void drawButton(Graphics g)
    {
        g.drawImage(button.getScaledInstance(100,100,Image.SCALE_DEFAULT),1120,450,null);
    	
    }
    public void drawButton2(Graphics g)
    {
        g.drawImage(button2.getScaledInstance(100,100,Image.SCALE_DEFAULT),1120,450,null);
    	
    }
    public void drawWin(Graphics g)
    {
    	g.drawImage(winScreen.getScaledInstance(1500,700,Image.SCALE_DEFAULT),0,0,null);
    	
    }
    public void drawLose(Graphics g)
    {    		
    	g.drawImage(loseScreen.getScaledInstance(1500,700,Image.SCALE_DEFAULT),0,0,null);	
    }
    public void drawArgo(Graphics g)
    {
        g.drawImage(argo, argoX, argoY, 150,150,null);
    }

    public void drawWave1(Graphics g) {
        g.drawImage(wave1_1, wave1X, wave1Y, null);
        g.drawImage(wave1_2, wave1X + 1497, wave1Y, null);
    }

    public void drawWave2(Graphics g) {
        g.drawImage(wave2_1, wave2X, wave2Y,null);
        g.drawImage(wave2_2, wave2X - 1497, wave2Y, null);
    }

    public void drawWave3(Graphics g) {
        g.drawImage(wave3_1, wave1X, wave3Y,null);
        g.drawImage(wave3_2, wave1X + 1497, wave3Y, null);
    }

    public void drawWave4(Graphics g) {
        g.drawImage(wave4_1, wave2X, wave4Y,null);
        g.drawImage(wave4_2, wave2X - 1497, wave4Y, null);
    }

    public void drawShield(Graphics g)
    {
        g.drawImage(powerup, shieldX, shieldY, 50,50,null);
    
    }

    public void drawHeart(Graphics g)
    {
        g.drawImage(powerup, heartpX, heartpY, 50,50,null);
    
    }

    public void drawArgoWithShield(Graphics g)
    {
        g.drawImage(argoWithShield, argoX, argoY, 200,200,null);
    }

    private boolean touchingPowerup(int pwpX, int pwpY, int argoX, int argoY) {
        return pwpX < argoX + 150 &&
               pwpX + 50 > argoX &&
               pwpY < argoY + 150 &&
               pwpY + 50 > argoY;
    }

    private void drawMonster1(Graphics g) {
        Image monster;
        int[] randY = {wave1Y, wave2Y, wave3Y, wave4Y};

        if(resetMonster1) { // at or reset to the front
            resetMonster1 = false;
            int chooseY = (int) (Math.random() * 4) + 1;
            // below MIGHT change depending on height of the sprite, might need to crop siren and reupload to git
            monster1Y = randY[chooseY - 1];
            monster1X = 1300;

            monster1Type = (int) (Math.random() * 2) + 1;
            if(monster1Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y - 10, 170, 170, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y - 17, 190, 190, null);
                }
                catch(Exception e) {}
            }
        }
        else {
            if(monster1Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y - 10, 170, 170, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster1X, monster1Y - 17, 190, 190, null);
                }
                catch(Exception e) {}
            }
        }
    }


    private void drawMonster2(Graphics g) {
        Image monster;
        int[] randY = {wave1Y, wave2Y, wave3Y, wave4Y};

        if(resetMonster2) { // at or reset to the front
            resetMonster2 = false;
            int chooseY = (int) (Math.random() * 4) + 1;
            // below MIGHT change depending on height of the sprite, might need to crop siren and reupload to git
            monster2Y = randY[chooseY - 1];
            monster2X = 1450;

            monster2Type = (int) (Math.random() * 2) + 1;
            if(monster2Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster2X, monster2Y - 10, 170, 170, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster2X, monster2Y - 17, 190, 190, null);
                }
                catch(Exception e) {}
            }
        }
        else {
            if(monster2Type == 1) {
                try {
                    monster = ImageIO.read(new File("krakenSprite.png"));
                    g.drawImage(monster, monster2X, monster2Y - 10, 170, 170, null);
                }
                catch(Exception e) {}
            }
            else {
                try {
                    monster = ImageIO.read(new File("sirenSprite.png"));
                    g.drawImage(monster, monster2X, monster2Y - 17, 190, 190, null);
                }
                catch(Exception e) {}
            }
        }
    }


    
    public void drawLife1(Graphics g)
    {
        g.drawImage(life1.getScaledInstance(30, 30, Image.SCALE_DEFAULT),50,50,null);
    }
    public void drawLife2(Graphics g)
    {
        g.drawImage(life2.getScaledInstance(30, 30, Image.SCALE_DEFAULT),100,50,null);
    }
    public void drawLife3(Graphics g)
    {
        g.drawImage(life3.getScaledInstance(30, 30, Image.SCALE_DEFAULT),150,50,null);
    }
    public void drawLife4(Graphics g)
    {
        g.drawImage(life4.getScaledInstance(30, 30, Image.SCALE_DEFAULT),200,50,null);
    }

    public void actionPerformed(ActionEvent e)
    {
    	if(timeStart)
    	{
    		timeDec-=0.15;
    		if(timeDec<=0 || numLives==0)
        	{
        		timeEnd=true;
        		timeStart=false;
        	}

            //argo movement

            argoX -= addlvl1;
            argoX -= addlvl1;
            if(argoX< -100||argoX>= 1450)
            {
                numLives--;
                argoX = 650;
                argoY = 250;
            }

            if(argoY< -100|argoY>= 650)
            {
                numLives--;
                argoX = 650;
                argoY = 250;
            }


            //  waves bg movement
            wave1X -= addlvl1;
            if(wave1X < -1496)
                wave1X = 0;

            wave2X += addlvl1;
            if(wave2X > 1500)
                wave2X = 0;

            //powerup movement
            shieldX -= addlvl1;
            heartpX -= addlvl1;

            //shield powerup mechanics
            if(!shieldVisible && Math.random() < 0.008)
            {
                shieldX = 1450;
                shieldY = (int) (Math.random() * 650);
                shieldVisible = true;
            }

            if(shieldVisible && shieldX < -50)
            {
                shieldVisible = false;
            }

            if(shieldVisible && (touchingPowerup(shieldX, shieldY, argoX, argoY)))
            {
                shieldVisible = false;
                argowithShield = true;
                shieldTime = System.currentTimeMillis();

            }
            if (argowithShield && System.currentTimeMillis() - shieldTime >= 5000) {
                argowithShield = false;
            }

            //heart powerup mechanics
            if(!heartVisible && Math.random() < 0.007)
            {
                heartpX = 1450;
                heartpY = (int) (Math.random() * 650);
                heartVisible = true;
            }

            if(heartVisible && heartpX < -50)
            {
                heartVisible = false;
            }

            if(heartVisible && (touchingPowerup(heartpX, heartpY, argoX, argoY)))
            {
                heartVisible = false;
                if(numLives<4)
                    numLives++;
            }

            // monster movement
            monster1X -= addlvl1 + 20;
            if(!argowithShield && monster1X < argoX + 100 && monster1X + 100 > argoX && monster1Y < argoY + 100 && monster1Y + 100 > argoY) {
                resetMonster1 = true;
                numLives--;
            }
            if(monster1X + 150 < 0)
                resetMonster1 = true;

            monster2X -= addlvl1 + 20;
            if(!argowithShield && monster2X < argoX + 100 && monster2X + 100 > argoX && monster2Y < argoY + 100 && monster2Y + 100 > argoY) {
                resetMonster2 = true;
                numLives--;
            }
            if(monster2X + 150 < 0)
                resetMonster2 = true;
    	}


        repaint();
    }


    public void keyPressed(KeyEvent e) {
        // Moves the argoSprite
        if(e.getKeyCode() == KeyEvent.VK_UP)
            argoY-=35;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            argoY+=35;
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            argoX -=35;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            argoX +=100;


        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(MouseEvent e){
    	
    	 Point pos = e.getPoint();
    	 mouseX = pos.x;
         mouseY = pos.y;
     	
         // nextbutton
         if(mouseX>=1120 && mouseX<=1220 && mouseY>=450 && mouseY<=550)
         {
         	nextIndex++;
         }
         if(timeEnd && mouseX>=1120 && mouseX<=1220 && mouseY>=450 && mouseY<=550)
         {
         	nextIndex2++;
         }
         

        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}


}
