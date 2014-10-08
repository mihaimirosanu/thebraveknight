package ro.skywebdesign.pocketheroes.model;

import android.graphics.Canvas;

public interface Drawable {

	public void draw(final Canvas canvas, Position position, boolean grayscale);
	public void selfDraw(final Canvas canvas, boolean grayscale);
}

