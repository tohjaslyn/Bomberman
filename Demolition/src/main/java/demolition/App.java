package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import processing.data.JSONObject; 
import processing.data.JSONArray; 
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class App extends PApplet {

    // set up dimensions of gameplay
    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;
    public static final int FPS = 60;
    // data processing 
    private JSONArray json;
    private JSONObject value;
    private String s;
    private int x = 0;
    private int y = 64;
    private PFont font;
    private String displayLives;
    private int start;
    //private String displayTime;

    // game objects 
    private BombGuy bombguy;
    private List<Map> arr = new ArrayList<Map>();
    private List<Enemies> enemy = new ArrayList<Enemies>();
    private boolean move = true;
    private long lastPressProcessed = 0;
    private Map[][] fullMap = new Map[13][15];
    int timee;
    int wait = 1000;

    public App() {
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        
        timee = millis();
        frameRate(FPS);

        this.font = createFont("PressStart2P-Regular.ttf", 20);
        textFont(this.font);

        //json = loadJSONArray("config.json");
        value = loadJSONObject("config.json");
        int lives = value.getInt("lives");
        this.displayLives = Integer.toString(lives);

        JSONArray levels = value.getJSONArray("levels");

        JSONObject level1 = levels.getJSONObject(0); 
        JSONObject level2 = levels.getJSONObject(1);
        String path1 = level1.getString("path");
        int time1 = level1.getInt("time");
        //this.start = second();
        this.start = time1;
        //this.displayTime = Integer.toString(time1);

        String path2 = level2.getString("path");
        int time2 = level2.getInt("time");
        String[] lines = loadStrings(path1);

        Map player = new Map(100, 20, this.loadImage("src/main/resources/icons/player.png"), "I", false, false);
        arr.add(player);

        Map clock = new Map(250, 20, this.loadImage("src/main/resources/icons/clock.png"), "I", false, false);
        arr.add(clock);

        for (int i = 0; i < lines.length; i++){
            char[] chars = lines[i].toCharArray();
            int count = 0;
            for (char ch: chars){
                Map map;

                if (Character.toString(ch).matches("W") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/wall/solid.png"), "W", false, false);
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("P") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"), "E", false, true);
                    arr.add(map);
                    this.bombguy = new BombGuy(this.x, this.y, this.loadImage("src/main/resources/player/player1.png"), i, count, lives);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("B") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/broken/broken.png"), "B", true, false);
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("G") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/goal/goal.png") , "G", false, true);
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches(" ") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"), "E", false, true);
                    arr.add(map);
                    this.x += 32;
                }
                else if (Character.toString(ch).matches("R") ){
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"), "E", false , true);
                    arr.add(map);
                    Enemies redEnemy = new RedEnemy(this.x, this.y, this.loadImage("src/main/resources/red_enemy/red_down1.png"), "R", i, count) ;
                    enemy.add(redEnemy);
                    this.x += 32;
                }
                else {
                    map = new Map(this.x, this.y, this.loadImage("src/main/resources/empty/empty.png"), "E", false, true);
                    arr.add(map);
                    Enemies yellowEnemy = new YellowEnemy(this.x, this.y, this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"), "Y", i, count);
                    enemy.add(yellowEnemy);
                    this.x += 32;
                }

                this.fullMap[i][count] = map;
                count += 1;

            }
            // System.out.println();

            this.x = 0;
            this.y += 32;

        }
        this.bombguy.setMap(this.fullMap);

        for (Enemies enemyColour: this.enemy ){
            enemyColour.setMap(this.fullMap);
        }
       

        // passing in x, y , image to bombguy class
        //this.bombguy = new BombGuy(0, 0 , this.loadImage("src/main/resources/player/player1.png"));
        //this.map = new Map(0,64, this.loadImage("src/main/resources/wall/solid.png"));
        // supposed to animate your own gif with the images given
        //this.bombguy = new BombGuy(0,0, this.loadImage("src/main/resources/player/player1.png"));
        // for ( int i = 0; i < this.fullMap.length; i++ ){
        //     for ( int j = 0; j < this.fullMap[i].length; j ++ ){
        //         System.out.print(this.fullMap[i][j]);

        //     }
        //     System.out.println();            
        // }
    }

    public void draw() {
        background(255, 145, 0);
        
        text( this.displayLives , 140, 50);
        int timer = this.start - second();
        text( timer , 290, 50); 
        fill(0);
    
        // if (millis() < startMillis + duration) {
        //     ellipse(mouseX, mouseY, 10, 10);
        // }
     
        // loading image inside draw reduces the speed, thus load in setup

        for ( Map maps : this.arr ){
            maps.tick();
            maps.draw(this);
        }

        if(millis() - timee >= wait){

            for (Enemies enemyColour: this.enemy ){
                enemyColour.tick();
            }

            timee = millis();
        }

        for (Enemies enemyColour: this.enemy ){
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
