package com.company;

import Sac.SacADos;
import algos.Dynammique;
import algos.Glouton;
import algos.PSE;

import java.io.FileNotFoundException;

public class appli {

    public static void main(String[] args) throws FileNotFoundException {
        SacADos sac = new SacADos(args[0],Double.parseDouble(args[1]));
        if(args[2].equals("gloutonne")){
            System.out.println("gloutonne");
            Glouton glt = new Glouton(sac);
            long begin = System.nanoTime();
            sac.setListObjFinis(glt.SacFinis());
            long end = System.nanoTime();
            long duration = end - begin;
            System.out.println(duration/1000000.0);
        }else if(args[2].equals("dynammique")){
            System.out.println("dynammique");
            long begin = System.nanoTime();
            Dynammique D = new Dynammique(sac);
            D.PoidMinBenef();
            long end = System.nanoTime();
            long duration = end - begin;
            System.out.println(duration/1000000.0 + "======================> TEMPS");
        }else if(args[2].equals("pse")){
            long begin = System.nanoTime();
            PSE ps = new PSE(sac);
            long end = System.nanoTime();
            long duration = end - begin;
            System.out.println(duration/1000000.0);
            sac.setListObjFinis(ps.resultat());
        }

        System.out.print(sac);


    }
}
