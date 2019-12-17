package ca.testbrain.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import ca.testbrain.beans.Category;
import ca.testbrain.beans.Mail;
import ca.testbrain.beans.Question;
import ca.testbrain.beans.answers;
import ca.testbrain.beans.clsUser;

@Controller
public class HomeController {
	String style = "color: #fff!important;background-color: #4285f4 !important";
	@Autowired
	EmailServiceImpl esi;

	@GetMapping("/")
	public String home() {
		return "index.html";
	}

	@GetMapping("/Member")
	public String goMember(RestTemplate restTemplate, HttpSession Session, Authentication auth) {
		ResponseEntity<clsUser> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Users/" + auth.getName(), clsUser.class);
		Session.setAttribute("mem", responseEntity.getBody().getMembership());

		return "/Member/index.html";
	}

	@GetMapping("/FeedBack")
	public String FeedBack(Model model) {
		model.addAttribute("m", new Mail());
		return "ContactUs.html";
	}

	@GetMapping("/Admin")
	public String goAdmin(Model model, RestTemplate restTemplate, HttpSession Session, Authentication auth) {
		model.addAttribute("over", style);
		model.addAttribute("cats", restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class).getBody().length);
		model.addAttribute("que", restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Question", Question[].class).getBody().length);
		ResponseEntity<clsUser> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Users/" + auth.getName(), clsUser.class);
		Session.setAttribute("name", responseEntity.getBody().getName());
		model.addAttribute("users",
				restTemplate.getForEntity("http://localhost:8080/TestBrain/Users", clsUser[].class).getBody().length);
		return "/Admin/index.html";
	}

	@PostMapping("/GenerateDummy")
	public String goGenerateDummy(RestTemplate restTemplate) {

		restTemplate.delete("http://localhost:8080/TestBrain/Category");
		restTemplate.delete("http://localhost:8080/TestBrain/Users");

		// Users
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Register/Ajay/ajaydesai157@gmail.com/ADMIN/Admin/1", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/Aman/aman123@gmail.com/PROF/P123/2",
				null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/Deep/deep123@gmail.com/PROF/P123/2",
				null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/Member1/Member1@gmail.com/Basic/123/3",
				null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/Member2/Member2@gmail.com/Pro/123/3",
				null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/Member3/Member3@gmail.com/Premium/123/3",
				null);

		// Cat
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Computer fundamental/1", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Computer Hardware/1", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Unix/1", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Linux/1", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Networking/0", null);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Categories/Database Systems/0", null);

		// Quest
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which of the following languages is more suited to a structured "
						+ "program/C/FORTRAN/BASIC/PASCAL/1/PASCAL",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/A computer assisted method for the recording and analyzing of "
						+ "existing or hypothetical systems is/Data flow/Data capture/Data processing/None/1/Data capture",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/The brain of any computer system is/ALU/Memory/CPU/Control "
						+ "unit/1/CPU",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What difference does the 5th generation computer have "
						+ "from other generation computers/Technological advancement/Scientific code/Object Oriented Programming/All of the above/1/Technological advancement",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which of the following computer language is used for "
						+ "artificial intelligence/FORTRAN/PROLOG/C/COBOL/1/PROLOG",
				null);
		restTemplate
				.postForLocation(
						"http://localhost:8080/TestBrain/Question/The tracks on a disk which can be accessed without "
								+ "repositioning the R|W heads is/Surface/Cylinder/Cluster/All of Above/1/Cylinder",
						null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which of the following is the 1's complement of 10/01/110/11/10/1/01",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/A section of code to which control is transferred when a "
						+ "processor is interrupted is known as/M/SVC/IP/MDR/1/M",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which part interprets program instructions and initiate control "
						+ "operations/Input/Storage unit/Logic unit/Control unit/1/Control unit",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/The binary system uses powers of/2/10/8/16/1/2", null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/From what location are the 1st computer instructions available"
						+ " on boot up/ROM BIOS/CPU/boot.ini/CONFIG.SYS/2/ROM BIOS",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What could cause a fixed disk error/No-CD installed/bad ram/"
						+ "slow processor/Incorrect CMOS settings/2/Incorrect CMOS settings",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Missing slot covers on a computer can cause/over heat/"
						+ "power surges/EMI/incomplete path for ESD/2/over heat",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/When installing PCI NICS you can check the IRQ availability by "
						+ "looking at/dip switches/CONFIG.SYS/jumper settings/motherboard BIOS/2/motherboard BIOS",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/With respect to a network interface card, the term 10|100 "
						+ "refers to/protocol speed/a fiber speed/megabits per seconds/minimum and maximum server speed/2/megabits per seconds",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which Motherboard form factor uses one 20 pin connector/ATX"
						+ "/AT/BABY AT/All/2/ATX",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/A hard disk is divided into tracks which are further subdivided"
						+ " into:/clusters/sectors/vectors/heads/2/sectors",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/A wrist grounding strap contains which of the following:"
						+ "/Surge protector/Resistor/OP3/OP4/2/Resistor",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which standard govern parallel communications/RS232/RS-232a/"
						+ "IEEE 1284/OP4/2/IEEE 1284",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/In laser printer technology, what happens during the conditioning "
						+ "stage/A uniform negative charge is placed on the toner/"
						+ "A uniform negative charge is placed on the photosensitive drum/OP3/OP4/2/A uniform negative charge is placed on the photosensitive drum",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which symbol will be used with grep command to match the "
						+ "pattern pat at the beginning of a line/^pat/OP2/OP3/OP4/3/^pat",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to sort the lines of data in a file in"
						+ " reverse order/sort/OP2/OP3/sort -r/3/sort -r",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to display the top of the file/cat/"
						+ "head/OP3/op4/3/head",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to copy all files having the string chap "
						+ "and any two characters after that to the progs directory/cp chap progs/"
						+ "cp chap* progs/q.getOpt3/q.getOpt4/3/cp chap progs",
				null);

		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to change protection mode of files"
						+ " starting with the string emp and ending with 1,2, or 3/chmod u+x emp[1-3]/chmod 777 emp*/op3/op4/"
						+ "3/chmod u+x emp[1-3]",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to remove a directory/rd/rmdir/op3/op4/3"
						+ "/rmdir",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which of the following keys is used to replace a single character "
						+ "with new text/t/s/r/c/3/s",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to extract specific columns from the "
						+ "file/cat/cut/op3/op4/3/cut",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command sends the word count of the file infile to the "
						+ "newfile./wc infile >newfile/wc <infile >newfile/op1/op4/3/wc infile >newfile",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which command is used to remove the read permission of the "
						+ "file 'note' from both the group and others/op1/op2/chmod go-r note/op4/3/chmod go-r note",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What command is used to count the total number of lines, "
						+ "words, and characters contained in a file/countw/op2/Wc/op4/4/Wc",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What command is used to remove files/op1/rm/op3/op4/4/rm",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What command is used to remove the directory/rdir/op2/op3/rmdir/4/rmdir",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What command is used with vi editor to delete a single character/"
						+ "op1/op2/X/y/4/X",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/What TCP|IP protocol is used for remote terminal connection service/op1/op2/TELNET/op4/4/TELNET",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/A report generator is used to/op1/print files on paper/op3/op4/6/print files on paper",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Which of the following is not a logical data-base structure/tree/op2/op3/chain/6/chain",
				null);
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Question/Each of data files has a _____ that describe the way the data"
						+ " is stored in the file./File structure/op2/op3/op4/6/File structure",
				null);

		return "redirect:/";
	}

	@GetMapping("/Admin/Users")
	public String GetUsers(Model model, RestTemplate restTemplate) {
		ResponseEntity<clsUser[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/TestBrain/Users",
				clsUser[].class);
		model.addAttribute("users", responseEntity.getBody());
		model.addAttribute("mnUser", style);
		model.addAttribute("u", new clsUser());
		return "/Admin/Users.html";
	}

	@GetMapping("/Admin/SearchUsers")
	public String SearchUsers(@RequestParam(defaultValue = "null") String name,
			@RequestParam(defaultValue = "null") String email, Model model, RestTemplate restTemplate) {
		ResponseEntity<clsUser[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/SearchUsers/" + name + "/" + email, clsUser[].class);
		model.addAttribute("users", responseEntity.getBody());
		model.addAttribute("mnUser", style);
		model.addAttribute("u", new clsUser());
		return "/Admin/Users.html";
	}

	@GetMapping("/Register")
	public String Registration(Model model) {
		model.addAttribute("u", new clsUser());
		return "register.html";
	}

	@PostMapping("/Register")
	public String CreateUser(@ModelAttribute clsUser user, HttpSession Session, RestTemplate restTemplate) {
		System.out.println("sdq" + user);
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Register/" + user.getName() + "/"
				+ user.getEmail() + "/basic/" + user.getEncrytedPassword() + "/" + 3, null);
		if (Session.getAttribute("name") != null)
			return "redirect:/Admin/Users";
		return "redirect:/";
	}

	@GetMapping("/access-denied")
	public String error() {
		return "/Error/access-denied.html";
	}

	@GetMapping("/Membership/Upgrade")
	public String MembershipUpgrade() {
		return "/Member/pricing.html";
	}

	@GetMapping("/Categories")
	public String getCategory(Model model, RestTemplate restTemplate) {
		model.addAttribute("actcat", style);
		model.addAttribute("cat", new Category());
		ResponseEntity<Category[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);
		model.addAttribute("list", responseEntity.getBody());
		model.addAttribute("action", "/InsertCat");
		model.addAttribute("btn", "Create");
		return "/Admin/categories.html";
	}

	@GetMapping("/Admin/SearchCategories")
	public String SearchCategory(@RequestParam String cat, Model model, RestTemplate restTemplate) {
		ResponseEntity<Category[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/SearchCategories/" + cat, Category[].class);
		model.addAttribute("list", responseEntity.getBody());
		model.addAttribute("cat", new Category());
		model.addAttribute("actcat", style);
		model.addAttribute("action", "/InsertCat");
		model.addAttribute("btn", "Create");
		return "/Admin/categories.html";
	}

	@GetMapping("/Admin/SearchQuestion")
	public String SearchQuestionBy(@RequestParam String que, Model model, RestTemplate restTemplate) {
		model.addAttribute("actQue", style);
		model.addAttribute("question", new Question());

		ResponseEntity<Question[]> responseEntity2 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/SearchQuestion/" + que, Question[].class);
		model.addAttribute("que", responseEntity2.getBody());

		ResponseEntity<Category[]> responseEntity3 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);
		model.addAttribute("cats", responseEntity3.getBody());
		model.addAttribute("action", "/InsertQue");
		model.addAttribute("btn", "Upload");
		return "/Admin/Question.html";
	}

	@GetMapping("/Categories/{id}")
	public String getCategoryByID(@PathVariable int id, Model model, RestTemplate restTemplate) {
		model.addAttribute("actcat", style);
		ResponseEntity<Category> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories/" + id, Category.class);
		model.addAttribute("cat", responseEntity.getBody());
		ResponseEntity<Category[]> responseEntity2 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);
		model.addAttribute("list", responseEntity2.getBody());
		model.addAttribute("action", "/EditCat");
		model.addAttribute("btn", "Update");
		return "/Admin/categories.html";
	}

	@GetMapping("/Questions")
	public String GetQuestions(Model model, RestTemplate restTemplate) {
		model.addAttribute("question", new Question());
		model.addAttribute("actQue", style);

		ResponseEntity<Question[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Question", Question[].class);
		model.addAttribute("que", responseEntity.getBody());

		ResponseEntity<Category[]> responseEntity2 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);

		model.addAttribute("cats", responseEntity2.getBody());
		model.addAttribute("action", "/InsertQue");
		model.addAttribute("btn", "Upload");
		return "/Admin/Question.html";
	}

	@GetMapping("/Questions/{Id}")
	public String GetQuestionByID(@PathVariable int Id, Model model, RestTemplate restTemplate) {
		model.addAttribute("actQue", style);
		ResponseEntity<Question> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Question/" + Id, Question.class);
		model.addAttribute("question", responseEntity.getBody());

		ResponseEntity<Question[]> responseEntity2 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Question", Question[].class);
		model.addAttribute("que", responseEntity2.getBody());

		ResponseEntity<Category[]> responseEntity3 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);
		model.addAttribute("cats", responseEntity3.getBody());
		model.addAttribute("action", "/EditQuestion");
		model.addAttribute("btn", "Update");
		return "/Admin/Question.html";
	}

	@PostMapping("/InsertQue")
	public String InsertQuestion(@ModelAttribute Question q, RestTemplate restTemplate) {
		System.out.println(q);
		if (q.getAns().isBlank())
			q.setAns(q.getOpt1());
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Question/" + q.getQue() + "/" + q.getOpt1() + "/"
				+ q.getOpt2() + "/" + q.getOpt3() + "/" + q.getOpt4() + "/" + q.getCatId() + "/" + q.getAns(), null);
		return "redirect:/Questions";
	}

	@PostMapping("/InsertCat")
	public String InsertCat(@ModelAttribute Category cat, RestTemplate restTemplate) {
		restTemplate.postForLocation(
				"http://localhost:8080/TestBrain/Categories/" + cat.getCategory() + "/" + cat.getEnabled(), null);
		return "redirect:/Categories";
	}

	@PostMapping("/EditQuestion")
	public String EditQuestion(@ModelAttribute Question q, RestTemplate restTemplate) {
		restTemplate.put(
				"http://localhost:8080/TestBrain/Question/" + q.getQueId() + "/" + q.getQue() + "/" + q.getOpt1() + "/"
						+ q.getOpt2() + "/" + q.getOpt3() + "/" + q.getOpt4() + "/" + q.getCatId() + "/" + q.getAns(),
				null);
		return "redirect:/Questions";
	}

	@PostMapping("/EditCat")
	public String Edit(@ModelAttribute Category cat, RestTemplate restTemplate) {
		restTemplate.put("http://localhost:8080//TestBrain/Categories/" + cat.getCatId() + "/" + cat.getCategory() + "/"
				+ cat.getEnabled(), null);
		return "redirect:/Categories";
	}

	@GetMapping("/Delete/Category/{id}")
	public String DeleteCat(@PathVariable int id, RestTemplate restTemplate) {
		restTemplate.delete("http://localhost:8080/TestBrain/Category/" + id);
		return "redirect:/Categories";
	}

	@GetMapping("/Delete/Question/{id}")
	public String DeleteQue(@PathVariable int id, RestTemplate restTemplate) {
		restTemplate.delete("http://localhost:8080/TestBrain/Question/" + id);
		return "redirect:/Questions";
	}

	@GetMapping("/Delete/User/{id}")
	public String DeleteUser(@PathVariable int id, RestTemplate restTemplate) {
		restTemplate.delete("http://localhost:8080/TestBrain/Users/" + id);
		return "redirect:/Admin/Users";
	}

	@PostMapping("/SendMail")
	public String send(@ModelAttribute Mail m) {
		esi.sendMail(m.getFrom(), m.getSubject(), m.getName(), m.getMsg());
		return "redirect:/";
	}

	@GetMapping("SelectCategory")
	public String SelectCat(Model model, RestTemplate restTemplate) {
		ResponseEntity<Category[]> responseEntity3 = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Categories", Category[].class);
		model.addAttribute("cats", responseEntity3.getBody());
		model.addAttribute("c", new Category());
		return "/Member/Category.html";
	}

	@GetMapping("SearchCat")
	public String SearchCat(@RequestParam String cat, Model model, RestTemplate restTemplate) {
		ResponseEntity<Category[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/SearchCategories/" + cat, Category[].class);
		model.addAttribute("cat", responseEntity.getBody());
		return "/Member/index.html";
	}

	@GetMapping("/GetQuiz/{id}")
	public String SearchCat(@PathVariable int id, Model model, HttpSession session, RestTemplate restTemplate) {
		Category category = new Category();
		category.setCatId(id);
		model.addAttribute("cat", category);
		return Quiz(category, session, model, restTemplate);
	}

	@PostMapping("/Quiz")
	public String Quiz(@ModelAttribute Category cat, HttpSession session, Model model, RestTemplate restTemplate) {
		System.out.println(cat.getCatId());
		ArrayList<Question> list = new ArrayList<Question>();
		ArrayList<Question> q = new ArrayList<Question>();

		ResponseEntity<Question[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/TestBrain/Quiz/" + cat.getCatId(), Question[].class);

		for (Question question : responseEntity.getBody()) {
			list.add(question);
		}

		for (int i = 0; i < 5; i++) {
			int index = (int) (Math.random() * (list.size()));
			System.out.println(index + " | " + list.size());
			System.out.println(list.get(index));
			q.add(list.get(index));
			list.remove(index);
		}
		System.out.println(q);
		model.addAttribute("ans", new answers());
		model.addAttribute("Quiz", q);
		session.setAttribute("ques", q);
		return "/Member/quiz.html";
	}

	@PostMapping("/Eval")
	public String Eval(@ModelAttribute answers ans, HttpSession session, Model model, RestTemplate restTemplate) {

		ArrayList<Question> que = (ArrayList<Question>) session.getAttribute("ques");
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(ans.getAns1());
		answers.add(ans.getAns2());
		answers.add(ans.getAns3());
		answers.add(ans.getAns4());
		answers.add(ans.getAns5());

		System.out.println(que);
		System.out.println(ans);
		int score = 0;
		for (int i = 0; i < 5; i++) {
			if (que.get(i).getAns().equalsIgnoreCase(answers.get(i))) {
				score += 1;
			}
			System.out.println(que.get(i).getAns());
		}
		System.out.println(score);
		model.addAttribute("Score", score * 20);
		model.addAttribute("Quiz", que);
		model.addAttribute("userAns", answers);
		return "/Member/Result.html";
	}

	@PostMapping("/Membership/{m}")
	public String Single(@PathVariable String m, Model model, RestTemplate restTemplate,
			Authentication authentication) {
		restTemplate.postForLocation("http://localhost:8080/TestBrain/Membership/" + authentication.getName() + "/" + m,
				null);
		System.out.println(m + "/" + authentication.getName());
		return "redirect:/Member";
	}
}