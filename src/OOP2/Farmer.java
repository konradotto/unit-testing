package OOP2;

public class Farmer {

    private String name;

    public Farmer(String name){
        this.name = name;
    }

    // ------------- Getters -------------------

    public String getName() {
        return name;
    }

    // ------------- Methods --------------------

    public void Feed(Animal animal, int food){
        animal.beFed(food);
    }

}
