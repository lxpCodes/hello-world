package leetcode;

import struc.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName Case0103_treeNode_zigzag_travers
 * @Description 二叉树的齿状排序
 * @Author liangxp
 * @Date 2021/4/6 17:33
 **/
public class Case0103_treeNode_zigzag_travers {


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);

        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(15);

        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(16);

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);
        node3.setRight(node7);

        List<List<Integer>> lists = zigzagLevelOrder(node1);
        for (List<Integer> list: lists) {
            for (Integer integer: list) {
                System.out.println(integer);
            }
        }
    }

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resp = new LinkedList<>();
        if (root == null){
            return resp;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isOrderLeft = true;

        while (!queue.isEmpty()){
            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (isOrderLeft){
                    levelList.offerLast(treeNode.getValue());
                } else {
                    levelList.offerFirst(treeNode.getValue());
                }

                if (treeNode.getLeft() != null){
                    queue.offer(treeNode.getLeft());
                }
                if (treeNode.getRight() != null){
                    queue.offer(treeNode.getRight());
                }

            }
            resp.add((List<Integer>) levelList);
            isOrderLeft = !isOrderLeft;
        }

        return resp;
    }
}
