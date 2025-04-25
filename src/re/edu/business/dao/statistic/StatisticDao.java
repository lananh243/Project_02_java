package re.edu.business.dao.statistic;

import re.edu.business.dao.AppDao;
import re.edu.business.model.Statistic;

import java.time.LocalDate;
import java.util.List;

public interface StatisticDao extends AppDao<Statistic> {
    List<Statistic> getRevenueByDay(LocalDate created_at);
    List<Statistic> getRevenueByMonth(LocalDate start_month,  LocalDate end_month);
    List<Statistic> getRevenueByYear(int start_year, int end_year);
}
