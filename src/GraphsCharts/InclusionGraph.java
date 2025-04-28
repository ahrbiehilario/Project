
package GraphsCharts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class InclusionGraph extends javax.swing.JPanel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/accounts";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sha@123";

    public InclusionGraph() {
        initComponents();
        createInclusionPieChart();
    }

    private void createInclusionPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> inclusionCountMap = new HashMap<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT resInclusions FROM reservations";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String inclusions = rs.getString("resInclusions");
                    String[] inclusionList = inclusions.split(",");
                    for (String inclusion : inclusionList) {
                        inclusion = inclusion.trim();
                        inclusionCountMap.put(inclusion, inclusionCountMap.getOrDefault(inclusion, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Integer> entry : inclusionCountMap.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart pieChart = ChartFactory.createPieChart(
            "Most Chosen Inclusions",
            dataset,
            true,
            true,
            false
        );
        pieChart.getTitle().setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        pieChart.getTitle().setPaint(new java.awt.Color(0, 102, 0));
        pieChart.setBackgroundPaint(new java.awt.Color(255, 255, 204));
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setLabelPaint(new java.awt.Color(0, 102, 0));
        plot.setSectionPaint("Gas Stove", new java.awt.Color(51, 204, 0));
        plot.setSectionPaint("Function Hall", new java.awt.Color(255, 204, 0));
        plot.setSectionPaint("Extra Air-Conditioned Room", new java.awt.Color(0, 102, 0));
        plot.setSectionPaint("Outside Catering Corkcage", new java.awt.Color(255, 102, 102));
        plot.setBackgroundPaint(new java.awt.Color(0,102,0));
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 420));
        chartPanel.setBackground(new java.awt.Color(255, 255, 204));
        this.setLayout(new java.awt.BorderLayout());
        this.add(chartPanel, java.awt.BorderLayout.CENTER);
        this.setBackground(new java.awt.Color(255, 255, 204));
        this.setForeground(new java.awt.Color(0, 102, 0));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 204));

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
