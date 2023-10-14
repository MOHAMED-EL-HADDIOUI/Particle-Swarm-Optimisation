package SMA_Particle_Swarm_Optimisation;

import PSO.PSOUtils;
import PSO.Particle;
import PSO.Space;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class IslandAgent extends Agent {
    private Space searchSpace ;

    @Override
    protected void setup() {

        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("initializePopulation");
                initializePopulation();
            }
        });
        sequentialBehaviour.addSubBehaviour(new Behaviour() {
            int iter=0;
            @Override
            public void action() {

                searchSpace.setPBest();
                searchSpace.setGBest();
                searchSpace.updateParticles();
                iter++;
            }

            @Override
            public boolean done() {
                return PSOUtils.nIterations <= iter;
            }
        });

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                DFAgentDescription dfAgentDescription=new DFAgentDescription();
                ServiceDescription serviceDescription=new ServiceDescription();
                serviceDescription.setType("ga");
                dfAgentDescription.addServices(serviceDescription);
                DFAgentDescription[] dfAgentDescriptions ;
                try {
                    dfAgentDescriptions= DFService.search(getAgent(), dfAgentDescription);
                } catch (FIPAException e) {
                    throw new RuntimeException(e);
                }

                ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);
                aclMessage.addReceiver(dfAgentDescriptions[0].getName());
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
                String chaine = Arrays.toString(searchSpace.getgBestPosition());
                aclMessage.setContent(chaine+" "+res/PSOUtils.chaines.length());
                send(aclMessage);

            }
        });
        addBehaviour(sequentialBehaviour);
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }

    }

    public void initializePopulation() {
        this.searchSpace= new Space(PSOUtils.targetError, PSOUtils.nParticles);
    }

}
