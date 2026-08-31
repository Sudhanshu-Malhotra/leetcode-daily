/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int firstCritical = -1;
        int prevCritical = -1;

        int position = 1;
        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a critical point
            boolean isMax = curr.val > prev.val && curr.val > next.val;
            boolean isMin = curr.val < prev.val && curr.val < next.val;

            if (isMax || isMin) {
                if (firstCritical == -1) {
                    // First critical point
                    firstCritical = position;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );
                }

                // Update previous critical point
                prevCritical = position;
            }

            prev = curr;
            curr = next;
            position++;
        }

        // Fewer than two critical points
        if (firstCritical == prevCritical) {
            return new int[]{-1, -1};
        }

        // Maximum distance = last critical - first critical
        int maxDistance = prevCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}
