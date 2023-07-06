package org.example.arraysTasks;

import java.util.HashSet;
import java.util.Set;

public class HasArrayDuplicate {

    // поиск повторяющихся элементов

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }
}
