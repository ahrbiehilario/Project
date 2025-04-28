
package Content;

import DateChooser.ModernCalendar;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.system.Staff;

public class Reservation extends javax.swing.JPanel {
    
    private Availability availabilityPanel;
    private int accID;
    private ModernCalendar calendarInstance;

    public Reservation(Availability availabilityPanel, int accID, ModernCalendar calendarInstance) {
        this.availabilityPanel = availabilityPanel;
        this.accID = accID;
        this.calendarInstance = calendarInstance;
        initComponents();
    }
    
    public Reservation() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtphone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfullname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbtot = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtnoa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btSelectDate = new javax.swing.JButton();
        dateChooser1 = new DateChooser.DateChooser();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbgs = new javax.swing.JCheckBox();
        cbfh = new javax.swing.JCheckBox();
        cbacr = new javax.swing.JCheckBox();
        cbocc = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btAccept = new javax.swing.JButton();
        selecteddatetxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtResFee = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setText("RESERVATION");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText("FULLNAME");

        txtphone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("PHONE NO.");

        txtfullname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("TIME OF RESERVATION");

        cbtot.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbtot.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Day Tour (₱10,000) (6:00 AM - 5:00 PM)", "Night Tour (₱11,000) (6:00 PM - 5:00 AM)", "22 Hours (₱19,000) (6:00 AM - 5:00 AM)" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 204));
        jLabel5.setText("NO. OF ATTENDEES");

        txtnoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 204));
        jLabel6.setText("DATE OF TOUR");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 204));
        jLabel7.setText("MAXIMUM OF 70");

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        btSelectDate.setBackground(new java.awt.Color(0, 102, 0));
        btSelectDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btSelectDate.setForeground(new java.awt.Color(255, 255, 204));
        btSelectDate.setText("SELECT DATE");
        btSelectDate.setFocusPainted(false);
        btSelectDate.setFocusable(false);
        btSelectDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSelectDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSelectDate)
                .addContainerGap())
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 204));
        jLabel8.setText("INCLUSIONS");

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        cbgs.setBackground(new java.awt.Color(255, 255, 204));
        cbgs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbgs.setForeground(new java.awt.Color(0, 102, 0));
        cbgs.setText("GAS STOVE (₱ 250)");
        cbgs.setFocusPainted(false);
        cbgs.setFocusable(false);

        cbfh.setBackground(new java.awt.Color(255, 255, 204));
        cbfh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbfh.setForeground(new java.awt.Color(0, 102, 0));
        cbfh.setText("FUNCTION HALL (₱ 1,500)");
        cbfh.setFocusPainted(false);
        cbfh.setFocusable(false);

        cbacr.setBackground(new java.awt.Color(255, 255, 204));
        cbacr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbacr.setForeground(new java.awt.Color(0, 102, 0));
        cbacr.setText("EXTRA AIR-CONDITIONED ROOM (₱ 2,500)");
        cbacr.setFocusPainted(false);
        cbacr.setFocusable(false);

        cbocc.setBackground(new java.awt.Color(255, 255, 204));
        cbocc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbocc.setForeground(new java.awt.Color(0, 102, 0));
        cbocc.setText("OUTSIDE CATERING CORCKAGE (₱ 2000)");
        cbocc.setFocusPainted(false);
        cbocc.setFocusable(false);

        jPanel3.setBackground(new java.awt.Color(0, 102, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 204));
        jLabel9.setText("This comes with additional fee!!");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbacr)
                            .addComponent(cbocc)
                            .addComponent(cbgs)
                            .addComponent(cbfh))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbgs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbfh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbocc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbacr)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        btAccept.setBackground(new java.awt.Color(255, 255, 204));
        btAccept.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btAccept.setForeground(new java.awt.Color(0, 102, 0));
        btAccept.setText("ACCEPT");
        btAccept.setFocusPainted(false);
        btAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAcceptActionPerformed(evt);
            }
        });

        selecteddatetxt.setEditable(false);
        selecteddatetxt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        selecteddatetxt.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 204));
        jLabel10.setText("DOWN PAYMENT");

        txtResFee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfullname)
                                    .addComponent(txtphone)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbtot, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(223, 223, 223)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnoa)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(selecteddatetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtResFee, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(138, 138, 138))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btAccept, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbtot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(selecteddatetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtResFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btAccept)
                .addContainerGap(95, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSelectDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectDateActionPerformed
        String selectedDate = dateChooser1.getSelectedDate();
        if (!selectedDate.equals("No date selected")) {
            selecteddatetxt.setText(selectedDate);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a date first!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btSelectDateActionPerformed

    private void btAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAcceptActionPerformed
        processReservation("Accepted", true);
    } 

    private String getSelectedInclusions() {
        StringBuilder inclusions = new StringBuilder();
            if (cbgs.isSelected()) inclusions.append("Gas Stove(₱250), ");
            if (cbfh.isSelected()) inclusions.append("Function Hall(₱1500), ");
            if (cbocc.isSelected()) inclusions.append("Outside Catering Corkcage(₱2000), ");
            if (cbacr.isSelected()) inclusions.append("Extra Air-Conditioned Room(₱2500), ");
            if (inclusions.length() > 0) {
                inclusions.setLength(inclusions.length() - 2);
            }
        return inclusions.toString();
    }
    
private String convertDateToMySQLFormat(String inputDate) {
    try {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = inputFormat.parse(inputDate); 
        return outputFormat.format(date);
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
}
    
    private boolean isDateTimeAlreadyBooked(String date, String time) {
    Connection con = null;
    PreparedStatement pstCheck = null;
    ResultSet rs = null;
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
        con.setAutoCommit(false);
        String selectedTime = extractTourType(time);
        String sql = "SELECT resTime FROM reservations WHERE resDate = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')";
        pstCheck = con.prepareStatement(sql);
        pstCheck.setString(1, date);
        rs = pstCheck.executeQuery();
        boolean conflict = false;
        String conflictMessage = "";
        while (rs.next()) {
            String existingTime = extractTourType(rs.getString("resTime"));
            if ((existingTime.equals("Day Tour") || existingTime.equals("Night Tour")) && selectedTime.equals("22 Hours")) {
                conflict = true;
                conflictMessage = "❌ " + existingTime + " is already reserved. You CANNOT reserve 22 Hours!";
                break;
            }
            if ((existingTime.equals("Day Tour") && selectedTime.equals("Day Tour"))) {
                conflict = true;
                conflictMessage = "❌ Day Tour is already reserved. You CANNOT reserve Day Tour again!";
                break;
            }
            if ((existingTime.equals("Night Tour") && selectedTime.equals("Night Tour"))) {
                conflict = true;
                conflictMessage = "❌ Night Tour is already reserved. You CANNOT reserve Night Tour again!";
                break;
            }
            if (existingTime.equals("22 Hours") && (selectedTime.equals("Day Tour") || selectedTime.equals("Night Tour") || selectedTime.equals("22 Hours"))) {
                conflict = true;
                conflictMessage = "❌ 22 Hours is already reserved. No reservations allowed!";
                break;
            }
        }
        if (conflict) {
            JOptionPane.showMessageDialog(null, conflictMessage, "Booking Conflict", JOptionPane.ERROR_MESSAGE);
            con.rollback();
            return false;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstCheck != null) pstCheck.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return true;
}

private String extractTourType(String resTime) {
    if (resTime.contains("Day Tour")) return "Day Tour";
    if (resTime.contains("Night Tour")) return "Night Tour";
    if (resTime.contains("22 Hours")) return "22 Hours";
    return resTime;
    }      

private void saveReservation(String fullname, String phone, String time, String attendees, String inclusions, String date, String fee, String status) {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
        con.setAutoCommit(false);
        
        int totalFee = calculateTotalFee(time, inclusions);
        int paidFee = Integer.parseInt(fee);
        int unpaidFee = totalFee - paidFee;

        String sql = "INSERT INTO reservations (accID, resFullname, resPhone, resTime, resAttendees, resInclusions, resDate, resFee, resUnpaidFee, resStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        pst.setInt(1, accID);
        pst.setString(2, fullname);
        pst.setString(3, phone);
        pst.setString(4, time);
        pst.setString(5, attendees);
        pst.setString(6, inclusions);
        pst.setString(7, date);
        pst.setString(8, fee);
        pst.setInt(9, unpaidFee);
        pst.setString(10, status);
        
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            rs = pst.getGeneratedKeys();
            int resID = -1;
            if (rs.next()) {
                resID = rs.getInt(1);
            }
            if (resID != -1) {
                String historySql = "INSERT INTO staffhistory (accID, resID, hisFullname, hisPhone, hisAttendees, hisTime, hisDate, hisFee, hisInclusions, hisStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement historyPst = con.prepareStatement(historySql)) {
                    historyPst.setInt(1, accID);
                    historyPst.setInt(2, resID);
                    historyPst.setString(3, fullname);
                    historyPst.setString(4, phone);
                    historyPst.setString(5, attendees);
                    historyPst.setString(6, time);
                    historyPst.setString(7, date);
                    historyPst.setString(8, fee);
                    historyPst.setString(9, inclusions);
                    historyPst.setString(10, status);
                    historyPst.executeUpdate();
                }
            }
            con.commit();
            JOptionPane.showMessageDialog(this, "Reservation saved successfully as " + status + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            if (Staff.c != null) {
                Staff.c.loadHistory();
            }
            if (availabilityPanel != null) {
                availabilityPanel.refreshCalendar();
            }
        } else {
            con.rollback();
            JOptionPane.showMessageDialog(this, "Failed to save reservation.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

private int calculateTotalFee(String time, String inclusions) {
    int baseFee = 0;

    if (time.contains("Day Tour")) {
        baseFee = 10000;
    } else if (time.contains("Night Tour")) {
        baseFee = 11000;
    } else if (time.contains("22 Hours")) {
        baseFee = 19000;
    }

    int additionalFee = 0;
    if (inclusions != null && !inclusions.trim().isEmpty()) {
        if (inclusions.contains("Gas Stove")) {
            additionalFee += 250;
        }
        if (inclusions.contains("Function Hall")) {
            additionalFee += 1500;
        }
        if (inclusions.contains("Outside Catering Corkcage")) {
            additionalFee += 2000;
        }
        if (inclusions.contains("Extra Air-Conditioned Room")) {
            additionalFee += 2500;
        }
    }

    return baseFee + additionalFee;
    }//GEN-LAST:event_btAcceptActionPerformed

private void processReservation(String status, boolean requireFee) {
    String fullname = txtfullname.getText().trim();
    String phone = txtphone.getText().trim();
    String attendees = txtnoa.getText().trim();
    String rawDate = dateChooser1.getSelectedDate().trim();
    String inclusions = getSelectedInclusions();
    String time = cbtot.getSelectedItem().toString().trim();
    String feeText = txtResFee.getText().trim();
    String date = convertDateToMySQLFormat(rawDate);
        if (date == null) {
            JOptionPane.showMessageDialog(this, "Invalid date format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (isDateSuspended(date)) {
        JOptionPane.showMessageDialog(this, "❌ This date is suspended and cannot be reserved!", "Suspended Date", JOptionPane.ERROR_MESSAGE);
        return;
        }
        if (fullname.isEmpty() || phone.isEmpty() || attendees.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Some fields are empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (time.equalsIgnoreCase("Choose")) {
            JOptionPane.showMessageDialog(this, "Please select a valid time!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!fullname.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Fullname must contain only letters!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!phone.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "Phone number must contain only numbers (11 digits)!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!attendees.matches("\\d{1,2}") || Integer.parseInt(attendees) < 1 || Integer.parseInt(attendees) > 70) {
            JOptionPane.showMessageDialog(this, "Number of attendees must be between 1 and 70!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (status.equals("Pending") && isDateAcceptedOrFullyPaid(date, time)) {
        JOptionPane.showMessageDialog(this, "❌ This date and time are already Accepted or Fully Paid. You CANNOT mark it as Pending!", 
                                      "Reservation Conflict", JOptionPane.ERROR_MESSAGE);
        return;
        }
        if (!isDateTimeAlreadyBooked(date, time)) {
            return;
        }
        if (requireFee) {
            if (feeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Reservation Fee is required for Acceptance!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int fee = Integer.parseInt(feeText);
                if (fee < 3000) {
                    JOptionPane.showMessageDialog(this, "Reservation Fee must be at least 3,000 to Accept!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid fee amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        int totalFee = calculateTotalFee(time, inclusions);
        txtResFee.setText(String.valueOf(totalFee));
        
        saveReservation(fullname, phone, time, attendees, inclusions, date, feeText, status);
        
        if (availabilityPanel != null) {
        availabilityPanel.refreshCalendar();
    }
}

private boolean isDateAcceptedOrFullyPaid(String date, String time) {
    Connection con = null;
    PreparedStatement pstCheck = null;
    ResultSet rs = null;
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
        String sql = "SELECT COUNT(*) FROM reservations WHERE resDate = ? AND resTime = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')";
        pstCheck = con.prepareStatement(sql);
        pstCheck.setString(1, date);
        pstCheck.setString(2, time);
        rs = pstCheck.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstCheck != null) pstCheck.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return false;
}
    
    private void clearFields() {
        txtfullname.setText("");
        txtphone.setText("");
        txtnoa.setText("");
        selecteddatetxt.setText("");
        txtResFee.setText("");
        cbtot.setSelectedIndex(0);
        cbgs.setSelected(false);
        cbfh.setSelected(false);
        cbocc.setSelected(false);
        cbacr.setSelected(false);
    }
    
private boolean isDateSuspended(String date) {
    if (date == null) {
        System.err.println("❌ ERROR: Date is null and cannot be checked!");
        return false;
    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    boolean isSuspended = false;

    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");

        System.out.println("🔍 Checking Suspended Date: " + date);

        String sql = "SELECT COUNT(*) FROM suspended_dates WHERE dates = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, date);
        rs = pst.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("✅ Suspended Date Check for " + date + ": Found " + count + " entries");
            isSuspended = count > 0;
        }

        if (!isSuspended) {
            System.out.println("✅ Date " + date + " is **not suspended**, reservation allowed.");
        } else {
            System.out.println("🚫 Date " + date + " **is suspended**, reservation denied.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return isSuspended;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAccept;
    private javax.swing.JButton btSelectDate;
    private javax.swing.JCheckBox cbacr;
    private javax.swing.JCheckBox cbfh;
    private javax.swing.JCheckBox cbgs;
    private javax.swing.JCheckBox cbocc;
    private javax.swing.JComboBox<String> cbtot;
    private DateChooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField selecteddatetxt;
    private javax.swing.JTextField txtResFee;
    private javax.swing.JTextField txtfullname;
    private javax.swing.JTextField txtnoa;
    private javax.swing.JTextField txtphone;
    // End of variables declaration//GEN-END:variables
}
