public class Problem_10_Solution {

    public static boolean isPalindrome(String s) {
        String normalizedString = normalize(s);
        
        int left = 0;
        int right = normalizedString.length() - 1;
        
        
        while (left < right) {
            if (normalizedString.charAt(left) != normalizedString.charAt(right)) {
                return false; 
            }
            left++;
            right--;
        }
        
        return true; 
    }
    
    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
    }

    public static void main(String[] args) {

        String[] testCases = {
            "A man, a plan, a canal, Panama", 
            "race a car", 
            "Was it a car or a cat I saw?", 
            "No 'x' in Nixon" 
        };

        for (String testCase : testCases) {
            System.out.println("\"" + testCase + "\" is palindrome: " + isPalindrome(testCase));
        }
    }
}
