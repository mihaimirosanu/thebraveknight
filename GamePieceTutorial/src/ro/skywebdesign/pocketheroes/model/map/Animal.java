package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Fightable;
import ro.skywebdesign.pocketheroes.model.Position;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Animal extends MapObject implements Fightable {

	protected int life;
	protected int attack;
	protected int defence;
	protected int luck;
	
	protected boolean fullLuck; //controlled by a spell!!
	
	
	public Animal(MapObjectType type, Position position, Context context) {
		super(type, position, context);
		life = Constants.DEFAULT_HERO_VALUES.LIFE;
		attack = Constants.DEFAULT_HERO_VALUES.ATTACK;
		defence = Constants.DEFAULT_HERO_VALUES.DEFENCE;
		luck = Constants.DEFAULT_HERO_VALUES.LUCK;
	}




	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
		super.draw(canvas, position, grayscale);
		Bitmap bitmap = null;
		if(type.equals(MapObjectType.MONSTER_6)) {
			bitmap = BitmapFactory.getMonster6Bitmap(context);
		} else {
			//maybe this a hero.. nothing to draw for now! The hero has alrea
			return;
		}
		//TODO: add other monster types!

		canvas.drawBitmap(bitmap, Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE, 
				grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
		
		
	}

	public Bitmap getFightingBitmap(Context context) {
		if(type.equals(MapObjectType.MONSTER_6)) {
			return BitmapFactory.getMonster6FightBitmap(context);
		}
		
		throw new RuntimeException("Cannot find the bitmap! Animal class!");
	}



	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	public void dropLife(int lifeToDrop) {
		this.life = this.life - lifeToDrop;
		if(this.life < 0) {
			this.life = 0;
		}
	}




	public boolean isDead() {
		return life <= 0;
	}




	public boolean isFullLuck() {
		return fullLuck;
	}




	public void setFullLuck(boolean fullLuck) {
		this.fullLuck = fullLuck;
	}
	
	
}
