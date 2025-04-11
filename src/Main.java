import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DinnerConstructor constructor = new DinnerConstructor();

        while (true) {
            menu();
            String command = scanner.nextLine();

            if (command.equals("1")) {
                addDish(scanner, constructor);
            } else if (command.equals("2")) {
                generateCombos(scanner, constructor);
            } else if (command.equals("3")) {
                System.out.println("Выход из программы.");
                break;
            } else {
                System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }

        scanner.close();
    }


    public static void menu(){
        System.out.println();
        System.out.println("Меню:");
        System.out.println("1 — Добавить блюдо");
        System.out.println("2 — Сгенерировать комбинации");
        System.out.println("3 — Выйти");
        System.out.print("Выберите команду: ");
    }


    public static void addDish(Scanner scanner, DinnerConstructor constructor){
        System.out.print("Введите тип блюда: ");
        String type = scanner.nextLine();

        System.out.print("Введите название блюда: ");
        String name = scanner.nextLine();

        constructor.addDish(type, name);
        System.out.println("Блюдо добавлено.");

    }

    private static void generateCombos(Scanner scanner, DinnerConstructor constructor) {
        System.out.print("Сколько комбинаций вы хотите сгенерировать? ");
        String countInput = scanner.nextLine();

        int count = 0;
        boolean isNumber = true;
        for (int i = 0; i < countInput.length(); i++) {
            if (!Character.isDigit(countInput.charAt(i))) {
                isNumber = false;
                break;
            }
        }

        if (!isNumber) {
            System.out.println("Ошибка: введите число.");
            return;
        }

        count = Integer.parseInt(countInput);

        List<String> types = new ArrayList<>();
        System.out.println("Введите типы блюд по одному на строке. Чтобы завершить ввод, нажмите Enter на пустой строке.");

        while (true) {
            String type = scanner.nextLine();
            if (type.isEmpty()) {
                break;
            }

            if (!constructor.checkType(type)) {
                System.out.println("Такой тип не найден. Повторите ввод:");
                continue;
            }

            types.add(type);
        }

        if (types.isEmpty()) {
            System.out.println("Вы не ввели ни одного корректного типа блюда.");
            return;
        }

        List<List<String>> combos = constructor.generateCombos(count, types);
        System.out.println("Сгенерированные комбинации:");
        for (List<String> combo : combos) {
            System.out.println(String.join(", ", combo));
        }
    }
}


