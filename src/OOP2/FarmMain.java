package OOP2;
//you may need to change the package to match your project's package.
import java.util.Scanner;
public class FarmMain {
	
	private static final int ACQUIRE_ANIMAL = 1;
	private static final int PRINT_ANIMALS = 2;
	private static final int PRINT_CHEAPEST_ANIMAL = 3;
	private static final int PRINT_ANIMAL = 4;
	private static final int ANIMAL_WANDER = 5;
	private static final int HIRE_FARMER = 6;
	private static final int FEED_ANIMAL = 7;
	private static final int QUIT = 8;

	private Animal[] animals;
	private Farmer[] farmers;
	private int acquiredAnimals;   // declaring  the variable
	private int hiredFarmers;// declaring the variable

	private Scanner input;

	/*
	 * Constructor to initialize your data set of animals and farmers.
	 * It begins empty. The default size is 5 animals and 5 farmers.
	 */
	 
	public FarmMain() {
		final int INITIAL_BARN_SIZE = 5; 			
		this.animals = new Animal[INITIAL_BARN_SIZE];	// this is the size of the barn in which the first set of animals can be housed 
		this.acquiredAnimals = 0;
		
		final int INITIAL_HOUSE_SIZE = 5;
		this.farmers = new Farmer[INITIAL_HOUSE_SIZE];	// this is the size of the house in which the first set of farmers can live 
		this.hiredFarmers = 0;
		input = new Scanner(System.in);
	}

    /*
     * This method will retrieve an animal from the array based on a specified name.
     * If the animal was not created and added to the array, it will return NULL,
     * meaning that the animal does not exist in the system.
     * 
     */
	public Animal retrieveAnimal(String animalName){
		for (int i = 0; i < this.animals.length; i++){
			if(animals[i] != null && animals[i].getName().equals(animalName)){
				return animals[i];
			}
		}
		/*example of a null... if no animal
           with the give name exists, the
           object does not exist (i.e. "nothing"). */
		return null;
	}
	
	/*
     * This method will retrieve a farmer from the array based on a specified name.
     * If the farmer was not created and added to the array, it should return NULL,
     * meaning that the farmer does not exist in the system.
     * 
     */
	public Farmer retrieveFarmer(String farmerName){
		for (int i = 0; i < this.farmers.length; i++){
			if(farmers[i] != null && farmers[i].getName().equals(farmerName)){
				return farmers[i];
			}
		}
		/*example of a null... if no animal
           with the give name exists, the
           object does not exist (i.e. "nothing"). */
		return null;
	}

	/*
	 * This method allows the user to create the animal. Either create an animal with a default Hunger level 1, or create an animal with a custom animal.
	 */
    public Animal createAnimal() {

		Animal newAnimal;

		System.out.println("Please type in the following attributes to create and register a new Animal.");

		System.out.println("Animal's name: ");
		String name = input.nextLine();

		while(retrieveAnimal(name) != null){
			System.out.println("Animal already exists. Try another name!");
			System.out.println("Animal's name: ");
			name = input.nextLine();
		}

		System.out.println("Animal's type (chicken, cow or sheep): ");
		String type = input.nextLine().toLowerCase();
    	while(!(type.equals("chicken") || type.equals("cow") || type.equals("sheep"))){
			System.out.println("We do not keep other type of animal in this barn. Type chicken, cow or sheep.");
			System.out.println("Animal's type: ");
			type = input.nextLine().toLowerCase();
		}

		System.out.println("Animal's age: ");
    	int age = input.nextInt();
    	input.nextLine();
    	while(age < 0){
			System.out.println("Animal should have non-negative age. Try again!");
			System.out.println("Animal's age: ");
			age = input.nextInt();
			input.nextLine();
		}

		System.out.println("Animal's weight: ");
    	double weight = input.nextDouble();
    	input.nextLine();
    	while(weight < 0){
			System.out.println("Animal should have non-negative weight. Try again!");
			System.out.println("Animal's weight: ");
			weight = input.nextDouble();
			input.nextLine();
		}

		System.out.println("Do you want to specify hunger level (Y/N)? ");

    	if(input.nextLine().toUpperCase().equals("Y")){
			System.out.println("Animal's hunger level: ");
			double hunger_level = input.nextDouble();
			input.nextLine();
			while(hunger_level>1 || hunger_level < 0){
				System.out.println("Hunger level should be between 0 and 1. Try again!");
				System.out.println("Animal's hunger level: ");
				hunger_level = input.nextDouble();
				input.nextLine();
			}

			newAnimal = new Animal(name, type, weight, age, hunger_level);
		}else{

			newAnimal = new Animal(name, type, weight, age);
		}

    	return newAnimal;
	}
    	

	public Farmer createFarmer() {

		System.out.println("Please type in the following attributes to create and register a new Farmer.");

		System.out.println("Farmer's name");
		String name = input.nextLine();
		while(retrieveFarmer(name) != null){
			System.out.println("Farmer with this name already exists. Try another name!");
			System.out.println("Farmer's name");
			name = input.nextLine();
		}

		Farmer newFarmer = new Farmer(name);

		return newFarmer;

    }

    
    public void run() {

		int option;
		do {
			printMenuOptions();
			System.out.print(" Type the option number: ");

			option = input.nextInt();
			input.nextLine(); //this skips the enter
			//that the user types after
			//typing the integer option.

			switch (option) {
				case ACQUIRE_ANIMAL:

					Animal newAnimal =  createAnimal();
					this.animals[acquiredAnimals] = newAnimal;
					this.acquiredAnimals = this.acquiredAnimals + 1;
					//Make sure your program allows any amount of animals!
					// Assume that we can add more than 5 animals to the array.
					// 
					break;

				case PRINT_ANIMALS:
					printAllAnimals();
					break;

				case PRINT_ANIMAL:
					printOneAnimal();
					break;

				case PRINT_CHEAPEST_ANIMAL:
					printCheapest();
					break;

				case ANIMAL_WANDER:
					wander();
					break;

				case HIRE_FARMER:
					Farmer newFarmer = createFarmer();
					this.farmers[hiredFarmers] = newFarmer;
					this.hiredFarmers = this.hiredFarmers + 1;
					//Make sure your program allows any amount of farmers!
					// Assume that we can add more than 5 farmers to the array.
					//
					break;

					case FEED_ANIMAL:
					feed();
					break;

				case QUIT:
					System.out.println("Thank you for visiting our Barn. See you soon!");
					System.out.println();
					break;

				default:
					System.out.println("Option "+option+" is not valid.");
					System.out.println();
					break;
			}
		} while (option != QUIT);
	}

	//This method is private because it should be used only by
	// this class since the menu is specific to this main.
	private void printMenuOptions() {
		System.out.println(" === Welcome to DIT042 Barn === ");
		System.out.println(" Choose an option below: ");
		System.out.println(" ");
		System.out.println(" 1. Acquire an animal. ");
		System.out.println(" 2. Print all animals. ");
		System.out.println(" 3. Print the cheapest animal's information. ");
		System.out.println(" 4. Print an animal's information. ");
		System.out.println(" 5. Make an animal wander ");
		System.out.println(" 6. Hire a farmer ");
		System.out.println(" 7. Feed an animal ");
		System.out.println(" 8. Quit this program. ");
		System.out.println();
	}

	public void printAllAnimals() {

		for (int i = 0; i < acquiredAnimals; i++) {
			if(animals[i] != null){
				System.out.println(animals[i].getName());
			}
		}

	}

	public void printOneAnimal() {
		String animalName = readName();

		Animal foundAnimal = retrieveAnimal(animalName);

		if(foundAnimal != null){
			System.out.println(foundAnimal.toString());
		}else{
			System.out.println("Animal does not registered in the barn.");
		}
	}

	public void printCheapest() {

		System.out.println("What type of animal are you looking for (chicken, cow or sheep)? ");
		String type = input.nextLine().toLowerCase();
		while(!(type.equals("chicken") || type.equals("cow") || type.equals("sheep"))) {
			System.out.println("We do not keep other type of animal in this barn. Type chicken, cow or sheep.");
			System.out.printf("Animal's type: ");
			type = input.nextLine().toLowerCase();
		}

		double lowestPrice = Integer.MAX_VALUE;
		Animal cheapestAnimal = null;
		for (int i = 0; i < acquiredAnimals; i++) {
			if(animals[i] != null){
				if(animals[i].getType().equals(type)){
					if(animals[i].getPrice() < lowestPrice){
						lowestPrice = animals[i].getPrice();
						cheapestAnimal = animals[i];
					}
				}
			}
		}

		if(cheapestAnimal == null){
			System.out.println("There is no animal of this type in the barn.");
		}

		System.out.println("The cheapest animal is " + cheapestAnimal.getName());

	}

	/*
	 * This method only reads a String that here, will be the name
	 * of an animal, or farmer, that you want to use.
	 */
	public String readName() {
		System.out.print("Type the name that you want to use: ");
		String animalName = input.nextLine();
		return animalName;
	}
	
	/*
	 * This method only reads the amount of food your farmer will feed the animal.
	 * For simplicity, we will use int, instead of double for the food.
	 */
	public int readFoodAmount() {
		System.out.print("Type the integer amount of food in kg: ");
		int foodAmount = input.nextInt();
		input.nextLine();
		return foodAmount;
	}
	
	public void wander() {
		String animalName = readName();
		Animal foundAnimal = retrieveAnimal(animalName);

		if(foundAnimal != null){
			System.out.println("Type the amount of time the animal should wander (minutes): ");
			int time = input.nextInt();
			foundAnimal.wander(time);
		}
	}

	public void feed() {
		String animalName = readName();
		Animal foundAnimal = retrieveAnimal(animalName);

		String farmerName = readName();
		Farmer foundFarmer = retrieveFarmer(farmerName);
		int foodAmount = readFoodAmount();

		while(foodAmount<0){
			System.out.println("Animals cannot eat negative amount of food. Try again!");
			foodAmount = readFoodAmount();
		}

		if(foundAnimal != null && foundFarmer != null){
			foundFarmer.Feed(foundAnimal,foodAmount);
		}

	}

	public static void main(String[] args) {
		FarmMain program = new FarmMain();
		program.run();
	}
}
