import greenfoot.*;  

public class Char1Button extends Button
{
    private boolean isPlaying = false;

    public Char1Button(){
        img1 = new GreenfootImage(getImage());
        img1.scale(MyWorld.WALL_SIZE*3, MyWorld.WALL_SIZE*5);
        img1.setTransparency(100);
        setImage(img1);
        img2 = new GreenfootImage(getImage());
        img2.scale(MyWorld.WALL_SIZE*3, MyWorld.WALL_SIZE*5);
        img2.setTransparency(255);
    }
    
    public void act(){
        animate();
        if(Greenfoot.mouseClicked(this)){
            LevelSelect.player = "mc";
        }
        if(LevelSelect.player.equals("mc")){
            setImage(img2);
        } else {
            setImage(img1);
        }
    }
}
