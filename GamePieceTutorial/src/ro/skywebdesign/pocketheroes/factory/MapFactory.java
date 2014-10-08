package ro.skywebdesign.pocketheroes.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ro.skywebdesign.pocketheroes.Constants;
import ro.skywebdesign.pocketheroes.Utils;
import ro.skywebdesign.pocketheroes.exception.ExceptionUtils;
import ro.skywebdesign.pocketheroes.exception.MapLoadingException;
import ro.skywebdesign.pocketheroes.logic.GameLogic;
import ro.skywebdesign.pocketheroes.model.GameVariables;
import ro.skywebdesign.pocketheroes.model.Position;
import ro.skywebdesign.pocketheroes.model.map.Animal;
import ro.skywebdesign.pocketheroes.model.map.Hero;
import ro.skywebdesign.pocketheroes.model.map.MapObject;
import ro.skywebdesign.pocketheroes.model.map.MapObjectType;
import ro.skywebdesign.pocketheroes.model.map.MapObstacle;
import ro.skywebdesign.pocketheroes.model.map.Temple;
import ro.skywebdesign.pocketheroes.model.map.Treasure;
import ro.skywebdesign.pocketheroes.model.map.VisionTower;
import android.content.Context;

public class MapFactory {

	public static GameVariables buildMap(Context context) throws MapLoadingException {
		GameVariables gameMap = new GameVariables();
		boolean mainHeroPositionFound = false;
		
		InputStream in = MapFactory.class
				.getResourceAsStream("/resources/maps/map.txt");		
		
		MapObject[][] mapObjects = new MapObject[Constants.NUM_COLS_ROWS][Constants.NUM_COLS_ROWS];
		List<String> mapOptions = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    int row = 0;
		try {
	        String line = br.readLine();

	        while (line != null) {
		        if(row < 10){
		        	//parsing map and not game options!
		        	if(line.length() != Constants.NUM_COLS_ROWS) {
			        	ExceptionUtils.handleException(new RuntimeException("map contains" + line.length() + " columns!"));
			        	throw new MapLoadingException();
			        }
		        	
		        	for (int col = 0; col < Constants.NUM_COLS_ROWS; col++) {
		                 MapObject currentObject = buildMapObjectByCharacter(line.charAt(col), context, row, col);
		                 mapObjects[row][col] = currentObject;
		                if(MapObjectType.MAIN_HERO.asChar() == line.charAt(col)) {
		                	mainHeroPositionFound = true;
		                	GameLogic.addMainHero(currentObject, gameMap);
		                }
		                
		                else if(MapObjectType.MONSTER_6.asChar() == line.charAt(col)) {
		                	GameLogic.addMonster6(currentObject, gameMap);
		                }
		                
		                else if(MapObjectType.COMPUTER_HERO_2.asChar() == line.charAt(col)) {
		                	GameLogic.addComputerHero2(currentObject, gameMap);
		                }
		                
		                else if(MapObjectType.VISION_TOWER.asChar() == line.charAt(col)) {
		                	gameMap.totalVisionTowers ++;
		                	
		                	if(gameMap.totalVisionTowers > 8) {
		                		ExceptionUtils.handleException(new RuntimeException("map contains to much vision powers!"));
		                		throw new MapLoadingException();
		                	}
		                }
		                
		            }
		        }
	        	
	            line = br.readLine();
	            row++;
	            
	            if(row >= Constants.NUM_COLS_ROWS && line != null) {
	            	mapOptions.add(line);
	            }
	        }
	    } catch (IOException e) {
			ExceptionUtils.handleException(e);
	    	throw new MapLoadingException();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				ExceptionUtils.handleException(e);
		    	throw new MapLoadingException();
			}
	    }
		
		if(row < Constants.NUM_COLS_ROWS) {
			ExceptionUtils.handleException(new RuntimeException("map contains only " + row + " rows!"));
        	throw new MapLoadingException();
		}
		gameMap.mapObjects = mapObjects;
		
		//the main hero position was not found on the map
		//that means that the start is random! generate tile!
		if(!mainHeroPositionFound){
			Position position = Utils.getRandomPosition(mapObjects);
			MapObject mainHero = 
					buildMapObjectByCharacter(MapObjectType.MAIN_HERO.asChar(), context, position.getRow(), position.getCol());
			mapObjects[position.getRow()][position.getCol()] = mainHero;
			GameLogic.addMainHero(mainHero, gameMap);
		}
	
		
		for (Iterator<String> iterator = mapOptions.iterator(); iterator.hasNext();) {
			String option = (String) iterator.next();
			String[] splittedOption = option.split("=");

			if(Constants.MAP_OPTION_TOTAL_TURNS.equals(splittedOption[0])){
				gameMap.totalTurns = Integer.parseInt(splittedOption[1]);
				gameMap.currentTurn = Integer.parseInt(splittedOption[1]);
			} else if(Constants.MAP_OPTION_TREASURES.equals(splittedOption[0])){
				String[] treasuresValues = splittedOption[1].split(",");
				gameMap.treasures = Integer.parseInt(treasuresValues[0]);
				if(treasuresValues.length > 1){
					gameMap.treasuresTurns = Integer.parseInt(treasuresValues[1]);
				}
			}
			
		}

		//if there is at least one vision tower, decide the artifact position!
		if(gameMap.totalVisionTowers > 0){
			gameMap.visionTowerPosition = VisionTowerFactory.generateArtifactPosition(mapObjects);
		}
		
		addTreasures(gameMap, context);
		
		return gameMap;
		
	}
	
	public static void addTreasures(GameVariables gameMap,Context context) throws MapLoadingException {
		for(int i = 0; i < gameMap.treasures; i++ ){
			Position position = Utils.getRandomPosition(gameMap.mapObjects);
			MapObject treasure = 
					buildMapObjectByCharacter(MapObjectType.TREASURE.asChar(), context, position.getRow(), position.getCol());
			gameMap.mapObjects[position.getRow()][position.getCol()] = treasure;
		}
	}

	public static MapObject buildMapObjectByCharacter(char charAt, Context context, int row, int col) {
		Position position = new Position(row, col);
		
		if(MapObjectType.TREE.asChar() == charAt) {
			return new MapObstacle(MapObjectType.TREE, position, context);
		} else if(MapObjectType.MOUNTAIN.asChar() == charAt) {
			return new MapObstacle(MapObjectType.MOUNTAIN, position, context);			
		} else if(MapObjectType.PALM.asChar() == charAt) {
			return new MapObstacle(MapObjectType.PALM, position, context);
		} else if(MapObjectType.RIVER_A.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_A, position, context);			
		} else if(MapObjectType.RIVER_B.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_B, position, context);
		} else if(MapObjectType.RIVER_C.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_C, position, context);
		} else if(MapObjectType.RIVER_D.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_D, position, context);
		} else if(MapObjectType.RIVER_E.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_E, position, context);
		} else if(MapObjectType.RIVER_F.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_F, position, context);
		} else if(MapObjectType.RIVER_G.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_G, position, context);
		} else if(MapObjectType.RIVER_H.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_H, position, context);
		} else if(MapObjectType.RIVER_I.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_I, position, context);
		} else if(MapObjectType.RIVER_J.asChar() == charAt) {
			return new MapObstacle(MapObjectType.RIVER_J, position, context);

		} else if(MapObjectType.MONSTER_6.asChar() == charAt) {
			return new Animal(MapObjectType.MONSTER_6, position, context);
			
		} else if(MapObjectType.MAIN_HERO.asChar() == charAt) {
			return new Hero(MapObjectType.MAIN_HERO, position, context);
			
		} else if(MapObjectType.COMPUTER_HERO_2.asChar() == charAt) {
			return new Hero(MapObjectType.COMPUTER_HERO_2, position, context);			
		
		} else if(MapObjectType.TEMPLE.asChar() == charAt) {
			return new Temple(MapObjectType.TEMPLE, position, context);			
		
		} else if(MapObjectType.TREASURE.asChar() == charAt) {
			return new Treasure(MapObjectType.TREASURE, position, context);
		} else if(MapObjectType.VISION_TOWER.asChar() == charAt) {
			return new VisionTower(MapObjectType.VISION_TOWER, position, context);
						
		} else if(MapObjectType.GRASS.asChar() == charAt) {
			return new MapObject(MapObjectType.GRASS, position, context);
		}
		
		Utils.showFatalError(context);
		return null;
		
	}

}
