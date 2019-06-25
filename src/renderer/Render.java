package renderer;
import java.awt.*;
import elements.Camera;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;


import static geometries.Intersectable.GeoPoint;
import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Render {
    private ImageWriter imageWriter;
    private Scene scene;

    public Render(ImageWriter i, Scene s){
        imageWriter=i;
        scene=s;
    }

    private void renderImage(){
        for(int x=0;x<imageWriter.getNx();x++)
            for(int y=0;y<imageWriter.getNy();y++) {
                Ray ray=scene.getCamera().constructRayThroughPixel(x,y,imageWriter.getWidth(),imageWriter.getHeight(),imageWriter.getNx(),imageWriter.getNy(),scene.getDistance());
                List<GeoPoint> intersectionPoints=getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    imageWriter.writePixel(x,y,scene.getBackground());
                else
                {
                    GeoPoint closestPoint=getClosestPoint(intersectionPoints);
                    imageWriter.writePixel(x,y,calcColor(closestPoint));
                }

            }

    }


    private List<GeoPoint> getSceneRayIntersections(Ray ray) {
        Iterator<Geometry> geometries=scene.getGeometriesIterator();
        List<GeoPoint> intersecrionsPoints=new ArrayList<GeoPoint>();
        while(geometries.hasNext())
        {
            Geometry geometry=geometries.next();
            List<GeoPoint> geometryIntersectionPoints=geometry.findIntersections(ray);
            intersecrionsPoints.addAll(geometryIntersectionPoints);
        }
        return intersecrionsPoints;
    }
    private static final double MIN_CALC_COLOR_K = 0.001;

    private Color calcColor(GeoPoint geopoint) {
        Color color = scene.getAmbient().getIntensity(new Point3D(0, 0, 0));
        color = add(color, geopoint.geometry.getEmission());
        Vector v = geopoint.point.sub(scene.getCamera().getLocation()).normalize();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        int nShininess = geopoint.geometry.getMaterial().getnShininess();
        double kd = geopoint.geometry.getMaterial().getkD();
        double ks = geopoint.geometry.getMaterial().getkS();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geopoint.point);
            if ((n.dotProduct(l) * n.dotProduct(v) > 0)&&(unshaded(l, n, geopoint) )) {
                Color lightIntensity = lightSource.getIntensity(geopoint.point);
                color=add(add(color,calcDiffusive(kd, l, n, lightIntensity)),calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }
    private static final double EPS = 1.0;
    private boolean unshaded(Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.mult(-1); // from point to light source
        Vector epsVector = n.mult(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);
        Point3D point = geopoint.point.add(epsVector);
        Ray lightRay = new Ray(lightDirection,point);
        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        return intersections.isEmpty();
    }

    private void printGrid(int interval)
    {
        Color white=new Color(255,255,255);
        int px=imageWriter.getNx();
        int py=imageWriter.getNy();
        for(int i=0;i<px;i+=interval)
        {
            for (int j=0;j<py;j+=interval)
            {
                imageWriter.writePixel(i,j,white);
            }
        }
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        l.normalize();
        n.normalize();
        double k=kd*(abs(l.dotProduct(n)));
        return new Color((int)(lightIntensity.getRGB()*k));
    }



    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        v.normalize();
        n.normalize();
        l.normalize();
        Vector n1=new Vector(n);
        Vector r=new Vector(l.sub(n1.mult(2*(l.dotProduct(n1)))));
        Vector v1=new Vector(v);
        v1.mult(-1);
        double k=max(0,ks*(pow(v1.dotProduct(r),nShininess)));
        return new Color((int)(lightIntensity.getRGB()*k));
    }



    private GeoPoint getClosestPoint(List<GeoPoint> points){
       double distance=Double.MAX_VALUE;
       Point3D P0=scene.getCamera().getLocation();
        GeoPoint minDistancePoint=null;

       for(GeoPoint geoPoint: points)
           if(P0.distance(geoPoint.point)<distance)
           {
               minDistancePoint=new GeoPoint(geoPoint);
               distance=P0.distance(geoPoint.point);
           }

        return minDistancePoint;
    }
    private Color add (Color a,Color b){
        int r=min(255,a.getRed()+b.getRed());
        int g=min(255,a.getGreen()+b.getGreen());
        int bl=min(255,a.getBlue()+b.getBlue());
        return new Color(r,g,bl);
    }
}
