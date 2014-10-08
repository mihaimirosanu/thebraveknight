package ro.skywebdesign.pocketheroes.model.map;

public enum MapObjectType {
   	GRASS('0'),
    TREE('T'),
    PALM('P'),
    MOUNTAIN('M'),
    MAIN_HERO('1'),
    COMPUTER_HERO_2('2'),
    
    MONSTER_6('6'),
    MONSTER_7('7'),
    MONSTER_8('8'),
    MONSTER_9('9'),
    
    RIVER_A('A'),
    RIVER_B('B'),
    RIVER_C('C'),
    RIVER_D('D'),
    RIVER_E('E'),
    RIVER_F('F'),
    RIVER_G('G'),
    RIVER_H('H'),
    RIVER_I('I'),
    RIVER_J('J'),
    
    TEMPLE('*'), 
    TREASURE('r'),
   	
   	VISION_TOWER('v');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    private MapObjectType(char asChar) {
        this.asChar = asChar;
    }
}
