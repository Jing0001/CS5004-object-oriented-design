public class Parrot extends Bird{
    //member variable
    private int vocabulary;
    public String saying;

    //constructor: create a Parrot object
    public Parrot(int id,  String name,Food[] food, int foodQuantity, Type type, String characteristic, boolean extinct, int wings, String waterBody, int vocabulary, String saying) {
        super(id, name, food, foodQuantity, type,characteristic, extinct, wings);
        this.vocabulary = vocabulary;
        this.saying = saying;
    }

    //getter for vocabulary
    public int getVocabulary() {
        return vocabulary;
    }

    //setter for vocabulary
    public int setVocabulary(int vocabulary) {
        this.vocabulary = vocabulary;
        return vocabulary;
    }

    //getter for saying
    public String getSaying() {
        return saying;
    }

    //setter for saying
    public String setSaying(String saying) {
        this.saying = saying;
        return saying;
    }
}
