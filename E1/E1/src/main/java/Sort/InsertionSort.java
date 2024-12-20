package Sort;

import Entity.Student;
import java.util.List;

public class InsertionSort {
    public static void sort(List<Student> students) {
        int n = students.size();
        for (int i = 1; i < n; i++) {
            Student temp = students.get(i);
            int j = i - 1;

            while (j >= 0 && students.get(j).getScore() > temp.getScore()) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, temp);
        }
    }
}