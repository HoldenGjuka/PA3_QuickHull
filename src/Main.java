import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArrayList<Point> points = new ArrayList<>();
        String fileName = "input1.txt";
        File file = new File(fileName);
        Scanner fileScan = null;
        try {
           fileScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        points = scanInput(fileScan);
        Collections.sort(points);
        //System.out.println(points.get(1).distanceFromLine(points.get(0), points.get(2)));
        findUpperHull(getMin(points), getMax(points), points);
    }

    public static void findUpperHull(Point p, Point q, ArrayList<Point> s){
        //check to see if there are any points not along this line, end if there aren't any
        s.remove(p);
        s.remove(q);
        for(int i = 0; i < s.size(); i++){
            if(s.get(i).distanceFromLine(p, q) == 0){
                System.out.println("Along the line: " + s.get(i).toString());
                s.remove(s.get(i));
            }
        }
        if(s.size() == 0){
            System.out.println("Recursion ends here!");
        }
        else {


        }

    }

    private static Point getMin(ArrayList<Point> points){
        Collections.sort(points);
        return points.get(0);
    }

    private static Point getMax(ArrayList<Point> points){
        Collections.sort(points);
        return points.get(points.size() - 1);
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
