
package Content;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class History extends JPanel {
    
    private JPanel historyPanel;

    public History() {
        setOpaque(false);
        setupUI();
        loadHistory();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon(getClass().getResource("/icons/BGPANE.jpg")).getImage();
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        historyPanel = new JPanel();
        historyPanel.setOpaque(false);
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(historyPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }
    
public void loadHistory() {
    historyPanel.removeAll();
    ArrayList<String[]> historyData = fetchHistoryData();
    for (String[] record : historyData) {
        String status = record[0];
        String formattedDate = record[1];
        String formattedTime = record[2];
        String resID = record[3];
        JPanel notificationContainer = createNotificationContainer(status, formattedDate, formattedTime, resID);
        historyPanel.add(Box.createVerticalStrut(10));
        historyPanel.add(notificationContainer);
    }
    historyPanel.revalidate();
    historyPanel.repaint();
}

private JPanel createNotificationContainer(String status, String dateSent, String timeSent, String resID) {
        JPanel container = new JPanel(new BorderLayout(10, 10));
        container.setPreferredSize(new Dimension(1075, 80));
        container.setMaximumSize(new Dimension(1075, 80));
        container.setBackground(new Color(255, 255, 204, 180));
        container.setOpaque(true);
        container.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 0), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JLabel iconLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/info.png"));
        Image scaledIcon = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        iconLabel.setIcon(new ImageIcon(scaledIcon));
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        JLabel messageLabel = new JLabel("You marked a reservation as ");
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        messageLabel.setForeground(new Color(0, 102, 0));
        JLabel statusLabel = new JLabel(status.toUpperCase());
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setForeground(getStatusColor(status));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setOpaque(false);
        statusPanel.add(messageLabel);
        statusPanel.add(statusLabel);
        JLabel dateTimeLabel = new JLabel(dateSent + " | " + timeSent);
        dateTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        dateTimeLabel.setForeground(new Color(0, 102, 0));
        textPanel.add(statusPanel);
        textPanel.add(dateTimeLabel);
        container.add(iconLabel, BorderLayout.WEST);
        container.add(textPanel, BorderLayout.CENTER);
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showReservationDetails(resID);
            }
        });
        return container;
    }

    private Color getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "accepted": return new Color(51, 153, 0);
            case "canceled": return new Color(255, 102, 102);
            case "fully paid": return new Color(128, 128, 255);
            default: return new Color(0, 102, 0);
        }
    }

    private void showReservationDetails(String resID) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
         PreparedStatement pst = con.prepareStatement("SELECT resFullname, resPhone, resAttendees, resTime, resDate, resFee, resInclusions, resStatus FROM reservations WHERE resID = ?")) {
        pst.setString(1, resID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String status = rs.getString("resStatus");
            String message = "<html><b>Fullname:</b> " + rs.getString("resFullname") + "<br>"
                    + "<b>Phone no:</b> " + rs.getString("resPhone") + "<br>"
                    + "<b>Attendees:</b> " + rs.getString("resAttendees") + "<br>"
                    + "<b>Time of Tour:</b> " + rs.getString("resTime") + "<br>"
                    + "<b>Date:</b> " + rs.getString("resDate") + "<br>"
                    + "<b>Fee:</b> " + rs.getString("resFee") + "<br>"
                    + "<b>Inclusions:</b> " + rs.getString("resInclusions") + "<br><br>"
                    + "<center><b>Status: </b><font color='" + getStatusHexColor(status) + "'>" + status.toUpperCase() + "</font></center></html>";
            JOptionPane.showMessageDialog(this, message, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private String getStatusHexColor(String status) {
        switch (status.toLowerCase()) {
            case "accepted": return "#00FF00";
            case "canceled": return "#FF6666";
            case "fully paid": return "#8080FF";
            default: return "#006600";
        }
    }

private ArrayList<String[]> fetchHistoryData() {
    ArrayList<String[]> historyList = new ArrayList<>();
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
         PreparedStatement pst = con.prepareStatement("SELECT hisStatus, hisTimestamp, resID FROM staffhistory ORDER BY hisTimestamp DESC");
         ResultSet rs = pst.executeQuery()) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/y");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Manila"));
        timeFormat.setTimeZone(java.util.TimeZone.getTimeZone("Asia/Manila"));
        while (rs.next()) {
            String status = rs.getString("hisStatus");
            String resID = rs.getString("resID");
            Timestamp timestamp = rs.getTimestamp("hisTimestamp");
            String formattedDate = dateFormat.format(timestamp);
            String formattedTime = timeFormat.format(timestamp);
            System.out.println("Fetched status: " + status);
            if (status == null) {
                System.err.println("Warning: hisStatus is null for resID: " + resID);
                status = "Unknown";
            }
            historyList.add(new String[]{status, formattedDate, formattedTime, resID});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return historyList;
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
