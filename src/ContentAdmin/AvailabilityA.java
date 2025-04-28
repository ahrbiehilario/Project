
package ContentAdmin;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class AvailabilityA extends javax.swing.JPanel {

    private java.util.HashMap<Integer, String> suspendedOriginalStatusMap = new java.util.HashMap<>();
    private java.util.HashMap<Integer, Integer> suspendedOriginalFeeMap = new java.util.HashMap<>();
    
    public AvailabilityA() {
        initComponents();
        setOpaque(false);
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

        modernCalendar1 = new DateChooser.ModernCalendar();
        dateChooser1 = new DateChooser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btSelect = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtSelectedDate = new javax.swing.JTextField();
        btSuspend = new javax.swing.JButton();
        btFree = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setBackground(new java.awt.Color(102, 255, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("FULLY RESERVED");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(255, 255, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("DAY TIME RESERVED");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(153, 153, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("NIGHT TIME RESERVED");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(255, 102, 102));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("  SUSPENDED");
        jLabel4.setOpaque(true);

        btSelect.setBackground(new java.awt.Color(255, 255, 204));
        btSelect.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btSelect.setForeground(new java.awt.Color(0, 102, 0));
        btSelect.setText("SELECT");
        btSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("Selected Date:");

        txtSelectedDate.setEditable(false);
        txtSelectedDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btSuspend.setBackground(new java.awt.Color(255, 255, 204));
        btSuspend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btSuspend.setForeground(new java.awt.Color(0, 102, 0));
        btSuspend.setText("SUSPEND");
        btSuspend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuspendActionPerformed(evt);
            }
        });

        btFree.setBackground(new java.awt.Color(255, 255, 204));
        btFree.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btFree.setForeground(new java.awt.Color(0, 102, 0));
        btFree.setText("FREE");
        btFree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modernCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(175, 175, 175)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtSelectedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btSuspend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btFree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modernCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtSelectedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelect))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSuspend)
                        .addComponent(btFree)))
                .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectActionPerformed
        String selectedDate = dateChooser1.getSelectedDate();
        if (selectedDate != null && !selectedDate.isEmpty()) {
            txtSelectedDate.setText(selectedDate);
        } else {
            JOptionPane.showMessageDialog(this, "No date selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btSelectActionPerformed

    private void btSuspendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuspendActionPerformed
        String selectedDate = txtSelectedDate.getText();
    if (selectedDate.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a date first.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String checkQuery = "SELECT COUNT(*) FROM suspended_dates WHERE dates = ?";
        try (PreparedStatement pstCheck = con.prepareStatement(checkQuery)) {
            pstCheck.setString(1, selectedDate);
            ResultSet rs = pstCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "This date is already suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String insertQuery = "INSERT INTO suspended_dates (dates) VALUES (?)";
        try (PreparedStatement pstInsert = con.prepareStatement(insertQuery)) {
            pstInsert.setString(1, selectedDate);
            pstInsert.executeUpdate();
        }

        String fetchReservations = "SELECT resID, resStatus, resFee FROM reservations WHERE resDate = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')";
        try (PreparedStatement pstFetch = con.prepareStatement(fetchReservations)) {
            pstFetch.setString(1, selectedDate);
            ResultSet rsFetch = pstFetch.executeQuery();
            while (rsFetch.next()) {
                int resID = rsFetch.getInt("resID");
                String status = rsFetch.getString("resStatus");
                int fee = rsFetch.getInt("resFee");
                suspendedOriginalStatusMap.put(resID, status);
                suspendedOriginalFeeMap.put(resID, fee);
            }
        }

        String updateReservations = "UPDATE reservations SET resStatus = 'Suspended', resFee = 0 WHERE resDate = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')";
        try (PreparedStatement pstUpdate = con.prepareStatement(updateReservations)) {
            pstUpdate.setString(1, selectedDate);
            pstUpdate.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Date suspended successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        modernCalendar1.fetchSuspendedDates();
        modernCalendar1.refreshCalendar();
        modernCalendar1.repaint();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btSuspendActionPerformed

    private void btFreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFreeActionPerformed
        // TODO add your handling code here:
        String selectedDate = txtSelectedDate.getText();
    if (selectedDate.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a date first.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String deleteQuery = "DELETE FROM suspended_dates WHERE dates = ?";
        try (PreparedStatement pstDelete = con.prepareStatement(deleteQuery)) {
            pstDelete.setString(1, selectedDate);
            int rowsAffected = pstDelete.executeUpdate();
            for (Integer resID : suspendedOriginalStatusMap.keySet()) {
                String originalStatus = suspendedOriginalStatusMap.get(resID);
                int originalFee = suspendedOriginalFeeMap.getOrDefault(resID, 0);
                String restoreQuery = "UPDATE reservations SET resStatus = ?, resFee = ? WHERE resID = ? AND resStatus = 'Suspended'";
                try (PreparedStatement pstRestore = con.prepareStatement(restoreQuery)) {
                    pstRestore.setString(1, originalStatus);
                    pstRestore.setInt(2, originalFee);
                    pstRestore.setInt(3, resID);
                    pstRestore.executeUpdate();
                }
            }
            suspendedOriginalStatusMap.clear();
            suspendedOriginalFeeMap.clear();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Date freed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No suspended date found for the selected date.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        modernCalendar1.fetchSuspendedDates();
        modernCalendar1.refreshCalendar();
        modernCalendar1.repaint();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btFreeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFree;
    private javax.swing.JButton btSelect;
    private javax.swing.JButton btSuspend;
    private DateChooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private DateChooser.ModernCalendar modernCalendar1;
    private javax.swing.JTextField txtSelectedDate;
    // End of variables declaration//GEN-END:variables
}
