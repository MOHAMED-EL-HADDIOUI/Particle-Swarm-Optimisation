package PSO;

import java.util.Random;

import static java.lang.Math.abs;

public class Particle {
    private double[] position;
    private double[] pBestPosition;
    private double pBestValue;
    private double[] velocity;

    public Particle() {
        Random rd = new Random();
        position = new double[PSOUtils.chaines.length()];
        for(int i = 0; i<PSOUtils.chaines.length(); i++)
        {
            position[i] = abs(rd.nextInt(255));
        }
        pBestPosition = position.clone();
        pBestValue = Double.POSITIVE_INFINITY;
        velocity = new double[PSOUtils.chaines.length()];
        for (double v : velocity) {
            v=1;
        }
    }

    public void update() {
        for (int i = 0; i < position.length; i++) {
            position[i] += velocity[i];
        }
    }

    // getters and setters

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double[] getPBestPosition() {
        return pBestPosition;
    }

    public void setPBestPosition(double[] pBestPosition) {
        this.pBestPosition = pBestPosition;
    }

    public double getPBestValue() {
        return pBestValue;
    }

    public void setPBestValue(double pBestValue) {
        this.pBestValue = pBestValue;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }
}
