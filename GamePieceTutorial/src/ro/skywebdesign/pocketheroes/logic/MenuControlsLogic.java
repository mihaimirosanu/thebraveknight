package ro.skywebdesign.pocketheroes.logic;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.factory.GamePadFactory;
import android.content.res.Configuration;
import android.view.MotionEvent;

public class MenuControlsLogic {

	public static boolean detectUp(MotionEvent event, int orientation) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		int menu_control_size = GamePadFactory.MENU_CONTROLS_LANDSCAPE_SIZE;
		if(orientation == Configuration.ORIENTATION_PORTRAIT) {
			menu_control_size = GamePadFactory.MENU_CONTROLS_PORTRAIT_SIZE;
		}
		

    	int UP_START_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (3 * menu_control_size / 4);			
    	
    	int UP_START_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size;
    	int UP_END_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			(2 * menu_control_size / 3);
    	
    	if(x >= UP_START_X && x <= UP_END_X && y >= UP_START_Y && y <= UP_END_Y) {
    		return true;
    	}
		
		
		return false;
	}

	public static boolean detectDown(MotionEvent event, int orientation) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		int menu_control_size = GamePadFactory.MENU_CONTROLS_LANDSCAPE_SIZE;
		if(orientation == Configuration.ORIENTATION_PORTRAIT) {
			menu_control_size = GamePadFactory.MENU_CONTROLS_PORTRAIT_SIZE;
		}

    	int UP_START_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (3 * menu_control_size / 4);			
    	
    	int UP_START_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size / 3;
    	int UP_END_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING;
    	
    	if(x >= UP_START_X && x <= UP_END_X && y >= UP_START_Y && y <= UP_END_Y) {
    		return true;
    	}
		
		
		return false;
	}

	public static boolean detectLeft(MotionEvent event, int orientation) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		int menu_control_size = GamePadFactory.MENU_CONTROLS_LANDSCAPE_SIZE;
		if(orientation == Configuration.ORIENTATION_PORTRAIT) {
			menu_control_size = GamePadFactory.MENU_CONTROLS_PORTRAIT_SIZE;
		}

    	int UP_START_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size;
    	int UP_END_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + ( menu_control_size / 3);			
    	
    	int UP_START_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING -
    			menu_control_size + (3 * menu_control_size / 4);
    	
    	if(x >= UP_START_X && x <= UP_END_X && y >= UP_START_Y && y <= UP_END_Y) {
    		return true;
    	}
		
		return false;
	}

	public static boolean detectRight(MotionEvent event, int orientation) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		int menu_control_size = GamePadFactory.MENU_CONTROLS_LANDSCAPE_SIZE;
		if(orientation == Configuration.ORIENTATION_PORTRAIT) {
			menu_control_size = GamePadFactory.MENU_CONTROLS_PORTRAIT_SIZE;
		}

    	int UP_START_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + 2 * menu_control_size / 3;
    	int UP_END_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING;			
    	
    	int UP_START_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING -
    			menu_control_size + (3 * menu_control_size / 4);
    	
    	if(x >= UP_START_X && x <= UP_END_X && y >= UP_START_Y && y <= UP_END_Y) {
    		return true;
    	}
    	
		return false;
	}

	public static boolean detectAction(MotionEvent event, int orientation) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		int menu_control_size = GamePadFactory.MENU_CONTROLS_LANDSCAPE_SIZE;
		if(orientation == Configuration.ORIENTATION_PORTRAIT) {
			menu_control_size = GamePadFactory.MENU_CONTROLS_PORTRAIT_SIZE;
		}

		int UP_START_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_X = Constants.X_ORIGIN + Constants.SCREEN_WIDTH - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (3 * menu_control_size / 4);			
    	
    	int UP_START_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING - 
    			menu_control_size + (menu_control_size / 4);
    	int UP_END_Y = Constants.Y_ORIGIN + Constants.SCREEN_HEIGHT - Constants.MENU_CONTROL_PADDING -
    			menu_control_size + (3 * menu_control_size / 4);
    	
    	if(x >= UP_START_X && x <= UP_END_X && y >= UP_START_Y && y <= UP_END_Y) {
    		return true;
    	}
		
		
		return false;
	}

	
}
