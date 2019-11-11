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
 * A game involving one player dropping apples and another player catching them
 *
 * @author Arnav Parashar and Dana Nigrin
 * @version 11/10/19
 */
public class AppleDrop extends JPanel implements ActionListener
{
    
    private static final int MAX_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getWidth();
    private static final int MAX_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().
    getHeight();
    private static final Rectangle SCREEN_DIMENSIONS = new Rectangle( 0, 0, MAX_WIDTH,
    MAX_HEIGHT );
    
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int SEVEN = 7;
    private static final int FIFTEEN = 15;
    private static final int TWENTY_THREE = 23;
    
    private static final int THIRD = 3;
    private static final int FOURTH = 4; 
    private static final int FIFTH = 5;
    private static final int TENTH = 10;
    private static final int TWELFTH = 12;
    private static final int SIXTEENTH = 16;
    private static final int EIGHTH = 8;
    private static final int TWENTY_FOURTH = 24;
    private static final int THIRTY_SECONDTH = 32;
    
    private static final int FONT_SIZE = 24;
    private static final int HALF_CIRCLE = 180;
    private static final int INCREMENT = 15;
    private static final int TRAP_POINTS = 4;
    private static final int NUM_KEYS = 4;
    private static final int FOURTH_ELEMENT = 3;
    
    private int player1Points;
    private int player2Points;
    
    private int panelWidth;
    private int panelHeight;
    
    private int time; 

    
    private long pressedTime;
    private long lastPressedTime;
    
    private ArrayList< Apple > apples;
    private Basket[] baskets;
    
    private boolean barFill;
    private boolean recoilBar;
    
    private boolean first;
    private boolean[] keys;
    
    /**
     * Constructor to initialize variables for the AppleDrop game
     */
    public AppleDrop()
    {
        apples = new ArrayList< Apple >();
        player1Points = 0;
        player2Points = 0;
        first = true;
    }
    
    /**
     * Whenever the clock is "fired" thsi method will the tell the objects to move 
     * and have them redrawn
     * 
     * @param ae is the ActionEvent of the clock Timer firing
     */
    public void actionPerformed( ActionEvent ae )
    {
        for( Apple apple : apples )
             apple.move();
        update();
        repaint();
    }
    
    /**
     * Returns the arraylist of apples in the game
     * 
     * @return the arraylist of apples in the game
     */
    public ArrayList<Apple> getApples()
    {
        return apples;
    }
    
    /**
     * Returns the key boolean array
     * 
     * @return a boolean array of the keys
     */
    public boolean[] getKeys()
    {
        return keys;
    }
    
    /**
     * Sets when the player pressed the mouse
     * 
     * @param t long time when the player pressed the mouse
     */
    public void setPressedTime( long t )
    {
        pressedTime = t;
    }
    
    /**
     * Returns when the player pressed the mouse as a long
     * 
     * @return when the player pressed the mouse as a long
     */
    public long getPressedTime()
    {
        return pressedTime;
    }
    
    /**
     * Returns the time, as a long, the player previously pressed the mouse
     * 
     * @return long value of the previous time the player pressed the mouse 
     */
    public long getLastPressedTime()
    {
        return lastPressedTime;
    }
    
    /**
     * Sets when the player previously pressed the mouse
     * 
     * @param t long time when the player previously pressed the mouse
     */
    public void setLastPressedTime( long t )
    {
        lastPressedTime = t;
    }
    
    /**
     * Sets whether the bar should fill or not
     * 
     * @param s boolean true if the bar should fill, false otherwise
     */
    public void setBarFill( boolean s )
    {
        barFill = s;
    }
    
    /**
     * Sets whether the recoil bar should fill or not
     * 
     * @param r boolean true if the recoil bar should fill, false otherwise
     */
    public void setRecoilBar( boolean r )
    {
        recoilBar = r;
    }
  
    private void update()
    {
        if( keys[  KeyboardListener.RIGHT] )
            for( Basket basket : baskets )
                basket.moveRight();
        if( keys[ KeyboardListener.LEFT ] )
            for( Basket basket : baskets )
                basket.moveLeft();
        if( keys[  KeyboardListener.SPACE] ) 
            for( Apple apple : apples )
                for( Basket basket : baskets )
                    if( basket.contains( apple ) ) {
                        apple.swat();
                        if(apple.getColor().equals(Colour.BROWN))
                            player2Points += apple.getPointValue(); 
                        else
                            player1Points += apple.getPointValue();
                    }
          
        outer: for(  int i = 0; i < apples.size(); i++ )
        {
            if(apples.get(i).getSpeed() < 0 )
               continue;
            for( Basket basket : baskets )
                if( basket.contains( apples.get(i) ) )
                {
                        if( apples.get(i).getColor().equals( Colour.BROWN ) )
                            player1Points += apples.get(i).getPointValue(); 
                        else
                            player2Points += apples.get(i).getPointValue();
                        apples.remove(i);
                        break outer; 
                }
            if( apples.get(i).getY() > getHeight() - baskets[0].getHeight()/2.0) {
                     player1Points += apples.get(i).getPointValue();
                     apples.remove(i);
            }
        }
    }
    
    /**
     * This method paints the background and the objects in the appropiate location
     * 
     * @param g the Graphics given to the program by the computer running the program
     */
    public void paintComponent( Graphics g )
    {
        if( first )
            initialize();
            
        background( g );
        
        for( Basket basket : baskets )
        {
            g.setColor( Colour.WHITE );
            g.fillPolygon( basket.getXCoords(), basket.getYCoords(), TRAP_POINTS );
            if(  basket.getPast() > 0 )
                g.fillPolygon( basket.getXCoords( true ), basket.getYCoords(),
                TRAP_POINTS );
        }
        
        for( Apple apple : apples )
        {
            g.setColor( apple.getColor() );
            int size = apple.getSize();
            g.fillOval( apple.getX(), apple.getY(), size, size );
        }
        
        for( Basket basket : baskets )
        {
            g.setColor( Colour.CHOCOLATE_TRANSLUCENT );
            g.fillPolygon( basket.getXCoords(), basket.getYCoords(), TRAP_POINTS ); 
            g.setColor( Colour.CHOCOLATE );
            for( int[] line : basket.getLines() )
                g.drawLine( line[0], line[1], line[2], line[ FOURTH_ELEMENT ] );
            if(  basket.getPast() > 0 )
            {    
                g.setColor( Colour.CHOCOLATE_TRANSLUCENT );
                g.fillPolygon( basket.getXCoords( true ), basket.getYCoords(),
                TRAP_POINTS );
                g.setColor( Colour.CHOCOLATE );
                for( int[] line : basket.getLines( true ) )
                    g.drawLine( line[0], line[1], line[2], line[ FOURTH_ELEMENT ] );
            }
        }
        
        drawSpeedBar(g);
        drawPoints(g);
    }
    private void initialize()
    {
        panelWidth = this.getWidth();
        panelHeight = this.getHeight();
        
        time = 0;
        Timer clock = new Timer( INCREMENT, this ); 
        clock.start();
            
        keys = new boolean[ NUM_KEYS ];

        baskets = new Basket[]{ new Basket( panelWidth, panelHeight, panelWidth /
        FOURTH ), new Basket( panelWidth, panelHeight, THREE * panelWidth / FOURTH ) };
        

        first = false;
    }
    private void background( Graphics g )
    {
        g.setColor( Colour.LIGHT_BLUE );
        g.fillRect( 0, 0, panelWidth, panelHeight / 2 );
        g.setColor( Colour.GRASS_GREEN );
        g.fillRect( 0, panelHeight / 2, panelWidth, panelHeight / 2 );
        g.setColor( Colour.SUBDUED_GREEN );
        g.fillArc( panelWidth / FOURTH, 0, panelWidth / 2, THREE * panelHeight / FOURTH,
        0, HALF_CIRCLE );
        g.setColor( Colour.GREEN );
        g.fillArc( 0, 0, panelWidth / 2, THREE * panelHeight / FOURTH, 0, HALF_CIRCLE );
        g.fillArc( panelWidth / 2, 0, panelWidth / 2, THREE * panelHeight / FOURTH, 0,
        HALF_CIRCLE );
        g.setColor( Colour.SUBDUED_CHOCOLATE );
        g.fillRect( SEVEN * panelWidth / THIRTY_SECONDTH, THREE * panelHeight / 
        EIGHTH, panelWidth / SIXTEENTH, panelHeight / FOURTH );
        g.fillRect( FIFTEEN * panelWidth / THIRTY_SECONDTH, THREE * panelHeight / 
        EIGHTH, panelWidth / SIXTEENTH, FIVE * panelHeight / THIRTY_SECONDTH );
        g.fillRect( TWENTY_THREE * panelWidth / THIRTY_SECONDTH, THREE * panelHeight / 
        EIGHTH, panelWidth / SIXTEENTH, panelHeight / FOURTH );
    }
    private void drawSpeedBar(Graphics g)
    {
        int widthPorportion = getWidth() / TENTH;
        double widthPlacement = SEVEN;
        double widthMultiplier = 2; 
        int heightPorportion = getHeight() / TWELFTH;
        double heightPlacement = 0.5;
        double heightMultiplier = 1;
        double leftwardMovement = 0.5;
        

        g.setColor( Colour.WHITE );
        g.fillRect( (int)( widthPorportion * ( widthPlacement - leftwardMovement ) ),
        (int)( heightPorportion * heightPlacement ), (int)( widthPorportion * (      
        widthMultiplier + leftwardMovement ) ), (int)( heightPorportion *
        heightMultiplier ) );
        g.setColor( Colour.BLACK );
        
        g.drawRect( (int)( widthPorportion * widthPlacement ), (int)( heightPorportion *
        heightPlacement), (int)( widthPorportion * widthMultiplier ), (int)(
        heightPorportion * heightMultiplier ) );
        g.drawRect( (int)( widthPorportion * ( widthPlacement - leftwardMovement ) ),
        (int)(heightPorportion*heightPlacement), (int)( widthPorportion *
        leftwardMovement ), (int)( heightPorportion * heightMultiplier ) );
        
        if( recoilBar )
        {
            g.setColor( Colour.BLUE );
            double timePorportion = ( System.currentTimeMillis() - lastPressedTime ) / 
            (double)MouseClickListener.TIME_BETWEEN_CLICKS;
            
            if( timePorportion > 1 )
            {
                recoilBar = false;
                timePorportion = 1;
            }
            
            int roundingFix = 1;
            g.fillRect( (int)( widthPorportion  * ( widthPlacement - ( 1 -
            timePorportion ) * leftwardMovement ) ), (int)( heightPorportion *
            heightPlacement ), (int)( widthPorportion * leftwardMovement * ( 1 -
            timePorportion ) + roundingFix ), (int)( heightPorportion *
            heightMultiplier ) );
        }
        if( barFill )
        {
            g.setColor( Colour.LIME_GREEN );
            double timePorportion = ( System.currentTimeMillis() - pressedTime ) /
            (double)MouseClickListener.MAX_CLICK_TIME;
            double redAppleTime = MouseClickListener.NORMAL_APPLE_CLICK_LIMIT / 
            (double)MouseClickListener.MAX_CLICK_TIME;
            if( timePorportion > 1 )
                timePorportion = 1;
            if( timePorportion < redAppleTime )
                g.setColor( Colour.RED );
            g.fillRect( (int)( widthPorportion * widthPlacement ), (int)
            ( heightPorportion * heightPlacement ), (int)( widthPorportion *
            widthMultiplier * timePorportion ), (int)( heightPorportion *
            heightMultiplier) );
        }
    }
    private void drawPoints(Graphics g)
    {
        g.setColor( Colour.BLACK );
        g.setFont( new Font( Font.DIALOG, Font.BOLD, FONT_SIZE ) );
        g.drawString( player1Points + "  /  " + player2Points, getWidth() / SIXTEENTH,
        getHeight() / TWENTY_FOURTH );
    }
    
    /**
     * Starts up the AppleDrop game
     * 
     * @param a String array that is not used for this program
     */
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "Apple Drop" );
        frame.setBounds( SCREEN_DIMENSIONS );
        
        AppleDrop ad = new AppleDrop();
        
        frame.addKeyListener( new KeyboardListener( ad ) );
        frame.addMouseListener( new MouseClickListener( ad ) );
        frame.add( ad );
        
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        frame.setAlwaysOnTop( true );
        
        frame.setVisible( true );
    }
}