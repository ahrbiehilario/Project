
package ContentAdmin;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnouncementsA extends javax.swing.JPanel {
    
    public AnnouncementsA() {
        initComponents();
        setOpaque(false);
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageSlider2.addNewImage();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imageSlider2.deleteSelectedImage();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon(getClass().getResource("/icons/BGPANE.jpg")).getImage();
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageSlider2 = new swing.ImageSlider();
        delete = new javax.swing.JButton();
        add = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 0));

        imageSlider2.setBackground(new java.awt.Color(255, 255, 204));

        delete.setBackground(new java.awt.Color(255, 255, 204));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(0, 102, 0));
        delete.setText("Delete Image");
        delete.setBorderPainted(false);
        delete.setFocusPainted(false);
        delete.setFocusable(false);

        add.setBackground(new java.awt.Color(255, 255, 204));
        add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(0, 102, 0));
        add.setText("Add Image");
        add.setBorderPainted(false);
        add.setFocusPainted(false);
        add.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(196, 196, 196))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(delete))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private swing.ImageSlider imageSlider2;
    // End of variables declaration//GEN-END:variables
}
