package struc;

/**
 * @ClassName TreeNode
 * @Description 二叉树
 * @Author liangxp
 * @Date 2021/4/6 15:27
 **/
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode(){

    }

    public TreeNode(int value){
        this.value = value;
    }

    public TreeNode(int value,TreeNode left, TreeNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setLeft(TreeNode lnode){
        this.left = lnode;
    }

    public void setRight(TreeNode rnode){
        this.right = rnode;
    }

    public int size(TreeNode root) {
        if (root == null){
            return 0;
        } else {
            return 1 + size(root.left) + size(root.right);
        }
    }

    //前序遍历
    public static void preTraverseTree(TreeNode root){
        if (root != null){
            // 根节点
            System.out.println(root.getValue());
            // 左节点
            preTraverseTree(root.getLeft());
            // 右节点
            preTraverseTree(root.getRight());
        }
    }

    //中序遍历
    public static void inTraverseTree(TreeNode root){
        if (root != null){
            inTraverseTree(root.getLeft());
            System.out.println(root.getValue());
            inTraverseTree(root.getRight());
        }
    }

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

        System.out.println(root.size(root));
        preTraverseTree(root);
        inTraverseTree(root);
    }




}
