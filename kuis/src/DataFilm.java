
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DataFilm extends JFrame {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/kuis";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;  
    
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    
   //kolom
    Object namaKolom[] = {"Judul", "Tipe", "Episode", "Genre" , "Status", "Rating"};   
    
    JLabel lTitle = new JLabel("INDOXXI");
    //untuk judul
    JLabel ljudul = new JLabel("Judul");
    JTextField tfjudul = new JTextField();
    
    //untuk tipe
    JLabel ltipe= new JLabel("Tipe");
    JTextField tfTipe = new JTextField();    
    
    //untuk episode
    JLabel lepisode= new JLabel("Episode");
    JTextField tfEpisode = new JTextField();  
    
    //genre
    JLabel lgenre= new JLabel("Genre");
    JTextField tfGenre = new JTextField();    
    
    //status
        JLabel lstatus= new JLabel("Status");
        String[] status={"Selesai","Belum"};
        JComboBox cmbStatus = new JComboBox(status);
        
     //rating
        JLabel lrat= new JLabel("Rating");
        JTextField tfRat = new JTextField();  
        
    //button&pencarian
    JTextField tfCari = new JTextField();
    JButton btnSimpanPanel = new JButton("Simpan");
    JButton btnHapusPanel = new JButton("Hapus");
    JButton btnKeluarPanel = new JButton("Kembali");
    JButton btnRefreshPanel = new JButton("Refresh");
    JButton btnCariPanel = new JButton("Cari");
    
    
    
    
    
    
    public void dtfilm(){
        
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }        
        setTitle("KUIS_123180024");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(660, 585);
        setLocation(630, 300);

        tableModel = new DefaultTableModel(namaKolom,0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);        
        
        add(scrollPane);
        scrollPane.setBounds(20, 235, 600, 300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
                add(lTitle);
        lTitle.setBounds(280, 5, 300, 50);
        lTitle.setFont(new Font("Segoe Script",Font.BOLD, 25));
        lTitle.setForeground(Color.black);
        
        add(ljudul);
        add(tfjudul);
        
        add(ltipe);
        add(tfTipe);
        
        add(lepisode);
        add(tfEpisode);
        
        add(lgenre);
        add(tfGenre);
       
        add(lstatus);
        add(cmbStatus);
        
        add(lrat);
        add(tfRat);
        
        ljudul.setBounds(20, 55, 120, 20);       
        tfjudul.setBounds(140, 55, 150, 20); 
        
        ltipe.setBounds(20, 85, 120, 20);        
        tfTipe.setBounds(140, 85, 150, 20);
        
        lepisode.setBounds(20, 115, 120, 20);        
        tfEpisode.setBounds(140, 115, 150, 20);
        
        lgenre.setBounds(20, 145, 120, 20);        
        tfGenre.setBounds(140, 145, 150, 20); 
        
        lstatus.setBounds(20, 175, 120, 20);        
        cmbStatus.setBounds(140, 175, 150, 20);
        
        lrat.setBounds(20, 205, 120, 20);        
        tfRat.setBounds(140, 205, 150, 20);
        
        add(btnSimpanPanel);
        add(btnHapusPanel);
        add(btnCariPanel);
        add(btnKeluarPanel);
        add(btnRefreshPanel);
        add(tfCari);
        
        tfCari.setBounds(375, 175, 150, 20);
        btnSimpanPanel.setBounds(517, 80, 100, 20);        
        btnHapusPanel.setBounds(375, 110, 100, 20);
        btnCariPanel.setBounds(537, 175, 80, 20);
        btnKeluarPanel.setBounds(517, 110, 100, 20);        
        btnRefreshPanel.setBounds(375, 80, 100, 20);

        btnSimpanPanel.addActionListener((ActionEvent e) -> {
            if (tfjudul.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Isi Semua Field !!");
            } else {
                String judul = tfjudul.getText();
                String tipe = tfTipe.getText();
                String episode = tfEpisode.getText();
                String genre = tfGenre.getText();
                String status = (String) cmbStatus.getSelectedItem();
                String rating = tfRat.getText();
    
                this.simpanFilm(judul,tipe,episode,genre,status,rating);
  
                String dataFilm[][] = this.readFilm();
                table.setModel(new JTable(dataFilm,namaKolom).getModel());
            }
        });
        tfCari.getText();
        tfCari.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String getSearch= tfCari.getText();
            String dataFilm[][] = searchFilm(getSearch);
                table.setModel(new JTable (dataFilm, namaKolom).getModel());
            }
    });
        
         if (this.getBanyakData() != 0) {  
            String dataFilm[][] = this.readFilm();  
            table.setModel((new JTable(dataFilm, namaKolom)).getModel());
             
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
    }
         table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e){ 
            int baris = table.getSelectedRow();
            int kolom = table.getSelectedColumn(); 
            String dataterpilih = table.getValueAt(baris, 0).toString();
            
            btnHapusPanel.addActionListener((ActionEvent f) -> {
                hapusFilm(dataterpilih);
                String dataFilm[][] = readFilm();
                table.setModel(new JTable(dataFilm,namaKolom).getModel());
            }); 
        }
    });

    btnKeluarPanel.addActionListener((ActionEvent e) -> {
          DataFilm film = new DataFilm();
           dispose();
    });
    
    btnRefreshPanel.addActionListener((ActionEvent e) -> {
          tfjudul.setText("");
          tfTipe.setText("");
          tfEpisode.setText("");
          tfGenre.setText("");
          tfRat.setText("");
    });
    }

    
    
    public void simpanFilm(String judul,String tipe,String episode,String genre,String status,String rat) {
        try{
            String query = "INSERT INTO `datafilm`(`judul`,`tipe`,`episode`,`genre`,`status`,`rating`) VALUES ('"+judul+"','"+tipe+"', '"+episode+"','"+genre+"','"+status+"','"+rat+"')";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query); 
        System.out.println("Berhasil Ditambahkan");
        JOptionPane.showMessageDialog(null,"Berhasil menambahkan "+judul);
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    
        String[][] readFilm() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][6];
            String query = "Select * from `datafilm`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("judul");
                data[jmlData][1] = resultSet.getString("tipe");
                data[jmlData][2] = resultSet.getString("episode");
                data[jmlData][3] = resultSet.getString("genre");
                data[jmlData][4] = resultSet.getString("status");
                data[jmlData][5] = resultSet.getString("rating");
                
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }
        
        int getBanyakData() {
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "SELECT * from `datafilm`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return 0;
        }
    }
    int getBanyakDataSearch(String getSearch){
        int jmlData=0;
        try{
            statement = koneksi.createStatement();
            String query = "Select * from `datafilm` where `judul` like '%"+getSearch+"%'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            jmlData++;
            } return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL ERROR");
             return 0;
        }
    }
    
            String[][] searchFilm(String getSearch){
        try{
            int jmlData = 0;
            String data[][] = new String[getBanyakDataSearch(getSearch)][6];
            String query = "Select * from `datafilm` where `judul` like '%"+getSearch+"%'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("judul");
                data[jmlData][1] = resultSet.getString("tipe");
                data[jmlData][2] = resultSet.getString("episode");
                data[jmlData][3] = resultSet.getString("genre");
                data[jmlData][4] = resultSet.getString("status");
                data[jmlData][5] = resultSet.getString("rating");
            jmlData++;
            } return data;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL ERROR");
            return null;
        }
    }
            void hapusFilm(String judul) {
        try{
            String query = "DELETE FROM `datafilm` WHERE `judul` = '"+judul+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus "+judul );
        }catch(SQLException sql){
            System.out.println(sql.getMessage());
        }
    }
}

   

