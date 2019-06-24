package elements;

import primitives.Color;

public class AmbientLight {
    private Color color;
    private double ka;
    private Color intensity;


    public AmbientLight(Color c, double k){
        color=c;
        ka=k;
        intensity=GetIntensity();
    }

    public Color getIntensity() {
        return intensity;
    }

    private Color GetIntensity(){
        return color.scale(ka);
    }
}
