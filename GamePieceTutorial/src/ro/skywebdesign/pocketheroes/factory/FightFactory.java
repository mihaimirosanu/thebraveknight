package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.model.map.Animal;
import ro.skywebdesign.pocketheroes.model.map.Hero;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import ro.skywebdesign.pocketheroes.model.map.MapObjectType;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class FightFactory {

	public static void draw(Canvas canvas, BoardView boardView) {
		Context context = boardView.getContext();

		for (int c = 1; c < (Constants.NUM_COLS_ROWS-1); c++) {
			for (int r = 1; r < (Constants.NUM_COLS_ROWS - 1); r++) {
			canvas.drawBitmap(
	    			BitmapFactory.getFightBitmap(context), 
	    			Constants.X_ORIGIN + c * Constants.SQUARE_SIZE, 
	    			Constants.Y_ORIGIN +  r * Constants.SQUARE_SIZE
	    			, BitmapFactory.PAINT);
			}
		}
		
		//draw hero life
		Bitmap lifeBitmap = BitmapFactory.getLifeFightTopBitmap(context);
		canvas.drawBitmap(lifeBitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		//draw hero actual life
		Paint paint = new Paint();
		int heroLife = Constants.GAME.heroes[1].getLife();
		int lifeRectActualWidth = (int)(1.5 * Constants.SQUARE_SIZE) * heroLife / 100; 
		Rect lifeRect = new Rect(
				Constants.X_ORIGIN + (int)(2.5 * Constants.SQUARE_SIZE), 
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.3 * Constants.SQUARE_SIZE),
				Constants.X_ORIGIN + (int)(2.5 * Constants.SQUARE_SIZE) + lifeRectActualWidth,
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.5 * Constants.SQUARE_SIZE));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(heroLife < 50 ? heroLife < 15 ? Color.RED : Color.YELLOW : Color.GREEN); 
	    canvas.drawRect(lifeRect, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRect, paint);
	    //draw life remaining
		Rect lifeRectEmpty = new Rect(
				Constants.X_ORIGIN + (int)(2.5 * Constants.SQUARE_SIZE) + lifeRectActualWidth, 
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.3 * Constants.SQUARE_SIZE),
				Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE,
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.5 * Constants.SQUARE_SIZE));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.GRAY); 
	    canvas.drawRect(lifeRectEmpty, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRectEmpty, paint);

	    //draw hero bitmap!
	    if(Constants.GAME.heroes[1].isDead()) {
    		canvas.drawBitmap(BitmapFactory.getDeadFightBitmap(context), Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    } else {
	    	Bitmap hero1Bitmap = BitmapFactory.getHero1FightBitmap(context);
	    	canvas.drawBitmap(hero1Bitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    }
	    
	    
	    //draw hero data
	    //draw attack and defence and luck
	    Bitmap attackBitmap = BitmapFactory.getAttackFightBitmap(context);
	    canvas.drawBitmap(attackBitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
	    Bitmap defenceBitmap = BitmapFactory.getDefenceFightBitmap(context);
	    canvas.drawBitmap(defenceBitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
	    Bitmap luckBitmap = BitmapFactory.getLuckFightBitmap(context);
	    canvas.drawBitmap(luckBitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
		
		int heroAttack = Constants.GAME.heroes[1].getAttack();
		int tens_move = 0;
		int units_move = 0;
		tens_move = (heroAttack - heroAttack % 10) / 10;
		units_move = heroAttack % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 3 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(3.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE, canvas, context);
		
		int heroDefence = Constants.GAME.heroes[1].getDefence();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroDefence - heroDefence % 10) / 10;
		units_move = heroDefence % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 3 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(3.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 6* Constants.SQUARE_SIZE, canvas, context);

		int heroLuck = Constants.GAME.heroes[1].getLuck();
		tens_move = 0;
		units_move = 0;
		tens_move = (heroLuck - heroLuck % 10) / 10;
		units_move = heroLuck % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 3 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(3.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE, canvas, context);	    
		
		

		//draw oponent life
		canvas.drawBitmap(lifeBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		
		//draw oponent actual life
		int oponentLife = Constants.GAME.fightingAnimal.getLife();
		lifeRectActualWidth = (int)(1.5 * Constants.SQUARE_SIZE) * oponentLife / 100; 
		lifeRect = new Rect(
				Constants.X_ORIGIN + (int)(6.5 * Constants.SQUARE_SIZE), 
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.3 * Constants.SQUARE_SIZE),
				Constants.X_ORIGIN + (int)(6.5 * Constants.SQUARE_SIZE) + lifeRectActualWidth,
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.5 * Constants.SQUARE_SIZE));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(oponentLife < 50 ? oponentLife < 15 ? Color.RED : Color.YELLOW : Color.GREEN); 
	    canvas.drawRect(lifeRect, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRect, paint);
	    //draw life remaining
		lifeRectEmpty = new Rect(
				Constants.X_ORIGIN + (int)(6.5 * Constants.SQUARE_SIZE) + lifeRectActualWidth, 
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.3 * Constants.SQUARE_SIZE),
				Constants.X_ORIGIN + 8 * Constants.SQUARE_SIZE,
				Constants.Y_ORIGIN + Constants.SQUARE_SIZE + (int)(0.5 * Constants.SQUARE_SIZE));
	    paint.setStyle(Paint.Style.FILL);
	    paint.setColor(Color.GRAY); 
	    canvas.drawRect(lifeRectEmpty, paint);
	    paint.setStyle(Paint.Style.STROKE);
	    paint.setColor(Color.BLACK);
	    canvas.drawRect(lifeRectEmpty, paint);

	    //draw oponent bitmap!
	    if(Constants.GAME.fightingAnimal.isDead()) {
	    		canvas.drawBitmap(BitmapFactory.getDeadFightBitmap(context), Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    } else {
	    		Bitmap oponentBitmap = Constants.GAME.fightingAnimal.getFightingBitmap(context);
	    		canvas.drawBitmap(oponentBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    }
	    		
	    //draw hero data
	    //draw attack and defence and luck
	    canvas.drawBitmap(attackBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
	    canvas.drawBitmap(defenceBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
	    canvas.drawBitmap(luckBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE , BitmapFactory.PAINT);
		
		int oponentAttack = Constants.GAME.fightingAnimal.getAttack();
		tens_move = 0;
		units_move = 0;
		tens_move = (oponentAttack - oponentAttack % 10) / 10;
		units_move = oponentAttack % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 7 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(7.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 5 * Constants.SQUARE_SIZE, canvas, context);
		
		int oponentDefence = Constants.GAME.fightingAnimal.getDefence();
		tens_move = 0;
		units_move = 0;
		tens_move = (oponentDefence - oponentDefence % 10) / 10;
		units_move = oponentDefence % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 7 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 6 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(7.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 6* Constants.SQUARE_SIZE, canvas, context);

		int oponentLuck = Constants.GAME.fightingAnimal.getLuck();
		tens_move = 0;
		units_move = 0;
		tens_move = (oponentLuck - oponentLuck % 10) / 10;
		units_move = oponentLuck % 10;
		BitmapFactory.drawNumber(tens_move, Constants.X_ORIGIN + 7 * Constants.SQUARE_SIZE , Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE, canvas, context);
		BitmapFactory.drawNumber(units_move, Constants.X_ORIGIN + (int)(7.5 * Constants.SQUARE_SIZE) , Constants.Y_ORIGIN + 7 * Constants.SQUARE_SIZE, canvas, context);	    
		
	
		
		
		
		//next, the versus icon!
		if(Constants.GAME.heroAttacking) {
			Bitmap fightBitmap = Constants.GAME.fightStage % 2 == 1? BitmapFactory.getFightLeftFightBitmap(context) : BitmapFactory.getFightingLeftFightBitmap(context);
			canvas.drawBitmap(fightBitmap, Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		} else {
			Bitmap fightBitmap = Constants.GAME.fightStage % 2 == 1? BitmapFactory.getFightRightFightBitmap(context) : BitmapFactory.getFightingRightFightBitmap(context);
			canvas.drawBitmap(fightBitmap, Constants.X_ORIGIN + 4 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
		}
	    
	    if(Constants.GAME.fightStage == 2) {
	    	int outcome = fight(true, Constants.GAME.fightingAnimal, Constants.GAME.heroAttacking); 
	    	
	    	Bitmap lostBitmap = BitmapFactory.getLostFightBitmap(context);
			if((outcome < 0 && Constants.GAME.heroAttacking) || (outcome > 0 && !Constants.GAME.heroAttacking)) {
	    		canvas.drawBitmap(lostBitmap, Constants.X_ORIGIN + 2 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    	} else if((outcome > 0 && Constants.GAME.heroAttacking) || (outcome < 0 && !Constants.GAME.heroAttacking)) {
	    		canvas.drawBitmap(lostBitmap, Constants.X_ORIGIN + 6 * Constants.SQUARE_SIZE, Constants.Y_ORIGIN + 2 * Constants.SQUARE_SIZE, BitmapFactory.PAINT);
	    	}
	    	
	    }
	    
	    
	    if(Constants.GAME.fightStage == 3 && !Constants.GAME.heroAttacking) {
	    	//this is another hero attacking and this is the last stage! End fighting! No need for user to be pressing the action button!
			FightFactory.checkForSurvivors(context);
			Constants.GAME.fightStage = 0;
			Constants.GAME.fightingMode = false;
			Constants.GAME.fightingAnimal = null;
	    }
	    
	    boardView.invalidate();

	}

	/**
	 * 
	 * 
	 * @param dropLifes: if sent false. The system just checks who will win! Will not drop the lifes!
	 * @return: > 0 && heroAttacking => hero won! will return the amount of life taken for the animal (also drops animal life if dropLifes = true)
	 * 			< 0 && heroAttacking => hero lost! will return the amount of life taken for the hero (also drop hero Life if dropLifes = true)
	 * 
	 * 		    >0 && !heroAttacking => hero lost! will return the amount of life taken for the hero (also drop hero Life if dropLifes = true)
	 * 			<0 && !heroAttacking => hero won!  will return the amount of life taken for the animal (also drop hero Life if dropLifes = true)
	 */
	public static int fight(boolean dropLifes, Animal fightingAnimal, boolean heroAttacking) {
		
		int heroAttack = Constants.GAME.heroes[1].getAttack();
		int heroDefence = Constants.GAME.heroes[1].getDefence();
		int heroLuck = Constants.GAME.heroes[1].getLuck();
		
		int opponentAttack = fightingAnimal.getAttack();
		int opponentDefence = fightingAnimal.getDefence();
		int opponentLuck = fightingAnimal.getLuck();
		
		float heroLuckAmount = 1 + (float)((float)heroLuck / 10);
		if(!Constants.GAME.heroes[1].isFullLuck()){
			heroLuckAmount = 1 + (float)((float)Utils.getRandom(heroLuck) / 10);
		}
		
		float opponentLuckAmount = 1 + (float)((float)opponentLuck / 10);
		if(!fightingAnimal.isFullLuck()){
			opponentLuckAmount = 1 + (float)((float)Utils.getRandom(opponentLuck) / 10);
		}
		
		
		if(heroAttacking) {
			
			
			int lifeToTake = (int)(heroAttack * 10 * heroLuckAmount - opponentDefence * 10 * 0.8 * opponentLuckAmount);		
			
			if(dropLifes) {
				Constants.GAME.heroes[1].decreaseActions();
				
				if(lifeToTake < 0){
					Constants.GAME.heroes[1].dropLife(-lifeToTake);
				} else {
					fightingAnimal.dropLife(lifeToTake);
				}
			}
			return lifeToTake;
		} else {
			int lifeToTake = (int)(opponentAttack * 10 * opponentLuckAmount - heroDefence * 10 * 0.8 * heroLuckAmount);		

			if(dropLifes) {
				((Hero)fightingAnimal).decreaseActions(); /*only a hero may attack! */
				
				if(lifeToTake > 0){
					Constants.GAME.heroes[1].dropLife(lifeToTake);
				} else {
					fightingAnimal.dropLife(-lifeToTake);
				}
			}
			return lifeToTake;
		}
		
		
		
	}

	

	public static void checkForSurvivors(Context context) {
		for(int i = 6; i <= 9; i++) {
			if(Constants.GAME.animal[i] != null && Constants.GAME.animal[i].isDead()) {
				MapObject grassObject = new MapObject(MapObjectType.GRASS, Constants.GAME.animal[i].getPosition(), context);
				Constants.GAME.mapObjects[Constants.GAME.animal[i].getPosition().getRow()][Constants.GAME.animal[i].getPosition().getCol()] = grassObject;
				Constants.GAME.animal[i] = null;
			}
		}
		for(int i = 1; i <= 5; i++) {
			if(Constants.GAME.heroes[i] != null && Constants.GAME.heroes[i].isDead()) {
				MapObject grassObject = new MapObject(MapObjectType.GRASS, Constants.GAME.heroes[i].getPosition(), context);
				Constants.GAME.mapObjects[Constants.GAME.heroes[i].getPosition().getRow()][Constants.GAME.heroes[i].getPosition().getCol()] = grassObject;
				Constants.GAME.heroes[i] = null;
				if(i == 1){
					Constants.GAME.gameEnded = true;
					Constants.GAME.gameEndedLost = true;
				}
			}
		}
	}
	
}
