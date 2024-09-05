import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
// Вище імпортовано я використав лише для того, щоб коли форматую число на число з плаваючою комою,
// то щоб замість комми яка між цілою та дробовою частиною була крапка

class NumberOperations {
    public static void main(String[] args) {
        ArrayList<Number> nums = new ArrayList<>(Arrays.asList(10, 20.5, 30, 40.7, 50, 60.3, 70, 80.1, 90, 100.9));
        System.out.println("Initial list: " + nums);
//########################################### ВАРІАНТ 6 ###############################################################
        // Так як маємо поки що початковий список то відразу можна виконати моє завдання,
        // де я перебираю усі числа і добавляю лише цілочисельні до новоствореного списку nums2
        ArrayList<Number> nums2 = new ArrayList<>();
        for(Number num : nums){
            if(num instanceof Integer integer) {
                nums2.add(integer);
            }
        }
//#####################################################################################################################
        // Додаємо до списоку числа різних типів даних
        nums.add((byte) 120);
        nums.add((byte) 38);
        nums.add((short) 2024);
        nums.add((short) 27899);
        nums.add((short) 24092);
        nums.add(80000);
        nums.add(211122);
        nums.add(3000000200L);
        nums.add(3.141592653589793);
        nums.add(2.99f);
        nums.add(4.56489f);

        // виводжу список із доповоненими числами різних типів
        System.out.println("List after adding numbers: " + nums);

        // тепер щоб перетворити числа на цілі і вивести їх, і так само окремо перетворити на числа з плаваючою комою і також вивести,
        // спочатку створимо два порожніх списки у які ми будемо додавати вже перетворені числа відповідного типу даних
        ArrayList<Long> numsAsInt = new ArrayList<>(); // список для цілочисельних
        ArrayList<String> numsAsDouble = new ArrayList<>(); // список для чисел з плаваючою комою

        // Добавлаємо усі числа конвертовані як цілочисельні до списку numsAsInt
        for(Number num : nums) {
            numsAsInt.add(num.longValue());
        }
        // Добавлаємо усі числа конвертовані як дробові до списку numsAsDouble
        for (Number num : nums) {
            numsAsDouble.add(String.format(Locale.US,"%.2f", num.doubleValue()));
        }

        // виводимо відповідні списки
        System.out.println("Numbers as integers: " + numsAsInt);
        System.out.println("Numbers as float: " + numsAsDouble);

        // Щоб відсортувати по окремих списках цілі та дробові числа створимо окремо порожній список для цілих і для дробових чисел
        ArrayList<Byte> byteNums = new ArrayList<>();
        ArrayList<Short> shortNums = new ArrayList<>();
        ArrayList<Integer> intNums = new ArrayList<>();
        ArrayList<Long> longNums = new ArrayList<>();
        ArrayList<Float> floatNums = new ArrayList<>();
        ArrayList<Double> doubleNums = new ArrayList<>();

        // Тепер за допомогою циклу for переберемо усі елементи зі списку nums і відповідно
        // до типу даних додамо числа до відповідних списків, які міститимуть числа з відповідним типом даних
        for(Number num : nums) {
            if (num instanceof Byte aByte) {
                byteNums.add(aByte);
            }
            if (num instanceof Short) {
                shortNums.add((short)num);
            }
            if(num instanceof Integer integer) {
                intNums.add(integer);
            }
            if(num instanceof Long aLong) {
                longNums.add(aLong);
            }
            if (num instanceof Float aFloat) {
                floatNums.add(aFloat);
            }
            if (num instanceof Double aDouble) {
                doubleNums.add(aDouble);
            }
        }
        // виводимо відповідні списки
        System.out.println("ByteNum list: " + byteNums);
        System.out.println("ShortNum list: " + shortNums);
        System.out.println("IntNum list: " + intNums);
        System.out.println("LongNum list: " + longNums);
        System.out.println("FloatNum list: " + floatNums);
        System.out.println("DoubleNum list: " + doubleNums);
        // виводимо також список завдання, який належить моєму варіанту
        System.out.println("Variant 6: " + nums2);
    }
}