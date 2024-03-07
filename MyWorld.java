import greenfoot.*;  
import java.util.List;
import java.util.ArrayList;

public class MyWorld extends World
{
    public static final int SCREEN_SIZE = 600;
    public static final int WALL_SIZE = SCREEN_SIZE/20;
    public static final int WALL_TOTAL = SCREEN_SIZE/WALL_SIZE;
    public static final int COIN_SIZE = WALL_SIZE/2;
    
    public static boolean easyMode = false;
    public static int[][] world_map;
    public static String currentLevel;
    public int max_koin = 0;
    public int collected_koin = 0;
    public int scoreIncreement = 30;
    
    public static String player;
    public int playerHealth = 1;
    public int score = 0;
    public int star = 0;
    
    public boolean shield = false;
    public boolean sword = false;
    public boolean speedup = false;
    public boolean potion = false;
    public boolean cloak = false;
    
    public int speedupTime = 0;
    public int potionTime = 0;
    public int cloakTime = 0;
    
    public Information TScore = new Information("Score.png", 7*WALL_SIZE, WALL_SIZE);
    public Information PScore = new Information("SCORE");
    public Information AScore = new Information("0");
    public Information IShield = new Information("Perisai.png", WALL_SIZE, WALL_SIZE);
    public Information ISword = new Information("Pedang.png", WALL_SIZE, WALL_SIZE);
    public Information ISpeedup = new Information("Speedup.png", WALL_SIZE, WALL_SIZE);
    public Information IPotion = new Information("Potion.png", WALL_SIZE, WALL_SIZE);
    public Information ICloak = new Information("Cloak.png", WALL_SIZE, WALL_SIZE);
    public Information IHealth1 = new Information("Health.png", WALL_SIZE, WALL_SIZE);
    public Information IHealth2 = new Information("Health.png", WALL_SIZE, WALL_SIZE);
    public Information IHealth3 = new Information("Health.png", WALL_SIZE, WALL_SIZE);
    
    public static GreenfootSound music = new GreenfootSound("backsound.mp3");
    public Zombie.TipeZombie currentzombie;
    public Actor ZombieHand = new ZombieHand();
    public GreenfootImage BG_IMAGE;
    
    public void minusHealth(){ 
        playerHealth--;
    }
    public void starIncrease(){
        star++;
    }
    public void scoreIncrease(int value){
        collected_koin++;
        score += value;
        if(collected_koin == max_koin) {
            (new GreenfootSound("portal_spawn.mp3")).play();
            randSpawn(new Portal());
        }
    }
    public void equipShield(){
        shield = true;
    }
    public void equipSword(){
        sword = true;
    }
    public void unequipShield(){
        shield = false;
    }
    public void unequipSword(){
        sword = false;
    }
    public void equipSpeedup(){
        speedup = true;
        speedupTime = 1000;
    }
    public void equipCloak(){
        cloak = true;
        cloakTime = 1000;
    }
    public void equipPotion(){
        potion = true;
        potionTime = 1000;
    }
    
    public MyWorld()
    {    
        super(SCREEN_SIZE, SCREEN_SIZE, 1);
        Greenfoot.setSpeed(55);
        music.setVolume(50);
        music.playLoop();
        showIndicator();
    }
    
    public void showIndicator(){
        addObject(TScore, 4*WALL_SIZE, COIN_SIZE);
        addObject(PScore, COIN_SIZE+2*WALL_SIZE, COIN_SIZE);
        addObject(AScore, COIN_SIZE + 4*WALL_SIZE, COIN_SIZE);
        addObject(IShield, 10 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(ISword, 11 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(ISpeedup, 12 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(IPotion, 13 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(ICloak, 14 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        
        addObject(IHealth1, 19 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(IHealth2, 18 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
        addObject(IHealth3, 17 * WALL_SIZE + COIN_SIZE, COIN_SIZE);
    }
    
    public void updateHealth(){
        switch (playerHealth){
            case 1:
                IHealth1.updateImage(255);
                IHealth2.updateImage(100);
                IHealth3.updateImage(100);
                break;
            case 2:
                IHealth1.updateImage(255);
                IHealth2.updateImage(255);
                IHealth3.updateImage(100);
                break;
            case 3:
                IHealth1.updateImage(255);
                IHealth2.updateImage(255);
                IHealth3.updateImage(255);
                break;
        }
    }
    
    public void updateInfoScore(){
        int width = AScore.getWidth();
        AScore.updateText(""+score, WALL_SIZE + 4*WALL_SIZE, COIN_SIZE);
    }
    
    public void updateIndikator(){
        if(shield == true){
            IShield.updateImage(255);
        } else{
            IShield.updateImage(100);
        }
        if(sword == true){
            ISword.updateImage(255);
        } else{
            ISword.updateImage(100);
        }
        if(speedup == true){
            ISpeedup.updateImage(255);
        } else{
            ISpeedup.updateImage(100);
        }
        if(potion == true){
            IPotion.updateImage(255);
        } else{
            IPotion.updateImage(100);
        }
        if(cloak == true){
            ICloak.updateImage(255);
        } else{
            ICloak.updateImage(100);
        }
    }
    
    public int win = 0;
    
    public void act() {
        updateInfoScore();
        updateIndikator();
        updateHealth();
        if(playerHealth == 0) {
            callGameOver();
            (new GreenfootSound("game_over.mp3")).play();
            playerHealth = -1;
            return;
        }
        if(win == 1){
            callGameOver();
            (new GreenfootSound("game_over.mp3")).play();
            win = -1;
            return;
        }
        if (potion == true){
            potionTimer();
        }
        if (cloak == true){
            cloakTimer();
        }
        if (speedup == true){
            speedupTimer();
        }
    }
    
    public void potionTimer(){
        if (potionTime == 0){
            potion = false;
        } else if (potionTime > 0){
            potionTime--;
        }
    }
    
    public void cloakTimer(){
        if (cloakTime == 0){
            cloak = false;
        } else if (cloakTime > 0){
            cloakTime--;
        }
    }
    
    public void speedupTimer(){
        if (speedupTime == 0){
            speedup = false;
        } else if (speedupTime > 0){
            speedupTime--;
        }
    }
    
    public void callLoading(){
        addObject(new LoadingScreen(), MyWorld.SCREEN_SIZE/2, MyWorld.SCREEN_SIZE/2);
    }
    
    public void callGameOver() {
        addObject(new GameOverFade(), SCREEN_SIZE/2, SCREEN_SIZE/2);
    }
    
    public ArrayList<Point> spawnLocations = new ArrayList<>();

    public class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }   
    }
    
    public void initializeSpawnLocations() {
        for (int y = 0; y < WALL_TOTAL; y++) {
            for (int x = 0; x < WALL_TOTAL; x++) {
                if (world_map[y][x] == 0) {
                    spawnLocations.add(new Point(x, y));
                }
            }
        }
    }

    public void randSpawn(Actor object) {
        if (spawnLocations.isEmpty()) {
        // No valid spawn locations left
        return;
        }

        int index = Greenfoot.getRandomNumber(spawnLocations.size());
        Point spawnLocation = spawnLocations.remove(index);

        int x = spawnLocation.x * WALL_SIZE + COIN_SIZE;
        int y = spawnLocation.y * WALL_SIZE + COIN_SIZE;

        addObject(object, x, y);
    }
    
    // public void randSpawn(Actor object){
        // int x = 0;
        // int y = 0;
        // int result = 1;
        // // spawn hanya di koin
        // while(result != 0){
            // x = Greenfoot.getRandomNumber(WALL_TOTAL);
            // y = Greenfoot.getRandomNumber(WALL_TOTAL);
            // result = world_map[y][x];
        // }
        // //world_map[y][x] = 3;
        // addObject(object, x*WALL_SIZE + COIN_SIZE, y*WALL_SIZE + COIN_SIZE);        
    // }
    
    public void respawnPlayer() {
        randSpawn(new Kid(player));
    }
    
    public void respawnZombie(){
        randSpawn(new Zombie(currentzombie));
    }
    
    public void respawnTangan(){
        removeObject(ZombieHand);
        randSpawn(ZombieHand);
    }
    
    /*
    public void drawWall(Wall.TipeWall wall, Coin.TipeCoin coin) {
        for(int y = 0; y < WALL_TOTAL; y++){
            for(int x = 0; x < WALL_TOTAL; x++){
                if(world_map[y][x] == 1){  
                    addObject(new Wall(wall), ((x+1)*WALL_SIZE) - COIN_SIZE, ((y+1)*WALL_SIZE) - COIN_SIZE);
                } else if (world_map[y][x] == 0 || world_map[y][x] == 4) {
                    max_koin++;
                    addObject(new Coin(coin), ((x+1)*WALL_SIZE) - COIN_SIZE, ((y+1)*WALL_SIZE) - COIN_SIZE);
                } else if(world_map[y][x] == 2){
                    addObject(new Border(), ((x+1)*WALL_SIZE) - COIN_SIZE, ((y+1)*WALL_SIZE) - COIN_SIZE);
                }
            }
        }
    }
    */
    
    
    public void InitialSpawn(int jumlahZombie, int jumlahTangan, int jumlahShield, int jumlahSword, int jumlahSpeedup, int jumlahPotion, int jumlahJubah) {
        randSpawn(new Kid(player));   
        
        for(int i = 0; i < jumlahShield; i++){
            randSpawn(new Shield());
        }       
        for(int i = 0; i < jumlahSword; i++){
            randSpawn(new Sword());
        }
        for(int i = 0; i < jumlahTangan; i++){
            randSpawn(ZombieHand);
        }
        for(int i = 0; i < jumlahZombie; i++){
            randSpawn(new Zombie(currentzombie));
        }
        for(int i = 0; i < jumlahSpeedup; i++){
            randSpawn(new Speedup());
        }
        for(int i = 0; i < jumlahPotion; i++){
            randSpawn(new Potion());
        }
        for(int i = 0; i < jumlahJubah; i++){
            randSpawn(new Cloak());
        }
    }
   
    public void DrawWall(String jenisCoin, String jenisWall) {
        GreenfootImage img = getBackground();
        img.scale(SCREEN_SIZE, SCREEN_SIZE);
        GreenfootImage coin = new GreenfootImage(jenisCoin);
        coin.scale(15, 15);
        GreenfootImage border = new GreenfootImage("Border.png");
        border.scale(30, 30);
        GreenfootImage wall = new GreenfootImage(jenisWall);
        wall.scale(30, 30);
        
        for(int y = 0; y < WALL_TOTAL; y++){
            for(int x = 0; x < WALL_TOTAL; x++){
                if(world_map[y][x] == 1){  
                    img.drawImage(wall, x*WALL_SIZE, y*WALL_SIZE);
                } else if (world_map[y][x] == 0 || world_map[y][x] == 4) {
                    max_koin++;
                    img.drawImage(coin, x*WALL_SIZE + COIN_SIZE/2, y*WALL_SIZE + COIN_SIZE/2);
                } else if(world_map[y][x] == 2){
                     img.drawImage(border, x*WALL_SIZE, y*WALL_SIZE);
                }
            }
        }
        //setBackground(img);
    }
    
    // public void removeCoinAt(int xIndex, int yIndex) {
        // GreenfootImage img = getBackground();
        // img.scale(SCREEN_SIZE, SCREEN_SIZE);
        
        // for (int x = 0; x < COIN_SIZE; x++) {
            // for (int y = 0; y < COIN_SIZE; y++) {
                // Color color = BG_IMAGE.getColorAt((xIndex*WALL_SIZE + COIN_SIZE/2) + x, (yIndex*WALL_SIZE + COIN_SIZE/2) + y  );
                // img.setColorAt((xIndex*WALL_SIZE + COIN_SIZE/2) + x, (yIndex*WALL_SIZE + COIN_SIZE/2) + y, color);
            // }
        // }
        // setBackground(img);
    // }
    
    public void removeCoinAt(int xIndex, int yIndex) {
        GreenfootImage img = getBackground();
        img.scale(SCREEN_SIZE, SCREEN_SIZE);
        int startX = (xIndex * WALL_SIZE + COIN_SIZE / 2);
        int startY = (yIndex * WALL_SIZE + COIN_SIZE / 2);
        removeCoinPixel(img, startX, startY, COIN_SIZE, COIN_SIZE);
        setBackground(img);
    }
    
    private void removeCoinPixel(GreenfootImage img, int x, int y, int width, int height) {
        if (width == 0 || height == 0) {
            // Base case: no more pixels to overwrite
            return;
        }
    
        // Mengganti backgroun colour per pixel
        Color color = BG_IMAGE.getColorAt(x, y);
        img.setColorAt(x, y, color);
    
        // Rekursif untuk tiap pixel
        if (width > 1) {
            // berpindah ke pixel selanjutnya
            removeCoinPixel(img, x + 1, y, width - 1, height);
        } else {
            // berpindah baris
            removeCoinPixel(img, x - (COIN_SIZE - 1), y + 1, COIN_SIZE, height - 1);
        }
    }

}
