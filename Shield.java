import greenfoot.*;  

public class Shield extends Actor
{
    public Shield(){
        GreenfootImage img = new GreenfootImage("Perisai.png");
        img.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
        setImage(img);
    }
}
