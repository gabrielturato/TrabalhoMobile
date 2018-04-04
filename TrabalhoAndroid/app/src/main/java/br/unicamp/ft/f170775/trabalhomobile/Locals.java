package br.unicamp.ft.f170775.trabalhomobile;

/**
 * Created by faad2 on 01/04/2018.
 */

public class Locals {

    String name;
    int resId;
    String endereco;

    public Locals(String name, int resId, String endereco) {
        this.name = name;
        this.resId = resId;
        this.endereco = endereco;
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
