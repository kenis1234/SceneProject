package unitTests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
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
}
