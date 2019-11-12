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
        Point xMin = getMin(points);
        Point xMax = getMax(points);
        points = removePointsBelowLine(xMin, xMax, points);
        findUpperHull(getMin(points), getMax(points), points);
        findUpperHull(getMax(points), getMin(points), points);
    }

    /**
     * Recursively finds and prints the convex upper hull of a set of points.
     * @param p - First point in the separating line.
     * @param q - Second point in the separating line.
     * @param s - Set of points to hull in.
     */
    private static void findUpperHull(Point p, Point q, ArrayList<Point> s){
        for(int i = 0; i < s.size(); i++){
            Point a = s.get(i);
            if(a.distanceFromLine(p, q) == 0){
                System.out.println(a.toString());
            }
        }
        if(s.size() != 0){
            Point pointMax = furthestFromLine(p, q, s);
            System.out.println("New Hull Point: " + pointMax.toString());
            s.remove(pointMax);
            ArrayList<Point> s1 = new ArrayList<>();
            for (int i = 0; i < s.size(); i++) {
                if(s.get(i).isAboveLine(p, pointMax)){
                    s1.add(s.get(i));
                }
            }
            ArrayList<Point> s2 = new ArrayList<>();
            for (int i = 0; i < s.size(); i++) {
                if(s.get(i).isAboveLine(pointMax, q)){
                    s2.add(s.get(i));
                }
            }
            findUpperHull(p, pointMax, s1);
            findUpperHull(pointMax, q, s2);
        }
    }

    private static ArrayList<Point> removePointsBelowLine(Point a, Point b, ArrayList<Point> points){
        for (Point p : new ArrayList<>(points)) {
            if (p.isBelowLine(a, b)) {
                points.remove(p);
            }
        }
        return points;
    }

    /**
     * Finds the Point object furthest from the line.
     * @param p - First Point of the line.
     * @param q - Second Point of the line.
     * @param points - An ArrayList of Point objects.
     * @return - Point furthest from the line.
     */
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

    /**
     * Finds the Point with the smallest X-coordinate value.
     * @param points - An ArrayList of Point objects.
     * @return - Point with the smallest X-coordinate value.
     */
    private static Point getMin(ArrayList<Point> points){
        Collections.sort(points);
        return points.get(0);
    }

    /**
     * Finds the Point with the largest X-coordinate value.
     * @param points - An ArrayList of Point objects.
     * @return - Point with the largest X-coordinate value.
     */
    private static Point getMax(ArrayList<Point> points){
        Collections.sort(points);
        return points.get(points.size() - 1);
    }

    /**
     * Prints all the points in the format specified by the assignment instructions.
     * @param points - An ArrayList of Point objects.
     */
    private static void printPoints(ArrayList<Point> points){
        for (Point point : points) {
            System.out.println(point.toString());
        }
    }

    /**
     * Takes a file scanner and turns the file into an ArrayList of Point objects.
     * @param fileScan - Scanner holding an input file.
     * @return - ArrayList of Point objects.
     */
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
