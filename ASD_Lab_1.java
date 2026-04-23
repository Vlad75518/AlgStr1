// Рівень 1 — Черга дійсних чисел (векторне розміщення)
package ASD_Lab_1;

class QueueDouble {

    private double[] data;              // масив для зберігання елементів
    private int size;                   // поточна кількість елементів
    private int tail;                   // індекс наступної вставки
    private static final int CAPACITY = 10; // максимальний розмір

    // Конструктор — ініціалізує порожню чергу
    public QueueDouble() {
        data = new double[CAPACITY];
        size = 0;
        tail = 0;
    }

    // Перевірка: черга повна?
    public boolean isFull() {
        return size == data.length;
    }

    // Перевірка: черга порожня?
    public boolean isEmpty() {
        return size == 0;
    }

    // Вставка елемента в хвіст черги
    public boolean enqueue(double value) {
        if (isFull()) {
            System.out.println("  [ПОМИЛКА] Черга переповнена, елемент " + value + " не додано.");
            return false;
        }
        data[tail] = value;                      // записуємо в хвіст
        tail = (tail + 1) % data.length;         // зсуваємо хвіст (кільцево)
        size++;                                  // збільшуємо лічильник
        return true;
    }

    // Видалення елемента з голови черги
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Черга порожня — видалення неможливе!");
        }
        int head = (tail - size + data.length) % data.length; // обчислюємо голову
        double value = data[head];               // зчитуємо значення з голови
        size--;                                  // зменшуємо лічильник
        return value;                            // повертаємо видалений елемент
    }

    // Виведення вмісту черги
    public void print() {
        if (isEmpty()) {
            System.out.println("  [Черга порожня]");
            return;
        }
        System.out.printf("  Черга (%d ел.): ", size);
        for (int i = 0; i < size; i++) {
            int idx = (tail - size + i + data.length) % data.length;
            System.out.printf("%.4f ", data[idx]);
        }
        System.out.println();
    }
}

public class ASD_Lab_1 {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" РІВЕНЬ 1: Черга дійсних чисел (масив) ");
        System.out.println("========================================");

        // Створюємо екземпляр черги
        QueueDouble queue = new QueueDouble();

        // Вставка елементів
        System.out.println("\n-- Вставка елементів у чергу --");
        double[] values = {3.14, 2.71, 1.41, 9.80, 0.57, 6.67};
        for (double v : values) {
            boolean ok = queue.enqueue(v);
            System.out.printf("  enqueue(%.2f) -> %s%n", v, ok ? "OK" : "FAILED");
        }

        // Вміст після вставки
        System.out.println("\n-- Вміст черги після вставки --");
        queue.print();

        // Видалення елементів
        System.out.println("\n-- Видалення 2 елементів з черги --");
        for (int i = 0; i < 2; i++) {
            double removed = queue.dequeue();
            System.out.printf("  dequeue() -> %.4f%n", removed);
        }

        // Вміст після видалення
        System.out.println("\n-- Вміст черги після видалення --");
        queue.print();
    }
}