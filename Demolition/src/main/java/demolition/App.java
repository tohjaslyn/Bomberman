package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject; 
import processing.data.JSONArray; 
import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;

    //private BombGuy[] bombguys = new BombGuy[5];
    private JSONArray json;
    private JSONObject value;
    //private BombGuy bombguy;
    //private Map map;
    private BombGuy bombguy;

    private List<Enemies> enemy = new ArrayList<Enemies>();
    //boolean left, right, up, down;
    String s;
    private int x;
    private int y;
    
    private List<Map> arr = new ArrayList<Map>();

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);
        
        //json = loadJSONArray("config.json");
        value = loadJSONObject("config.json");
        int lives = value.getInt("lives");
        JSONArray levels = value.getJSONArray("levels");
        JSONObject level1 = levels.getJSONObject(0); 
        JSONObject level2 = levels.getJSONObject(1);

        String path1 = level1.getString("path");
        String path2 = level2.getString("path");

        String[] lines = loadStrings(path1);
        System.out.println(lines.length); // 13

        this.x = 0;
        this.y = 64;

        for (int i = 0; i < lines.length; i++){
            char[] chars = lines[i].toCharArray();

            for (char ch: chars){
                // System.out.print(ch);
                if (Character.toString(ch).matches("W") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/wall/solid.png"));
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("P") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"));
                    arr.add(map);
                    this.bombguy = new BombGuy(this.x, this.y, this.loadImage("src/main/resources/player/player1.png"));
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("B") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/broken/broken.png"));
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("G") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/goal/goal.png"));
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches(" ") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"));
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("R") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"));
                    arr.add(map);
                    Enemies redEnemy = new Enemies(this.x, this.y, this.loadImage("src/main/resources/red_enemy/red_down1.png"));
                    enemy.add(redEnemy);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("Y") ){
                    Map map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"));
                    arr.add(map);
                    Enemies redEnemy = new Enemies(this.x, this.y, this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"));
                    enemy.add(redEnemy);
                    this.x += 32;
                }
            }
            // System.out.println();

            this.x = 0;
            this.y += 32;
        }
        // Load images during setup
        // passing in x, y , image to bombguy class
        //this.bombguy = new BombGuy(0, 0, this.loadImage("src/main/resources/player/player1.png"));
        //this.map = new Map(0,64, this.loadImage("src/main/resources/wall/solid.png"));
        // supposed to animate your own gif with the images given
        //this.bombguy = new BombGuy(0,0, this.loadImage("src/main/resources/player/player1.png"));
    }

    public void draw() {
        background(255, 145, 0);
        // for image to draw onto the screen at x y 0
        // loading image inside draw reduces the speed, thus load in setup
        //this.image(this.sprite, 0, 0);

        //this.bombguy.tick();

        for ( Map maps : this.arr ){
            maps.tick();
            maps.draw(this);
        }
               
        for (Enemies enemyColour: this.enemy ){
            enemyColour.tick();
            enemyColour.draw(this);
        }
        this.bombguy.tick();
        this.bombguy.draw(this);

    }

    public void keyPressed(){

        s = "key: " + keyCode;
        switch(keyCode) {
            case LEFT: // 37
                this.bombguy.toggleLeft();
                break;
            case RIGHT: // 39
                this.bombguy.toggleRight();
                break;
            case UP: // 38
                this.bombguy.toggleUp();
                break;
            case DOWN: //40
                 this.bombguy.toggleDown();
                break; 
        }
    }

    public void keyReleased(){
        switch(keyCode) {
            case LEFT: // 37
                this.bombguy.untoggleLeft();
                break;
            case RIGHT: // 39
                this.bombguy.untoggleRight();
                break;
            case UP: // 38
                this.bombguy.untoggleUp();
                break;
            case DOWN: //40
                 this.bombguy.untoggleDown();
                break; 
        }
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
