import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false; 
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; 
                }
            }
        }
        
        return stack.isEmpty(); 
    }
}
public class Problem_6_Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.isValid("()"));      
        System.out.println(solution.isValid("()[]{}"));  
        System.out.println(solution.isValid("(]"));     
        System.out.println(solution.isValid("([)]"));    
        System.out.println(solution.isValid("{[]}"));    
        System.out.println(solution.isValid(""));       
    }
}