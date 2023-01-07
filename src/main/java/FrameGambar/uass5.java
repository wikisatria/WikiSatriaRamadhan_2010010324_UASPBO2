package FrameGambar;

import Bedjo.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class uass5 extends JFrame{

    private JLabel cariPanel;
    private JTextField caritextField;
    private JButton cariButton;
    private JTable table1;
    private JButton tambahbutton;
    private JButton tutupButton;
    private JButton ubahButton;
    private JButton hapusButton;
    private JButton batalButton;
    private JScrollPane viewScrollPanel;
    private JPanel mainPenel;

public void init() {
    setContentPane(mainPenel);
    setTitle("uaspbo");
    pack();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
}
    public void isiTable() {
        Connection c = Koneksi.getConnection();
        String selectSQL = "SELECT * FROM data_mahasiswa";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(selectSQL);
            String header[] = {"ID", "Nama", "NPM", "Id", "Nama barang", "Harga", "Stok", "Pemasok", "Gambar"};
            DefaultTableModel dtm = new DefaultTableModel(header, 0) {
                public Class getColumnClass(int colomn) {
                    return getValueAt(0, colomn).getClass();
              };
            viewtable.setModel(dtm);
            viewTable.setPreferredScrollableViewportSize(viewTable.getPreferredSize());
            viewTable.setRowHeight(100);
            Object[] row = new Object[10];
            while (rs.next()) {
                Icon icon = new
                        ImageIcon(getBufferimage(rs.getBlob("foto_anggota")));
                row[0] = rs.getInt("id");
                row[1] = rs.getString("nama");
                row[2] = rs.getString("npm");
                row[3] = rs.getString("tempat_tanggal_lahir");
                row[4] = rs.getString("jenis_kelamin");
                row[5] = rs.getString("email");
                row[6] = rs.getString("nomor_telepon");
                row[7] = rs.getString("alamat");
                row[8] = rs.getString("status");
                row[9] = icon;
                dtm.addRow(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage getBufferedImage(Blob imageBlob) {
        InputStream binaryStream = null;
        BufferedImage b = null;
        try {
            binaryStream = imageBlob.getBinaryStream();
            b = ImageIO.read(binaryStream);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return b;
    }
    public iframe() {
        tambahbutton.addActionListener(e -> {
            inputframe = new inputframe();
            inputframe.setVisible(true);
        });
        tutupButton.addActionListener(e -> {
            dispose();
        });

        batalButton.addActionListener(e -> {
            isiTable();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                isiTable();
        });
        cariButton.addActionListener(e ->  {
                Connection c = koneksi.getConnection();
                String keyword = "%" + textField1.getText() + "%";
                String searchSQL = "SELECT * FROM data_mahasiswa WHERE nama like ?";
                try {
                    PreparedStatement ps = c.prepareStatement(searchSQL);
                    ps.setString(1, keyword);
                    ResultSet rs = ps.executeQuery();
                    DefaultTableModel dtm = (DefaultTableModel) viewTable.getModel();
                    dtm.setRowCount(0);
                    Object[] row = new Object[10];
                    while (rs.next()){
                        Icon icon = new ImageIcon(getBufferedImage(rs.getBlob("foto_anggota")));
                        row[0] = rs.getInt("id");
                        row[1] = rs.getString("nama");
                        row[2] = rs.getString("npm");
                        row[3] = rs.getString("tempat_tanggal_lahir");
                        row[4] = rs.getString("jenis_kelamin");
                        row[5] = rs.getString("email");
                        row[6] = rs.getString("nomor_telepon");
                        row[7] = rs.getString("alamat");
                        row[8] = rs.getString("status");
                        row[9] = icon;
                        dtm.addRow(row);
                    }
                } catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            });


