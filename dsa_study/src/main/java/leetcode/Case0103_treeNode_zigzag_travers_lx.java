package leetcode;

import struc.TreeNode;

import java.util.*;

/**
 * @ClassName Case0103_treeNode_zigzag_travers_lx
 * @Description 齿状遍历二叉树练习
 * @Author liangxp
 * @Date 2021/4/6 17:42
 **/
public class Case0103_treeNode_zigzag_travers_lx {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(9);

        TreeNode node1 = new TreeNode(7);
        TreeNode node2 = new TreeNode(15);

        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(6);

        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(13);

        root.setLeft(node1);
        root.setRight(node2);

        node1.setLeft(node3);
        node1.setRight(node4);

        node2.setLeft(node5);
        node2.setRight(node6);

        List<List<Integer>> lists = zigzagTravers(root);
        lists.forEach(list -> {list.forEach(integer -> System.out.println(integer));});

    }

    public static List<List<Integer>> zigzagTravers(TreeNode root){
        List<List<Integer>> resp = new LinkedList<>();
        if (root == null){
            return resp;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrder = true;

        while (!queue.isEmpty()){
            Deque<Integer> tmp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (isOrder){
                    tmp.offerLast(treeNode.getValue());
                } else {
                    tmp.offerFirst(treeNode.getValue());
                }
                if (treeNode.getLeft() != null) {
                    queue.offer(treeNode.getLeft());
                }
                if (treeNode.getRight() != null){
                    queue.offer(treeNode.getRight());
                }
            }
            resp.add((List<Integer>) tmp);
            isOrder = !isOrder;
        }
        return resp;
    }
}
