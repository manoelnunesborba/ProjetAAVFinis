package algos;

import Objet.Objet;
import Sac.SacADos;

import java.util.LinkedList;

public class PSE {
    private static SacADos sac;
    private static float valPD=0;
    private float poidsMax;
    private float valueMax=0;

    private LinkedList<Objet> val2 = new LinkedList<Objet>();
    private PSE pere;
    private PSE gauche;
    private PSE droite;
    public static LinkedList<Objet> fin = new LinkedList<Objet>();



    public PSE(SacADos cesac){
        sac= cesac;
        for(int i=0; i<sac.getListObj().size();i++){
            this.AjoutAList(sac.getListObj().get(i));
        }
    }








    public LinkedList<Objet> resultat(){
        return this.fin;
    }

    public PSE(PSE p, Objet val) {

        pere = p;
        val2.addAll(pere.val2);
        val2.remove(null);
        if(!val2.contains(val)){
            val2.add(val);
        }
        valueMax = this.valueNoeud();
        poidsMax = this.poidsNoeud();
        gauche = null;
        droite = null;

        if (poidsMax > sac.getMaxContent()) {
            val2.remove(val);
            valueMax = this.valueNoeud();
            poidsMax = this.poidsNoeud();
        }

        if (this.valueMax > valPD) {
            valPD = this.valueMax;
            fin = this.val2;

        }


    }

        private float poidsNoeud(){
        float m=0;
        if (!this.val2.isEmpty() ||this.val2 != null) {
            for(int i=0;i< val2.size();i++){
                if(val2.get(i)!=null)
                    m+=val2.get(i).getPoids();
            }

        }

        return m;
    }
    boolean isEmpty() {
        return  val2==null||val2.size()==0;
    }

    public PSE getRoot() {
        PSE currentSacPSE = this;
        if (currentSacPSE.pere == null) {
            return currentSacPSE;
        }
        while (currentSacPSE.pere != currentSacPSE) {
            if(currentSacPSE.pere == null){
                return currentSacPSE;
            }
            currentSacPSE = currentSacPSE.pere;
        }
        return currentSacPSE;
    }

    public PSE getDroit(PSE a) {
        return a.droite;
    }

    public PSE getGauche(PSE a) {
        return a.gauche;
    }

    public float valueNoeud(){
        float m=0;
        if (!this.val2.isEmpty() ||this.val2 != null) {
            for(int i=0;i< val2.size();i++){
                if(val2.get(i)!=null)
                    m+=val2.get(i).getValue();
            }

        }

        return m;
    }

    public void AjoutAList(Objet i) {

        if (this.isEmpty() && this.gauche == null) {
            this.droite = new PSE(this, null);
            this.gauche = new PSE(this, i);
            if(this!=null && this.droite != null && this.gauche!=null && this.val2.equals(this.droite.val2) && this.val2.equals(this.gauche.val2) ){
                System.out.println("Pere egaux fils");
            }
        } else if (pere != null) {
            gauche = getGauche(this);
            droite = getDroit(this);
            if(gauche == null || droite == null){
                gauche = new PSE(this, i);
                droite = new PSE(this, this.val2.get(0));
                if(this!=null && this.droite != null && this.gauche!=null && this.val2.equals(this.droite.val2) && this.val2.equals(this.gauche.val2) ){
                    this.gauche = this.gauche.gauche;
                    this.droite = this.droite.droite;
                }
            }else{
                gauche.AjoutAList(i);
                droite.AjoutAList(i);
            }
        } else {
            PSE tmpG = getGauche(this);
            PSE tmpD = getDroit(this);
            tmpG.AjoutAList(i);
            tmpD.AjoutAList(i);
        }
    }




    public String toString() { // AFFICHAGE
        return toString("\t");
    }

    public String toString(String s) {
        if (gauche != null) {
            if (droite != null)
                return (s + val2.toString() + " " + this.valueMax + " " + this.poidsMax +  "\n" + gauche.toString(s + "\t") + droite.toString(s + "\t"));
            else
                return (s + val2.toString() + " " + this.valueMax  + " " + this.poidsMax + "\n" + gauche.toString(s + "\t") + "\n");
        } else {
            if (droite != null)
                return (s + val2.toString() + " " + this.valueMax  + " " + this.poidsMax + "\n\n" + droite.toString(s + "\t"));
            else
                return (s + val2.toString() + " " + this.valueMax  + " " + this.poidsMax + "\n");
        }
    }
}
