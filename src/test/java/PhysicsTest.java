import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Math.pow;
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

    @Test
    public void TestSim()
    {
        int height = 20;
        int width = 20;

        double velocity1 = 5; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(19, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(10)
                .run();

        assert(runSim.collisionDetected() == true);
    }

    @Test
    public void TestEarthGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 9.81 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        System.out.println(finalY);
        System.out.println(correctY);

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestVenusGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddVenus(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 8.87 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestUranusGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddUranus(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 8.87 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestSunGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddSun(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 274 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestSaturnGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Saturn sat = new Saturn(9,10,0.5);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddSaturn(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 10.44 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false); //Note, overtime there may be slight errors due to rounding
    }

    @Test
    public void TestNeptuneGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddNeptune(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 11.15 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false); //Note, overtime there may be slight errors due to rounding
    }

    @Test
    public void TestMoonGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 1.62 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestMercuryGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMercury(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 3.7 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestJupiterGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddJupiter(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 24.79 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;
        System.out.println(finalY);
        System.out.println(correctY);

        assert(runSim.collisionDetected() == false);    }

    @Test
    public void TestBlackHoleGravity()
    {
        int height = 20;
        int width = 10;
        int runtime = 5;

        double velocity1 = 3; // m/s
        double mass1 = 1; // kg
        Point location1 = new Point(0, 0); // meters

        double velocity2 = -3; // m/s
        double mass2 = 2; // kg
        Point location2 = new Point(10, 0);

        Object objOne = Object.newBuilder()
                .shape(1,1)
                .mass(mass1)
                .velocity(velocity1)
                .direction(Direction.RIGHT)
                .location(location1)
                .create();

        Object objTwo = Object.newBuilder()
                .shape(1, 1)
                .mass(mass2)
                .velocity(velocity2)
                .direction(Direction.LEFT)
                .location(location2)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddBlackHole(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setRuntime(runtime)
                .run();

        int correctY = (int) (0 - (3 + runtime) - (0.5 * 99999999.99999 * pow(runtime, 2)));
        int finalY = objOne.getLocation().y;

        assert(runSim.collisionDetected() == false);    }


    @Test
    public void TestLogging(){
        int x = 5;
        int y = 5;

        Sim sim = new Sim();
        Environment env = new Environment(3,4,3,1);

        sim.display(env, x,y);
    }
}

