package re.edu.business.model;

import java.time.LocalDate;

public class Statistic {
    private LocalDate revenue;
    private double total_revenue;

    public Statistic() {
    }

    public Statistic(LocalDate revenue, double total_revenue) {
        this.revenue = revenue;
        this.total_revenue = total_revenue;
    }

    public LocalDate getRevenue() {
        return revenue;
    }

    public void setRevenue(LocalDate revenue) {
        this.revenue = revenue;
    }

    public double getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(double total_revenue) {
        this.total_revenue = total_revenue;
    }
}
