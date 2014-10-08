package ro.skywebdesign.pocketheroes;

import android.media.SoundPool;
import ro.skywebdesign.pocketheroes.model.GameVariables;

public class Constants {

	public static final boolean LOG_INFO = true;
	
    public static final int NUM_COLS_ROWS = 10;
    
    //current running game. If null, then call build map!!
    public static GameVariables GAME = null;
    
    
    //screen variables
    public static int SCREEN_WIDTH = 0;
    public static int SCREEN_HEIGHT = 0;
    public static int SQUARE_SIZE = 0;
    public static int BORDER_WALL_WIDTH = 0;
    public static int X_ORIGIN = 0;
    public static int Y_ORIGIN = 0;
    public static int MENU_CONTROL_PADDING = 0;
    
    //map txt options
    public static final String MAP_OPTION_TOTAL_TURNS = "totalTurns";
	public static final String MAP_OPTION_TREASURES = "treasures";
    
   
    
    
    
    
    //sounds
    public static SoundPool SOUND_POOL = null;
    public static boolean SOUNDS_LOADED = false;
    
    public static int SOUND_RUN = 0;
    public static int SOUND_HIT = 0;
    
    
    //default hero starting values
    public static final class DEFAULT_HERO_VALUES {
		public static final int MOVEMENTS_PER_TURN = 5;
		public static final int LIFE = 100;
		public static final int ATTACK = 10;
		public static final int DEFENCE = 10;
		public static final int ACTIONS_PER_TURN = 1;
		public static final int LUCK = 5;
    }
    
    public static final class DEFAULT_COMPUTER_HERO_2_VALUES {
		public static final int MOVEMENTS_PER_TURN = 3;
		public static final int LIFE = 100;
		public static final int ATTACK = 10;
		public static final int DEFENCE = 10;
		public static final int ACTIONS_PER_TURN = 1;
		public static final int LUCK = 5;
    }
    
    public static final class DEFAULT_ANIMAL_VALUES {
		public static final int LIFE = 100;
		public static final int ATTACK = 20;
		public static final int DEFENCE = 10;
		public static final int LUCK = 5;
    }
    
    public static final class DEFAULT_GAME_VALUES {
    	public static final int MAX_HEROES = 5;
    	public static final int TOTAL_TURNS = 30;
    }


}
