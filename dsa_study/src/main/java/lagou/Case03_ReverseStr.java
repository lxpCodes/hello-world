package lagou;


import java.util.Stack;

/**
 * @ClassName Case03_ReverseStr
 * @Description 给定一个含有 n 个元素的链表，现在要求每 k 个节点一组进行翻转，打印翻转后的链表结果。其中，k 是一个正整数，且可被 n 整除
 *              例如，链表为 1 -> 2 -> 3 -> 4 -> 5 -> 6，k = 3，则打印 321654
 * @Author liangxp
 * @Date 2020/9/29 10:30
 **/
public class Case03_ReverseStr {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        // 用三个指针解决
//        ListNode node = reverseKGoup(head, 3);
//        while (node != null){
//            System.out.print(" " + node.object);
//            node = node.next;
//        }
        System.out.println();
        // 用栈解决
        reverseKGoup2(head, 3);

    }

    private static void reverseKGoup2(ListNode head, int k) {
        ListNode tmp = head;
        int count = 0;
        Stack stack = new Stack();
        while (tmp != null && count < k){
            stack.push(tmp.object);
            tmp = tmp.next;
            count ++;
            if (count == k){
                while (!stack.isEmpty()){
                    System.out.print(" " +stack.pop());
                }
                count = 0;
            }
        }
        while (!stack.isEmpty()){
            System.out.print(" " +stack.pop());
        }
    }

    private static ListNode reverseKGoup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null){
            for (int i = 0;i < k && end.next != null; i++){
                end = end.next;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);

            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;

        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    static class ListNode{
        private Object object;
        private ListNode next;
        private ListNode curr;
        private ListNode pre;

        public ListNode(Object object){
            this.object = object;
        }


    }


}
