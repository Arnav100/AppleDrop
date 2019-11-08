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
    private final int SPEED = 20;
    private final int THIRD = 3;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIFTH = 5;
    private final int FOURTH_ELEMENT = 3;
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
    
    public boolean contains( Apple apple )
    {
        int h = apple.getX();
        int k = apple.getY();
        int radius = apple.getSize() / 2;
        if( past <= 0)
            return h >= x + radius && h <= x + bigWidth - radius && k > screenHeight -
            basketHeight - radius && k <= screenHeight;
        if( past > 0 )
            return h >= x + radius && h <= past - radius && k > screenHeight -
            basketHeight - radius && k <= screenHeight;
        return h >= screenWidth + x && h <= x + bigWidth - radius && k > screenHeight -
            basketHeight - radius && k <= screenHeight;
    }
    
    public void moveRight()
    {
        x += moveSpeed;
        if(x > screenWidth)
            x = 0;
        past = x + bigWidth - screenWidth;
    }
    
    public void moveLeft()
    {
        x -= moveSpeed;
        if(x < 0)
            x = screenWidth;
        past = x + bigWidth - screenWidth;
    }
    
    public int getHeight()
    {
        return basketHeight;
    }
    
    public int getPast()
    {
        return past;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int[][] getLines()
    {
        return getLines( false );
    }
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
                new int[]{ xs[ FOURTH_ELEMENT ], minY, xs[ FOURTH_ELEMENT ],
                getYRight( xs[ FOURTH_ELEMENT ] ) }, 
                new int[]{ getXLeft( ys[0] ), ys[0], getXRight( ys[0] ),
                ys[0] }, new int[]{ getXLeft( ys[1] ), ys[1], getXRight( ys[1] ),
                ys[1] }, new int[]{ getXLeft( ys[2] ), ys[2], getXRight( ys[2] ),
                ys[2] } };
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
    
    public int[] getXCoords()
    {
        return getXCoords( false );
    }
    public int[] getXCoords( boolean isSplit )
    {
        int diff = (bigWidth-smallWidth)/2;
        if( !isSplit )
            return new int[]{x, x + bigWidth, x + bigWidth - diff, x + diff };
        return new int[]{ past - bigWidth, past,
            past - diff, past - bigWidth + diff };
    }
    
    public int[] getYCoords()
    {
        return new int[]{screenHeight-basketHeight, screenHeight-basketHeight, screenHeight,
            screenHeight};
    }
}
