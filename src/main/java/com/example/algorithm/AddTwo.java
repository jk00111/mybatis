package com.example.algorithm;

public class AddTwo {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode();
        sum(l1, l2, false, answer);
        return answer.next;
    }

    private void sum(final ListNode l1, final ListNode l2, boolean flag, final ListNode answer) {
        if (isFinish(l1, l2, flag)) {
            return;
        }

        int sum;

        if (flag) {
            sum = getValueWrapper(l1) + getValueWrapper(l2) + 1;
        }else {
            sum = getValueWrapper(l1) + getValueWrapper(l2);
        }


        if (sum > 9) {
            flag = true;
            answer.next = new ListNode(sum - 10);
        }else {
            flag = false;
            answer.next = new ListNode(sum);
        }

        sum(getNextElseNull(l1), getNextElseNull(l2), flag, answer.next);
    }

    private int getValueWrapper(ListNode listNode) {
        if (listNode == null) {
            return 0;
        }

        return listNode.val;
    }

    private ListNode getNextElseNull(ListNode listNode) {
        if (listNode == null) {
            return null;
        }

        if (listNode.next == null) {
            return null;
        }

        return listNode.next;
    }

    private boolean isFinish(ListNode l1, ListNode l2, boolean flag) {
        return l1 == null && l2 == null && !flag;
    }


    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
