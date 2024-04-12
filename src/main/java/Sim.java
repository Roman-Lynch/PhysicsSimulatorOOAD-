import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sim {

    private static final Logger logger = LoggerFactory.getLogger(Sim.class);

    public static Builder Builder(){
        return new Builder();
    }
//    public void display(Environment env, double x, double y){
//        for(int i = 0; i < y; i++) {
//            StringBuilder row = new StringBuilder();
//            for (int j = 0; j < x; j++) {
//                Point currentPoint = new Point(j, i);
//                if (env.getObjects().e.contains(currentPoint)) {
//                    row.append("*");
//                } else {
//                    row.append("+");
//                }
//            }
//            logger.info(row.toString());
//        }
//    }
    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder {
        public EnvironmentFactory eFactory = new EnvironmentFactory();
        private Sim sim = new Sim();
        private boolean elasticCollisions = true;
        private Environment env;


        private int runTime;

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

        public Builder addObjects(Object obj, int y_cord, int x_cord) {
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

        public Builder setRuntime(int desiredRunTime) {
            runTime = desiredRunTime;
            return this;
        }

        public Sim run() {

            double mass1 = env.getObject(0).getMass();
            double mass2 = env.getObject(1).getMass();
            double vel1 = env.getObject(0).getVelocity();
            double vel2 = env.getObject(1).getVelocity();

            double v2 = (2*mass1*vel1+vel2*(mass2-mass1))/(mass2+mass1);
            double v1 = (2*mass2*vel2+vel1*(mass1-mass2))/(mass2+mass1);

            logger.info("Velocity of object 1: " + v1 + ". Velocity of object 2: " + v2);

//            sim.display(env, env.getHeight(), env.getWidth());

            return sim;
        }

    }
}
