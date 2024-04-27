import java.awt.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.lang.Math.pow;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sim implements IObservable{
    private static final Logger logger = LoggerFactory.getLogger(Sim.class);
    private boolean collisionDetect = false;

    public boolean collisionDetected(){
        return collisionDetect;
    }

    public void display(Environment env, int x, int y){
        for(int i = 0; i > -(x); i--) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < y; j++) {
                boolean found = false;
                for (int k = 0; k < env.getObjects().size(); k++) {
                    int objectX = (int) Math.round(env.getObject(k).getLocation().getX());
                    int objectY = (int) Math.round(env.getObject(k).getLocation().getY());
                    double distance = Math.sqrt(Math.pow(objectX - j, 2) + Math.pow(objectY - i, 2));
                    if (distance <= env.getObject(k).getRadius()) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    row.append(" O ");
                } else {
                    row.append(" + ");
                }
            }
            logger.info(row.toString());
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ArrayList<Location> positions = new ArrayList<Location>();
        public EnvironmentFactory eFactory = new EnvironmentFactory();
        private Sim sim = new Sim();

        private double timmer;

        private GUI gui;

        private double damper = 1;

        private boolean elastic = true;
        public Environment env;
        private double timeSteps;
        private double duration;

        public Builder createAndAddMars(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Mars", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddEarth(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Earth", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddBlackHole(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("BlackHole", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddJupiter(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Jupiter", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddMercury(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Mercury", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddMoon(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Moon", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddNeptune(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Neptune", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddSaturn(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Saturn", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddSun(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Sun", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddUranus(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Uranus", height, width, wallElasticity);
            return this;
        }

        public Builder createAndAddVenus(int height, int width, double wallElasticity) {
            if (height <= 0) {
                throw new IllegalArgumentException("Height must be greater than 0");
            }

            if (width <= 0) {
                throw new IllegalArgumentException("Width must be greater than 0");
            }

            env = eFactory.createEnvironment("Venus", height, width, wallElasticity);
            return this;
        }

        public Builder addObjects(Object obj) {
            double x_cord = obj.getLocation().getX();
            double y_cord = obj.getLocation().getY();

//            if (y_cord > env.getHeight() || y_cord < 0) {
//                throw new IllegalArgumentException("y_cord must be between 0 and the specified height boundary");
//            }
//
//            if (x_cord > env.getWidth() || x_cord < 0) {
//                throw new IllegalArgumentException("x_cord must be between 0 and the specified width boundary");
//            }

            obj.setLocation(new Location(x_cord, y_cord));
            env.addObject(obj);
            return this;
        }
        public Builder run() {
            logger.info("Running");
            for (double t = 0; t <= duration; t += timeSteps) {
                timmer = t;

                moveObjects(t);

                // Update the positions
                for (Object object : env.getObjects()){
                    positions.add(object.getLocation());
                }

                checkCollisions(t);
                checkWallCollisions(t);
                if (Math.abs(t - Math.round(t)) < 0.000001) {
                    if(sim.collisionDetected()){
                        logger.info("A collision has occurred!");
                        sim.collisionDetect = false;
                    }
                    displayObjects();

                }
            }
            return this;
        }

        public Sim executeGUI() {
            GUI gui = new GUI(env, positions, (long)timeSteps, duration);
            return sim;
        }

        private void checkCollisions(double timeSteps) {
            if (env.getObjects().size() > 1) {
                for (int i = 0; i < env.getObjects().size(); i++) {
                    for (int j = i + 1; j < env.getObjects().size(); j++){
                        if (checkOverlap(env.getObject(i),
                                         nextLocation(env.getObject(i), false),
                                                      env.getObject(i).getRadius(),
                                         env.getObject(j),
                                         nextLocation(env.getObject(j),  false),
                                                      env.getObject(j).getRadius())) {

                            handleCollision(env.getObject(i), env.getObject(j));
                        }
                    }
                }
            }
        }

        boolean checkOverlap(Object object1, Location obj1, double R1, Object object2, Location obj2, double R2) {

            boolean alreadyStuck = false;

            for(int i = 0; i < object1.getStuckObjects().size(); i++){
                if(object1.getStuckObject(i) == object2){
                    alreadyStuck = true;
                }
            }

            if(alreadyStuck){
                return false;
            }
            else {
                double dx = obj1.getX() - obj2.getX();
                double dy = obj1.getY() - obj2.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                return distance <= (R1 + R2);
            }
        }

        boolean checkWallOverlap(Location obj, double radius, Environment envior) {
            boolean returnFlag = false;

            // Check Side Borders
            double xLocationOfFarWall = envior.getWidth();
            //logger.info("xLocationOfWall: " + xLocationOfFarWall);
            if ((obj.getX() + radius) >= xLocationOfFarWall) {
                returnFlag = true;
            } else if ((obj.getX() - radius) <= 0) {
                returnFlag = true;
            }

            // Check Top Borders
            double yLocationOfWall = (0 - envior.getHeight());
            //logger.info("yLocationOfWall: " + yLocationOfWall);
            if ((obj.getY() + radius) >= 0) {
                returnFlag = true;
            } else if ((obj.getY() - radius) <= yLocationOfWall) {
                returnFlag = true;
            }

            return returnFlag;
        }

        private void handleCollision(Object obj1, Object obj2) {

//            logger.info("obj1 Velocity x: " + obj1.getVelocity().getX());
//            logger.info("obj2 Velocity x: " + obj2.getVelocity().getX());

                double mass1 = obj1.getMass();
                double mass2 = obj2.getMass();

                double vel1x = obj1.getVelocity().getX();
                double vel2x = obj2.getVelocity().getX();

                double vel1y = obj1.getVelocity().getY();
                double vel2y = obj2.getVelocity().getY();

            if(elastic) {
                double vel1xf = (((mass1 - mass2) / (mass1 + mass2)) * vel1x) + (((2 * mass2) / (mass1 + mass2)) * vel2x);
                double vel2xf = (((2 * mass1) / (mass1 + mass2)) * vel1x) - (((mass1 - mass2) / (mass1 + mass2)) * vel2x);

                double vel1yf = (((mass1 - mass2) / (mass1 + mass2)) * vel1y) + (((2 * mass2) / (mass1 + mass2)) * vel2y);
                double vel2yf = (((2 * mass1) / (mass1 + mass2)) * vel1y) - (((mass1 - mass2) / (mass1 + mass2)) * vel2y);

                sim.collisionDetect = true;

                double v1xf = vel1xf * damper;
                double v2xf = vel2xf * damper;
                double v1yf = vel1yf * damper;
                double v2yf = vel2yf * damper;

                obj1.setVelocity(new Velocity(vel1xf, vel1yf));
                obj2.setVelocity(new Velocity(vel2xf, vel2yf));


            }
            else {
                double vel1xf = (((mass1*vel1x)+(mass2*vel2x))/(mass1+mass2));
                double vel1yf = (((mass1*vel1y)+(mass2*vel2y))/(mass1+mass2));

                sim.collisionDetect = true;

                double v1xf = vel1xf * damper;
                double v1yf = vel1yf * damper;

                obj1.setVelocity(new Velocity(vel1xf, vel1yf));
                obj2.setVelocity(new Velocity(vel1xf, vel1yf));

                obj1.setMass((mass1+mass2));
                obj2.setMass((mass1+mass2));

                obj1.addStuckObject(obj2);
                obj2.addStuckObject(obj1);



                for (Object stuckObject : obj2.getStuckObjects()) {
                    obj1.addStuckObject(stuckObject);
                }

                for (Object stuckObject : obj1.getStuckObjects()) {
                    obj2.addStuckObject(stuckObject);
                }

                for (Object stuckObject : obj1.getStuckObjects()) {
                    stuckObject.setMass(obj1.getMass());
                    stuckObject.setVelocity(obj1.getVelocity());
                }
            }

//            logger.info("obj1 Velocity x: " + obj1.getVelocity().getX());
//            logger.info("obj2 Velocity x: " + obj2.getVelocity().getX());
        }

        private void checkWallCollisions(double timeSteps) {
            if (!env.getObjects().isEmpty()) {
                for (int i = 0; i < env.getObjects().size(); i++) {
                    if (checkWallOverlap(nextLocation(env.getObject(i), false),
                            env.getObject(i).getRadius(),
                            env)) {
                        handleWallCollision(env.getObject(i), env);
                    }
                }
            }
        }

        private void displayObjects() {
            for (int i = 0; i < env.getObjects().size(); i++) {
                double vel = Math.sqrt(Math.pow(env.getObject(i).getVelocity().getX(), 2) + Math.pow(env.getObject(i).getVelocity().getY(), 2));
                double KE = (.5)*(env.getObject(i).getMass())*(Math.pow(vel, 2));
                double PE = env.getObject(i).getMass() * env.getGravity() * (env.getHeight()+env.getObject(i).getLocation().getY());
                PE = Math.abs(PE);
                double totalEnergy = PE + KE;
                totalEnergy = totalEnergy;

                logger.info("Object " + (i + 1) + " has Total Energy: " + totalEnergy + " and velocity: [" + env.getObject(i).getVelocity().getX() + ", " + env.getObject(i).getVelocity().getY() + "] and mass: " + env.getObject(i).getMass() + "kg and position: (" + env.getObject(i).getLocation().getX() + "," + env.getObject(i).getLocation().getY() + ")");
            }
            sim.display(env, (int)env.getHeight(), (int)env.getWidth());
        }

        private void handleWallCollision(Object obj, Environment envior) {
            //logger.info("obj1 Velocity x: " + obj.getVelocity().getX());
            //logger.info("obj Velocity y: " + obj.getVelocity().getY());

            double velObjX = obj.getVelocity().getX();
            double velObjY = obj.getVelocity().getY();

            velObjX = velObjX * damper;
            velObjY = velObjY * damper;


            Location locObj = obj.getLocation();
            double radius = obj.getRadius();

            // Check Side Borders
            double xLocationOfFarWall = envior.getWidth();
            //logger.info("xLocationOfWall: " + xLocationOfFarWall);
            if ((locObj.getX() + radius) >= xLocationOfFarWall) {
                obj.setVelocity(new Velocity(-velObjX, velObjY));
            } else if ((locObj.getX() - radius) <= 0) {
                obj.setVelocity(new Velocity(-velObjX, velObjY));
            }

            // Check Top Borders
            double yLocationOfWall = (0 - envior.getHeight());
            //logger.info("yLocationOfWall: " + yLocationOfWall);
            if ((locObj.getY() + radius) >= 0) {
                obj.setVelocity(new Velocity(velObjX, -velObjY));
            } else if ((locObj.getY() - radius) <= yLocationOfWall) {
                obj.setVelocity(new Velocity(velObjX, -velObjY));
            }

            sim.collisionDetect = true;
        }

        private Location nextLocation(Object obj , boolean velocity) {
            double xInitPos = obj.getLocation().getX();
            double yInitPos = obj.getLocation().getY();

            double xInitVelocity = obj.getVelocity().getX();
            double yInitVelocity = obj.getVelocity().getY();
//            if (velocity) {
//                logger.info("Object " + obj);
//                logger.info("Velocity X: " + xInitVelocity);
//                logger.info("Velocity Y: " + yInitVelocity);
//            }
            double xAcceleration = 0;
            double yAcceleration = -env.getGravity();

            double xFinalPos = xInitPos + (xInitVelocity * timeSteps) + (0.5 * xAcceleration * pow(timeSteps, 2));
            double yFinalPos = yInitPos + (yInitVelocity * timeSteps) + (0.5 * yAcceleration * pow(timeSteps, 2));
//            if (velocity) {
//                logger.info("position X: " + xFinalPos);
//                logger.info("position Y: " + yFinalPos);
//                logger.info("");
//            }
            double xVelFinal = xInitVelocity + (xAcceleration * timeSteps);
            double yVelFinal = yInitVelocity + (yAcceleration * timeSteps);
            Velocity newVel = new Velocity(xVelFinal,yVelFinal);

            // This is just so we don't update the velocity when checking the next position in the collision detection
            // We only want to do this when we are moving the objects, in the below function.
            if (velocity){
                obj.setVelocity(newVel);
            }

            return new Location(xFinalPos, yFinalPos);
        }

        private void moveObjects(double timeStep) {
            for (int i = 0; i < env.getObjects().size(); i++)
            {
                env.getObject(i).setLocation(nextLocation(env.getObject(i), true));
            }
        }

        public Builder setTimeSteps(double desiredRunTime) {
            timeSteps = desiredRunTime;
            return this;
        }

        public Builder setDuration(double desiredDuration) {
            duration = desiredDuration;
            return this;
        }

        public Builder setElastic(boolean elastic){
            this.elastic = elastic;
            return this;
        }

        public Builder setDamper(double damper){
            this.damper = damper;
            return this;
        }

    }

    @Override
    public void subscribe(IObserver observer, List<EventType> interestedEvents) {
        for (EventType event : interestedEvents) {
            EventBus.getInstance().attach(observer, event);
        }
    }
}
