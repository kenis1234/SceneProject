package unitTests;

//import org.junit.Assert;
//import org.junit.Test;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//import static org.junit.Assert.*;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import java.util.ArrayList;
import java.util.List;
import elements.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import primitives.*;


public class CameraTest {
    /*** Camera test ***/
    @Test
    public void testRaysConstruction(){
        final int WIDTH = 3;
        final int HEIGHT = 3;
        Point3D[][] screen = new Point3D [HEIGHT][WIDTH];
        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));
        System.out.println("Camera:\n" + camera);
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                Ray ray = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                screen[i][j] = ray.getHead();
                System.out.print(screen[i][j]);
                System.out.println(ray.getDirection());
// Checking z-coordinate
                assertTrue(Double.compare(screen[i][j].getCoordinate_z().get(), -1.0) == 0);
// Checking all options
                double x = screen[i][j].getCoordinate_y().get();
                double y = screen[i][j].getCoordinate_x().get();
                if (Double.compare(x, 3) == 0 ||
                        Double.compare(x, 0) == 0 ||
                        Double.compare(x, -3) == 0){
                    if (Double.compare(y, 3) == 0 ||
                            Double.compare(y, 0) == 0 ||
                            Double.compare(y, -3) == 0){
                        assertTrue(true);
                    }
                    else
                        fail("Wrong y coordinate");
                } else
                    fail("Wrong x coordinate");
            }
            System.out.println("---");
        }
    }

    /*@Test
    public void Test13() {

        final int WIDTH = 10;
        final int HEIGHT = 10;

        Point3D[][] screen = new Point3D[HEIGHT][WIDTH];

        Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0),
                new Vector(0.0, 1.0, 0.0),
                new Vector(0.0, 0.0, -1.0));

        System.out.println("Test13: Camera test:\n" + camera);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {

                Ray ray = camera.constructRayThroughPixel(WIDTH, HEIGHT, j + 1, i + 1, 1, 3 * WIDTH, 3 * HEIGHT);

                screen[i][j] = ray.getHead();

                System.out.printf("[%d,%d]=", i, j);
                System.out.print(screen[i][j]);
                System.out.println(ray.getDirection());


                         System.out.printf("x = %f ",screen[i][j].getCoordinate_x().get());
                         System.out.printf("y = %f ",screen[i][j].getCoordinate_y().get());
                         System.out.printf("z = %f\n",screen[i][j].getCoordinate_z().get());
                // Checking z-coordinate
                //assertTrue(Double.compare(screen[i][j].getCoordinate_z().get(), -1.0) == 0);

                // Checking all options
                double x = screen[i][j].getCoordinate_x().get();
                double y = screen[i][j].getCoordinate_y().get();

                double[] values = {1.5, 4.5, 7.5, 10.5, 13.5};

                int k = 0;
                for (; k < values.length; k++)
                    if (values[k] == Math.abs(x))
                        break;
                if (k == values.length);
//                    fail("Wrong X coordinate");

                k = 0;
                for (; k < values.length; k++)
                    if (values[k] == Math.abs(y))
                        break;
                if (k == values.length);
     //               fail("Wrong Y coordinate");
            }
            System.out.println("---");
        }

    }*/
    /*@Test
    public void rayToPixeltest() {
        try {
            Camera camera = new Camera(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0)), new Vector(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(1))), new Vector(new Point3D(new Coordinate(1), new Coordinate(0), new Coordinate(0))),3);
            Ray r1 = camera.rayToPixel(1300.5, 900.5);
            Vector v1 = (new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0))).subtractionVector(new Point3D(new Coordinate(3), new Coordinate(4), new Coordinate(3)));
            Ray r2 = camera.rayToPixel(-1300.5, -900.5);
            Vector v2 = (new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0))).subtractionVector(new Point3D(new Coordinate(-3), new Coordinate(-4), new Coordinate(3)));
            assertEquals(r1.getV(), v1.normalVec());
            assertEquals(r2.getV(), v2.normalVec());
        } catch (Exception e) {
            fail("exception thrown");
        }
    }*/
}