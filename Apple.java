
/**
 * Write a description of class Apple here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Apple
{
   private final double SCREEN_PORPORTION = 32;
   private double radius;
   private double x, y;
   
    
   public Apple(int screenWidth, int screenHeight, int clickX)
   {
       this.x = clickX;
       this.y = 0;
       radius = screenWidth/ SCREEN_PORPORTION;
   }
   
   public void move()
   {
   }
   
}
