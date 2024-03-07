import greenfoot.*;  

public class LevelSelect extends World
{
    public boolean mode;
    public static String player = "mc";
    public static int currentScore;
    Color warnaSkor = new Color(249, 204, 9, 255);
    
    public LevelSelect(boolean currentMode)
    {    
        super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1); 
        GreenfootImage img = getBackground();
        img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        currentUserInfo();
        locked();
        levelSelect();
        charSelect();
        mode = currentMode;
    }
    
    public void currentUserInfo(){
        if (UserInfo.isStorageAvailable()) {
             UserInfo myInfo = UserInfo.getMyInfo();
             GreenfootImage ProfilePicture = myInfo.getUserImage();
             currentScore = myInfo.getScore();
        }
    }
    
    public void callLoading(){
        addObject(new LoadingScreen(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE/2);
    }
    
    public void levelSelect(){
        addObject(new Level1Button(), 247, 281);
        addObject(new Level2Button(), 366, 281); 
    }
    
    public void charSelect(){
        addObject(new Char1Button(), 159, 420);
        addObject(new Char3Button(), 310, 420);
        addObject(new Char2Button(), 454, 420);
    }
    
    public void locked(){
        if(currentScore < 20000){
            addObject(new Information("Unlock 20.000", warnaSkor), 453, 469);
        }
        if(currentScore < 10000){
            addObject(new Information("Unlock 10.000", warnaSkor), 304, 428);
        }
        addObject(new Information("Your Score : "+currentScore+"", warnaSkor), 311, 525);
    }
}
