import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Write a description of class AppleDrop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AppleDrop extends JPanel implements ActionListener
{
    private static final int MAX_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getWidth();
    private static final int MAX_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getHeight();
    private static final Rectangle SCREEN_DIMENSIONS = new Rectangle( 0, 0, MAX_WIDTH,
    MAX_HEIGHT );
    
    private static final int QUARTER = 4;
    private static final int THREE = 3;
    private static final int TRAP_POINTS = 4;
    
    private int player1Points;
    private int player2Points;
    
    private int panelWidth;
    private int panelHeight;
    
    private int time; 

    
    private long pressedTime;
    private long lastPressedTime;
    
    private ArrayList< Apple > apples;
    private Basket[] baskets;
    
    private boolean startBarFill;
    private boolean recoilBar;
    
    private boolean first;
    private boolean[] keys;
    public AppleDrop()
    {
        apples = new ArrayList< Apple >();
        player1Points = 0;
        player2Points = 0;
        first = true;
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        for( Apple apple : apples )
             apple.move();
        update();
        repaint();
    }
    
    public Basket[] getBaskets()
    {
        return baskets;
    }
    
    public ArrayList<Apple> getApples()
    {
        return apples;
    }
    
    public void setKeys(boolean[] keys)
    {
        this.keys = keys;
    }
    
    public boolean[] getKeys()
    {
        return keys;
    }
    
    public void setPressedTime(long t)
    {
        pressedTime = t;
    }
    
    public long getPressedTime()
    {
        return pressedTime;
    }
    
    public void setStartBarFill(boolean s)
    {
        startBarFill = s;
    }
  
    int x;
    private void update()
    {
        
        if( keys[  KeyboardListener.RIGHT] )
            for( Basket basket : baskets )
                basket.moveRight();
        if( keys[ KeyboardListener.LEFT ] )
            for( Basket basket : baskets )
                basket.moveLeft();
        if( keys[  KeyboardListener.SPACE] )       {
            System.out.println("Space is clicked");
            for( Apple apple : apples )
            {
                for( Basket basket : baskets )
                    if( basket.contains( apple ) )
                    {
                        apple.swat();
                        if(apple.getColor().equals(Colour.BROWN))
                            player2Points += apple.getPointValue(); 
                        else
                            player1Points += apple.getPointValue();
                    }
                }
        }
        
        for(  int i = 0; i < apples.size(); i ++)
         {
             if(apples.get(i).getSpeed() < 0 )
                continue;
             for( Basket basket : baskets )
                if( basket.contains( apples.get(i) ) )
                 {
                        if(apples.get(i).getColor().equals(Colour.BROWN))
                            player1Points += apples.get(i).getPointValue(); 
                        else
                            player2Points += apples.get(i).getPointValue();
                        apples.remove(i);
                }
                else if(apples.get(i).getY() > getHeight() - basket.getHeight()/2.0)
                {
                     player1Points += apples.get(i).getPointValue();
                     apples.remove(i);
                }
        }
       
    }
    
    
    int size;
    public void paintComponent( Graphics g )
    {
        if( first )
            initialize();
        background( g );
        for( Apple apple : apples )
        {
            g.setColor( apple.getColor() );
            size = apple.getSize();
            g.fillOval( apple.getX(), apple.getY(), size, size );
        }
        
        drawSpeedBar(g);
        drawPoints(g);
        for( Basket basket : baskets )
        {
            g.setColor( basket.getColor() );
            g.fillPolygon( basket.getXCoords(), basket.getYCoords(), TRAP_POINTS ); 
            if(  basket.getPast() > 0 )
                g.fillPolygon( basket.getXCoords( true ), basket.getYCoords(), TRAP_POINTS );
        }
    }
    
    private void drawPoints(Graphics g)
    {
        g.setColor(Colour.BLACK);
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        g.drawString(player1Points + "  /  " + player2Points, (int)(getWidth()/10*1), (int)(getHeight()/12 * 0.5));
    }
    
    private void drawSpeedBar(Graphics g)
    {
        int widthPorportion = getWidth()/10;
        double widthPlacement = 7;
        double widthMultiplier = 2;
        int heightPorportion = getHeight()/12;
        double heightPlacement = 0.5;
        double heightMultiplier = 1;
        

        g.setColor(Colour.WHITE);
        g.fillRect((int)(widthPorportion * widthPlacement), (int)(heightPorportion*heightPlacement),
                    (int)(widthPorportion * widthMultiplier), (int)(heightPorportion * heightMultiplier));
        g.setColor(Colour.BLACK);
        g.drawRect((int)(widthPorportion * widthPlacement), (int)(heightPorportion*heightPlacement),
                    (int)(widthPorportion * widthMultiplier), (int)(heightPorportion * heightMultiplier));
        if(recoilBar)
        {
            g.setColor(Colour.BLUE);
        }
        
        if(startBarFill)
        {
            g.setColor(Colour.GREEN);
            double timePorportion = (System.currentTimeMillis() - pressedTime)/(double)MouseClickListener.MAX_CLICK_TIME;
            if(timePorportion > 1)
                timePorportion = 1;
            g.fillRect((int)(widthPorportion * widthPlacement), (int)(heightPorportion*heightPlacement),
                        (int)(widthPorportion * widthMultiplier * timePorportion), (int)(heightPorportion * heightMultiplier));
        }
    }
    
    private void drawTimer( Graphics g )
    {
        int widthProportion = getWidth() / 10;
    }
    
    private void background( Graphics g )
    {
        super.paintComponent( g );
    }
    private void initialize()
    {
        panelWidth = this.getWidth();
        panelHeight = this.getHeight();
        
        time = 0;
        Timer clock = new Timer( 20 /*what should this be*/, this ); 
        clock.start();
            
        keys = new boolean[4];

        baskets = new Basket[]{ new Basket( panelWidth, panelHeight, panelWidth / 4 ),
      /*  new Basket( panelWidth, panelHeight, 3 * panelWidth / 4 )*/ };
        

        first = false;
    }
    
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "Apple Drop" );
        frame.setBounds( SCREEN_DIMENSIONS );
        
        AppleDrop ad = new AppleDrop();
        
        frame.addKeyListener( new KeyboardListener(ad) );
        frame.addMouseListener( new MouseClickListener(ad) );
        frame.add( ad );
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setAlwaysOnTop( true );
        
        frame.setVisible( true );
    }
    
    /*Player1 = mouse 
     * red apples - slow, left-click + points for p1
     * green apples - faster (variable speed, length held) left-click + more pts for p1
     * brown rotten apples - medium speed, right-click - remove pts for p2
     * 
     * Player2 = keyboard
     * 
     * left/right + <space> swatting away bad apples
     * 
     * 
     * Ideas: 
     * 
     * All speeds should be written as relative to the screen size
     * 
     * 1 point for red and then a point based on a ratio of points to speed
     * 
     * Basket should wrap around to the other side of the screen if it hits one end
     * 
     * 1 minute rounds, number can be adjusted later
     * 
     * Mac:
     * 
     * setExtendedState java
    */
}
