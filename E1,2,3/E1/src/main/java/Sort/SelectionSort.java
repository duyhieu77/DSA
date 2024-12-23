package Sort;

import Entity.Student;
import java.util.List;

public class SelectionSort {
    public static void sort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).getScore() < students.get(minIndex).getScore()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Student temp = students.get(minIndex);
                students.set(minIndex, students.get(i));
                students.set(i, temp);
            }
        }
    }
}