import java.awt.Color;
/**
 * Write a description of class Basket here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Basket
{
    private int x;
    private int past;
    private int bigWidth, smallWidth, screenWidth;
    private int basketHeight, screenHeight;
    private int moveSpeed;
    private final int SCREEN_PORPORTION_FOR_BIG_WIDTH = 8,
    SCREEN_PORPORTION_FOR_SMALL_WIDTH = 16;
    private final int SCREEN_PORPORTION_FOR_HEIGHT = 16;
    private final int SPEED = 15;
    private final int THIRD = 3;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIFTH = 5;
    private final int FOURTH_ELEMENT = 3;
    
    /**
     * Creates a basket with widths and height proproportional to screen width and height,
     * a specified initial x-position, and a predetermined speed.
     * 
     * @param screenWidth int width of the user's screen
     * @param screenHeight int height of the user's screen
     * @pram initialCenter int x-position of the center of the basket
     */
    public Basket(int screenWidth, int screenHeight, int initialCenter )
    {
        this.bigWidth = screenWidth/SCREEN_PORPORTION_FOR_BIG_WIDTH;
        this.smallWidth = screenWidth/SCREEN_PORPORTION_FOR_SMALL_WIDTH;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.basketHeight = screenHeight/SCREEN_PORPORTION_FOR_HEIGHT;
        this.x = initialCenter - bigWidth / 2;
        this.moveSpeed = SPEED;
    }
    
    /**
     * Determines if the given Apple object lies within this Basket object
     * 
     * @param apple the Apple object examined relative to this Basket object
     * 
     * @return whether the given apple lies within this basket
     */
    public boolean contains( Apple apple )
    {
        int appleX = apple.getX();
        int appleY = apple.getY();
        if(appleY < screenHeight - basketHeight)
            return false;

        return (appleX > x && appleX <  x + bigWidth) || (past > 0 && appleX < past) ;
    }
    
    /**
     * Moves this Basket object right by the number of pixels equal to its speed. If the
     * basket moves past the edge of the screen, the part of the basket past the screen
     * appears on the left side of the screen.
     */
    public void moveRight()
    {
        x += moveSpeed;
        if(x > screenWidth)
            x = 0;
        past = x + bigWidth - screenWidth;
    }
    
    /**
     * Moves this Basket object left by the number of pixels equal to its speed. If the
     * basket moves past the edge of the screen, the part of the basket past the screen
     * appears on the right side of the screen.
     */
    public void moveLeft()
    {
        x -= moveSpeed;
        if(x < 0)
            x = screenWidth;
        past = x + bigWidth - screenWidth;
    }
    
    /**
     * Returns the height of this Basket object
     * 
     * @return height of this basket
     */
    public int getHeight()
    {
        return basketHeight;
    }
    
    /**
     * Returns the number of pixels the top-right corner of this basket is past the screen.
     * If this basket is not past the screen, it returns the negative difference between
     * the width and the x-position of the top-right corner of the basket
     * 
     * @return the difference between this basket's top-right x-position and the width of
     * the screen
     */
    public int getPast()
    {
        return past;
    }
    
    /**
     * Returns the x-position of the top-left corner of this basket
     * 
     * @return the x-position of the top-left corner of this basket 
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Returns the 2D int array for the lines of this basket, which is not past the screen
     * 
     * @return 2D array for the lines of this basket
     */
    public int[][] getLines()
    {
        return getLines( false );
    }
    /**
     * Returns the 2D int array for the lines of this basket, which may or may be past
     * the screen
     * 
     * @param isSplit boolean stating whether this basket is past the screen
     * 
     * @return 2D array for the lines of this basket
     */
    public int[][] getLines( boolean isSplit )
    {
        if( !isSplit )
        {
            int minY = screenHeight - basketHeight;
            int[] xs = new int[]{ x + bigWidth / FIFTH, x + 2 * bigWidth / FIFTH, x +
                THREE * bigWidth / FIFTH, x + FOUR * bigWidth / FIFTH };
            int[] ys = new int[]{ minY, screenHeight - 2 *
                basketHeight / THIRD, screenHeight - basketHeight / THIRD,};
            return new int[][]{ new int[]{ xs[0], minY, xs[0], getYLeft( xs[0] ) },
                new int[]{ xs[1], minY, xs[1], getYLeft( xs[1] ) }, 
                new int[]{ xs[2], minY, xs[2], getYRight( xs[2] ) }, 
                new int[]{ xs[ FOURTH_ELEMENT ], minY, xs[ FOURTH_ELEMENT ], getYRight( xs[
                FOURTH_ELEMENT ] ) }, new int[]{ getXLeft( ys[0] ), ys[0], getXRight(
                ys[0] ), ys[0] }, new int[]{ getXLeft( ys[1] ), ys[1], getXRight( ys[1] ),
                ys[1] }, new int[]{ getXLeft( ys[2] ), ys[2], getXRight( ys[2] ),ys[2] } };
        }
        return ( new Basket( screenWidth, screenHeight, x - screenWidth + smallWidth ) ).
        getLines();
    }
    private int getXLeft( int y )
    {
        double m = 2.0 * basketHeight / ( bigWidth - smallWidth );
        return (int)( ( y - screenHeight + basketHeight + m * x ) / m );
    }
    private int getXRight( int y )
    {
        double m = -2.0 * basketHeight / ( bigWidth - smallWidth );
        return (int)( ( y - screenHeight + basketHeight + m * ( x + bigWidth ) ) / m );
    }
    private int getYLeft( int x )
    {
        double m = 2.0 * basketHeight / ( bigWidth - smallWidth );
        return (int)( m * ( x - this.x ) + screenHeight - basketHeight );
    }
    private int getYRight( int x )
    {
        double m = -2.0 * basketHeight / ( bigWidth - smallWidth );
        return (int)( m * ( x - this.x - bigWidth ) + screenHeight - basketHeight );
    }
    
    /**
     * Returns the int x-coordinates for the four points of this basket, which is not past
     * the screen
     * 
     * @return array of x-coordinates for the four points of this basket
     */
    public int[] getXCoords()
    {
        return getXCoords( false );
    }
    /**
     * Returns the int x-coordinates for the four points of this basket, which may or may
     * not be past the screen
     * 
     * @boolean isSplit boolean stating whether this basket is past the screen
     * 
     * @return array of x-coordinates for the four points of this basket
     */
    public int[] getXCoords( boolean isSplit )
    {
        int diff = (bigWidth-smallWidth)/2;
        if( !isSplit )
            return new int[]{x, x + bigWidth, x + bigWidth - diff, x + diff };
        return new int[]{ past - bigWidth, past,
            past - diff, past - bigWidth + diff };
    }
    
    /**
     * Returns the int y-coordinates for the four points of this basket
     * 
     * @return array of y-coordinates for the four points of this basket
     */
    public int[] getYCoords()
    {
        return new int[]{screenHeight-basketHeight, screenHeight-basketHeight, screenHeight,
            screenHeight};
    }
}
