import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Color;
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
public class AppleDrop extends JPanel implements ActionListener, MouseListener
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
    
    private int panelWidth;
    private int panelHeight;
    
    private int time; 

    
    private long pressedTime;
    private long lastPressedTime;
    
    private ArrayList< Apple > apples;
    private Basket[] baskets;
    
    private boolean startBarFill;
    
    private boolean first;
    private boolean[] keys;
    public AppleDrop()
    {
        apples = new ArrayList< Apple >();
        
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
    
    public void setKeys(boolean[] keys)
    {
        this.keys = keys;
    }

  
    int x;
    private void update()
    {
        
        if( keys[  KeyboardListener.Keys.RIGHT.get() ] )
            for( Basket basket : baskets )
                basket.moveRight();
        if( keys[ KeyboardListener.Keys.LEFT.get() ] )
            for( Basket basket : baskets )
                basket.moveLeft();
        if( keys[  KeyboardListener.Keys.SPACE.get() ] )
        {
            for( Apple apple : apples )
            {
                for( Basket basket : baskets )
                    if( basket.contains( apple ) )
                        apple.swat();
                }
        }
       
    }
    
    public void mouseExited(MouseEvent me)
    {
    }
    
    public void mouseEntered(MouseEvent me)
    {
    }
    
    private final int MAX_CLICK_TIME = 5000;
   
    
    public void mouseReleased(MouseEvent me)
    {
        System.out.println(me.getClickCount());
        if( pressedTime - lastPressedTime >= 1000 )
        { 
            
            lastPressedTime = pressedTime;
            long totalTimePressed = System.currentTimeMillis() - pressedTime;
            if(me.getButton() == MouseEvent.BUTTON3 && totalTimePressed < 500)
            {
                apples.add( new Apple( this.panelWidth, this.panelHeight, me.getX() ,true));
                startBarFill = false;
                return;
            }
               
            if(me.getButton() == MouseEvent.BUTTON1 && totalTimePressed < 500 )
            {
                apples.add( new Apple( this.panelWidth, this.panelHeight, me.getX() ) );
                startBarFill = false;
                return;
            }
            
            if(totalTimePressed > MAX_CLICK_TIME)
                totalTimePressed = MAX_CLICK_TIME;
                
            double speed = (totalTimePressed/(double)MAX_CLICK_TIME)*(Apple.MAX_SPEED - 5) ;
            apples.add( new Apple( this.panelWidth, this.panelHeight, me.getX(), (int)speed + 5 ) );
           
        }
        startBarFill = false;
    }
    
    
    public void mousePressed(MouseEvent me)
    {
        pressedTime = System.currentTimeMillis();
        startBarFill = true;
    }
    
    public void mouseClicked(MouseEvent me)
    {
        
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
        for( Basket basket : baskets )
        {
            g.setColor( basket.getColor() );
            g.fillPolygon( basket.getXCoords(), basket.getYCoords(), TRAP_POINTS ); 
            if(  basket.getPast() > 0 )
                g.fillPolygon( basket.getXCoords( true ), basket.getYCoords(), TRAP_POINTS );
        }
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
        if(startBarFill)
        {
            g.setColor(Colour.GREEN);
            double timePorportion = (System.currentTimeMillis() - pressedTime)/(double)MAX_CLICK_TIME;
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
        frame.addMouseListener( ad );
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
