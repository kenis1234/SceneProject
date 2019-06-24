package scene;
import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import primitives.*;

import java.util.Iterator;

public class Scene {
    private String name;
    private Color background;
    private AmbientLight ambient;
    private Geometries geometries;
    private Camera camera;
    private double distance;


    public Scene(String name) {
        this.name = name;
        this.geometries = new Geometries();
    }


    public String getName() {
        return new String(name);
    }
    public Color getBackground() {
        return new Color(background);
    }
    public AmbientLight getAmbient() {
        return ambient;
    }
    public Geometries getGeometries() {
        return geometries;
    }
    public Camera getCamera() {
        return camera;
    }
    public double getDistance() {
        return distance;
    }

    public void setBackground(Color background) {
        this.background = background;
    }
    public void setAmbient(AmbientLight ambient) {
        this.ambient = ambient;
    }
    public void setCameraAndDistance(Camera camera, double distance) {
        this.camera = camera;
        this.distance = distance;
    }

    public void addGeometry(Geometry g){
        getGeometries().add(g);
    }

    public Iterator<Geometry> getGeometriesIterator(){
        return geometries.getGeometries().iterator();
    }
}
