//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.awt.*;

public class Object
{
    double height;
    double width;
    double velocity;
    double mass;
    Point location;
    Direction direction;

    public Object(double height, double width, double velocity, double mass, Point location, Direction direction)
    {
        this.height = height;
        this.width = width;
        this.velocity = velocity;
        this.mass = mass;
        this.location = location;
        this.direction = direction;
    }

    public void setLocation(Point point)
    {
        location = point;
    }

    public double getHeight()
    {
        return height;
    }

    public double getWidth()
    {
        return width;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public double getMass()
    {
        return mass;
    }

    public Point getLocation()
    {
        return location;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public boolean isValidObj()
    {
        return height != 0 && width != 0 && velocity != 0 && mass != 0 && direction != null;
    }

    public static Builder newBuilder()
    {
        return new Builder();
    }

    public static class Builder
    {
        double height;
        double width;
        double velocity;
        double mass;
        Point location;
        Direction direction;

        public Builder shape(double height, double width)
        {
            this.height = height;
            this.width = width;
            return this;
        }
        public Builder velocity(double velocity)
        {
            this.velocity = velocity;
            return this;
        }
        public Builder mass(double mass)
        {
            this.mass = mass;
            return this;
        }
        public Builder direction(Direction direction)
        {
            this.direction = direction;
            return this;
        }
        public Builder location(Point location)
        {
            this.location = location;
            return this;
        }
        public Object create()
        {
            Object obj = new Object(height, width, velocity, mass, location, direction);
            return obj;
        }
    }
}
