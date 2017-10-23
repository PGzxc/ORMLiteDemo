package com.example.ormlitedemo2;

import java.sql.SQLException;
import java.util.List;
import com.example.ormlitedemo2.bean.Article;
import com.example.ormlitedemo2.bean.Student;
import com.example.ormlitedemo2.bean.User;
import com.example.ormlitedemo2.db.ArticleDao;
import com.example.ormlitedemo2.db.DatabaseHelper;
import com.example.ormlitedemo2.db.UserDao;
import com.example.ormlitedemo2.utils.LogUitls;
import com.j256.ormlite.dao.Dao;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAddArticle();
        testGetArticleById();
        testGetArticleWithUser();
        testListArticlesByUserId();
        testGetUserById();
        try {
            testAddStudent();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void testAddArticle() {
        User u = new User();
        u.setName("张三");
        new UserDao(MainActivity.this).add(u);
        Article article = new Article();
        article.setTitle("ORMLite的使用");
        article.setUser(u);
        new ArticleDao(MainActivity.this).add(article);

    }

    public void testGetArticleById() {
        Article article = new ArticleDao(MainActivity.this).get(1);
        LogUitls.e(article.getUser() + " , " + article.getTitle());
    }

    public void testGetArticleWithUser() {
        Article article = new ArticleDao(MainActivity.this).getArticleWithUser(1);
        LogUitls.e(article.getUser() + " , " + article.getTitle());
    }

    public void testListArticlesByUserId() {
        List<Article> articles = new ArticleDao(MainActivity.this).listByUserId(1);
        LogUitls.e(articles.toString());
    }

    public void testGetUserById() {
        User user = new UserDao(MainActivity.this).get(1);
        LogUitls.e(user.getName());
        if (user.getArticles() != null)
            for (Article article : user.getArticles()) {
                LogUitls.e(article.toString());
            }
    }

    public void testAddStudent() throws SQLException {
        Dao dao = DatabaseHelper.getHelper(MainActivity.this).getDao(Student.class);
        Student student = new Student();
        student.setDao(dao);
        student.setName("张三");
        student.create();
    }
}
