package leetcode;

import struc.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Case0102_treeNode_level_travers_lx
 * @Description 练习二叉树的层序遍历
 * @Author liangxp
 * @Date 2021/4/6 16:33
 **/
public class Case0102_treeNode_level_travers_lx {

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

        List<List<Integer>> lists = levelOrder(node1);
        for (List<Integer> list: lists) {
            for (Integer integer: list) {
                System.out.println(integer);
            }
        }
    }


    public static List<List<Integer>> levelOrder(TreeNode root){
        if (root == null){
            return new ArrayList<>();
        }

        List<List<Integer>> resp = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (queue.size() > 0){
            int size = queue.size();
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode treeNode = queue.remove();
                tmp.add(treeNode.getValue());
                if (treeNode.getLeft() != null){
                    queue.add(treeNode.getLeft());
                }
                if (treeNode.getRight() != null){
                    queue.add(treeNode.getRight());
                }
            }

            resp.add(tmp);
        }
        return resp;
    }
}
