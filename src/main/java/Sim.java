import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.pow;

public class Sim {


    private static final Logger logger = LoggerFactory.getLogger(Sim.class);
    private boolean collisionDetect = false;

    public boolean collisionDetected(){
        return collisionDetect;
    }

    public void display(Environment env, int x, int y){
        for(int i = (x/2); i > -(x/2); i--) {
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
        public EnvironmentFactory eFactory = new EnvironmentFactory();
        private Sim sim = new Sim();


        private boolean elasticCollisions = true;
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

            if (y_cord > env.getHeight() || y_cord < 0) {
                throw new IllegalArgumentException("y_cord must be between 0 and the specified height boundary");
            }

            if (x_cord > env.getWidth() || x_cord < 0) {
                throw new IllegalArgumentException("x_cord must be between 0 and the specified width boundary");
            }

            obj.setLocation(new Location(x_cord, y_cord));
            env.addObject(obj);
            return this;
        }
        public Sim run() {
            logger.info("Running");
            for (double t = 0; t <= duration; t += timeSteps) {

                moveObjects(t);
                checkCollisions();
                checkWallCollisions();
                if (Math.abs(t - Math.round(t)) < 0.000001) {
                    if(sim.collisionDetected()){
                        logger.info("A collision has occurred!");
                        sim.collisionDetect = false;
                    }
                    displayObjects();

                }
            }
            return sim;
        }

        private void checkCollisions() {
            if (env.getObjects().size() > 1) {
                for (int i = 0; i < env.getObjects().size(); i++) {
                    for (int j = i + 1; j < env.getObjects().size(); j++) {
                        if (checkOverlap(env.getObject(i), env.getObject(j))) {
                            handleCollision(env.getObject(i), env.getObject(j));
                        }
                    }
                }
            }
        }

        boolean checkOverlap(Object obj1, Object obj2) {
            Location location1 = obj1.getLocation();
            Location location2 = obj2.getLocation();

            double radius1 = obj1.getRadius();
            double radius2 = obj2.getRadius();

            double dx = location1.getX() - location2.getX();
            double dy = location1.getY() - location2.getY();
            double distance = Math.sqrt(dx * dx + dy * dy);

            return distance <= (radius1 + radius2);
        }

        boolean checkWallOverlap(Object obj1, Environment envior) {
            boolean returnFlag = false;

            Location location = obj1.getLocation();
            double radius = obj1.getRadius();

            // Check Side Borders
            double xLocationOfFarWall = envior.getWidth();
            if ((location.getX() + radius) >= xLocationOfFarWall) {
                returnFlag = true;
            } else if ((location.getX() - radius) <= 0) {
                returnFlag = true;
            }

            // Check Top Borders
            double yLocationOfTopWall = envior.getHeight();
            if ((location.getY() + radius) >= yLocationOfTopWall) {
                returnFlag = true;
            } else if ((location.getY() - radius) <= 0) {
                returnFlag = true;
            }

            return returnFlag;
        }

        private void handleCollision(Object obj1, Object obj2) {
            double mass1 = obj1.getMass();
            double mass2 = obj2.getMass();

            double vel1x = obj1.getVelocity().getX();
            double vel2x = obj2.getVelocity().getX();

            double vel1y = obj1.getVelocity().getY();
            double vel2y = obj2.getVelocity().getY();

            double vel1xf = (((mass1 - mass2) / (mass1 + mass2)) * vel1x) + (((2 * mass2) / (mass1 + mass2)) * vel2x);
            double vel2xf = (((2 * mass1) / (mass1 + mass2)) * vel1x) - (((mass1 - mass2) / (mass1 + mass2)) * vel2x);

            double vel1yf = (((mass1 - mass2) / (mass1 + mass2)) * vel1y) + (((2 * mass2) / (mass1 + mass2)) * vel2y);
            double vel2yf = (((2 * mass1) / (mass1 + mass2)) * vel1y) - (((mass1 - mass2) / (mass1 + mass2)) * vel2y);

            sim.collisionDetect = true;

            obj1.setVelocity(new Velocity(vel1xf, vel1yf));
            obj2.setVelocity(new Velocity(vel2xf, vel2yf));
        }

        private void checkWallCollisions() {
            if (env.getObjects().size() > 1) {
                for (int i = 0; i < env.getObjects().size(); i++) {
                    if (checkWallOverlap(env.getObject(i), env)) {
                        handleWallCollision(env.getObject(i));
                    }
                }
            }
        }

        private void displayObjects() {
            for (int i = 0; i < env.getObjects().size(); i++) {
                logger.info("Object " + (i + 1) + " has velocity: [" + env.getObject(i).getVelocity().getX() + ", " + env.getObject(i).getVelocity().getY() + "] and mass: " + env.getObject(i).getMass() + "kg and position: (" + env.getObject(i).getLocation().getX() + "," + env.getObject(i).getLocation().getY());
            }
            sim.display(env, (int)env.getHeight(), (int)env.getWidth());
        }

        private void handleWallCollision(Object obj) {
            double mass = obj.getMass();
            double massOfWall = Double.MAX_VALUE;

            double velObjX = obj.getVelocity().getX();
            double velWallX = 0;

            double velObjY = obj.getVelocity().getY();
            double velWallY = 0;

            double velObjXf = (((mass - massOfWall) / (mass + massOfWall)) * velObjX) + (((2 * massOfWall) / (mass + massOfWall)) * velWallX);

            double velObjYf = (((mass - massOfWall) / (mass + massOfWall)) * velObjY) + (((2 * massOfWall) / (mass + massOfWall)) * velWallY);

            sim.collisionDetect = true;

            obj.setVelocity(new Velocity(velObjXf, velObjYf));
        }

        private Location nextLocation(Object obj, double timeStep) {
            double xInitPos = obj.getStartLocation().getX();
            double yInitPos = obj.getStartLocation().getY();

            double xInitVelocity = obj.getStartVelocity().getX();
            double yInitVelocity = obj.getStartVelocity().getY();

            double xAcceleration = 0;
            double yAcceleration = -env.getGravity();

            double xFinalPos = xInitPos + (xInitVelocity * timeStep) + (0.5 * xAcceleration * pow(timeStep, 2));
            double yFinalPos = yInitPos + (yInitVelocity * timeStep) + (0.5 * yAcceleration * pow(timeStep, 2));

            double xVelFinal = xInitVelocity + (xAcceleration * timeStep);
            double yVelFinal = yInitVelocity + (yAcceleration * timeStep);
            Velocity newVel = new Velocity(xVelFinal,yVelFinal);

            obj.setVelocity(newVel);

            return new Location(xFinalPos, yFinalPos);
        }

        private void moveObjects(double timeStep) {
            for (int i = 0; i < env.getObjects().size(); i++)
            {
                env.getObject(i).setLocation(nextLocation(env.getObject(i), timeStep));
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

    }
}
