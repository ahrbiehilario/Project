
package swing;

import java.awt.Color;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import scrollbar.ScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImageSlider extends javax.swing.JPanel {
    
    private JLabel selectedImageLabel = null;
    private byte[] selectedImageData = null;
    private final MigLayout imageLayout;
    
    public ImageSlider() {
        initComponents();
        setOpaque(false);
        imageLayout = new MigLayout("al center, filly", "10[]10");
        panelItem.setLayout(imageLayout);
        ScrollBar sb = new ScrollBar();
        sb.setOrientation(ScrollBar.HORIZONTAL);
        sb.setOpaque(false);
        sp.setHorizontalScrollBar(sb);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(null);
        loadImagesFromDatabase();
    }
    
    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        if (panelItem != null) {
            panelItem.setOpaque(false);
            sp.setOpaque(false);
            sp.getViewport().setOpaque(false);
            sp.getHorizontalScrollBar().setOpaque(false);
        }
    }
    
    private void loadImagesFromDatabase() {
    String url = "jdbc:mysql://localhost:3306/accounts";
    String user = "root";
    String password = "sha@123";
    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement("SELECT id, image, date_added, time_added FROM images");
         ResultSet rs = pst.executeQuery()) {

        panelItem.removeAll();

        while (rs.next()) {
            byte[] imgData = rs.getBytes("image");
            String date = rs.getString("date_added");
            Time time = rs.getTime("time_added");

            if (imgData != null) {
                ImageIcon icon = resizeImage(imgData, 900, 500);

                JPanel imageContainer = new JPanel(new BorderLayout());
                imageContainer.setOpaque(false);
                imageContainer.setPreferredSize(new Dimension(900, 500));

                JLabel imageLabel = new JLabel(icon);
                imageLabel.setPreferredSize(new Dimension(900, 500));
                imageLabel.setBorder(BorderFactory.createEmptyBorder());

                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("hh:mm a");
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Manila"));
                String formattedTime = sdf.format(time);

                JLabel overlay = new JLabel("  " + date + "  " + formattedTime);
                overlay.setFont(new Font("Arial", Font.BOLD, 16));
                overlay.setForeground(Color.WHITE);
                overlay.setBackground(new Color(0, 0, 0, 170));
                overlay.setOpaque(true);
                overlay.setPreferredSize(new Dimension(900, 30));

                imageContainer.add(overlay, BorderLayout.NORTH);
                imageContainer.add(imageLabel, BorderLayout.CENTER);

                imageContainer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selectImage(imageLabel, imgData);
                    }
                });

                panelItem.add(imageContainer, "w 900, h 500");
            }
        }

        panelItem.revalidate();
        panelItem.repaint();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
        private void selectImage(JLabel imageLabel, byte[] imgData) {
        if (selectedImageLabel != null) {
            selectedImageLabel.setBorder(BorderFactory.createEmptyBorder());
        }
        selectedImageLabel = imageLabel;
        selectedImageData = imgData;
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }

    public void deleteSelectedImage() {
        if (selectedImageData == null) {
            JOptionPane.showMessageDialog(this, "No image selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String url = "jdbc:mysql://localhost:3306/accounts";
        String user = "root";
        String password = "sha@123";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("DELETE FROM images WHERE image = ?")) {
            pst.setBytes(1, selectedImageData);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                panelItem.remove(selectedImageLabel);
                panelItem.revalidate();
                panelItem.repaint();
                JOptionPane.showMessageDialog(this, "Image deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadImagesFromDatabase();
                selectedImageLabel = null;
                selectedImageData = null;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getName();
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                byte[] imageData = baos.toByteArray();
                saveImageToDatabase(imageData, fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveImageToDatabase(byte[] imageData, String imageName) {
    String url = "jdbc:mysql://localhost:3306/accounts";
    String user = "root";
    String password = "sha@123";
    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement pst = con.prepareStatement("INSERT INTO images (image, image_name, date_added, time_added) VALUES (?, ?, CURDATE(), CURTIME())")) {
        pst.setBytes(1, imageData);
        pst.setString(2, imageName);
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Image added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadImagesFromDatabase();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add image.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private ImageIcon resizeImage(byte[] imgData, int width, int height) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imgData);
            BufferedImage originalImage = ImageIO.read(bais);
            if (originalImage != null) {
                
                Image resizedImg = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panelItem = new javax.swing.JPanel();

        setOpaque(false);

        sp.setBorder(null);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
        panelItem.setLayout(panelItemLayout);
        panelItemLayout.setHorizontalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        panelItemLayout.setVerticalGroup(
            panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        sp.setViewportView(panelItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelItem;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
