//bs"d
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class Sphere extends RadialGeometry  implements Geometry, Intersectable{
    private Point3D center;

    /********** Constructors ***********/
    public Sphere(double radius, Point3D point) {
        super(radius);
        this.center = point;
    }


    /************** Getters/Setters *******/
    public Point3D getPoint() {
        return center;
    }

    /************** Operations ***************/
    @Override
    public Vector getNormal(Point3D point) {
        return point.sub(center).normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray)
    {
        List<Point3D> list=new ArrayList<Point3D>();
        Vector L = center.sub(ray.getHead());
        double tm = L.dotProduct(ray.getDirection());
        double d = pow(L.dotProduct(L)+tm*tm,0.5);

        if(d <= this.getRadius())
            return list;
        return null;

    }
}
