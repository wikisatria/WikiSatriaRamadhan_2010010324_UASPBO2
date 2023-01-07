package data;

import Bedjo.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;

public class fdata  extends JFrame {
    private JTable viewTable;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JPanel mainPanel;


    public fdata() {
        btn1.addActionListener(e -> {
            fdata inputFrame = new fdata();
            inputFrame.setVisible(true);
        });
        btn2.addActionListener(e -> {
            int barisTerpilih = viewTable.getSelectedRow();
            if (barisTerpilih < 0) {
                JOptionPane.showConfirmDialog(null, "pilih data dulu");
                return;
            }
            int pilihan = JOptionPane.showConfirmDialog(null, "yakin mau dihapus ?", "Konfirmasi hapus", JOptionPane.YES_NO_OPTION);
            if (pilihan == 0) {
                TableModel tm = viewTable.getModel();
                int id = Integer.parseInt(tm.getValueAt(barisTerpilih, 0).toString());
                Connection c = Koneksi.getConnection();
                String deleteSQl = "DELETE FROM kota WHERE id = ?";
                try {
                    PreparedStatement ps = c.prepareStatement(deleteSQl);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btn3.addActionListener(e -> {
            int barisTerpilih = viewTable.getSelectedRow();
            if (barisTerpilih < 0) {
                JOptionPane.showConfirmDialog(null, "pilih Data Dulu");
                return;
            }
            TableModel tm = viewTable.getModel();
            int id = Integer.parseInt(tm.getValueAt(barisTerpilih, 0).toString());
            iframe inputFrame = new iframe();
            inputFrame.setId(id);
            inputFrame.isiKomponen();
            inputFrame.setVisible(true);

        });
        btn4.addActionListener(e -> {
            isiTable();
        });
        isiTable();
        init();

    }

    public void init() {
        setContentPane(mainPanel);
        setTitle("Data Kota");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setContentPane(JPanel mainPanel) {
    }

    public void isiTable() {
        Connection c = Koneksi.getConnection();
        String selectSQL = "SELECT * FROM tbarang";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);
            String header[] = {"id", "Nama barang", "Harga", "Stock", "Pemasok", "Gambar"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            viewTable.setModel(dtm);
            viewTable.getColumnModel().getColumn(0).setMaxWidth(32);
            Object[] row = new Object[3];
            while (rs.next()) {
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nama");
                row[2] = rs.getString("Harga");
                row[3] = rs.getString("Stock");
                row[4] = rs.getString("Pemasok");
                row[5] = rs.getString("Gambar");
                dtm.addRow(row);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}

