import java.awt.Color;
/**
 * Apples to be used in the AppleDrop game
 *
 * @author Arnav Parashar and Dana Nigrin
 * @version 11/10/19
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
   
   /**
    * Constructs a red apple at the top of the screen at the given x position
    * 
    * @param screenWidth the width of the screen
    * @param screenHeight the height of th escreen
    * @param clickX the x position that was clicked
    */
   public Apple(int screenWidth, int screenHeight, int clickX)
   {
       this(screenWidth, screenHeight, clickX, RED_SPEED );
       color = Colour.RED;
   }
   
   /**
    * Constructs either a red or brown apple at the top of the screen at the given x position
    * 
    * @param screenWidth the width of the screen
    * @param screenHeight the height of th escreen
    * @param clickX the x position that was clicked
    * @param isBrown true if apple should be brown
    */
   public Apple(int screenWidth, int screenHeight, int clickX, boolean isBrown)
   {
      this(screenWidth, screenHeight, clickX, MAX_SPEED / 2);
      if(isBrown)
        color = Colour.BROWN;
      else
        color = Colour.RED;
   }
   
   /**
    * Constructs a green apple at the top of the screen at the given x position with the given speed
    * 
    * @param screenWidth the width of the screen
    * @param screenHeight the height of th escreen
    * @param clickX the x position that was clicked
    * @param moveSpeed the speed of the apple
    */
   public Apple(int screenWidth, int screenHeight, int clickX, int moveSpeed)
   {
       this.x = clickX;
       this.y = 0;
       this.radius = screenWidth/SCREEN_PORPORTION;
       this.moveSpeed = moveSpeed;
       color = Colour.LIME_GREEN;
   }
   
   /**
    * Moves the apple down the screen
    */
   public void move()
   {
       y += moveSpeed;
   }
   
   /**
    * Returns the x value of the apple
    * 
    * @return the x value of the apple
    */
   public int getX()
   {
       return x;
   }
   
   /**
    * Returns the y value of the apple
    * 
    * @return the y value of the apple
    */
   public int getY()
   {
       return y;
   }
    
   /**
    * Returns the point value of the apple determined by its speed
    * 
    * @return the point value of the apple determined by its speed
    */
   public int getPointValue()
   {
       if(color.equals(Colour.RED))
            return 1;
       return (int)( Math.abs( moveSpeed ) / (double)MAX_SPEED * POINT_SPEED_RATIO );
   }
   
   /**
    * Returns the speed of the apple
    * 
    * @return the speed of the apple
    */
   public int getSpeed()
   {
    return moveSpeed;
   }
   
   /**
    * Returns the diameter of the apple
    * 
    * @return the diameter of the apple
    */
   public int getSize()
   {
       return radius * 2;
   }
   
   /**
    * Returns the Color of the apple
    * 
    * @return the Color of the apple
    */
   public Color getColor()
   {
       return color;
   }
   
   /**
    * "Swats" the apple by negating the speed
    */
   public void swat()
   {
       moveSpeed = -moveSpeed;
   }
}
