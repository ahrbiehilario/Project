
package ContentAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.table.JTableHeader;

public class ReservationsA extends javax.swing.JPanel {
    
    private JTable recordsTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;
    private JLabel searchLabel;
    
    public ReservationsA() {
        initComponents();
        setupUI();
        refreshTable();
    }
    
private void setupUI() {
        setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(0, 102, 0));
        searchLabel = new JLabel("Search Full Name:");
        searchLabel.setForeground(new Color(255, 255, 204));
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton = new JButton("Search");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
        });
        searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        searchButton.setBackground(new Color(255, 255, 204));
        searchButton.setForeground(new Color(0, 102, 0));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 204), 1));
        searchButton.addActionListener(e -> searchReservation());
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);
        
        String[] columns = {"ID", "Full Name", "Phone", "Date", "Time", "Attendees", "Inclusions", "Fee", "Unpaid Fee", "Damage Fee", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        recordsTable = new JTable(tableModel);
        recordsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recordsTable.setBackground(new Color(0, 102, 0));
        recordsTable.setFont(recordsTable.getFont().deriveFont(Font.PLAIN, 16f));
        recordsTable.setRowHeight(40);
        recordsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) component;
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                renderer.setVerticalAlignment(SwingConstants.CENTER);
                component.setForeground(new Color(255, 255, 204));
                
                if (column == 7 || column == 8 || column == 9) {
                    if (value != null) {
                        int feeValue = Integer.parseInt(value.toString());
                        setText("₱" + String.format("%,d", feeValue));
                    }
                }
                
                if (column == 10 && value != null) {
                    String status = value.toString();
                    if ("Accepted".equals(status)) {
                        component.setForeground(new Color(0, 255, 0));
                        component.setFont(component.getFont().deriveFont(Font.BOLD));
                    } else if ("Fully Paid".equals(status)) {
                        component.setForeground(new Color(128, 128, 255));
                        component.setFont(component.getFont().deriveFont(Font.BOLD));
                    } else if ("Canceled".equals(status)) {
                        component.setForeground(new Color(255, 102, 102));
                        component.setFont(component.getFont().deriveFont(Font.BOLD));
                    } else if ("Suspended".equals(status)) {
                        component.setForeground(new Color(255, 102, 102));
                        component.setFont(component.getFont().deriveFont(Font.BOLD));
                    }
                }

                if (column == 10 && recordsTable.getValueAt(row, 9) != null) {
                    Integer damageFee = (Integer) recordsTable.getValueAt(row, 9);
                    if (damageFee != null && damageFee > 0) {
                        component.setForeground(Color.GRAY);
                    }
                }
                
                setCellBorder(component);
                return component;
            }
        });

        JTableHeader header = recordsTable.getTableHeader();
        header.setBackground(new Color(255, 255, 204));
        header.setForeground(new Color(0, 102, 0));
        header.setFont(recordsTable.getFont().deriveFont(Font.BOLD, 16f));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) component;
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                renderer.setVerticalAlignment(SwingConstants.CENTER);
                component.setForeground(new Color(0, 102, 0));
                component.setBackground(new Color(255, 255, 204));
                component.setFont(component.getFont().deriveFont(Font.BOLD));
                setCellBorder(component);
                return component;
            }
        });

        recordsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = recordsTable.rowAtPoint(e.getPoint());
                int column = recordsTable.columnAtPoint(e.getPoint());

                System.out.println("Row: " + row + ", Column: " + column);

                if (row != -1 && column != -1) {
                    String columnName = recordsTable.getColumnName(column);
                    System.out.println("Column Name: " + columnName);
                    if ("Status".equals(columnName)) {
                        handleStatusClick(row);
                    } else if ("Inclusions".equals(columnName)) {
                        showInclusions(row);
                    } else if ("Fee".equals(columnName)) {
                        showFeeStatus(row);
                    } else if ("Damage Fee".equals(columnName)) {
                        handleDamageFeeClick(row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(recordsTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);
    }

public void refreshTable() {
        System.out.println("Refreshing table...");
        tableModel.setRowCount(0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
             PreparedStatement pst = con.prepareStatement(
                     "SELECT resID, resFullname, resPhone, resDate, resTime, resAttendees, resInclusions, resFee, resUnpaidFee, resDmgFee, resStatus " +
                             "FROM reservations ORDER BY resID DESC");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int resID = rs.getInt("resID");
                String fullName = rs.getString("resFullname");
                String phone = rs.getString("resPhone");
                String date = rs.getString("resDate");
                String time = rs.getString("resTime");
                int attendees = rs.getInt("resAttendees");
                String inclusions = rs.getString("resInclusions");
                int fee = rs.getInt("resFee");
                int unpaidFee = rs.getInt("resUnpaidFee");
                int dmgFee = rs.getInt("resDmgFee");
                String status = rs.getString("resStatus");

                tableModel.addRow(new Object[]{
                    resID, fullName, phone, date, time, attendees, inclusions, fee, unpaidFee, dmgFee, status
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading reservations: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

private void showFeeStatus(int row) {
    String status = (String) recordsTable.getValueAt(row, 10);
    int originalPaidFee = (Integer) recordsTable.getValueAt(row, 7); 
    int resID = (Integer) recordsTable.getValueAt(row, 0); 

    if ("Accepted".equals(status) || "Fully Paid".equals(status)) {
        Object[] options = {"Add Discount", "Cancel"}; 
        int choice = JOptionPane.showOptionDialog(this, 
            "Current Paid Fee: ₱" + String.format("%,d", originalPaidFee) + 
            "\nWould you like to add a discount?",
            "Fee Status", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]);

        if (choice == 0) {
            String discountInput = JOptionPane.showInputDialog(this, 
                "Enter discount percentage (e.g., 10 for 10%):", 
                "Apply Discount", 
                JOptionPane.PLAIN_MESSAGE);
            if (discountInput != null && !discountInput.trim().isEmpty()) {
                try {
                    double discountPercent = Double.parseDouble(discountInput);
                    if (discountPercent < 0 || discountPercent > 100) {
                        JOptionPane.showMessageDialog(this, 
                            "Invalid discount. Enter a value between 0 and 100.", 
                            "Invalid Input", 
                            JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    double discountAmount = originalPaidFee * (discountPercent / 100.0);
                    int discountedFee = (int) Math.round(originalPaidFee - discountAmount);

                    int confirm = JOptionPane.showConfirmDialog(this,
                        "Original Fee: ₱" + String.format("%,d", originalPaidFee) +
                        "\nDiscount: " + discountPercent + "%" +
                        "\nNew Fee: ₱" + String.format("%,d", discountedFee) +
                        "\n\nApply this discount?",
                        "Confirm Discount", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        updateReservationFee(resID, discountedFee); 
                        refreshTable(); 
                        JOptionPane.showMessageDialog(this, 
                            "Discount applied successfully.", 
                            "Success", 
                            JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Invalid number format for discount.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "Cannot modify fee for reservations with status: " + status, 
            "Action Denied", 
            JOptionPane.WARNING_MESSAGE);
    }
}

private void handleStatusClick(int row) {
    int resID = (int) recordsTable.getValueAt(row, 0); 
    Object statusValue = recordsTable.getValueAt(row, 10); 

    if (statusValue instanceof String) {

        String[] options = {"Cancel Reservation", "Mark as Fully Paid"};
        int choice = JOptionPane.showOptionDialog(this, "What would you like to do?", "Reservation Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            updateReservationStatus(resID, "Canceled");
            setFeesToZero(resID);
            canceledReservationFee(resID);
            refreshTable();
        } else if (choice == 1) {

            String resTime = (String) recordsTable.getValueAt(row, 4);
            String inclusions = (String) recordsTable.getValueAt(row, 6);
            int damageFee = (Integer) recordsTable.getValueAt(row, 9);
            int totalFee = calculateTotalFee(resTime, inclusions) + damageFee;

            updateReservationStatus(resID, "Fully Paid");
            updateReservationFee(resID, totalFee);
            refreshTable();

            if (totalFee == (Integer) recordsTable.getValueAt(row, 7)) {
                setFeesToZero(resID);
                refreshTable();
            }
        }
    } else {
        System.out.println("Status is not a String: " + statusValue + " (Type: " + statusValue.getClass().getName() + ")");
    }
}

private void canceledReservationFee(int resID) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateFeesSQL = "UPDATE reservations SET resUnpaidFee = 0, resDmgFee = 0, resFee = 3000 WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateFeesSQL)) {
            pst.setInt(1, resID);
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating unpaid fee and damage fee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateReservationFee(int resID, int newFee) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateFeeSQL = "UPDATE reservations SET resFee = ? WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateFeeSQL)) {
            pst.setInt(1, newFee);
            pst.setInt(2, resID);
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating fee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void setFeesToZero(int resID) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateFeesSQL = "UPDATE reservations SET resUnpaidFee = 0, resDmgFee = 0 WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateFeesSQL)) {
            pst.setInt(1, resID);
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating unpaid fee and damage fee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void setCellBorder(Component component) {
        if (component instanceof JComponent) {
            JComponent jComponent = (JComponent) component;
            jComponent.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 204), 1));
        }
    }

private void updateReservationStatus(int resID, String newStatus) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateReservationSQL = "UPDATE reservations SET resStatus = ? WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateReservationSQL)) {
            pst.setString(1, newStatus);
            pst.setInt(2, resID);
            pst.executeUpdate();
        }
        String fetchReservationSQL = "SELECT accID, resFullname, resPhone, resTime, resAttendees, resDate, resInclusions, resFee " +
                                     "FROM reservations WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(fetchReservationSQL)) {
            pst.setInt(1, resID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int accID = rs.getInt("accID");
                    String hisFullname = rs.getString("resFullname");
                    String hisPhone = rs.getString("resPhone");
                    String hisTime = rs.getString("resTime");
                    int hisAttendees = rs.getInt("resAttendees");
                    String hisDate = rs.getString("resDate");
                    String hisInclusions = rs.getString("resInclusions");
                    String hisFee = rs.getString("resFee");
                    String insertHistorySQL = "INSERT INTO staffhistory (resID, accID, hisFullname, hisPhone, hisTime, hisAttendees, hisDate, hisInclusions, hisFee, hisStatus, hisTimestamp) " +
                                              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
                    try (PreparedStatement insertPst = con.prepareStatement(insertHistorySQL)) {
                        insertPst.setInt(1, resID);
                        insertPst.setInt(2, accID);
                        insertPst.setString(3, hisFullname);
                        insertPst.setString(4, hisPhone);
                        insertPst.setString(5, hisTime);
                        insertPst.setInt(6, hisAttendees);
                        insertPst.setString(7, hisDate);
                        insertPst.setString(8, hisInclusions);
                        insertPst.setString(9, hisFee);
                        insertPst.setString(10, newStatus);
                        insertPst.executeUpdate();
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Reservation marked as " + newStatus, "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
        
    private void showInclusions(int row) {
        String inclusions = (String) recordsTable.getValueAt(row, 6); 
        JOptionPane.showMessageDialog(this, "Inclusions: " + inclusions, "Reservation Inclusions", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void searchReservation() {
        String fullName = searchField.getText().trim();
        if (fullName.isEmpty()) {
            refreshTable();
            return;
        }

        tableModel.setRowCount(0);
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
             PreparedStatement pst = con.prepareStatement(
                     "SELECT resID, resFullname, resPhone, resDate, resTime, resAttendees, resInclusions, resFee, resStatus " +
                             "FROM reservations WHERE resFullname LIKE ? ORDER BY resDate DESC, resID DESC")) {
            pst.setString(1, "%" + fullName + "%");
            ResultSet rs = pst.executeQuery();
            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                tableModel.addRow(new Object[]{
                        rs.getInt("resID"),
                        rs.getString("resFullname"),
                        rs.getString("resPhone"),
                        rs.getString("resDate"),
                        rs.getString("resTime"),
                        rs.getInt("resAttendees"),
                        rs.getString("resInclusions"),
                        rs.getString("resFee"),
                        rs.getString("resStatus")
                });
            }
            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "No record found for: " + fullName, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching reservation: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
private void handleDamageFeeClick(int row) {
    int resID = (Integer) recordsTable.getValueAt(row, 0);
    int currentDamageFee = (Integer) recordsTable.getValueAt(row, 9);
    String status = (String) recordsTable.getValueAt(row, 10); 

    if (!"Fully Paid".equals(status)) {
        int currentUnpaidFee = (Integer) recordsTable.getValueAt(row, 8);
        int newUnpaidFee = currentUnpaidFee + currentDamageFee;

        updateUnpaidFee(resID, newUnpaidFee);
        JOptionPane.showMessageDialog(this, 
            "You cannot add a Damage Fee yet.", 
            "Error", 
            JOptionPane.INFORMATION_MESSAGE);
        return; 
    }

    String[] options = {"Add Damage Fee", "Add Paid Fee"};
    int choice = JOptionPane.showOptionDialog(this, 
        "Current Damage Fee: ₱" + String.format("%,d", currentDamageFee) + 
        "\nChoose an action:", 
        "Update Damage Fee", 
        JOptionPane.DEFAULT_OPTION, 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        options, 
        options[0]);

    if (choice == 0) { 
        String input = JOptionPane.showInputDialog(this, 
            "Enter amount to add to damage fee:", 
            "Update Damage Fee", 
            JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int additionalDamageFee = Integer.parseInt(input);
                if (additionalDamageFee < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Damage fee cannot be negative.", 
                        "Invalid Input", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int newDamageFee = currentDamageFee + additionalDamageFee;
                int currentUnpaidFee = (Integer) recordsTable.getValueAt(row, 8);
                int newUnpaidFee = currentUnpaidFee + additionalDamageFee;

                updateDamageFee(resID, newDamageFee);
                updateUnpaidFee(resID, newUnpaidFee); 
                refreshTable();
                JOptionPane.showMessageDialog(this, 
                    "Damage fee updated successfully. New Damage Fee: ₱" + String.format("%,d", newDamageFee), 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid number format for damage fee.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    } else if (choice == 1) { 
        String input = JOptionPane.showInputDialog(this, 
            "Enter amount to add to paid fee:", 
            "Add Paid Fee", 
            JOptionPane.PLAIN_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int additionalPaidFee = Integer.parseInt(input);
                if (additionalPaidFee < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Paid fee cannot be negative.", 
                        "Invalid Input", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int originalPaidFee = (Integer) recordsTable.getValueAt(row, 7);
                int newPaidFee = originalPaidFee + additionalPaidFee;

                int newDamageFee = currentDamageFee - additionalPaidFee;
                if (newDamageFee < 0) {
                    JOptionPane.showMessageDialog(this, 
                        "Damage fee cannot be negative after adjustment.", 
                        "Invalid Input", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int currentUnpaidFee = (Integer) recordsTable.getValueAt(row, 8); 
                int newUnpaidFee = currentUnpaidFee - additionalPaidFee; 

                updateDamageFee(resID, newDamageFee);
                updateReservationFee(resID, newPaidFee);
                updateUnpaidFee(resID, newUnpaidFee);
                refreshTable();
                JOptionPane.showMessageDialog(this, 
                    "Paid fee updated successfully.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Invalid number format for paid fee.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

private void updateDamageFee(int resID, int newDamageFee) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateDamageFeeSQL = "UPDATE reservations SET resDmgFee = ? WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateDamageFeeSQL)) {
            pst.setInt(1, newDamageFee);
            pst.setInt(2, resID);
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating damage fee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private int calculateTotalFee(String resTime, String inclusions) {
    int baseFee = 0;
    if (resTime.contains("Night Tour")) {
        baseFee = 11000;
    } else if (resTime.contains("Day Tour")) {
        baseFee = 10000;
    } else if (resTime.contains("22 Hours")) {
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
}

private void updateUnpaidFee(int resID, int unpaidFee) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String updateUnpaidFeeSQL = "UPDATE reservations SET resUnpaidFee = ? WHERE resID = ?";
        try (PreparedStatement pst = con.prepareStatement(updateUnpaidFeeSQL)) {
            pst.setInt(1, unpaidFee);
            pst.setInt(2, resID);
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error updating unpaid fee: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
