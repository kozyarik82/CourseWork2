import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Service{

    private final Map<Integer, Task> tasks = new HashMap<>();
    public Service() {
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {             // добавление задачи
        tasks.put(task.getId(), task);
    }
      // создание задачи
    public Task createTask(String header, String description, boolean isPersonal, LocalDateTime taskDate, int repeatable) {
        switch (repeatable) {
            case 1:
                return new SingleTask(header, description, isPersonal, taskDate);
            case 2:
                return new DailyTask(header, description, isPersonal, taskDate);
            case 3:
                return new WeeklyTask(header, description, isPersonal, taskDate);
            case 4:
                return new MonthlyTask(header, description, isPersonal, taskDate);
            case 5:
                return new YearlyTask(header, description, isPersonal, taskDate);
            default:
                throw new IllegalArgumentException("Неправильно выбран тип повторяемости задачи!");
        }
    }

    public LocalDateTime createDateTime(String date, String time) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date, dateFormat);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormat);
        return LocalDateTime.of(localDate, localTime);
    }

    public boolean createIsPersonal(int isPersonal) {
        if (isPersonal == 1) {
            return true;
        }
        if (isPersonal == 0) {
            return false;
        }
        throw new IllegalArgumentException("Неправильно введен тип задачи!");
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Задачи в ежедневнике отсутствуют");
            return;
        }
        for (Task tasks : tasks.values()) {
            System.out.println(tasks);
        }
    }

    public void printTaskDay(LocalDateTime date) {
        boolean presentTaskDay = false;
        for (Task task : tasks.values()) {
            if (task.checkTaskDay(date)) {
                presentTaskDay = true;
                System.out.println(task);
            }
        }
        if (!presentTaskDay) {
            System.out.println("На запрашиваемый день задачи отсутствуют");
        }
    }

    public void deleteTask(int id){
        Task task = tasks.get(id);
        tasks.remove(id);
    }
}
