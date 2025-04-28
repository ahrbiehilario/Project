
package Content;

import FpContent.FpFirstForm;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import main.system.Admin;
import main.system.SignIn;
import main.system.Staff;

public class Sign_In extends javax.swing.JPanel {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Sign_In() {
        initComponents();
        initComponents();
        setupSavedCredentialsFeature();
        addHoverEffectToButton(btLogLogin);
        addHoverEffectToLabel(jLabel4);
        addHoverEffectTobutton(btLogForgot);
        addHoverEffectToCheckBox(cbLogRm);
    }

private boolean stopAsking = false;

private void setupSavedCredentialsFeature() {
    txtLogUsername.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            showSavedAccountsDialog();
        }
    });
    txtLogPassword.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            showSavedAccountsDialog();
        }
    });
}

private void showSavedAccountsDialog() {
    if (stopAsking) return;
    Preferences prefs = Preferences.userRoot().node("SavedAccounts");
    String savedUsernames = prefs.get("usernames", "");
    String savedPasswords = prefs.get("passwords", "");
    if (savedUsernames.isEmpty()) return;
    String[] usernameList = savedUsernames.split(",");
    String[] passwordList = savedPasswords.split(",");
    String selectedUsername = (String) JOptionPane.showInputDialog(
        null,
        "Select this Account?",
        "Saved Accounts",
        JOptionPane.QUESTION_MESSAGE,
        null,
        usernameList,
        usernameList[0]
    );
    if (selectedUsername != null) {
        int index = -1;
        for (int i = 0; i < usernameList.length; i++) {
            if (usernameList[i].equals(selectedUsername)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            Object[] options = {"Use this account", "Delete this account", "Cancel"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "What would you like to do?",
                "Account Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );
            if (choice == JOptionPane.YES_OPTION) {
                txtLogUsername.setText(usernameList[index]);
                txtLogPassword.setText(passwordList[index]);
            } else if (choice == JOptionPane.NO_OPTION) {
                deleteSavedAccount(index);
            } else {
                stopAsking = true;
            }
        }
    } else {
        stopAsking = true;
    }
}

private void deleteSavedAccount(int index) {
    Preferences prefs = Preferences.userRoot().node("SavedAccounts");
    String savedUsernames = prefs.get("usernames", "");
    String savedPasswords = prefs.get("passwords", "");
    if (savedUsernames.isEmpty() || savedPasswords.isEmpty()) return;
    String[] usernameList = savedUsernames.split(",");
    String[] passwordList = savedPasswords.split(",");
    if (index < 0 || index >= usernameList.length) return;
    List<String> updatedUsernames = new ArrayList<>(Arrays.asList(usernameList));
    List<String> updatedPasswords = new ArrayList<>(Arrays.asList(passwordList));
    updatedUsernames.remove(index);
    updatedPasswords.remove(index);
    prefs.put("usernames", String.join(",", updatedUsernames));
    prefs.put("passwords", String.join(",", updatedPasswords));
    JOptionPane.showMessageDialog(null, "Saved account deleted successfully.", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtLogUsername = new javax.swing.JTextField();
        txtLogPassword = new javax.swing.JPasswordField();
        btLogLogin = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbLogRm = new javax.swing.JCheckBox();
        btLogForgot = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setText("SIGN-IN");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 41, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText("USERNAME:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 131, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("PASSWORD:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 177, -1, -1));

        txtLogUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtLogUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLogUsernameActionPerformed(evt);
            }
        });
        add(txtLogUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 128, 385, -1));

        txtLogPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        add(txtLogPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 177, 385, -1));

        btLogLogin.setBackground(new java.awt.Color(255, 255, 204));
        btLogLogin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btLogLogin.setForeground(new java.awt.Color(0, 102, 0));
        btLogLogin.setText("LOGIN");
        btLogLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btLogLogin.setBorderPainted(false);
        btLogLogin.setFocusPainted(false);
        btLogLogin.setFocusable(false);
        btLogLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogLoginActionPerformed(evt);
            }
        });
        add(btLogLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 226, 385, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("Don't have an account? Sign-Up");
        jLabel4.setFocusable(false);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 274, -1, -1));

        cbLogRm.setBackground(new java.awt.Color(0, 102, 0));
        cbLogRm.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbLogRm.setForeground(new java.awt.Color(255, 255, 204));
        cbLogRm.setText("Remember me");
        cbLogRm.setContentAreaFilled(false);
        cbLogRm.setFocusPainted(false);
        cbLogRm.setFocusable(false);
        add(cbLogRm, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 409, -1, -1));

        btLogForgot.setBackground(new java.awt.Color(0, 102, 0));
        btLogForgot.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btLogForgot.setForeground(new java.awt.Color(255, 255, 204));
        btLogForgot.setText("Forgot Password?");
        btLogForgot.setBorder(null);
        btLogForgot.setContentAreaFilled(false);
        btLogForgot.setFocusPainted(false);
        btLogForgot.setFocusable(false);
        btLogForgot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogForgotActionPerformed(evt);
            }
        });
        add(btLogForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 411, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BGPANE.jpg"))); // NOI18N
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 500));

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void txtLogUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLogUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLogUsernameActionPerformed

    private void btLogLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogLoginActionPerformed
        // TODO add your handling code here:
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/accounts", "root", "sha@123");
            String query = "select * from account_details where accUsername=? and accPassword=?";
            pst = con.prepareStatement(query);
            pst.setString(1, txtLogUsername.getText());
            pst.setString(2, txtLogPassword.getText());
            rs = pst.executeQuery();
            if ("".equals(txtLogUsername.getText())||"".equals(txtLogPassword.getText())){
                JOptionPane.showMessageDialog(this, "Some Fields are Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                if(rs.next()){
                    String username = rs.getString("accUsername");
                    String type = rs.getString("accType");
                    if (cbLogRm.isSelected()) {
                    Preferences prefs = Preferences.userRoot().node("SavedAccounts");
                    String existingUsers = prefs.get("usernames", "");
                    String existingPasswords = prefs.get("passwords", "");
                    if (!existingUsers.isEmpty()) {
                        existingUsers += ",";
                        existingPasswords += ",";
                    }
                    existingUsers += txtLogUsername.getText().trim();
                    existingPasswords += txtLogPassword.getText().trim();
                    prefs.put("usernames", existingUsers);
                    prefs.put("passwords", existingPasswords);
                    }
                    if (type.equals("User")){
                        javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
                        Staff StaffFrame = new Staff(username);
                        StaffFrame.setVisible(true);
                        StaffFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
                    }else if (type.equals("Admin")){
                        javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
                        Admin AdminFrame = new Admin(username);
                        AdminFrame.setVisible(true);
                        AdminFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                    txtLogUsername.setText("");
                    txtLogPassword.setText("");
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(SignIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btLogLoginActionPerformed

    private void btLogForgotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogForgotActionPerformed
        // TODO add your handling code here:
        SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.switchPanel(new FpFirstForm());
        }
    }//GEN-LAST:event_btLogForgotActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        SignIn parentFrame = (SignIn) javax.swing.SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
        parentFrame.showSign_UpPanel();
        }
    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogForgot;
    private javax.swing.JButton btLogLogin;
    private javax.swing.JCheckBox cbLogRm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField txtLogPassword;
    private javax.swing.JTextField txtLogUsername;
    // End of variables declaration//GEN-END:variables

}
