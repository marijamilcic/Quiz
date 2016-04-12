/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontrolor;

import db.DBBroker;
import domen.Predmet;
import domen.Prijava;
import domen.Student;
import java.util.List;

/**
 *
 * @author marij
 */
public class KontrolorPL {
    private static KontrolorPL instance;
    private DBBroker db;
    private KontrolorPL(){
        db = new DBBroker();
    }
    public static KontrolorPL getInstance(){
        if(instance == null){
            instance = new KontrolorPL();
        }
        return instance;
               
    }
    public List<Predmet> vratiListuPredmeta(){
        db.ucitajDrajver();
        db.otvoriKonekciju();
        List<Predmet> lista = db.inicijalizujListuPredmeta();
        
        db.commit();
        db.zatvoriKonekciju();
       return lista;
    }
    public List<Student> vratiListuStudenata(){
        db.ucitajDrajver();
        db.otvoriKonekciju();
        List<Student> lista = db.inicijalizujListuStudenata();
        
        db.commit();
        db.zatvoriKonekciju();
       return lista;
    }
    
     public List<Prijava> vratiListuPrijava(){
        db.ucitajDrajver();
        db.otvoriKonekciju();
        List<Prijava> lista = db.inicijalizujListuPrijava();
        
        db.commit();
        db.zatvoriKonekciju();
       return lista;
    }
     
     public boolean sacuvaj(Prijava prijava){
         db.ucitajDrajver();
        db.otvoriKonekciju();
           db.sacuvaj(prijava);
             db.commit();
             return db.sacuvaj(prijava);
//        db.zatvoriKonekciju();
            
     }
     public List<Prijava> vratiListuPrijavaPoPredmetu(Predmet p){
        db.ucitajDrajver();
        db.otvoriKonekciju();
        List<Prijava> lista = db.vratiListuPrijavaSpecificnih(p);
        
        db.commit();
        db.zatvoriKonekciju();
       return lista;
    }
     
     
}
