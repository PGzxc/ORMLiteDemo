package com.example.ormlitedemo2;

import java.sql.SQLException;
import java.util.List;
import android.test.InstrumentationTestCase;
import com.example.ormlitedemo2.bean.Article;
import com.example.ormlitedemo2.bean.Student;
import com.example.ormlitedemo2.bean.User;
import com.example.ormlitedemo2.db.ArticleDao;
import com.example.ormlitedemo2.db.DatabaseHelper;
import com.example.ormlitedemo2.db.UserDao;
import com.example.ormlitedemo2.utils.LogUitls;
import com.j256.ormlite.dao.Dao;
import static android.support.test.InstrumentationRegistry.getContext;


public class OrmLiteDbTest extends InstrumentationTestCase
{
	public void testAddArticle()
	{
		User u = new User();
		u.setName("张三");
		new UserDao(getContext()).add(u);
		Article article = new Article();
		article.setTitle("ORMLite的使用");
		article.setUser(u);
		new ArticleDao(getContext()).add(article);

	}

	public void testGetArticleById()
	{
		Article article = new ArticleDao(getContext()).get(1);
		LogUitls.e(article.getUser() + " , " + article.getTitle());
	}

	public void testGetArticleWithUser()
	{

		Article article = new ArticleDao(getContext()).getArticleWithUser(1);
		LogUitls.e(article.getUser() + " , " + article.getTitle());
	}

	public void testListArticlesByUserId()
	{

		List<Article> articles = new ArticleDao(getContext()).listByUserId(1);
		LogUitls.e(articles.toString());
	}

	public void testGetUserById()
	{
		User user = new UserDao(getContext()).get(1);
		LogUitls.e(user.getName());
		if (user.getArticles() != null)
			for (Article article : user.getArticles())
			{
				LogUitls.e(article.toString());
			}
	}

	public void testAddStudent() throws SQLException
	{
		Dao dao = DatabaseHelper.getHelper(getContext()).getDao(Student.class);
		Student student = new Student();
		student.setDao(dao);
		student.setName("张三");
		student.create();
	}



}
