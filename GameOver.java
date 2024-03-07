import greenfoot.*; 

public class GameOver extends World
{ 
    private int WS = MyWorld.WALL_SIZE;
    private int SS = MyWorld.SCREEN_SIZE;
    private int CS = MyWorld.COIN_SIZE;
    int finalScore;
    int finalStar;
    
    public GameOver(int finalScore, int finalStar)
    {    
        super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1);
        this.finalScore = finalScore;
        this.finalStar = finalStar;
        GreenfootImage img = getBackground();
        img.scale(SS, SS);
        showTulisan();
        showBintang();
        showScore();
        showRetryButton();
        updateScore();
    }
    
    public void showTulisan(){
        if (finalStar<3){
            Information tulisan = new Information("GameOver.png", 15*WS, 5*WS);
            addObject(tulisan, SS/2, SS/4);
        } if (finalStar==3){
            Information tulisan = new Information("Trophy.png",  15*WS, 10*WS);
            addObject(tulisan, SS/2, SS/4-1*WS);
        }
    }
    
    public void showBintang(){
        Information img = new Information(finalStar+"Star.png", 9*WS, 3*WS);
        addObject(img, SS/2, SS/2);
    }
    
    public void showScore(){
            Information TScore = new Information("Score.png", 7*WS, WS);
            Information PScore = new Information("SCORE");
            Information AScore = new Information(""+finalScore);
            addObject(TScore, (6*WS)+4*WS, 12*WS+CS);
            addObject(PScore, (6*WS)+CS+2*WS, 12*WS+CS);
            addObject(AScore, (6*WS)+CS+5*WS, 12*WS+CS);
    }
    
    public void showRetryButton(){
        addObject(new MainMenuButton(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE - 3*MyWorld.WALL_SIZE);
    }
    
    public void updateScore(){
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            int scoreBefore = myInfo.getScore();
            myInfo.setScore(scoreBefore + finalScore);
            myInfo.store();
        }
    }
}
