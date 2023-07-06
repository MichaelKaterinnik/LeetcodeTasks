package org.example.stringTasks;

import java.util.Stack;

public class ValidParentheses {

    /**
     Использован Stack для решения (подсказка).

     Входной параметр s представляет собой строку, в которой содержатся скобки различных типов, такие как
     круглые скобки (), фигурные скобки {}, и квадратные скобки [].

     Метод проходит по каждому символу строки s с помощью цикла for.

     Если текущий символ является открывающей скобкой ((, {, [), он помещается в стек stack. Если текущий
     символ является закрывающей скобкой (), }, ]),  происходит проверка соответствия этой закрывающей скобки
     последней открывающей скобке в стеке. Если соответствие не выполняется, то комбинация скобок невалидна и
     метод возвращает false. Если соответствие выполняется, то пара скобок считается валидной, и открывающая
     скобка удаляется из стека.

     После обработки всех символов входной строки, происходит окончательная проверка. Если стек stack пустой,
     это означает, что все открывающие скобки были закрыты правильно, и метод возвращает true.

     Если же стек не пустой, это означает, что есть несбалансированные скобки (больше открывающих скобок, чем
     закрывающих), и метод возвращает false.
     */

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
