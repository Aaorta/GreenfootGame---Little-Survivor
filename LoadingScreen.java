import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingScreen extends Actor
{
    public LoadingScreen(){	
        GreenfootImage img = new GreenfootImage("Loading.png");
        img.scale(MyWorld.SCREEN_SIZE, MyWorld.SCREEN_SIZE);
        setImage(img);
    }
}
