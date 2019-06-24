package renderer;
import java.awt.*;
import elements.Camera;
import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

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
                List<Point3D> intersectionPoints=getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    imageWriter.writePixel(x,y,scene.getBackground());
                else
                {
                    Point3D closestPoint=getClosestPoint(intersectionPoints);
                    imageWriter.writePixel(x,y,calcColor(closestPoint));
                }

            }

    }


    private List<Point3D> getSceneRayIntersections(Ray ray) {
        Iterator<Geometry> geometries=scene.getGeometriesIterator();
        List<Point3D> intersecrionsPoints=new ArrayList<Point3D>();
        while(geometries.hasNext())
        {
            Geometry geometry=geometries.next();
            List<Point3D> geometryIntersectionPoints=geometry.findIntersections(ray);
            intersecrionsPoints.addAll(geometryIntersectionPoints);
        }
        return intersecrionsPoints;
    }

    private Color calcColor(Point3D point){
        return scene.getAmbient().getIntensity();
    }

    private Point3D getClosestPoint(List<Point3D> points){
       double distance=Double.MAX_VALUE;
       Point3D P0=scene.getCamera().getLocation();
       Point3D minDistancePoint=null;

       for(Point3D point: points)
           if(P0.distance(point)<distance)
           {
               minDistancePoint=new Point3D(point);
               distance=P0.distance(point);
           }

        return minDistancePoint;
    }
}
