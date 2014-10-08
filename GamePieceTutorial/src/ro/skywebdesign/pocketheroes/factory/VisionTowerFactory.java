package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.VisionedMapObject;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class VisionTowerFactory {

	public static void calculateNextVisions() {
		//calculate vision number based on number of towers
		//there are 8 positions to match
		int noOfVisionsToShow = 0;
		
		switch(Constants.GAME.totalVisionTowers) {
			case 1:
				noOfVisionsToShow = 8;
				break;
			case 2:
				noOfVisionsToShow = 4;
				break;
			case 3:
				noOfVisionsToShow = 3;
				break;
			case 4:
				noOfVisionsToShow = 2;
				break;
			case 5:
			case 6:
			case 7:
				noOfVisionsToShow = 2;
				break;
			case 8:
				noOfVisionsToShow = 1;
				break;
			default:
				noOfVisionsToShow = 1;
		}
		
		for(int i = 0; i < noOfVisionsToShow;i++) {
			boolean fullVisionMapVisible = true;
			for(int j = 0; j < 8; j++) {
				if(Constants.GAME.visionMap[j] == 0){
					fullVisionMapVisible = false;
					break;
				}
			}
			
			if(fullVisionMapVisible){
				break;
			}
			
			int pos = Utils.getRandom(7);
			while(Constants.GAME.visionMap[pos] == 1) {
				pos = Utils.getRandom(7);
			}
			Constants.GAME.visionMap[pos] = 1;
		}
		
	}

	public static void draw(Canvas canvas, BoardView boardView) {
		Context context = boardView.getContext();

		for (int c = 1; c < (Constants.NUM_COLS_ROWS-1); c++) {
			for (int r = 2; r < (Constants.NUM_COLS_ROWS - 3); r++) {
			canvas.drawBitmap(
	    			BitmapFactory.getFightBitmap(context), 
	    			Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, 
	    			Constants.Y_ORIGIN +  r * Constants.SQUARE_SIZE
	    			, BitmapFactory.PAINT);
			}
		}
		
		//draw tower image
		canvas.drawBitmap(BitmapFactory.getVisionTowerX2Bitmap(context), Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 3 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		
		//draw middle section
		canvas.drawBitmap(BitmapFactory.getGrassBitmap(context), Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 4 * Constants.SQUARE_SIZE, BitmapFactory.PAINT_GRAYSCALE);
		canvas.drawBitmap(BitmapFactory.getMarkBitmap(context), Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 4 * Constants.SQUARE_SIZE, BitmapFactory.PAINT_GRAYSCALE);
		
		
		for(int i = 0; i < 8; i++) {
			
			int r = 0;
			int c = 0;
			
			if(i == 0) {r = 3;c = 5;} else if( i == 1) {r = 3;c = 6;}else if(i == 2){r = 3;c = 7;}
			else if(i == 3) {r = 4;c = 5;} else if( i == 4) {r = 4;c = 7;}
			else if(i == 5) {r = 5;c = 5;} else if( i == 6) {r = 5;c = 6;}else if(i == 7){r = 5;c = 7;}
			
			Bitmap drawble = null;
			if(Constants.GAME.visionMap[i] == 1) {
				//get object type based on artifact position
				int mapPositionRow = 0;
				int mapPositionCol = 0;
				if(i == 0) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() - 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() - 1;
				} else
				if(i == 1) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() - 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol();
				} else
				if(i == 2) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() - 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() + 1;
				} else		
				if(i == 3) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow();
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() - 1;
				} else		
				if(i == 4) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow();
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() + 1;
				} else	
				if(i == 5) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() + 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() - 1;
				} else
				if(i == 6) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() + 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol();
				} else
				if(i == 7) {
					mapPositionRow = Constants.GAME.visionTowerPosition.getRow() + 1;
					mapPositionCol = Constants.GAME.visionTowerPosition.getCol() + 1;
				}
				
				//check if tile is outside map borders
				if(mapPositionCol < 0 || mapPositionCol > 9 || mapPositionRow < 0 || mapPositionRow > 9) {
					drawble = BitmapFactory.getBrickBitmap(context);
					canvas.drawBitmap(drawble, Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + r * Constants.SQUARE_SIZE, BitmapFactory.PAINT_GRAYSCALE);
				}else {
					MapObject mapObject = Constants.GAME.mapObjects[mapPositionRow][mapPositionCol];
					
					//only visioned map objects are displayed on the vision map
					if(mapObject instanceof VisionedMapObject) {
						mapObject.draw(canvas, new Position(r,c), true);
					} else {
						drawble = BitmapFactory.getGrassBitmap(context);
						canvas.drawBitmap(drawble, Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + r * Constants.SQUARE_SIZE, BitmapFactory.PAINT_GRAYSCALE);
					}
				}
					
			} else {
				drawble = BitmapFactory.getDarkBitmap(context);
				canvas.drawBitmap(drawble, Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + r * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
			}
			
		}
		
	    if(Constants.GAME.visionTowerStage == 2) {
			Constants.GAME.visionTowerStage = 0;
			Constants.GAME.visionTowerMode = false;
	    }
	    
	    boardView.invalidate();
	}

	public static Position generateArtifactPosition(MapObject[][] mapObjects) throws MapLoadingException {
		int i = 0;
		Position pos = null;
		do {
			i++;
			pos = Utils.getRandomPosition(mapObjects);
			
			
			
			//check for valid positon. At least one VisionedMapObject near it or it must be at the adega of the map
			//when the object is on the edge of the map it can have no VisionedMapObjects near it!
			if(pos.getRow() < 1 || pos.getRow() > 8 || pos.getCol() > 8 || pos.getCol() < 1){
				break;
			}
			
			if(	(mapObjects[pos.getRow()-1][pos.getCol()-1] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()][pos.getCol()-1] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()+1][pos.getCol()-1] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()-1][pos.getCol()] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()+1][pos.getCol()] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()-1][pos.getCol()+1] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()][pos.getCol()+1] instanceof VisionedMapObject) ||
					(mapObjects[pos.getRow()+1][pos.getCol()+1] instanceof VisionedMapObject)
					) {
				break;
			}
			
		} while(i <= 3);
		Utils.log("Vision Tower", "Generated artifact at position: " + pos);
		return pos;
	}

}
