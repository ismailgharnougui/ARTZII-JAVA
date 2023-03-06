package models;

import db.MyConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.*;
import org.jfree.data.time.*;


public class Chart {
    public static void main(String[] args) throws SQLException {
       Connection conn = MyConnection.getInstance().getConnection();

        // Retrieve the order data from the database
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DATE_FORMAT(date_commande, '%Y-%m-01') AS month, SUM(cout_totale) AS total FROM commands GROUP BY month");

        // Create the chart data
        TimeSeries series = new TimeSeries("Coût total des commandes");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (rs.next()) {
            LocalDate date = LocalDate.parse(rs.getString("month"), formatter);
            double total = rs.getDouble("total");
            series.add(new org.jfree.data.time.Month(date.getMonthValue(), date.getYear()), total);
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Coût total des commandes par mois", "Mois", "Coût total", dataset, true, true, false);
        chart.getXYPlot().setDomainAxis(new DateAxis("Mois"));

        // Display the chart
        ChartFrame frame = new ChartFrame("Graphique des commandes", chart);
        frame.setSize(967, 600);
        frame.setLocation(475, 160);
        frame.setVisible(true);

        // Close the database connection
        stmt.close();
        conn.close();
    }
}
