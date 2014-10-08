package ro.skywebdesign.pocketheroes.model.map;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.BitmapFactory;
import ro.skywebdesign.pocketheroes.model.Obstacle;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.VisionedMapObject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MapObstacle extends MapObject implements Obstacle, VisionedMapObject {

	public MapObstacle(MapObjectType type, Position position, Context context) {
		super(type, position, context);
	}

	@Override
	public void draw(Canvas canvas, Position position, boolean grayscale) {
		super.draw(canvas, position, grayscale);
		
		Bitmap bitmap = null;
		if(type.equals(MapObjectType.MOUNTAIN)) {
			bitmap = BitmapFactory.getMountainBitmap(context);
		} else if(type.equals(MapObjectType.PALM)) {
			bitmap = BitmapFactory.getPalmBitmap(context);
		} else if(type.equals(MapObjectType.TREE)) {
			bitmap = BitmapFactory.getTreeBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_A)) {
			bitmap = BitmapFactory.getRiverABitmap(context);
		} else if(type.equals(MapObjectType.RIVER_B)) {
			bitmap = BitmapFactory.getRiverBBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_C)) {
			bitmap = BitmapFactory.getRiverCBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_D)) {
			bitmap = BitmapFactory.getRiverDBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_E)) {
			bitmap = BitmapFactory.getRiverEBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_F)) {
			bitmap = BitmapFactory.getRiverFBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_G)) {
			bitmap = BitmapFactory.getRiverGBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_H)) {
			bitmap = BitmapFactory.getRiverHBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_I)) {
			bitmap = BitmapFactory.getRiverIBitmap(context);
		} else if(type.equals(MapObjectType.RIVER_J)) {
			bitmap = BitmapFactory.getRiverJBitmap(context);
		} else {
			throw new RuntimeException("What to draw?? - Obstacle");
		}
		canvas.drawBitmap(bitmap, Constants.X_ORIGIN + position.getCol() * Constants.SQUARE_SIZE, Constants.Y_ORIGIN +  position.getRow() * Constants.SQUARE_SIZE, 
				grayscale?BitmapFactory.PAINT_GRAYSCALE:BitmapFactory.PAINT);
		
		
	}
	
}
