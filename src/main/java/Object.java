import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Object {

    private double radius;
    private Velocity velocity;
    private Velocity startVelocity;
    private double mass;
    private Location location;
    private Location startLocation;

    public Object(double radius, Velocity velocity, double mass, Location location) {
        this.velocity = velocity;
        this.startVelocity = new Velocity(velocity.getX(), velocity.getY());
        this.mass = mass;
        this.location = location;
        this.startLocation = new Location(location.getX(), location.getY());
        this.radius = radius;
    }

    public void setLocation(Location point) {
        location = point;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double r){
        this.radius = r;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public Velocity getStartVelocity() {
        return startVelocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public Location getLocation() {
        return location;
    }

    public Location getStartLocation() { return startLocation; }

    public boolean isValidObj() {
        return radius != 0 && mass != 0;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private double radius;
        private Velocity velocity;
        private double mass;
        private Location location;


        public Builder radius(double r) {
            this.radius = r;
            return this;
        }

        public Builder velocity(Velocity velocity) {
            this.velocity = velocity;
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
            return new Object(radius, velocity, mass, location);
        }
    }
}
