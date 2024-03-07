import greenfoot.*;  

public class Kid extends Actor
{
    private final int SS = MyWorld.SCREEN_SIZE;
    private final int WS = MyWorld.WALL_SIZE;
    private final int WT = MyWorld.WALL_TOTAL;
    private final int CS = MyWorld.COIN_SIZE;
    public static int VELOCITY = 1;
    
    protected enum Arah{atas, bawah, kanan, kiri};
    public int x,y;
    
    int animationIndex = 0;
    String[] imgKanan;
    String[] imgKiri;
    
    private MyWorld CurrentWorld;
    private int[][] world_map;
    private String WorldName;
    private int scoreIncreement;
    
    public Kid(String player){
        // GreenfootImage initImg = new GreenfootImage(player);
        // initImg.scale(WS, WS);
        // setImage(initImg);
        
        imgKanan = new String[]{
            player + "/Kanan (1).png",
            player + "/Kanan (2).png",
            player + "/Kanan (3).png",
            player + "/Kanan (4).png",
            player + "/Kanan (5).png",
            player + "/Kanan (6).png",
        };
        
        imgKiri = new String[]{
            player + "/Kiri (1).png",
            player + "/Kiri (2).png",
            player + "/Kiri (3).png",
            player + "/Kiri (4).png",
            player + "/Kiri (5).png",
            player + "/Kiri (6).png",
        };
        
        GreenfootImage init = new GreenfootImage(imgKanan[animationIndex]);
        init.scale(WS, WS);
        setImage(init);
        // imgKiri = new GreenfootImage(imgKanan.getWidth(), imgKanan.getHeight());
        // imgKiri.drawImage(imgKanan, 0, 0);
        // imgKiri.mirrorHorizontally();
    }   
    
    protected void addedToWorld(World world){
        CurrentWorld = (MyWorld) world;
        world_map = CurrentWorld.world_map;
        WorldName = MyWorld.currentLevel;
        scoreIncreement = CurrentWorld.scoreIncreement;
    }
    
    public void act()
    {
        GreenfootImage current = getImage();

        Arah arahInput = cekInput();
        
        try{
            if (CurrentWorld.cloak == true){
                current.setTransparency(100);
                animateCloak(arahInput);
            } else {
                current.setTransparency(255);
                animate(arahInput);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        move(arahInput);
        interact();
        isTouchCoin();
        
        if (CurrentWorld.speedupTime == 0){
            VELOCITY = 1;
        }
    }
    
    // private void handleTouchPortal() {
        // if(!isTouching(Portal.class)) {
           // return; 
        // }
        // CurrentWorld.music.stop();
        // if(WorldName == "Level1") {
            // (new GreenfootSound("portal_enter.mp3")).play();
            // CurrentWorld.callLoading();
            // Greenfoot.delay(5);
            // Greenfoot.setWorld(new Level2(CurrentWorld.score));
        // } else if (WorldName == "Level2") {
            // (new GreenfootSound("portal_enter.mp3")).play();
            // CurrentWorld.callLoading();
            // Greenfoot.delay(5);
            // Greenfoot.setWorld(new Level3(CurrentWorld.score));
        // } else if (WorldName == "Level3") {
            // CurrentWorld.star = 3;
            // CurrentWorld.win = 1;
            // //GreenfootSound audio = new GreenfootSound("success.mp3");
            // //audio.setVolume(50);
            // //audio.play(); 
            // return;
        // }
    // } 
    
    private void animate(Arah arahInput){
        if (animationIndex > 45){animationIndex = 0;}
        
        if(arahInput == Arah.kanan || arahInput == Arah.atas){
            GreenfootImage currImage = new GreenfootImage (imgKanan[animationIndex/9]);
            currImage.scale(WS, WS);
            setImage(currImage);
        } else if(arahInput == Arah.kiri || arahInput == Arah.bawah){
            GreenfootImage currImage = new GreenfootImage (imgKiri[animationIndex/9]);
            currImage.scale(WS, WS);
            setImage(currImage);
        }
        animationIndex++;
    }
    
    private void animateCloak(Arah arahInput){
        if (animationIndex > 45){animationIndex = 0;}
        
        if(arahInput == Arah.kanan || arahInput == Arah.atas){
            GreenfootImage currImage = new GreenfootImage (imgKanan[animationIndex/9]);
            currImage.scale(WS, WS);
            currImage.setTransparency(100);
            setImage(currImage);
        } else if(arahInput == Arah.kiri || arahInput == Arah.bawah){
            GreenfootImage currImage = new GreenfootImage (imgKiri[animationIndex/9]);
            currImage.scale(WS, WS);
            currImage.setTransparency(100);
            setImage(currImage);
        }
        animationIndex++;
    }
    
    private void isTouchCoin() {
        int indexX = getX()/MyWorld.WALL_SIZE;
        int indexY = getY()/MyWorld.WALL_SIZE;
        MyWorld currentWorld = (MyWorld) getWorld();
        if(world_map[indexY][indexX] == 0 || world_map[indexY][indexX] == 4) {
            currentWorld.removeCoinAt(indexX, indexY);
            world_map[indexY][indexX] = 727;
            GreenfootSound audio = new GreenfootSound("coin.mp3");
            audio.setVolume(50);
            audio.play();
            currentWorld.scoreIncrease(scoreIncreement);
        }
    }
    
    private void interact(){
        MyWorld world = (MyWorld) getWorld();
        // if(isTouching(Coin.class)){
            // GreenfootSound audio = new GreenfootSound("coin.mp3");
            // audio.setVolume(50);
            // audio.play();
            // removeTouching(Coin.class);
            // world.scoreIncrease(100);
        // }
        if(isTouching(Shield.class)){
            (new GreenfootSound("item-equip.mp3")).play();
            removeTouching(Shield.class);
            world.equipShield();
        }
        if(isTouching(Sword.class)){
            (new GreenfootSound("item-equip.mp3")).play();
            removeTouching(Sword.class);
            world.equipSword();
        }
        if(isTouching(Speedup.class)){
            (new GreenfootSound("item-equip.mp3")).play();
            removeTouching(Speedup.class);
            world.equipSpeedup();
            VELOCITY = 2;
        }
        if(isTouching(Cloak.class)){
            (new GreenfootSound("item-equip.mp3")).play();
            removeTouching(Cloak.class);
            world.equipCloak();
        }
        if(isTouching(Potion.class)){
            (new GreenfootSound("item-equip.mp3")).play();
            removeTouching(Potion.class);
            world.equipPotion();
        }
        
        if (world.cloak == true || world.potion == true){
            if(isTouching(Zombie.class)){
                return;
            }
        }
        if(world.sword == true){
            if(isTouching(Zombie.class)){
                (new GreenfootSound("sword-hit.mp3")).play();
                removeTouching(Zombie.class);
                world.unequipSword();
                return;
            }           
        }
        if(world.shield == true){
            if(isTouching(Zombie.class)){
                (new GreenfootSound("shield-guard.mp3")).play();
                removeTouching(Zombie.class);
                world.respawnZombie();
                world.unequipShield();
                return;
            }
        }
    }
    
    private void move(Arah arahSekarang) {
        // saat tidak bisa bergerak lagi
        if (!canMove(arahSekarang)){
            return;
        }
        ubahKoordinat(arahSekarang, VELOCITY);
        int xNew = getX() + x;
        int yNew = getY() + y;
        setLocation(xNew, yNew);
    }
    
    private boolean canMove(Arah arahSekarang){
        int xCurrent = getX();
        int yCurrent = getY();
        
        if(xCurrent == SS|| yCurrent == SS){
            return false;
        }
        
        int xFuture = xCurrent;
        int yFuture = yCurrent;
        
        // offset = 1/2 karakter size
        if(arahSekarang == Arah.atas){
            yFuture -= CS;
        }
        if(arahSekarang == Arah.bawah){
            yFuture += CS;
        }
        if(arahSekarang == Arah.kanan){
            xFuture += CS;
        }
        if(arahSekarang == Arah.kiri){
            xFuture -= CS;
        }
        
        // agar tidak melebihi map
        if((xFuture/WS) <= 0 || (yFuture/WS) <= 0){
            return false;
        }
        
        // agar tidak melebihi map
        if((xFuture/WS) >= WT - 1 || (yFuture/WS) >= WT - 1){
            return false;
        }
        
        // agar tidak menabrak tembok
        int wall = world_map[yFuture/WS][xFuture/WS];
        if(wall == 1 || wall == 2){
            return false;
        }
        
        return true;
    }
    
    public void ubahKoordinat(Arah arah, int velocity){
        x = 0;
        y = 0;
        if(arah == Arah.atas) {
            y -= velocity;
        }
        if(arah == Arah.bawah) {
            y += velocity;
        }
        if(arah == Arah.kanan) {
            x += velocity;
        }
        if(arah == Arah.kiri) {
            x -= velocity;
        }
    }
    
    public Arah cekInput(){
        if (Greenfoot.isKeyDown("w"))
            return Arah.atas;
        else if (Greenfoot.isKeyDown("d"))
            return Arah.kanan;
        else if (Greenfoot.isKeyDown("s"))
            return Arah.bawah;
        else if (Greenfoot.isKeyDown("a"))
            return Arah.kiri;
        return null;
    }
}
