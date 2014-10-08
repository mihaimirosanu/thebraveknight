package ro.skywebdesign.pocketheroes;

import ro.skywebdesign.pocketheroes.enums.Direction;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.factory.CollectableFactory;
import ro.skywebdesign.pocketheroes.factory.FightFactory;
import ro.skywebdesign.pocketheroes.factory.GamePadFactory;
import ro.skywebdesign.pocketheroes.factory.MapFactory;
import ro.skywebdesign.pocketheroes.factory.MenuFactory;
import ro.skywebdesign.pocketheroes.factory.VisionTowerFactory;
import ro.skywebdesign.pocketheroes.logic.ControlsLogic;
import ro.skywebdesign.pocketheroes.logic.GameLogic;
import ro.skywebdesign.pocketheroes.logic.MenuControlsLogic;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.example.gamepiecetutorial.R;

public class BoardView extends View {
 
    private final Context context;

    

	public BoardView(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        
        init();
        
        try {
        	if(Constants.GAME == null) {
        		Constants.GAME = MapFactory.buildMap(context);
        	}
		} catch (MapLoadingException e) {
			Utils.showAlert("Error", "Error occured while loading map", context);
		}
    }
 
    private void init() {
    	WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	Display display = wm.getDefaultDisplay();
    	
    	
    	Point size = new Point();
    	display.getSize(size);
    	
    	Constants.BORDER_WALL_WIDTH = Math.min(size.x, size.y) / 50;
    	
    	Constants.SCREEN_WIDTH = size.x - Constants.BORDER_WALL_WIDTH * 2;
    	Constants.SCREEN_HEIGHT = size.y - Constants.BORDER_WALL_WIDTH * 2;
 
    	
    	Constants.X_ORIGIN = Constants.BORDER_WALL_WIDTH;
    	Constants.Y_ORIGIN = Constants.BORDER_WALL_WIDTH;
    	Constants.MENU_CONTROL_PADDING = Constants.BORDER_WALL_WIDTH;
    	
        Constants.SQUARE_SIZE = Math.min(
        	Constants.SCREEN_WIDTH / Constants.NUM_COLS_ROWS,
        	Constants.SCREEN_HEIGHT / Constants.NUM_COLS_ROWS
        );
	}
 
    @Override
    protected void onDraw(final Canvas canvas) {
         
    	try {
    		if(Constants.GAME.menuMode) {
    			MenuFactory.draw(canvas,this);
    		} else if(Constants.GAME.fightingMode) {
				//draw the fighting mode!
				FightFactory.draw(canvas, this);
			} else if(Constants.GAME.collectableMode) {
				//draw the fighting mode!
				CollectableFactory.draw(canvas, this);
			} else if(Constants.GAME.visionTowerMode) {
				//draw the fighting mode!
				VisionTowerFactory.draw(canvas, this);
			} else {    		
				//just draw the map
				
				for (int c = 0; c < Constants.NUM_COLS_ROWS; c++) {
					for (int r = 0; r < Constants.NUM_COLS_ROWS; r++) {
						MapObject currentMapObject = Constants.GAME.mapObjects[r][c];
						
						currentMapObject.selfDraw(canvas, false);
					}
				}
			}
			
			
			int orientation = getResources().getConfiguration().orientation;
			
			if(orientation == Configuration.ORIENTATION_PORTRAIT) {
				GamePadFactory.drawGamePadPortrait(context, canvas);
				GamePadFactory.drawGameInfoPortrait(context, canvas);
				
			} else {
				//orientation landscape
			    GamePadFactory.drawGamePadLandscape(context, canvas);
			    GamePadFactory.drawGameInfoLandscape(context, canvas);
			}
			
			
			//do fighting logic!
			if(Constants.GAME.fightingMode){
				if(Constants.GAME.fightStage > 1) {
			    	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(Constants.GAME.fightStage < 3) {
					Constants.GAME.fightStage++;
					this.invalidate();
				}
				
				
			}
			
			//check game condition!
			if(Constants.GAME.gameEnded) {
				
				
				String message = "You won!";
				if(Constants.GAME.gameEndedLost){
					message = "You lost!";	
				}
				
				
				
		    	AlertDialog alertDialog = new AlertDialog.Builder(this.getContext()).create();
		    	alertDialog.setTitle(message);
		    	alertDialog.setMessage(message);
		    	alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int which) {
		    		dialog.dismiss();
					Intent intent = ((MainActivity)getContext()).getIntent();
					((MainActivity)getContext()).finish();
		        	Constants.GAME = null;
		        	((MainActivity)getContext()).startActivity(intent);
		    	}
		    	});
		    	alertDialog.setIcon(R.drawable.ic_launcher);
		    	alertDialog.show();

			}
			
			if(!Constants.GAME.gameEnded && Constants.GAME.currentHeroToMove > 1 && !Constants.GAME.fightingMode){
				GameLogic.doHeroAI(this);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Utils.showAlert("Error!", e.getMessage(), context);
			e.printStackTrace();
		}
    }
 
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
    	int orientation = getResources().getConfiguration().orientation;
    	
    	try {
    	    /*cannot do anything while other heroes move!*/
    		if(Constants.GAME.currentHeroToMove == 1 ) {
    		
		    	if(MenuControlsLogic.detectUp(event, orientation)){
		    		ControlsLogic.handleDirection(this, Direction.UP);
		    	} else if(MenuControlsLogic.detectDown(event, orientation)){
		    		ControlsLogic.handleDirection(this, Direction.DOWN);
		    	} else if(MenuControlsLogic.detectLeft(event, orientation)){
		    		ControlsLogic.handleDirection(this, Direction.LEFT);
		    	} else if(MenuControlsLogic.detectRight(event, orientation)){
		    		ControlsLogic.handleDirection(this, Direction.RIGHT);
		    	} else if(MenuControlsLogic.detectAction(event, orientation)){
		    		ControlsLogic.handleAction(this);
		    	}
    		}
    	} catch (Exception e) {
			Utils.showFatalError(context);
		}
    	
    	
    	
		return super.onTouchEvent(event);
	}
    
}
