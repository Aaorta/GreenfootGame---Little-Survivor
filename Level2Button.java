import greenfoot.*;  

public class Level2Button extends Button
{
    private boolean isPlaying = false;

    public Level2Button(){
        img1 = new GreenfootImage(getImage());
        img1.scale(MyWorld.WALL_SIZE*3, MyWorld.WALL_SIZE*3);
        setImage(img1);
        img2 = new GreenfootImage(getImage());
        img2.scale(MyWorld.WALL_SIZE*5, MyWorld.WALL_SIZE*5);
    }
    
    public void act(){
        animate();
        if(Greenfoot.mouseClicked(this)){
            (new GreenfootSound("game_start.MP3")).play();
            LevelSelect menu = (LevelSelect) getWorld();
            menu.callLoading();
            Greenfoot.delay(5);
            Greenfoot.setWorld(new L2Stage1(menu.mode, LevelSelect.player));
        }
    }
}
