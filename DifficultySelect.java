import greenfoot.*;  

public class DifficultySelect extends World
{
    public DifficultySelect()
    {    
        super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1); 
        GreenfootImage img = getBackground();
        img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        difficultySelect();
    }
    
    public void callLoading(){
        addObject(new LoadingScreen(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE/2);
    }
    
    public void difficultySelect(){
        addObject(new EasyButton(), MyWorld.SCREEN_SIZE/2, 9*MyWorld.WALL_SIZE);
        addObject(new HardButton(), MyWorld.SCREEN_SIZE/2, 12*MyWorld.WALL_SIZE); 
    }
}
