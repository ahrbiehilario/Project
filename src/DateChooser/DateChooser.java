
package DateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DateChooser extends javax.swing.JPanel {

    private JLabel lblMonthYear;
    private JButton btnPrev, btnNext;
    private JPanel calendarPanel;
    private Calendar calendar, today;
    private JButton lastSelectedButton = null;
    private String selectedDate = "No date selected";
    private ModernCalendar modernCalendar;
    private Map<String, JButton> dateLabels = new HashMap<>();
    private Set<String> suspendedDates = new HashSet<>(); 
    
    public DateChooser(ModernCalendar modernCalendar) {
        this.modernCalendar = modernCalendar;
        initialize();
    }

    public DateChooser() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 204));
        calendar = Calendar.getInstance();
        today = Calendar.getInstance();
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 0));
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
        calendarPanel = new JPanel(new GridLayout(7, 7, 8, 8));
        calendarPanel.setBackground(new Color(255, 255, 204));
        add(calendarPanel, BorderLayout.CENTER);
        updateCalendar();
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(new Color(255, 255, 204));
        button.setForeground(new Color(0, 102, 0));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        return button;
    }
    
    private void loadSuspendedDates() {
    suspendedDates.clear();
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
         PreparedStatement pst = con.prepareStatement("SELECT dates FROM suspended_dates");
         ResultSet rs = pst.executeQuery()) {

        SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat queryFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {
            String rawDate = rs.getString("dates");
            try {
                String formattedDate = queryFormat.format(dbFormat.parse(rawDate));
                
                if (isDateReserved(formattedDate)) {
                    continue;
                }

                String bookedTime = getBookedTime(formattedDate);
                if (!bookedTime.equals("Day Tour") && !bookedTime.equals("Night Tour") && !bookedTime.equals("22 Hours")) {
                    suspendedDates.add(formattedDate);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    private void updateCalendar() {
        calendarPanel.removeAll();
        dateLabels.clear();
        loadSuspendedDates();
        calendarPanel.setLayout(new GridLayout(7, 7, 8, 8));
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < days.length; i++) {
            JLabel lbl = new JLabel(days[i], JLabel.CENTER);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            lbl.setForeground(i == 0 ? Color.RED : new Color(0, 102, 0));
            lbl.setOpaque(true);
            lbl.setBackground(new Color(255, 255, 204));
            lbl.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 0), 1));
            calendarPanel.add(lbl);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < firstDay; i++) {
            calendarPanel.add(new JLabel(""));
        }
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayMonth = today.get(Calendar.MONTH);
        int todayYear = today.get(Calendar.YEAR);
        for (int day = 1; day <= daysInMonth; day++) {
            Calendar temp = (Calendar) calendar.clone();
            temp.set(Calendar.DAY_OF_MONTH, day);
            JButton btnDay = new JButton(String.valueOf(day));
            styleDateButton(btnDay, temp);
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(temp.getTime());
            dateLabels.put(dateString, btnDay);
            String bookedTime = getBookedTime(dateString);
            if (temp.before(today) || 
                (temp.get(Calendar.YEAR) == todayYear &&
                 temp.get(Calendar.MONTH) == todayMonth &&
                 temp.get(Calendar.DAY_OF_MONTH) == todayDay)) {
                btnDay.setEnabled(false);
                btnDay.setBackground(new Color(230, 230, 230));
            } else {
                if (bookedTime.equals("22 Hours")) {
                    btnDay.setEnabled(false);
                    btnDay.setBackground(new Color(200, 0, 0)); 
                    btnDay.setToolTipText("❌ Fully booked (22 Hours). No reservations allowed.");
                } else if (bookedTime.equals("Day Tour")) {
                    btnDay.setToolTipText("✅ Only Night Tour is available.");
                } else if (bookedTime.equals("Night Tour")) {
                    btnDay.setToolTipText("✅ Only Day Tour is available.");
                }

                btnDay.addActionListener(new DateSelectListener(temp, btnDay));
            }
            calendarPanel.add(btnDay);
        }
        while (calendarPanel.getComponentCount() < 49) {
            calendarPanel.add(new JLabel(""));
        }
        lblMonthYear.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, getLocale()) + " " + calendar.get(Calendar.YEAR));
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    private String getBookedTime(String date) {
        String bookedTime = "";
        boolean isDayTourBooked = false;
        boolean isNightTourBooked = false;
        boolean is22HoursBooked = false;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
             PreparedStatement pst = con.prepareStatement("SELECT resTime FROM reservations WHERE resDate = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')")) {
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String time = rs.getString("resTime");
                if (time.equalsIgnoreCase("22 Hours")) {
                    is22HoursBooked = true;
                    break;
                } else if (time.equalsIgnoreCase("Day Tour")) {
                    isDayTourBooked = true;
                } else if (time.equalsIgnoreCase("Night Tour")) {
                    isNightTourBooked = true;
                }
            }
            rs.close();
            if (is22HoursBooked) {
                return "22 Hours";
            } else if (isDayTourBooked) {
                return "Day Tour";
            } else if (isNightTourBooked) {
                return "Night Tour";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return bookedTime;
    }
    
    private boolean isDateReserved(String date) {
    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123");
         PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM reservations WHERE resDate = ? AND (resStatus = 'Accepted' OR resStatus = 'Fully Paid')")) {
        pst.setString(1, date);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public void refreshCalendar() {
        updateCalendar();
    }

    private void changeMonth(int amount) {
        calendar.add(Calendar.MONTH, amount);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        updateCalendar();
    }

    private void styleDateButton(JButton button, Calendar date) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(255, 255, 204));
        button.setForeground(new Color(0, 102, 0));
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 0), 2));
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(50, 50));
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(new Color(0, 102, 0));
                    button.setForeground(new Color(255, 255, 204));
                }
            }
            public void mouseExited(MouseEvent evt) {
                if (button != lastSelectedButton && button.isEnabled()) {
                    button.setBackground(new Color(255, 255, 204));
                    button.setForeground(new Color(0, 102, 0));
                }
            }
            public void mousePressed(MouseEvent evt) {
                if (button.isEnabled()) {
                    if (lastSelectedButton != null) {
                        lastSelectedButton.setBackground(new Color(255, 255, 204));
                        lastSelectedButton.setForeground(new Color(0, 102, 0));
                    }
                    button.setBackground(new Color(0, 204, 102));
                    button.setForeground(Color.BLACK);
                    lastSelectedButton = button;
                }
            }
        });
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    private class DateSelectListener implements ActionListener {
        private final Calendar selectedCalendar;
        private final JButton button;

        public DateSelectListener(Calendar selectedCalendar, JButton button) {
            this.selectedCalendar = selectedCalendar;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (lastSelectedButton != null) {
                lastSelectedButton.setBackground(new Color(255, 255, 204));
                lastSelectedButton.setForeground(new Color(0, 102, 0));
            }
            button.setBackground(new Color(0, 204, 102));
            button.setForeground(Color.BLACK);
            lastSelectedButton = button;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            selectedDate = sdf.format(selectedCalendar.getTime());
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
