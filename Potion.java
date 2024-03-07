import greenfoot.*;  

public class Potion extends Actor
{
    public Potion(){
        GreenfootImage img = new GreenfootImage("Potion.png");
        img.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
        setImage(img);
    }
}
