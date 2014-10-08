package ro.skywebdesign.pocketheroes.enums;

public enum CollectableType {

   	ATTACK(1),
   	DEFENCE(2),
   	LUCK(3),
   	LIFE(4),
   	MOVEMENTS(5),
   	ACTIONS(6),
   	SIGHT(8);
   	
   	

    public int asInt() {
        return asInt;
    }

    private final int asInt;

    private CollectableType(int asInt) {
        this.asInt = asInt;
    }
}
