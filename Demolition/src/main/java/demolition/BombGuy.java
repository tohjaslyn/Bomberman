package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class BombGuy extends PApplet {

    private int x;
    private int y;
    private int speedY;
    private int speedX;
    private int maxSpeed;
    private PImage sprite;
    //boolean left, right, up, down;
    String s;
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    int width;
    int height;

    public BombGuy(int x , int y, PImage sprite){
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
        // handle only logic

        if (left){
            this.speedY = 0;
            this.speedX = -this.maxSpeed;
        }
        if (right){
            this.speedY = 0;
            this.speedX = this.maxSpeed;
        }
        if ((!left && !right) || (left && right)){
            this.speedX = 0;
        }
        if (up){
            this.speedX = 0;
            this.speedY = -this.maxSpeed;
        }
        if (down){
            this.speedX = 0;
            this.speedY = this.maxSpeed;
        }
        if ((!up && !down) || (up && down)){
            this.speedY = 0;
        }

        checkBound();

        this.x += this.speedX;
        this.y += this.speedY;
    }
    
    public void checkBound(){
        if (this.x > this.width){ 
            this.x = -this.width;
        }
        if ( this.x < -this.width) { 
            this.x = this.width;
        }
        if (this.y > this.height ){
            this.y = -this.height;
        }
        if (this.y < -this.height){
            this.y = this.height;
        }
    }

    public void toggleUp(){
        this.up = true;
    }
    public void toggleDown(){   
        this.down = true;
    }

    public void toggleLeft(){
        this.left = true;
    }

    public void toggleRight(){
        this.right = true;
    }

   public void untoggleUp(){
        this.up = false;
    }
    public void untoggleDown(){   
        this.down = false;
    }

    public void untoggleLeft(){
        this.left = false;
    }

    public void untoggleRight(){
        this.right = false;
    }

    public void draw(PApplet app) {
        // handling graphics, just to draw sprites and no logic statements
        app.image(this.sprite , this.x, this.y);
    }
    
}