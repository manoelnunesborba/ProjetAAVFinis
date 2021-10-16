package Sac;

import Objet.Objet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class SacADos {
    protected static double max_content;

    public float getObjsPoidsMax() {
        return objsPoidsMax;
    }

    private float objsPoidsMax=0;
    public double getMaxContent(){
        return max_content;
    }
    private String chemin;

    public LinkedList<Objet> getListObj() {
        return listObj;
    }

    private LinkedList<Objet> listObj = new LinkedList<Objet>();

    public LinkedList getListObjFinis() {
        return listObjFinis;
    }

    public void setListObjFinis(LinkedList listObjFinis) {
        this.listObjFinis = listObjFinis;
    }

    private LinkedList<Objet> listObjFinis = new LinkedList<Objet>();

    protected static int nbrObj=0;

    public double getMax_content(){
        return max_content;
    }
    public SacADos() {
        max_content=0;
        chemin="";
    }
    public SacADos(String path, double capacite) throws FileNotFoundException {
        chemin=path;
        max_content=capacite;
        lireFichier(this.chemin);
    }




    public void lireFichier(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            String[] a = sc.nextLine().split(";");
            this.objsPoidsMax+=Double.parseDouble(a[1]);
            this.listObj.add(new Objet(a[0],Double.parseDouble(a[1]),Double.parseDouble(a[2])));
        }
    }




    public String Affichage() {
        StringBuilder a = new StringBuilder();
        a.append("Ce sac a pour taille max " + max_content + "\n");
        for(int i=0;i<this.listObj.size();++i) {
            a.append(this.listObj.get(i).toString() + ",");
        }
        return a.toString();
    }
    public double calcul(){
        double a=0;
        for(int i=0;i<this.listObjFinis.size();++i) {
            a +=listObjFinis.get(i).getPoids();
        }
        return a;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Ce sac a pour taille max " + max_content + "\n");
        for(int i=0;i<this.listObjFinis.size();++i) {
            s.append("[");
            s.append(this.listObjFinis.get(i).toString());
            s.append("]");
        }
        s.append(" Pour un total de " + this.calcul() + "\n");
        return s.toString();
    }
}
