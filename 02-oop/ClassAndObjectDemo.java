/**
 * 模块 02 - 面向对象
 * 第 1 节：类与对象、构造方法、this
 *
 * 运行: javac ClassAndObjectDemo.java && java ClassAndObjectDemo
 */

// ============ 定义一个学生类 ============
class Student {
    // 成员变量（字段）—— 有默认值
    String name;        // 默认 null
    int age;            // 默认 0
    String school;      // 默认 null

    // 静态变量（类变量）—— 所有对象共享
    static String country = "中国";

    // ============ 构造方法重载 ============

    // 1. 无参构造（如果没写任何构造方法，编译器会自动加这个；
    //    但只要写了任意一个构造方法，无参构造就不会自动生成了）
    public Student() {
        // 用 this(...) 调用另一个构造方法，必须在第一行
        this("未知", 0, "未知学校");
        System.out.println("  (调用了无参构造)");
    }

    // 2. 两个参数的构造
    public Student(String name, int age) {
        // 复用下面的全参构造
        this(name, age, "默认学校");
    }

    // 3. 全参构造
    public Student(String name, int age, String school) {
        // this 指向"当前正在创建的对象"
        // this.name 是字段，name 是参数（当字段名与参数名相同时必须用 this 区分）
        this.name = name;
        this.age = age;
        this.school = school;
    }

    // 成员方法
    public void introduce() {
        // 方法内访问字段时，this 可省略（编译器自动加）
        // 但当参数/局部变量与字段同名时，必须用 this.name
        System.out.println("我叫" + name + "，" + age + "岁，就读于" + school + "，来自" + country);
    }

    // this 作为返回值（链式调用）
    public Student setName(String name) {
        this.name = name;
        return this;   // 返回当前对象，可继续调用
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }
}

public class ClassAndObjectDemo {
    public static void main(String[] args) {

        System.out.println("=== 1. 创建对象 ===");

        // 用 new 调用构造方法，在堆上创建对象
        Student s1 = new Student("张三", 20, "清华大学");
        Student s2 = new Student("李四", 21);
        Student s3 = new Student();   // 无参构造

        s1.introduce();   // 我叫张三，20岁，就读于清华大学，来自中国
        s2.introduce();   // 我叫李四，21岁，就读于默认学校，来自中国
        s3.introduce();   // 我叫未知，0岁，就读于未知学校，来自中国

        System.out.println("\n=== 2. 静态变量共享 ===");
        // 静态变量 country 所有对象共享，改一个全变（一般用类名访问）
        Student.country = "中华";
        s1.introduce();   // 来自中华

        // ⚠️ 不推荐用对象引用访问静态变量（编译能过但容易误导）
        // s1.country = "XX";  // 不推荐
        // Student.country = "XX";  // 推荐

        System.out.println("\n=== 3. this 实现链式调用 ===");
        Student s4 = new Student()
                .setName("王五")
                .setAge(22);
        s4.introduce();

        System.out.println("\n=== 4. 引用赋值 vs 新对象 ===");
        Student a = new Student("A", 18, "X");
        Student b = a;          // b 和 a 指向同一个对象（不是复制）
        b.age = 99;
        System.out.println("a.age = " + a.age);   // 99（因为 a、b 是同一个对象）

        Student c = new Student("A", 18, "X");   // 新对象，内容和 a 一样但是独立的
        c.age = 100;
        System.out.println("a.age = " + a.age);   // 99（c 是独立对象）

        System.out.println("\n=== 5. null 引用与空指针异常 ===");
        Student s = null;   // s 不指向任何对象
        try {
            s.introduce();   // ❌ NullPointerException
        } catch (NullPointerException e) {
            System.out.println("捕获到空指针异常: 不能调用 null 对象的方法");
        }

        System.out.println("\n=== 6. 内存模型说明 ===");
        Student stu = new Student("赵六", 25, "北大");
        /*
         栈 (Stack)                    堆 (Heap)
         ┌─────────────┐               ┌──────────────────────┐
         │ stu = 0x100 │─────指向──────▶│  Student 对象 0x100  │
         │             │               │  name = "赵六"       │
         │             │               │  age  = 25           │
         │             │               │  school = "北大"     │
         └─────────────┘               └──────────────────────┘
                                         方法区 (Method Area)
                                         ┌──────────────────────┐
                                         │  country = "中华"    │ ← 静态变量
                                         │  Student 类信息      │
                                         └──────────────────────┘
         */
        System.out.println("stu 在栈上存的是引用地址，对象本身在堆上");
        System.out.println("静态变量 country 在方法区，所有对象共享");
    }
}
