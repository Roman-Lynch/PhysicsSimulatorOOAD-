import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame {
    private CirclePanel circlePanel;
    private ArrayList<Circle> circles;

    JFrame frame = new JFrame();

    public GUI(Environment enviro, ArrayList<Location> objectPositions, long timeDelay) {

        circles = new ArrayList<>();

        // Find number of objects in environment
        int numObs = enviro.getObjects().size();
        // Put objects with initial positions in
        for (Object object : enviro.getObjects()){
            circles.add(new Circle(((int)object.getLocation().getX()*10), -((int)object.getLocation().getY()*10), ((int)object.getRadius()*10), Color.RED));
        }

        circlePanel = new CirclePanel(circles);

        // Set preferred size of the circlePanel to the size of the environment
        circlePanel.setPreferredSize(new Dimension(((int)enviro.getWidth()*10), ((int)enviro.getHeight())*10));

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
        }, 10000); // Close after 10 seconds

        Timer timer = new Timer();
        // Run the sim, updating the circles positions each time
        for (int i = 0; i < objectPositions.size(); i+=numObs){
            ArrayList<Location> newLocations = new ArrayList<Location>();

            // Build a list contain numObs new locations
            for (int x = i; x<(i + numObs); x++){
                newLocations.add(objectPositions.get(x));
            }

            updateCirclePositions(newLocations);
            try {
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
                Thread.sleep(timeDelay*1000000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCirclePositions(ArrayList<Location> newLocations) {
        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);
            Location newLocation = newLocations.get(i);
            circle.setX((int)newLocation.getX()*10);
            circle.setY(-(int)newLocation.getY()*10);
        }
        circlePanel.repaint();
    }

    public static void main(String[] args) {

        int height = 40;
        int width = 50;

        double mass1 = 1; // kg
        Location location1 = new Location(20, -20); // meters
        Velocity velocity1 = new Velocity(-10, 0);

        Object objOne = Object.newBuilder()
                .radius(2)
                .mass(mass1)
                .velocity(velocity1)
                .location(location1)
                .create();

        Sim runSim = Sim.newBuilder()
                .createAndAddMars(height, width,1)
                .addObjects(objOne)
                .setTimeSteps(0.0001)
                .setDuration(7)
                .run()
                .executeGUI();

        //GUI gui = new GUI();

        // Start a timer to update circle positions
        //Timer timer = new Timer(50, e -> gui.updateCirclePositions());
        //timer.start();;
    }

    public CirclePanel getCirclePanel() {
        return circlePanel;
    }
}

class CirclePanel extends JPanel {
    private ArrayList<Circle> circles;

    public CirclePanel(ArrayList<Circle> circles) {
        this.circles = circles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle circle : circles) {
            g.setColor(circle.getColor());
            g.fillOval(circle.getX(), circle.getY(), circle.getRadius(), circle.getRadius());
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

    public Circle(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
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

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
