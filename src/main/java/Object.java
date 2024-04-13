import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Object {
    private double height;
    private double width;
    private double velocity;
    private double startVelocity;
    private double mass;
    private Point location;
    private Point startLocation;
    private Direction direction;

    public Object(double height, double width, double velocity, double mass, Point location, Direction direction) {
        this.height = height;
        this.width = width;
        this.velocity = velocity;
        this.startVelocity = velocity;
        this.mass = mass;
        this.location = location;
        this.startLocation = new Point(location.x, location.y);
        this.direction = direction;
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

    public double getVelocity() {
        return velocity;
    }

    public double getStartVelocity() {
        return startVelocity;
    }

    public void setVelocity(double newVel) { velocity = newVel; }

    public double getMass() {
        return mass;
    }

    public Point getLocation() {
        return location;
    }

    public Point getStartLocation() { return startLocation; }

    public Direction getDirection() {
        return direction;
    }

    public boolean isValidObj() {
        return height != 0 && width != 0 && velocity != 0 && mass != 0 && direction != null;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private double height;
        private double width;
        private double velocity;
        private double mass;
        private Point location;
        private Direction direction;

        public Builder shape(double height, double width) {
            this.height = height;
            this.width = width;
            return this;
        }

        public Builder velocity(double velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        public Builder location(Point location) {
            this.location = location;
            return this;
        }

        public Object create() {
            Object obj = new Object(height, width, velocity, mass, location, direction);
            return obj;
        }
    }
}
