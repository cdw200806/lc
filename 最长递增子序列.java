public class 最长递增子序列 {


    public static void main(String[] args) {

        //测试输入
        int[] nums = {4, 10, 4, 3, 8, 9};
        //自己写的。 看起来2分都不会写
        System.out.println(new 最长递增子序列().calculate(nums));

        //抄的最佳答案，但是我怀疑不一定记得住。
        System.out.println(new 最长递增子序列().cal2(nums));

        //学到的东西，1是最长递增子序列，利用d 最长为i的子序列末尾最小的值。构造这样的概念。经过一些数学证明。来实现一个贪心算法，提升时间性能。
        //学到的第二点就是二分法的pos可以单向跳转。处理好边界值。从0开始。
        //学到的第三点是，不要过分关注于等于，要简化代码。
        //学到的第四点，关于lr的重定义。跳过mid,使用 mid+1 mid-1。也不要考虑提前命中的事。简化代码。


        //dp代码 自己写的，注意序号对应就行。
        System.out.println(new 最长递增子序列().cal3(nums));

    }

    int calculate(int[] nums) {
        //采用动态规划 + 贪心优化 达成 nlogn

        // 定义dp[i], 代表前i个数结果。 dp[0]初始化为0。数组建好就是0。
        int dp[] = new int[nums.length + 1];

        //d[i] 存的是 长度为i的递增组序列，末尾最小值是多少。
        //比如长度为3，最小值为4。 长度为4，必然大于4。论证：假设长度为4也是4。那么去掉4之后，那个会比4小。导致矛盾。
        //递增子序列，有d[i]严格递增的机制。
        //有了这个。在新阶段中。根据当前最长。寻找末尾值。
        //若num[i]大于之前d[len]，本身就是必然要更新最长。 将来可能会优化d数组。
        //若num[i]等于之前的d[len],可以无视这个数据。
        //若num[i]小于之前的d[len]，用2分法，更新一下d。比如d现在到6了。 num[i]是4。 d是  1356。那我可以把5替换成4。找一个可以更新的更新一下。
        //如果没有d数组。每次循环，需要遍历dp.看看针对长度能到len的.能否找一个小于当前值的。如果没有，就是说我最后一个元素不能更长，所以也能维护。
        //所以dp 不会减少。但是并非严格递增。
        //所以辅助数组似乎也没有必要？ 直接在dp中寻找到所有为len的dp。然后挑一个最小的。
        //极限情况下，len可能每个格子都是len。但是正常情况下。len会集中在dp后面几个格子。
        //推翻，dp只记载了结果，但是不知道对应的最后一个数值在哪个位置，是多少。所以要一个一个遍历。
        //有了d数组之后。就之前之前最小是多少。
        //老做法是要用numi 一个一个去对比numj。如果大。就看看能否生成更大的len。
        //有了d数组之后，对于现有的len。直接就有最小的结果。看看能否让len++。若能。更新dlen
        //若不能。要更新dlen。这次是要让numi去更新合适的位置。小值，也要更新掉。确保所有长度，末尾都是最小的。
        //di意思是结果为i.末尾值最小的。在刚找到长度i时，记录一个结果。后面每个数据，如果小，都要更新一下它。
        //比如d   1 3 5 6 7 9   此时 代表前六个数最小值如此。 处理到i时，发现了4。d不扩充，意味着现在答案就是6。长度为6最末是9。但是我现在有个4。
        //首先，长度为3那个肯定可以更新为4。
        //假设之前的结论是对的。
        //然后长度为2的。我当前肯定是优化不掉。
        //然后就是长度为4的。有没有可能我能凑出一个长度为4的。 这里我更新了3。说明我已经小于3那个位置了。
        //假设没我也能搞出一个3的。那么3的位置肯定小于我。和我小于3相悖。所以我只能更新5那个位置。更新不了6那个位置。同理后面的也更新不掉。
        //至此，论证结束。仍然正确。
        //感悟，这个像是在做数学题。建议记住结论。像是使用了一些比较巧合的技巧。还是有点抽象的。

        int d[] = new int[nums.length + 1];

        int len = 1;
        d[1] = nums[0];
        //便于理解，所以下标从1开始计算。dp0用不到。如果输入是0.自然是0。根据输入长度。最后在+1的位置获取。 0特殊返回一下
        for (int i = 1; i < nums.length; i++) {
            //从低到高分阶段动态规划。
            //处理i 要考虑找到d[len]
            if (d[len] > nums[i]) {
                //尝试更新d[len] 一定要找到一个nums[i] 要严格小于某个的时候，更新一下。
                int mid = len / 2;
                int left = 0;
                int right = len;
                while (!(d[mid] > nums[i] && nums[i] > d[mid - 1]) && left != right) {
                    if (d[mid] > nums[i]) {
                        left = mid;
                        mid = (left + right) / 2;
                    } else {
                        right = mid;
                        mid = (left + right) / 2;
                    }
                }
                //此时已经找到。更新一下mid
                if (left != right) {
                    d[mid] = nums[i];
                }
            } else if (d[len] < nums[i]) {
                len++;
                d[len] = nums[i];
            }
            //如果相等，说明是无效数据，直接跳过
        }

        return len;
    }


    int cal2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] d = new int[nums.length + 1];
        d[1] = nums[0];
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0, mid;
                //首先，numsi 不会大于 最大值。 最多相等。 我要找一个位置，让numi 大于pos。也就是说严格小于nums里面最靠右的。
                //只要dmid小，就移动pos
                while (l < r) {
                    mid = (r + l) / 2;
                    if (d[mid] < nums[i]) {
                        //这个mid对应的数比较小，是严格安全的，pos右移
                        pos = mid;
                        //继续向右找。
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


    int cal3(int nums[]) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        //以第i个元素为结尾的，其len。
        dp[1] = 1;
        int res = 1;
        //i要完成迭代
        for (int i = 1; i < nums.length; i++) {
            int len = 1;
            for (int j = 0; j < i; j++) {
                //i前面的都试试
                if (dp[j + 1] + 1 > len && nums[i] > nums[j]) {
                    len = dp[j + 1] + 1;
                }
            }
            dp[i + 1] = len;
            res = Math.max(len, res);
        }
        return res;
    }
}


