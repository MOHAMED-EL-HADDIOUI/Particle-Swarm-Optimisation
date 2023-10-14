package PSO_3;

import PSO_3.PSOUtils;

import java.util.Random;

public class PSOAlgorithm {
    public static void main(String[] args) {
        Space searchSpace = new Space(PSOUtils.targetError, PSOUtils.nParticles);
        for (int i = 0; i < PSOUtils.nParticles; i++) {
            searchSpace.getParticles().add(new Particle());
        }

        int iteration = 0;
        while (iteration < PSOUtils.nIterations) {
            searchSpace.setPBest();
            searchSpace.setGBest();
            Particle gBest = new Particle();
            gBest.setPBestPosition(searchSpace.getgBestPosition());
//            System.out.println("-----erreur "+searchSpace.fitness(gBest));
            if (searchSpace.fitness(gBest) <= PSOUtils.targetError) {
                break;
            }
            searchSpace.updateParticles();
            iteration++;
        }
        System.out.println("The best solution is: " + searchSpace.arrayToString(searchSpace.getgBestPosition()) + " in " + iteration + " iterations");
        double res=0;
        char best[]=new char[PSOUtils.chaines.length()];
        for(int i=0;i<PSOUtils.chaines.length();i++)
        {
            char in;
            if(searchSpace.getgBestPosition()[i]-PSOUtils.chaines.charAt(i)>0.5)
            {
                in = (char) (searchSpace.getgBestPosition()[i]);
            }
            else{
                in = (char) (searchSpace.getgBestPosition()[i]+1);
            }
            if(in==PSOUtils.chaines.charAt(i))
            {
                res+=1;
            }
            best[i]=in;
        }
        System.out.printf("{");
        for(int i=0;i<PSOUtils.chaines.length();i++)
        {
            System.out.printf(","+best[i]);
        }
        System.out.printf("}");
        System.out.println("=> "+res/PSOUtils.chaines.length());
    }
}
