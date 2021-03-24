package leetcode;

/**
 * @ClassName Case0002_add_linklist
 * @Description 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *              如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *              您可以假设除了数字 0 之外，这两个数都不会以 0 开头
 *              输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *              输出：7 -> 0 -> 8
 *              原因：342 + 465 = 807
 * @Author liangxp
 * @Date 2020/12/30 18:19
 **/
public class Case0002_add_linklist {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        listNode1.next = new ListNode(4);
        listNode1.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(4);
        listNode2.next.next.next = new ListNode(9);

        ListNode result = addTwoNumbers(listNode1,listNode2);
        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //要返回的新链表
        ListNode head = new ListNode();
        //操作链表用的指针
        ListNode tmp = head;
        //l1和l2中的节点都没有取完时
        while(l1 != null && l2 != null){
            //计算新节点的值
            int val = l1.val + l2.val;
            //检查是否需要进位
            if(val >= 10){
                //如果l1的下一个节点不是空
                if(l1.next != null)
                    //下一个节点的值+1（向上进位，此时下一个节点的值可能为10）
                    l1.next.val += 1;
                else{
                    //没有下一个节点
                    //给l1创建一个新节点，值为1
                    l1.next = new ListNode(1);
                }
                //值取余10，获取个位数部分
                val %= 10;
            }
            //将新链表当前节点值设置为计算值
            tmp.val = val;
            //为新链表添加新节点
            tmp.next = new ListNode();
            //tmp移动到新节点
            tmp = tmp.next;
            //l1移动到下一个元素
            l1 = l1.next;
            //l2移动到下一个元素
            l2 = l2.next;
        }
        //此时为l2先被取完，l1还有剩余元素
        if(l1 != null){
            //直接将剩余部分接到新链表上，注意此时l1的值可能为10（原本是9，上次计算进位+1）
            tmp.val = l1.val;
            tmp.next = l1.next;
        }
        //此时为l1先被取完，l2还有剩余元素
        if(l2 != null){
            //直接将剩余部分接到新链表上
            tmp.val = l2.val;
            tmp.next = l2.next;
        }
        //此时为l1，l2长度相同，二者同时被取完，新链表中存在一个多余的节点
        if(l1 == l2 && l1 == null){
            //从头开始遍历，找到多余节点的上一个节点，将它的next设置为null，即删除多余节点
            tmp = head;
            while(tmp.next.next != null){
                tmp = tmp.next;
            }
            tmp.next = null;
        }
        //处理拼接l1可能存在值为10的节点的问题
        while(tmp != null){
            //此时tmp所在位置为拼接点，由此开始向后遍历
            if(tmp.val >= 10){
                //发现值为10的节点
                if(tmp.next != null){
                    //进位操作
                    tmp.next.val += 1;
                }else{
                    tmp.next = new ListNode(1);
                }
                tmp.val %= 10;
            }
            tmp = tmp.next;
        }
        return head;
    }


    static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
