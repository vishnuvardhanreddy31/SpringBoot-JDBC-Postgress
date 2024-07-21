package com.udemy.SpringJDBCEx.repo;


import com.udemy.SpringJDBCEx.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student student) {
        String sql="insert into student (rollno,name,marks) values (?,?,?)";
        int update = jdbc.update(sql, student.getRollNo(), student.getName(), student.getMarks());
        System.out.println(update +" Rows Effected");

    }

    public List<Student> findall() {

        String sql="select * from student";
        List<Student> students = new ArrayList<>();
        List<Student> query = jdbc.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setRollNo(rs.getInt("rollno"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));
            return student;
        });
        return query;
    }
}
