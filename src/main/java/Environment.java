public class Environment {
    protected double gravity;
    protected double height;
    protected double width;
    protected double wallElasticity;

    public Environment(double gravity, int height, int width, double wallElasticity)
    {
        this.gravity = gravity;
        this.height = height;
        this.width = width;
        this.wallElasticity = wallElasticity;
    }

    public double getGravity() { return gravity;}
    public double getHeight() { return height;}
    public double getWidth() { return width;}
    public double getWallElasticity() { return wallElasticity;}
}
