package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Map{

    public int x;
    public int y;
    private PImage sprite;

    public Map(int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void xIncrease(){
        this.x += 32;
    }

    public void yIncrease(){
        this.y += 32;
    }

    public void tick(){
    }

    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }

}