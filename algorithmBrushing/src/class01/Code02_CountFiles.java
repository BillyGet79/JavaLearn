package class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 题目2
 * 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回
 * 隐藏文件也算，但是文件夹不算
 */
public class Code02_CountFiles {

    //图的遍历问题，但是需要注意读文件的操作，这个必须得会
    //这里使用宽度优先遍历的方式解决（这样能避免递归方法）
    //定义一个队列Q来存储遍历到的文件夹（第一个进队列的就是我们要遍历的初始文件夹）
    //如果Q内有文件夹，那么就去遍历它下面的文件/文件夹
    //如果遇到文件，则文件数量+1
    //如果遇到文件夹，则让其入队即可
    //最后直到队列当中没有文件夹，算法统计结束
    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);   //导入文件夹
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> queue = new LinkedList<>();
        queue.add(root);
        int files = 0;
        while (!queue.isEmpty()) {
            File folder = queue.poll();
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isFile()) {
                    files++;
                }
                if (file.isDirectory()) {
                    queue.add(file);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {

    }
}
