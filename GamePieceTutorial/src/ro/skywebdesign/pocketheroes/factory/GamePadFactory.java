package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.Constants;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;

import com.example.gamepiecetutorial.R;

public class GamePadFactory {

	public static BitmapDrawable MENU_PORTRAIT_BACKGROUND = null;
	public static Bitmap MENU_CONTROLS_PORTRAIT_BITMAP = null;
	public static Bitmap MENU_CONTROLS_DISABLED_ALL_PORTRAIT_BITMAP = null;
	public static Bitmap MENU_CONTROLS_DISABLED_MOVES_PORTRAIT_BITMAP = null;
	public static int MENU_CONTROLS_PORTRAIT_SIZE;
	
	public static BitmapDrawable MENU_LANDSCAPE_BACKGROUND = null;
	public static Bitmap MENU_CONTROLS_LANDSCAPE_BITMAP = null;
	public static Bitmap MENU_CONTROLS_DISABLED_ALL_LANDSCAPE_BITMAP = null;
	public static Bitmap MENU_CONTROLS_DISABLED_MOVES_LANDSCAPE_BITMAP = null;	
	public static int MENU_CONTROLS_LANDSCAPE_SIZE;
	
	
	public static void drawGamePadPortrait(Context context, Canvas canvas) {
		//draw rect
		if(MENU_PORTRAIT_BACKGROUND == null){
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_tile);
			
			MENU_PORTRAIT_BACKGROUND = new BitmapDrawable(context.getResources(), b);
			MENU_PORTRAIT_BACKGROUND.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	        
	        Rect boundsRect = new Rect(
	                Constants.X_ORIGIN,               								// left
	                Constants.Y_ORIGIN + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.BORDER_WALL_WIDTH,               	// top
	                Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH,  									// right
	                Constants.SCREEN_HEIGHT + Constants.BORDER_WALL_WIDTH   								// bottom
	            );
	        
	        MENU_PORTRAIT_BACKGROUND.setBounds(boundsRect);

		}
		MENU_PORTRAIT_BACKGROUND.draw(canvas);
		
		//calculate menu controls size
		
		//calculate min between half screen width and menu height
		MENU_CONTROLS_PORTRAIT_SIZE = Math.min(
				(Constants.SCREEN_WIDTH) / 2 ,
				Constants.SCREEN_HEIGHT - (Constants.Y_ORIGIN + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.BORDER_WALL_WIDTH)
				);
		
		if(Constants.GAME.visionTowerMode || Constants.GAME.collectableMode || Constants.GAME.fightingMode || Constants.GAME.currentHeroToMove != 1 
			|| (Constants.GAME.currentHeroToMove == 1 && (Constants.GAME.heroes[1] != null && Constants.GAME.heroes[1].getCurrentTurnMovements() == 0)) ){
			if((Constants.GAME.currentHeroToMove == 1 && Constants.GAME.heroes[1].getCurrentTurnMovements() == 0) || Constants.GAME.visionTowerStage == 1 || Constants.GAME.collectableStage == 1 || (Constants.GAME.fightStage == 3 && Constants.GAME.heroAttacking)) {
				if(MENU_CONTROLS_DISABLED_MOVES_PORTRAIT_BITMAP == null) {
					Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls_disabled_moves);
					MENU_CONTROLS_DISABLED_MOVES_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_PORTRAIT_SIZE, MENU_CONTROLS_PORTRAIT_SIZE, false);
				}
				canvas.drawBitmap(MENU_CONTROLS_DISABLED_MOVES_PORTRAIT_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
			} else {
				if(MENU_CONTROLS_DISABLED_ALL_PORTRAIT_BITMAP == null) {
					Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls_disabled_all);
					MENU_CONTROLS_DISABLED_ALL_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_PORTRAIT_SIZE, MENU_CONTROLS_PORTRAIT_SIZE, false);
				}
				canvas.drawBitmap(MENU_CONTROLS_DISABLED_ALL_PORTRAIT_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
			}
		} else {
			if(MENU_CONTROLS_PORTRAIT_BITMAP == null) {
				Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls);
				MENU_CONTROLS_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_PORTRAIT_SIZE, MENU_CONTROLS_PORTRAIT_SIZE, false);
			}
			canvas.drawBitmap(MENU_CONTROLS_PORTRAIT_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
		}
	}
	
	
	public static void drawGamePadLandscape(Context context, Canvas canvas) {
		//draw rect
		if(MENU_LANDSCAPE_BACKGROUND == null){
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_tile);
			
			MENU_LANDSCAPE_BACKGROUND = new BitmapDrawable(context.getResources(), b);
			MENU_LANDSCAPE_BACKGROUND.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
	        
	        Rect boundsRect = new Rect(
	                Constants.X_ORIGIN + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.BORDER_WALL_WIDTH,               								// left
	                Constants.Y_ORIGIN,               	// top
	                Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH,  									// right
	                Constants.SCREEN_HEIGHT + Constants.BORDER_WALL_WIDTH   								// bottom
	            );
	        
	        MENU_LANDSCAPE_BACKGROUND.setBounds(boundsRect);

		}
		MENU_LANDSCAPE_BACKGROUND.draw(canvas);

		//calculate menu controls size
		
		//calculate min between half screen width and menu height
		MENU_CONTROLS_LANDSCAPE_SIZE = Math.min(
				(Constants.SCREEN_HEIGHT) / 2 ,
				Constants.SCREEN_WIDTH - (Constants.X_ORIGIN + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.BORDER_WALL_WIDTH)
				);
		
		if(Constants.GAME.visionTowerMode || Constants.GAME.collectableMode || Constants.GAME.fightingMode || Constants.GAME.currentHeroToMove != 1 
				|| (Constants.GAME.currentHeroToMove == 1 && (Constants.GAME.heroes[1] != null && Constants.GAME.heroes[1].getCurrentTurnMovements() == 0)) ){
			if((Constants.GAME.currentHeroToMove == 1 && Constants.GAME.heroes[1].getCurrentTurnMovements() == 0) || Constants.GAME.visionTowerStage == 1 || Constants.GAME.collectableStage == 1 || (Constants.GAME.fightStage == 3 && Constants.GAME.heroAttacking)) {
				if(MENU_CONTROLS_DISABLED_MOVES_LANDSCAPE_BITMAP == null) {
					Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls_disabled_moves);
					MENU_CONTROLS_DISABLED_MOVES_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_LANDSCAPE_SIZE, MENU_CONTROLS_LANDSCAPE_SIZE, false);
				}
				canvas.drawBitmap(MENU_CONTROLS_DISABLED_MOVES_LANDSCAPE_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT  + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
			} else {
				if(MENU_CONTROLS_DISABLED_ALL_LANDSCAPE_BITMAP == null) {
					Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls_disabled_all);
					MENU_CONTROLS_DISABLED_ALL_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_LANDSCAPE_SIZE, MENU_CONTROLS_LANDSCAPE_SIZE, false);
				}
				canvas.drawBitmap(MENU_CONTROLS_DISABLED_ALL_LANDSCAPE_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT  + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
			}
		} else {
			if(MENU_CONTROLS_LANDSCAPE_BITMAP == null) {
				Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.controls);
				MENU_CONTROLS_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(b, MENU_CONTROLS_LANDSCAPE_SIZE, MENU_CONTROLS_LANDSCAPE_SIZE, false);
			}
			canvas.drawBitmap(MENU_CONTROLS_LANDSCAPE_BITMAP, Constants.SCREEN_WIDTH + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, Constants.SCREEN_HEIGHT  + Constants.BORDER_WALL_WIDTH - MENU_CONTROLS_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
		}

	}


	
	public static Bitmap NUMBERS_BITMAP = null;
	
	public static int GAME_INFO_ICON_PORTRAIT_SIZE;
	public static int NUMBER_INFO_ICON_PORTRAIT_WIDTH;
	public static int LIFE_RECT_PORTRAIT_WIDTH;
	
	public static Bitmap MOVEMENT_PORTRAIT_BITMAP = null;
	public static Bitmap TURNS_PORTRAIT_BITMAP = null;
	public static Bitmap LIFE_PORTRAIT_BITMAP = null;
	public static Bitmap ATTACK_PORTRAIT_BITMAP = null;
	public static Bitmap DEFENCE_PORTRAIT_BITMAP = null;
	public static Bitmap LUCK_PORTRAIT_BITMAP = null;
	public static Bitmap ATTACKS_PORTRAIT_BITMAP = null;
	
	
	public static void drawGameInfoPortrait(Context context, Canvas canvas) {
		
		//if the hero is dead, don't draw anything!!
		if(Constants.GAME.gameEnded){
			return;
		}
		
		if(NUMBERS_BITMAP == null){
			NUMBERS_BITMAP =android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_numbers);
		}
		
		//draw curent movement
		if(MOVEMENT_PORTRAIT_BITMAP == null) {
			Bitmap gM = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_movements);
			Bitmap gT = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_turn);
			Bitmap gL = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_life);
			Bitmap gA = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_attack);
			Bitmap gD = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_defence);
			Bitmap gLU = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_luck);
			Bitmap gAS = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_actions);
		
			//calculate size
		
			//calculate min between half screen width and menu height
			MENU_CONTROLS_PORTRAIT_SIZE = Math.min(
	            (Constants.SCREEN_WIDTH) / 2 ,
	            Constants.SCREEN_HEIGHT - (Constants.Y_ORIGIN + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.BORDER_WALL_WIDTH)
	        );
			int movement_turns_line_width = Constants.SCREEN_WIDTH - MENU_CONTROLS_PORTRAIT_SIZE -  2 * (Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING);
			GAME_INFO_ICON_PORTRAIT_SIZE = (int) ((int) movement_turns_line_width / 2 * 0.4); 
			NUMBER_INFO_ICON_PORTRAIT_WIDTH = (int) ((int) (movement_turns_line_width / 2 * 0.6) - Constants.MENU_CONTROL_PADDING) / 2;
			LIFE_RECT_PORTRAIT_WIDTH = movement_turns_line_width - GAME_INFO_ICON_PORTRAIT_SIZE - Constants.MENU_CONTROL_PADDING;
			
			MOVEMENT_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gM, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			TURNS_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gT, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			LIFE_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gL, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			
			ATTACK_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gA, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			DEFENCE_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gD, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			LUCK_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gLU, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
			ATTACKS_PORTRAIT_BITMAP = Bitmap.createScaledBitmap(gAS, GAME_INFO_ICON_PORTRAIT_SIZE, GAME_INFO_ICON_PORTRAIT_SIZE, false);
		}
		canvas.drawBitmap(MOVEMENT_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , BitmapFactory.PAINT);
		canvas.drawBitmap(TURNS_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , BitmapFactory.PAINT);

		int heroMovements = Constants.GAME.heroes[1].getCurrentTurnMovements();
		int tens_move = 0;
		int units_move = 0;
		tens_move = (heroMovements - heroMovements % 10) / 10;
		units_move = heroMovements % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , canvas);
		
		//draw number for current turn
		int currentTurn = Constants.GAME.currentTurn;
		tens_move = (currentTurn - currentTurn % 10) / 10;
		units_move = currentTurn % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING , canvas);
		
		//draw life
		canvas.drawBitmap(LIFE_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
		
		//draw actual life
		Paint paint = new Paint();
		int heroLife = Constants.GAME.heroes[1].getLife();
		int lifeRectActualWidth = LIFE_RECT_PORTRAIT_WIDTH * heroLife / 100; 
		Rect lifeRect = new Rect(
			Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING, 
			(int)(2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE * 0.3),
			Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + lifeRectActualWidth,
			(int)(2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE * 0.7));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(heroLife < 50 ? heroLife < 15 ? Color.RED : Color.YELLOW : Color.GREEN); 
	    canvas.drawRect(lifeRect, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRect, paint);
	    //draw life remaining
		Rect lifeRectEmpty = new Rect(
				Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + lifeRectActualWidth, 
				(int)(2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE * 0.3),
				Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + LIFE_RECT_PORTRAIT_WIDTH,
				(int)(2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE * 0.7));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.GRAY); 
	    canvas.drawRect(lifeRectEmpty, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRectEmpty, paint);
	    
	    
	    //draw attack and defence
	    canvas.drawBitmap(ATTACK_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , BitmapFactory.PAINT);
		canvas.drawBitmap(DEFENCE_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , BitmapFactory.PAINT);

		int heroAttack = Constants.GAME.heroes[1].getAttack();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroAttack - heroAttack % 10) / 10;
		units_move = heroAttack % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		
		int heroDefence = Constants.GAME.heroes[1].getDefence();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroDefence - heroDefence % 10) / 10;
		units_move = heroDefence % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING  + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING  + 2 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		
		
	    //draw luck and attacks
	    canvas.drawBitmap(LUCK_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , BitmapFactory.PAINT);
		canvas.drawBitmap(ATTACKS_PORTRAIT_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , BitmapFactory.PAINT);

		int heroLuck = Constants.GAME.heroes[1].getLuck();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroLuck - heroLuck % 10) / 10;
		units_move = heroLuck % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING) , canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		
		int heroAttacks = Constants.GAME.heroes[1].getCurrentTurnActions();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroAttacks - heroAttacks % 10) / 10;
		units_move = heroAttacks % 10;
		drawNumberPortrait(tens_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING  + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		drawNumberPortrait(units_move, NUMBER_INFO_ICON_PORTRAIT_WIDTH, Constants.BORDER_WALL_WIDTH + 2 * Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_PORTRAIT_SIZE + NUMBER_INFO_ICON_PORTRAIT_WIDTH + GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_PORTRAIT_WIDTH + Constants.MENU_CONTROL_PADDING, 2 * Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING  + 3 * (GAME_INFO_ICON_PORTRAIT_SIZE + Constants.MENU_CONTROL_PADDING), canvas);
		
	}

	
	public static int GAME_INFO_ICON_LANDSCAPE_SIZE;
	public static int NUMBER_INFO_ICON_LANDSCAPE_WIDTH;
	public static int LIFE_RECT_LANDSCAPE_WIDTH;
	
	public static Bitmap MOVEMENT_LANDSCAPE_BITMAP = null;
	public static Bitmap TURNS_LANDSCAPE_BITMAP = null;
	public static Bitmap LIFE_LANDSCAPE_BITMAP = null;
	public static Bitmap ATTACK_LANDSCAPE_BITMAP = null;
	public static Bitmap DEFENCE_LANDSCAPE_BITMAP = null;
	public static Bitmap LUCK_LANDSCAPE_BITMAP = null;
	public static Bitmap ATTACKS_LANDSCAPE_BITMAP = null;	
	
	public static void drawGameInfoLandscape(Context context, Canvas canvas) {
		//if the hero is dead, don't draw anything!!
		if(Constants.GAME.gameEnded){
			return;
		}
		
		if(NUMBERS_BITMAP == null){
			NUMBERS_BITMAP =android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_numbers);
		}
		
		//draw curent movement
		if(MOVEMENT_LANDSCAPE_BITMAP == null) {
			Bitmap gM = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_movements);
			Bitmap gT = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_turn);
			Bitmap gL = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_life);
			Bitmap gA = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_attack);
			Bitmap gD = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_defence);
			Bitmap gLU = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_luck);
			Bitmap gAS = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_actions);			
		
			//calculate size
			int movement_turns_line_width = (Constants.SCREEN_WIDTH  -  3 * Constants.BORDER_WALL_WIDTH - 2 * Constants.MENU_CONTROL_PADDING - Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE) / 2;
			GAME_INFO_ICON_LANDSCAPE_SIZE = (int) ((int) movement_turns_line_width / 2 * 0.4); 
			NUMBER_INFO_ICON_LANDSCAPE_WIDTH = (int) ((int) (movement_turns_line_width / 2 * 0.6) - Constants.MENU_CONTROL_PADDING) / 2;
			LIFE_RECT_LANDSCAPE_WIDTH = movement_turns_line_width - GAME_INFO_ICON_LANDSCAPE_SIZE - Constants.MENU_CONTROL_PADDING;
			
			MOVEMENT_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gM, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			TURNS_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gT, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			LIFE_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gL, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			ATTACK_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gA, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			DEFENCE_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gD, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			ATTACKS_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gAS, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			LUCK_LANDSCAPE_BITMAP = Bitmap.createScaledBitmap(gLU, GAME_INFO_ICON_LANDSCAPE_SIZE, GAME_INFO_ICON_LANDSCAPE_SIZE, false);
			
		}
		canvas.drawBitmap(MOVEMENT_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , BitmapFactory.PAINT);
		canvas.drawBitmap(TURNS_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING  , BitmapFactory.PAINT);

		//draw number for hero movements
		int heroMovements = Constants.GAME.heroes[1].getCurrentTurnMovements();
		int tens_move = 0;
		int units_move = 0;
		tens_move = (heroMovements - heroMovements % 10) / 10;
		units_move = heroMovements % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		
		//draw number for current turn
		int currentTurn = Constants.GAME.currentTurn;
		tens_move = (currentTurn - currentTurn % 10) / 10;
		units_move = currentTurn % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		
		
		//draw life
		canvas.drawBitmap(LIFE_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING , BitmapFactory.PAINT);
		
		//draw actual life
		Paint paint = new Paint();
		int heroLife = Constants.GAME.heroes[1].getLife();
		int lifeRectActualWidth = LIFE_RECT_LANDSCAPE_WIDTH * heroLife / 100; 
		Rect lifeRect = new Rect(
			(int)(Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING), 
			(int)(Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE * 0.3),
			(int)(Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + lifeRectActualWidth),
			(int)(Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE * 0.7));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(heroLife < 50 ? heroLife < 15 ? Color.RED : Color.YELLOW : Color.GREEN); 
	    canvas.drawRect(lifeRect, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRect, paint);
	    //draw life remaining
		Rect lifeRectEmpty = new Rect(
				(int)(Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + lifeRectActualWidth), 
				(int)(Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE * 0.3),
				(int)(Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + LIFE_RECT_LANDSCAPE_WIDTH),
				(int)(Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE * 0.7));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.GRAY); 
	    canvas.drawRect(lifeRectEmpty, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRectEmpty, paint);
	    
	    
	    //draw attack and defence
	    canvas.drawBitmap(ATTACK_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , BitmapFactory.PAINT);
		canvas.drawBitmap(DEFENCE_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * Constants.MENU_CONTROL_PADDING  + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING  , BitmapFactory.PAINT);

		int heroAttack = Constants.GAME.heroes[1].getAttack() / 10;
		tens_move = 0;
		units_move = 0;
		tens_move = (heroAttack - heroAttack % 10) / 10;
		units_move = heroAttack % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING  + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		
		int heroDefence = Constants.GAME.heroes[1].getDefence() / 10;
		tens_move = 0;
		units_move = 0;
		tens_move = (heroDefence - heroDefence % 10) / 10;
		units_move = heroDefence % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING , canvas);
		
	    //draw luck and attacks
	    canvas.drawBitmap(LUCK_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING  + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);
		canvas.drawBitmap(ATTACKS_LANDSCAPE_BITMAP, Constants.BORDER_WALL_WIDTH + Constants.SQUARE_SIZE * Constants.NUM_COLS_ROWS + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * Constants.MENU_CONTROL_PADDING  + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING   + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, BitmapFactory.PAINT);

		int heroLuck = Constants.GAME.heroes[1].getLuck();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroLuck - heroLuck % 10) / 10;
		units_move = heroLuck % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING  + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING , canvas);
		
		int heroAttacks = Constants.GAME.heroes[1].getCurrentTurnActions();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroAttacks - heroAttacks % 10) / 10;
		units_move = heroAttacks % 10;
		drawNumberLandscape(tens_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, canvas);
		drawNumberLandscape(units_move, NUMBER_INFO_ICON_LANDSCAPE_WIDTH, Constants.BORDER_WALL_WIDTH + Constants.NUM_COLS_ROWS * Constants.SQUARE_SIZE + Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + 2 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING + NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 2 * GAME_INFO_ICON_LANDSCAPE_SIZE + 4 * NUMBER_INFO_ICON_LANDSCAPE_WIDTH + 4 * Constants.MENU_CONTROL_PADDING, Constants.BORDER_WALL_WIDTH + Constants.MENU_CONTROL_PADDING + GAME_INFO_ICON_LANDSCAPE_SIZE + Constants.MENU_CONTROL_PADDING, canvas);
		
	}

	
	public static SparseArray<Bitmap> PORTRAIT_NUMBER_MAP = new SparseArray<Bitmap>();
	public static SparseArray<Bitmap> LANSCAPE_NUMBER_MAP = new SparseArray<Bitmap>();
	private static void drawNumberPortrait(int number,
			int bitmapWidth, int x, int y, Canvas canvas) {
		
		if(PORTRAIT_NUMBER_MAP.get(number) == null) {
			Bitmap NUMBER_BITMAP = getNumberFromNumbersBitmap(number);
			PORTRAIT_NUMBER_MAP.put(number, Bitmap.createScaledBitmap(NUMBER_BITMAP, bitmapWidth, GAME_INFO_ICON_PORTRAIT_SIZE, false));
		}
		
		canvas.drawBitmap(PORTRAIT_NUMBER_MAP.get(number), x, y , BitmapFactory.PAINT);
		
	}
	private static void drawNumberLandscape(int number,
			int bitmapWidth, int x, int y, Canvas canvas) {
		
		if(LANSCAPE_NUMBER_MAP.get(number) == null) {
			Bitmap NUMBER_BITMAP = getNumberFromNumbersBitmap(number);
			LANSCAPE_NUMBER_MAP.put(number, Bitmap.createScaledBitmap(NUMBER_BITMAP, bitmapWidth, GAME_INFO_ICON_LANDSCAPE_SIZE, false));
		}
		
		canvas.drawBitmap(LANSCAPE_NUMBER_MAP.get(number), x, y , BitmapFactory.PAINT);
		
	}	


	private static Bitmap getNumberFromNumbersBitmap(int number) {
		//TODO: optimize!
		try {
			return Bitmap.createBitmap(NUMBERS_BITMAP, number * (NUMBERS_BITMAP.getWidth() / 10), 0, NUMBERS_BITMAP.getWidth() / 10, NUMBERS_BITMAP.getHeight());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}



}
