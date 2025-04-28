
package FpContent;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import main.system.SignIn;

public class FpFirstForm extends javax.swing.JPanel {

    public FpFirstForm() {
        initComponents();
        addHoverEffectToButton(btFPVerify);
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
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Error!");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFPUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFPanswer = new javax.swing.JTextField();
        btFPVerify = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fpQuestion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(0, 102, 0));
        setPreferredSize(new java.awt.Dimension(640, 505));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("Username");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtFPUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFPUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFPUsernameActionPerformed(evt);
            }
        });
        add(txtFPUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 390, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("Question");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        txtFPanswer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtFPanswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFPanswerActionPerformed(evt);
            }
        });
        add(txtFPanswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 390, -1));

        btFPVerify.setBackground(new java.awt.Color(255, 255, 204));
        btFPVerify.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btFPVerify.setForeground(new java.awt.Color(0, 102, 0));
        btFPVerify.setText("Verify");
        btFPVerify.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btFPVerify.setBorderPainted(false);
        btFPVerify.setFocusPainted(false);
        btFPVerify.setFocusable(false);
        btFPVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFPVerifyActionPerformed(evt);
            }
        });
        add(btFPVerify, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 500, 31));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("ACCOUNT RECOVERY");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 204));
        jLabel6.setText("Answer");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        fpQuestion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fpQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Which city are you born in?", "Name of your friend?", "Name of your parent?" }));
        add(fpQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 390, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BGPANE.jpg"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void txtFPUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFPUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFPUsernameActionPerformed

    private void txtFPanswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFPanswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFPanswerActionPerformed

    private void btFPVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFPVerifyActionPerformed
        // TODO add your handling code here:
        String username = txtFPUsername.getText();
        String question = fpQuestion.getSelectedItem().toString();
        String answer = txtFPanswer.getText();
        if (username.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (question.equals("Choose")) {
            JOptionPane.showMessageDialog(this, "Please select a valid security question!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection con = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        try {
            con = getConnection();
            if (con == null) {
            return;
        }
            String query = "SELECT * FROM account_details WHERE accUsername = ? AND accQuestion = ? AND accAnswer = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, question);
            pst.setString(3, answer);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Verification successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                    parentFrame.switchPanel(new FpSecondForm(username));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btFPVerifyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFPVerify;
    private javax.swing.JComboBox<String> fpQuestion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtFPUsername;
    private javax.swing.JTextField txtFPanswer;
    // End of variables declaration//GEN-END:variables
}
