package com.example.yt;

public class DeletBean {
    private  String Numb;
    private  String Name;
    private String Coures;

    public DeletBean(String numb, String name, String coures) {
        Numb = numb;
        Name = name;
        Coures = coures;
    }

    @Override
    public String toString() {
        return "DeletBean{" +
                "Numb='" + Numb + '\'' +
                ", Name='" + Name + '\'' +
                ", Coures='" + Coures + '\'' +
                '}';
    }

    public String getNumb() {
        return Numb;
    }

    public void setNumb(String numb) {
        Numb = numb;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCoures() {
        return Coures;
    }

    public void setCoures(String coures) {
        Coures = coures;
    }
}
