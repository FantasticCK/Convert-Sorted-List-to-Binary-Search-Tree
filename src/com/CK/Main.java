package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// Inorder without global variable
class Solution {

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r, ListNode head) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1, head);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(head.val);
        node.left = left;

        if (head.next != null) {
            head.val = head.next.val;
            head.next = head.next.next;
        }

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r, head);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1, head);
    }
}

// Inorder with Global variable
class Solution {

    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }
}

// Convert to ArrayList
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        List<Integer> nums = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            nums.add(curr.val);
            curr = curr.next;
        }
        return helper(nums, 0, nums.size() - 1);
    }

    private TreeNode helper(List<Integer> nums, int st, int ed) {
        if (st > ed) {
            return null;
        }

        int mid = st + (ed - st) / 2;
        TreeNode root = new TreeNode(nums.get(mid));
        root.left = helper(nums, st, mid - 1);
        root.right = helper(nums, mid + 1, ed);
        return root;
    }
}

