package org.example.algorhytmical;

import java.util.ArrayList;
import java.util.List;

public class BunaryTree {

    /**
     СМОТРИМ КАК ОБОЙТИ БИНАРНОЕ ДЕРЕВО

     Надо поместить все значения в List - углубляемся (используя рекурсию) вглубь дерева, пока какая-то из
     веток не дает null; вызывая тот же метод на каждую не-null ноду дерева (левую - правую)

     Метод maxDepth для подсчета самой глубококй ветки (количества узлов),
     используем рекурсию, углубляясь максимально по направлени "влево", а потом "вправо" - количество переходов
     будет давать увеличение счетчика - и так до null-значения.
     В итоге получим самую длинную ветку.
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalHelper(root, result);
        return result;
    }

    private void inorderTraversalHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorderTraversalHelper(node.left, result); // Рекурсивный обход левого поддерева
        result.add(node.val); // Добавление значения текущего узла в результат
        inorderTraversalHelper(node.right, result); // Рекурсивный обход правого поддерева
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
