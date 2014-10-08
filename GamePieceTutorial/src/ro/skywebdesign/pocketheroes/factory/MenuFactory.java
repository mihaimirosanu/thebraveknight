package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.enums.Direction;
import ro.skywebdesign.pocketheroes.enums.MenuItem;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MenuFactory {

	public static void draw(Canvas canvas, BoardView boardView) {
		Context context = boardView.getContext();

		for (int c = 0; c < (Constants.NUM_COLS_ROWS); c++) {
			for (int r = 0; r < (Constants.NUM_COLS_ROWS); r++) {
			canvas.drawBitmap(
	    			BitmapFactory.getFightBitmap(context), 
	    			Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, 
	    			Constants.Y_ORIGIN +  r * Constants.SQUARE_SIZE
	    			, BitmapFactory.PAINT);
			}
		}
		
		//draw end turn image
		canvas.drawBitmap(BitmapFactory.getMenuEndTurnBitmap(context), Constants.X_ORIGIN + 1 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 1 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		//draw magic
				
		//draw dig image
		canvas.drawBitmap(BitmapFactory.getMenuDigBitmap(context), Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 1 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		//draw vision image
		canvas.drawBitmap(BitmapFactory.getMenuVisionBitmap(context), Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 4 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);

		//draw restart image
		canvas.drawBitmap(BitmapFactory.getMenuRestartBitmap(context), Constants.X_ORIGIN + 7 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 1 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
				
		
		//draw selected menu item
		int x_pad = 0;
		int y_pad = 0;
		String explanation = "";
		switch (Constants.GAME.selectedMenuItem) {
			case END_TURN: x_pad = 1;y_pad = 1;explanation = "Ends current turn";break;
			case DO_MAGIC: x_pad = 1;y_pad = 4;explanation = "View your collected spells";break;
			case DIG: x_pad = 4;y_pad = 1;explanation = "Dig on the current location in search for the artifact";break;
			case VIEW_VISION_MAP: x_pad = 4;y_pad = 4;explanation = "Review the vision map";break;
			case RESTART_LEVEL: x_pad = 7;y_pad = 1;explanation = "Restart level";break;
			case EXIT_LEVEL: x_pad = 7;y_pad = 4;explanation = "Exit game";break;
		}
		canvas.drawBitmap(BitmapFactory.getMenuSelectBitmap(context), Constants.X_ORIGIN + x_pad * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + y_pad * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		
		Paint paint = new Paint(); 
		//canvas.drawPaint(paint); 
		paint.setColor(Color.BLACK); 
		paint.setTextSize(60); 
		canvas.drawText(explanation,Constants.X_ORIGIN + 1 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 8 * Constants.SQUARE_SIZE, paint); 
		
	    if(Constants.GAME.visionTowerStage == 2) {
			Constants.GAME.visionTowerStage = 0;
			Constants.GAME.visionTowerMode = false;
	    }
	    
	    boardView.invalidate();
	}

	public static void handleMenuDirection(Direction direction) {
		if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.END_TURN)) Constants.GAME.selectedMenuItem = MenuItem.DIG;
		else if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.DIG)) Constants.GAME.selectedMenuItem = MenuItem.RESTART_LEVEL;
		else if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.RESTART_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.END_TURN;
		else if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.DO_MAGIC)) Constants.GAME.selectedMenuItem = MenuItem.VIEW_VISION_MAP;
		else if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.VIEW_VISION_MAP)) Constants.GAME.selectedMenuItem = MenuItem.EXIT_LEVEL;
		else if(direction.equals(Direction.RIGHT) && Constants.GAME.selectedMenuItem.equals(MenuItem.EXIT_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.DO_MAGIC;
		
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.END_TURN)) Constants.GAME.selectedMenuItem = MenuItem.RESTART_LEVEL;
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.DIG)) Constants.GAME.selectedMenuItem = MenuItem.END_TURN;
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.RESTART_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.DIG;
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.DO_MAGIC)) Constants.GAME.selectedMenuItem = MenuItem.EXIT_LEVEL;
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.VIEW_VISION_MAP)) Constants.GAME.selectedMenuItem = MenuItem.DO_MAGIC;
		else if(direction.equals(Direction.LEFT) && Constants.GAME.selectedMenuItem.equals(MenuItem.EXIT_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.VIEW_VISION_MAP;
		
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.END_TURN)) Constants.GAME.selectedMenuItem = MenuItem.DO_MAGIC;
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.DIG)) Constants.GAME.selectedMenuItem = MenuItem.VIEW_VISION_MAP;
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.RESTART_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.EXIT_LEVEL;
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.DO_MAGIC)) Constants.GAME.selectedMenuItem = MenuItem.END_TURN;
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.VIEW_VISION_MAP)) Constants.GAME.selectedMenuItem = MenuItem.DIG;
		else if(direction.equals(Direction.UP) && Constants.GAME.selectedMenuItem.equals(MenuItem.EXIT_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.RESTART_LEVEL;		

		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.END_TURN)) Constants.GAME.selectedMenuItem = MenuItem.DO_MAGIC;
		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.DIG)) Constants.GAME.selectedMenuItem = MenuItem.VIEW_VISION_MAP;
		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.RESTART_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.EXIT_LEVEL;
		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.DO_MAGIC)) Constants.GAME.selectedMenuItem = MenuItem.END_TURN;
		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.VIEW_VISION_MAP)) Constants.GAME.selectedMenuItem = MenuItem.DIG;
		else if(direction.equals(Direction.DOWN) && Constants.GAME.selectedMenuItem.equals(MenuItem.EXIT_LEVEL)) Constants.GAME.selectedMenuItem = MenuItem.RESTART_LEVEL;			
	}

}
