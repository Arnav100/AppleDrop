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
    private int bigWidth, smallWidth;
    private int basketHeight, screenHeight;
    private int moveSpeed;
    private Color color;
    private final int SCREEN_PORPORTION_FOR_BIG_WIDTH= 20, SCREEN_PORPORTION_FOR_SMALL_WIDTH = 25;
    private final int SCREEN_PORPORTION_FOR_HEIGHT = 20;
    public Basket(int screenWidth, int screenHeight, int initialX)
    {
        this.bigWidth = screenWidth/SCREEN_PORPORTION_FOR_BIG_WIDTH;
        this.smallWidth = screenWidth/SCREEN_PORPORTION_FOR_SMALL_WIDTH;
        this.screenHeight = screenHeight;
        this.basketHeight = screenHeight/SCREEN_PORPORTION_FOR_HEIGHT;
        this.x = initialX;
        this.moveSpeed = 5;
        this.color = Colour.BROWN;
    }
    
    public void moveRight()
    {
        x += moveSpeed;
    }
    
    public void moveLeft()
    {
        x -= moveSpeed;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getHeight()
    {
        return basketHeight;
    }
    
    public int[] getXCoords()
    {
        int diff = (bigWidth-smallWidth)/2;
        return new int[]{x, x + bigWidth, x + bigWidth - diff, x + diff };
    }
    
    public int[] getYCoords()
    {
        return new int[]{screenHeight-basketHeight, screenHeight-basketHeight, screenHeight, screenHeight};
    }
    
    
}
