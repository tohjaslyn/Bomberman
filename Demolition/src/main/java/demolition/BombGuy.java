package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class BombGuy {

    private int x;
    private int y;

    private PImage sprite;

    public BombGuy(int x , int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void tick(){
        // handle only logic

    }

    public void draw(PApplet app) {
        // handling graphics, just to draw sprites and no logic statements
        app.image(this.sprite , this.x, this.y);
    }
    
    public void move(){
        //left, right, up, down.
    
    }

}