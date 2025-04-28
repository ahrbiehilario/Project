
package Content;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.UnitValue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.nio.file.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer; 

public class Reports extends javax.swing.JPanel {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/accounts";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "sha@123";
    private String reportFilePath = "generated_report.pdf";
    private BufferedImage reportImage;

    public Reports() {
        initComponents();
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image background = new ImageIcon(getClass().getResource("/icons/BGPANE.jpg")).getImage();
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    private void generateMonthlyReport(int month, int year) {
        try {
            String monthName = java.time.Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            reportFilePath = "Monthly_Report_" + monthName + "_" + year + ".pdf";
            File oldPdf = new File(reportFilePath);
            if (oldPdf.exists()) oldPdf.delete();

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(reportFilePath));
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Monthly Report - " + monthName + " " + year)
                    .setBold().setFontSize(16).setFontColor(ColorConstants.RED)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER)
                    .setMarginBottom(10));

            float[] columnWidths = {80f, 80f, 60f, 100f, 100f, 80f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"Name", "Date", "Time", "Inclusions", "Status", "Fee"};
            for (String header : headers) {
                table.addCell(new Cell().add(new Paragraph(header).setBold().setFontSize(11)));
            }

            double totalFee = 0;
            String query = "SELECT resFullname, resDate, resTime, resInclusions, resStatus, resFee FROM reservations WHERE MONTH(resDate) = ? AND YEAR(resDate) = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, month);
                pstmt.setInt(2, year);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    table.addCell(rs.getString("resFullname"));
                    table.addCell(rs.getString("resDate"));
                    table.addCell(rs.getString("resTime"));
                    table.addCell(rs.getString("resInclusions"));

                    String status = rs.getString("resStatus");
                    Color statusColor;
                    switch (status) {
                        case "Accepted": statusColor = new DeviceRgb(0, 255, 0); break;
                        case "Canceled": statusColor = new DeviceRgb(255, 102, 102); break;
                        case "Fully Paid": statusColor = new DeviceRgb(128, 128, 255); break;
                        case "Suspended": statusColor = new DeviceRgb(255, 102, 102); break;
                        default: statusColor = new DeviceRgb(0, 0, 0);
                    }
                    table.addCell(new Cell().add(new Paragraph(status).setFontColor(statusColor)));

                    double fee = rs.getDouble("resFee");
                    table.addCell(new Cell().add(new Paragraph("₱" + String.format("%,.2f", fee))
                            .setBold().setFontColor(new DeviceRgb(0, 102, 0))));
                    totalFee += fee;
                }
            }

            document.add(table);
            document.add(new Paragraph("\nTotal: ₱" + String.format("%,.2f", totalFee))
                    .setBold().setFontSize(11).setFontColor(new DeviceRgb(0, 102, 0))
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT));
            document.add(new Paragraph("Date/Signature Over Printed Name")
                    .setBold().setFontSize(11)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.LEFT));
            document.close();
            displayReportAsImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateMonthlyReportWithStatus(int month, int year, List<String> statuses) {
        try {
            String monthName = java.time.Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            reportFilePath = "Monthly_Report_" + monthName + "_" + year + ".pdf";
            File oldPdf = new File(reportFilePath);
            if (oldPdf.exists()) oldPdf.delete();

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(reportFilePath));
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Monthly Report - " + monthName + " " + year)
                    .setBold().setFontSize(16).setFontColor(ColorConstants.RED)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER)
                    .setMarginBottom(10));

            float[] columnWidths = {80f, 80f, 60f, 100f, 100f, 80f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"Name", "Date", "Time", "Inclusions", "Status", "Fee"};
            for (String header : headers) {
                table.addCell(new Cell().add(new Paragraph(header).setBold().setFontSize(11)));
            }

            double totalFee = 0;
            StringBuilder query = new StringBuilder("SELECT resFullname, resDate, resTime, resInclusions, resStatus, resFee FROM reservations WHERE MONTH(resDate) = ? AND YEAR(resDate) = ? AND resStatus IN (");
            for (int i = 0; i < statuses.size(); i++) {
                query.append("?");
                if (i < statuses.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(")");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                pstmt.setInt(1, month);
                pstmt.setInt(2, year);
                for (int i = 0; i < statuses.size(); i++) {
                    pstmt.setString(i + 3, statuses.get(i));
                }
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    table.addCell(rs.getString("resFullname"));
                    table.addCell(rs.getString("resDate"));
                    table.addCell(rs.getString("resTime"));
                    table.addCell(rs.getString("resInclusions"));

                    String status = rs.getString("resStatus");
                    Color statusColor;
                    switch (status) {
                        case "Accepted": statusColor = new DeviceRgb(0, 255, 0); break;
                        case "Canceled": statusColor = new DeviceRgb(255, 102, 102); break;
                        case "Fully Paid": statusColor = new DeviceRgb(128, 128, 255); break;
                        case "Suspended": statusColor = new DeviceRgb(255, 102, 102); break;
                        default: statusColor = new DeviceRgb(0, 0, 0);
                    }
                    table.addCell(new Cell().add(new Paragraph(status).setFontColor(statusColor)));

                    double fee = rs.getDouble("resFee");
                    table.addCell(new Cell().add(new Paragraph("₱" + String.format("%,.2f", fee))
                            .setBold().setFontColor(new DeviceRgb(0, 102, 0))));
                    totalFee += fee;
                }
            }

            document.add(table);
            document.add(new Paragraph("\nTotal: ₱" + String.format("%,.2f", totalFee))
                    .setBold().setFontSize(11).setFontColor(new DeviceRgb(0, 102, 0))
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT));
            document.add(new Paragraph("Date/Signature Over Printed Name")
                    .setBold().setFontSize(11)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.LEFT));
            document.close();
            displayReportAsImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateYearlyReport(int year, List<Integer> months) {
        try {
            reportFilePath = "Yearly_Report_" + year + ".pdf";
            File oldPdf = new File(reportFilePath);
            if (oldPdf.exists()) oldPdf.delete();

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(reportFilePath));
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Yearly Report - " + year)
                    .setBold().setFontSize(16).setFontColor(ColorConstants.RED)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER)
                    .setMarginBottom(10));

            float[] columnWidths = {60f, 100f, 80f, 60f, 100f, 80f, 80f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"Month", "Name", "Date", "Time", "Inclusions", "Status", "Fee"};
            for (String header : headers) {
                table.addCell(new Cell().add(new Paragraph(header).setBold().setFontSize(11)));
            }

            double totalFee = 0;
            for (int month : months) {
                String query = "SELECT MONTHNAME(resDate) AS resMonth, resFullname, resDate, resTime, resInclusions, resStatus, resFee FROM reservations WHERE YEAR(resDate) = ? AND MONTH(resDate) = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, year);
                    pstmt.setInt(2, month);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        table.addCell(rs.getString("resMonth"));
                        table.addCell(rs.getString("resFullname"));
                        table.addCell(rs.getString("resDate"));
                        table.addCell(rs.getString("resTime"));
                        table.addCell(rs.getString("resInclusions"));

                        String status = rs.getString("resStatus");
                        Color statusColor;
                        switch (status) {
                            case "Accepted": statusColor = new DeviceRgb(0, 255, 0); break;
                            case "Canceled": statusColor = new DeviceRgb(255, 102, 102); break;
                            case "Fully Paid": statusColor = new DeviceRgb(128, 128, 255); break;
                            case "Suspended": statusColor = new DeviceRgb(255, 102, 102); break;
                            default: statusColor = new DeviceRgb(0, 0, 0);
                        }
                        table.addCell(new Cell().add(new Paragraph(status).setFontColor(statusColor)));

                        double fee = rs.getDouble("resFee");
                        table.addCell(new Cell().add(new Paragraph("₱" + String.format("%,.2f", fee))
                                .setBold().setFontColor(new DeviceRgb(0, 102, 0))));
                        totalFee += fee;
                    }
                }
            }

            document.add(table);
            document.add(new Paragraph("\nTotal: ₱" + String.format("%,.2f", totalFee))
                    .setBold().setFontSize(11).setFontColor(new DeviceRgb(0, 102, 0))
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT));
            document.add(new Paragraph("Date/Signature Over Printed Name")
                    .setBold().setFontSize(11)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.LEFT));
            document.close();
            displayReportAsImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateYearlyReportWithStatus(int year, List<Integer> months, List<String> statuses) {
        try {
            reportFilePath = "Yearly_Report_" + year + ".pdf";
            File oldPdf = new File(reportFilePath);
            if (oldPdf.exists()) oldPdf.delete();

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(reportFilePath));
            Document document = new Document(pdfDoc);
            document.add(new Paragraph("Yearly Report - " + year)
                    .setBold().setFontSize(16).setFontColor(ColorConstants.RED)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER)
                    .setMarginBottom(10));

            float[] columnWidths = {60f, 100f, 80f, 60f, 100f, 80f, 80f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));
            String[] headers = {"Month", "Name", "Date", "Time", "Inclusions", "Status", "Fee"};
            for (String header : headers) {
                table.addCell(new Cell().add(new Paragraph(header).setBold().setFontSize(11)));
            }

            double totalFee = 0;
            for (int month : months) {
                StringBuilder query = new StringBuilder("SELECT MONTHNAME(resDate) AS resMonth, resFullname, resDate, resTime, resInclusions, resStatus, resFee FROM reservations WHERE YEAR(resDate) = ? AND MONTH(resDate) = ? AND resStatus IN (");
                for (int i = 0; i < statuses.size(); i++) {
                    query.append("?");
                    if (i < statuses.size() - 1) {
                        query.append(", ");
                    }
                }
                query.append(")");

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
                    pstmt.setInt(1, year);
                    pstmt.setInt(2, month);
                    for (int i = 0; i < statuses.size(); i++) {
                        pstmt.setString(i + 3, statuses.get(i));
                    }
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        table.addCell(rs.getString("resMonth"));
                        table.addCell(rs.getString("resFullname"));
                        table.addCell(rs.getString("resDate"));
                        table.addCell(rs.getString("resTime"));
                        table.addCell(rs.getString("resInclusions"));

                        String status = rs.getString("resStatus");
                        Color statusColor;
                        switch (status) {
                            case "Accepted": statusColor = new DeviceRgb(0, 255, 0); break;
                            case "Canceled": statusColor = new DeviceRgb(255, 102, 102); break;
                            case "Fully Paid": statusColor = new DeviceRgb(128, 128, 255); break;
                            case "Suspended": statusColor = new DeviceRgb(255, 102, 102); break;
                            default: statusColor = new DeviceRgb(0, 0, 0);
                        }
                        table.addCell(new Cell().add(new Paragraph(status).setFontColor(statusColor)));

                        double fee = rs.getDouble("resFee");
                        table.addCell(new Cell().add(new Paragraph("₱" + String.format("%,.2f", fee))
                                .setBold().setFontColor(new DeviceRgb(0, 102, 0))));
                        totalFee += fee;
                    }
                }
            }

            document.add(table);
            document.add(new Paragraph("\nTotal: ₱" + String.format("%,.2f", totalFee))
                    .setBold().setFontSize(11).setFontColor(new DeviceRgb(0, 102, 0))
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT));
            document.add(new Paragraph("Date/Signature Over Printed Name")
                    .setBold().setFontSize(11)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.LEFT));
            document.close();
            displayReportAsImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayReportAsImage() {
        try {
            File reportFile = new File(reportFilePath);
            if (!reportFile.exists()) {
                JOptionPane.showMessageDialog(this, "Report file not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PDDocument document = PDDocument.load(reportFile);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
            reportImage = null;
            for (int pageIndex = 0; pageIndex < document.getNumberOfPages(); pageIndex++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 100);
                if (image != null) {
                    reportImage = image;
                    JLabel imageLabel = new JLabel(new ImageIcon(image));
                    imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    imagePanel.add(imageLabel);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to generate report preview for page " + (pageIndex + 1), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            document.close();
            reportsPanel.removeAll();
            reportsPanel.setLayout(new java.awt.BorderLayout());
            JScrollPane scrollPane = new JScrollPane(imagePanel);
            reportsPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
            reportsPanel.revalidate();
            reportsPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error displaying report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTGENERATE = new javax.swing.JButton();
        BTDOWNLOAD = new javax.swing.JButton();
        reportsPanel = new javax.swing.JPanel();
        BTSEND = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 0));

        BTGENERATE.setBackground(new java.awt.Color(255, 255, 204));
        BTGENERATE.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BTGENERATE.setForeground(new java.awt.Color(0, 102, 0));
        BTGENERATE.setText("GENERATE REPORT");
        BTGENERATE.setBorderPainted(false);
        BTGENERATE.setFocusPainted(false);
        BTGENERATE.setFocusable(false);
        BTGENERATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTGENERATEActionPerformed(evt);
            }
        });

        BTDOWNLOAD.setBackground(new java.awt.Color(255, 255, 204));
        BTDOWNLOAD.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BTDOWNLOAD.setForeground(new java.awt.Color(0, 102, 0));
        BTDOWNLOAD.setText("DOWNLOAD");
        BTDOWNLOAD.setBorderPainted(false);
        BTDOWNLOAD.setFocusPainted(false);
        BTDOWNLOAD.setFocusable(false);
        BTDOWNLOAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTDOWNLOADActionPerformed(evt);
            }
        });

        reportsPanel.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        BTSEND.setBackground(new java.awt.Color(255, 255, 204));
        BTSEND.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        BTSEND.setForeground(new java.awt.Color(0, 102, 0));
        BTSEND.setText("SEND TO ADMIN");
        BTSEND.setBorderPainted(false);
        BTSEND.setFocusPainted(false);
        BTSEND.setFocusable(false);
        BTSEND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTSENDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTGENERATE, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTDOWNLOAD, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(BTSEND, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTDOWNLOAD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTSEND))
                    .addComponent(BTGENERATE, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BTGENERATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTGENERATEActionPerformed
        // TODO add your handling code here:
        String[] options = {"Generate Monthly Report", "Generate Yearly Report"};
        int choice = JOptionPane.showOptionDialog(this, "Choose report type", "Report Type",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Select Month and Year");
            dialog.setModal(true);
            dialog.setSize(400, 420);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel monthPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };

            JTextField yearField = new JTextField(String.valueOf(LocalDate.now().getYear()));
            yearField.setFont(new Font("Arial", Font.PLAIN, 14));
            yearField.setHorizontalAlignment(JTextField.CENTER);
            yearField.setBorder(BorderFactory.createTitledBorder("Enter Year (e.g. 2024)"));

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            bottomPanel.add(yearField, BorderLayout.CENTER);

            for (int i = 0; i < months.length; i++) {
                final int monthNumber = i + 1;
                JButton monthButton = new JButton(months[i]);
                monthButton.setFocusPainted(false);
                monthButton.setBackground(new java.awt.Color(0, 102, 0));
                monthButton.setForeground(new java.awt.Color(255, 255, 204));
                monthButton.setFont(new Font("Arial", Font.BOLD, 13));
                monthButton.addActionListener(e -> {
                    String yearText = yearField.getText().trim();
                    if (yearText.isEmpty() || !yearText.matches("\\d{4}")) {
                        JOptionPane.showMessageDialog(dialog, "Please enter a valid year (e.g. 2024)", "Invalid Year", JOptionPane.WARNING_MESSAGE);
                    } else {
                        dialog.dispose();
                        List<String> selectedStatuses = getSelectedStatuses();
                        if (!selectedStatuses.isEmpty()) {
                            generateMonthlyReportWithStatus(monthNumber, Integer.parseInt(yearText), selectedStatuses);
                        } else {
                            JOptionPane.showMessageDialog(dialog, "Please select at least one status.", "No Filter Selected", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                monthPanel.add(monthButton);
            }

            dialog.add(monthPanel, BorderLayout.CENTER);
            dialog.add(bottomPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);

        } else if (choice == 1) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Select Year and Months");
            dialog.setModal(true);
            dialog.setSize(400, 420);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BorderLayout(10, 10));

            JPanel monthPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };

            JTextField yearField = new JTextField(String.valueOf(LocalDate.now().getYear()));
            yearField.setFont(new Font("Arial", Font.PLAIN, 14));
            yearField.setHorizontalAlignment(JTextField.CENTER);
            yearField.setBorder(BorderFactory.createTitledBorder("Enter Year (e.g. 2024)"));

            List<JCheckBox> monthCheckBoxes = new ArrayList<>();
            for (String month : months) {
                JCheckBox monthCheckBox = new JCheckBox(month);
                monthPanel.add(monthCheckBox);
                monthCheckBoxes.add(monthCheckBox);
            }

            JButton generateButton = new JButton("Generate Yearly Report");
            generateButton.addActionListener(e -> {
                String yearText = yearField.getText().trim();
                if (yearText.isEmpty() || !yearText.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid year (e.g. 2024)", "Invalid Year", JOptionPane.WARNING_MESSAGE);
                } else {
                    dialog.dispose();
                    int year = Integer.parseInt(yearText);
                    List<Integer> selectedMonths = new ArrayList<>();
                    for (int i = 0; i < monthCheckBoxes.size(); i++) {
                        if (monthCheckBoxes.get(i).isSelected()) {
                            selectedMonths.add(i + 1);
                        }
                    }
                    List<String> selectedStatuses = getSelectedStatuses();
                    if (!selectedMonths.isEmpty() && !selectedStatuses.isEmpty()) {
                        generateYearlyReportWithStatus(year, selectedMonths, selectedStatuses);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Please select at least one month and one status.", "No Selection", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(yearField, BorderLayout.NORTH);
            bottomPanel.add(generateButton, BorderLayout.SOUTH);
            dialog.add(monthPanel, BorderLayout.CENTER);
            dialog.add(bottomPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_BTGENERATEActionPerformed

    private void BTDOWNLOADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTDOWNLOADActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report");
        fileChooser.setSelectedFile(new File(reportFilePath));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                Files.copy(Paths.get(reportFilePath), fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "Report saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving report.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTDOWNLOADActionPerformed

    private void BTSENDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTSENDActionPerformed
        // TODO add your handling code here:
        if (reportFilePath == null || reportFilePath.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No report file found to send.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
        File reportFile = new File(reportFilePath);
        if (!reportFile.exists()) {
            JOptionPane.showMessageDialog(this, "No generated report found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (reportImage == null) {
            JOptionPane.showMessageDialog(this, "No report image preview available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pdfFileName = reportFile.getName();
        String imageFileName = pdfFileName.replace(".pdf", ".png");
        String url = "jdbc:mysql://localhost:3306/accounts";
        String user = "root";
        String password = "sha@123";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO reports (report_name, report_pdf, image_name, report_image) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 FileInputStream pdfInputStream = new FileInputStream(reportFile);
                 ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(reportImage, "png", baos);
                ByteArrayInputStream imageInputStream = new ByteArrayInputStream(baos.toByteArray());
                statement.setString(1, pdfFileName);
                statement.setBlob(2, pdfInputStream);
                statement.setString(3, imageFileName);
                statement.setBlob(4, imageInputStream);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Report sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to send report.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTSENDActionPerformed

    private void filterReportsByStatus(List<String> statuses) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/accounts", "root", "sha@123");

            StringBuilder query = new StringBuilder("SELECT * FROM reservations WHERE ");
            if (statuses.contains("All")) {
                query = new StringBuilder("SELECT * FROM reservations");
            } else {
                query.append("resStatus IN (");
                for (int i = 0; i < statuses.size(); i++) {
                    query.append("?");
                    if (i < statuses.size() - 1) {
                        query.append(", ");
                    }
                }
                query.append(")");
            }

            PreparedStatement ps = con.prepareStatement(query.toString());

            if (!statuses.contains("All")) {
                for (int i = 0; i < statuses.size(); i++) {
                    ps.setString(i + 1, statuses.get(i));
                }
            }

            ResultSet rs = ps.executeQuery();

            List<String> filteredData = new ArrayList<>();
            while (rs.next()) {
                String resID = rs.getString("resID");
                String fullname = rs.getString("resFullname");
                String status = rs.getString("resStatus");
                String date = rs.getString("resDate");
                filteredData.add("ID: " + resID + ", Name: " + fullname + ", Status: " + status + ", Date: " + date);
            }

            if (filteredData.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No results found for selected statuses.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder resultMsg = new StringBuilder();
                for (String line : filteredData) {
                    resultMsg.append(line).append("\n");
                }

                JTextArea textArea = new JTextArea(resultMsg.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(500, 300));

                JOptionPane.showMessageDialog(null, scrollPane, "Filtered Reservations", JOptionPane.INFORMATION_MESSAGE);
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error filtering reports: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<String> getSelectedStatuses() {
        JCheckBox cbAccepted = new JCheckBox("Accepted");
        JCheckBox cbCanceled = new JCheckBox("Canceled");
        JCheckBox cbFullyPaid = new JCheckBox("Fully Paid");
        JCheckBox cbSuspended = new JCheckBox("Suspended");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select status to filter the report:"));
        panel.add(cbAccepted);
        panel.add(cbCanceled);
        panel.add(cbFullyPaid);
        panel.add(cbSuspended);

        int result = JOptionPane.showConfirmDialog(this, panel, "Filter Report Status",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        List<String> selectedStatuses = new ArrayList<>();
        if (result == JOptionPane.OK_OPTION) {
            if (cbAccepted.isSelected()) selectedStatuses.add("Accepted");
            if (cbCanceled.isSelected()) selectedStatuses.add("Canceled");
            if (cbFullyPaid.isSelected()) selectedStatuses.add("Fully Paid");
            if (cbSuspended.isSelected()) selectedStatuses.add("Suspended");
        }
        return selectedStatuses;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTDOWNLOAD;
    private javax.swing.JButton BTGENERATE;
    private javax.swing.JButton BTSEND;
    private javax.swing.JPanel reportsPanel;
    // End of variables declaration//GEN-END:variables
}
