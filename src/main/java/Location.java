public class Location {
    private double x;
    private double y;

    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getXLocation(){
        return x;
    }

    public double getYLocation(){
        return y;
    }
}
