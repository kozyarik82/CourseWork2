import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task implements Repeatable{
    public WeeklyTask(String header, String description, boolean isPersonalTask, LocalDateTime taskDate) {
        super(header, description, isPersonalTask, taskDate);
    }

    @Override
    public boolean checkTaskDay(LocalDateTime date) {
        DayOfWeek repeatDayOfWeek = DayOfWeek.of(getTaskDate().getDayOfWeek().getValue());
        if (getTaskDate().isAfter(date)) {
            return false;
        }
        return date.getDayOfWeek().equals(repeatDayOfWeek);
    }

    @Override
    public LocalDateTime getNextDateTime() {
        LocalTime timeRepeat = LocalTime.of(getTaskDate().getHour(), getTaskDate().getMinute());
        DayOfWeek repeatDayOfWeek = DayOfWeek.of(getTaskDate().getDayOfWeek().getValue());
        if (getTaskDate().isAfter(LocalDateTime.now())) {
            return getTaskDate();
        }
        if (repeatDayOfWeek.equals(LocalDate.now().getDayOfWeek()) && timeRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeRepeat);
        }
        return LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.next(repeatDayOfWeek)), timeRepeat);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи: 'Еженедельная'" + ", Дата следующего выполнения: " + getNextDateTime().toLocalDate() + ", Время: " + getNextDateTime().toLocalTime();
    }
}
