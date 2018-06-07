package br.unicamp.ft.f170775.trabalhomobile;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by faad2 on 01/04/2018.
 */

public class Locals implements Serializable{

    String name;
    int resId;
    String endereco;
    int oneStar;
    int twoStar;
    int threeStar;
    int fourStar;
    int fiveStar;


    public Locals(){

    }

    public Locals(String name, int resId, String endereco, int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar) {
        this.name = name;
        this.resId = resId;
        this.endereco = endereco;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.threeStar = threeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
    }

    public void setOneStar(int oneStar) {
        this.oneStar = oneStar;
    }

    public void setTwoStar(int twoStar) {
        this.twoStar = twoStar;
    }

    public void setThreeStar(int threeStar) {
        this.threeStar = threeStar;
    }

    public void setFourStar(int fourStar) {
        this.fourStar = fourStar;
    }

    public void setFiveStar(int fiveStar) {
        this.fiveStar = fiveStar;
    }

    public int totalStars(){
        return getOneStar() + getTwoStar() + getThreeStar() + getFourStar() + getFiveStar();
    }

    public double mediaStars(){
        double media;
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormat.setMaximumFractionDigits(2);
        if(totalStars() == 0){
            return 0;
        }else {
            double somaEstrelas = ((getOneStar() * 1) + (getTwoStar() * 2) + (getThreeStar() * 3) + (getFourStar() * 4)
                    + (getFiveStar() * 5));
            double divisor = totalStars();
            media = (somaEstrelas / divisor);

            return media;
        }
    }


    public int getOneStar() {
        return oneStar;
    }

    public int getTwoStar() {
        return twoStar;
    }

    public int getThreeStar() {
        return threeStar;
    }

    public int getFourStar() {
        return fourStar;
    }

    public int getFiveStar() {
        return fiveStar;
    }

    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
