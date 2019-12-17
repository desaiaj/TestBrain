package ca.testbrain.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.testbrain.beans.Category;
import ca.testbrain.beans.Question;
import ca.testbrain.beans.clsUser;

@Repository
public class DatabaseAccess {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void addUser(clsUser user, int type) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO user(Name,email,membership,encrytedPassword,ENABLED) VALUES (:name,:email,'Basic',:Pass,1)";
		parameters.addValue("name", user.getName());
		parameters.addValue("email", user.getEmail());
		parameters.addValue("Pass", passwordEncoder.encode(user.getEncrytedPassword()));
		jdbc.update(query, parameters);

		user = findUserAccount(user.getEmail());
		query = "INSERT INTO user_role(userId,roleId) VALUES (:id,:role)";
		if (type == 3) {
			parameters = new MapSqlParameterSource();
			parameters.addValue("id", user.getUserId());
			parameters.addValue("role", 3);
			jdbc.update(query, parameters);
		} else if (type == 2) {
			parameters = new MapSqlParameterSource();
			parameters.addValue("id", user.getUserId());
			parameters.addValue("role", 2);
			jdbc.update(query, parameters);
		} else if (type == 1) {
			parameters = new MapSqlParameterSource();
			parameters.addValue("id", user.getUserId());
			parameters.addValue("role", 1);
			jdbc.update(query, parameters);
		}
	}

	public int addQuestion(Question que) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO question(catId,que,opt1,opt2,opt3,opt4,ans) "
				+ "VALUES (:cat,:que,:opt1,:opt2,:opt3,:opt4,:ans)";
		parameters.addValue("que", que.getQue());
		parameters.addValue("cat", que.getCatId());
		parameters.addValue("ans", que.getAns());
		parameters.addValue("opt1", que.getOpt1());
		parameters.addValue("opt2", que.getOpt2());
		parameters.addValue("opt3", que.getOpt3());
		parameters.addValue("opt4", que.getOpt4());
		return jdbc.update(query, parameters);
	}

	public int addCategory(Category cat) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO category(category,ENABLED) VALUES (:Cat,:en)";
		parameters.addValue("Cat", cat.getCategory());
		parameters.addValue("en", cat.getEnabled());
		return jdbc.update(query, parameters);
	}

	public clsUser findUserAccount(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM user WHERE email=:email";
		parameters.addValue("email", username);
		ArrayList<clsUser> user = (ArrayList<clsUser>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<clsUser>(clsUser.class));
		if (user.size() > 0) {
			// System.out.println();
			return user.get(0);
		} else {
			return null;
		}
	}

	public List<String> getRolesById(Long id) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName FROM user_role ,sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId and userId=:userId";
		parameters.addValue("userId", id);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

		for (Map<String, Object> row : rows) {
			roles.add((String) row.get("roleName"));
		}
		return roles;
	}

	public ArrayList<Question> getQuestionsByQue(String Que) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "Select * from question where que like :que";
		param.addValue("que", '%' + Que + '%');
		return (ArrayList<Question>) jdbc.query(q, param, new BeanPropertyRowMapper<Question>(Question.class));
	}

	public ArrayList<Question> getQuestionsByCat(int Id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "Select * from question where catId=:id";
		param.addValue("id", Id);
		return (ArrayList<Question>) jdbc.query(q, param, new BeanPropertyRowMapper<Question>(Question.class));
	}

	public Question getQuestionsById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "SELECT queId,catId,que,opt1,opt2,opt3,opt4,ans FROM question where queId=:id";
		param.addValue("id", id);
		return jdbc.query(q, param, new BeanPropertyRowMapper<Question>(Question.class)).get(0);
	}

	public Category getCategoryById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "SELECT catId, category,ENABLED as enabled FROM category where catId=:id";
		param.addValue("id", id);
		return jdbc.query(q, param, new BeanPropertyRowMapper<Category>(Category.class)).get(0);
	}

	public ArrayList<Category> getCategoryByCat(String cat) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "SELECT catId, category,ENABLED as enabled FROM category where category like :cat";
		param.addValue("cat", '%' + cat + '%');
		return (ArrayList<Category>) jdbc.query(q, param, new BeanPropertyRowMapper<Category>(Category.class));
	}

	public ArrayList<Category> getCategories() {
		String Query = "SELECT catId,category,ENABLED as enabled FROM category";
		return (ArrayList<Category>) jdbc.query(Query, new BeanPropertyRowMapper<Category>(Category.class));
	}

	public ArrayList<Question> getAllQuestion() {
		String Query = "SELECT queId,catId, que,opt1, opt2, opt3, opt4, ans FROM question";
		return (ArrayList<Question>) jdbc.query(Query, new BeanPropertyRowMapper<Question>(Question.class));
	}

	public int DeleteQuesById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "delete from question where queId=:id";
		param.addValue("id", id);
		return jdbc.update(q, param);
	}

	public int DeleteCatById(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "delete from question where catId=:id";
		param.addValue("id", id);
		jdbc.update(q, param);
		q = "delete from category where catId=:id";
		return jdbc.update(q, param);
	}

	public int UpdateCat(Category cat) {
		DeleteCatById(cat.getCatId());
		return addCategory(cat);
	}

	public int UpdateQuestion(Question que) {
		DeleteQuesById(que.getQueId());
		return addQuestion(que);
	}

	public ArrayList<clsUser> getAllUsers() {
		String query = "SELECT * FROM user";
		return (ArrayList<clsUser>) jdbc.query(query, new BeanPropertyRowMapper<clsUser>(clsUser.class));
	}

	public void UpgradeMembership(String uName, String m) {
		String q = "update user set membership=:m where email=:email";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("m", m);
		param.addValue("email", uName);
		jdbc.update(q, param);
	}

	public int deleteAllQue() {
		String q = "TRUNCATE question";
		return jdbc.update(q, new HashMap());
	}

	public int DeleteAllCat() {
		String q = "TRUNCATE category";
		jdbc.update(q, new HashMap());
		deleteAllQue();
		return 0;
	}

	public void deleteAllUsers() {
		String q = "TRUNCATE user_role";
		jdbc.update(q, new HashMap());

		q = "TRUNCATE user";
		jdbc.update(q, new HashMap());
	}

	public void deleteUserById(String id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		String q = "delete from user_role where userId=:id";
		param.addValue("id", id);
		jdbc.update(q, param);
		q = "delete from user where userId=:id";
		jdbc.update(q, param);
	}

	public ArrayList<clsUser> getUserBySearch(String email, String name) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = null;
		if (email.equalsIgnoreCase("null") && name.equalsIgnoreCase("null")) {
			return getAllUsers();
		}
		if (!email.equalsIgnoreCase("null") && !name.equalsIgnoreCase("null")) {
			q = "SELECT * FROM user WHERE Name like :name or email like :email";
			parameters.addValue("name", name);
			parameters.addValue("email", email);
			return (ArrayList<clsUser>) jdbc.query(q, parameters, new BeanPropertyRowMapper<clsUser>(clsUser.class));
		}
		if (!email.equalsIgnoreCase("null")) {
			q = "SELECT * FROM user WHERE email like :email";
			parameters.addValue("email", email);
		}
		if (!name.equalsIgnoreCase("null")) {
			q = "SELECT * FROM user WHERE Name like :name";
			parameters.addValue("name", name);
		}
		return (ArrayList<clsUser>) jdbc.query(q, parameters, new BeanPropertyRowMapper<clsUser>(clsUser.class));
	}
}
