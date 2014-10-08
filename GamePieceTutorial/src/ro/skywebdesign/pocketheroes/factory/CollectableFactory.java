package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.enums.CollectableType;
import ro.skywebdesign.pocketheroes.model.map.Hero;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CollectableFactory {

	public static CollectableType getNextCollectable() {
		int random = 1 + Utils.getRandom(4);
		return CollectableType.values()[random];
	}
	
	public static int getNextCollectableValue(CollectableType collectableType) {
		//TODO: based on map treasures level!!
		
		switch (collectableType) {
		case LUCK:
			return 1 + Utils.getRandom(2);
		case ATTACK:
			return 10 + Utils.getRandom(1) * 10;
		case DEFENCE:
			return 10 + Utils.getRandom(2) * 10;
		case LIFE:
			return 10 + Utils.getRandom(5) * 10;
		case SIGHT:
			return 1 + Utils.getRandom(1);		
		case MOVEMENTS:
			return 1 + Utils.getRandom(2);	
		case ACTIONS:
			return 1 + Utils.getRandom(1);
		default:
			return 1 + Utils.getRandom(1);
		}
		
	}
	
	public static void draw(Canvas canvas, BoardView boardView) {
		Context context = boardView.getContext();

		for (int c = 2; c < (Constants.NUM_COLS_ROWS-2); c++) {
			for (int r = 2; r < (Constants.NUM_COLS_ROWS - 2); r++) {
			canvas.drawBitmap(
	    			BitmapFactory.getFightBitmap(context), 
	    			Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, 
	    			Constants.Y_ORIGIN +  r * Constants.SQUARE_SIZE
	    			, BitmapFactory.PAINT);
			}
		}
		
		
		
		//draw collectable
		Bitmap collectableBitmap = null;
		Bitmap collectableBitmapSmall = null;
		CollectableType collectableType = Constants.GAME.collectableType;
		
		if(collectableType.equals(CollectableType.ATTACK)){
			collectableBitmap = BitmapFactory.getAttackCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getAttackFightBitmap(context);
		} else if(collectableType.equals(CollectableType.DEFENCE)){
			collectableBitmap = BitmapFactory.getDefenceCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getDefenceFightBitmap(context);
		} else if(collectableType.equals(CollectableType.LUCK)){
			collectableBitmap = BitmapFactory.getLuckCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getLuckFightBitmap(context);
		} else if(collectableType.equals(CollectableType.LIFE)){
			collectableBitmap = BitmapFactory.getLifeCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getLifeFightBitmap(context);
		} else if(collectableType.equals(CollectableType.MOVEMENTS)){
			collectableBitmap = BitmapFactory.getMovementsCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getMovementsFightBitmap(context);
		} else if(collectableType.equals(CollectableType.ACTIONS)){
			collectableBitmap = BitmapFactory.getActionsCollectableBitmap(context);
			collectableBitmapSmall = BitmapFactory.getActionsFightBitmap(context);
		} 
		
		
		canvas.drawBitmap(BitmapFactory.getCollectableBitmap(context), Constants.X_ORIGIN + 3 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 3 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		canvas.drawBitmap(collectableBitmap, Constants.X_ORIGIN + 5 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 3 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		canvas.drawBitmap(collectableBitmapSmall, Constants.X_ORIGIN + 3 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
		
		
		int collectableValue = Constants.GAME.collectableValue;
		int tens_move = 0;
		int units_move = 0;
		tens_move = (collectableValue - collectableValue % 10) / 10;
		units_move = collectableValue % 10;
		
		BitmapFactory.drawPlus(Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE, canvas, context);
		
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 5 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(5.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE, canvas, context);
		
		
	    
	    if(Constants.GAME.collectableStage == 2) {
			Constants.GAME.collectableStage = 0;
			Constants.GAME.collectableMode = false;
	    }
	    
	    boardView.invalidate();

	}

	public static void awardPrize(Hero currentHero,
			CollectableType collectableType, int collectableValue,
			Context context) {
		if(collectableType.equals(CollectableType.ATTACK)){
			currentHero.increaseAttack(collectableValue);
		} else if(collectableType.equals(CollectableType.DEFENCE)){
			currentHero.increaseDefence(collectableValue);
		} else if(collectableType.equals(CollectableType.LUCK)){
			currentHero.increaseLuck(collectableValue);
		} else if(collectableType.equals(CollectableType.LIFE)){
			currentHero.increaseLife(collectableValue);
		} else if(collectableType.equals(CollectableType.MOVEMENTS)){
			currentHero.increaseMovements(collectableValue);
		} else if(collectableType.equals(CollectableType.ACTIONS)){
			currentHero.increaseActions(collectableValue);
		} 
	}
}
