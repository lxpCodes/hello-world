package leetcode;

/**
 * @ClassName Case0876_find_mid_of_list
 * @Description 找到链表中间节点
 *              快慢指针解决
 * @Author liangxp
 * @Date 2021/4/19 17:32
 **/
public class Case0876_find_mid_of_list {

    static class ListNode{
        ListNode next;
        int val;

        ListNode() {}
        ListNode(int val){
            this.val = val;
        }

    }

    // 链表为奇数个时，返回中间节点，链表为偶数个时，返回中间两个节点的第一个节点
    public ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Case0876_find_mid_of_list list = new Case0876_find_mid_of_list();
    }
}
