import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task{
    public SingleTask(String header, String description, boolean isPersonal, LocalDateTime taskDate) {
        super(header, description, isPersonal, taskDate);
    }

    @Override
    public boolean checkTaskDay(LocalDateTime date) {
        return false;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return task + ", Повторяемость задачи: 'Однократная'";
    }
}
