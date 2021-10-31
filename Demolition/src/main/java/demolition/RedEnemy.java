package demolition;
import java.lang.Math;

import processing.core.PImage;
import processing.core.PApplet;

public class RedEnemy extends Enemies{
    
    boolean hitWall = false;
    boolean[] randomMove = new boolean[]{right, left, up, down };
    int move = (int)random(0,4);

    public RedEnemy(int x , int y, PImage sprite, String type , int row, int col ){
        super(x , y,  sprite,  type, row, col);
    }

    public void tick(){
        initialRow = this.row;
        initialCol = this.col;

        if (hitWall){
            move = (int)random(0,4);
            //System.out.println(move);
            this.hitWall = false;
        }
        this.randomMove[move] = true;
        //System.out.print(randomMove[move]);

        if (move == 0){
            if ( arr[this.row ][this.col + 1].canPlayerBeHere == true ){
                this.x = arr[this.row][this.col+1].x;
                this.y = arr[this.row][this.col+1].y;
                this.col += 1;
            }
            else{
                this.hitWall = true;
                this.randomMove[move] = false;
            }
        }
        else if (move == 1){
            if ( arr[this.row ][this.col - 1].canPlayerBeHere == true ){
                this.x = arr[this.row][this.col-1].x;
                this.y = arr[this.row][this.col-1].y;
                this.col -= 1;
            }
            else{
                this.hitWall = true;
                this.randomMove[move] = false;
            }
        }
        else if (move == 2){
            if ( arr[this.row - 1][this.col].canPlayerBeHere == true ){
                this.x = arr[this.row-1][this.col].x;
                this.y = arr[this.row-1][this.col].y;
                this.row -= 1;
            }
            else{
                this.hitWall = true;
                this.randomMove[move] = false;
            }  
        }
        else if (move == 3){
             if ( arr[this.row + 1][this.col].canPlayerBeHere == true ){
                this.x = arr[this.row+1][this.col].x;
                this.y = arr[this.row+1][this.col].y;
                this.row += 1;
            }
            else{
                this.hitWall = true;
                this.randomMove[move] = false;
            }
            
        }

        updatePosition(this.row, this.col, initialRow, initialCol);
        
    }

}