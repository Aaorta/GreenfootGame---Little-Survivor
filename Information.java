import greenfoot.*;  

public class Information extends Actor
{
    MyWorld world;
    
    Color warna = new Color(130,17,173,255);
    
    public Information(String text)
    {
        world = (MyWorld) getWorld();
        GreenfootImage img = new GreenfootImage(text, world.WALL_SIZE, warna, new Color (0, 0, 0, 0));
        setImage(img);
    }
    
    public Information(String text, Color color)
    {
        world = (MyWorld) getWorld();
        GreenfootImage img = new GreenfootImage(text, world.WALL_SIZE, color, new Color (0, 0, 0, 0));
        setImage(img);
    }
    
    public Information(String file, int a, int b){
        world = (MyWorld) getWorld();
        GreenfootImage img = new GreenfootImage(file);
        img.scale(a, b);
        setImage(img);
    }
    
    public Information(GreenfootImage input, int a, int b){
        world = (MyWorld) getWorld();
        GreenfootImage img = input;
        img.scale(a, b);
        setImage(img);
    }
    
    public int getWidth(){
        GreenfootImage img = getImage();
        return img.getWidth();
    }
    
    public void updateImage(int transparency){
        GreenfootImage img = getImage();
        img.setTransparency(transparency);
    }
    
    public void updateText(String text, int x, int y){
        GreenfootImage img = new GreenfootImage(text, world.WALL_SIZE, warna, new Color (0, 0, 0, 0));
        setImage(img);
        setLocation(x, y);
    }
}
