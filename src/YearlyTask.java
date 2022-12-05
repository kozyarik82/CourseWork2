import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class YearlyTask extends Task implements Repeatable {
    public YearlyTask(String header, String description, boolean isPersonalTask, LocalDateTime taskDate) {
        super(header, description, isPersonalTask, taskDate);
    }

    @Override
    public boolean checkTaskDay(LocalDateTime date) {
        int repeatDayOfYear = getTaskDate().getDayOfYear();
        if (getTaskDate().isAfter(date)) {
            return false;
        }
        return date.getDayOfYear() == repeatDayOfYear;
    }

    @Override
    public LocalDateTime getNextDateTime() {
        LocalTime timeRepeat = LocalTime.of(getTaskDate().getHour(), getTaskDate().getMinute());
        int repeatDayOfYear = getTaskDate().getDayOfYear();
        if (getTaskDate().isAfter(LocalDateTime.now())) {
            return getTaskDate();
        }
        if (repeatDayOfYear == LocalDate.now().getDayOfYear() && timeRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeRepeat);
        }
        if (repeatDayOfYear > LocalDateTime.now().getDayOfYear()) {
            return LocalDateTime.of(LocalDate.now().plusDays(repeatDayOfYear - LocalDateTime.now().getDayOfMonth()), timeRepeat);
        }else {
            return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.firstDayOfNextYear()).plusDays(repeatDayOfYear - 1), timeRepeat);
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи: 'Ежегодная'" + ", Дата следующего выполнения: " + getNextDateTime().toLocalDate() + ", Время: " + getNextDateTime().toLocalTime();
    }
}
