package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Drawable;
import ro.skywebdesign.pocketheroes.model.Position;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Hero extends Animal {

	protected boolean mainHero = false; 
	protected int currentTurnMovements;
	protected int movements;
	protected int currentTurnActions;
	protected int actions;
	

	protected Drawable additionalObject = null;
	
	//for computer heroes only
	private int runningDirection = 0;
	private int lastRunningDirection = 0;
	

	public Hero(MapObjectType type, Position position, Context context) {
		super(type, position, context);
		
		if(type.equals(MapObjectType.MAIN_HERO)) {
			this.life = Constants.DEFAULT_HERO_VALUES.LIFE;
			this.attack = Constants.DEFAULT_HERO_VALUES.ATTACK;
			this.defence = Constants.DEFAULT_HERO_VALUES.DEFENCE;
			this.luck = Constants.DEFAULT_HERO_VALUES.LUCK;
			this.currentTurnMovements = Constants.DEFAULT_HERO_VALUES.MOVEMENTS_PER_TURN;
			this.movements = Constants.DEFAULT_HERO_VALUES.MOVEMENTS_PER_TURN;
			this.actions = Constants.DEFAULT_HERO_VALUES.ACTIONS_PER_TURN;
			this.currentTurnActions = Constants.DEFAULT_HERO_VALUES.ACTIONS_PER_TURN;
		} else if(type.equals(MapObjectType.COMPUTER_HERO_2)) {
			this.life = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.LIFE;
			this.attack = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.ATTACK;
			this.defence = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.DEFENCE;
			this.luck = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.LUCK;
			this.currentTurnMovements = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.MOVEMENTS_PER_TURN;
			this.movements = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.MOVEMENTS_PER_TURN;
			this.actions = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.ACTIONS_PER_TURN;
			this.currentTurnActions = Constants.DEFAULT_COMPUTER_HERO_2_VALUES.ACTIONS_PER_TURN;
		}
	}


	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
		super.draw(canvas, position, grayscale);
		
		//check for additional tiles on hero
		if(additionalObject != null) {
			additionalObject.draw(canvas, position, grayscale);			
		}
		
		Bitmap bitmap = null;
		if(type.equals(MapObjectType.MAIN_HERO)){
			bitmap = BitmapFactory.getHero1Bitmap(context);
		} else if(type.equals(MapObjectType.COMPUTER_HERO_2)) {
			bitmap = BitmapFactory.getHero2Bitmap(context);
		}

		canvas.drawBitmap(bitmap, Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE, 
				grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
	}





	public boolean isMainHero() {
		return mainHero;
	}

	public void setMainHero(boolean mainHero) {
		this.mainHero = mainHero;
	}

	public int getCurrentTurnMovements() {
		return currentTurnMovements;
	}

	public void setCurrentTurnMovements(int currentTurnMovements) {
		this.currentTurnMovements = currentTurnMovements;
	}

	public int getMovements() {
		return movements;
	}

	public void setMovements(int movements) {
		this.movements = movements;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) { 
		this.actions = actions;
	}

	public Drawable getAdditionalObject() {
		return additionalObject;
	}

	public void setAdditionalObject(Drawable additionalObject) {
		this.additionalObject = additionalObject;
	}

	public void decreaseCurrentTurns() {
		this.currentTurnMovements--;
	}

	public void resetMovements() {
		this.currentTurnMovements = this.movements;
	}


	public void decreaseActions() {
		this.currentTurnActions--;
	}


	public int getCurrentTurnActions() {
		return currentTurnActions;
	}


	public void setCurrentTurnActions(int currentTurnActions) {
		this.currentTurnActions = currentTurnActions;
	}


	public void resetActions() {
		this.currentTurnActions = actions;
	}


	@Override
	public Bitmap getFightingBitmap(Context context) {
			if(type.equals(MapObjectType.COMPUTER_HERO_2)) {
				return BitmapFactory.getHero2FightBitmap(context);
			}
			
			throw new RuntimeException("Cannot find the bitmap! Hero class!");
	}


	public int getRunningDirection() {
		return runningDirection;
	}


	public void setRunningDirection(int runningDirection) {
		this.runningDirection = runningDirection;
	}


	public int getLastRunningDirection() {
		return lastRunningDirection;
	}


	public void setLastRunningDirection(int lastRunningDirection) {
		this.lastRunningDirection = lastRunningDirection;
	}


	public void increaseAttack(int value) {
		this.attack += value;
	}
	
	public void increaseDefence(int value) {
		this.defence += value;
	}
	
	public void increaseLuck(int value) {
		this.luck += value;
	}
	
	public void increaseLife(int value) {
		this.life += value;
		if(life > Constants.DEFAULT_HERO_VALUES.LIFE) {
			this.life = Constants.DEFAULT_HERO_VALUES.LIFE;
		}
	}
	
	public void increaseActions(int value) {
		this.actions += value;
	}
	
	public void increaseMovements(int value) {
		this.movements += value;
	}
	
	
	



	
	
	
}
