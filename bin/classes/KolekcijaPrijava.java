/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcije;

import domen.Prijava;
import java.util.ArrayList;

/**
 *
 * @author Cule
 */
public class KolekcijaPrijava {
    ArrayList<Prijava> listaPrijava;

    public KolekcijaPrijava() {
        listaPrijava = new ArrayList<>();
    }

    public ArrayList<Prijava> getListaPrijava() {
        return listaPrijava;
    }

    public void setListaPrijava(ArrayList<Prijava> listaPrijava) {
        this.listaPrijava = listaPrijava;
    }
    public void dodajPrijavu(Prijava prijava){
        //mrzelo nas je da overajdujemo equals metode
        for (Prijava pr : listaPrijava) {
            if(pr.getStudent().getBrojIndeksa().equals(prijava.getStudent().getBrojIndeksa()) && pr.getPredmet().getSifraPredmeta()== prijava.getPredmet().getSifraPredmeta()){
                pr.setDatum(prijava.getDatum());
                pr.setOcena(prijava.getOcena());
                pr.setStatus("Izmeni");
                return;
            }
        }
        
        listaPrijava.add(prijava);
    }
}
