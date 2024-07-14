import java.util.Random;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int targetIndex = nums.length - k;
        return quickselect(nums, 0, nums.length - 1, targetIndex);
    }
    
    private int quickselect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);
        
        pivotIndex = partition(nums, left, right, pivotIndex);
        
        if (pivotIndex == k) {
            return nums[k];
        } else if (pivotIndex < k) {
            return quickselect(nums, pivotIndex + 1, right, k);
        } else {
            return quickselect(nums, left, pivotIndex - 1, k);
        }
    }
    
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        
        swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
public class Problem_8_Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println(solution.findKthLargest(nums1, k1)); 
        
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println(solution.findKthLargest(nums2, k2)); 
    }
}
