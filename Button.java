import greenfoot.*;  
public class Button extends Actor
{
    public GreenfootImage img1;
    public GreenfootImage img2;
    private boolean isPlaying;
    public void animate()
    {
        if(Greenfoot.mouseMoved(this)) {

            if(isPlaying == false)
                (new GreenfootSound("btn_hover.mp3")).play();
                isPlaying = true;
                
            setImage(img2);    
            //img.scale(MyWorld.WALL_SIZE*9, MyWorld.WALL_SIZE*3);
        } 
        
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)){
            isPlaying = false;
            setImage(img1);  
            //img.scale(MyWorld.WALL_SIZE*6, MyWorld.WALL_SIZE*2);
        }
    }
}
