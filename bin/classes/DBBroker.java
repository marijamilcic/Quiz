/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Predmet;
import domen.Prijava;
import domen.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marij
 */
public class DBBroker {
    Connection konekcija;
    
    public void ucitajDrajver(){
    
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Nije ucitan drajver");
        }
    }
    
    public void otvoriKonekciju(){
        try {
            konekcija = DriverManager.getConnection("jdbc:mysql://localhost/kolokvijum2012", "root", "");
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Nije otvorena konekcija");        }
    }
    
    public void zatvoriKonekciju(){
        try {
            konekcija.close();
        } catch (SQLException ex) {
            System.err.println("Nije zatvorena konekcija");
        }
    }
    
    public void commit(){
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            System.out.println("Nije izvrsen commit");
        }
    }
    
    public void rollback(){
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            System.out.println("Nije izvrsen rollback");    
        }
    }
    public List<Predmet> inicijalizujListuPredmeta() {
        List<Predmet> listaPredmeta = new ArrayList<>();
             
             String sql = "SELECT * FROM predmet p";
             Statement statement;
         try {
             statement = konekcija.createStatement();
             ResultSet rs = statement.executeQuery(sql);
             
             while(rs.next()){
                 int id = rs.getInt("sifra");
                 String naziv= rs.getString("naziv");
                 int espb = rs.getInt("espb");
                 
                 Predmet p = new Predmet(id, naziv, espb);
                 listaPredmeta.add(p);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaPredmeta;
    }
    public List<Student> inicijalizujListuStudenata() {
        List<Student> listaStudenta = new ArrayList<>();
             
             String sql = "SELECT * FROM student";
             Statement statement;
         try {
             statement = konekcija.createStatement();
             ResultSet rs = statement.executeQuery(sql);
             
             while(rs.next()){
                 
                 int studentid= rs.getInt("id");
                 String index = rs.getString("index");
                 String ime = rs.getString("ime");
                 String prezime = rs.getString("prezime");
                 double stanje = rs.getDouble("stanje");
                 Student s= new Student(studentid, index, ime, prezime, stanje);
                 listaStudenta.add(s);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaStudenta;
    }
    public boolean sacuvaj(Prijava p){
        
         String sql = "INSERT INTO prijava(idstudenta,idpredmeta,datum,ocena, profesor) VALUES (?,?,?,?,?)";
         
             try {
                 PreparedStatement ps = konekcija.prepareStatement(sql);
                 ps.setInt(1, p.getStudent().getId());
                 ps.setInt(2, p.getPredmet().getId());
//                 ps.setDate(3, (java.sql.Date) p.getDatum());
                 ps.setDate(3, null);
                 ps.setInt(4, p.getOcena());
                 ps.setString(5, p.getProfesor());
                 ps.executeUpdate();
                 return  true;
             } catch (SQLException ex) {
                 Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                 return false;
             }
         
    }
    public void izmeniPrijavu(Prijava p) throws SQLException {
        String sql = "UPDATE prijava pr SET ocena ="+p.getOcena()+",datum= "+new java.sql.Date(p.getDatum().getTime())+ " ,profesor ="+p.getProfesor()+
                " where idstudenta="+p.getStudent().getId()+"AND idpredmeta="+ p.getPredmet().getId();
        Statement s = konekcija.createStatement();
        s.executeUpdate(sql);
    }

    public List<Prijava> inicijalizujListuPrijava() {
        List<Prijava> listaPrijava = new ArrayList<>();
             
             String sql = "SELECT * FROM prijava p join student s on p.idstudenta = s.id join predmet pr on p.idpredmeta = pr.sifra";
             Statement statement;
         try {
             statement = konekcija.createStatement();
             ResultSet rs = statement.executeQuery(sql);
             
             while(rs.next()){
                 int studentid = rs.getInt("idstudenta");
                 String index= rs.getString("index");
                 String ime = rs.getString("ime");
                 String prezime = rs.getString("prezime");
                 double stanje = rs.getDouble("stanje");
                 Student s = new Student(studentid, index, ime, prezime, stanje);
                 int id = rs.getInt("idpredmeta");
                 String naziv= rs.getString("naziv");
                 int espb = rs.getInt("espb");
                 
                 Predmet p = new Predmet(id, naziv, espb);
                 int ocena = rs.getInt("ocena");
                 String profesor= rs.getString("profesor");
                 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                 Date datum = rs.getDate("datum");
                 Prijava prijava= new Prijava(s, p, ocena, profesor, datum);
                 listaPrijava.add(prijava);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaPrijava;
    }
   public List<Prijava> vratiListuPrijavaSpecificnih(Predmet p) {
       List<Prijava> listaPrijava = new ArrayList<>();
             
             String sql = "SELECT * FROM prijava p join student s on p.idstudenta = s.id join predmet pr on p.idpredmeta = pr.sifra where pr.sifra="+p.getId();
             Statement statement;
         try {
             statement = konekcija.createStatement();
             ResultSet rs = statement.executeQuery(sql);
             
             while(rs.next()){
                 int studentid = rs.getInt("idstudenta");
                 String index= rs.getString("index");
                 String ime = rs.getString("ime");
                 String prezime = rs.getString("prezime");
                 double stanje = rs.getDouble("stanje");
                 Student s = new Student(studentid, index, ime, prezime, stanje);
                 int id = rs.getInt("idpredmeta");
                 String naziv= rs.getString("naziv");
                 int espb = rs.getInt("espb");
                 
                 int ocena = rs.getInt("ocena");
                 String profesor= rs.getString("profesor");
                 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
                 Date datum = rs.getDate("datum");
                 Prijava prijava= new Prijava(s, p, ocena, profesor, datum);
                 listaPrijava.add(prijava);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaPrijava;
   }
    
}
