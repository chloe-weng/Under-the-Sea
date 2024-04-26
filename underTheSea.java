import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import javax.sound.sampled.*;

//chloe
// scarlett
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
        music.loop(Clip.LOOP_CONTINUOUSLY); //Loop the music
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
    private int x,y;
    private int wineY, chamY;
    private int addX, addY;
    private boolean flower;


    MyPanelb()
    {
        time = new Timer(5, this); //sets delay to 15 millis and calls the actionPerformed of this class.
        setSize(1500, 700);
        setVisible(true); //it's like calling the repaint method.
        time.start();

        // add variables

        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);
    }


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1500,700);
        drawStillLife(g, flower);
        drawAnimation(g, x, y, wineY, chamY);


    }


    public void drawStillLife(Graphics g, boolean flower)
    {
        // bg imported image
        Image bg;
        try{
            bg = ImageIO.read(new File("abstractbg.jpg"));
            g.drawImage(bg, 0, 0, null);
        }
        catch(Exception e) {}




        // FLOWERS IMPORTED IMAGE
        if(flower) {
            Image flowers;
            try {
                flowers = ImageIO.read(new File("flowers1.png"));
                g.drawImage(flowers, 635, 40, null);
            } catch (Exception e) {
            }
        }
        else {
            Image flowers2;
            try {
                flowers2 = ImageIO.read(new File("flowers2.png"));
                g.drawImage(flowers2, 670, 95, null);
            } catch (Exception e) {
            }
        }


        // IMPORTED IMAGE - WINE BOTTLE
        Image wineBottle;
        try {
            wineBottle = ImageIO.read(new File("wine bottle.png"));
            g.drawImage(wineBottle, 625, 90, null);
        } catch (Exception e) {
        }


    }


    public void drawAnimation(Graphics g, int x, int y, int wineY, int chamY) {
        // wings
        if(x % 4 == 0)
            flyWing1(g, x);


    }


    public void flyWing1(Graphics g, int x) {
        g.setColor(Color.WHITE);
        g.fillOval(x+15, y-4, 30, 10);
        g.setColor(Color.BLACK);
        g.drawOval(x+15, y-4, 30, 10);
    }


    public void actionPerformed(ActionEvent e)
    {
        // fly movement
        if (x <= 0)
            x=1200;


        x-=addX;
        y+=addY;


        if(y >= 600)
            addY*=-1;
        if(y <= 30)
            addY*=-1;


        // wine movement
        if(wineY >= 70)
            wineY = 0;
        if(chamY >= 70)
            chamY = 0;


        chamY += 1;
        wineY += 3;






        repaint();
    }


    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode() == KeyEvent.VK_UP)
            y-=35;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            y+=35;


        repaint();
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void mousePressed(MouseEvent e){
        Point pos = e.getPoint();


        x = pos.x;
        y = pos.y;


        addY*=-1;


        if(flower)
            flower = false;
        else
            flower = true;


        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}


}
