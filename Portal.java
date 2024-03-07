import greenfoot.*;  

public class Portal extends Actor 
{
    public MyWorld world;
    public String currentWorldName;  
    private int curFrame = 1;
    private GreenfootImage image;
    private boolean stopRender = false;
    public void act() {
        animate();
        if(!stopRender)
            handleTouchPortal();
    }
    
    protected void addedToWorld(World current){
        world = (MyWorld)current;
        currentWorldName = world.currentLevel;
    }
    
    private void handleTouchPortal() {
        if(!isTouching(Kid.class)) {
           return; 
        }
        world.music.stop();
        world.starIncrease();
        if(currentWorldName == "L1Stage1") {
            (new GreenfootSound("portal_enter.mp3")).play();
            world.callLoading();
            Greenfoot.delay(5);
            Greenfoot.setWorld(new L1Stage2(world.score));
        } else if (currentWorldName == "L1Stage2") {
            (new GreenfootSound("portal_enter.mp3")).play();
            world.callLoading();
            Greenfoot.delay(5);
            Greenfoot.setWorld(new L1Stage3(world.score));
        } else if (currentWorldName == "L1Stage3") {
            stopRender = true;
            world.callGameOver();
            GreenfootSound audio = new GreenfootSound("success.mp3");
            audio.setVolume(100);
            audio.play(); 
            return;
        }
        
        if(currentWorldName == "L2Stage1") {
            (new GreenfootSound("portal_enter.mp3")).play();
            world.callLoading();
            Greenfoot.delay(5);
            Greenfoot.setWorld(new L2Stage2(world.score));
        } else if (currentWorldName == "L2Stage2") {
            (new GreenfootSound("portal_enter.mp3")).play();
            world.callLoading();
            Greenfoot.delay(5);
            Greenfoot.setWorld(new L2Stage3(world.score));
        } else if (currentWorldName == "L2Stage3") {
            stopRender = true;
            world.callGameOver();
            GreenfootSound audio = new GreenfootSound("success.mp3");
            audio.setVolume(100);
            audio.play(); 
            return;
        }
    }
    
    
    private void animate() {
        if(curFrame == 5*7) {
            curFrame = 1;
        }
        
        if(curFrame%7==0) {
            image = new GreenfootImage("portal/frame ("+curFrame/7+").png");
            image.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
            setImage(image);
        }
        
        curFrame++;
    }
    
}
