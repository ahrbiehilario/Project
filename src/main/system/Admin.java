
package main.system;

import ContentAdmin.HomeA;
import ContentAdmin.AnnouncementsA;
import ContentAdmin.AvailabilityA;
import ContentAdmin.ReportsA;
import ContentAdmin.ReservationsA;
import DateChooser.DateChooser;
import DateChooser.ModernCalendar;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Admin extends javax.swing.JFrame {
    
    HomeA a = new HomeA();
    AnnouncementsA b = new AnnouncementsA();
    AvailabilityA c = new AvailabilityA();
    public static ReservationsA d;
    ReportsA e = new ReportsA();
    private final Color defaultColor = new Color(0,102,0);
    private final Color activeColor = new Color(51, 204, 0);
    private JButton activeButton = null;
    private ModernCalendar modernCalendar;
    private DateChooser dateChooser;
    private String loggedInUsername;
    private int accID;

    public Admin(String username) {
        this.loggedInUsername = username;
        initComponents();
        fetchUserDetails(username);
        modernCalendar = new ModernCalendar();
        dateChooser = new DateChooser(modernCalendar);
        d = new ReservationsA();
        LpAdminMain.add(a);
        LpAdminMain.add(b);
        LpAdminMain.add(c);
        LpAdminMain.add(d);
        LpAdminMain.add(e);
        setActiveButton(btAdminHome);
        showPanel(a); 
        addHoverEffect(btAdminHome);
        addHoverEffect(btAdminAnnouncements);
        addHoverEffect(btAdminAvailability);
        addHoverEffect(btAdminReservations);
        addHoverEffect(btAdminReports);
        addHoverEffectToButton(btAdminLogout);
        addHoverEffectTobutton(btAdminEdit);
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

        jPanel9 = new javax.swing.JPanel();
        btAdminHome = new javax.swing.JButton();
        btAdminAnnouncements = new javax.swing.JButton();
        btAdminAvailability = new javax.swing.JButton();
        btAdminReservations = new javax.swing.JButton();
        btAdminReports = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btAdminEdit = new javax.swing.JButton();
        btAdminLogout = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        LpAdminMain = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setUndecorated(true);

        jPanel9.setBackground(new java.awt.Color(255, 255, 204));
        jPanel9.setPreferredSize(new java.awt.Dimension(165, 500));

        btAdminHome.setBackground(new java.awt.Color(0, 102, 0));
        btAdminHome.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminHome.setForeground(new java.awt.Color(255, 255, 204));
        btAdminHome.setText("Home");
        btAdminHome.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminHome.setBorderPainted(false);
        btAdminHome.setFocusPainted(false);
        btAdminHome.setFocusable(false);
        btAdminHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminHomeActionPerformed(evt);
            }
        });

        btAdminAnnouncements.setBackground(new java.awt.Color(0, 102, 0));
        btAdminAnnouncements.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminAnnouncements.setForeground(new java.awt.Color(255, 255, 204));
        btAdminAnnouncements.setText("Announcements");
        btAdminAnnouncements.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminAnnouncements.setBorderPainted(false);
        btAdminAnnouncements.setFocusPainted(false);
        btAdminAnnouncements.setFocusable(false);
        btAdminAnnouncements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminAnnouncementsActionPerformed(evt);
            }
        });

        btAdminAvailability.setBackground(new java.awt.Color(0, 102, 0));
        btAdminAvailability.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminAvailability.setForeground(new java.awt.Color(255, 255, 204));
        btAdminAvailability.setText("Availability");
        btAdminAvailability.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminAvailability.setBorderPainted(false);
        btAdminAvailability.setFocusPainted(false);
        btAdminAvailability.setFocusable(false);
        btAdminAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminAvailabilityActionPerformed(evt);
            }
        });

        btAdminReservations.setBackground(new java.awt.Color(0, 102, 0));
        btAdminReservations.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminReservations.setForeground(new java.awt.Color(255, 255, 204));
        btAdminReservations.setText("Reservations");
        btAdminReservations.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminReservations.setBorderPainted(false);
        btAdminReservations.setFocusPainted(false);
        btAdminReservations.setFocusable(false);
        btAdminReservations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminReservationsActionPerformed(evt);
            }
        });

        btAdminReports.setBackground(new java.awt.Color(0, 102, 0));
        btAdminReports.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminReports.setForeground(new java.awt.Color(255, 255, 204));
        btAdminReports.setText("Reports");
        btAdminReports.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminReports.setBorderPainted(false);
        btAdminReports.setFocusPainted(false);
        btAdminReports.setFocusable(false);
        btAdminReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminReportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btAdminAvailability, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btAdminHome, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addComponent(btAdminAnnouncements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btAdminReservations, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAdminReports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAdminHome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAdminAnnouncements, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAdminAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAdminReservations, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btAdminReports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 172, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel9, java.awt.BorderLayout.LINE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 100));

        Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (2).png"))); // NOI18N
        Name.setText("ADMIN");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 2, new java.awt.Color(0, 0, 0)));

        btAdminEdit.setBackground(new java.awt.Color(255, 255, 204));
        btAdminEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminEdit.setForeground(new java.awt.Color(0, 102, 0));
        btAdminEdit.setText("Edit");
        btAdminEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminEdit.setFocusPainted(false);
        btAdminEdit.setFocusable(false);
        btAdminEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminEditActionPerformed(evt);
            }
        });

        btAdminLogout.setBackground(new java.awt.Color(255, 255, 204));
        btAdminLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btAdminLogout.setForeground(new java.awt.Color(0, 102, 0));
        btAdminLogout.setText("Logout");
        btAdminLogout.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btAdminLogout.setFocusPainted(false);
        btAdminLogout.setFocusable(false);
        btAdminLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdminLogoutActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (3).png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel6.setText("Online Reservation System");

        jSeparator2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (7).png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Name)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btAdminEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btAdminLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(Name)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btAdminEdit)
                                            .addComponent(btAdminLogout)))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(2, 2, 2)))))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        LpAdminMain.setLayout(new java.awt.CardLayout());
        getContentPane().add(LpAdminMain, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private void showPanel(JPanel panel) {
    a.setVisible(false);
    b.setVisible(false);
    c.setVisible(false);
    d.setVisible(false);
    e.setVisible(false);
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
private void addHoverEffectTobutton(javax.swing.JButton button) {
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

    private void btAdminHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminHomeActionPerformed
        // TODO add your handling code here:
        setActiveButton(btAdminHome);
        showPanel(a);
    }//GEN-LAST:event_btAdminHomeActionPerformed

    private void btAdminAnnouncementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminAnnouncementsActionPerformed
        // TODO add your handling code here:
        setActiveButton(btAdminAnnouncements);
        showPanel(b);
    }//GEN-LAST:event_btAdminAnnouncementsActionPerformed

    private void btAdminAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminAvailabilityActionPerformed
        // TODO add your handling code here:
        setActiveButton(btAdminAvailability);
        showPanel(c);
    }//GEN-LAST:event_btAdminAvailabilityActionPerformed

    private void btAdminLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminLogoutActionPerformed
        // TODO add your handling code here:
            SignIn SignInFrame = new SignIn();
            SignInFrame.setVisible(true);
            SignInFrame.pack();
            SignInFrame.setLocationRelativeTo(null);
            this.dispose();
    }//GEN-LAST:event_btAdminLogoutActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btAdminReservationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminReservationsActionPerformed
        // TODO add your handling code here:
        setActiveButton(btAdminReservations);
        d.refreshTable();
        showPanel(d);
    }//GEN-LAST:event_btAdminReservationsActionPerformed

    private void btAdminReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminReportsActionPerformed
        // TODO add your handling code here:
        setActiveButton(btAdminReports);
        showPanel(e);
    }//GEN-LAST:event_btAdminReportsActionPerformed

    private void btAdminEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminEditActionPerformed
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
    }//GEN-LAST:event_btAdminEditActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LpAdminMain;
    private javax.swing.JLabel Name;
    private javax.swing.JButton btAdminAnnouncements;
    private javax.swing.JButton btAdminAvailability;
    private javax.swing.JButton btAdminEdit;
    private javax.swing.JButton btAdminHome;
    private javax.swing.JButton btAdminLogout;
    private javax.swing.JButton btAdminReports;
    private javax.swing.JButton btAdminReservations;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}