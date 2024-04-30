import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;

public class GUI extends JFrame implements IObservable{
    private CirclePanel circlePanel;
    private ArrayList<Circle> circles;
    JFrame frame = new JFrame();
    AudibleObserver observer = new AudibleObserver(null, null, 0);

    public GUI(Environment enviro, ArrayList<Location> objectPositions, ArrayList<Boolean> CollisionCheck, ArrayList<Velocity> objectVelocities, double timeDelay, double duration, Boolean showVelocity, Boolean speak) {
        // Add 5 seconds to the duration
        duration += 5;
        circles = new ArrayList<>();
        EventBus.getInstance().attach(observer,EventType.COLLISION);
        // Find number of objects in environment
        int numObs = enviro.getObjects().size();
        // Put objects with initial positions in
        for (Object object : enviro.getObjects()) {
            circles.add(new Circle(((int) (object.getLocation().getX()) * 10), -(int) (object.getLocation().getY() * 10), (int) (object.getRadius() * 10), object.getColor()));
        }

        circlePanel = new CirclePanel(circles, showVelocity);

        // Set preferred size of the circlePanel to the size of the environment
        circlePanel.setPreferredSize(new Dimension((int)(enviro.getWidth()*10), (int)(enviro.getHeight())*10));

        frame.add(circlePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PHYS SIM");
        frame.setResizable(false); // Disable frame resizing
        frame.setVisible(true);
        frame.pack();

        Timer closeTimer = new Timer();
        closeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.dispose();
            }
        }, ((int)(duration)*1000));

        Timer timer = new Timer();
        // Run the sim, updating the circles positions each time
        for (int i = 0; i < objectPositions.size(); i+=numObs){
            ArrayList<Location> newLocations = new ArrayList<Location>();
            ArrayList<Velocity> newVelocities = new ArrayList<Velocity>();

            // Build a list contain numObs new locations
            for (int x = i; x<(i + numObs); x++){
                newLocations.add(objectPositions.get(x));
                newVelocities.add(objectVelocities.get(x));
            }

            if (CollisionCheck.get(i) && speak) {
                EventBus.getInstance().postMessage(EventType.COLLISION, "BONK");
            }

            for (int steps = 0; steps < 100; steps ++) {
                try {
                    Thread.sleep((long) (timeDelay * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateCirclePositions(newLocations,newVelocities);
        }
    }

    public void updateCirclePositions(ArrayList<Location> newLocations, ArrayList<Velocity> velocities) {
        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);
            Location newLocation = newLocations.get(i);
            circle.setX((int)(newLocation.getX() * 10));
            circle.setY(-(int)(newLocation.getY() * 10));
            circle.setVelocity(velocities.get(i)); // Set the velocity
        }
        circlePanel.repaint();
    }

    public CirclePanel getCirclePanel() {
        return circlePanel;
    }

    @Override
    public void subscribe(IObserver observer, List<EventType> interestedEvents) {
        for (EventType event : interestedEvents) {
            EventBus.getInstance().attach(observer, event);
        }
    }
}

class CirclePanel extends JPanel {
    private Boolean showVel = false;
    private ArrayList<Circle> circles;

    public CirclePanel(ArrayList<Circle> circles, Boolean showVelocity) {
        this.circles = circles;
        this.showVel = showVelocity;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle circle : circles) {
            int size = (int)(circle.getRadius()*2);
            int x = circle.getX() - (size/2);
            int y = circle.getY() - (size/2);
            g.setColor(circle.getColor());
            g.fillOval(x, y, size, size);
            g.setColor(Color.BLACK); // Set the color for velocity text
            if (showVel) {
                DecimalFormat df = new DecimalFormat("#." + "0".repeat(2)); // Create DecimalFormat with specified precision
                String roundedXVelocity = df.format(circle.getVelocity().getX());
                String roundedYVelocity = df.format(circle.getVelocity().getY());
                g.drawString("Velocity: " + roundedXVelocity + ", " + roundedYVelocity, circle.getX(), circle.getY() + circle.getRadius() + 12);
            }
        }
    }

    public void setCircles(ArrayList<Circle> circles) {
        this.circles = circles;
        repaint(); // Repaint the panel when circles are updated
    }
}

class Circle {
    private int x;
    private int y;
    private int radius;
    private Color color;
    private Velocity velocity;

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.color = color;
        this.velocity = velocity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public void setX(int xCord) {
        this.x = xCord;
    }

    public void setY(int yCord) {
        this.y = yCord;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

}
