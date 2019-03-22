package com.market.pos.tool.jpgTable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 工具类，生成图片
 */
public class GraphicsGeneration {

    public static void graphicsGeneration(String[][] cellsValue,String title,String remark,String jpgName) throws Exception {
        // 实际数据行数+标题+备注
        //行
        int totalrow = 7;
        //列
        int totalcol = 5;
        int imageWidth = 1024;
        int imageHeight = totalrow * 40 + 20;
        int rowheight = 40;
        int startHeight = 10;
        int startWidth = 10;
        int colwidth = ((imageWidth - 20) / totalcol);

        BufferedImage image = new BufferedImage(imageWidth, imageHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        graphics.setColor(new Color(255, 255, 255));

        // 画横线
        for (int j = 0; j < totalrow - 1; j++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight + (j + 1) * rowheight,
                    imageWidth - 5, startHeight + (j + 1) * rowheight);
        }
        // 末行
        graphics.setColor(Color.black);
        graphics.drawLine(startWidth, imageHeight - 90, imageWidth - 5,
                imageHeight - 90);
        // 画竖线
        for (int k = 0; k < totalcol; k++) {
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth + k * colwidth, startHeight
                    + rowheight, startWidth + k * colwidth, imageHeight - 50);
        }
        // 末列
        graphics.setColor(Color.black);
        graphics.drawLine(imageWidth - 5, startHeight + rowheight,
                imageWidth - 5, imageHeight - 50);
        // 设置字体
        Font font = new Font("华文楷体", Font.BOLD, 20);
        graphics.setFont(font);
        // 写标题
//        String title = "25人难度挑战·荒血路 2019年03月19日19点30分";
        graphics.drawString(title, 292 + startWidth, startHeight
                + rowheight - 10);
        font = new Font("华文楷体", Font.PLAIN, 18);
        graphics.setFont(font);
//        // 写入表头
//        String[] headCells = { "编号", "名称", "年龄", "性别", "体重" };
//        for (int m = 0; m < headCells.length; m++) {
//            graphics.drawString(headCells[m].toString(), startWidth + colwidth
//                    * m + 5, startHeight + rowheight * 2 - 10);
//        }
        // 设置字体
        font = new Font("华文细黑", Font.PLAIN, 18);
        graphics.setFont(font);
        graphics.setColor(Color.DARK_GRAY);
//        String[][] cellsValue = { { "101", "xiaozhang", "13", "M", "55" },
//                { "102", "xiaowang", "14", "F", "53" },
//                { "102", "xiaowang", "14", "F", "53" },
//                { "102", "xiaowang", "14", "F", "53" },
//                { "102", "xiaowang", "14", "F", "53" }};
        // 写入内容
        for (int n = 0; n < cellsValue.length; n++) {
            String[] arr = cellsValue[n];
            for (int l = 0; l < arr.length; l++) {
                graphics.drawString(cellsValue[n][l].toString(), startWidth
                        + colwidth * l + 4, startHeight + rowheight * (n + 2)
                        - 10);
            }
        }
        font = new Font("宋体", Font.PLAIN, 18);
        graphics.setFont(font);
        graphics.setColor(Color.RED);
        // 写备注
//        String remark = "备注：备注写在这里。";
        graphics.drawString(remark, startWidth, imageHeight - 30);
        //createImage("D:\\1.jpg");
        ImageIO.write(image, "png", new File("C:\\Users/Administrator/Desktop/mxPos/CQP/酷Q Pro/data/image/team_table/" + jpgName + ".jpg"));
    }
}
