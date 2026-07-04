/**
 * 模块 01 - 基础回顾
 * 第 2 节：运算符演示
 *
 * 运行: javac OperatorsDemo.java && java OperatorsDemo
 */
public class OperatorsDemo {

    public static void main(String[] args) {

        // ============ 1. 算术运算符 ============
        System.out.println("=== 1. 算术运算符 ===");
        int a = 10, b = 3;
        System.out.println("10 / 3  = " + (a / b));          // 3   整数除法，截断
        System.out.println("10 % 3  = " + (a % b));          // 1   取余
        System.out.println("10 / 3.0 = " + (a / 3.0));       // 3.333  有浮点参与，结果为浮点

        // % 的符号跟"被除数"（左操作数）
        System.out.println("-7 % 3  = " + (-7 % 3));         // -1
        System.out.println("7 % -3  = " + (7 % -3));         // 1

        // ++ 前置 vs 后置（经典面试题）
        int i = 5;
        System.out.println("i++ 输出: " + (i++));  // 5 (先用后加)
        System.out.println("现在 i = " + i);        // 6
        int j = 5;
        System.out.println("++j 输出: " + (++j));  // 6 (先加后用)

        // 经典陷阱题
        int k = 1;
        k = k++;   // 临时变量！k 还是 1
        System.out.println("k = k++ 后 k = " + k); // 1   (而不是 2)

        // ============ 2. 关系运算符 ============
        System.out.println("\n=== 2. 关系运算符 ===");
        System.out.println("5 == 5.0 ? " + (5 == 5.0));     // true  (类型提升后比较)
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println("s1 == s2 ? " + (s1 == s2));           // false (比地址)
        System.out.println("s1.equals(s2) ? " + s1.equals(s2));   // true  (比内容)

        // instanceof: 判断对象是否是某类/接口的实例
        Object obj = "hello";
        System.out.println("obj instanceof String ? " + (obj instanceof String));  // true
        // Java 16+ 模式匹配: if (obj instanceof String s) { ... }

        // ============ 3. 位运算符（高频面试）============
        System.out.println("\n=== 3. 位运算符 ===");
        int x = 0b1100;   // 12
        int y = 0b1010;   // 10
        System.out.println("12 & 10  = " + (x & y));    // 8    0b1000  按位与
        System.out.println("12 | 10  = " + (x | y));    // 14   0b1110  按位或
        System.out.println("12 ^ 10  = " + (x ^ y));    // 6    0b0110  按位异或
        System.out.println("~12      = " + (~x));       // -13  按位取反

        // 位移：经常用来代替乘除 2 的幂（高效）
        System.out.println("1 << 3   = " + (1 << 3));   // 8   等于 1 * 2^3
        System.out.println("8 >> 2   = " + (8 >> 2));   // 2   等于 8 / 2^2

        // >> vs >>> 的区别（用负数才看得出来）
        int neg = -8;
        System.out.println("-8 >> 1  = " + (neg >> 1));   // -4   (高位补 1，保持负号)
        System.out.println("-8 >>> 1 = " + (neg >>> 1));  // 2147483644 (高位补 0，变正数)

        // 经典：不用临时变量交换两个数
        int p = 10, q = 20;
        p = p ^ q; q = p ^ q; p = p ^ q;
        System.out.println("异或交换后: p=" + p + ", q=" + q);  // p=20, q=10

        // ============ 4. 逻辑运算符（短路 vs 非短路）============
        System.out.println("\n=== 4. 逻辑运算符 ===");
        int[] arr = {1, 2, 3};
        int idx = 3;
        // 短路: idx < arr.length 为 false 时，arr[idx] 不会被求值，避免越界
        if (idx < arr.length && arr[idx] == 4) {
            System.out.println("不会进来");
        } else {
            System.out.println("&& 短路: 左 false 时右不执行，避免越界");
        }

        // 短路演示：自定义方法
        boolean r1 = test(1) && test(2);  // test(1)=false，test(2) 不会被调用
        System.out.println("1 && 2 结果: " + r1);
        boolean r2 = test(3) || test(4);  // test(3)=true，test(4) 不会被调用
        System.out.println("3 || 4 结果: " + r2);

        // 非短路 & |：两边都会执行
        boolean r3 = test(1) & test(2);   // 即使左边 false，右边也执行
        System.out.println("1 & 2 结果: " + r3);

        // ============ 5. 赋值运算符（含隐式强转）============
        System.out.println("\n=== 5. 赋值运算符 ===");
        byte bb = 10;
        // bb = bb + 5;   // ❌ 编译错误: bb+5 是 int，赋回 byte 需强转
        bb += 5;          // ✅ 复合赋值自带隐式强转: bb = (byte)(bb + 5)
        System.out.println("bb += 5 -> " + bb);

        // ============ 6. 三目运算符（类型提升陷阱）============
        System.out.println("\n=== 6. 三目运算符 ===");
        int n = 5;
        String rs = (n > 0) ? "正" : (n < 0 ? "负" : "零");  // 可嵌套
        System.out.println("n 的符号: " + rs);

        // ⚠️ 经典陷阱：三目两侧类型不同会自动提升
        Object o = true ? Integer.valueOf(1) : Double.valueOf(2.0);
        System.out.println("三目提升结果: " + o);            // 1.0 (Double!)
        System.out.println("o 的实际类型: " + o.getClass().getSimpleName());  // Double

        // ============ 7. 字符串 + 的结合性（从左到右）============
        System.out.println("\n=== 7. 字符串拼接 ===");
        System.out.println(1 + 2 + "a");    // "3a"   先算 1+2=3，再拼 "a"
        System.out.println("a" + 1 + 2);    // "a12"  从左到右，"a"+1="a1"，再 +2="a12"
        System.out.println("a" + (1 + 2));  // "a3"   括号改变优先级

        // ============ 8. 运算符优先级（不用全背，多用括号）============
        System.out.println("\n=== 8. 优先级 ===");
        boolean ok = true || false && false;
        System.out.println("true || false && false = " + ok);  // true   && 优先级高于 ||
        System.out.println("(true || false) && false = " + ((true || false) && false)); // false
    }

    /** 测试方法，用于演示短路是否调用了它 */
    static boolean test(int x) {
        System.out.println("  -> test(" + x + ") 被调用");
        return x > 2;
    }
}
