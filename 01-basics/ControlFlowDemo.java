/**
 * 模块 01 - 基础回顾
 * 第 3 节：流程控制演示
 *
 * 运行: javac ControlFlowDemo.java && java ControlFlowDemo
 */
public class ControlFlowDemo {

    public static void main(String[] args) {

        // ============ 1. if-else（最常用，但要注意 else 悬挂）============
        System.out.println("=== 1. if-else ===");
        int score = 75;
        String grade;
        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 60) {
            grade = "C";
        } else {
            grade = "D";
        }
        System.out.println("score=" + score + ", grade=" + grade);

        // else 悬挂问题：else 总和最近的未配对 if 配对
        int a = 1, b = 2;
        if (a > 0)
            if (b > 0)
                System.out.println("a,b 都正");
        else  // ⚠️ 这个 else 实际配的是内层 if，不是外层
            System.out.println("a <= 0");   // 不打印
        // 教训: 永远用花括号 {} !

        // ============ 2. switch 经典语法（含穿透陷阱）============
        System.out.println("\n=== 2. switch 经典 ===");
        int day = 2;
        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("工作日");   // day=2 时穿透到这里
                break;                          // ⚠️ 漏写 break 就会继续执行后面 case
            case 6:
            case 7:
                System.out.println("周末");
                break;
            default:
                System.out.println("非法");
        }

        // ⚠️ switch 不支持的类型
        // long l = 1L;
        // switch (l) { }      // ❌ 编译错误
        // double d = 1.0;
        // switch (d) { }      // ❌ 编译错误

        // ============ 3. switch 新语法（Java 14+，箭头形式不穿透）============
        System.out.println("\n=== 3. switch 新语法 ===");
        String dayName = switch (day) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            case 4, 5 -> "周四周五合并";   // 多值合并
            case 6, 7 -> "周末";
            default -> "未知";
        };   // ⚠️ switch 作为表达式时末尾要分号
        System.out.println("day=" + day + " -> " + dayName);

        // switch 表达式 + yield（块语句中返回值）
        int code = 200;
        String msg = switch (code / 100) {
            case 2 -> "成功";
            case 3 -> "重定向";
            case 4 -> {
                System.out.println("  (客户端错误)");
                yield "客户端错误";     // 块中用 yield 返回
            }
            case 5 -> "服务端错误";
            default -> "未知";
        };
        System.out.println("HTTP " + code + " -> " + msg);

        // ============ 4. for 循环（普通 + 增强）============
        System.out.println("\n=== 4. for 循环 ===");
        int[] nums = {10, 20, 30};
        int sum = 0;
        for (int n : nums) {    // 增强 for（foreach），不能拿索引
            sum += n;
        }
        System.out.println("总和: " + sum);

        // ============ 5. continue 在 for vs while 的差异 ============
        System.out.println("\n=== 5. continue 差异 ===");
        // for 中 continue 跳到 i++（更新语句），不会死循环
        for (int i = 0; i < 5; i++) {
            if (i == 2) continue;
            System.out.print(i + " ");   // 0 1 3 4
        }
        System.out.println();

        // ⚠️ while 中 continue 跳到条件判断，如果更新写在 continue 之后会死循环
        // int w = 0;
        // while (w < 5) {
        //     if (w == 2) continue;   // ❌ 死循环！w 永远是 2，i++ 永远不执行
        //     System.out.println(w);
        //     w++;
        // }

        // 正确写法
        int w = 0;
        while (w < 5) {
            if (w == 2) { w++; continue; }   // ✅ continue 之前先更新
            System.out.print(w + " ");        // 0 1 3 4
            w++;
        }
        System.out.println();

        // ============ 6. 带标签的 break（跳出多层循环）============
        System.out.println("\n=== 6. 标签 break ===");
        outer:   // 标签名 + 冒号
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    System.out.println("  跳出全部 (i=" + i + ", j=" + j + ")");
                    break outer;   // 跳出 outer 标记的循环
                }
                System.out.println("  i=" + i + ", j=" + j);
            }
        }

        // ============ 7. 经典实战：查找二维数组中的目标值 ============
        System.out.println("\n=== 7. 实战 ===");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int target = 5;
        boolean found = false;
        int foundRow = -1, foundCol = -1;
        search:
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    found = true;
                    foundRow = i;
                    foundCol = j;
                    break search;
                }
            }
        }
        System.out.println("找 " + target + ": " + (found ?
                "在 (" + foundRow + "," + foundCol + ")" : "没找到"));

        // ============ 8. do-while（少用，但保证至少执行一次）============
        System.out.println("\n=== 8. do-while ===");
        int k = 0;
        do {
            System.out.println("至少执行一次, k=" + k);
            k++;
        } while (k < 0);   // 条件为 false 也已经执行过一次了
    }
}
