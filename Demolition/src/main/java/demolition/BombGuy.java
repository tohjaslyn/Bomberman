package demolition;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.List;

public class BombGuy extends GameObject {

    private int lives;

    public BombGuy(int x , int y, PImage sprite, int row, int col, int lives){
        super();
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.maxSpeed = 5;
        this.speedX = 0;
        this.speedY = 0;
        this.width = 480;
        this.height = 480;
        this.row = row;
        this.col = col;
        this.lives = lives;
    }

    public void setMap(Map[][] arr){
        this.arr = arr;
    }

    public void tick(){
        initialRow = this.row;
        initialCol = this.col;

        // handle only logic
        if (left){
            this.speedY = 0;
            //this.speedX = -this.maxSpeed;
            if ( arr[this.row ][this.col - 1].canPlayerBeHere == true ){
                this.x = arr[this.row][this.col-1].x;
                this.y = arr[this.row][this.col-1].y;
                if (arr[this.row][this.col-1].type.equals("Y") || arr[this.row][this.col-1].type.equals("R") ){
                    this.lives -= 1;
                    System.out.println(lives);
                }
                this.col -= 1;
                
            }
            this.left = false;
            
        }
        if (right){
            this.speedY = 0;
            this.speedX = 1;
            //this.speedX = this.maxSpeed;
            if ( arr[this.row ][this.col + 1].canPlayerBeHere == true ){
                this.x = arr[this.row][this.col+1].x;
                this.y = arr[this.row][this.col+1].y;
                this.col += 1;
            }
            this.right = false;
        }
        if ((!left && !right) || (left && right)){
            this.speedX = 0;
        }
        if (up){
            this.speedX = 0;
            //this.speedY = -this.maxSpeed;
            if ( arr[this.row - 1][this.col].canPlayerBeHere == true ){
                this.x = arr[this.row-1][this.col].x;
                this.y = arr[this.row-1][this.col].y;
                this.row -= 1;
            }
            this.up = false;
            
        }
        if (down){
            this.speedX = 0;
            //this.speedY = this.maxSpeed;
            if ( arr[this.row + 1][this.col].canPlayerBeHere == true ){
                this.x = arr[this.row+1][this.col].x;
                this.y = arr[this.row+1][this.col].y;
                this.row += 1;
            }
            this.down = false;
        }
        if ((!up && !down) || (up && down)){
            this.speedY = 0;
        }

        checkBound();
       
        // this.x += this.speedX;
        // this.y += this.speedY;
        updatePosition(this.row, this.col, initialRow, initialCol);
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