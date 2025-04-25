package re.edu.business.service.statistic;

import re.edu.business.dao.statistic.StatisticDao;
import re.edu.business.dao.statistic.StatisticDaoImp;
import re.edu.business.model.Statistic;

import java.time.LocalDate;
import java.util.List;

public class StatisticServiceImp implements StatisticService {
    public final StatisticDao statisticDao;

    public StatisticServiceImp() {
        statisticDao = new StatisticDaoImp();
    }

    @Override
    public List<Statistic> getRevenueByDay(LocalDate created_at) {
        return statisticDao.getRevenueByDay(created_at);
    }

    @Override
    public List<Statistic> getRevenueByMonth(LocalDate start_month, LocalDate end_month) {
        return statisticDao.getRevenueByMonth(start_month, end_month);
    }

    @Override
    public List<Statistic> getRevenueByYear(int start_year, int end_year) {
        return statisticDao.getRevenueByYear(start_year, end_year);
    }
}
