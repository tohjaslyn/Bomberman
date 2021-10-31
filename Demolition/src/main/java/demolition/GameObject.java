package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public abstract class GameObject extends PApplet {
    protected int x;
    protected int y;
    protected int speedY;
    protected int speedX;
    protected int maxSpeed;
    protected PImage sprite;
    protected int width;
    protected int height;
    protected static Map[][] arr;
    protected int row;
    protected int col;
    protected boolean left = false;
    protected boolean right = false;
    protected boolean up = false;
    protected boolean down = false;
    protected int initialRow;
    protected int initialCol;

    public void setMap(Map[][] arr){
        this.arr = arr;
    }

    public void updatePosition(int newRow, int newCol, int oldRow, int oldCol){
        Map temp = arr[oldRow][oldCol];
        // System.out.println(arr[oldRow][oldCol]);
        // System.out.println(arr[newRow][newCol]);
        arr[oldRow][oldCol] = arr[newRow][newCol];
        arr[newRow][newCol] = temp;
        // System.out.println(arr[oldRow][oldCol]);
        // System.out.println(arr[newRow][newCol]);
    }

    public abstract void tick();

    public void draw(PApplet app) {
        // handling graphics, just to draw sprites and no logic statements
        app.image(this.sprite , this.x, this.y);
    }
}