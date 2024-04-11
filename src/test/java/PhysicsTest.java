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
    public void TestLogging(){
        int x = 5;
        int y = 5;

        Sim sim = new Sim();

        sim.display(x,y);
    }
}

