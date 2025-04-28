
package FpContent;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.system.SignIn;

public class FpSecondForm extends javax.swing.JPanel {
    
    private String username;

    public FpSecondForm(String username) {
        this.username = username;
        initComponents();
        addHoverEffectToButton(btFPUpdate);
    }
    
    private void addHoverEffectToButton(javax.swing.JButton button) {
    java.awt.Color hoverBackgroundColor = new java.awt.Color(51, 204, 0);
    java.awt.Color hoverTextColor = new java.awt.Color(255, 255, 204);
    java.awt.Color defaultBackgroundColor = button.getBackground();
    java.awt.Color defaultTextColor = button.getForeground();
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(hoverBackgroundColor);
            button.setForeground(hoverTextColor);
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(defaultBackgroundColor);
            button.setForeground(defaultTextColor);
        }
    });
    }
    
    private Connection getConnection() {
    try {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Database Connection Error", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFpRp = new javax.swing.JTextField();
        btFPUpdate = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFpNp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 0));
        setPreferredSize(new java.awt.Dimension(640, 505));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setText("UPDATE PASSWORD");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("REPEAT PASSWORD:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        txtFpRp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFpRp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFpRpActionPerformed(evt);
            }
        });
        add(txtFpRp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 300, -1));

        btFPUpdate.setBackground(new java.awt.Color(255, 255, 204));
        btFPUpdate.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btFPUpdate.setForeground(new java.awt.Color(0, 102, 0));
        btFPUpdate.setText("Update Password");
        btFPUpdate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btFPUpdate.setBorderPainted(false);
        btFPUpdate.setFocusPainted(false);
        btFPUpdate.setFocusable(false);
        btFPUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFPUpdateActionPerformed(evt);
            }
        });
        add(btFPUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 490, 31));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("NEW PASSWORD:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 158, -1));

        txtFpNp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFpNp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFpNpActionPerformed(evt);
            }
        });
        add(txtFpNp, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 300, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BGPANE.jpg"))); // NOI18N
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void txtFpRpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFpRpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFpRpActionPerformed

    private void btFPUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFPUpdateActionPerformed
        // TODO add your handling code here:
        String newPassword = txtFpNp.getText();
        String repeatPassword = txtFpRp.getText();
        if (newPassword.isEmpty() || repeatPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!newPassword.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection con = getConnection();
        if (con == null) return;
        try {
            String checkQuery = "SELECT accPassword FROM account_details WHERE accUsername = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                String oldPassword = rs.getString("accPassword");
                if (newPassword.equals(oldPassword)) {
                    JOptionPane.showMessageDialog(this, "New password cannot be the same as the old password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String updateQuery = "UPDATE account_details SET accPassword = ? WHERE accUsername = ?";
            PreparedStatement updateStmt = con.prepareStatement(updateQuery);
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, username);
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                parentFrame.showSign_InPanel();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Password update failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btFPUpdateActionPerformed

    private void txtFpNpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFpNpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFpNpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFPUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtFpNp;
    private javax.swing.JTextField txtFpRp;
    // End of variables declaration//GEN-END:variables
}
