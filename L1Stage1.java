import greenfoot.*;  

public class L1Stage1 extends Level1
{   
    public L1Stage1(boolean Mode, String currentPlayer){      
        int[][] map = {
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 0, 0, 4, 0, 4, 0, 4, 0, 0, 0, 0, 0, 4, 0, 4, 0, 4, 0, 2},
            {2, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 2},
            {2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 2},
            {2, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 2},
            {2, 0, 1, 0, 1, 0, 0, 0, 1, 4, 0, 0, 0, 4, 0, 4, 1, 0, 1, 2},
            {2, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 2},
            {2, 4, 0, 0, 0, 0, 1, 0, 1, 0, 0, 4, 0, 0, 1, 0, 0, 0, 0, 2},
            {2, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 2},
            {2, 0, 0, 0, 0, 4, 0, 4, 0, 0, 1, 0, 1, 4, 0, 0, 0, 0, 4, 2},
            {2, 1, 1, 1, 1, 1, 1, 0, 1, 4, 0, 4, 0, 4, 1, 1, 1, 1, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 4, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 4, 2},
            {2, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 2},
            {2, 0, 0, 0, 0, 4, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 2},
            {2, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 2},
            {2, 0, 0, 4, 0, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2},
            {2, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 2},
            {2, 0, 0, 4, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 2},   
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };
        BG_IMAGE = new GreenfootImage("Background1.jpg");
        BG_IMAGE.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        GreenfootImage BG_ASLI = new GreenfootImage("Background1.jpg");
        BG_ASLI.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        this.setBackground(BG_ASLI);
        
        int jumlahZombie = 5;
        int jumlahTangan = 1;
        int jumlahShield = 1;
        int jumlahSword = 1;
        int jumlahSpeedup = 1;
        int jumlahPotion = 1;
        int jumlahJubah = 1;
        
        easyMode = Mode;
        player = currentPlayer;
        
        this.currentLevel = "L1Stage1";
        this.currentzombie = Zombie.TipeZombie.ZStage1;
        this.score = 0;
        this.star = 0;
        world_map = map;
        
        if (easyMode){
           this.scoreIncreement = 10;
        }
        this.initializeSpawnLocations();
        this.InitialSpawn(jumlahZombie, jumlahTangan, jumlahShield, jumlahSword, jumlahSpeedup, jumlahPotion, jumlahJubah);
        this.DrawWall("Buah.png", "Rumput.png");
        
        if (easyMode){
           this.max_koin = 1; 
        }
        if (player.equals("female")){
            this.equipSpeedup();
            Kid.VELOCITY = 2;
        } else if (player.equals("om")){
            this.equipShield();
        }
    }
    
    
}
