import greenfoot.*;  

public class LeaderboardButton extends Button
{
    private boolean isPlaying = false;

    public LeaderboardButton(){
        img1 = new GreenfootImage(getImage());
        img1.scale(MyWorld.WALL_SIZE*6, MyWorld.WALL_SIZE*2);
        setImage(img1);
        img2 = new GreenfootImage(getImage());
        img2.scale(MyWorld.WALL_SIZE*9, MyWorld.WALL_SIZE*3);
    }
    
    public void act(){
        animate();
        if(Greenfoot.mouseClicked(this)){
            (new GreenfootSound("game_start.MP3")).play();
            Greenfoot.setWorld(new LeaderboardMenu());
        }
    }
}
