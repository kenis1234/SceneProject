package unitTests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PlaneTest {
    Plane plane=new Plane(new Point3D(1,0,0),new Point3D(1,1,1),new Point3D(1,1,0));
    @Test
    void getNormal() {
        Vector vector = plane.getNormal(new Point3D(10, 0, 0));
        assertEquals("Vector{head=Point3D{x=0.0, y=0.0, z=0.0}}", vector.toString());
    }

    @Test
    void func1() {
        Plane p=new Plane(new Point3D(100, 0,0),new Vector(-1,0,0));
        Vector v=new Vector(1,2,0);
        Point3D point3D=new Point3D(0,0,0);
        Point3D point=p.findIntersections(new Ray(v,point3D)).get(0).point;
        assertEquals(200, point.getCoordinate_y().get());
    }
}
