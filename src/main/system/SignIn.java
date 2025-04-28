
package main.system;

import Content.Sign_In;
import Content.Sign_Up;
import FpContent.FpFirstForm;
import FpContent.FpSecondForm;

public class SignIn extends javax.swing.JFrame {
    Sign_In a = new Sign_In();
    Sign_Up b = new Sign_Up();
    FpFirstForm c = new FpFirstForm();
    private String username = "";
    FpSecondForm d = new FpSecondForm(username);

    public SignIn() {
        initComponents();
        LpLogMain.add(a);
        LpLogMain.add(b);
        LpLogMain.add(c);
        LpLogMain.add(d);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);        
        d.setVisible(false);
        addHoverEffect(btLogSignIn);
        addHoverEffect(btLogSignUpmain);
        btLogSignIn.setBackground(new java.awt.Color(51, 204, 0));
    }
    
    private void addHoverEffect(javax.swing.JButton button) {
    java.awt.Color hoverColor = new java.awt.Color(51,204,0);
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (!button.getBackground().equals(hoverColor)) {
                button.setBackground(hoverColor);
            }
        }
        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            updateButtonColors((javax.swing.JPanel) LpLogMain.getComponent(0));
        }
    });
}
    
    public void switchPanel(javax.swing.JPanel panel) {
        LpLogMain.removeAll();
        LpLogMain.add(panel);
        LpLogMain.revalidate();
        LpLogMain.repaint();
        updateButtonColors(panel);
    }
    private void updateButtonColors(javax.swing.JPanel panel) {
    java.awt.Color activeColor = new java.awt.Color(51,204,0);
    java.awt.Color defaultColor = new java.awt.Color(0, 102, 0);
        if (panel == a) {
            btLogSignIn.setBackground(activeColor);
            btLogSignUpmain.setBackground(defaultColor);
        } else if (panel == b) {
            btLogSignIn.setBackground(defaultColor);
            btLogSignUpmain.setBackground(activeColor);
        } else {
            btLogSignIn.setBackground(defaultColor);
            btLogSignUpmain.setBackground(defaultColor);
        }
    }
    public void showForgotPasswordPanel() {
        switchPanel(c);
    }
    public void showSign_InPanel() {
        switchPanel(a);
    }
    public void showSign_UpPanel() {
        switchPanel(b);
    }
    public void showFpSecondFormPanel() {
        d = new FpSecondForm(username);
        switchPanel(d);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btLogSignIn = new javax.swing.JButton();
        btLogSignUpmain = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        LpLogMain = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SignIn");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(255, 255, 204));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo3-removebg-preview.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        Right.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 6, 175, -1));

        btLogSignIn.setBackground(new java.awt.Color(0, 102, 0));
        btLogSignIn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btLogSignIn.setForeground(new java.awt.Color(255, 255, 204));
        btLogSignIn.setText("SIGN-IN");
        btLogSignIn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btLogSignIn.setBorderPainted(false);
        btLogSignIn.setFocusPainted(false);
        btLogSignIn.setFocusable(false);
        btLogSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btLogSignInMouseEntered(evt);
            }
        });
        btLogSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogSignInActionPerformed(evt);
            }
        });
        Right.add(btLogSignIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 219, 150, -1));

        btLogSignUpmain.setBackground(new java.awt.Color(0, 102, 0));
        btLogSignUpmain.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btLogSignUpmain.setForeground(new java.awt.Color(255, 255, 204));
        btLogSignUpmain.setText("SIGN-UP");
        btLogSignUpmain.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btLogSignUpmain.setBorderPainted(false);
        btLogSignUpmain.setFocusPainted(false);
        btLogSignUpmain.setFocusable(false);
        btLogSignUpmain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogSignUpmainActionPerformed(evt);
            }
        });
        Right.add(btLogSignUpmain, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 270, 150, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Background (7).png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        Right.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(Right);
        Right.setBounds(0, 0, 210, 500);

        LpLogMain.setLayout(new java.awt.CardLayout());
        jPanel1.add(LpLogMain);
        LpLogMain.setBounds(210, 0, 590, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLogSignUpmainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogSignUpmainActionPerformed
        switchPanel(b);
    }//GEN-LAST:event_btLogSignUpmainActionPerformed

    private void btLogSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogSignInActionPerformed
        // TODO add your handling code here:
        switchPanel(a);
    }//GEN-LAST:event_btLogSignInActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btLogSignInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLogSignInMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btLogSignInMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LpLogMain;
    private javax.swing.JPanel Right;
    private javax.swing.JButton btLogSignIn;
    private javax.swing.JButton btLogSignUpmain;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
