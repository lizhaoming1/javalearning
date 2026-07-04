/**
 * 模块 01 - 基础回顾
 * 第 1 节：数据类型演示
 *
 * 运行: javac DataTypesDemo.java && java DataTypesDemo
 */
public class DataTypesDemo {

    // 成员变量（字段）有默认值
    static int defaultInt;          // 0
    static boolean defaultBool;     // false
    static String defaultStr;       // null

    public static void main(String[] args) {
        // ============ 1. 八种基本类型 ============
        byte    b = 127;                    // -128 ~ 127
        short   s = 32767;
        int     i = 2_000_000_000;          // 下划线分隔，编译器忽略，便于读
        long    l = 9_000_000_000L;         // 注意后缀 L
        float   f = 3.14f;                  // 注意后缀 f
        double  d = 3.141592653589;
        char    c = 'A';                    // 也可是 65 或 '\u0041'
        boolean flag = true;

        System.out.println("=== 基本类型 ===");
        System.out.println("byte  b = " + b);
        System.out.println("int   i = " + i);
        System.out.println("long  l = " + l);
        System.out.println("float f = " + f);
        System.out.println("char  c = " + c + " , (int)c = " + (int) c);
        System.out.println("boolean flag = " + flag);

        // ============ 2. 整数溢出（无声错误，最危险）============
        System.out.println("\n=== 溢出 ===");
        int max = Integer.MAX_VALUE;        // 2147483647
        System.out.println("Integer.MAX_VALUE = " + max);
        System.out.println("MAX + 1 = " + (max + 1));   // 溢出成负数！不会报错
        // 用 Math.addExact 会在溢出时抛异常，更安全
        // Math.addExact(max, 1);  // 抛 ArithmeticException

        // ============ 3. 自动类型提升（高频考点）============
        System.out.println("\n=== 类型提升 ===");
        byte x = 10, y = 20;
        // byte z = x + y;   // ❌ 编译错误: x+y 是 int
        int  z = x + y;      // ✅
        byte w = (byte) (x + y);  // ✅ 强转，但可能丢精度
        System.out.println("byte 相加结果(int): " + z);

        // ============ 4. char 是整数 ============
        System.out.println("\n=== char 运算 ===");
        char ch = 'A';
        System.out.println("ch + 1 = " + (ch + 1));       // 66  (提升为 int)
        System.out.println("(char)(ch+1) = " + (char) (ch + 1));  // B
        // 经典：小写转大写
        char lower = 'g';
        char upper = (char) (lower - 32);   // 'G'
        System.out.println("小写 g -> 大写 " + upper);

        // ============ 5. 浮点精度问题（必踩坑）============
        System.out.println("\n=== 浮点精度 ===");
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));   // 0.30000000000000004
        System.out.println("0.1 + 0.2 == 0.3 ? " + (0.1 + 0.2 == 0.3)); // false!
        // 钱要用 BigDecimal，不能用 double

        // ============ 6. 成员变量默认值 vs 局部变量 ============
        System.out.println("\n=== 默认值（成员变量）===");
        System.out.println("defaultInt  = " + defaultInt);
        System.out.println("defaultBool = " + defaultBool);
        System.out.println("defaultStr  = " + defaultStr);

        // int local;  // 局部变量不初始化
        // System.out.println(local);  // ❌ 编译错误: 可能尚未初始化

        // ============ 7. 引用类型 String（不可变）============
        System.out.println("\n=== String ===");
        String s1 = "hello";
        String s2 = s1;          // s2 和 s1 指向同一对象
        s1 = s1 + " world";      // s1 指向新对象，s2 不变
        System.out.println("s1 = " + s1);   // hello world
        System.out.println("s2 = " + s2);   // hello   (不可变性的体现)

        // 字符串拼接：+ 左右有 String 时为拼接，否则为算术加
        System.out.println("1 + 2 = " + 1 + 2);        // "12"  (从左到右，先 "1+2=1" 再 "1+2=12")
        System.out.println("1 + 2 = " + (1 + 2));      // "3"
    }
}
