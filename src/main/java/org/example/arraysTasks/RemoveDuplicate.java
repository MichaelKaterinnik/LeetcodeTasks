package org.example.arraysTasks;

public class RemoveDuplicate {

    public int removeDuplicatesFromSortedArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int uniquePointer = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniquePointer]) {
                uniquePointer++;
                nums[uniquePointer] = nums[i];
            }
        }

        return uniquePointer + 1;
    }
}
