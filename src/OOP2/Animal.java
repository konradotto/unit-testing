package OOP2;

import com.sun.security.jgss.GSSUtil;

public class Animal {

    private String name;
    private String type;
    private double weight;
    private int age;
    private double hunger_level;

    // ------------------ Constructors -----------------------------

    public Animal(String name, String type, double weight, int age){
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.age = age;
        this.hunger_level = 0;
    }

    public Animal(String name, String type, double weight, int age, double hunger_level){
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.age = age;
        this.hunger_level = hunger_level;
    }

    // ------------------ Getters -----------------------------

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public double getHunger_level() {
        return hunger_level;
    }

    // ------------------ Additional methods -----------------------------

    public double getPrice(){

        double price;

        int typePrice = 0;
        if(this.type.toLowerCase().equals("chicken")){
            typePrice = 2;
        }else if(this.type.toLowerCase().equals("cow")){
            typePrice = 5;
        }else if(this.type.toLowerCase().equals("sheep")){
            typePrice = 3;
        }else{
            System.out.println("We shouldn't keep animal of this type in the barn.");
            return 0.0;
        }

        price = typePrice*this.weight-age;

        if(price<0){
            return 0.0;
        }

        return price;

    }

    public String getHealthStatus(){
        if(hunger_level<=0.25){
            return "Good";
        }else if(hunger_level<0.75){
            return "Ok";
        }else{
            return "Bad";
        }
    }


    public String toString(){

        String stringOut = "Name: " + this.name + System.lineSeparator() + "Type: " + this.type + System.lineSeparator() +
                "Weight: " + this.weight + " kg" +  System.lineSeparator() + "Age: " + this.age + "years old" + System.lineSeparator() +
                "Hunger Level: " + this.hunger_level*100 + "%" + System.lineSeparator() + "Health Status: " + getHealthStatus() +
                System.lineSeparator() + "Price: " + getPrice();

        return stringOut;
    }

    public void wander(int laps){

        if(laps<0){
            System.out.println("Error: the animal " + this.name + " cannot wander for negative minutes!" );
            return;
        }

        if(this.hunger_level + laps*0.1 > 1){
            System.out.println("Error: the animal " + this.name + " is too hungry. Ask the farmer to feed it before wandering off!");
            return;
        }
        // increase hunger level by 0.1 in every lap
        this.hunger_level += laps*0.1;

        // decreases the weight by 1 kg in every lap
        this.weight -= laps;

        System.out.println(this.name + " weight is " + this.weight + " and hunger level is "+ this.hunger_level);
    }

    public void beFed(int food){

        // calculate the food unit that is given for every 2kg
        double foodUnit = food/2.0;

        this.hunger_level -= foodUnit*0.1;
        this.weight += foodUnit;

        System.out.println(this.name + " weight is " + this.weight + " and hunger level is "+ this.hunger_level);


    }
}
