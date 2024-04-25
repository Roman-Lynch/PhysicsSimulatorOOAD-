import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Object {
    private double height;
    private double width;
    private Velocity velocity;
    private Velocity startVelocity;
    private double mass;
    private Point location;
    private Point startLocation;

    public Object(double height, double width, double xVelocity, double yVelocity, double mass, Point location) {
        this.height = height;
        this.width = width;
        this.velocity.setVelocity(xVelocity, yVelocity);
        this.startVelocity = new Velocity(xVelocity, yVelocity);
        this.mass = mass;
        this.location = location;
        this.startLocation = new Point(location.x, location.y);
    }

    public void setLocation(Point point) {
        location = point;
        if (startLocation == null)
            startLocation = new Point(location.x, location.y);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Velocity getStartVelocity() {
        return startVelocity;
    }

    public void setVelocity(double newX, double newY) {
        velocity.setVelocity(newX, newY);
    }

    public double getMass() {
        return mass;
    }

    public Point getLocation() {
        return location;
    }

    public Point getStartLocation() { return startLocation; }

    public boolean isValidObj() {
        return height != 0 && width != 0 && mass != 0;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private double height;
        private double width;
        private Velocity velocity;
        private double mass;
        private Point location;


        public Builder shape(double height, double width) {
            this.height = height;
            this.width = width;
            return this;
        }

        public Builder velocity(double xVelocity, double yVelocity) {
            this.velocity.setVelocity(xVelocity, yVelocity);
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder location(Point location) {
            this.location = location;
            return this;
        }

        public Object create() {
            Object obj = new Object(height, width, velocity, mass, location);
            return obj;
        }
    }
}
