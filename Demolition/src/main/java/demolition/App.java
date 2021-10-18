package demolition;

import processing.core.PApplet;
import processing.core.PImage;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    private BombGuy bombguy;
    private Map map;

    int directionX= 1; 
    int directionY= 0;
    int x=20;
    int y=20; 
    int speed=1;

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        // Load images during setup

        // passing in x, y , image to bombguy class
        this.bombguy = new BombGuy(0, 0, this.loadImage("src/main/resources/player/player1.png"));
        this.map = new Map(20,100, this.loadImage("src/main/resources/wall/solid.png"));
        // supposed to animate your own gif with the images given
    }

    public void draw() {
        background(0, 0, 0);
        // for image to draw onto the screen at x y 0
        // loading image inside draw reduces the speed, thus load in setup
        //this.image(this.sprite, 0, 0);

        this.bombguy.tick();
        this.map.tick();

        this.rect(x, y , 20, 20);
        this.bombguy.draw(this);
        this.map.draw(this);

        x = x+speed*directionX;
        y = y+speed*directionY; 
    }

    public void keyPressed(){

        if (key == CODED){
            if (keyCode == LEFT){
                if (directionX > 0){
                    directionX = -1;
                    directionY = 0;
                }
            }
            else if (keyCode == RIGHT){
                if (directionX < 0){
                    directionX = 1;
                    directionY = 0;
                }
            }
            else if (keyCode == UP){
                if (directionY < 0){
                    directionX = -1;
                    directionY = 0;
                }
            }
            else if (keyCode == DOWN){
                if (directionY < 0){
                    directionX = 1;
                    directionY = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
