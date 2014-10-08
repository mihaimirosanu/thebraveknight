package ro.skywebdesign.pocketheroes.logic;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.enums.Direction;
import ro.skywebdesign.pocketheroes.exception.ExceptionUtils;
import ro.skywebdesign.pocketheroes.exception.InGameException;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.factory.CollectableFactory;
import ro.skywebdesign.pocketheroes.factory.FightFactory;
import ro.skywebdesign.pocketheroes.factory.MapFactory;
import ro.skywebdesign.pocketheroes.factory.MenuFactory;
import ro.skywebdesign.pocketheroes.factory.VisionTowerFactory;
import ro.skywebdesign.pocketheroes.model.Collectable;
import ro.skywebdesign.pocketheroes.model.Fightable;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.map.Animal;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import ro.skywebdesign.pocketheroes.model.map.MapObjectType;
import ro.skywebdesign.pocketheroes.model.map.Temple;
import ro.skywebdesign.pocketheroes.model.map.VisionTower;
import android.content.Context;

public class ControlsLogic {

	public static void handleDirection(BoardView boardView, Direction direction) throws InGameException {
		if(Constants.GAME.menuMode) {
			MenuFactory.handleMenuDirection(direction);
			return;
		}
		
		Position newPosition = getPositionForDirection(direction);
		
		//check to see if he can move up;
		if(checkCanMoveTo(newPosition, boardView)) {
			ControlsLogic.moveHero(newPosition, boardView);
			boardView.invalidate();
		} else {
			Utils.playSound(Constants.SOUND_HIT, boardView.getContext());
		}
	}


	public static void handleAction(BoardView boardView) throws MapLoadingException {
		/*
		final Dialog dialog1 = new Dialog(boardView.getContext());
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.alert);
        

        Button yes = (Button) dialog1.findViewById(R.id.button1);
        Button no = (Button) dialog1.findViewById(R.id.button2);

        yes.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) 
            {
                   
            }
        });
        no.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) 
            {
                    dialog1.dismiss();  

            }
        });
        dialog1.show();
		final BoardView view = boardView;
		*/
		
		if(Constants.GAME.fightingMode) {
			FightFactory.checkForSurvivors(boardView.getContext());
			Constants.GAME.fightStage = 0;
			Constants.GAME.fightingMode = false;
			Constants.GAME.fightingAnimal = null;
		} else  if(Constants.GAME.collectableMode) {
			Constants.GAME.collectableStage = 0;
			Constants.GAME.collectableMode = false;
			CollectableFactory.awardPrize( Constants.GAME.heroes[1], Constants.GAME.collectableType, Constants.GAME.collectableValue, boardView.getContext());
		} else  if(Constants.GAME.visionTowerMode) {
			Constants.GAME.visionTowerStage = 0;
			Constants.GAME.visionTowerMode = false;
		}
		else {
			
			/*
			if(Constants.GAME.heroes[1].getCurrentTurnMovements() > 0) {
				Utils.showOKCancelAlert("Warning", "Your hero has still more movements to do. Are you sure you want to end your turn?", 
						boardView.getContext(), new DialogInterface.OnClickListener(){
	
							@Override
							public void onClick(DialogInterface dialog, int which) {
								GameLogic.endTurn(view);
								view.invalidate();
							}
					
				});
			} else {
				GameLogic.endTurn(boardView);
				boardView.invalidate();
			}
			*/
			
			if(!Constants.GAME.menuMode) {
				Constants.GAME.menuMode = true;
			} else {
				//already in menu mode and action was pressed
				switch (Constants.GAME.selectedMenuItem) {
					case END_TURN: GameLogic.endTurn(boardView);break;
					case DO_MAGIC: break;
					case DIG: break;
					case VIEW_VISION_MAP: break;
					case RESTART_LEVEL: break;
					case EXIT_LEVEL: break;
				}
					
				Constants.GAME.menuMode = false;	
			}
			
		}
		boardView.invalidate();
	}

	private static boolean checkCanMoveTo(Position position, BoardView boardView) {
		Context context = boardView.getContext();

		if(Constants.GAME.fightingMode || Constants.GAME.collectableMode || Constants.GAME.visionTowerMode){
			//Hero is in fighting mode. Only action button can be used!
			return false;
		}
		
		//check if hero has more movements left for the current turn
		if(Constants.GAME.heroes[1].getCurrentTurnMovements() <= 0) {
			Utils.showToast("No moves left for current turn. End your turn to move again.", context);
			return false;
		}
			
		
		//check for valid position
		if(position.getCol() < 0 || position.getCol() > 9
				|| position.getRow() < 0 || position.getRow() > 9) {
			return false;
		}
		
		MapObject positionMapObject = Constants.GAME.getObjectAt(position);
		
		if(positionMapObject.getClass().equals(MapObject.class)) {
			return true;
		}
		
		//check of temple and
		if(positionMapObject.getClass().equals(Temple.class)) {
			return true;
		}
		
		//check of vision tower condition
		if(positionMapObject.getClass().equals(VisionTower.class)) {
			return true;
		}
		
		if(positionMapObject instanceof Fightable) {
			
			//check if hero has more movements left for the current turn
			if(Constants.GAME.heroes[1].getCurrentTurnActions() <= 0) {
				Utils.showToast("No attacks  left for current turn. End your turn first.", context);
				return false;
			}
			
			return true;
		}
		
		if(positionMapObject instanceof Collectable) {
			return true;
		}
		
		return false;
	}
	
	public static void moveHero(Position newPosition, BoardView boardView) throws InGameException {
		//get the object at the position before moving
		MapObject positionMapObject = Constants.GAME.getObjectAt(newPosition);
		Context context = boardView.getContext();
		
		Position oldPosition = Constants.GAME.heroes[1].getPosition();
		
		//check of temple and
		//TODO: check if map condition equals temple
		if(positionMapObject.getClass().equals(Temple.class)) {
			//set aditional game logic
			Constants.GAME.gameEnded = true;
			Constants.GAME.gameEndedLost = false;
			switchHeroTiles(oldPosition, newPosition, context);
			Constants.GAME.heroes[1].setAdditionalObject(positionMapObject);
		}
		
		//check of vision tower condition
		else if(positionMapObject.getClass().equals(VisionTower.class)) {
			VisionTower visionTower = (VisionTower)positionMapObject;
			
			if(visionTower.isVisited()) {
				Utils.showToast("Already visited...", context);
			} else {		
				//set aditional game logic
				Constants.GAME.visionTowerMode = true;
				Constants.GAME.visionTowerStage = 1;
				VisionTowerFactory.calculateNextVisions();
				visionTower.setVisited(true);
			}
			switchHeroTiles(oldPosition, newPosition, context);
			Constants.GAME.heroes[1].setAdditionalObject(positionMapObject);
		}
		
		else if(positionMapObject instanceof Fightable) {
			
			//set aditional game logic
			Constants.GAME.fightingMode = true;
			Constants.GAME.fightStage = 1;
			Constants.GAME.fightingAnimal = (Animal)positionMapObject;
			Constants.GAME.heroAttacking = true;
		}
		
		else if(positionMapObject instanceof Collectable) {
			
			//set aditional game logic
			Constants.GAME.collectableMode = true;
			Constants.GAME.collectableStage = 1;
			Constants.GAME.collectableType = CollectableFactory.getNextCollectable();
			Constants.GAME.collectableValue = CollectableFactory.getNextCollectableValue(Constants.GAME.collectableType);
			Constants.GAME.collectablePosition = positionMapObject.getPosition();
			
			switchHeroTiles(oldPosition, newPosition, context);
		} else {
			switchHeroTiles(oldPosition, newPosition, context);
		}
		
				
		
		
		
		
		
		
		
		//make a sound
		Utils.playSound(Constants.SOUND_RUN, context);
		
		

		
				
		//decrease movements for the hero
		Constants.GAME.heroes[1].decreaseCurrentTurns();
	}

	
	private static void switchHeroTiles(Position oldPosition, Position newPosition, Context context) {
		//switch!
		Constants.GAME.heroes[1].setPosition(newPosition);
		//grassToMoveTo.setPosition(oldPosition);
		Constants.GAME.mapObjects[newPosition.getRow()][newPosition.getCol()] = Constants.GAME.heroes[1];

		if(Constants.GAME.heroes[1].getAdditionalObject() != null) {
			Constants.GAME.mapObjects[oldPosition.getRow()][oldPosition.getCol()] = (MapObject)Constants.GAME.heroes[1].getAdditionalObject();
			Constants.GAME.heroes[1].setAdditionalObject(null);
		} else {
			MapObject grassToMoveTo = MapFactory.buildMapObjectByCharacter(MapObjectType.GRASS.asChar(), context, oldPosition.getRow(), oldPosition.getCol());
			Constants.GAME.mapObjects[oldPosition.getRow()][oldPosition.getCol()] = grassToMoveTo;
		}
		/*
		Hero mainHeroTile = 
			(Hero)MapFactory.buildMapObjectByCharacter(MapObjectType.MAIN_HERO.asChar(), context, newPosition.getRow(), newPosition.getCol());
		Constants.GAME.mapObjects[newPosition.getRow()][newPosition.getCol()] = mainHeroTile;
		MapObject grassTile = 
				MapFactory.buildMapObjectByCharacter(MapObjectType.GRASS.asChar(), context, oldPosition.getRow(), oldPosition.getCol());	
		Constants.GAME.mapObjects[oldPosition.getRow()][oldPosition.getCol()] = grassTile;
		Constants.GAME.heroes[1].setPosition(mainHeroTile.getPosition());
				*/
	}
	
	public static Position getPositionForDirection(Direction direction) throws InGameException {
		Position oldPosition = Constants.GAME.heroes[1].getPosition();
		Position newPosition = Position.clone(oldPosition);
		
		if(direction.equals(Direction.UP)){
			newPosition.setRow(newPosition.getRow() - 1);
		} else if(direction.equals(Direction.RIGHT)){
			newPosition.setCol(newPosition.getCol() + 1);
		} else if(direction.equals(Direction.DOWN)){
			newPosition.setRow(newPosition.getRow() + 1);
		} else if(direction.equals(Direction.LEFT)){
			newPosition.setCol(newPosition.getCol() - 1);
		} else {
			ExceptionUtils.handleException(new RuntimeException("invalid direction!"));
        	throw new InGameException();
		}
		
		return newPosition;
	}


}
