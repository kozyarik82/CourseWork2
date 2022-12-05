import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DailyTask extends Task implements Repeatable {
    public DailyTask(String header, String description, boolean isPersonalTask, LocalDateTime taskDate) {
        super(header, description, isPersonalTask, taskDate);
    }

    @Override
    public boolean checkTaskDay(LocalDateTime date) {
        return getTaskDate().isBefore(date);
    }

    @Override
    public LocalDateTime getNextDateTime() {
        LocalTime timeRepeat = LocalTime.of(getTaskDate().getHour(), getTaskDate().getMinute());
        if (getTaskDate().isAfter(LocalDateTime.now())) {
            return getTaskDate();
        }
        if (timeRepeat.isAfter(LocalTime.now())) {
            return LocalDateTime.of(LocalDate.now(), timeRepeat);
        }
        return LocalDateTime.of(LocalDate.now().plusDays(1), timeRepeat);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи: 'Ежедневная'" + ", Дата следующего выполнения: " + getNextDateTime().toLocalDate() + ", Время: " + getNextDateTime().toLocalTime();
    }
}
