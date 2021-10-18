package demolition;

import processing.core.PImage;
import processing.core.PApplet;

//try to extend etc
public class Map{
    private int x;
    private int y;
    private int xVel;

    private PImage sprite;

    public Map(int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void tick(){

    }

    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }

}