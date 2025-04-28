package GraphsCharts;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;
import java.awt.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Calendar;

public class MonthlyGraph extends JPanel {
    
    public MonthlyGraph() {
        setLayout(new BorderLayout());
        setBackground(new Color(255,255,204));
        displayGraph();
    }
    
    private void displayGraph() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        JFreeChart barChart = ChartFactory.createBarChart(
            "Monthly Reservation Trends", currentYear +  "Months", "Reservations (%)",
            createDataset(), PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0, 102, 0));
        plot.setDomainGridlinePaint(new Color(255, 255, 204));
        plot.setRangeGridlinePaint(new Color(255, 255, 204));
        plot.setOutlineVisible(false);
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(255, 255, 102));
        renderer.setSeriesPaint(1, new Color(153, 153, 255));
        renderer.setSeriesPaint(2, new Color(102, 255, 102));
        renderer.setDrawBarOutline(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 100);
        rangeAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(10));
        rangeAxis.setLabel("Reservations (%)");
        rangeAxis.setLabelPaint(new Color(0,102,0));
        rangeAxis.setTickLabelPaint(new Color(0,102,0));
        plot.getDomainAxis().setLabelPaint(new Color(0, 102, 0));
        plot.getDomainAxis().setTickLabelPaint(new Color(0,102,0));
        barChart.getTitle().setPaint(new Color(0,102,0));
        barChart.setBackgroundPaint(new Color(255,255,204));
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(650, 300));
        chartPanel.setBackground(new Color(255,255,204));
        add(chartPanel, BorderLayout.CENTER);
    }
    
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, int[]> monthlyData = getMonthlyReservationData();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] fullMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int totalReservations = 0;
        for (int[] counts : monthlyData.values()) {
            totalReservations += counts[0] + counts[1] + counts[2];
        }
        for (int i = 0; i < months.length; i++) {
            int[] counts = monthlyData.getOrDefault(fullMonths[i], new int[]{0, 0, 0});
            if (totalReservations > 0) {
                dataset.addValue((counts[0] * 100.0) / totalReservations, "Day Tour", months[i]);
                dataset.addValue((counts[1] * 100.0) / totalReservations, "Night Tour", months[i]);
                dataset.addValue((counts[2] * 100.0) / totalReservations, "22 Hours", months[i]);
            } else {
                dataset.addValue(0, "Day Tour", months[i]);
                dataset.addValue(0, "Night Tour", months[i]);
                dataset.addValue(0, "22 Hours", months[i]);
            }
        }
        return dataset;
    }
    
    private Map<String, int[]> getMonthlyReservationData() {
        Map<String, int[]> data = new LinkedHashMap<>();
        String url = "jdbc:mysql://localhost:3306/accounts";
        String user = "root";
        String password = "sha@123";
        String query = "SELECT DATE_FORMAT(resDate, '%M') AS month, " +
                       "SUM(CASE WHEN resTime LIKE 'Day Tour%' THEN 1 ELSE 0 END) AS day_tour, " +
                       "SUM(CASE WHEN resTime LIKE 'Night Tour%' THEN 1 ELSE 0 END) AS night_tour, " +
                       "SUM(CASE WHEN resTime LIKE '22 Hours%' THEN 1 ELSE 0 END) AS hours_22 " +
                       "FROM reservations GROUP BY month ORDER BY FIELD(month, 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December')";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("month"), new int[]{
                    rs.getInt("day_tour"),
                    rs.getInt("night_tour"),
                    rs.getInt("hours_22")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
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
