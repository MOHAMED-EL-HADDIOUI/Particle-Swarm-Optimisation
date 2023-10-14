package PSO_1;

import PSO_1.PSOUtils;

import java.util.Random;

public class PSOAlgorithm {
    public static void main(String[] args) {
        Random rd = new Random();
        double targetError = 1e-200;

        Space searchSpace = new Space(1, targetError, PSOUtils.nParticles);

        for (int i = 0; i < PSOUtils.nParticles; i++) {
            searchSpace.getParticles().add(new Particle());
        }

        int iteration = 0;
        while (iteration < PSOUtils.nIterations) {
            searchSpace.setPBest();
            searchSpace.setGBest();

            searchSpace.showParticles(iteration);
            System.out.println("Erreur : "+searchSpace.getTargetError());

            if (Math.abs(searchSpace.getgBestValue() - searchSpace.getTarget()) <= searchSpace.getTargetError()) {
                break;
            }

            searchSpace.updateParticles();
            iteration++;
        }

        System.out.println("The best solution is: " + searchSpace.arrayToString(searchSpace.getgBestPosition()) + " in " + iteration + " iterations");
    }
}
