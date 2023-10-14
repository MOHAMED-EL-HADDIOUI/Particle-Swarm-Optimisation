package PSO_1;

import java.util.Random;

public class Particle {
    private double[] position;
    private double[] pBestPosition;
    private double pBestValue;
    private double[] velocity;

    public Particle() {
        Random rd = new Random();
        double x = (Math.pow(-1, rd.nextInt(2))) * rd.nextDouble() * 1000;
        double y = (Math.pow(-1, rd.nextInt(2))) * rd.nextDouble() * 1000;
        position = new double[]{x, y};
        pBestPosition = position.clone();
        pBestValue = Double.POSITIVE_INFINITY;
        velocity = new double[2];
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
