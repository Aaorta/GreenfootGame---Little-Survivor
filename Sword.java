import greenfoot.*;  

public class Sword extends Actor
{
    public Sword(){
        GreenfootImage img = new GreenfootImage("Pedang.png");
        img.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
        setImage(img);
    }
}
