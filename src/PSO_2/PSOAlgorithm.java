package PSO_2;


import java.util.Random;

public class PSOAlgorithm {
    public static void main(String[] args) {
        Random rd = new Random();
        Space searchSpace = new Space(1024.0, PSOUtils.targetError, PSOUtils.nParticles);

        for (int i = 0; i < PSOUtils.nParticles; i++) {
            searchSpace.getParticles().add(new Particle());
        }
        searchSpace.getParticles().forEach(particle -> {
            System.out.println(particle.getPosition());
        });

        int iteration = 0;
        while (iteration < PSOUtils.nIterations) {
            searchSpace.setPBest();
            searchSpace.setGBest();
            //System.out.println(" Erreur ===>     "+Math.abs(searchSpace.getgBestValue()-PSOUtils.SIZE));
            if (Math.abs(searchSpace.getgBestValue()-PSOUtils.SIZE) <= searchSpace.getTargetError()) {
                System.out.println("{{{{{{{{{{{{}}}}}}}}}}}}}}}}}");
                break;
            }
            searchSpace.updateParticles();
            iteration++;
        }
        System.out.println(" Erreur ===>     "+Math.abs(searchSpace.getgBestValue()-PSOUtils.SIZE));
        searchSpace.showParticles(0);
        System.out.println("The best solution is: " + searchSpace.getgBestValue() + " in " + iteration + " iterations");
    }
}
