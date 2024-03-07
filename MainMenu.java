import greenfoot.*;  

public class MainMenu extends World
{
    public MainMenu(){    
        super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1); 
        GreenfootImage img = getBackground();
        img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        addButtons();
    }
    
    public void addButtons(){
        addObject(new StartButton(), MyWorld.SCREEN_SIZE/2, 8*MyWorld.WALL_SIZE + MyWorld.COIN_SIZE);
        addObject(new StoryButton(), MyWorld.SCREEN_SIZE/2, 10*MyWorld.WALL_SIZE + 2*MyWorld.COIN_SIZE);
        addObject(new HelpButton(), MyWorld.SCREEN_SIZE/2, 12*MyWorld.WALL_SIZE + 2*MyWorld.COIN_SIZE);
        addObject(new LeaderboardButton(), MyWorld.SCREEN_SIZE/2, 14*MyWorld.WALL_SIZE + 2*MyWorld.COIN_SIZE);
    }

}
