package data;

import Bedjo.Koneksi;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class iframe extends JFrame{
    private JTextField id;
    private JTextField Nama_Brg;
    private JTextField HargaF;
    private JTextField StockF;
    private JTextField PemasokF;
    private JButton simpanButton;
    private JButton batalButton;
    private JPanel mainPanel;

    private int ID;

    public iframe(){
        simpanButton.addActionListener(e -> {
            String nama = Nama_Brg.getText();
            Double harga = Double.valueOf(HargaF.getText());
            String stock = StockF.getText();
            String pemasok = PemasokF.getText();
            Connection c = Koneksi.getConnection();
            PreparedStatement ps;
            try{
                if (ID == 0){
                    String insertSQL = "INSERT INTO VALUES (NULL, ?, ?, ?, ?)";
                    ps = c.prepareStatement(insertSQL);
                    ps.setString(1,nama);
                    ps.setDouble(2,harga);
                    ps.setString(3,stock);
                    ps.setString(4,pemasok);
                    ps.executeUpdate();
                    dispose();
                } else {
                    String updateSQL = "UPDATE tbarang SET nama = ? ," + "harga = ?, " + "pemasok = ? whare id =?";
                    ps = c.prepareStatement(updateSQL);
                    ps.setString(1,nama);
                    ps.setDouble(2,harga);
                    ps.setInt(3,ID);
                    ps.setString(4,stock);
                    ps.setString(5,pemasok);
                    ps.executeUpdate();
                    dispose();
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        init();
    }
        public  void init(){
        setContentPane(mainPanel);
        setTitle("Data Kota");
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void setId(int id) {
        this.ID = id;
    }

    public void isiKomponen() {
        Connection c = Koneksi.getConnection();
        String findSQL = "SELECT * FROM tbarang WHERE id = ?;";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(findSQL);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id.setText(String.valueOf(rs.getInt("id")));
                Nama_Brg.setText(String.valueOf(rs.getInt("nama")));
                HargaF.setText(String.valueOf(rs.getInt("harga")));
                StockF.setText(String.valueOf(rs.getInt("stock")));
                PemasokF.setText(String.valueOf(rs.getInt("pemasok")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}

