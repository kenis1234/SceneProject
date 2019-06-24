package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {

    private Point3D location;
    private Vector vto;
    private Vector vRight;
    private Vector vUp;


    /*public Camera() {
        location=new Point3D(0,0,0);
        vto=new Vector(1,0,0);
        vRight=new Vector(0,-1,0);
        vUp=new Vector(0,0,1);
    }*/
    public Camera(Point3D p,Vector v1, Vector v2) {
        location=new Point3D(p);
        vto=new Vector(v1);
        vRight=new Vector(v1.crossProduct(v2));
        vUp=new Vector(v2);
        vto.normalize();
        vRight.normalize();
        vUp.normalize();
    }



    public Point3D getLocation() {
        return new Point3D(location);
    }
    public Vector getvto() {
        return new Vector(vto);
    }
    public Vector getvRight() {
        return new Vector(vRight);
    }
    public Vector getvUp() {
        return new Vector(vUp);
    }

    public Ray constructRayThroughPixel (double x, double y, double w,double h,int px,int py, double d){
        Point3D p0=location;
        Point3D pc=p0.add(getvto().mult(d));
        double rx=w/px;
        double ry=h/py;
        double tx=(x-px/2.0)*rx+rx/2.0;
        double ty=(y-py/2.0)*ry+ry/2.0;
        Vector vx= getvRight().mult(tx);
        Vector vy=getvUp().mult(ty*(-1));
        Point3D p=(pc.add(vx)).add(vy);
        Vector res=p.sub(location);
        res.normalize();
        return new Ray(res,location);
    }
}
