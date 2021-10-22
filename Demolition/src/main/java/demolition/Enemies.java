package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class Enemies extends PApplet {

    private int x;
    private int y;
    private int speedY;
    private int speedX;
    private int maxSpeed;
    private PImage sprite;
    //boolean left, right, up, down;
    String s;
    int width;
    int height;

    public Enemies(int x , int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.maxSpeed = 5;
        this.speedX = 0;
        this.speedY = 0;
        this.width = 480;
        this.height = 480;

    }

    public void tick(){

    }

    public void draw(PApplet app) {
        // handling graphics, just to draw sprites and no logic statements
        app.image(this.sprite , this.x, this.y);
    }
}
