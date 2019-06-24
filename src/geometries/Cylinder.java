package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube  implements Geometry{
    private double height;

    /********** Constructors ***********/
    public Cylinder(double radius, Vector vector, Point3D point, double height) {
        super(radius, vector, point);
        this.height = height;
    }
    public Cylinder(double radius, Ray ray, double height) {
        super(radius, ray.getDirection(), ray.getHead());
        this.height = height;
    }

    /************** Getters/Setters *******/
    public double getHeight() {
        return height;
    }

    /************** Operations ***************/
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }
}
