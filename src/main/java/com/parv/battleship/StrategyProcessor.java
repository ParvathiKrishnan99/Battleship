package com.parv.battleship;

public class StrategyProcessor {//create separate class for strategy
	  //finalize values of grid size
	  static final int ROW = 10;
	  static final int COL = 10;
	  int[][] compShotList = new int[ROW][COL];//array that the computer uses to keep track of its shots
	  int[] firstHit={-1,-1};//keeps track of the coordinate upon hit
	  int dir = -1;//determines direction the the comp keeps shooting
	  int row = -1;//row value of shooting coordinate
	  int col = -1;//column value of shooting coordinate
	  String[] mode={"randomMode","firstHitMode","forwardContinueMode","backwardContinueMode"};//String that explains what mode the comp is on (for understanding purposes)
	  int[][] futurePoints = new int[4][2]; //array that stores the predicted values of the opponents ships 
	  String stage=mode[0];//variable that controls the mode the comp is on
	  
	  public StrategyProcessor(){//constructor that initializes
	    initialize();//calling method that initializes
	  }
	  
	  public int[] getNextRowAndColumn(){//public method that returns a coordinate the GUI will use to shoot
	    if(stage.equals(mode[0])){//if no previous hits have been recorded, proceed to random mode
	      do{
	        row = getRandom();//sets row value to random number
	        col = getRandom();//sets col value to random number
	      }while(!isValid());//if its not valid, repeat to get new values
	      int[] x={row+1,col+1};
	      return x;//return these values in an array
	    }
	    else{//if stage is not randomMode do the following
	      if(stage.equals(mode[1])){//when stage is in firstHitMode
	        do{
	          row=futurePoints[dir][0];//set values to the direction specified(will be NORTH,EAST,SOUTH,WEST)
	          col=futurePoints[dir][1];
	          dir++;//change direction if point validation cannot be confirmed
	        }while(!isValid());//if its not valid, repeat with new direction
	        int[] x = {row+1,col+1};
	        return x;//return these values in an array
	      }
	      else if(stage.equals(mode[2])){//when stage is in forwardContinueMode
	        row=futurePoints[dir][0];//set values as next coordinate in that direction
	        col=futurePoints[dir][1];
	        if(isValid()){ // if this coordinate is valid         
	          int[] x = {row+1,col+1};
	          return x;//return these values in an array
	        }
	        else{//if this coordinate is invalid
	          dir=getOpposite(dir);//switch to the opposite direction
	          row=futurePoints[dir][0];//and continue in that direction (basically backwardsContinueMode)
	          col=futurePoints[dir][1];
	          if(dir==0){//dependant on the direction chosen increment to the next coordinate
	            row = firstHit[0]-1;
	          }else if(dir==1){
	            col = firstHit[1]+1;
	          }else if(dir==2){
	            row = firstHit[0]+1;
	          }else if(dir==3){
	            col = firstHit[1]-1;
	          }
	          if(isValid()){  //if this coordinate is valid        
	            int[] x = {row+1,col+1};
	            return x;//return these values in an array
	          }
	          else{//if coordinate returns invalid
	            stage=mode[0];//set gameplay to randomMode (means all ships in this row/col have been demolished)
	            getNextRowAndColumn();//refresh settings
	          }
	        }
	      }
	      else if(stage.equals(mode[3])){//when stage is in backwardsContinueMode
	        row=futurePoints[dir][0];//set values as the next coordinate in the opposite direction
	        col=futurePoints[dir][1];
	        if(isValid()){//if coordinate return valid
	          int[] x = {row+1,col+1};
	          return x;//return these values in an array
	        }
	        else{//if coordinate returns invalid
	          stage=mode[0];//give up and set gameplay to randomMode
	          getNextRowAndColumn();//refresh settings
	          
	        }
	      }
	    }
	    
	    int[] x = {row+1,col+1};
	    return x;//code won't run if this isn't here(method must return something)
	  }
	  public void updateList(int status){//this method updates comps hit/miss/sink info
	    
	    if(status==0){//if at any point, GUI returns a hit
	      futurePoints[0] = new int[]{row-1, col};//refresh my future points to suit that new hit coordinate
	      futurePoints[1] = new int[]{row, col+1};
	      futurePoints[2] = new int[]{row+1, col};
	      futurePoints[3] = new int[]{row, col-1};
	      compShotList[row][col]=1;//update shot list with 1 for hit
	      
	      if(stage.equals(mode[0])){//if stage is in random mode when hit
	        firstHit[0]=row;//store this hit coordinate in an array
	        firstHit[1]=col;
	        dir=0;//set direction to NORTH
	        stage=mode[1];//Game enters next mode(firstHitMode)
	        
	      }
	      else if(stage.equals(mode[1])){//if stage has hit its "first" target in dirstHitMode
	        dir--;//set direction as previous direction because counter was incremented 
	        stage=mode[2];//game enters next mode(forwardContinueMode)
	      }
	    }
	    else if (status==1){//if at any point GUI returns a miss
	      compShotList[row][col]=2;//update shot list with 2 for miss
	      if(stage.equals(mode[2])){//if stage is in forwardContinueMode when miss
	        dir=getOpposite(dir);//set direction as opposite
	        row=firstHit[0];//refresh future values
	        col=firstHit[1];
	        futurePoints[0] = new int[]{row-1, col};
	        futurePoints[1] = new int[]{row, col+1};
	        futurePoints[2] = new int[]{row+1, col};
	        futurePoints[3] = new int[]{row, col-1};
	        stage=mode[3];//game is now set to backwardContinueMode
	      }
	      else if(stage.equals(mode[3])){//if stage is backwardContinueMode when miss
	        
	        stage=mode[0];//give up and set to random mode
	      }
	      
	    }
	    else{//if GUI returns "sunk", change mode to RANDOMMODE
	      stage=mode[0];
	    }
	  }
	  private static int getRandom(){//method to return a random number
	    int x= (int) Math.floor(Math.random()*ROW);//within the parameters of the grid
	    return x;
	    
	  }
	  private void initialize(){//method to set array values to 0
	    for(int i=0; i<ROW; i++){
	      for(int j=0; j<COL; j++){
	        compShotList[i][j]=0;//setting each element to 0
	      }
	    }
	  }
	  private boolean isValid(){//checks if point is valid before shooting
	    
	    if(((row<0)||(row>9)||(col<0)||(col>9) || compShotList[row][col]!=0)){//if points are OOB or the point as been shot before
	      return false;//return false
	    }else{
	      return true;//else return true
	    }
	  }
	  private static int getOpposite(int currentDirection){//this method returns the opposite direction
	    if((currentDirection==0)||(currentDirection==1)){//in the future points array every second element is an opposite, so to call opposite add or subtract 2
	      return currentDirection+2;
	    }else{
	      return currentDirection-2;
	    }
	  }

	}