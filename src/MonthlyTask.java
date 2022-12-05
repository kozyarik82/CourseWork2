import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class MonthlyTask extends Task implements Repeatable{
    public MonthlyTask(String header, String description, boolean isPersonalTask, LocalDateTime taskDate) {
        super(header, description, isPersonalTask, taskDate);
    }

    @Override
    public boolean checkTaskDay(LocalDateTime date) {
        int repeatDayOfMonth = getTaskDate().getDayOfMonth();
        if (getTaskDate().isAfter(date)) {
            return false;
        }
        return date.getDayOfMonth() == repeatDayOfMonth;
    }

    @Override
    public LocalDateTime getNextDateTime() {
        LocalTime timeRepeat = LocalTime.of(getTaskDate().getHour(), getTaskDate().getMinute());
        int repeatDayOfMonths = getTaskDate().getDayOfMonth();
        if (getTaskDate().isAfter(LocalDateTime.now())) {
            return getTaskDate();
        }
        if (repeatDayOfMonths == LocalDate.now().getDayOfMonth() && timeRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeRepeat);
        }
        if (repeatDayOfMonths > LocalDateTime.now().getDayOfMonth()) {
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfMonths - LocalDateTime.now().getDayOfMonth()), timeRepeat);
        }else {
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()).plusDays(repeatDayOfMonths - 1), timeRepeat);
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи: 'Ежемесячная'" + ", Дата следующего выполнения: " + getNextDateTime().toLocalDate() + ", Время: " + getNextDateTime().toLocalTime();
    }
}
