import greenfoot.*;  

public class Cloak extends Actor
{
    public Cloak(){
        GreenfootImage img = new GreenfootImage("Cloak.png");
        img.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
        setImage(img);
    }
}
