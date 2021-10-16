package algos;

import Objet.Objet;
import Sac.SacADos;

import java.util.Arrays;
import java.util.LinkedList;

public class Glouton {
    SacADos sac;
    protected int qtePres=0;
    protected int nbrObj=0;


    double[] moyenne ; /*ajouter tab dynamique*/
    double AncMoy =0;


    public Glouton(SacADos cesac) {
        this.sac = cesac;
        moyenne = new double[sac.getListObj().size()];
        for(int i=0;i<sac.getListObj().size();i++){
            this.AjoutAList(sac.getListObj().get(i));
        }

    }


    public void AjoutAList(Objet obj) {

        this.moyenne[nbrObj]=obj.getMoyenne();
        obj.setIndexOld(nbrObj);
        nbrObj++;

    }


    public void AjoutAListFinis(Objet obj) {
        sac.getListObjFinis().add(obj);
    }
    public boolean estAjoutable(double moy){
        this.AncMoy = this.AncMoy + moy;
        if(this.AncMoy < 1){
            return true;
        }
        return false;
    }
    public Objet getObj(double moy){
        for(int i=0;i<sac.getListObj().size();++i){
            if(moy == sac.getListObj().get(i).getMoyenne()){
                return sac.getListObj().get(i);
            }
        }
        return null;
    }

    public LinkedList<Objet> SacFinis() {
        Arrays.sort(moyenne);
        LinkedList<Objet> sacFinis = new LinkedList<Objet>();
        for(int i=0;i<moyenne.length-1;i++) {
            if(estAjoutable(moyenne[i])) {
                sacFinis.add(getObj(moyenne[i]));
            }else{
                return sacFinis;
            }

        }
        return sacFinis;
    }
}
