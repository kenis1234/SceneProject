package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Plane implements Geometry{

    private Point3D point1;
    private Point3D point2;
    private Point3D point3;


    /********** Constructors ***********/
    public Triangle(Point3D point1, Point3D point2, Point3D point3) {
        super(point1, point2, point3);
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }


    /************** Getters/Setters *******/
    public Point3D getPoint1() {
        return point1;
    }

    public Point3D getPoint2() {
        return point2;
    }

    public Point3D getPoint3() {
        return point3;
    }

    /************** Operations ***************/
    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }
}
