import greenfoot.*;  

public class L1Stage3 extends Level1
{
    public L1Stage3(int initialScore){
        int[][] map = {
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 0, 0, 4, 0, 0, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 4, 0, 0, 2},
            {2, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 2},
            {2, 4, 0, 1, 1, 0, 4, 0, 4, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 2},
            {2, 0, 1, 1, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 4, 2},
            {2, 4, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 2},
            {2, 0, 1, 1, 0, 0, 4, 0, 0, 0, 4, 1, 0, 0, 0, 0, 1, 1, 1, 2},
            {2, 0, 1, 1, 1, 1, 0, 1, 1, 1, 4, 0, 0, 1, 1, 1, 1, 0, 0, 2},
            {2, 4, 0, 0, 0, 1, 0, 0, 0, 0, 4, 1, 0, 0, 4, 0, 4, 0, 1, 2},
            {2, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 4, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 1, 0, 1, 0, 2},
            {2, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 2},
            {2, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 1, 1, 0, 4, 0, 4, 2},
            {2, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 4, 0, 0, 4, 0, 1, 0, 2},
            {2, 0, 0, 0, 0, 4, 0, 0, 4, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 2},
            {2, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 2},
            {2, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 2},
            {2, 0, 1, 0, 0, 0, 1, 0, 0, 4, 0, 4, 0, 0, 1, 0, 0, 0, 0, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        BG_IMAGE = new GreenfootImage("Background3.jpg");
        GreenfootImage BG_ASLI = new GreenfootImage("Background3.jpg");
        BG_ASLI.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        BG_IMAGE.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        this.setBackground(BG_ASLI);
        
        int jumlahZombie = 5;
        int jumlahTangan = 1;
        int jumlahShield = 1;
        int jumlahSword = 1;
        int jumlahSpeedup = 1;
        int jumlahPotion = 1;
        int jumlahJubah = 1;
                
        this.currentLevel = "L1Stage3";
        this.currentzombie = Zombie.TipeZombie.ZStage3;
        this.score = initialScore;
        this.star = 2;
        this.world_map = map;
        
        if (easyMode){
           this.scoreIncreement = 10;
        }
        this.initializeSpawnLocations();
        this.InitialSpawn(jumlahZombie, jumlahTangan, jumlahShield, jumlahSword, jumlahSpeedup, jumlahPotion, jumlahJubah);
        this.DrawWall("Koin.png", "Gentong.png");
        
        if (player.equals("female")){
            this.equipSpeedup();
            Kid.VELOCITY = 2;
        } else if (player.equals("om")){
            this.equipShield();
        }
    }
}
