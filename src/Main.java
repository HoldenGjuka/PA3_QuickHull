import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArrayList<Point> points = new ArrayList<>();
        String fileName = "input3.txt";
        File file = new File(fileName);
        Scanner fileScan = null;
        try {
           fileScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        points = scanInput(fileScan);
        Point a = new Point(1, 1);
        Point b = new Point(3, 3);
        Point c = new Point(10, 0);
        System.out.println(c.distanceFromLine(a, b));
    }

    public static void findUpperHull(Point p, Point q, ArrayList<Point> s){
        //complete this
    }

    private static void printPoints(ArrayList<Point> points){
        for (Point point : points) {
            System.out.println(point.toString());
        }
    }

    private static ArrayList<Point> scanInput(Scanner fileScan){
        ArrayList<Point> points = new ArrayList<>();
        int quantity = fileScan.nextInt();
        fileScan.nextLine();
        for(int i = 0; i < quantity; i++){
            String line = fileScan.nextLine();
            Scanner lineScan = new Scanner(line);
            Point point = new Point(Double.parseDouble(lineScan.next()), Double.parseDouble(lineScan.next()));
            points.add(point);
        }
        return points;
    }
}
