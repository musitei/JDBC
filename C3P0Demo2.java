package cn.itcast.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0的演示
 */
public class C3P0Demo2 {
    public static void main(String[] args) throws SQLException {

        //1.创建数据库连接池对象，（这里没有传参，会使用xml里面默认的配置）
        DataSource ds=new ComboPooledDataSource();

        //1.1获取DataSource,使用指定名称配置
        //DataSource ds=new ComboPooledDataSource("otherc3p0");

        //2.获取连接对象,xml里面设置的最大连接对象为10个，这里如果是11个人同时连接？
        //会报错，此时我们应该怎么提前设置归还到连接池里的操作
        for (int i = 1; i <=11; i++) {
            Connection conn = ds.getConnection();
            //3.打印
            System.out.println(i+":"+conn);
            if (i==5){
                conn.close();//归还到连接池中
            }
        }

    }
    //测试的时候：可以在这里用public static void testNameConfig() throws SQLException，直接载主方法调用方法名
    public void testNameConfig() throws SQLException {
        //1.1获取DataSource.使用指定名称配额
        DataSource ds=new ComboPooledDataSource("otherc3p0");
        //2.获取连接
        for (int i = 1; i <=10; i++) {
            Connection conn = ds.getConnection();
            //3.打印
            System.out.println(i+":"+conn);
        }

    }
}
