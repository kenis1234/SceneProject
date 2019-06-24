package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    private Point3D point;
    private Vector plumb;

    /********** Constructors ***********/
    public Plane(Point3D point, Vector plumb) {
        this.point = point;
        this.plumb = plumb;
    }
    public Plane(Point3D point1, Point3D point2, Point3D point3)
    {
        point=point1;
        plumb=(point1.sub(point2)).crossProduct(point1.sub(point3));
    }


    /************** Getters/Setters *******/
    public Point3D getPoint() {
        return point;
    }

    public Vector getPlumb() {
        return plumb;
    }

    /************** Operations ***************/
    @Override
    public Vector getNormal(Point3D point) {
        return new Vector(plumb.normalize());
    }
}
