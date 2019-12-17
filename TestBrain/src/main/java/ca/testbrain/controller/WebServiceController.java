package ca.testbrain.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.testbrain.beans.Category;
import ca.testbrain.beans.Question;
import ca.testbrain.beans.clsUser;
import ca.testbrain.database.DatabaseAccess;

@RestController
public class WebServiceController {
	@Autowired
	private DatabaseAccess da;

	@PostMapping("/TestBrain/Register/{name}/{email}/{mem}/{password}/{type}")
	public void AddUser(@PathVariable String name, @PathVariable String mem, @PathVariable String email,
			@PathVariable String password, @PathVariable int type) {

		System.out.println("sd" + name);
		da.addUser(new clsUser(name, email, mem, password), type);
	}

	@GetMapping("/TestBrain/Categories")
	public ArrayList<Category> categories() {
		return da.getCategories();

	}

	@GetMapping("/TestBrain/Categories/{id}")
	public Category GetCatbyID(@PathVariable int id) {
		return da.getCategoryById(id);

	}

	@GetMapping("/TestBrain/SearchCategories/{cat}")
	public ArrayList<Category> SearchCat(@PathVariable String cat) {
		return da.getCategoryByCat(cat);

	}

	@PostMapping("/TestBrain/Categories/{cat}/{enabled}")
	public void InsertCat(@PathVariable String cat, @PathVariable int enabled) {
		da.addCategory(new Category(cat, enabled));
//		System.out.println(id+"asd");
	}

	@GetMapping("/TestBrain/Question")
	public ArrayList<Question> getAllQuest() {
		return da.getAllQuestion();

	}

	@GetMapping("/TestBrain/SearchQuestion/{que}")
	public ArrayList<Question> SearchQuest(@PathVariable String que) {
		return da.getQuestionsByQue(que);

	}

	@GetMapping("/TestBrain/Question/{id}")
	public Question GetQuebyID(@PathVariable int id) {
		return da.getQuestionsById(id);

	}

	@PostMapping("/TestBrain/Question/{Que}/{Opt1}/{Opt2}/{Opt3}/{Opt4}/{CatId}/{ans}")
	public void InsertQuestion(@PathVariable String Que, @PathVariable String Opt1, @PathVariable String Opt2,
			@PathVariable String Opt3, @PathVariable String Opt4, @PathVariable int CatId, @PathVariable String ans) {
		System.out.println(Que);
		da.addQuestion(new Question(0, Que, Opt1, Opt2, Opt3, Opt4, CatId, ans));
//		System.out.println(id+"asd");
	}

	@DeleteMapping("/TestBrain/Category/{id}")
	public int DeleteCat(@PathVariable int id) {
		return da.DeleteCatById(id);
	}

	@DeleteMapping("/TestBrain/Category")
	public int DeleteAllCat() {
		return da.DeleteAllCat();
	}

	@DeleteMapping("/TestBrain/Question/{id}")
	public int DeleteQue(@PathVariable int id) {
		return da.DeleteQuesById(id);
	}

	@GetMapping("/TestBrain/Quiz/{id}")
	public ArrayList<Question> getQuiz(@PathVariable int id) {
		return da.getQuestionsByCat(id);

	}

	@PutMapping("/TestBrain/Categories/{id}/{cat}/{enabled}")
	public String EditCat(@PathVariable int id, @PathVariable String cat, @PathVariable int enabled) {
		da.UpdateCat(new Category(id, cat, enabled));
		return "Updated Successfully";
	}

	@PutMapping("/TestBrain/Question/{id}/{Que}/{Opt1}/{Opt2}/{Opt3}/{Opt4}/{CatId}/{ans}")
	public void UpdateQuestion(@PathVariable int id, @PathVariable String Que, @PathVariable String Opt1,
			@PathVariable String Opt2, @PathVariable String Opt3, @PathVariable String Opt4, @PathVariable int CatId,
			@PathVariable String ans) {
		da.UpdateQuestion(new Question(id, Que, Opt1, Opt2, Opt3, Opt4, CatId, ans));
//		System.out.println(id+"asd");
	}

	@PostMapping("/TestBrain/Membership/{uName}/{m}")
	public void UpgradeMem(@PathVariable String m, @PathVariable String uName) {
		da.UpgradeMembership(uName, m);
	}

	@GetMapping("/TestBrain/Users")
	public ArrayList<clsUser> getUserList() {
		return da.getAllUsers();
	}

	@GetMapping("/TestBrain/SearchUsers/{name}/{email}")
	public ArrayList<clsUser> SearchUser(@PathVariable String email, @PathVariable String name) {
		return da.getUserBySearch(email, name);
	}

	@GetMapping("/TestBrain/Users/{id}")
	public clsUser getUserById(@PathVariable String id) {
		return da.findUserAccount(id);
	}

	@DeleteMapping("/TestBrain/Users")
	public void deleteAllUsers() {
		da.deleteAllUsers();
	}

	@DeleteMapping("/TestBrain/Users/{id}")
	public void DeleteUserById(@PathVariable String id) {
		da.deleteUserById(id);
	}
}
