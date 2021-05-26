package leetcode;

import struc.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: liangxp
 * @description: 层序遍历二叉树  使用广度优先遍历实现
 * @time: 2021/4/6 15:23
 */
public class Case0102_treeNode_level_travers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);

        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(35);
        TreeNode node5 = new TreeNode(12);
        TreeNode node6 = new TreeNode(40);

        root.setLeft(node1);
        root.setRight(node2);

        node1.setLeft(node3);
        node1.setRight(node4);

        node2.setLeft(node5);
        node2.setRight(node6);

        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list: lists) {
            for (Integer integer: list) {
                System.out.println(integer);
            }
        }
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        //将根节点放入队列中，然后不断遍历队列
        queue.add(root);
        while(queue.size() > 0) {
            //获取当前队列的长度，这个长度相当于 当前这一层的节点个数
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<>();
            //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时list中
            //如果节点的左/右子树不为空，也放入队列中
            for(int i = 0;i < size;++i) {
                TreeNode t = queue.remove();
                tmp.add(t.getValue());
                if(t.getLeft() != null) {
                    queue.add(t.getLeft());
                }
                if(t.getRight() != null) {
                    queue.add(t.getRight());
                }
            }
            //将临时list加入最终返回结果中
            res.add(tmp);
        }
        return res;
    }

}
