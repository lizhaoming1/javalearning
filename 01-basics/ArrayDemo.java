import java.util.Arrays;

/**
 * 模块 01 - 基础回顾
 * 第 4 节：数组演示
 *
 * 运行: javac ArrayDemo.java && java ArrayDemo
 */
public class ArrayDemo {

    public static void main(String[] args) {

        // ============ 1. 创建数组的几种方式 ============
        System.out.println("=== 1. 创建方式 ===");
        int[] a1 = new int[5];              // 元素默认 0
        int[] a2 = {1, 2, 3, 4, 5};         // 静态初始化（最常用）
        int[] a3 = new int[]{10, 20, 30};   // 完整写法（传参时必须用这种）

        // ⚠️ 错误写法
        // int[] wrong = new int[3]{1,2,3};  // ❌ 不能同时指定长度和元素

        System.out.println("a1 = " + Arrays.toString(a1));   // [0, 0, 0, 0, 0]
        System.out.println("a2 = " + Arrays.toString(a2));
        System.out.println("a3 = " + Arrays.toString(a3));

        // ============ 2. length 是属性不是方法 ============
        System.out.println("\n=== 2. length 属性 ===");
        System.out.println("a2.length = " + a2.length);   // 5 (无括号)
        // String 是方法: "abc".length()，数组是属性: arr.length  ← 易混淆

        // ============ 3. 默认值规则 ============
        System.out.println("\n=== 3. 默认值 ===");
        int[] ints = new int[3];
        boolean[] bools = new boolean[3];
        String[] strs = new String[3];
        System.out.println("int[]     默认: " + Arrays.toString(ints));   // [0, 0, 0]
        System.out.println("boolean[] 默认: " + Arrays.toString(bools));  // [false, false, false]
        System.out.println("String[]  默认: " + Arrays.toString(strs));   // [null, null, null]

        // ============ 4. 数组越界（运行时异常）============
        System.out.println("\n=== 4. 越界异常 ===");
        try {
            int x = a2[10];   // ❌ 运行时抛 ArrayIndexOutOfBoundsException
            System.out.println(x);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获到越界异常: " + e.getMessage());
        }

        // ============ 5. 数组是引用类型（传参影响外部）============
        System.out.println("\n=== 5. 引用传递 ===");
        int[] arr = {1, 2, 3};
        System.out.println("修改前: " + Arrays.toString(arr));
        modifyArray(arr);
        System.out.println("修改后: " + Arrays.toString(arr));   // [99, 2, 3]  外部可见

        // ============ 6. Arrays 工具类（高频使用）============
        System.out.println("\n=== 6. Arrays 工具类 ===");
        int[] data = {5, 2, 8, 1, 9, 3};

        // 排序
        int[] sorted = data.clone();      // clone 防止修改原数组
        Arrays.sort(sorted);
        System.out.println("排序后: " + Arrays.toString(sorted));   // [1, 2, 3, 5, 8, 9]

        // 二分查找（必须先排序）
        int idx = Arrays.binarySearch(sorted, 8);
        System.out.println("找 8 的索引: " + idx);    // 4

        // 填充
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("填充后: " + Arrays.toString(filled));   // [7, 7, 7, 7, 7]

        // 拷贝
        int[] copy1 = Arrays.copyOf(data, 3);          // 拷前 3 个
        int[] copy2 = Arrays.copyOfRange(data, 1, 4);  // 拷 [1, 4)
        System.out.println("copyOf(data,3): " + Arrays.toString(copy1));     // [5, 2, 8]
        System.out.println("copyOfRange(data,1,4): " + Arrays.toString(copy2));  // [2, 8, 1]

        // 比较
        int[] x1 = {1, 2, 3};
        int[] x2 = {1, 2, 3};
        System.out.println("x1 == x2 ? " + (x1 == x2));                // false (比地址)
        System.out.println("Arrays.equals ? " + Arrays.equals(x1, x2)); // true  (比内容)

        // ============ 7. 二维数组（含不规则数组）============
        System.out.println("\n=== 7. 二维数组 ===");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5},
            {6, 7, 8, 9}
        };
        // 二维数组本质是"数组的数组"，每行可不等长
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("第" + i + "行长度: " + matrix[i].length +
                    " -> " + Arrays.toString(matrix[i]));
        }

        // 打印二维数组要用 deepToString
        System.out.println("deepToString: " + Arrays.deepToString(matrix));

        // ============ 8. 增强for 遍历二维数组 ============
        System.out.println("\n=== 8. 遍历二维 ===");
        for (int[] row : matrix) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

        // ============ 9. 实战：冒泡排序 ============
        System.out.println("\n=== 9. 冒泡排序 ===");
        int[] toSort = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前: " + Arrays.toString(toSort));
        bubbleSort(toSort);
        System.out.println("排序后: " + Arrays.toString(toSort));
    }

    /** 修改数组元素（影响外部）*/
    static void modifyArray(int[] a) {
        a[0] = 99;
    }

    /** 冒泡排序（升序）*/
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;   // 优化：本轮无交换说明已有序
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
