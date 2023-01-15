package roguelike;

public enum PlayerSlots {
    HEAD,
    LEFT_HAND,
    RIGHT_HAND,
    CHEST,
    LEGS,
    FEET,
    LEFT_FINGER,
    RIGHT_FINGER,
    CAPE;

    private static final int size = PlayerSlots.values().length;

    public static int getSize() {
        return size;
    }
}