package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Drawable;
import ro.skywebdesign.pocketheroes.model.Position;
import android.content.Context;
import android.graphics.Canvas;

public class MapObject implements Drawable {
	
	protected MapObjectType type;
	protected Position position;
	protected Context context;
	
	public MapObject(MapObjectType objectType, Position position, Context context) {
		super();
		this.position = position;
		this.type = objectType;
		this.context = context;
	}

	@Override
	public void selfDraw(Canvas canvas, boolean grayscale) {
		this.draw(canvas, position, grayscale);
	}
	

	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
    	

		canvas.drawBitmap(
    			BitmapFactory.getGrassBitmap(context), 
    			Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, 
    			Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE
    			, grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
	}
	
	

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
}
