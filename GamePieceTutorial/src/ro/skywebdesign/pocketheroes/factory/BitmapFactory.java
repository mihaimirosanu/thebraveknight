package ro.skywebdesign.pocketheroes.factory;

import ro.skywebdesign.pocketheroes.Constants;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.SparseArray;

import com.example.gamepiecetutorial.R;


public class BitmapFactory {
	public  static final Paint PAINT = new Paint();

	
    public static final Paint PAINT_GRAYSCALE = new Paint();
    static {
    	ColorMatrix cm = new ColorMatrix();
    	cm.setSaturation(0);
    	PAINT_GRAYSCALE.setColorFilter(new ColorMatrixColorFilter(cm));
    }
	

    /** MENU ITEMS **/
	private static Bitmap MENU_END_TURN_BITMAP = null;
	public static Bitmap getMenuEndTurnBitmap(Context context){
		if(MENU_END_TURN_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_turn);
			MENU_END_TURN_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MENU_END_TURN_BITMAP;
	}
	
	private static Bitmap MENU_RESTART_BITMAP = null;
	public static Bitmap getMenuRestartBitmap(Context context){
		if(MENU_RESTART_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_restart);
			MENU_RESTART_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MENU_RESTART_BITMAP;
	}	
	private static Bitmap MENU_DIG_BITMAP = null;
	public static Bitmap getMenuDigBitmap(Context context){
		if(MENU_DIG_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.shovel);
			MENU_DIG_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MENU_DIG_BITMAP;
	}
	
	private static Bitmap MENU_VISION_BITMAP = null;
	public static Bitmap getMenuVisionBitmap(Context context){
		if(MENU_VISION_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_tower);
			MENU_VISION_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MENU_VISION_BITMAP;
	}	
	
	
	private static Bitmap MENU_SELECT_BITMAP = null;
	public static Bitmap getMenuSelectBitmap(Context context){
		if(MENU_SELECT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.selection);
			MENU_SELECT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MENU_SELECT_BITMAP;
	}
	
    
    /** MENU ITEMS **/
	
	
	private static Bitmap GRASS_BITMAP = null;
	public static Bitmap getGrassBitmap(Context context){
		if(GRASS_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_grass);
			GRASS_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return GRASS_BITMAP;
	}
	
	private static Bitmap DARK_BITMAP = null;
	public static Bitmap getDarkBitmap(Context context){
		if(DARK_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_dark);
			DARK_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return DARK_BITMAP;
	}
	
	private static Bitmap BRICK_BITMAP = null;
	public static Bitmap getBrickBitmap(Context context){
		if(BRICK_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.brick_tile_bw);
			BRICK_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return BRICK_BITMAP;
	}
	
	private static Bitmap MARK_BITMAP = null;
	public static Bitmap getMarkBitmap(Context context){
		if(MARK_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_mark);
			MARK_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return MARK_BITMAP;
	}

	private static Bitmap PALM_BITMAP = null;
	public static Bitmap getPalmBitmap(Context context) {
		if(PALM_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_palm);
			PALM_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return PALM_BITMAP;
	}
	
	private static Bitmap TREE_BITMAP = null;
	public static Bitmap getTreeBitmap(Context context) {
		if(TREE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_tree);
			TREE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return TREE_BITMAP;
	}
	
	private static Bitmap HERO_BITMAP_1 = null;
	public static Bitmap getHero1Bitmap(Context context) {
		if(HERO_BITMAP_1 == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_hero_1);
			HERO_BITMAP_1 = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return HERO_BITMAP_1;
	}
	
	private static Bitmap HERO_BITMAP_2 = null;
	public static Bitmap getHero2Bitmap(Context context) {
		if(HERO_BITMAP_2 == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_hero_2);
			HERO_BITMAP_2 = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return HERO_BITMAP_2;
	}
	
	private static Bitmap RIVER_A_BITMAP = null;
	public static Bitmap getRiverABitmap(Context context) {
		if(RIVER_A_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_a);
			RIVER_A_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_A_BITMAP;
	}
	
	private static Bitmap RIVER_B_BITMAP = null;
	public static Bitmap getRiverBBitmap(Context context) {
		if(RIVER_B_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_b);
			RIVER_B_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_B_BITMAP;
	}
	
	private static Bitmap RIVER_C_BITMAP = null;
	public static Bitmap getRiverCBitmap(Context context) {
		if(RIVER_C_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_c);
			RIVER_C_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_C_BITMAP;
	}
	
	private static Bitmap RIVER_D_BITMAP = null;
	public static Bitmap getRiverDBitmap(Context context) {
		if(RIVER_D_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_d);
			RIVER_D_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_D_BITMAP;
	}
	
	private static Bitmap RIVER_E_BITMAP = null;
	public static Bitmap getRiverEBitmap(Context context) {
		if(RIVER_E_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_e);
			RIVER_E_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_E_BITMAP;
	}
	
	private static Bitmap RIVER_F_BITMAP = null;
	public static Bitmap getRiverFBitmap(Context context) {
		if(RIVER_F_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_f);
			RIVER_F_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_F_BITMAP;
	}
	
	private static Bitmap RIVER_G_BITMAP = null;
	public static Bitmap getRiverGBitmap(Context context) {
		if(RIVER_G_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_g);
			RIVER_G_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_G_BITMAP;
	}
	
	private static Bitmap RIVER_H_BITMAP = null;
	public static Bitmap getRiverHBitmap(Context context) {
		if(RIVER_H_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_h);
			RIVER_H_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_H_BITMAP;
	}
	
	private static Bitmap RIVER_I_BITMAP = null;
	public static Bitmap getRiverIBitmap(Context context) {
		if(RIVER_I_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_i);
			RIVER_I_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_I_BITMAP;
	}
	
	private static Bitmap RIVER_J_BITMAP = null;
	public static Bitmap getRiverJBitmap(Context context) {
		if(RIVER_J_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_river_j);
			RIVER_J_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return RIVER_J_BITMAP;
	}
	
	private static Bitmap MOUNTAIN_BITMAP = null;
	public static Bitmap getMountainBitmap(Context context) {
		if(MOUNTAIN_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_mountain);
			MOUNTAIN_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return MOUNTAIN_BITMAP;
	}
	
	private static Bitmap TEMPLE_BITMAP = null;
	public static Bitmap getTempleBitmap(Context context) {
		if(TEMPLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_temple);
			TEMPLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return TEMPLE_BITMAP;
	}
	
	private static Bitmap VISION_TOWER_BITMAP = null;
	public static Bitmap getVisionTowerBitmap(Context context) {
		if(VISION_TOWER_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_tower);
			VISION_TOWER_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return VISION_TOWER_BITMAP;
	}
	
	private static Bitmap VISION_TOWER_X2_BITMAP = null;	
	public static Bitmap getVisionTowerX2Bitmap(Context context) {
		if(VISION_TOWER_X2_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_tower);
			VISION_TOWER_X2_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return VISION_TOWER_X2_BITMAP;
	}
	
	
	
	private static Bitmap MONSTER_6_BITMAP = null;	
	public static Bitmap getMonster6Bitmap(Context context) {
		if(MONSTER_6_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_monster_6);
			MONSTER_6_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return MONSTER_6_BITMAP;
	}
	
	private static Bitmap FIGHT_BITMAP = null;	
	public static Bitmap getFightBitmap(Context context) {
		if(FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_fight);
			FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return FIGHT_BITMAP;
	}
	
	private static Bitmap LIFE_FIGHT_BITMAP = null;	
	public static Bitmap getLifeFightBitmap(Context context) {
		if(LIFE_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_life);
			LIFE_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return LIFE_FIGHT_BITMAP;
	}
	
	private static Bitmap LIFE_FIGHT_TOP_BITMAP = null;	
	public static Bitmap getLifeFightTopBitmap(Context context) {
		if(LIFE_FIGHT_TOP_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_life);
			LIFE_FIGHT_TOP_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE / 2, Constants.SQUARE_SIZE / 2, false);
		}
		return LIFE_FIGHT_TOP_BITMAP;
	}
	
	private static Bitmap HERO1_FIGHT_BITMAP = null;
	public static Bitmap getHero1FightBitmap(Context context) {
		if(HERO1_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_hero_1);
			HERO1_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return HERO1_FIGHT_BITMAP;
	}
	private static Bitmap HERO2_FIGHT_BITMAP = null;
	public static Bitmap getHero2FightBitmap(Context context) {
		if(HERO2_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_hero_2);
			HERO2_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return HERO2_FIGHT_BITMAP;
	}	
	
	
	private static Bitmap ATTACK_FIGHT_BITMAP = null;
	public static Bitmap getAttackFightBitmap(Context context) {
		if(ATTACK_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_attack);
			ATTACK_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return ATTACK_FIGHT_BITMAP;
	}
	private static Bitmap DEFENCE_FIGHT_BITMAP = null;
	public static Bitmap getDefenceFightBitmap(Context context) {
		if(DEFENCE_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_defence);
			DEFENCE_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return DEFENCE_FIGHT_BITMAP;
	}
	private static Bitmap LUCK_FIGHT_BITMAP = null;
	public static Bitmap getLuckFightBitmap(Context context) {
		if(LUCK_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_luck);
			LUCK_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return LUCK_FIGHT_BITMAP;
	}
	private static Bitmap MOVEMENTS_FIGHT_BITMAP = null;
	public static Bitmap getMovementsFightBitmap(Context context) {
		if(MOVEMENTS_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_movements);
			MOVEMENTS_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return MOVEMENTS_FIGHT_BITMAP;
	}
	private static Bitmap ACTIONS_FIGHT_BITMAP = null;
	public static Bitmap getActionsFightBitmap(Context context) {
		if(ACTIONS_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_actions);
			ACTIONS_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return ACTIONS_FIGHT_BITMAP;
	}	
	
	private static Bitmap FIGHT_LEFT_FIGHT_BITMAP = null;
	public static Bitmap getFightLeftFightBitmap(Context context) {
		if(FIGHT_LEFT_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_attack_left);
			FIGHT_LEFT_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return FIGHT_LEFT_FIGHT_BITMAP;
	}
	private static Bitmap FIGHTING_LEFT_FIGHT_BITMAP = null;
	public static Bitmap getFightingLeftFightBitmap(Context context) {
		if(FIGHTING_LEFT_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_attacking_left);
			FIGHTING_LEFT_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return FIGHTING_LEFT_FIGHT_BITMAP;
	}
	private static Bitmap FIGHT_RIGHT_FIGHT_BITMAP = null;
	public static Bitmap getFightRightFightBitmap(Context context) {
		if(FIGHT_RIGHT_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_attack_right);
			FIGHT_RIGHT_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return FIGHT_RIGHT_FIGHT_BITMAP;
	}
	private static Bitmap FIGHTING_RIGHT_FIGHT_BITMAP = null;
	public static Bitmap getFightingRightFightBitmap(Context context) {
		if(FIGHTING_RIGHT_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_attacking_right);
			FIGHTING_RIGHT_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return FIGHTING_RIGHT_FIGHT_BITMAP;
	}
	
	private static Bitmap MONSTER_6_FIGHT_BITMAP = null;	
	public static Bitmap getMonster6FightBitmap(Context context) {
		if(MONSTER_6_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_monster_6);
			MONSTER_6_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, 2 * Constants.SQUARE_SIZE, 2 * Constants.SQUARE_SIZE, false);
		}
		return MONSTER_6_FIGHT_BITMAP;
	}
	
	private static Bitmap LOST_FIGHT_BITMAP = null;
	public static Bitmap getLostFightBitmap(Context context) {
		if(LOST_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_lost);
			LOST_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, 2 * Constants.SQUARE_SIZE, 2 * Constants.SQUARE_SIZE, false);
		}
		return LOST_FIGHT_BITMAP;
	}
	private static Bitmap DEAD_FIGHT_BITMAP = null;
	public static Bitmap getDeadFightBitmap(Context context) {
		if(DEAD_FIGHT_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.fight_grave);
			DEAD_FIGHT_BITMAP = Bitmap.createScaledBitmap(b, 2 * Constants.SQUARE_SIZE, 2 * Constants.SQUARE_SIZE, false);
		}
		return DEAD_FIGHT_BITMAP;
	}

	
	private static Bitmap TREASURE_BITMAP = null;
	public static Bitmap getTreasureBitmap(Context context) {
		if(TREASURE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_treasure);
			TREASURE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		return TREASURE_BITMAP;
	}


	
	private static Bitmap ATTACK_COLLECTABLE_BITMAP = null;	
	public static Bitmap getAttackCollectableBitmap(Context context) {
		if(ATTACK_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_attack);
			ATTACK_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return ATTACK_COLLECTABLE_BITMAP;
	}
	private static Bitmap DEFENCE_COLLECTABLE_BITMAP = null;	
	public static Bitmap getDefenceCollectableBitmap(Context context) {
		if(DEFENCE_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_defence);
			DEFENCE_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return DEFENCE_COLLECTABLE_BITMAP;
	}
	private static Bitmap LUCK_COLLECTABLE_BITMAP = null;	
	public static Bitmap getLuckCollectableBitmap(Context context) {
		if(LUCK_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_luck);
			LUCK_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return LUCK_COLLECTABLE_BITMAP;
	}
	private static Bitmap LIFE_COLLECTABLE_BITMAP = null;	
	public static Bitmap getLifeCollectableBitmap(Context context) {
		if(LIFE_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_life);
			LIFE_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return LIFE_COLLECTABLE_BITMAP;
	}
	private static Bitmap MOVEMENTS_COLLECTABLE_BITMAP = null;	
	public static Bitmap getMovementsCollectableBitmap(Context context) {
		if(MOVEMENTS_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_movements);
			MOVEMENTS_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return MOVEMENTS_COLLECTABLE_BITMAP;
	}
	private static Bitmap ACTIONS_COLLECTABLE_BITMAP = null;	
	public static Bitmap getActionsCollectableBitmap(Context context) {
		if(ACTIONS_COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_actions);
			ACTIONS_COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return ACTIONS_COLLECTABLE_BITMAP;
	}
	private static Bitmap COLLECTABLE_BITMAP = null;
	public static Bitmap getCollectableBitmap(Context context) {
		if(COLLECTABLE_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.terrain_treasure);
			COLLECTABLE_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE * 2, Constants.SQUARE_SIZE * 2, false);
		}
		return COLLECTABLE_BITMAP;
	}
	
	
	
	
	
	//functions for numbers used in fidht and collectable windows!!
	public static SparseArray<Bitmap> NUMBER_MAP = new SparseArray<Bitmap>();
	public static void drawNumber(int number, int x, int y, Canvas canvas, Context context) {
		
		if(NUMBER_MAP.get(number) == null) {
			Bitmap NUMBER_BITMAP = getNumberFromNumbersBitmap(number, context);
			NUMBER_MAP.put(number, Bitmap.createScaledBitmap(NUMBER_BITMAP, Constants.SQUARE_SIZE / 2, Constants.SQUARE_SIZE , false));
		}
		
		canvas.drawBitmap(NUMBER_MAP.get(number), x, y , BitmapFactory.PAINT);
		
	}
	
	public static Bitmap NUMBERS_BITMAP = null;
	public static Bitmap getNumberFromNumbersBitmap(int number, Context context) {
		//TODO: optimize!
		
		if(NUMBERS_BITMAP == null){
			NUMBERS_BITMAP =android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_numbers);
		}
		
		return Bitmap.createBitmap(NUMBERS_BITMAP, number * (NUMBERS_BITMAP.getWidth() / 10), 0, NUMBERS_BITMAP.getWidth() / 10, NUMBERS_BITMAP.getHeight());
	}
	
	private static Bitmap PLUS_BITMAP = null;	
	public static void drawPlus(int x, int y, Canvas canvas, Context context) {
		if(PLUS_BITMAP == null) {
			Bitmap b=android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.game_plus);
			PLUS_BITMAP = Bitmap.createScaledBitmap(b, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, false);
		}
		canvas.drawBitmap(PLUS_BITMAP, x, y , BitmapFactory.PAINT);
	}

	

}
