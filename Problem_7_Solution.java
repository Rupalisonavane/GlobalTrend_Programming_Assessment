class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;
            maxArea = Math.max(maxArea, area);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
public class Problem_7_Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int[] heights1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea(heights1)); 
        
        int[] heights2 = {1, 1};
        System.out.println(solution.maxArea(heights2)); 
        
        int[] heights3 = {4, 3, 2, 1, 4};
        System.out.println(solution.maxArea(heights3)); 
        
        int[] heights4 = {1, 2, 1};
        System.out.println(solution.maxArea(heights4)); 
    }
}
