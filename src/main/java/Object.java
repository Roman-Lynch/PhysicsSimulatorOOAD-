import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Object {

    private double radius;
    private Velocity velocity;

    private List<Object> stuckObjects = new ArrayList<>();
    private double mass;
    private Location location;


    public Object(double radius, Velocity velocity, double mass, Location location) {
        this.velocity = velocity;

        this.mass = mass;
        this.location = location;

        this.radius = radius;
    }

    public List<Object> getStuckObjects() {
        return stuckObjects;
    }

    public Object getStuckObject(int index) {
        return stuckObjects.get(index);
    }

    public void addStuckObject(Object obj) {
        stuckObjects.add(obj);
    }

    public void setLocation(Location point) {
        location = point;
    }

    public void setMass(double mass){
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public Velocity getVelocity() {
        return velocity;
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
