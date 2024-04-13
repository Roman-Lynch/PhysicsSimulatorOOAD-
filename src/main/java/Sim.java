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
        for(int i = (y/2); i > -(y/2); i--) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < x; j++) {
                boolean found = false;
                for (int k = 0; k < env.getObjects().size(); k++) {
                    if (env.getObject(k).getLocation().getX() == j && env.getObject(k).getLocation().getY() == i) {
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

    boolean checkOverlap(Object obj1, Object obj2) {
        double startX1 = obj1.getStartLocation().x;
        double endX1 = obj1.getLocation().x;

        double startX2 = obj2.getStartLocation().x;
        double endX2 = obj2.getLocation().x;

        return (startX1 <= endX2 && endX1 >= startX2) || (startX2 <= endX1 && endX2 >= startX1);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder {
        public EnvironmentFactory eFactory = new EnvironmentFactory();
        private Sim sim = new Sim();


        private boolean elasticCollisions = true;
        public Environment env;
        private int timeSteps;

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
            int x_cord = obj.getLocation().x;
            int y_cord = obj.getLocation().y;

            if (y_cord > env.getHeight() || y_cord < 0) {
                throw new IllegalArgumentException("y_cord must be between 0 and the specified height boundary");
            }

            if (x_cord > env.getWidth() || x_cord < 0) {
                throw new IllegalArgumentException("x_cord must be between 0 and the specified width boundary");
            }

            obj.setLocation(new Point(x_cord, y_cord));
            env.addObject(obj);
            return this;
        }
        public Sim run() {
            logger.info("Running");
            for (int t = 0; t <= timeSteps; t++) {
                logger.info("TimeStep: " + t);
                moveObjects(t);
                checkCollisions();
                displayObjects();
            }
            return sim;
        }

        private void checkCollisions() {
            if (env.getObjects().size() > 1) {
                for (int i = 0; i < env.getObjects().size(); i++) {
                    for (int j = i + 1; j < env.getObjects().size(); j++) {
                        if (sim.checkOverlap(env.getObject(i), env.getObject(j))) {
                            handleCollision(env.getObject(i), env.getObject(j));
                        }
                    }
                }
            }
        }

        private void handleCollision(Object obj1, Object obj2) {
            double mass1 = obj1.getMass();
            double mass2 = obj2.getMass();
            double vel1 = obj1.getVelocity();
            double vel2 = obj2.getVelocity();

            double v1f = (((2 * mass1) / (mass1 + mass2)) * vel1) - (((mass1 - mass2) / (mass1 + mass2)) * vel2);
            double v2f = (((mass1 - mass2) / (mass1 + mass2)) * vel1) + (((2 * mass2) / (mass1 + mass2)) * vel2);

            logger.info("A collision has occurred!");
            sim.collisionDetect = true;

            obj1.setVelocity(v1f);
            obj2.setVelocity(v2f);
        }

        private void displayObjects() {
            for (int i = 0; i < env.getObjects().size(); i++) {
                logger.info("Object " + (i + 1) + " has horizonal velocity: " + env.getObject(i).getVelocity() + " and position: " + env.getObject(i).getLocation());
            }
            sim.display(env, (int)env.getHeight(), (int)env.getWidth());
        }

        private void moveObjects(int timeStep) {
            for (int i = 0; i < env.getObjects().size(); i++) {
                double velocityX = 0;
                double posY = 0;

                if (env.getObject(i).getDirection() == Direction.LEFT || env.getObject(i).getDirection() == Direction.RIGHT)
                {
                    velocityX = env.getObject(i).getVelocity();
                    posY = env.getObject(i).getStartLocation().y - timeStep * env.getGravity();
                }
                else
                {
                    posY = env.getObject(i).getStartLocation().y + (env.getObject(i).getStartVelocity() * timeStep) - (0.5 * env.getGravity() * pow(timeStep, 2));
                }

                Point location = env.getObject(i).getLocation();
                location.x += velocityX;
                location.y = (int)posY;
            }
        }

        public Builder setRuntime(int desiredRunTime) {
            timeSteps = desiredRunTime;
            return this;
        }
    }
}
