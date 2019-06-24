package geometries;

public abstract class RadialGeometry {
    private double radius;

    /********** Constructors ***********/
    public RadialGeometry(RadialGeometry radialGeometry) {
        this.radius = radialGeometry.getRadius();
    }


    /************** Getters/Setters *******/
    public double getRadius() {
        return radius;
    }

    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
