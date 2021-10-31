package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class Enemies extends GameObject {

    private String type;

    public Enemies(int x , int y, PImage sprite, String type , int row, int col){
        super();
        
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.maxSpeed = 5;
        this.speedX = 0;
        this.speedY = 0;
        this.width = 480;
        this.height = 480;
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public abstract void tick();

}
