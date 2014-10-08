package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.VisionedMapObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class VisionTower extends MapObject implements VisionedMapObject {
	
	private boolean visited;
	
	public VisionTower(MapObjectType type, Position position, Context context) {
		super(type, position, context);
	}

	
	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
		super.draw(canvas, position, grayscale);
		Bitmap bitmap = BitmapFactory.getVisionTowerBitmap(context);
		
		canvas.drawBitmap(bitmap, Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE, 
				grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
		
	}
	
	public boolean isVisited() {
		return visited;
	}


	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	
}
