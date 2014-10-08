package ro.skywebdesign.pocketheroes.logic;

import ro.skywebdesign.pocketheroes.BoardView;
import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.enums.CollectableType;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.factory.CollectableFactory;
import ro.skywebdesign.pocketheroes.factory.FightFactory;
import ro.skywebdesign.pocketheroes.factory.MapFactory;
import ro.skywebdesign.pocketheroes.model.Collectable;
import ro.skywebdesign.pocketheroes.model.GameVariables;
import ro.skywebdesign.pocketheroes.model.Obstacle;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.map.Animal;
import ro.skywebdesign.pocketheroes.model.map.Hero;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import ro.skywebdesign.pocketheroes.model.map.MapObjectType;
import ro.skywebdesign.pocketheroes.model.map.Treasure;
import ro.skywebdesign.pocketheroes.model.map.VisionTower;
import android.content.Context;

public class GameLogic {

	
	public static void addMainHero(MapObject hero, GameVariables gameVariables){
		if(gameVariables.heroes[1] == null){
			gameVariables.heroes[1] = (Hero)hero;
		}
		gameVariables.heroes[1].setPosition(hero.getPosition());
	}

	public static void endTurn(BoardView boardView) throws MapLoadingException {
		//Utils.showSpinner(boardView.getContext(),"Ending turn ...");
		
		final Context context = boardView.getContext();
		
		//add more treasures (if the case)
		if(Constants.GAME.currentTreasuresTurn == Constants.GAME.treasuresTurns) {
			Constants.GAME.currentTreasuresTurn = 0;
			MapFactory.addTreasures(Constants.GAME, context);
		}
		
		Constants.GAME.currentHeroToMove++;
		boardView.invalidate();
		
		
		/*
		new Thread(new Runnable() {
            @Override
            public void run() {
                try {

            		//increase the current turn
            		Constants.GAME.currentTurn++;
            		
            		//reset hero movements
            		for (int i = 0; i < Constants.GAME.heroes.length; i++) {
            			if(Constants.GAME.heroes[i] != null){
            				Constants.GAME.heroes[i].resetMovements();
            				Constants.GAME.heroes[i].resetAttacks();
            			}
            		}
            		try {
            			Thread.sleep(1000);
            		} catch (InterruptedException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                } catch (Exception e) {
                	e.printStackTrace();
                	Utils.showAlert("Error!", e.getMessage(), ctx);
                }
                Utils.hideSpinner();
            }
        }).start();
*/
		
		
	}

	public static void addMonster6(MapObject animal, GameVariables gameVariables) {
		if(gameVariables.animal[6] == null) {
			gameVariables.animal[6] = (Animal)animal;
		}
		gameVariables.animal[6].setPosition(animal.getPosition());
	}

	public static void addComputerHero2(MapObject hero,
			GameVariables gameVariables) {
		if(gameVariables.heroes[2] == null){
			gameVariables.heroes[2] = (Hero)hero;
		}
		gameVariables.heroes[2].setPosition(hero.getPosition());
	}

	public static void doHeroAI(BoardView boardView) {
		Hero currentHero = Constants.GAME.heroes[Constants.GAME.currentHeroToMove];
		Context context = boardView.getContext();
		
		if(currentHero != null) {
			
			if(currentHero.getCurrentTurnMovements() > 0) {
				//check surroundings for obstacles!
				
				int currentHeroRow = currentHero.getPosition().getRow();
				int currentHeroCol = currentHero.getPosition().getCol();
				int upScore = checkAIScore(currentHero, currentHeroRow - 1, currentHeroCol);
				int downScore = checkAIScore(currentHero, currentHeroRow + 1, currentHeroCol);
				int leftScore = checkAIScore(currentHero, currentHeroRow , currentHeroCol - 1);
				int rightScore = checkAIScore(currentHero, currentHeroRow, currentHeroCol + 1);
				
				if(upScore == 0 && downScore == 0 && leftScore == 0 && rightScore == 0) {
					currentHero.setCurrentTurnMovements(0);
					return; //stuck!
				}
				
				
				//check to see if any of directions return -1! If this is the case, then remember to try to run in the same direction
				//until the end of the turn!
				if(currentHero.getRunningDirection() > 0 || upScore == -1 || downScore == -1 || leftScore == -1 || rightScore == -1) {
					
					int runningDirection = 3; //1-up 2-right 3-down 4-left
					
					if(currentHero.getRunningDirection() > 0){
						//restore the running direction!!
						runningDirection = currentHero.getRunningDirection();
					} else {
						if(downScore == -1) runningDirection = 1;
						else if(leftScore == -1) runningDirection = 2;
						else if(rightScore == -1) runningDirection = 4;
					}
					
					currentHero.setRunningDirection(runningDirection);
					
					//check to see if the hero can move into the oposite direction, if not, he should at least strafe the other two directions
					if(runningDirection == 1){
						if(upScore > 0) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else if(leftScore > 0 && currentHero.getLastRunningDirection() == 4) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else if(rightScore > 0 && currentHero.getLastRunningDirection() == 2) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context );
						} else if(leftScore > 0) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else if(rightScore > 0) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context );							
						} else {
							//that's it... engage and hope for the best!!
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						}
					} else if(runningDirection == 2) {
						if(rightScore > 0) {
							moveComputerHero(currentHero, currentHeroRow , currentHeroCol + 1, context );
						} else if(downScore > 0 && currentHero.getLastRunningDirection() == 3) {
							moveComputerHero(currentHero, currentHeroRow +1 , currentHeroCol, context );
						} else if(upScore > 0 && currentHero.getLastRunningDirection() == 1) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else if(downScore > 0) {
							moveComputerHero(currentHero, currentHeroRow +1 , currentHeroCol, context );
						} else if(upScore > 0) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );							
						} else {
							//that's it... engage and hope for the best!!
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						}
					} else if(runningDirection == 3) {
						if(downScore > 0) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else if(leftScore > 0 && currentHero.getLastRunningDirection() == 4) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else if(rightScore > 0 && currentHero.getLastRunningDirection() == 2) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context );
						} else if(leftScore > 0) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else if(rightScore > 0) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context );							
						} else {
							//that's it... engage and hope for the best!!
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						}
					} else {
						//running direction = 4
						if(leftScore > 0) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else if(upScore > 0 && currentHero.getLastRunningDirection() == 1) {
							moveComputerHero(currentHero, currentHeroRow -1 , currentHeroCol, context );
						} else if(downScore > 0 && currentHero.getLastRunningDirection() == 3) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else if(upScore > 0) {
							moveComputerHero(currentHero, currentHeroRow -1 , currentHeroCol, context );
						} else if(downScore > 0) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else {
							//that's it... engage and hope for the best!!
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context );
						}
					}
					
				} else {
				
					
					if(upScore > downScore && upScore > leftScore && upScore > rightScore) {
						moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
					} else 	if(downScore > upScore && downScore > leftScore && downScore > rightScore) {
						moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context);
					} else 	if(leftScore > downScore && leftScore > upScore && leftScore > rightScore) {
						moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context);
					} else if(rightScore > downScore && rightScore > upScore && rightScore > leftScore){
						moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
					} else if(upScore == downScore && upScore > leftScore && upScore > rightScore) {
						//decide between up and down
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context);
						}
					} else if(upScore == leftScore && upScore > downScore && upScore > rightScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context);
						}
					} else if(upScore == rightScore && upScore > downScore && upScore > leftScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
						}
					} else if(downScore == leftScore && downScore > upScore && downScore > rightScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context);
						}
					} else if(downScore == rightScore && downScore > upScore && downScore > leftScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
						}
					} else if(leftScore == rightScore && leftScore > upScore && leftScore > downScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
						} else {
							moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
						}
					} else if(downScore == rightScore && downScore == leftScore && downScore > upScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow + 1, currentHeroCol, context );
						} else {
							if(Utils.getRandomBoolean()) {
								moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
							} else {
								moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
							}
						}
					} else if(upScore == rightScore && upScore == leftScore && upScore > downScore) {
						if(Utils.getRandomBoolean()) {
							moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
						} else {
							if(Utils.getRandomBoolean()) {
								moveComputerHero(currentHero, currentHeroRow, currentHeroCol - 1, context );
							} else {
								moveComputerHero(currentHero, currentHeroRow, currentHeroCol + 1, context);
							}
						}
					} else {
						//just move up by default
						moveComputerHero(currentHero, currentHeroRow - 1, currentHeroCol, context );
					}
				}
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Constants.GAME.currentHeroToMove++;
				
			}
		}  else {
			Constants.GAME.currentHeroToMove++;
		}
		
		if(Constants.GAME.currentHeroToMove > Constants.DEFAULT_GAME_VALUES.MAX_HEROES) {
			//END TURN!!
			//increase the current turn
    		Constants.GAME.currentTurn--;
    		Constants.GAME.currentTreasuresTurn++;
    		
    		if(Constants.GAME.currentTurn <= 0) {
    			Constants.GAME.gameEnded = true;
    			Constants.GAME.gameEndedLost = true;
    		}
    		
    		Constants.GAME.currentHeroToMove = 1;
    		
    		//reset hero movements
    		for (int i = 0; i < Constants.GAME.heroes.length; i++) {
    			if(Constants.GAME.heroes[i] != null){
    				Constants.GAME.heroes[i].resetMovements();
    				Constants.GAME.heroes[i].resetActions();
    				Constants.GAME.heroes[i].setRunningDirection(0);
    			}
    		}
		}
		boardView.invalidate();
	}

	private static void moveComputerHero(Hero currentHero, int row, int col, Context context) {
		try {
			MapObject mapObject = Constants.GAME.mapObjects[row][col];
			if(mapObject.getClass().equals(MapObject.class) || mapObject.getClass().equals(Treasure.class)) {
				
				if(mapObject.getClass().equals(Treasure.class)) {
					CollectableType collectableType = CollectableFactory.getNextCollectable();
					int collectableValue = CollectableFactory.getNextCollectableValue(collectableType);
					CollectableFactory.awardPrize(currentHero, collectableType, collectableValue, context);
				}
				
				Position oldPosition = currentHero.getPosition();

				//update the last hero direction!
				int oldDirection = 0;
				if(oldPosition.getRow() > row && oldPosition.getCol() == col) {
					oldDirection = 3;
				} else if(oldPosition.getRow() < row && oldPosition.getCol() == col) {
					oldDirection = 1;
				} else if(oldPosition.getRow() == row && oldPosition.getCol() > col){
					oldDirection = 4;
				} else if(oldPosition.getRow() == row && oldPosition.getCol() < col) {
					oldDirection = 2;
				}
				currentHero.setLastRunningDirection(oldDirection);
				
				MapObject grassToMoveTo = MapFactory.buildMapObjectByCharacter(MapObjectType.GRASS.asChar(), context, oldPosition.getRow(), oldPosition.getCol());
				currentHero.setPosition(new Position(row, col));
				Constants.GAME.mapObjects[currentHero.getPosition().getRow()][currentHero.getPosition().getCol()] = currentHero;
				Constants.GAME.mapObjects[grassToMoveTo.getPosition().getRow()][grassToMoveTo.getPosition().getCol()] = grassToMoveTo;
				
				//make a sound
				Utils.playSound(Constants.SOUND_RUN, context);
				
				//decrease movements for the hero
				currentHero.decreaseCurrentTurns();
				
			} else if(mapObject.equals(Constants.GAME.heroes[1])) {
				//attack!!
				Constants.GAME.fightingMode = true;
				Constants.GAME.fightStage = 1;
				Constants.GAME.fightingAnimal = (Animal)currentHero;
				Constants.GAME.heroAttacking = false;
			} else {
				//Nothing to do... just end turn!
				currentHero.decreaseCurrentTurns();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int checkAIScore(Hero currentHero,
			int row, int col) {
		try {
			if(Constants.GAME.mapObjects[row][col].equals(Constants.GAME.heroes[1])) {
				//so the main hero is near by! check! to see if it can attack!
				if(FightFactory.fight(false, currentHero, false) > 0 && currentHero.getCurrentTurnActions() > 0) {
					//this hero will win the fight! Attack!
					return 3;
				} else {
					//Run forest!!
					return -1;
				}
			}
			
			if(Constants.GAME.mapObjects[row][col] instanceof Obstacle) {
				return 0;
			}
			
			if(Constants.GAME.mapObjects[row][col] instanceof Collectable) {
				return 2;
			}
			
			if(Constants.GAME.mapObjects[row][col] instanceof VisionTower) {
				//nothing interesting for the hero
				return 0;
			}
			
			return 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			/* The hero is at the edge of the map maybe!*/
			return 0;
		}
	}

	
}
