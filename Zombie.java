import greenfoot.*;  

public class Zombie extends Actor
{    
    protected enum Arah {atas, bawah, kanan, kiri};
    protected enum TipeZombie {
        ZStage1,
        ZStage2,
        ZStage3
    }
    private int x,y;
    private Arah arahSekarang = Arah.kiri;
    
    private MyWorld CurrentWorld;
    private int[][] world_map;
    private int scoreIncreement;

    private final int SS = MyWorld.SCREEN_SIZE;
    private final int WS = MyWorld.WALL_SIZE;
    private final int WT = MyWorld.WALL_TOTAL;
    private final int CS = MyWorld.COIN_SIZE;
    private final int VELOCITY = 1;
    
    GreenfootImage imgKanan;
    GreenfootImage imgKiri;
    
    public void act(){
        GreenfootImage tamedZombie = new GreenfootImage("tamedZombie.png");
        tamedZombie.scale(WS, WS);
        if (CurrentWorld.potion == true){
            isTouchCoin();  
            setImage(tamedZombie);
        } else{
           animate(); 
        }
        move();
        interact();
    }
    
    protected void addedToWorld(World world){
        CurrentWorld = (MyWorld) world;
        scoreIncreement = CurrentWorld.scoreIncreement;
    }
    
    private void animate(){
        if(arahSekarang == Arah.kanan){
            setImage(imgKanan);
        } else if(arahSekarang == Arah.kiri){
            setImage(imgKiri);
        }
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
        if (world.shield == true || world.sword == true || world.cloak == true || world.potion == true){
            return;
        }
        if(isTouching(Kid.class)){
            (new GreenfootSound("zombie_eating.MP3")).play();
            world.minusHealth();
            removeTouching(Kid.class);
            if (world.playerHealth>0){
                world.respawnPlayer();
            }
        }
    }
    
    public Zombie(TipeZombie zombie){
        world_map = MyWorld.world_map;
        imgKiri = new GreenfootImage(zombie + ".png");
        imgKiri.scale(WS, WS);
        setImage(imgKiri);
        imgKanan = new GreenfootImage(zombie + ".png");
        imgKanan.scale(WS, WS);
        imgKanan.mirrorHorizontally();
    }
    
    private void move(){
        // saat awal dan belum punya arah
        //if (arahSekarang == null){
        //    arahSekarang = randomArah(arahSekarang);
        //}
        
        // saat bisa belok
        int indexY = getY()/WS;
        int indexX = getX()/WS;
        if (world_map[indexY][indexX] == 4) {
            boolean antaraY = getY() >= indexY * WS + CS - 1 && getY() < indexY * WS + CS + 1;
            boolean antaraX = getX() >= indexX * WS + CS - 1 && getX() < indexX * WS + CS + 1;
            if(antaraY && antaraX) {
                arahSekarang = randomArah(arahSekarang);
                ubahKoordinat(arahSekarang, VELOCITY);
                int xNew = getX() + x;
                int yNew = getY() + y;
                setLocation(xNew, yNew);
                return;
            }
            
            //ubahKoordinat(arahSekarang, 1);
            //int xNew = ((getX() / WS) + x) * WS;
            //int yNew = ((getY() / WS) + y) * WS;
            //setLocation(xNew + CS, yNew + CS);
        }
        
        
        // saat tidak bisa bergerak lagi
        if (!canMove(arahSekarang)){
            arahSekarang = randomArah(arahSekarang);
        }
                
        ubahKoordinat(arahSekarang, VELOCITY);
        int xNew = getX() + x;
        int yNew = getY() + y;
        setLocation(xNew, yNew);
    }

    public Arah getArahSebaliknya(Arah arahSekarang){
        if(arahSekarang == Arah.atas){
            return Arah.bawah;
        }
        if(arahSekarang == Arah.bawah){
            return Arah.atas;
        }
        if(arahSekarang == Arah.kanan){
            return Arah.kiri;
        }
        if(arahSekarang == Arah.kiri){
            return Arah.kanan;
        }

        return arahSekarang;
    }
    
    public Arah randomArah(Arah arahSekarang){
        Arah toRemove = getArahSebaliknya(arahSekarang); 
        Arah[] angka = {Arah.atas, Arah.bawah, Arah.kanan, Arah.kiri};
        Arah[] arah = new Arah[4]; 
        
        for (int i = 0, j = 0 ; i < 4; i++) {
            if (angka[i] == toRemove) { 
                continue;
            } 
            arah[j] = angka[i];
            j++;
        }
        
        Arah arahFinal = arah[Greenfoot.getRandomNumber(3)];
        return arahFinal;
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
    
    private void ubahKoordinat(Arah arah, int velocity){
        x = 0;
        y = 0;
        if(!canMove(arah)){
            return;
        } 
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
    
}
