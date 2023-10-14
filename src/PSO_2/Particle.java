package PSO_2;

import java.util.Random;

public class Particle {
    private double position;
    private double pBestValue;
    private double velocity;

    public Particle() {
        Random random = new Random();
        position =  random.nextDouble()*100;
        pBestValue = Double.POSITIVE_INFINITY;
        velocity = 0;
    }

    public void update() {
            position += velocity;

    }

    // getters and setters

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }





    public double getPBestValue() {
        return pBestValue;
    }

    public void setPBestValue(double pBestValue) {
        this.pBestValue = pBestValue;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}
