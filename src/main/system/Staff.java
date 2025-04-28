
package main.system;

import Content.Home;
import Content.Reservation;
import Content.Availability;
import Content.History;
import Content.Records;
import Content.Reports;
import DateChooser.ModernCalendar;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Staff extends javax.swing.JFrame {
    
    Home a = new Home();
    Reservation b;
    public static History c = new History();
    Reports d = new Reports();
    Availability e;
    public static Records f;
    private final Color defaultColor = new Color(0,102,0);
    private final Color activeColor = new Color(51, 204, 0);
    private JButton activeButton = null;
    private String loggedInUsername;
    ModernCalendar calendarInstance = new ModernCalendar();
    private int accID;
    
    public Staff(String username) { 
        this.loggedInUsername = username;
        initComponents();
        fetchUserDetails(username);
        e = new Availability();
        calendarInstance = new ModernCalendar();
        b = new Reservation(e, accID, calendarInstance);
        f = new Records();
        c = new History();
        d = new Reports();
        LpUserMain.add(a);
        LpUserMain.add(b);
        LpUserMain.add(e);
        LpUserMain.add(f);
        LpUserMain.add(c);
        LpUserMain.add(d);
        setActiveButton(btStaffHome);
        showPanel(a); 
        addHoverEffect(btStaffHome);
        addHoverEffect(btStaffReservation);
        addHoverEffect(btStaffAvailability);
        addHoverEffect(btUserRecords);
        addHoverEffect(btStaffHistory);
        addHoverEffect(btStaffReports);
        addHoverEffectToButton(btUserLogout);
        addHoverEffectToButton(btUserEdit);
    }
    
    private void fetchUserDetails(String username) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
            String sql = "SELECT accID, accFirstname FROM account_details WHERE accUsername = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                accID = rs.getInt("accID");
                Name.setText(rs.getString("accFirstname"));
            } else {
                Name.setText("User");
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching user data: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        btStaffHome = new javax.swing.JButton();
        btStaffReservation = new javax.swing.JButton();
        btStaffAvailability = new javax.swing.JButton();
        btUserRecords = new javax.swing.JButton();
        btStaffHistory = new javax.swing.JButton();
        btStaffReports = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        btUserEdit = new javax.swing.JButton();
        btUserLogout = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        LpUserMain = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(140, 500));

        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        btStaffHome.setBackground(new java.awt.Color(0, 102, 0));
        btStaffHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btStaffHome.setForeground(new java.awt.Color(255, 255, 204));
        btStaffHome.setText("Home");
        btStaffHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btStaffHome.setBorderPainted(false);
        btStaffHome.setFocusPainted(false);
        btStaffHome.setFocusable(false);
        btStaffHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStaffHomeActionPerformed(evt);
            }
        });

        btStaffReservation.setBackground(new java.awt.Color(0, 102, 0));
        btStaffReservation.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btStaffReservation.setForeground(new java.awt.Color(255, 255, 204));
        btStaffReservation.setText("Reservation");
        btStaffReservation.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btStaffReservation.setBorderPainted(false);
        btStaffReservation.setFocusPainted(false);
        btStaffReservation.setFocusable(false);
        btStaffReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStaffReservationActionPerformed(evt);
            }
        });

        btStaffAvailability.setBackground(new java.awt.Color(0, 102, 0));
        btStaffAvailability.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btStaffAvailability.setForeground(new java.awt.Color(255, 255, 204));
        btStaffAvailability.setText("Availability");
        btStaffAvailability.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btStaffAvailability.setBorderPainted(false);
        btStaffAvailability.setFocusPainted(false);
        btStaffAvailability.setFocusable(false);
        btStaffAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStaffAvailabilityActionPerformed(evt);
            }
        });

        btUserRecords.setBackground(new java.awt.Color(0, 102, 0));
        btUserRecords.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btUserRecords.setForeground(new java.awt.Color(255, 255, 204));
        btUserRecords.setText("Records");
        btUserRecords.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btUserRecords.setBorderPainted(false);
        btUserRecords.setFocusPainted(false);
        btUserRecords.setFocusable(false);
        btUserRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUserRecordsActionPerformed(evt);
            }
        });

        btStaffHistory.setBackground(new java.awt.Color(0, 102, 0));
        btStaffHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btStaffHistory.setForeground(new java.awt.Color(255, 255, 204));
        btStaffHistory.setText("History");
        btStaffHistory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btStaffHistory.setBorderPainted(false);
        btStaffHistory.setFocusPainted(false);
        btStaffHistory.setFocusable(false);
        btStaffHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStaffHistoryActionPerformed(evt);
            }
        });

        btStaffReports.setBackground(new java.awt.Color(0, 102, 0));
        btStaffReports.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btStaffReports.setForeground(new java.awt.Color(255, 255, 204));
        btStaffReports.setText("Reports");
        btStaffReports.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btStaffReports.setBorderPainted(false);
        btStaffReports.setFocusPainted(false);
        btStaffReports.setFocusable(false);
        btStaffReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStaffReportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btStaffReports, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(btStaffHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btUserRecords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btStaffAvailability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btStaffReservation, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(btStaffHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btStaffHome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btStaffReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btStaffAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btUserRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btStaffHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btStaffReports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 89, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(640, 100));

        Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (2).png"))); // NOI18N
        Name.setText("STAFF");
        Name.setToolTipText("");

        btUserEdit.setBackground(new java.awt.Color(255, 255, 204));
        btUserEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btUserEdit.setForeground(new java.awt.Color(0, 102, 0));
        btUserEdit.setText("EDIT");
        btUserEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btUserEdit.setFocusPainted(false);
        btUserEdit.setFocusable(false);
        btUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUserEditActionPerformed(evt);
            }
        });

        btUserLogout.setBackground(new java.awt.Color(255, 255, 204));
        btUserLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btUserLogout.setForeground(new java.awt.Color(0, 102, 0));
        btUserLogout.setText("LOGOUT");
        btUserLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btUserLogout.setFocusPainted(false);
        btUserLogout.setFocusable(false);
        btUserLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUserLogoutActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 2, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 0));
        jLabel3.setText("WELCOME");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (7).png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btUserLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btUserLogout))
                        .addGap(7, 7, 7))
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        LpUserMain.setLayout(new java.awt.CardLayout());
        getContentPane().add(LpUserMain, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Staff");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void showPanel(JPanel panel) {
        a.setVisible(false);
        b.setVisible(false);
        e.setVisible(false);
        f.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        panel.setVisible(true);
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

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setBackground(defaultColor);
        }
        button.setBackground(activeColor);
        activeButton = button;
    }

    private void addHoverEffect(JButton button) {
        button.setBackground(defaultColor);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(activeColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (button != activeButton) {
                    button.setBackground(defaultColor);
                }
            }
        });
    }

    private void btStaffHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStaffHomeActionPerformed
        // TODO add your handling code here:
        setActiveButton(btStaffHome);
        showPanel(a);
    }//GEN-LAST:event_btStaffHomeActionPerformed

    private void btStaffReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStaffReservationActionPerformed
        // TODO add your handling code here:
            setActiveButton(btStaffReservation);
            showPanel(b);
            if (calendarInstance != null) {
                calendarInstance.refreshCalendar();
            }
    }//GEN-LAST:event_btStaffReservationActionPerformed

    private void btStaffAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStaffAvailabilityActionPerformed
        // TODO add your handling code here:
        setActiveButton(btStaffAvailability);
        if (e != null) {
        e.refreshCalendar();
    } 
        showPanel(e);
    }//GEN-LAST:event_btStaffAvailabilityActionPerformed

    private void btUserRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUserRecordsActionPerformed
        // TODO add your handling code here:
        setActiveButton(btUserRecords);
        f.refreshTable();
        showPanel(f);
    }//GEN-LAST:event_btUserRecordsActionPerformed

    private void btUserLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUserLogoutActionPerformed
        // TODO add your handling code here:
            SignIn SignInFrame = new SignIn();
            SignInFrame.setVisible(true);
            SignInFrame.pack();
            SignInFrame.setLocationRelativeTo(null);
            this.dispose();
    }//GEN-LAST:event_btUserLogoutActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btStaffHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStaffHistoryActionPerformed
        // TODO add your handling code here:
        setActiveButton(btStaffHistory);
        c.loadHistory(); 
        showPanel(c);
    }//GEN-LAST:event_btStaffHistoryActionPerformed

    private void btStaffReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStaffReportsActionPerformed
        // TODO add your handling code here:
        setActiveButton(btStaffReports);
        showPanel(d);
    }//GEN-LAST:event_btStaffReportsActionPerformed

    private void btUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUserEditActionPerformed
        // TODO add your handling code here:
        Object[] options = {"Change Name", "Change Password", "Cancel"};
    int choice = JOptionPane.showOptionDialog(this, "What would you like to do?", "Edit Profile",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, options, options[2]);

    if (choice == JOptionPane.YES_OPTION) {
        String newName = JOptionPane.showInputDialog(this, "Enter new name:");
        if (newName != null && !newName.trim().isEmpty()) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
                String sql = "UPDATE account_details SET accFirstname = ? WHERE accID = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, newName.trim());
                pst.setInt(2, accID);
                int updated = pst.executeUpdate();
                if (updated > 0) {
                    Name.setText(newName.trim());
                    JOptionPane.showMessageDialog(this, "Name updated successfully.");
                }
                pst.close();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating name: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.");
        }
    } else if (choice == JOptionPane.NO_OPTION) {
        String currentPassword = JOptionPane.showInputDialog(this, "Enter current password:");
        if (currentPassword == null || currentPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Current password cannot be empty.");
            return;
        }

        String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "New password cannot be empty.");
            return;
        }

        if (newPassword.length() < 8) {
            JOptionPane.showMessageDialog(this, "New password must be at least 8 characters.");
            return;
        }

        String repeatPassword = JOptionPane.showInputDialog(this, "Repeat new password:");
        if (repeatPassword == null || repeatPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Repeat password cannot be empty.");
            return;
        }

        if (!newPassword.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match.");
            return;
        }

        if (newPassword.equals(currentPassword)) {
            JOptionPane.showMessageDialog(this, "New password cannot be the same as current password.");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
            String sql = "SELECT accPassword FROM account_details WHERE accID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, accID);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String actualCurrentPassword = rs.getString("accPassword");
                if (!actualCurrentPassword.equals(currentPassword)) {
                    JOptionPane.showMessageDialog(this, "Current password is incorrect.");
                    rs.close();
                    pst.close();
                    con.close();
                    return;
                }
            }

            rs.close();
            pst.close();

            PreparedStatement updatePst = con.prepareStatement("UPDATE account_details SET accPassword = ? WHERE accID = ?");
            updatePst.setString(1, newPassword);
            updatePst.setInt(2, accID);
            int updated = updatePst.executeUpdate();
            updatePst.close();
            con.close();

            if (updated > 0) {
                JOptionPane.showMessageDialog(this, "Password updated successfully.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating password: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_btUserEditActionPerformed
    public void refreshHistory() {
        if (f != null) {
            f.refreshTable();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LpUserMain;
    private javax.swing.JLabel Name;
    private javax.swing.JButton btStaffAvailability;
    private javax.swing.JButton btStaffHistory;
    private javax.swing.JButton btStaffHome;
    private javax.swing.JButton btStaffReports;
    private javax.swing.JButton btStaffReservation;
    private javax.swing.JButton btUserEdit;
    private javax.swing.JButton btUserLogout;
    private javax.swing.JButton btUserRecords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
