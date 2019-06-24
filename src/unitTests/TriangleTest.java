package unitTests;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

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
        assertEquals("Vector{head=Point3D{x=-0.5773502691896258, y=-0.5773502691896258, z=-0.5773502691896258}}",v1.toString());
    }
    @Test
    void getLengthNormal()
    {
        Vector v2 = triangle.getNormal(null);
        double  l = v2.size();
        assertEquals(1.0,l);
    }
}
