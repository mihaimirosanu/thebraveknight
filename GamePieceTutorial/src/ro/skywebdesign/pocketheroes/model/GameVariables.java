package ro.skywebdesign.pocketheroes.model;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.enums.CollectableType;
import ro.skywebdesign.pocketheroes.enums.MenuItem;
import ro.skywebdesign.pocketheroes.model.map.Animal;
import ro.skywebdesign.pocketheroes.model.map.Hero;
import ro.skywebdesign.pocketheroes.model.map.MapObject;

public class GameVariables {

	public MapObject[][] mapObjects = null;
	public Hero[] heroes = new Hero[Constants.DEFAULT_GAME_VALUES.MAX_HEROES + 1]; //starts from 1
	public Animal[] animal = new Animal[10];
	
	public int currentTurn = Constants.DEFAULT_GAME_VALUES.TOTAL_TURNS;
	public int totalTurns = Constants.DEFAULT_GAME_VALUES.TOTAL_TURNS;
	
	
	
	public int treasures = 0;
	public int treasuresTurns = 0;
	public int currentTreasuresTurn = 0;
	
	public int currentHeroToMove = 1;

	public boolean gameEnded = false;
	public boolean gameEndedLost = false;
	
	public volatile boolean fightingMode = false;
	public int fightStage = 0;//no Fighting stage
	public Animal fightingAnimal;
	public boolean heroAttacking = true;
	
	public boolean collectableMode = false;
	public int collectableStage = 0;
	public CollectableType collectableType = null;
	public int collectableValue = 0;
	public Position collectablePosition = null;
	
	public int totalVisionTowers = 0;
	public boolean visionTowerMode = false;
	public int visionTowerStage = 0;
	public int[] visionMap = new int[8]; /* 0,1,2 3x4 5,6,7 -> from top to bottom 0->no vision, 1->vision*/
	public Position visionTowerPosition = null;
	
	
	public boolean menuMode = false;
	public MenuItem selectedMenuItem = MenuItem.END_TURN;
	
	public MapObject getObjectAt(int row, int col){
		return mapObjects[row][col];
	}
	
	public MapObject getObjectAt(Position position){
		return mapObjects[position.getRow()][position.getCol()];
	}
	
	

	
	
}
