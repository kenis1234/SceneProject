package unitTests;

import geometries.Intersectable;

import elements.Camera;
import geometries.Intersectable;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleTest {
    Triangle triangle = new Triangle(
            new Point3D(0.0,1.0,0.0),
            new Point3D(1.0,0.0,0.0),
            new Point3D(0.0,0.0,1.0));
    @Test
    void getNormal() {
        Vector v1=triangle.getNormal(null);
        assertEquals("Vector{head=Point3D{x=-0.5773502691896257, y=-0.5773502691896257, z=-0.5773502691896257}}",v1.toString());
    }
    @Test
    void getLengthNormal()
    {
        Vector v2 = triangle.getNormal(null);
        double  l = v2.size();
        assertEquals(1.0,l);
    }


        @Test
        public void findIntersections()
        {
            /*** Triangle test ***/

            final int WIDTH = 3;
            final int HEIGHT = 3;
            Ray[][] rays = new Ray [HEIGHT][WIDTH];
            Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                    new Vector (0.0, 1.0, 0.0),
                    new Vector (0.0, 0.0, -1.0));
            Triangle triangle = new Triangle(new Point3D( 0, 1, -2),
                    new Point3D( 1, -1, -2),
                    new Point3D(-1, -1, -2));
            Triangle triangle2 = new Triangle(new Point3D( 0, 10, -2),
                    new Point3D( 1, -1, -2),
                    new Point3D(-1, -1, -2));
            List<Point3D> intersectionPointsTriangle = new ArrayList<Point3D>();
            List<Point3D> intersectionPointsTriangle2 = new ArrayList<Point3D>();
            System.out.println("Camera:\n" + camera);
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                    rays[i][j] = camera.constructRayThroughPixel
                            (WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                    List<Intersectable.GeoPoint> rayIntersectionPoints = triangle. findIntersections(rays[i][j]);
                    List<Intersectable.GeoPoint> rayIntersectionPoints2 = triangle2.findIntersections(rays[i][j]);
                    for (Intersectable.GeoPoint iPoint: rayIntersectionPoints)
                        intersectionPointsTriangle.add(iPoint.point);
                    for (Intersectable.GeoPoint iPoint: rayIntersectionPoints2)
                        intersectionPointsTriangle2.add(iPoint.point);
                }
            }
            assertTrue(intersectionPointsTriangle. size() == 1);
            assertTrue(intersectionPointsTriangle2.size() == 2);
            System.out.println("Intersection Points:");
            for (Point3D iPoint: intersectionPointsTriangle)
                System.out.println(iPoint);
            System.out.println("--");
            for (Point3D iPoint: intersectionPointsTriangle2)
                System.out.println(iPoint);
        }


        @Test
        public void findIntersections1()
        {

            final int WIDTH = 500;
            final int HEIGHT = 500;
            Ray[][] rays = new Ray [HEIGHT][WIDTH];
            Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                    new Vector (0.0, 1.0, 0.0),
                    new Vector (0.0, 0.0, -1.0));
            Triangle triangle = new Triangle(new Point3D(100, 0, -49),
                    new Point3D(0, 100, -49),
                    new Point3D(100, 100, -49));
            Triangle triangle2 = new Triangle(new Point3D(-100, 0, -49),
                    new Point3D(0, 100, -49),
                    new Point3D(-100, 100, -49));
            triangle2.setEmission(new Color(80,255,255));

            Triangle triangle3 = new Triangle(new Point3D(100, 0, -49),
                    new Point3D(0, -100, -49),
                    new Point3D(100, -100, -49));
            triangle3.setEmission(new Color(90,45,125));

            Triangle triangle4 = new Triangle(new Point3D(-100, 0, -49),
                    new Point3D(0, -100, -49),
                    new Point3D(-100, -100, -49));
            triangle4.setEmission(new Color(215,45,34));
            List<Point3D> intersectionPointsTriangle = new ArrayList<Point3D>();
            List<Point3D> intersectionPointsTriangle2 = new ArrayList<Point3D>();
            System.out.println("Camera:\n" + camera);
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                    rays[i][j] = camera.constructRayThroughPixel
                            (WIDTH, HEIGHT, j, i, 50, 500*3, 500*3);
                    List<Intersectable.GeoPoint> rayIntersectionPoints = triangle. findIntersections(rays[i][j]);
                    List<Intersectable.GeoPoint> rayIntersectionPoints2 = triangle2.findIntersections(rays[i][j]);
                    for (Intersectable.GeoPoint iPoint: rayIntersectionPoints)
                        intersectionPointsTriangle.add(iPoint.point);
                    for (Intersectable.GeoPoint iPoint: rayIntersectionPoints2)
                        intersectionPointsTriangle2.add(iPoint.point);
                }
            }

            //    System.out.println("Intersection Points:");
            for (Point3D iPoint: intersectionPointsTriangle)
                System.out.println(iPoint);
            System.out.println("--");
            for (Point3D iPoint: intersectionPointsTriangle2)
                System.out.println(iPoint);
        }


}
