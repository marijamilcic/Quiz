//public class DateParser {
//    public static Date parse (String datum){
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        Date d = new Date();
//        try {
//            d = sdf.parse(datum);
//        } catch (ParseException ex) {
//            Logger.getLogger(DateParser.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return d;
//    }
//        public static java.sql.Date vratiSqlDatum(Date utilDatum){
//        java.sql.Date sql = new java.sql.Date(utilDatum.getTime());
//        return sql;
//    }
//}
//-------------------------------------------------------------------------------------------------------
//public class KlijentTransferObjekat implements Serializable{
//    private int operacija;
//    private Object parametar;
//    public KlijentTransferObjekat() {
//    }
//    public KlijentTransferObjekat(int operacija, Object parametar) {
//        this.operacija = operacija;
//        this.parametar = parametar;
//    }
//    public Object getParametar() {
//        return parametar;
//    }
//    public void setParametar(Object parametar) {
//        this.parametar = parametar;
//    }
//    public int getOperacija() {
//        return operacija;
//    }
//    public void setOperacija(int operacija) {
//        this.operacija = operacija;
//    }
//}
//-----------------------------------------------------------------------------------------------------
//public class ServerTransferObjekat implements Serializable{
//    private String poruka;
//    private Object odgovor;
//    public ServerTransferObjekat() {
//    }
//    public ServerTransferObjekat(String poruka, Object odgovor) {
//        this.poruka = poruka;
//        this.odgovor = odgovor;
//    }
//    public Object getOdgovor() {
//        return odgovor;
//    }
//    public void setOdgovor(Object odgovor) {
//        this.odgovor = odgovor;
//    }
//    public String getPoruka() {
//        return poruka;
//    }
//    public void setPoruka(String poruka) {
//        this.poruka = poruka;
//    }
//}
//-----------------------------------------------------------------------------------------------------
//public class Konstante {
//    public static final int SACUVAJ = 1;
//    public static final int OBRISI = 2;
//    public static final int  DODAJ = 3;
//    public static final int VRATI= 4;
//    public static final int  AZURIRAJ = 5;
//  }
//-----------------------------------------------------------------------------------------------------
//public class DBBroker {
//    Connection konekcija;
//    
//    public void ucitajDrajver(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Nije se ucitao drajver");
//        }
//    }
//    
//    public void otvoriKonekciju(){
//        try {
//            konekcija = DriverManager.getConnection("jdbc:mysql://localhost:3306/prosoftjul16g2", "root", "");
//            konekcija.setAutoCommit(false);
//        } catch (SQLException ex) {
//            System.out.println("Nije otvorena konekcija");
//        }
//    }
//    public void zatvoriKonekciju(){
//        try {
//            konekcija.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void komit(){
//        try {
//            konekcija.commit();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void rollback(){
//        try {
//            konekcija.rollback();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public ArrayList<Klijent> vratiListuKlijenta() {
//        String upit = "Select * from klijent";
//        ArrayList<Klijent> lista = new ArrayList<>();        
//        try {
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            while(rs.next()){
//                int ID= rs.getInt("KlijentID");
//                String naziv = rs.getString("Naziv");
//                String adresa = rs.getString("adresa");
//                Klijent k = new Klijent(ID, naziv, adresa);
//                
//                lista.add(k);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//        return lista;
//    }
//    public ArrayList<Proizvod> vratiListuProizvoda() {
//        String upit = "Select * from proizvod";
//        ArrayList<Proizvod> lista = new ArrayList<>();
//        
//        try {
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            while(rs.next()){
//                int polaznikID= rs.getInt("proizvodID");
//                String naziv = rs.getString("Naziv");
//                double cena = rs.getDouble("cena");
//                Proizvod p = new Proizvod(polaznikID, naziv, cena);
//                
//                lista.add(p);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return lista;
//    }
//    
//    public ArrayList<Racun> vratiListuRacuna() {
//        String upit = "Select * from racun";
//        ArrayList<Racun> lista = new ArrayList<>();
//        
//        try {
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            while(rs.next()){
//                int RacunID= rs.getInt("RacunID");
//                Date datum = rs.getDate("datum");
//                double iznos = rs.getDouble("ukupanIznos");
//                
//                int klijentId = rs.getInt("KlijentID");
//                Racun racun =  new Racun();
//                for(Klijent klijent: vratiListuKlijenta()){
//                    if(klijent.getKlijentID() == klijentId) {
//                        racun.setKlijent(klijent);
//                    }
//                }
//                racun.setRacunID(RacunID);
//                racun.setDatum(datum);
//                racun.setIznos(iznos);               
//                lista.add(racun);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//        return lista;
//    }    
//    public ArrayList<Stavka> vratiStavkeRacuna() {
//        String upit = "Select * from stavkaracuna";
//        ArrayList<Stavka> lista = new ArrayList<>();        
//        try {
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            while(rs.next()){
//                int RacunID= rs.getInt("RacunID");
//                Stavka stavka = new Stavka();
//                for(Racun racun: vratiListuRacuna()){
//                    if(racun.getRacunID() == RacunID) {
//                        stavka.setRacun(racun);
//                    }
//                }
//                int proizvodId = rs.getInt("ProizvodID");
//                for(Proizvod p : vratiListuProizvoda()){
//                    if(p.getProizvodId() == proizvodId){
//                        stavka.setProizvod(p);
//                    }
//                }
//                int kolicina = rs.getInt("kolicina");
//                stavka.setKolicina(kolicina);
//                int rb = rs.getInt("rb");
//                stavka.setRb(rb);
//                double iznos = rs.getDouble("iznos");
//                stavka.setIznos(iznos);               
//                lista.add(stavka);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//        return lista;
//    }
//    public void sacuvajStavku(Stavka stavka) throws SQLException {
//        String upit = "INSERT INTO stavkaracuna(RacunID,RB,Kolicina,ProizvodID,Iznos) VALUES (?,?,?,?,?)";        
//        PreparedStatement ps = konekcija.prepareStatement(upit);
//        ps.setInt(1, stavka.getRacun().getRacunID());
//        ps.setInt(2, stavka.getRb());
//        ps.setInt(3, stavka.getKolicina());
//        ps.setInt(4, stavka.getProizvod().getProizvodId());
//        ps.setDouble(5, stavka.getIznos());        
//        ps.executeUpdate();
//    }
//      public void sacuvajListuStavki(List<Stavka> lista) throws SQLException {        
//        for(Stavka s: lista){
//                sacuvajStavku(s);            
//        }
//    }
//      public void obrisi(Stavka s){
//          String deleteSQL = "DELETE FROM stavkaracuna WHERE RacunID=? AND RB=?";
//          PreparedStatement preparedStatement;
//        try {
//            preparedStatement = konekcija.prepareStatement(deleteSQL);
//            preparedStatement.setInt(1, s.getRacun().getRacunID());
//            preparedStatement.setInt(2, s.getRb());
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
//      public void obrisiListuStavki(List<Stavka> lista) throws SQLException {        
//        for(Stavka s: lista){
//                obrisi(s);            
//        }
//    }
//      public void azuriraj(Racun r){
//          String azurirajSQL = "UPDATE Racun SET Datum=?,KlijentID=?,UkupanIznos=? WHERE RacunID=?";
//          PreparedStatement preparedStatement;
//          double ukupanIznos = 0;
//          for(Stavka s: vratiStavkeRacuna()){
//              if(s.getRacun().getRacunID() == r.getRacunID()){
//                  ukupanIznos+=s.getIznos();
//              }
//          }
//        try {
//            preparedStatement = konekcija.prepareStatement(azurirajSQL);
//            preparedStatement.setDate(1, parseri.DateParser.vratiSqlDatum(r.getDatum()));
//            preparedStatement.setInt(2, r.getKlijent().getKlijentID());
//            preparedStatement.setDouble(3, ukupanIznos);
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }      
//      private int vratiMaksID() {
//        String upit = "Select max(id) as maks from tabela";
//        int maks = 0;        
//        try {
//            Statement s = konekcija.createStatement();
//            ResultSet rs = s.executeQuery(upit);
//            while(rs.next()){
//                maks= rs.getInt("maks");
//             }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return maks ;
//    }
//}
//-------------------------------------------------------------------------------------------------
//public class Kontroler {
//
//    private static Kontroler instanca;
//    DBBroker db;
//    
//    
//    private Kontroler() {
//        db = new DBBroker();
//    }
//
//    public static Kontroler getInstanca() {
//        if(instanca == null)
//            instanca = new Kontroler();
//        return instanca;
//    }
//    public ArrayList<Object> vratiListuRacuna() {
//        db.ucitajDrajver();
//        db.otvoriKonekciju();
//        ArrayList<Object> lista = db.vratiListuRacuna();
//        db.zatvoriKonekciju();
//        return lista;
//    }
//    public boolean sacuvaj(ArrayList<Object> lista) {
//        boolean sacuvano = false;
//        try {            
//            db.ucitajDrajver();
//            db.otvoriKonekciju();
//            db.sacuvajListuStavki(lista);
//            db.komit();
//            sacuvano = true;
//        } catch (SQLException ex) {
//       Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//            db.rollback();
//            sacuvano = false;
//        }
//        db.zatvoriKonekciju();
//        return sacuvano;
//    }    
//    public boolean obrisi(ArrayList<Stavka> lista) {
//        boolean obrisano = false;
//        try {            
//            db.ucitajDrajver();
//            db.otvoriKonekciju();
//            db.obrisiListuStavki(lista);
//            db.komit();
//            obrisano = true;
//        } catch (SQLException ex) {
//       Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
//            db.rollback();
//            obrisano = false;
//        }
//        db.zatvoriKonekciju();
//        return obrisano;
//    }
//}
//--------------------------------------------------------------------------------------------------------
//public class ModelTabeleServer extends AbstractTableModel{
//    ArrayList<Racun> lista;
//    public ModelTabeleServer(ArrayList<Racun> lista) {
//        this.lista = lista;
//    }   
//    @Override
//    public int getRowCount() {
//        return lista.size();
//    }
//    @Override
//    public int getColumnCount() {
//        return 2;
//    }
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        Racun r = lista.get(rowIndex);
//        
//        switch(columnIndex){
//            case 0: return r.getKlijent();
//            case 1: return r.getIznos();
//            default:return "N/A";
//        }
//    }
//    
//    String[] kolone = {"Klijent","Ukupan Iznos"};
//    @Override
//    public String getColumnName(int column) {
//        return kolone[column];
//    }    
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Racun racun = lista.get(rowIndex);        
//        switch(columnIndex){
//            case 0: racun.setKlijent((Klijent) aValue);break;
//            case 1: racun.setIznos((double) aValue);break;                
//        }
//    }    
//}
//------------------------------------------------------------------------------------------------------
//public class NitOsvezi extends Thread{    
//    FmServer sf;
//    JLabel labela;
//    public NitOsvezi(FmServer sf, JLabel labela) {
//        this.sf = sf;
//        this.labela = labela;
//    }
//    @Override
//    public void run() {
//        while (true) {            
//            sf.srediTabelu();            
//            System.out.println("Osvezio");
//            try {
//                sleep(10000);
//                labela.setText("Curko");
//            } catch (InterruptedException ex) {
//                Logger.getLogger(NitOsvezi.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }    
//}
//-----------------------------------------------------------------------------------------------------------
//public class NitKlijent extends Thread{
//    
//    Socket soket;
//    public NitKlijent(Socket soket) {
//        this.soket = soket;
//    }
//    @Override
//    public void run() {
//        while (true) {            
//            KlijentTransferObjekat kto = primiZahtev();
//            ServerTransferObjekat sto = new ServerTransferObjekat();
//            switch(kto.getOperacija()){
//                case Konstante.VRATI_KUPCE: 
//                    ArrayList<Klijent> lista = Kontroler.getInstanca().vratiListuKlijenta();
//                    sto.setOdgovor(lista);
//                    System.out.println(lista);
//                    break;
//                case Konstante.VRATI_PROIZVODE: 
//                    ArrayList<Proizvod> listaProizvoda = Kontroler.getInstanca().vratiListuProizvoda();
//                    sto.setOdgovor(listaProizvoda);
//                    break;
//                case Konstante.VRATI_RACUNE: 
//                    ArrayList<Racun> racuni = Kontroler.getInstanca().vratiListuRacuna();
//                    sto.setOdgovor(racuni);
//                    break;
//                case Konstante.VRATI_STAVKE: 
//                    ArrayList<Stavka> stavke = Kontroler.getInstanca().vratiListuStavki();
//                    sto.setOdgovor(stavke);
//                    break;
//                case Konstante.SACUVAJ: 
//                    ArrayList<Stavka> stavkeRacuna = (ArrayList<Stavka>) kto.getParametar();
//                    boolean sacuvano = Kontroler.getInstanca().sacuvaj(stavkeRacuna);
//                    if(sacuvano){
//                        sto.setPoruka("Sve uspesno sacuvano");
//                    }else{
//                        sto.setPoruka("Doslo je do greske, nije sacuvano");
//                    }
//                    sto.setOdgovor(sacuvano);
//                    break;
//                case Konstante.OBRISI: 
//                    ArrayList<Stavka> stavkeZaBrisanje = (ArrayList<Stavka>) kto.getParametar();
//                    boolean obrisano = Kontroler.getInstanca().obrisi(stavkeZaBrisanje);
//                    if(obrisano){
//                        sto.setPoruka("Sve uspesno sacuvano");
//                    }else{
//                        sto.setPoruka("Doslo je do greske, nije sacuvano");
//                    }
//                    sto.setOdgovor(obrisano);
//                    break;
//            }
//            posalji(sto);
//        }
//    }    
//    public void posalji(ServerTransferObjekat sto){
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(soket.getOutputStream());
//            oos.writeObject(sto);
//        } catch (IOException ex) {
//            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//    }    
//    public KlijentTransferObjekat primiZahtev(){
//        KlijentTransferObjekat kto = new KlijentTransferObjekat();
//        try {
//            ObjectInputStream ois = new ObjectInputStream(soket.getInputStream());
//            kto = (KlijentTransferObjekat) ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(NitKlijent.class.getName()).log(Level.SEVERE, null, ex);
//        }        
//        return kto;
//    }
//}
//-------------------------------------------------------------------------------------------------------
//public class PokretranjeServera extends Thread{
//
//    @Override
//    public void run() {
//        try {
//            ServerSocket ss = new ServerSocket(9000);
//            System.out.println("Server se pokrenuto");
//            while (true) {                
//                Socket s = ss.accept();
//                System.out.println("Klijent se povezao");
//                NitKlijent nk = new NitKlijent(s);
//                nk.start();
//        }
//            } catch (IOException ex) {
//            Logger.getLogger(PokretranjeServera.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
//------------------------------------------------------------------------------------------------------------------
//public FmServer() {
//        initComponents();
//        pokreniServer();
//        srediTabelu();
//        NitOsvezi no = new NitOsvezi(this, labelaKalendar);
//        no.start();
//    }
//************************************
//
//public void srediTabelu() {
//        
//        ArrayList<Racun>lista = Kontroler.getInstanca().vratiListuRacuna();
//        System.out.println(lista.toString());
//        ArrayList<Racun> filtriranaLista = new ArrayList<>();
//        if(jCheckBox1.isSelected()){
//        if(lista != null){
//            //filtriranje
//            Date datum = null;           
//            datum = parseri.DateParser.parse(txtDatum.getText());     
//            
//            if(datum == null){
//                filtriranaLista = lista;   
//            }else{
//                for(Racun racun: lista) {
//                    if(racun.getDatum().equals(datum)) {
//                        filtriranaLista.add(racun);
//                    }
//                }
//           }
//        ModelTabeleServer mts = new ModelTabeleServer(filtriranaLista);
//        jTable1.setModel(mts);
//        }
//        }else {
//        ModelTabeleServer mts = new ModelTabeleServer(lista);
//        jTable1.setModel(mts); }
//    }
//    private void pokreniServer() {
//        PokretranjeServera ps = new PokretranjeServera();
//        ps.start();
//    }
//--------------------------------------------------------------------------------------------------------------
//public class ModelTabeleKlijent extends AbstractTableModel{
//
//    ArrayList<Stavka> listaStavki;
//    Stavka s;
//
//    public Stavka getS() {
//        return s;
//    }
//    public void setS(Stavka s) {
//        this.s = s;
//    }
//    public ModelTabeleKlijent() {
//    listaStavki=new ArrayList<>();
//    }
//    public void setListaStavki(ArrayList<Stavka> listaStavki) {
//        this.listaStavki = listaStavki;
//    }  
//    @Override
//    public int getRowCount() {
//        return listaStavki.size();        
//    }
//    @Override
//    public int getColumnCount() {
//        return 4;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//
//        Stavka s = listaStavki.get(rowIndex);
//        double cena;
//        if(s.getProizvod() == null){
//           cena = 0;
//        }else {
//            cena = s.getProizvod().getCena();
//        }
//        switch(columnIndex){
//            case 0: return s.getProizvod();
//            case 1: return cena;
//            case 2: return s.getKolicina();
//            case 3: return s.getIznos();
//           default: return "N/A";
//        }       
//    }    
//    String[] kolone = {"Proizvod","Cena","Kolicina","Iznos"};
//
//    @Override
//    public String getColumnName(int column) {
//        return kolone[column];
//    }
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return true;
//    }
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        Stavka s = listaStavki.get(rowIndex);
//        
//        switch(columnIndex){
//            case 0: s.setProizvod((Proizvod) aValue);break;
//            case 1: s.getProizvod().setCena((Double) aValue);break;
//            case 2: s.setKolicina(Integer.parseInt((String) aValue));
//                    s.setIznos(s.getProizvod().getCena()*s.getKolicina());
//                    break;
//            case 3: s.setIznos(s.getProizvod().getCena()*s.getKolicina());break;
//        }
//        fireTableDataChanged();
//    }
//    public ArrayList<Stavka> getListaStavki() {
//        return listaStavki;
//    }
//    public void dodajRed() {
//        s= new Stavka();
//        listaStavki.add(s);
//        fireTableDataChanged();
//    }
//    public void obrisiREd(int red) {
//        listaStavki.remove(red);
//        fireTableDataChanged();
//    }
//    public void skloniPodatke() {
//        listaStavki = new ArrayList<>();
//        fireTableDataChanged();
//    }
//    }
//---------------------------------------------------------------------------------------------------------------------
//public class Komunikacija {
//    
//    private static Komunikacija instanca;
//    Socket s;
//    private Komunikacija() {
//        try {
//            s = new Socket("localhost", 9000);
//        } catch (IOException ex) {
//            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public static Komunikacija getInstanca() {
//        if(instanca == null)
//            instanca = new Komunikacija();
//        return instanca;
//    }
//    
//    public void posaljiZahtev(KlijentTransferObjekat kto){
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
//            oos.writeObject(kto);
//        } catch (IOException ex) {
//            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }    
//    public ServerTransferObjekat primiOdgovor(){
//        ServerTransferObjekat sto = new ServerTransferObjekat();
//        try {
//            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//            sto = (ServerTransferObjekat) ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         return sto;
//    }
//}
//---------------------------------------------------------------------------------------------------------------
// public FmKlijent() {
//        initComponents();
//        napuniKombo();
//        srediTabelu();
//    }
//private void btnPronadjiActionPerformed(java.awt.event.ActionEvent evt) {                                            
//       String procitaj =txtSifra.getText();
//       if(procitaj != null || procitaj != ""){
//           int idRacuna =Integer.parseInt(txtSifra.getText());
//           vratiRacun(idRacuna);
//       }else{
//           JOptionPane.showMessageDialog(this, "Sifra mora biti int!");
//       }
//        
//       
//    }                                           
//
//    public Racun vratiPoIDju(int ID){
//     KlijentTransferObjekat kto = new KlijentTransferObjekat();
//     kto.setOperacija(konstante.Konstante.VRATI_RACUNE);
//     Komunikacija.getInstanca().posaljiZahtev(kto);
//     
//     ServerTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
//     ArrayList<Racun> racuni =(ArrayList<Racun>) sto.getOdgovor();
//     
//     for(Racun r: racuni){
//         if(r.getRacunID()== ID){
//             return r;
//         }
//     }
//     return null;
//    }
//    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {                                          
//       ModelTabeleKlijent mtk = (ModelTabeleKlijent) jTable1.getModel();
//        
//        int red = jTable1.getSelectedRow();
//        
//        if(red == -1){
//            JOptionPane.showMessageDialog(this, "Odaberite red");
//        }else{
//            mtk.obrisiREd(red);
//        }
//    }                                         
//
//    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {                                         
//        ModelTabeleKlijent mtk = (ModelTabeleKlijent) jTable1.getModel();
//        mtk.dodajRed();
//        jTable1.setModel(mtk);
//        JComboBox kombo = new JComboBox();
//        kombo.removeAllItems();
//        for (Proizvod proizvod : vratiProizvode()) {
//            kombo.addItem(proizvod);
//            System.out.println(proizvod);
//        }
//        
//        TableColumnModel tcm = jTable1.getColumnModel();
//        TableColumn kolona = tcm.getColumn(0);
//        kolona.setCellEditor(new DefaultCellEditor(kombo));
//        
//        
//    }                                        
//
//    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {                                           
//       ModelTabeleKlijent mtk = (ModelTabeleKlijent) jTable1.getModel();
//       ArrayList<Stavka> listaZaBrisanje = new ArrayList<>();
//       ArrayList<Stavka> lista = mtk.getListaStavki();
//       int racunId = Integer.parseInt(txtSifra.getText());
//       for(Stavka s: lista){
//           if(s.getRb() != 0){
//               listaZaBrisanje.add(s);
//           }else{
//               s.setRacun(vratiPoIDju(racunId));
//               
//           }
//       }
//       int i =1;
//       for(Stavka s: lista){
//           s.setRb(vratiStavke().size()+i);
//           i++;
//       }
//       
//       ArrayList<Stavka> listObrisi = vratiStavke();
//       ArrayList<Stavka> listAzuriraj = new ArrayList<>();
//       for(Stavka s : listaZaBrisanje){
//           for(Stavka s2 :listObrisi){
//               if(s.getRb() == s2.getRb()){
//                   listObrisi.remove(s2);
//                   listAzuriraj.add(s);
//               }
//           }
//       }
//       obrisi(listObrisi);
////       azuriraj(listAzuriraj);
//       sacuvaj(lista);
//       Racun racun = new Racun();
//       azurirajRacun(racun);
//    }                                   
//public void napuniKombo(){
//     KlijentTransferObjekat kto = new KlijentTransferObjekat();
//     kto.setOperacija(konstante.Konstante.VRATI_KUPCE);
//     Komunikacija.getInstanca().posaljiZahtev(kto);
//     
//     ServerTransferObjekat sto = Komunikacija.getInstanca().primiOdgovor();
//     ArrayList<Klijent> lista = (ArrayList<Klijent>) sto.getOdgovor();
//     System.out.println(lista);
//     cbomboklijent.removeAllItems();
//     for(Klijent k : lista){
//        cbomboklijent.addItem(k);
//     }
// }
//    private void srediTabelu() {
//        ModelTabeleKlijent mtk = new ModelTabeleKlijent();
//        jTable1.setModel(mtk);
//        JComboBox kombo = new JComboBox();
//        kombo.removeAllItems();
//        for (Proizvod proizvod : vratiProizvode()) {
//            kombo.addItem(proizvod);
//            System.out.println(proizvod);
//        }        
//        TableColumnModel tcm = jTable1.getColumnModel();
//        TableColumn kolona = tcm.getColumn(0);
//        kolona.setCellEditor(new DefaultCellEditor(kombo));
//        
//    }       
//----------------------------------------------------------------------------------------------------------
///FILTERI

//  *CHECK BOX + COMBO BOX
//if(chbFiltriranje.isSelected()){
//            int ispRok = ((IspitniRok) cbIspitniRok.getSelectedItem()).getIspitniRokID();
//            int pred = ((Predmet) cbPredmet.getSelectedItem()).getPredmetID();
//            
//            lista = Kontroler.getInstanca().vratiPrikaz(pred, ispRok);
//        }


//  *CHECK BOX, da je na isti datum
//if (jCheckBox1.isSelected()) {
//
//            Date datum = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//            try {
//                datum = sdf.parse(jtfDatum.getText());
//            } catch (ParseException ex) {
//
//            }
//
//            ArrayList<Racun> listaRacuna = Kontroler.getInstanca().vratiListuRacuna();
//
//            if (datum == null) {
//                modifLista = lista;
//            } else {
//                for (PrikazKlasa pk : lista) {
//                    for(Racun r : listaRacuna){
//                        if(r.getKlijent().getKlijentID()==pk.getKlijent().getKlijentID() && datum.equals(r.getDatum())){
//                            modifLista.add(pk);
//                        }
//                    }
//                }
//            }
//            ModelTabeleServer mts = new ModelTabeleServer(modifLista);
//            jtTabela.setModel(mts);
//        } else {
//            ModelTabeleServer mts = new ModelTabeleServer(lista);
//            jtTabela.setModel(mts);
//
//        }


//  *DA SE NALAZI IZMEDJU 2 DATUMA
//if (lista != null) {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//
//            Date datumOd = null;
//            Date datumDo = null;
//
//            ArrayList<PrikazKlasa> filtriranaLista = new ArrayList<>();
//
//            try {
//                datumOd = sdf.parse(jtfDatumOd.getText());
//            } catch (ParseException ex) {
//            }
//
//            try {
//                datumDo = sdf.parse(jtfDatumDo.getText());
//            } catch (ParseException ex) {
//            }
//
//            if (datumOd == null && datumDo == null) {
//                filtriranaLista = lista;
//
//            } else if (datumOd != null && datumDo != null) {
//                for (PrikazKlasa pk : lista) {
//                    if (pk.getProjekat().getDatumOd().after(datumOd) && pk.getProjekat().getDatumDo().before(datumDo)) {
//                        filtriranaLista.add(pk);
//                    }
//                }
//            } else if (datumOd != null && datumDo == null) {
//                for (PrikazKlasa pk : lista) {
//                    if (pk.getProjekat().getDatumOd().after(datumOd)) {
//                        filtriranaLista.add(pk);
//                    }
//                }
//            } else if (datumOd == null && datumDo != null) {
//                for (PrikazKlasa pk : lista) {
//                    if (pk.getProjekat().getDatumDo().before(datumDo)) {
//                        filtriranaLista.add(pk);
//
//                    }
//                }
//            }
//
//        }
//public void validacije(){
//        try {
//            datumDo = sdf.parse(jtxtDatumDo.getText());
//            datumOd = sdf.parse(jtxtDatumOD.getText());
//        } catch (ParseException ex) {
//            JOptionPane.showMessageDialog(this, "Los format datumea");
//            return;
//        }
//        if(datumOd.compareTo(datumDo) != -1){
//         JOptionPane.showMessageDialog(this, "dat od veci");
//            return;
//        }
//        
//        if(datumOd.compareTo(new Date()) != 1){
//         JOptionPane.showMessageDialog(this, "dat od mora biti veci od sadanjeg datuma");
//            return;
//        }
//    }
//}