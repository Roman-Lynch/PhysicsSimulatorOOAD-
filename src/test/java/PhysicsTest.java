import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhysicsTest
{
    @Test
    public void TestValidObj()
    {
        double height = 5;
        double width = 5;
        double mass = 100; // kg
        Location location = new Location(0, 0); // meters
        Velocity velocity = new Velocity(1000, 0);

        Object obj = Object.newBuilder()
                .radius(1)
                .mass(mass)
                .velocity(velocity)
                .location(location)
                .color(Color.BLUE)
                .create();

        assertTrue(obj.isValidObj());
    }

    @Test
    public void BottomBorderTest()
    {
        int height = 30;
        int width = 50;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne)
                .setTimeSteps(0.0001)
                .setDuration(20)
                .showVelocityInGUI(false)
                .speak(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20.0);
        assert(Math.round(objOne.getLocation().getY())== -21.0);
    }

    @Test
    public void LeftWallBorderTest()
    {
        int height = 40;
        int width = 50;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(-10, 0);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne)
                .setTimeSteps(0.0001)
                .setDuration(7)
                .showVelocityInGUI(false)
                .speak(false)
                .run()
                .executeGUI();

        assert(Math.round(objOne.getLocation().getX()) == 42.0);
        assert(Math.round(objOne.getLocation().getY())== -21.0);
    }



    @Test
    public void TestSim()
    {
        int height = 50;
        int width = 50;

        double mass1 = 1; // kg
        Location location1 = new Location(10, -10); // meters
        Velocity velocity1 = new Velocity(1, 0);

        double mass2 = 2; // kg
        Location location2 = new Location(16, -10);
        Velocity velocity2 = new Velocity(-1, 0);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Object objTwo = Object.newBuilder()
                .radius(2)
                .mass(mass2)
                .velocity(velocity2)
                .location(location2)
                .color(Color.BLUE)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .setTimeSteps(0.0001)
                .setDuration(20)
                .showVelocityInGUI(true)
                .run()
                .executeGUI();

//        assert(runSim.collisionDetected() == true);
    }

    @Test
    public void InelasticTest()
    {
        int height = 75;
        int width = 125;

        double mass1 = 1; // kg
        Location location1 = new Location(10, -10); // meters
        Velocity velocity1 = new Velocity(1, 0);

        double mass2 = 2; // kg
        Location location2 = new Location(16, -10);
        Velocity velocity2 = new Velocity(-1, 0);

        double mass3 = 2; // kg
        Location location3 = new Location(70, -10);
        Velocity velocity3 = new Velocity(-8, 0);

        double mass4 = 2; // kg
        Location location4 = new Location(50, -10);
        Velocity velocity4 = new Velocity(-10, 0);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Object objTwo = Object.newBuilder()
                .radius(2)
                .mass(mass2)
                .velocity(velocity2)
                .location(location2)
                .color(Color.BLUE)
                .create();


        Object objThree = Object.newBuilder()
                .radius(2)
                .mass(mass3)
                .velocity(velocity3)
                .location(location3)
                .color(Color.green)
                .create();


        Object objFour = Object.newBuilder()
                .radius(2)
                .mass(mass4)
                .velocity(velocity4)
                .location(location4)
                .color(Color.pink)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .addObjects(objThree)
                .addObjects(objFour)
                .setTimeSteps(0.0001)
                .setDuration(30)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

//        assert(runSim.collisionDetected() == true);
    }

    @Test
    public void TestEarthGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 2.86;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddEarth(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
        assert((int)(objOne.getVelocity().getY()) == 0);
    }

    @Test
    public void TestVenusGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 3.003;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddVenus(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .showVelocityInGUI(false)
                .setDamper(0)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestUranusGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 3.003;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddUranus(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestSunGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 0.54;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.BLUE)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddSun(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestSaturnGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 2.77;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddSaturn(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestNeptuneGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 2.68;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddNeptune(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestMoonGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 7.026;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(0.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestMercuryGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 4.65;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.GREEN)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMercury(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestJupiterGravity()
    {
        int height = 60;
        int width = 50;

        // Time it should take an object to fall 40 meters
        double duration = 1.796;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(0, 0);

        Object objOne = Object.newBuilder()
                .radius(1)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddJupiter(height, width,1)
                .addObjects(objOne)
                .setDuration(duration)
                .setTimeSteps(.0001)
                .setDamper(0)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getLocation().getX() == 20);
        assert((int)(objOne.getLocation().getY()) == -59);
    }

    @Test
    public void TestLogging(){
        int x = 5;
        int y = 5;

        Sim sim = new Sim();
        Environment env = new Environment(3,4,3,1);

        sim.display(env, x, y);
    }
}

