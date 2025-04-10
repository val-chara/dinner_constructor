import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DinnerConstructor constructor = new DinnerConstructor();

        while (true) {
            System.out.println();
            System.out.println("Меню:");
            System.out.println("1 — Добавить блюдо");
            System.out.println("2 — Сгенерировать комбинации");
            System.out.println("3 — Выйти");

            System.out.print("Выберите команду: ");
            String command = scanner.nextLine();

            if (command.equals("1")) {
                System.out.print("Введите тип блюда: ");
                String type = scanner.nextLine();

                System.out.print("Введите название блюда: ");
                String name = scanner.nextLine();

                constructor.addDish(type, name);
                System.out.println("Блюдо добавлено.");

            } else if (command.equals("2")) {
                System.out.print("Сколько комбинаций вы хотите сгенерировать?");
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
                    continue;
                }

                count = Integer.parseInt(countInput);

                List<String> types = new ArrayList<>();
                System.out.println("Введите типы блюд по одному на строку. Чтобы завершить ввод, нажмите Enter на пустой строке.");

                while (true) {
                    String type = scanner.nextLine();
                    if (type.isEmpty()) {
                        break;
                    }

                    if (!constructor.checkType(type)) {
                        System.out.println("Такой тип не найден. Введите другой:");
                        continue;
                    }

                    types.add(type);
                }

                List<List<String>> combos = constructor.generateCombos(count, types);

                System.out.println("Сгенерированные комбинации:");
                for (List<String> combo : combos) {
                    System.out.println(String.join(",", combo));
                }

            } else if (command.equals("3")) {
                System.out.println("Выход из программы.");
                break;

            } else {
                System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}

