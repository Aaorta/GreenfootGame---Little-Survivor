import greenfoot.*;  

public class ZombieHand extends Actor
{
    public MyWorld current;
    
    public ZombieHand(){
        GreenfootImage img = new GreenfootImage("Tangan.png");
        img.scale(MyWorld.WALL_SIZE, MyWorld.WALL_SIZE);
        setImage(img);
    }
    
    protected void addedToWorld(World world){
        current = (MyWorld) world;
    }
    
    public int i = 0;
    public void act(){
        if(i == 1000){
            i = 0;
            current.respawnTangan();
            (new GreenfootSound("zombie_hand_spawn.mp3")).play();
        }
        i++;
        interact();
    }
    
    private void interact(){
        if (current.cloak == true){
            return;
        }
        if(isTouching(Kid.class)){
            current.minusHealth();
            removeTouching(Kid.class);
            if (current.playerHealth>0){
                current.respawnPlayer();
            }
        }    
    }
    
}
