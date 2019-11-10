/*
Name: Holden Gjuka
Assignment: Program 3 - QuickHull
Course/Semester: CS 371 Spring Semester
Class Section: 01
Lab section: None, Programming Assignment
Sources consulted: https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line, and the course textbook.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ArrayList<Point> points = new ArrayList<>();
        String fileName = "input2.txt";
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
        System.out.println("MinPoint: " + getMin(points));
        System.out.println("MaxPoint: " + getMax(points));
        findUpperHull(getMin(points), getMax(points), points);
    }

    private static void findUpperHull(Point p, Point q, ArrayList<Point> s){
        //check to see if there are any points not along this line, end if there aren't any
        s.remove(p);
        s.remove(q);
        for(int i = 0; i < s.size(); i++){
            Point a = s.get(i);
            if(a.distanceFromLine(p, q) == 0){
                System.out.println("Along the line: " + a.toString());
                s.remove(a);
            }
            if(s.contains(a) && a.isBelowLine(p, q)){
                s.remove(a);
                System.out.println("Removed an inferior point. Size is: " + s.size());
            }
        }
        if(s.size() == 0){
            System.out.println("Recursion ends here!");
        } else {
            Point pointMax = furthestFromLine(p, q, s);
            System.out.println("New Hull Point: " + pointMax.toString());
            s.remove(pointMax);
            ArrayList<Point> s1 = new ArrayList<>();
            for(Point i : s){
                if(!i.isAboveLine(p, pointMax)){
                    s1.add(i);
                }
            }
            ArrayList<Point> s2 = new ArrayList<>();
            for(Point i : s){
                if(!i.isAboveLine(pointMax, q)){
                    s2.add(i);
                }
            }
            findUpperHull(p, pointMax, s1);
            findUpperHull(pointMax, q, s2);
        }
    }

    private static Point furthestFromLine(Point p, Point q, ArrayList<Point> points){
        double minDistance = points.get(0).distanceFromLine(p, q);
        Point furthestPoint = points.get(0);
        for(int i = 0; i < points.size(); i++){
            if(points.get(i).distanceFromLine(p, q) < minDistance) {
                furthestPoint = points.get(i);
            }
        }
        return furthestPoint;
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
