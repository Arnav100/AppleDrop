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
    private Color color;
    private final int SCREEN_PORPORTION_FOR_BIG_WIDTH = 8,
    SCREEN_PORPORTION_FOR_SMALL_WIDTH = 16;
    private final int SCREEN_PORPORTION_FOR_HEIGHT = 16;
    private final int SPEED = 20;
    public Basket(int screenWidth, int screenHeight, int initialCenter )
    {
        this.bigWidth = screenWidth/SCREEN_PORPORTION_FOR_BIG_WIDTH;
        this.smallWidth = screenWidth/SCREEN_PORPORTION_FOR_SMALL_WIDTH;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.basketHeight = screenHeight/SCREEN_PORPORTION_FOR_HEIGHT;
        this.x = initialCenter - bigWidth / 2;
        this.moveSpeed = SPEED;
        this.color = Colour.CHOCOLATE_TRANSLUCENT;
    }
    
    public boolean contains( Apple apple )
    {
        int appleX = apple.getX();
        int appleY = apple.getY();
        if(appleY < screenHeight - basketHeight)
            return false;

        return (appleX > x && appleX <  x + bigWidth) || (past > 0 && appleX < past) ;
           
        
        // int h = apple.getX();
        // int k = apple.getY();
        // int radius = apple.getSize() / 2;
        // if( past <= 0)
            // return h >= x + radius && h <= x + bigWidth - radius && k > screenHeight -
            // basketHeight - radius && k <= screenHeight;
        // if( past > 0 )
            // return h >= x + radius && h <= past - radius && k > screenHeight -
            // basketHeight - radius && k <= screenHeight;
        // return h >= screenWidth + x && h <= x + bigWidth - radius && k > screenHeight -
            // basketHeight - radius && k <= screenHeight;
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
    
    public Color getColor()
    {
        return color;
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
