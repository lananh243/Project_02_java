package re.edu.business.service.statistic;

import re.edu.business.model.Statistic;
import re.edu.business.model.invoice.Invoice;
import re.edu.business.service.AppService;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService extends AppService<Invoice> {
    List<Statistic> getRevenueByDay(LocalDate created_at);
    List<Statistic> getRevenueByMonth(LocalDate start_month,  LocalDate end_month);
    List<Statistic> getRevenueByYear(int start_year, int end_year);
}
