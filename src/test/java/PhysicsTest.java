import org.junit.jupiter.api.Test;

import java.awt.*;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhysicsTest
{
    // Given a single object
    // The object should spawn in without any errors.
    @Test
    public void TestValidObj()
    {
        double height = 5;
        double width = 5;
        double mass = 100; // kg
        Location location = new Location(10, -10); // meters
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

    // Given a single object
    // The object should spawn in without any errors.
    // Through a visual check, it should also be verified that the object
    // has the correct size, location, and velocity and accelerates downwards
    // The object should collide with the bottom border
    // When the object collides with the bottom border,
    // The object should rebound back upwards without losing any energy
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

    // Given a single object
    // The object should spawn in without any errors.
    // Through a visual check, it should also be verified that the object
    // has the correct size, location, and velocity and accelerates downwards
    // The object should collide with the left border
    // When the object collides with the left border,
    // The object should rebound back upwards without losing any energy
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
                .speak(true)
                .run()
                .executeGUI();

        assert(Math.round(objOne.getLocation().getX()) == 42.0);
        assert(Math.round(objOne.getLocation().getY())== -21.0);
    }

    // Given two object and an environment
    // The objects should spawn in without any errors.
    // Through a visual check, it should also be verified that the objects
    // have the correct initial size, location, and velocity
    // The objects should collide
    // When the objects collide, their x velocity's should swap directions
    @Test
    public void TestSim()
    {
        int height = 50;
        int width = 50;

        double mass1 = 1; // kg
        Location location1 = new Location(11, -10); // meters
        Velocity velocity1 = new Velocity(1, 0);

        double mass2 = 1; // kg
        Location location2 = new Location(18, -10);
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
                .setDuration(5)
                .showVelocityInGUI(false)
                .run()
                .executeGUI();

        assert(objOne.getVelocity().getX() == - velocity1.getX() && objTwo.getVelocity().getX() == - velocity2.getX());
    }

    // Given multiple object and an environment
    // The objects should spawn in without any errors.
    // Through a visual check, it should also be verified that the objects
    // have the correct initial size, location, and velocity
    // The objects should collide
    // When the objects collide, they should collide and rebound with varying velocities
    @Test
    public void elasticTest()
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

        assert(objOne.getVelocity().getX() != velocity1.getX() && objTwo.getVelocity().getX() != velocity2.getX() && objThree.getVelocity().getX() != velocity3.getX() && objFour.getVelocity().getX() != velocity4.getX());
    }

    @Test
    public void testVolume()
    {
        int height = 85;
        int width = 142;

        Object one = Object.newBuilder()
                .radius(1)
                .mass(50)
                .velocity(new Velocity(3,3))
                .location(new Location(10,-10))
                .color(Color.RED)
                .create();

        Object two = Object.newBuilder()
                .radius(2)
                .mass(20)
                .velocity(new Velocity(4,5))
                .location(new Location(20,-20))
                .color(Color.blue)
                .create();

        Object three = Object.newBuilder()
                .radius(3)
                .mass(35)
                .velocity(new Velocity(5,6))
                .location(new Location(30,-30))
                .color(Color.yellow)
                .create();

        Object four = Object.newBuilder()
                .radius(4)
                .mass(60)
                .velocity(new Velocity(6,7))
                .location(new Location(40,-40))
                .color(Color.green)
                .create();

        Object five = Object.newBuilder()
                .radius(5)
                .mass(65)
                .velocity(new Velocity(25,8))
                .location(new Location(50,-50))
                .color(Color.GRAY)
                .create();

        Object six = Object.newBuilder()
                .radius(6)
                .mass(7)
                .velocity(new Velocity(8,9))
                .location(new Location(10,-40))
                .color(Color.PINK)
                .create();

        Object seven = Object.newBuilder()
                .radius(7)
                .mass(8)
                .velocity(new Velocity(9,-40))
                .location(new Location(15,-75))
                .color(Color.darkGray)
                .create();

        Object eight = Object.newBuilder()
                .radius(8)
                .mass(8)
                .velocity(new Velocity(11,12))
                .location(new Location(80,-75))
                .color(Color.magenta)
                .create();

        Object nine = Object.newBuilder()
                .radius(9)
                .mass(15)
                .velocity(new Velocity(1,-1))
                .location(new Location(120,-70))
                .color(Color.orange)
                .create();


        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(one)
                .addObjects(two)
                .addObjects(three)
                .addObjects(four)
                .addObjects(five)
                .addObjects(six)
//                .addObjects(seven)
//                .addObjects(eight)
//                .addObjects(nine)
                .setTimeSteps(0.0001)
                .setDuration(70)
                .showVelocityInGUI(false)
                .setElastic(true)
                .setOrbital(false)
                .run()
                .executeGUI();

//        assert(runSim.collisionDetected() == true);
    }

    @Test
    public void InelasticTest()
    {
        int height = 75;
        int width = 125;

        double mass1 = 200; // kg
        Location location1 = new Location(14, -30); // meters
        Velocity velocity1 = new Velocity(8, -12);

        double mass2 = 1; // kg
        Location location2 = new Location(27, -10);
        Velocity velocity2 = new Velocity(-3, 7);

        double mass3 = 1; // kg
        Location location3 = new Location(95, -50);
        Velocity velocity3 = new Velocity(-20, 9);

        double mass4 = 1; // kg
        Location location4 = new Location(80, -20);
        Velocity velocity4 = new Velocity(-10, 4);

        double mass5 = 1; // kg
        Location location5 = new Location(113, -20);
        Velocity velocity5 = new Velocity(-10, 4);

        Object objOne = Object.newBuilder()
                .radius(10)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Object objTwo = Object.newBuilder()
                .radius(1)
                .mass(mass2)
                .velocity(velocity2)
                .location(location2)
                .color(Color.BLUE)
                .create();


        Object objThree = Object.newBuilder()
                .radius(1)
                .mass(mass3)
                .velocity(velocity3)
                .location(location3)
                .color(Color.green)
                .create();

        Object objFour = Object.newBuilder()
                .radius(1)
                .mass(mass4)
                .velocity(velocity4)
                .location(location4)
                .color(Color.pink)
                .create();

        Object objFive = Object.newBuilder()
                .radius(1)
                .mass(mass5)
                .velocity(velocity5)
                .location(location5)
                .color(Color.orange)
                .create();


        Sim runSim = Sim.newBuilder()
                .createAndAddMoon(height, width,1)
                .addObjects(objOne)
                .addObjects(objTwo)
                .addObjects(objThree)
                .addObjects(objFour)
                .addObjects(objFive)
                .setTimeSteps(0.0001)
                .setDuration(50)
                .showVelocityInGUI(false)
                .setElastic(true)
                .run()
                .executeGUI();

//        assert(runSim.collisionDetected() == true);
    }

    @Test
    public void Orbit()
    {
        int height = 85;
        int width = 145;

        double mass1 = 800; // kg
        Location location1 = new Location(85, -10); // meters
        Velocity velocity1 = new Velocity(3, -10);

        double mass2 = 400; // kg
        Location location2 = new Location(80, -77);
        Velocity velocity2 = new Velocity(-1, 10);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .color(Color.RED)
                .create();

        Object objTwo = Object.newBuilder()
                .radius(1)
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
                .setDuration(50)
                .showVelocityInGUI(false)
                .setElastic(true)
                .setOrbital(true)
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

