package PSO_1;

import PSO_1.PSOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Space {
    private double target;
    private double targetError;
    private int nParticles;
    private List<Particle> particles;
    private double[] gBestPosition;
    private double gBestValue;

    public double getTarget() {
        return target;
    }

    public double getTargetError() {
        return targetError;
    }

    public int getnParticles() {
        return nParticles;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public double[] getgBestPosition() {
        return gBestPosition;
    }

    public double getgBestValue() {
        return gBestValue;
    }

    public Space(double target, double targetError, int nParticles) {
        this.target = target;
        this.targetError = targetError;
        this.nParticles = nParticles;
        particles = new ArrayList<>();
        gBestValue = Double.POSITIVE_INFINITY;
        gBestPosition = new double[2];
    }

    public double fitness(Particle particle) {
        double x = particle.getPosition()[0];
        double y = particle.getPosition()[1];
        return x * x + y * y + 1;
    }

    public void setPBest() {
        for (Particle particle : particles) {
            double fitnessCandidate = fitness(particle);
            if (particle.getPBestValue() > fitnessCandidate) {
                particle.setPBestValue(fitnessCandidate);
                particle.setPBestPosition(particle.getPosition().clone());
            }
        }
    }

    public void setGBest() {
        for (Particle particle : particles) {
            double bestFitnessCandidate = fitness(particle);
            if (gBestValue > bestFitnessCandidate) {
                gBestValue = bestFitnessCandidate;
                gBestPosition = particle.getPosition().clone();
            }
        }
    }

    public void updateParticles() {
        Random rd = new Random();

        for (Particle particle : particles) {
            double[] inertial = multiplyVector(PSOUtils.W, particle.getVelocity());
            double[] selfConfidence = multiplyVector(PSOUtils.c1 * rd.nextDouble(), subtractVectors(particle.getPBestPosition(), particle.getPosition()));
            double[] swarmConfidence = multiplyVector(PSOUtils.c2 * rd.nextDouble(), subtractVectors(gBestPosition, particle.getPosition()));

            double[] newVelocity = addVectors(inertial, selfConfidence, swarmConfidence);
            particle.setVelocity(newVelocity);
            particle.update();
        }
    }

    public void showParticles(int iteration) {
        System.out.println(iteration + " iterations");
        System.out.println("BestPosition in this time: " + arrayToString(gBestPosition));
        System.out.println("BestValue in this time: " + gBestValue);

        for (Particle particle : particles) {
            System.out.println("PSO_1.Particle Position: " + arrayToString(particle.getPosition()));
        }
    }

    // utility methods

    public double[] multiplyVector(double scalar, double[] vector) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = scalar * vector[i];
        }
        return result;
    }

    public double[] addVectors(double[]... vectors) {
        int length = vectors[0].length;
        double[] result = new double[length];
        for (double[] vector : vectors) {
            for (int i = 0; i < length; i++) {
                result[i] += vector[i];
            }
        }
        return result;
    }

    public double[] subtractVectors(double[] vector1, double[] vector2) {
        int length = vector1.length;
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            result[i] = vector1[i] - vector2[i];
        }
        return result;
    }

    public String arrayToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public void setTargetError(double targetError) {
        this.targetError = targetError;
    }

    public void setnParticles(int nParticles) {
        this.nParticles = nParticles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public void setgBestPosition(double[] gBestPosition) {
        this.gBestPosition = gBestPosition;
    }

    public void setgBestValue(double gBestValue) {
        this.gBestValue = gBestValue;
    }
}
