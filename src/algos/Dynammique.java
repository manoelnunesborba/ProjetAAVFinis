package algos;

import Objet.Objet;
import Sac.SacADos;

public class Dynammique {
    SacADos sac;
    private double[][] M;
    private int nbr_obj;


    public Dynammique(){
        super();
    }
    public Dynammique(SacADos cesac){
        sac= cesac;
        this.M=new double[sac.getListObj().size()][(int)sac.getMaxContent()+1];
        this.nbr_obj=0;
    }
    private double[][] matrice(){
        int i=0;
        for(int j=0;j<=sac.getMaxContent();j++) {
            if (sac.getListObj().get(i).getPoids() > j) {
                M[i][j] = 0;
            } else {
                double c =sac.getListObj().get(i).getValue();
                if(c - Math.floor(c)> 0.3){
                    c=c+1;
                }
                M[i][j] = (int) c;
            }
        }

        for(int j=0;j<=sac.getMaxContent();j++){
            for(i=1;i<M.length;i++){
                if(sac.getListObj().get(i).getPoids()>j){
                    M[i][j] = M[i-1][j];
                }   else{
                    double b = sac.getListObj().get(i).getPoids();
                    /*if(b-Math.floor(b)>0.6){
                        b = b+1;
                    }*/
                    double c =sac.getListObj().get(i).getValue();
                    if(c - Math.floor(c)> 0.6){
                        c=c+1;
                    }
                    M[i][j] = Math.max(M[i-1][j],M[i-1][j-(int)b] + (int)c);
                }
            }
        }
        return M;
    }


    public void PoidMinBenef() {
        double[][] M = this.matrice();
        int i = M.length-1;
        int j = M[0].length-1;
        while(M[i][j] == M[i][j-1]){
            j--;
        }
        while(j>0) {
            while (i > 0 && M[i][j] == M[i - 1][j]) {
                i--;
            }
            double b = sac.getListObj().get(i).getPoids();
            if(b-Math.floor(b)>0.6){
                b=b+1;
            }
            j = j - (int)b;
            if (j >= 0)
                AjoutAList(sac.getListObj().get(i));
            i--;
            if(i<0)
                i=0;

        }
    }
    public void AjoutAList(Objet obj) {

        sac.getListObjFinis().add(obj);
       // if(sac.calcul()>sac.getMaxContent()){
          //  sac.getListObjFinis().remove(obj);
          //  this.nbr_obj--;
       // }
        this.nbr_obj++;
    }
}
