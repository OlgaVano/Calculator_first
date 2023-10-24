import java.util.Arrays;
import java.util.Scanner;

public class calc {

    public static void main(String[] args) throws  MinusException, NaborException {
        //Ввод строки и преобразование элементов через пробел в массив
        Scanner input1 = new Scanner(System.in);
        String input = input1.nextLine();
        String[] inputForElements = input.split(" ");

        //выброс исключений при неверной длинне строки
        if (inputForElements.length>3) {
            throw new NaborException("Неверное выражение: формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *");
        }
        if (inputForElements.length !=3) {
            throw new NaborException("Неверное выражение: строка слишком короткая");
        }

        //проверка на римски цифры и выполнение арифметики, если ошибки нет, иначе далее проверка на цифры
        try {
            ReturnNumber test1 = ReturnNumber.valueOf(inputForElements[0]);
            ReturnNumber test2 = ReturnNumber.valueOf(inputForElements[2]);
            String operation = inputForElements[1];

            // присвоение значений инама - араб. цифр
            int rezultfirst = test1.getTranslate();
            int rezultsecond = test2.getTranslate();
            int rezul = 0;

            // проверка на 1 от 10
            if (rezultfirst>10 || rezultfirst <=0 || rezultsecond >10 || rezultsecond <=0) {
                throw new NaborException("Неверное выражение: можно вводить цифры от 1 до 10 как арабские, так и римские");
            }

            // арифметика
            switch (operation) {
                case "+":
                    rezul = rezultfirst + rezultsecond;
                    break;
                case "-":
                    rezul = rezultfirst - rezultsecond;
                    break;
                case "*":
                    rezul = rezultfirst * rezultsecond;
                    break;
                case "/":
                    rezul = rezultfirst / rezultsecond;
                    break;
                default: throw new NaborException("Неверное выражение: можно вводить цифры от 1 до 10 как арабские, так и римские и знак выражения: +,-,/,*");
            }

            // вывод римских цифр
            if (rezul<=0) {
                throw new MinusException("Неверное выражение: результат не может быть отрицательным");
            }

            int seloe = rezul / 10;
            int ostatok = rezul - seloe * 10;

            if (rezul <= 10) {
                System.out.println(ReturnNumber.values()[rezul - 1]);
            } else {
                if (rezul < 40) {

                    for (int i = 1; i <= seloe; i++) {
                        System.out.print(ReturnNumber.values()[9]);
                    }
                    if (ostatok != 0) {
                        System.out.print(ReturnNumber.values()[ostatok - 1]);
                    }
                } else {
                    if (rezul < 50) {

                        System.out.print(ReturnNumber.values()[9]);
                        System.out.print(ReturnNumber.values()[10]);
                        if (ostatok != 0) {
                            System.out.print(ReturnNumber.values()[ostatok - 1]);
                        }
                    } else {
                        if (rezul < 60) {
                            System.out.print(ReturnNumber.values()[10]);
                            int ostatok2 = rezul - 50;
                            if (ostatok2 != 0) {
                                System.out.print(ReturnNumber.values()[ostatok2 - 1]);
                            }
                        } else {
                            if (rezul < 90) {
                                System.out.print(ReturnNumber.values()[10]);
                                int seloe2 = (rezul - 50) / 10;
                                int ostatok2 = rezul - (50 + seloe2 * 10);
                                for (int i = 1; i <= seloe2; i++) {
                                    System.out.print(ReturnNumber.values()[9]);
                                }
                                if (ostatok2 != 0) {
                                    System.out.print(ReturnNumber.values()[ostatok2 - 1]);
                                }
                            } else {
                                if (rezul == 100) {
                                    System.out.print(ReturnNumber.values()[11]);

                                } else {
                                    if (rezul == 90) {
                                        System.out.print(ReturnNumber.values()[9]);
                                        System.out.print(ReturnNumber.values()[11]);
                                    }

                                }
                            }
                        }
                    }
                }
            }

            //если оба числа не заданы римскими цифрами идем дальше
        } catch (IllegalArgumentException e) {

            // проверяем все ли цифры арабские
            try {
                int first = Integer.parseInt(inputForElements[0]);  // из строки в число
                String operation = inputForElements[1];
                int second = Integer.parseInt(inputForElements[2]);

                // проверка на 1 от 10
                if (first>10 || first <=0 || second >10 || second <=0) {
                    throw new NaborException("Неверное выражение: можно вводить цифры от 1 до 10 как арабские, так и римские");
                }

                // арифметика
                int rezult = 0;
                switch (operation) {
                    case "+":
                        rezult=first+second; break;
                    case "-":
                        rezult=first-second;break;
                    case "*":
                        rezult=first*second;break;
                    case "/":
                        rezult=first/second;break;
                    default: throw new NaborException("Неверное выражение: можно вводить цифры от 1 до 10 как арабские, так и римские и знак выражения: +,-,/,*");
                }
                System.out.println(rezult);
            }
             catch (IllegalArgumentException e1) {
                 System.out.println("Неверная запись: используются одновременно разные системы счисления, есть дробные числа или др. знаки");
            }

        }
    }
}
