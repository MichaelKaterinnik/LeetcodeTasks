package org.example.algorhytmical;

public class IsTreeSymmteric {

    /**
     ПРОВЕРКА БИНАРНОГО ДЕРЕВА НА СИММЕТРИЮ

     Опять же, с помощью рекурсии проверяем, равны ли значения val для node1 и node2.
     Если они равны, мы вызываем isMirror для левого поддерева node1 и правого поддерева node2, а также для
     правого поддерева node1 и левого поддерева node2.
     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.val == node2.val)
                && isMirror(node1.left, node2.right) // Проверка симметрии левого поддерева и правого поддерева
                && isMirror(node1.right, node2.left); // Проверка симметрии правого поддерева и левого поддерева
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
