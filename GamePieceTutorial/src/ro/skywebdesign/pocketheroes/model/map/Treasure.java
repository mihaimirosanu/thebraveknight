package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Collectable;
import ro.skywebdesign.pocketheroes.model.Position;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Treasure extends MapObject implements Collectable/*, VisionedMapObject*/ {

	public Treasure(MapObjectType objectType, Position position, Context context) {
		super(objectType, position, context);
	}

	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
		super.draw(canvas, position, grayscale);
		Bitmap bitmap = BitmapFactory.getTreasureBitmap(context);
		
		canvas.drawBitmap(bitmap, Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE, 
				grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
	}

	
	
}
