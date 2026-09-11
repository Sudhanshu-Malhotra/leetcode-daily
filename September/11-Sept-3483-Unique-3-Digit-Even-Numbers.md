# LeetCode 3483 - Unique 3-Digit Even Numbers

**Difficulty:** Easy
**Problem:** Unique 3-Digit Even Numbers

## Problem

You are given an array of digits called `digits`.

Determine the number of **distinct three-digit even numbers** that can be formed using these digits.

### Rules

* Each copy of a digit can only be used once per number.
* The number must be a **three-digit number**, so it cannot start with `0`.
* The number must be **even**, so its last digit must be `0, 2, 4, 6, or 8`.
* Duplicate digits can be used only if they appear multiple times in the input.

## Examples

### Example 1

```text
Input: digits = [1,2,3,4]
Output: 12
```

The valid numbers are:

```text
124, 132, 134, 142,
214, 234,
312, 314, 324, 342,
412, 432
```

So the answer is `12`.

### Example 2

```text
Input: digits = [0,2,2]
Output: 2
```

The valid numbers are:

```text
202, 220
```

`222` cannot be formed because there are only two copies of `2`.

### Example 3

```text
Input: digits = [6,6,6]
Output: 1
```

Only:

```text
666
```

can be formed.

### Example 4

```text
Input: digits = [1,3,5]
Output: 0
```

There is no even digit, so no three-digit even number can be formed.

---

## Approach

Since a three-digit number can only range from `100` to `999`, there are only **900 possible numbers**.

We can simply:

1. Iterate through every number from `100` to `999`.
2. Skip the number if it is odd.
3. Count how many times each digit is required by the number.
4. Count how many times each digit is available in `digits`.
5. Check whether the available digits are enough to form the number.
6. If yes, add it to the answer.

Because we check each actual number, **distinctness is automatically handled**.

---

## Java

```java
class Solution {
    public int totalNumbers(int[] digits) {
        int ans = 0;

        // Try every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Number must be even
            if (num % 2 != 0) {
                continue;
            }

            // Count digits needed for this number
            int[] need = new int[10];
            int x = num;

            while (x > 0) {
                need[x % 10]++;
                x /= 10;
            }

            // Count digits available
            int[] have = new int[10];

            for (int d : digits) {
                have[d]++;
            }

            // Check if we have enough copies of every digit
            boolean possible = true;

            for (int i = 0; i < 10; i++) {
                if (need[i] > have[i]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                ans++;
            }
        }

        return ans;
    }
}
```

## Explanation

Suppose:

```text
digits = [0, 2, 2]
```

Consider the number `202`.

### Required digits

```text
2 → 2 times
0 → 1 time
```

So:

```text
need = {0:1, 2:2}
```

### Available digits

```text
0 → 1 time
2 → 2 times
```

Therefore:

```text
need[0] <= have[0]  ✓
need[2] <= have[2]  ✓
```

So `202` is valid.

Now consider `200`.

It requires:

```text
0 → 2 times
2 → 1 time
```

But we have only one `0`.

Therefore:

```text
need[0] > have[0]
```

So `200` is invalid.

---

## Why This Works

Every valid answer must be:

* between `100` and `999`,
* even,
* and constructible from the available digits.

The loop checks **every possible three-digit number**, so no valid number is missed.

The `need` array makes sure we respect duplicate digit counts.

For example:

```text
digits = [1, 2, 2]
```

`122` is valid because we have two `2`s.

But:

```text
222
```

is invalid because we need three `2`s but only have two.

---

## Complexity

There are only `900` three-digit numbers.

For each number, we use arrays of size `10`.

### Time Complexity

```text
O(900 × 10)
```

Since `900` and `10` are constants:

```text
O(1)
```

### Space Complexity

```text
O(10) = O(1)
```

---

## Key Takeaway

When the possible answer space is very small, **brute force can be the simplest and safest approach**.

Here, there are only `900` possible three-digit numbers, so checking each one is completely efficient.
