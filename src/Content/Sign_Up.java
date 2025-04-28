
package Content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import main.system.SignIn;
import java.sql.ResultSet;
import java.util.prefs.Preferences;

public class Sign_Up extends javax.swing.JPanel {
    Connection con = null;
    PreparedStatement pst = null;
    
    public Sign_Up() {
        initComponents();
        addHoverEffectToButton(txtRegRegister);
        addHoverEffectToLabel(jLabel8);
        addHoverEffectToCheckBox(cbRegRm);
    }
    
private void addHoverEffectToButton(javax.swing.JButton Button) {
    java.awt.Color hoverBackgroundColor = new java.awt.Color(51, 204, 0);
    java.awt.Color hoverTextColor = new java.awt.Color(255, 255, 204);
    java.awt.Color defaultBackgroundColor = Button.getBackground();
    java.awt.Color defaultTextColor = Button.getForeground();
    Button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            Button.setBackground(hoverBackgroundColor);
            Button.setForeground(hoverTextColor);
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            Button.setBackground(defaultBackgroundColor);
            Button.setForeground(defaultTextColor);
        }
    });
}

private void addHoverEffectTobutton(javax.swing.JButton button) {
    java.awt.Color hoverTextColor = new java.awt.Color(51, 204, 0);
    java.awt.Color defaultTextColor = button.getForeground();
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setForeground(hoverTextColor);
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setForeground(defaultTextColor);
        }
    });
}

private void addHoverEffectToLabel(javax.swing.JLabel label) {
    java.awt.Color hoverTextColor = new java.awt.Color(51, 204, 0);
    java.awt.Color defaultTextColor = label.getForeground();
    label.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            label.setForeground(hoverTextColor);
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            label.setForeground(defaultTextColor);
        }
    });
}

private void addHoverEffectToCheckBox(javax.swing.JCheckBox checkBox) {
    java.awt.Color hoverTextColor = new java.awt.Color(51, 204, 0);
    java.awt.Color defaultTextColor = checkBox.getForeground();
    checkBox.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            checkBox.setForeground(hoverTextColor);
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            checkBox.setForeground(defaultTextColor);
        }
    });
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRegFirstname = new javax.swing.JTextField();
        txtRegLastname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtRegRegister = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtRegEmailAddress = new javax.swing.JTextField();
        txtRegUsername = new javax.swing.JTextField();
        txtRegAnswer = new javax.swing.JTextField();
        cbRegRm = new javax.swing.JCheckBox();
        txtRegPassword = new javax.swing.JTextField();
        CbQuestion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 0));
        setPreferredSize(new java.awt.Dimension(640, 505));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText("SIGN-UP");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 42, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("FIRSTNAME");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("LASTNAME");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, -1, -1));

        txtRegFirstname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 200, -1));

        txtRegLastname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 200, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 204));
        jLabel6.setText("EMAIL ADDRESS");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 204));
        jLabel9.setText("USERNAME");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 204));
        jLabel7.setText("PASSWORD");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        txtRegRegister.setBackground(new java.awt.Color(255, 255, 204));
        txtRegRegister.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtRegRegister.setForeground(new java.awt.Color(0, 102, 0));
        txtRegRegister.setText("REGISTER");
        txtRegRegister.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtRegRegister.setBorderPainted(false);
        txtRegRegister.setFocusPainted(false);
        txtRegRegister.setFocusable(false);
        txtRegRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegRegisterActionPerformed(evt);
            }
        });
        add(txtRegRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 480, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 204));
        jLabel8.setText("Have an account? Sign-In");
        jLabel8.setFocusable(false);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, -1));

        txtRegEmailAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegEmailAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 349, -1));

        txtRegUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, 349, -1));

        txtRegAnswer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 350, -1));

        cbRegRm.setBackground(new java.awt.Color(0, 102, 0));
        cbRegRm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbRegRm.setForeground(new java.awt.Color(255, 255, 204));
        cbRegRm.setText("Remember me");
        cbRegRm.setContentAreaFilled(false);
        cbRegRm.setFocusPainted(false);
        cbRegRm.setFocusable(false);
        cbRegRm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegRmActionPerformed(evt);
            }
        });
        add(cbRegRm, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        txtRegPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        add(txtRegPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 350, -1));

        CbQuestion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CbQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Which city are you born in?", "Name of your friend?", "Name of your parent?" }));
        add(CbQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 350, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("QUESTION");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 204));
        jLabel10.setText("ANSWER");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BGPANE.jpg"))); // NOI18N
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 500));
    }// </editor-fold>//GEN-END:initComponents

    private void txtRegRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegRegisterActionPerformed
        try {
            String username = txtRegUsername.getText().trim();
            String password = txtRegPassword.getText().trim();
            String firstname = txtRegFirstname.getText().trim();
            String lastname = txtRegLastname.getText().trim();
            String email = txtRegEmailAddress.getText().trim();
            String answer = txtRegAnswer.getText().trim();
            String question = CbQuestion.getSelectedItem().toString();
            if ("".equals(txtRegUsername.getText())||"".equals(txtRegAnswer.getText())||"".equals(txtRegFirstname.getText())||"".equals(txtRegLastname.getText())||"".equals(txtRegEmailAddress.getText())||"".equals(txtRegAnswer.getText())){
                JOptionPane.showMessageDialog(this, "Some Fields are Empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (username.length() < 8 || password.length() < 8) {
                JOptionPane.showMessageDialog(this, "Username and Password must be at least 8 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (username.equals(password)) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (question.equals("Choose")) {
                JOptionPane.showMessageDialog(this, "Please select a valid security question!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            con = DriverManager.getConnection("jdbc:mysql://localhost/accounts", "root", "sha@123");
            String checkQuery = "SELECT accUsername FROM account_details WHERE accUsername = ?";
            pst = con.prepareStatement(checkQuery);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Username is already in use!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String query = "INSERT INTO `account_details`(`accUsername`, `accPassword`, `accFirstname`, `accLastname`, `accEmailAddress`, `accQuestion`, `accAnswer`, `accType`) VALUES (?, ?, ?, ?, ?, ?, ?, 'User')";
                con = DriverManager.getConnection("jdbc:mysql://localhost/accounts", "root", "sha@123");
                pst = con.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, firstname);
                pst.setString(4, lastname);
                pst.setString(5, email);
                pst.setString(6, question);
                pst.setString(7, answer);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account Successfully Registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (cbRegRm.isSelected()) {
                Preferences prefs = Preferences.userRoot().node("SavedAccounts");
                String existingUsers = prefs.get("usernames", "");
                String existingPasswords = prefs.get("passwords", "");
                if (!existingUsers.isEmpty()) {
                    existingUsers += ",";
                    existingPasswords += ",";
                }
                existingUsers += txtRegUsername.getText();
                existingPasswords += txtRegPassword.getText();
                prefs.put("usernames", existingUsers);
                prefs.put("passwords", existingPasswords);
                }
                resetSignUpForm();
                SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
                if (parentFrame != null) {
                parentFrame.showSign_InPanel();
                }
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_txtRegRegisterActionPerformed

private void resetSignUpForm() {
    txtRegUsername.setText("");
    txtRegPassword.setText("");
    txtRegFirstname.setText("");
    txtRegLastname.setText("");
    txtRegEmailAddress.setText("");
    txtRegAnswer.setText("");
    CbQuestion.setSelectedIndex(0);
    cbRegRm.setSelected(false);
}
    
    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.showSign_InPanel();
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void cbRegRmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegRmActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbRegRmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbQuestion;
    private javax.swing.JCheckBox cbRegRm;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtRegAnswer;
    private javax.swing.JTextField txtRegEmailAddress;
    private javax.swing.JTextField txtRegFirstname;
    private javax.swing.JTextField txtRegLastname;
    private javax.swing.JTextField txtRegPassword;
    private javax.swing.JButton txtRegRegister;
    private javax.swing.JTextField txtRegUsername;
    // End of variables declaration//GEN-END:variables

}

