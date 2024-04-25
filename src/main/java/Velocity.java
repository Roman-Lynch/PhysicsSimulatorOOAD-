public class Velocity {

    private double x;
    private double y;

    public Velocity(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setVelocity(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getXVelocity(){
        return x;
    }

    public double getYVelocity(){
        return y;
    }

}
