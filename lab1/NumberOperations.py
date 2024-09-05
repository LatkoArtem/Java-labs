class NumberOperations:
    def __init__(self):
        # Створюємо початковий список, що даний в завданні 
        self.nums = [10, 20.5, 30, 40.7, 50, 60.3, 70, 80.1, 90, 100.9]

    def print_nums(self):
        # Виводимо початковий список
        print("Initial list:", self.nums)

    def add_number(self, number):
        # Додаємо число до списку
        self.nums.append(number)

    def print_new_list(self):
        # Виводимо оновлений список
        print("List after adding numbers:", self.nums)

    def print_as_integers(self):
        # Виводимо числа у форматі цілих чисел
        print("Numbers as Integers:", [int(num) for num in self.nums])

    def print_as_floats(self):
        # Виводимо числа у форматі дробних чисел з 2 знаками після коми
        formatted_numbers = [f"{num:.2f}" for num in self.nums]
        print("Numbers as Floats: [", ", ".join(formatted_numbers), "]")

    def separate_by_type(self):
        # Сортуємо числа за типом даних
        integers = [num for num in self.nums if isinstance(num, int)]
        floats = [num for num in self.nums if isinstance(num, float)]
        # Виводимо відповідні списки
        print("Integers:", integers)
        print("Floats:", floats)

    def my_variant_task(self):
        # Виводимо список лише чисел, які є цілочисельними
        print("Variant 6:", [num for num in self.nums if isinstance(num, int)])


if __name__ == "__main__":
    operations = NumberOperations()
    # Виводимо початковий список
    operations.print_nums()
    # І також відразу виконую завдання свого варіанту а саме вивести список з цілих чисел для початкового списку
    operations.my_variant_task()

    # Додаємо до списоку числа різних типів даних
    operations.add_number(120)
    operations.add_number(38)
    operations.add_number(2024)
    operations.add_number(27899)
    operations.add_number(80000)
    operations.add_number(3000000200)
    operations.add_number(3.141592653589793)
    operations.add_number(2.99)
    operations.add_number(4.56489)

    operations.print_new_list()
    operations.print_as_integers()
    operations.print_as_floats()
    operations.separate_by_type()