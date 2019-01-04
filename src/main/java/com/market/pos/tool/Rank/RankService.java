package com.market.pos.tool.Rank;

public class RankService {
    public static String first_username;
    public static String second_username;
    public static String third_username;
    public static int first_grade;
    public static int second_grade;
    public static int third_grade;

    public static int myRowNo;

    /**
     * 查询排行前三的信息
     */
    public static void selectAllRank(String groupid){
        String sql = "select username, grade from users order by grade desc LIMIT 3";
        JdbcRank.searchRank(sql,groupid);
        first_username = JdbcRank.first_username;
        first_grade = JdbcRank.first_grade;
        second_username = JdbcRank.second_username;
        second_grade = JdbcRank.second_grade;
        third_username = JdbcRank.third_username;
        third_grade = JdbcRank.third_grade;
    }

    /**
     * 查询指定userid用户的排名
     * @param userid
     */
    public static void selectRankByUserid(String userid,String groupid){
        String sql = "select users.rowNo from " +
                "(select userid, (@rowNum := @rowNum + 1) as rowNo from " +
                "users , (select (@rowNum := 0)) b order by grade desc) " +
                "users where users.userid = " + "'" + userid + "'";
        JdbcRank.searchRankByUserid(sql,groupid);
        myRowNo = JdbcRank.myRankNum;
    }

    /**
     * 查询倒数前三信息
     * @param groupid
     */
    public static void selectMin(String groupid){
        String sql = "select username, grade from users order by grade LIMIT 3";
        JdbcRank.searchRank(sql,groupid);
        first_username = JdbcRank.first_username;
        first_grade = JdbcRank.first_grade;
        second_username = JdbcRank.second_username;
        second_grade = JdbcRank.second_grade;
        third_username = JdbcRank.third_username;
        third_grade = JdbcRank.third_grade;
    }
}
