package OOP2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    Animal myChicken;

    @BeforeEach
    public void initEach(){
        myChicken = new Animal("Joe", "chicken", 30.0, 3);
    }

    @Test
    public void priceTest(){
        double expectedValue = 57.0;
        double actualValue = myChicken.getPrice();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testHealthWithHungryAnimal(){
        myChicken.wander(9);
        String expectedHealth = "Bad";
        String actualHealth = myChicken.getHealthStatus();
        assertEquals(expectedHealth,actualHealth);
    }


}
