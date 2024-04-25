import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Object {
    private double height;
    private double width;
    private Velocity velocity;
    private Velocity startVelocity;
    private double mass;
    private Location location;
    private Location startLocation;

    public Object(double height, double width, Velocity velocity, double mass, Location location) {
        this.height = height;
        this.width = width;
        this.velocity = velocity;
        this.startVelocity = new Velocity(velocity.getX(), velocity.getY());
        this.mass = mass;
        this.location = location;
        this.startLocation = new Location(location.getX(), location.getY());
    }

    public void setLocation(Location point) {
        location = point;
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

    public Location getLocation() {
        return location;
    }

    public Location getStartLocation() { return startLocation; }

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
        private Location location;


        public Builder shape(double height, double width) {
            this.height = height;
            this.width = width;
            return this;
        }

        public Builder velocity(Velocity velocity) {
            this.velocity.setVelocity(velocity.getX(), velocity.getY());
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
            return this;
        }

        public Object create() {
            return new Object(height, width, velocity, mass, location);
        }
    }
}
