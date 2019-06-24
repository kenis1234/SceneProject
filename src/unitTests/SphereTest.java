package unitTests;

import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SphereTest {
    Sphere sphere=new Sphere(10,new Point3D(0,0,0));
    @Test
    void getNormal() {
        Vector vector=sphere.getNormal(new Point3D(10,0,0));
        assertEquals("Vector{head=Point3D{x=1.0, y=0.0, z=0.0}}",vector.toString());
    }
}
