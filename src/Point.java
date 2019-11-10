public class Point implements Comparable {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String toString(){
        return "(" + String.format("%.3f", this.x) + "," + String.format("%.3f", this.y) + ")";
    }

    @Override
    public int compareTo(Object o) {
        Point p = (Point) o;
        if(this.x < p.getX()){
            return -1;
        } else if (this.x == p.getX()){
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Computes the distance between this and another Point.
     * @param p - Second Point.
     * @return - The distance between the points.
     */
    public double computeDistance(Point p) {
        double xDist = Math.abs(Math.pow(this.x - p.x, 2));
        double yDist = Math.abs(Math.pow(this.y - p.y, 2));
        return (Math.sqrt(xDist + yDist));
    }

    /**
     * Computes this Point's distance from a line made of two Points.
     * @param g - First line Point.
     * @param h - Second line Point.
     * @return - Distance of this Point from the line.
     */
    public double distanceFromLine(Point g, Point h){
        double xDif = h.getX() - g.getX();
        double yDif = h.getY() - g.getY();
        double numerator = Math.abs((yDif * this.getX()) - (xDif * this.y) + h.getX()*g.getY() - h.getY()*g.getX());
        double denominator = Math.sqrt(Math.pow(yDif, 2) + Math.pow(xDif, 2));
        return numerator/denominator;
    }

}
