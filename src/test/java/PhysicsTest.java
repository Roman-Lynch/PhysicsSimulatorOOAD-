import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhysicsTest
{
    @Test
    public void TestValidObj()
    {
        double height = 5;
        double width = 5;
        double velocity = 1000; // m/s
        double mass = 100; // kg
        Point location = new Point(0, 0); // meters

        Object obj = Object.newBuilder()
                .shape(height, width)
                .mass(mass)
                .velocity(velocity)
                .direction(Direction.RIGHT)
                .location(location)
                .create();

        assertTrue(obj.isValidObj());
    }

//    Sim runSim = Sim.newBuilder()
//            .createAndAddMars(height, width)
//            .addObject(obj)
//            .addObject(obj)
//            .setRunTime(5)
//            .run();

    @Test
    public void TestSim()
    {
        int height = 20;
        int width = 20;
        double velocity1 = 5; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters
        double height2 = 5;
        double width2 = 5;
        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(5, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne, 0,0)
                .addObjects(objTwo,0,19)
                .setRuntime(10)
                .run();
    }


    @Test
    public void TestLogging(){
        int x = 5;
        int y = 5;

        Sim sim = new Sim();
        Environment env = new Environment(3,4,3,1);

//        sim.display(env, x,y);
    }
}

