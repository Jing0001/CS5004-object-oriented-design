public interface IBird {
    Type getType();
    String getCharacteristic();
    boolean extinct();
    int getWings();
    Food[] food();
    String getWaterBody();
    int getVocabulary();
    String getSaying();
}
