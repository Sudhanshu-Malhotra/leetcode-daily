# 2265. Count Nodes Equal to Average of Subtree

**Difficulty:** Medium
**Topics:** Binary Tree, DFS, Recursion

## Problem

Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of the values in its subtree.

The average is rounded down to the nearest integer.

## Approach

Use **DFS (Depth-First Search)** with **postorder traversal**.

For every node, calculate:

* `sum` → sum of all values in its subtree
* `count` → number of nodes in its subtree

Then calculate:

```text
average = sum / count
```

If the average is equal to the current node's value, increment the answer.

Because Java integer division automatically rounds down, `sum / count` gives the required average.

### Why Postorder DFS?

We need the sum and count of the left and right subtrees before calculating the values for the current node.

So we process:

```text
Left → Right → Root
```

## Java

```java
class Solution {
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        if (sum / count == node.val) {
            ans++;
        }

        return new int[]{sum, count};
    }
}
```

## Example

```text
Input:
root = [4,8,5,0,1,null,6]

Output:
5
```

The nodes whose values equal the average of their subtrees are:

```text
4, 5, 0, 1, 6
```

Therefore, the answer is:

```text
5
```

## Complexity

* **Time:** `O(n)` — each node is visited once.
* **Space:** `O(h)` — recursion stack, where `h` is the height of the tree.

## Key Takeaway

For tree problems where a node needs information about its entire subtree, use **DFS and return the required information from the children**.

Here, each recursive call returns:

```text
{sum, count}
```
