import java.util.Arrays;

/**
 * 模块 01 - 基础回顾
 * 第 5 节：方法演示
 *
 * 运行: javac MethodDemo.java && java MethodDemo
 */
public class MethodDemo {

    public static void main(String[] args) {

        // ============ 1. 方法定义与调用 ============
        System.out.println("=== 1. 方法定义 ===");
        int sum = add(3, 5);
        System.out.println("add(3, 5) = " + sum);

        // ============ 2. Java 只有值传递（核心概念）============
        System.out.println("\n=== 2. 值传递 ===");

        // 2.1 基本类型：传值的副本，不影响外部
        int x = 10;
        changeInt(x);
        System.out.println("changeInt后 x = " + x);   // 10 (没变)

        // 2.2 引用类型：传引用的副本，通过引用改属性会影响外部
        int[] arr = {1, 2, 3};
        changeArray(arr);
        System.out.println("changeArray后 arr = " + Arrays.toString(arr));  // [99, 2, 3]

        // 2.3 ⚠️ 关键陷阱：方法内重新赋值引用，不影响外部
        int[] arr2 = {1, 2, 3};
        reassignArray(arr2);
        System.out.println("reassignArray后 arr2 = " + Arrays.toString(arr2));  // [1, 2, 3] (没变!)

        // ============ 3. 方法重载 Overload ============
        System.out.println("\n=== 3. 方法重载 ===");
        System.out.println("max(3, 5) = " + max(3, 5));        // int 版本
        System.out.println("max(3.1, 5.2) = " + max(3.1, 5.2)); // double 版本
        System.out.println("max(1, 2, 3) = " + max(1, 2, 3));   // 三个参数版本

        // ⚠️ 重载只看参数列表，和返回值无关
        // 下面这样不行：只有返回值不同，编译报错
        // int max(int a, int b) { return a > b ? a : b; }
        // double max(int a, int b) { return a > b ? a : b; }  // ❌ 重复方法

        // ============ 4. 可变参数 ============
        System.out.println("\n=== 4. 可变参数 ===");
        System.out.println("sum() = " + sumAll());               // 0 个参数
        System.out.println("sum(1) = " + sumAll(1));             // 1 个参数
        System.out.println("sum(1,2,3,4,5) = " + sumAll(1,2,3,4,5)); // 多个参数

        // 也可以传数组
        int[] nums = {10, 20, 30};
        System.out.println("sum(int[]) = " + sumAll(nums));

        // ============ 5. 方法参数的默认值？没有！============
        // Java 不支持方法参数默认值，要通过重载模拟
        System.out.println("\n=== 5. 模拟默认值 ===");
        System.out.println("greet(\"张三\") = " + greet("张三"));
        System.out.println("greet() = " + greet());

        // ============ 6. 递归（经典：阶乘、斐波那契）============
        System.out.println("\n=== 6. 递归 ===");
        System.out.println("factorial(5) = " + factorial(5));    // 120
        System.out.println("fib(10) = " + fib(10));              // 55

        // 递归 vs 循环：阶乘对比
        System.out.println("factorialLoop(5) = " + factorialLoop(5));

        // ============ 7. 方法执行过程（栈帧）============
        System.out.println("\n=== 7. 方法调用栈 ===");
        a();

        // ============ 8. 实战：二分查找（递归+循环两种写法）============
        System.out.println("\n=== 8. 二分查找 ===");
        int[] sorted = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("找 7: " + binarySearch(sorted, 7));    // 3
        System.out.println("找 8: " + binarySearch(sorted, 8));    // -1
        System.out.println("找 1: " + binarySearch(sorted, 1));    // 0
        System.out.println("找 15: " + binarySearch(sorted, 15));  // 7
    }

    // ============ 1. 基本方法 ============
    static int add(int a, int b) {
        return a + b;
    }

    // ============ 2. 值传递演示 ============
    static void changeInt(int n) {
        n = 99;   // 改的是副本
    }

    static void changeArray(int[] a) {
        a[0] = 99;  // 通过引用改对象内容，外部可见
    }

    static void reassignArray(int[] a) {
        a = new int[]{100, 200, 300};  // 重新赋值引用，外部不可见！
    }

    // ============ 3. 方法重载 ============
    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static double max(double a, double b) {
        return a > b ? a : b;
    }

    static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    // ============ 4. 可变参数 ============
    static int sumAll(int... nums) {  // nums 本质是 int[]
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum;
    }

    // ⚠️ 可变参数必须是最后一个参数
    // static void bad(int... nums, int x) { }  // ❌ 编译错误
    static void good(String prefix, int... nums) { }  // ✅

    // ============ 5. 模拟默认值（通过重载）============
    static String greet(String name) {
        return "你好，" + name + "！";
    }

    static String greet() {
        return greet("陌生人");  // 调用带参版本，模拟默认值
    }

    // ============ 6. 递归 ============
    static int factorial(int n) {
        if (n <= 1) return 1;            // 终止条件（基线条件）
        return n * factorial(n - 1);     // 递归调用，向终止条件收敛
    }

    static int factorialLoop(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // 斐波那契：f(0)=0, f(1)=1, f(n)=f(n-1)+f(n-2)
    static int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
        // ⚠️ 这个写法有大量重复计算，O(2^n)，后面学动态规划会优化
    }

    // ============ 7. 方法调用链（演示栈帧）============
    static void a() {
        System.out.println("  进入 a()");
        b();
        System.out.println("  离开 a()");
    }

    static void b() {
        System.out.println("    进入 b()");
        c();
        System.out.println("    离开 b()");
    }

    static void c() {
        System.out.println("      进入 c()");
        // 打印当前调用栈
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 2; i < stack.length; i++) {
            System.out.println("      栈帧: " + stack[i].getMethodName());
        }
        System.out.println("      离开 c()");
    }

    // ============ 8. 二分查找（循环版）============
    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;  // 防溢出，不写 (left+right)/2
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;  // 没找到
    }
}
