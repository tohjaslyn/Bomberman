package demolition;

import processing.core.PImage;
import processing.core.PApplet;

public class YellowEnemy extends Enemies{
    //The Yellow Enemy moves in a straight line, but on collision 
    //with a wall will attempt to move clockwise (if it was moving left,
    // it will try to move up, and if unable to, then try right and then left; 
    //similarly if it was moving down, it will try to move left,
    // otherwise up or right).
    
    boolean hitWall = false;
    boolean[] randomMove = new boolean[]{right, left, up, down };
    int move = (int)random(0,4);

    public YellowEnemy(int x , int y, PImage sprite, String type , int row, int col){
        super(x , y,  sprite,  type, row, col);
    }

    public void chase(){
    }

    public void tick(){
        if (hitWall){
            if (move == 0){
                move = 3;
            }
            else if (move == 1){
                move = 2;
            }
            else if (move == 2){
                move = 0;
            }
            else if (move == 3){
                move = 1;
            }
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
    }



}