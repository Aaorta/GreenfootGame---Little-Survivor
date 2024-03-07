import greenfoot.*; 

public class StoryMenu extends World
{
    public StoryMenu(){    
            super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1); 
            GreenfootImage img = getBackground();
            img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
            addButtons();
        }
        
        public void addButtons(){
            addObject(new MainMenuButton(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE - 1*MyWorld.WALL_SIZE - MyWorld.COIN_SIZE);
        }
}
