package PSO_2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class Space {

    private double targetError;
    private List<Particle> particles;
    private double gBestValue;

    public double getTargetError() {
        return targetError;
    }


    public List<Particle> getParticles() {
        return particles;
    }


    public double getgBestValue() {
        return gBestValue;
    }

    public Space(double target, double targetError, int nParticles) {
        this.targetError = targetError;
        particles = new ArrayList<>();
        gBestValue = Double.POSITIVE_INFINITY;
    }

    public double fitness(Particle particle) {
        return abs(PSOUtils.SIZE -particle.getPosition());
    }

    public void setPBest() {
        System.out.println("------------set PBest----------------------");
        for (Particle particle : particles) {
            double fitnessCandidate = fitness(particle);
            if (abs(PSOUtils.SIZE -particle.getPBestValue()) > fitnessCandidate) {
                particle.setPBestValue(particle.getPosition());
            }
        }
    }

    public void setGBest() {
        System.out.println("------------set GBest----------------------");
        for (Particle particle : particles) {
            double bestFitnessCandidate = fitness(particle);
            if (abs(PSOUtils.SIZE -gBestValue) > bestFitnessCandidate) {
                gBestValue = particle.getPosition();
            }
        }
    }

    public void updateParticles() {
        System.out.println("------------update Particles----------------------");
        Random rd = new Random();
        for (Particle particle : particles) {
            double inertial = multiplyVector(PSOUtils.W, particle.getVelocity());
            double selfConfidence = multiplyVector(PSOUtils.c1 * rd.nextDouble(), subtractVectors(particle.getPBestValue(), particle.getPosition()));
            double swarmConfidence = multiplyVector(PSOUtils.c2 * rd.nextDouble(), subtractVectors(gBestValue, particle.getPosition()));
            double newVelocity = addVectors(inertial, selfConfidence,swarmConfidence);
            particle.setVelocity(newVelocity);
            particle.update();
        }
    }

    public void showParticles(int iteration) {
        System.out.println(iteration + " iterations");
        System.out.println("BestValue in this time: " + gBestValue);

        for (Particle particle : particles) {
            System.out.println("PSO_1.Particle Position: " + particle.getPosition());
        }
    }

    // utility methods

    public double multiplyVector(double scalar, double vector) {
        double result = scalar * vector;
        return result;
    }

    public double addVectors(double... vectors) {
        double result = 0;
        for (double vector : vectors) {
            result += vector;
        }
        return result;
    }

    public double subtractVectors(double vector1, double vector2) {
        return (vector1 - vector2);
    }

    public void setTargetError(double targetError) {
        this.targetError = targetError;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }

    public void setgBestValue(double gBestValue) {
        this.gBestValue = gBestValue;
    }
}
