import java.awt.Color;
/**
 * Write a description of class Apple here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Apple
{
   private final int SCREEN_PORPORTION = 70;
   private int radius;
   private int x, y;
   private int moveSpeed;
   private Color color; 
   public static final int RED_SPEED = 5;
   public static final int MAX_SPEED = 25;
   private static final int POINT_SPEED_RATIO = 5;
   
   public Apple(int screenWidth, int screenHeight, int clickX)
   {
       this(screenWidth, screenHeight, clickX, RED_SPEED );
       color = Colour.RED;
   }
   public Apple(int screenWidth, int screenHeight, int clickX, boolean isBrown)
   {
      this(screenWidth, screenHeight, clickX, MAX_SPEED / 2);
      if(isBrown)
        color = Colour.BROWN;
      else
        color = Colour.RED;
   }
   
   public Apple(int screenWidth, int screenHeight, int clickX, int moveSpeed)
   {
       this.x = clickX;
       this.y = 0;
       this.radius = screenWidth/SCREEN_PORPORTION;
       this.moveSpeed = moveSpeed;
       color = Colour.LIME_GREEN;
    }
   public void move()
   {
       y += moveSpeed;
   }
   
   public int getX()
   {
       return x;
   }
   
   public int getY()
   {
       return y;
   }
    
   public int getPointValue()
   {
       if(color.equals(Colour.RED))
            return 1;
       return (int)( Math.abs( moveSpeed ) / (double)MAX_SPEED * POINT_SPEED_RATIO );
   }

   public int getSpeed()
   {
    return moveSpeed;
    }
   
   public int getSize()
   {
       return radius * 2;
   }
   
   public Color getColor()
   {
       return color;
   }
   
   public void getSwat()
   {
       moveSpeed = -moveSpeed;
   }
}
