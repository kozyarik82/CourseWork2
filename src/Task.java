import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private final String header;
    private final String description;
    private final boolean isPersonalTask;
    private final LocalDateTime taskDate;
    private static int counter=0;
    private final int id;


    public Task(String header, String description, boolean isPersonalTask, LocalDateTime taskDate) {
        this.header = header;
        this.description = description;
        this.isPersonalTask = isPersonalTask;
        this.taskDate = taskDate;
        id = counter++;
    }

    public abstract boolean checkTaskDay(LocalDateTime date);

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPersonalTask() {
        return isPersonalTask;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        String isPersonal;
        if (isPersonalTask) {
            isPersonal = "Личная";
        }else{
            isPersonal = "Рабочая";
        }
        return "Задача:" +
                "идентификационный номер: " + id +
                ", Название задачи: " + header + '\'' +
                ", Описание: " + description + '\'' +
                ", Тип задачи: " + isPersonal + '\'' +
                ", Дата выполнения задачи: " + taskDate.toLocalDate() +
                ", Время: " + taskDate.toLocalTime();
    }
}
