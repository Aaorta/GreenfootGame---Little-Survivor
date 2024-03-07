import greenfoot.*; 
import java.util.*;

public class LeaderboardMenu extends World
{
    List<UserInfo> topGlobal = UserInfo.getTop(50);
    Color warnaUsername = new Color(0,0,0,255);
    Color warnaSkor = new Color(249, 204, 9, 255);
    Color warnaRank = new Color(255, 255, 255, 255);
    
    public LeaderboardMenu(){    
        super(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE, 1); 
        GreenfootImage img = getBackground();
        img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE); 
        currentUserInfo();
        leaderboard();
        addButtons();
    }
        
    public void addButtons(){
        addObject(new MainMenuButton(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE - 1*MyWorld.WALL_SIZE - 0*MyWorld.COIN_SIZE);
    }
    
    public void currentUserInfo(){
        try{
            if (UserInfo.isStorageAvailable()) {
             UserInfo myInfo = UserInfo.getMyInfo();
             GreenfootImage ProfilePicture = myInfo.getUserImage();
             String Username = myInfo.getUserName();
             Integer TotalScore = myInfo.getScore();
             Integer CurrentRank = 0;
             for (int i = 0; i < topGlobal.size(); i++) {
                UserInfo currentUser = topGlobal.get(i);
    
                if (currentUser.getUserName().equals(Username)) {
                    CurrentRank = i + 1;
                    break;
                }           
             }
             
             if (CurrentRank !=0){
                 addObject(new Information(CurrentRank.toString(), warnaRank), 168, 494);
             } else {
                 addObject(new Information("50+", warnaRank), 168, 494);
             }
             
             addObject(new Information(ProfilePicture, 2*MyWorld.WALL_SIZE, 2*MyWorld.WALL_SIZE), 243 , 494);
             addObject(new Information(Username, warnaUsername), 372, 478);
             addObject(new Information(TotalScore.toString(), warnaSkor), 358, 515);
         }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void leaderboard(){
        for (int i = 0; i < 5; i++){
             UserInfo currentPlayer = topGlobal.get(i);
             GreenfootImage ProfilePicture = currentPlayer.getUserImage();
             String Username = currentPlayer.getUserName();
             Integer TotalScore = currentPlayer.getScore();
             addObject(new Information(ProfilePicture, 2*MyWorld.WALL_SIZE, 2*MyWorld.WALL_SIZE), 170, (2+2+(2*i))*MyWorld.WALL_SIZE + i*MyWorld.COIN_SIZE);
             addObject(new Information(Username, warnaUsername), 280, (1+3+(2*i))*MyWorld.WALL_SIZE + (i)*MyWorld.COIN_SIZE);
             addObject(new Information(TotalScore.toString(), warnaSkor), 445, (1+3+(2*i))*MyWorld.WALL_SIZE + (i)*MyWorld.COIN_SIZE);
        }
    }
}
