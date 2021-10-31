package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Map{

    public int x;
    public int y;
    private PImage sprite;
    public String type;
    public boolean canBreak;
    public boolean canPlayerBeHere;

    // I, P, W, B, E, G

    public Map(int x, int y, PImage sprite, String type, boolean canBreak, boolean canPlayerBeHere){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.type = type;
        this.canBreak = canBreak;
        this.canPlayerBeHere = canPlayerBeHere;
    }

    public void tick(){
    }

    public void draw(PApplet app){
        app.image(this.sprite, this.x, this.y);
    }

}