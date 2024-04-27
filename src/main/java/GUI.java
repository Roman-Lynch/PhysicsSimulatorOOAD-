import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame {
    private CirclePanel circlePanel;
    private ArrayList<Circle> circles;
    public GUI() {
        JFrame frame = new JFrame();

        circles = new ArrayList<>();
        circles.add(new Circle(100, 100, 50, Color.RED)); // Example circle
        circles.add(new Circle(200, 200, 70, Color.BLUE)); // Another example circle
        circles.add(new Circle(300, 300, 30, Color.GREEN)); // Yet another example circle

        circlePanel = new CirclePanel(circles);

        // Set preferred size of the circlePanel to 500x500
        circlePanel.setPreferredSize(new Dimension(500, 500));

        frame.add(circlePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PHYS SIM");
        frame.setResizable(false); // Disable frame resizing
        frame.pack();
        frame.setVisible(true);
    }

    public GUI(Environment enviro) {
        JFrame frame = new JFrame();

        circles = new ArrayList<>();

        for (Object object : enviro.getObjects()){
            circles.add(new Circle((int)object.getLocation().getX(), (int)object.getLocation().getY(), (int)object.getRadius(), Color.RED));
        }

        circlePanel = new CirclePanel(circles);

        // Set preferred size of the circlePanel to 500x500
        circlePanel.setPreferredSize(new Dimension((int)enviro.getWidth(), (int)enviro.getHeight()));

        frame.add(circlePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PHYS SIM");
        frame.setResizable(false); // Disable frame resizing
        frame.pack();
        frame.setVisible(true);

        Timer closeTimer = new Timer();
        closeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                frame.dispose();
            }
        }, 10000); // Close after 10 seconds
    }

    public void updateCirclePositions(Location location, Location newLocation) {
        // Update position of each circle
        for (Circle circle : circles) {
            int dx = (int)(newLocation.getX() - location.getX());
            int dy = (int) (newLocation.getY() - location.getY());
            circle.move(dx, dy);
        }
        // Repaint the panel to reflect the updated positions
        circlePanel.repaint();
    }

    public static void main(String[] args) {

        GUI gui = new GUI();

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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500); // Set preferred size of the panel
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

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
