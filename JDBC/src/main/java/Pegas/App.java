package Pegas;

import Pegas.servlet.ContentServlet;
import Pegas.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        String sql = """
                drop schema game;
                """;
        String sql1 = """
                create table info(
                id serial primary key,
                data varchar(256)
                );
                """;
        String sql2 = """
                insert into info (data) values('text1'),('text2'),('text3'),('text4'),('text5');
                """;
        String sql3 = """
                delete from info;
                """;
        String sql4 = """
                select * from test.company;
                """;
        try(Connection connection = ConnectionManager.get();
        var statement = connection.createStatement()) {
//            System.out.println(statement.execute(sql));
//            System.out.println(statement.execute(sql1));
//            System.out.println(statement.executeUpdate(sql2));
//            System.out.println(statement.executeUpdate(sql3));
            var set = statement.executeQuery(sql4);
            while (set.next()){
//                System.out.println(set.getLong("id"));
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getWorkersByCompany(1L));

    }
    public static List<Long> getWorkersByCompany(Long ask){
        List<Long> arr = new ArrayList<>();
        String sql = """
                select w.id from test.worker w
                join test.jobmaker j on w.company_id = j.id
                where j.id = ?
                """;

        try(Connection connection = ConnectionManager.get();
            var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, ask);
            var res = statement.executeQuery();
            while (res.next()){
                arr.add(res.getLong("id"));
            };
            return arr;
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
