/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Predmet;
import domen.Prijava;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marij
 */
public class ModelTabele extends AbstractTableModel{
    List<Prijava> lista;
    boolean editable;

    public ModelTabele(List<Prijava> lista) {
        this.lista = lista;
        fireTableDataChanged();
    }
    public ModelTabele(List<Prijava> lista, boolean editable) {
        this.editable = editable;
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Prijava p = lista.get(rowIndex);
       switch(columnIndex){
           case 0: return p.getPredmet().getNaziv();
           case 1: return p.getPredmet().getEspb();
           case 2: return p.getStudent().getIme();
           case 3: return p.getOcena(); 
           case 4: return p.getProfesor();
           default: return "n/a";
      }
    }
    @Override
    public String getColumnName(int column){
        switch(column) {
           case 0: return "predmet";
           case 1: return "espb";
           case 2: return "student";
           case 3: return  "ocena";
           case 4: return  "profesor";
           default: return "n/a";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void azurirajlistu (List<Prijava> lp) {
        lista.removeAll(lista);
        
        fireTableDataChanged();
        
    }
    public void obrisiSveRedove(){
        lista.removeAll(lista);
        fireTableDataChanged();
    }
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Prijava prijava=lista.get(rowIndex);
//        switch(columnIndex){
//            case 0: prijava.getPredmet().setId(Integer.parseInt(String.valueOf(aValue)));
//            case 1: prijava.getPredmet().setEspb(Integer.parseInt(String.valueOf(aValue))); break;
//            case 2: prijava.getStudent().setIme((String)aValue); break;
//            case 3: prijava.getOcena(Integer.parseInt((String) aValue)); break;
//          
//            default: System.out.println("OVO NE BI TREBALO DA SE DESAVA!"); return;
//        }
//    }
    public Prijava vratiPrijavuSaPozicije(int odabraniRed) {
        return lista.get(odabraniRed);
    }
    
    public void obrisiSelektovano(Prijava p){
        lista.remove(p);
        fireTableDataChanged();
    }
}
