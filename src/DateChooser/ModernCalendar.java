
package DateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class ModernCalendar extends javax.swing.JPanel {
    
    private JLabel lblMonthYear;
    private JButton btnPrev, btnNext;
    private JPanel calendarPanel;
    private Calendar calendar;
    private Map<String, Set<String>> reservedDates;
    public Set<String> suspendedDates;
    
    public ModernCalendar() { 
        setLayout(new BorderLayout());
        setOpaque(false);
        calendar = Calendar.getInstance();
        reservedDates = new HashMap<>();
        suspendedDates = new HashSet<>();
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(450, 60));
        btnPrev = createNavButton("◄");
        btnPrev.addActionListener(e -> changeMonth(-1));
        btnNext = createNavButton("►");
        btnNext.addActionListener(e -> changeMonth(1));
        lblMonthYear = new JLabel("", JLabel.CENTER);
        lblMonthYear.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblMonthYear.setForeground(new Color(255, 255, 204));
        headerPanel.add(btnPrev, BorderLayout.WEST);
        headerPanel.add(lblMonthYear, BorderLayout.CENTER);
        headerPanel.add(btnNext, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);
        calendarPanel = new JPanel(new GridLayout(7, 7, 5, 5));
        calendarPanel.setOpaque(false);
        add(calendarPanel, BorderLayout.CENTER);
        fetchSuspendedDates();
        updateCalendar();
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(new Color(0, 102, 0));
        button.setForeground(new Color(255, 255, 204));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    public void updateCalendar() {
        calendarPanel.removeAll();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        fetchReservedDates();
        fetchSuspendedDates();
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel lbl = new JLabel(day, JLabel.CENTER);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            lbl.setForeground(day.equals("Sun") ? Color.RED : new Color(0, 102, 0));
            lbl.setOpaque(true);
            lbl.setBackground(new Color(255, 255, 204));
            lbl.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 0), 1));
            calendarPanel.add(lbl);
        }
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < firstDay; i++) {
            calendarPanel.add(new JLabel(""));
        }
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar today = Calendar.getInstance();
        for (int day = 1; day <= daysInMonth; day++) {
            Calendar dateToCheck = (Calendar) calendar.clone();
            dateToCheck.set(Calendar.DAY_OF_MONTH, day);
            String dateStr = String.format("%d-%02d-%02d",
                    dateToCheck.get(Calendar.YEAR),
                    dateToCheck.get(Calendar.MONTH) + 1,
                    day);
            JLabel lblDay = new JLabel(String.valueOf(day), JLabel.CENTER);
            lblDay.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblDay.setOpaque(true);
            lblDay.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 0), 1));
            if (dateToCheck.equals(today)) {
                lblDay.setBackground(new Color(0, 204, 255));
                lblDay.setForeground(Color.BLACK);
            }
            else if (suspendedDates.contains(dateStr)) {
                lblDay.setBackground(new Color(255,102,102));
            }
            else if (reservedDates.containsKey(dateStr)) {
                Set<String> reservationTypes = reservedDates.get(dateStr);
                if (reservationTypes.contains("22 HOURS")) {
                    lblDay.setBackground(new Color(102, 255, 102));
                } else if (reservationTypes.contains("DAY TOUR") && reservationTypes.contains("NIGHT TOUR")) {
                    lblDay.setBackground(new Color(102, 255, 102));
                } else if (reservationTypes.contains("DAY TOUR")) {
                    lblDay.setBackground(new Color(255, 255, 102));
                } else if (reservationTypes.contains("NIGHT TOUR")) {
                    lblDay.setBackground(new Color(153, 153, 255));
                }
                lblDay.setForeground(Color.BLACK);
            } else {
                lblDay.setBackground(new Color(255, 255, 204));
                lblDay.setForeground(new Color(0, 102, 0));
            }

            calendarPanel.add(lblDay);
        }
        while (calendarPanel.getComponentCount() < 49) {
            calendarPanel.add(new JLabel(""));
        }
        lblMonthYear.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()) + " " + calendar.get(Calendar.YEAR));
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

public void fetchReservedDates() {
        reservedDates.clear();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
             PreparedStatement pst = con.prepareStatement("SELECT resDate, resTime FROM reservations WHERE resStatus IN ('Accepted', 'Fully Paid')")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String date = rs.getString("resDate");
                String time = rs.getString("resTime").toLowerCase();
                reservedDates.putIfAbsent(date, new HashSet<>());
                if (time.contains("22 hours")) {
                    reservedDates.get(date).add("22 HOURS");
                }
                if (time.contains("day")) {
                    reservedDates.get(date).add("DAY TOUR");
                }
                if (time.contains("night")) {
                    reservedDates.get(date).add("NIGHT TOUR");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeMonth(int amount) {
        calendar.add(Calendar.MONTH, amount);
        updateCalendar();
    }
    
    public void refreshCalendar() {
    updateCalendar();
    }
    
    public void fetchSuspendedDates() {
        suspendedDates.clear();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
             PreparedStatement pst = con.prepareStatement("SELECT dates FROM suspended_dates")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                suspendedDates.add(rs.getString("dates"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
