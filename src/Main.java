import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static  Service service = new Service();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTasks(scanner);
                            break;
                        case 3:
                            printTaskForDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введите название задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите тип задачи:\n" + "0. Рабочая задача\t" + "1. Личная задача");
        System.out.print("Вы ввели: ");
        boolean isPersonal = service.createIsPersonal(scanner.nextInt());
        System.out.println("Введите тип повторяемости задачи:\n" + "1.Однократная\t 2.Ежедневная\t 3.Еженедельная\t 4.Ежемесячная\t 5.Ежегодная");
        System.out.print("Вы ввели: ");
        int repeatable = scanner.nextInt();
        System.out.println("Введите дату задачи в формате дд.мм.гггг: ");
        String date = scanner.next();
        System.out.println("Введите время задачи в формате чч:мм: ");
        String time = scanner.next();
        LocalDateTime localDateTime = service.createDateTime(date,time);
        Task task = service.createTask(taskName,description,isPersonal,localDateTime,repeatable);
        service.addTask(task);
        System.out.println("Создана новая задача: ");
        System.out.println(task.toString());
    }
    private static void printMenu() {
        System.out.println("" +
                "1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "0. Выход"
        );
    }
    private static void printTaskForDate(Scanner scanner) {
        if (service.getTasks().isEmpty()) {
            System.out.println("Задачи в ежедневнике отсутствуют");
            return;
        }
        System.out.println("Введите дату в формате дд.мм.гггг: ");
        String date = scanner.next();
        LocalDateTime dateTime = service.createDateTime(date, "00:00");
        System.out.println("Задача на: " + dateTime.toLocalDate());
        service.printTaskDay(dateTime);
    }

    private static void deleteTasks(Scanner scanner) {
        System.out.println("Список задач");
        service.printTasks();
        if (service.getTasks().isEmpty()) {
            return;
        }
        System.out.println("Введите id задачи, которую необходимо удалить: ");
        int idTask = scanner.nextInt();
        service.deleteTask(idTask);
        System.out.println("Задача удалена");
    }



}