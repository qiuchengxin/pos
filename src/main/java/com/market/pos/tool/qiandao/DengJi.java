package com.market.pos.tool.qiandao;public class DengJi {    public  static String dengji;    //等级区间划分    public static String dengJi(int grade) {       //斗之气小境界        if (grade < 0){            dengji = "弟中弟";        }else if (grade > 0 && grade <= 100){            dengji ="斗之气一段!";        }else if (grade > 100 && grade <= 200){            dengji = "斗之气二段!";        }else if (grade > 200 && grade <= 300 ){            dengji = "斗之气三段!";        }else if (grade > 300 && grade <= 400 ){            dengji = "斗之气四段!";        }else if (grade > 400 && grade <= 500 ){            dengji = "斗之气五段!";        }else if (grade > 500 && grade <= 600 ){            dengji = "斗之气六段!";        }else if (grade > 600 && grade <= 700 ){            dengji = "斗之气七段!";        }else if (grade > 700 && grade <= 800 ){            dengji = "斗之气八段!";        }else if (grade > 800 && grade <= 900 ){            dengji = "斗之气九段!";        }else if (grade > 900 && grade <= 2000){            dengji = "斗者!";        }else if (grade > 2000 && grade <= 3000){            dengji = "斗师!";        }else if (grade > 3000 && grade <= 4000){            dengji = "大斗师!";        }else if (grade > 4000 && grade <= 5000){            dengji = "斗灵!";        }else if (grade > 5000 && grade <= 6000 ){            dengji = "斗皇!";        }else if (grade > 6000 && grade <= 7000 ){            dengji = "斗宗!";        }else if (grade > 7000 && grade <= 8000 ){            dengji = "斗尊!";        }else if (grade > 8000 && grade <= 9000 ){            dengji = "斗尊巅峰!";        }else if (grade > 9000 && grade <= 10000 ){            dengji = "斗圣!";        }else if (grade > 10000 && grade <= 15000){            dengji = "半步斗帝!";        }else if (grade > 15000){            dengji = "斗帝！";        }        return dengji;    }}