package br.unicamp.ft.f170775.trabalhomobile;

/**
 * Created by faad2 on 01/04/2018.
 */

public class Locals {

    String name;
    int resId;
    String endereco;
    int oneStar;
    int twoStar;
    int threeStar;
    int fourStar;
    int fiveStar;


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

    public int totalStars(){
        return oneStar + twoStar + threeStar + fourStar + fiveStar;
    }

    public int mediaStars(){
        return (oneStar*1 + twoStar*2 + threeStar*3 + fourStar*4 + fiveStar*5)/totalStars();
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
