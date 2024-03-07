import greenfoot.*;  

public class GameOverFade extends Actor
{
    public int transparency = 0;
    GreenfootImage img = new GreenfootImage(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
    int i = 0;
    public GameOverFade() {
        img.setColor(Color.BLACK);    
        img.fill();
        img.setTransparency(0);
        setImage(img);
    }
    
    public void act() {
        if(i <= 255) {
            img.setTransparency(i);
            i+=5;
        }
        if (i == 255) {
            i++;
            MyWorld world = (MyWorld) getWorld();
            MyWorld.music.stop();
            Greenfoot.setWorld(new GameOver(world.score, world.star));
        }
    }
    
}
