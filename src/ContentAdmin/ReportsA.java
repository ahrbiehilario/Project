
package ContentAdmin;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class ReportsA extends javax.swing.JPanel {
    
    private String reportName;
    private byte[] reportPDF;
    private JPanel notificationsContainer;
    private JScrollPane scrollPane;
    private final java.util.Map<Integer, JPanel> reportPanels = new java.util.HashMap<>();
    private final java.util.Set<Integer> pastReports = new java.util.HashSet<>();
    private final File pastReportsFile = new File("past_reports.txt");

    public ReportsA() {
        initComponents();
        setOpaque(false);
        setupScrollPane();
        loadPastReportsFromFile();
        loadAllReports();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon(getClass().getResource("/icons/BGPANE.jpg")).getImage();
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void savePastReportIdToFile(int reportId) {
    if (pastReports.contains(reportId)) return;
    pastReports.add(reportId);
    try (FileWriter writer = new FileWriter(pastReportsFile, true)) {
        writer.write(reportId + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    private void loadPastReportsFromFile() {
    if (!pastReportsFile.exists()) return;

    try (BufferedReader reader = new BufferedReader(new FileReader(pastReportsFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                pastReports.add(Integer.parseInt(line.trim()));
            } catch (NumberFormatException ignored) {}
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
    private void setupScrollPane() {
    notificationsContainer = new JPanel();
    notificationsContainer.setLayout(new BoxLayout(notificationsContainer, BoxLayout.Y_AXIS));
    notificationsContainer.setOpaque(false);
    notificationsContainer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
    scrollPane = new JScrollPane(notificationsContainer);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setOpaque(false);
    scrollPane.setBorder(null);
    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);
}
    
    private void loadAllReports() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
            String sql = "SELECT id, report_name FROM reports ORDER BY id DESC";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int reportId = rs.getInt("id");
                    String reportName = rs.getString("report_name");
                    showNotification(reportId, reportName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showNotification(int reportId, String reportName) {
    if (reportName == null) return;

    RoundedPanel notificationPanel = new RoundedPanel(30);
    notificationPanel.setLayout(new BorderLayout(15, 0));
    notificationPanel.setBackground(new Color(255, 255, 204, 200));
    Dimension fixedSize = new Dimension(1000, 80);
    notificationPanel.setPreferredSize(fixedSize);
    notificationPanel.setMaximumSize(fixedSize);
    notificationPanel.setMinimumSize(fixedSize);

    JLabel label = new JLabel();
    label.setFont(new Font("Arial", Font.BOLD, 14));
    notificationPanel.add(label, BorderLayout.WEST);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 25));
    buttonPanel.setOpaque(false);

    JButton viewButton = new JButton("View");
    JButton saveButton = new JButton("Save");
    styleButton(viewButton);
    styleButton(saveButton);

    viewButton.addActionListener(e -> {
        viewReport(reportId);
        if (!pastReports.contains(reportId)) {
            markAsPastReport(notificationPanel, label, reportId, reportName);
        }
    });

    saveButton.addActionListener(e -> {
        saveReport(reportId);
        if (!pastReports.contains(reportId)) {
            markAsPastReport(notificationPanel, label, reportId, reportName);
        }
    });

    if (pastReports.contains(reportId)) {
        notificationPanel.setBackground(new Color(200, 200, 200, 180));
        label.setText("  Past Report: " + reportName);
    } else {
        label.setText("  New Report: " + reportName);
    }

    buttonPanel.add(viewButton);
    buttonPanel.add(saveButton);
    notificationPanel.add(buttonPanel, BorderLayout.EAST);

    notificationsContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    notificationsContainer.add(notificationPanel);
    notificationsContainer.revalidate();
    notificationsContainer.repaint();

    reportPanels.put(reportId, notificationPanel);
}

    private void markAsPastReport(JPanel panel, JLabel label, int reportId, String reportName) {
    panel.setBackground(new Color(200, 200, 200, 180));
    label.setText("  Past Report: " + reportName);
    savePastReportIdToFile(reportId);
}
    
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 102, 0));
        button.setForeground(new Color(255, 255, 204));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void viewReport(int reportId) {
        byte[] reportPDF = getReportPdfById(reportId);
        if (reportPDF == null) {
            JOptionPane.showMessageDialog(this, "No report available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            File tempFile = File.createTempFile("report", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(reportPDF);
            }
            PDDocument document = PDDocument.load(tempFile);
            PDFRenderer renderer = new PDFRenderer(document);
            ArrayList<ImageIcon> images = new ArrayList<>();
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 100);
                images.add(new ImageIcon(image));
            }
            document.close();
            showPdfViewer(images);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load report.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private byte[] getReportPdfById(int reportId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
            String sql = "SELECT report_pdf FROM reports WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, reportId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getBytes("report_pdf");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showPdfViewer(ArrayList<ImageIcon> images) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Report Preview", true);
        dialog.setSize(1000, 750);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());
        JLabel imageLabel = new JLabel(images.get(0));
        JScrollPane scrollPane = new JScrollPane(imageLabel);
        dialog.add(scrollPane, BorderLayout.CENTER);
        JButton nextButton = new JButton("Next");
        JButton prevButton = new JButton("Previous");
        styleButton(nextButton);
        styleButton(prevButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        final int[] currentIndex = {0};
        prevButton.addActionListener(e -> {
            if (currentIndex[0] > 0) {
                currentIndex[0]--;
                imageLabel.setIcon(images.get(currentIndex[0]));
            }
        });
        nextButton.addActionListener(e -> {
            if (currentIndex[0] < images.size() - 1) {
                currentIndex[0]++;
                imageLabel.setIcon(images.get(currentIndex[0]));
            }
        });
        dialog.setVisible(true);
    }

    private void saveReport(int reportId) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save PDF Report");
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts", "root", "sha@123")) {
        String query = "SELECT report_pdf, report_name FROM reports WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, reportId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            byte[] pdfData = rs.getBytes("report_pdf");
            String fileName = rs.getString("report_name");

            if (fileName == null || fileName.trim().isEmpty()) {
                fileName = "Report_" + reportId + ".pdf";
            } else if (!fileName.toLowerCase().endsWith(".pdf")) {
                fileName += ".pdf";
            }
            fileChooser.setSelectedFile(new File(fileName));
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                    fos.write(pdfData);
                    JOptionPane.showMessageDialog(null, "Report saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Report not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving report!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    class RoundedPanel extends JPanel {
        private int cornerRadius;
        public RoundedPanel(int radius) {
            this.cornerRadius = radius;
            setOpaque(false);
        }
        @Override
        

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
