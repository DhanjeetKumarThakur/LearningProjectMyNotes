package com.learningProjectMyNotes.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.learningProjectMyNotes.dao.MyNotesRepository;
import com.learningProjectMyNotes.entities.MyNotes;
import com.learningProjectMyNotes.helper.Message;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private MyNotesRepository myNotesRepository;
	
	//display all notes in card 
	/*@GetMapping("/")
	public String commonData(Model model) {
		List<TODOList> getall = this.todoListRepository.getall();
		model.addAttribute("all", getall);
		//return "card";
		//return "displayAll";
	}*/
	
	//Open form
	
	//@RequestMapping(method = RequestMethod.GET, value = "/AddTODOList")
	@GetMapping("/AddNotes")
	public String form(Model model) {
		model.addAttribute("title", "Add Data ");
		model.addAttribute("todo", new MyNotes());
		return "AddNotes";
	}
	
	//process form data
	@RequestMapping(value="/saveNotes", method = RequestMethod.POST)
	public String saveData(@ModelAttribute("todo") MyNotes todo, Model model, HttpSession session) {
		System.out.println("todo :"+todo);
		//model.addAttribute("context", todo.getContext());
		todo.setDate(new Date());
		this.myNotesRepository.save(todo);
		session.setAttribute("message", new Message("Notes is Saved", "alert-success"));
		return "redirect:/AddNotes";
	}
	
	//display all notes
	@GetMapping("/showall")
	public String showall(Model model) {
		List<MyNotes> getall = myNotesRepository.getall();
		model.addAttribute("all", getall);
		model.addAttribute("title", "Show All");
		//return "displayAll";
		return "card";
	}
	
	
	//delete the TODO
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		this.myNotesRepository.deleteById(id);
		return "redirect:/showall";
	}
	
	//update the TODO form
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		Optional<MyNotes> optional = this.myNotesRepository.findById(id);
		MyNotes todoList = optional.get();
		model.addAttribute("todo", todoList);
		model.addAttribute("title", "Update Notes");
		return "UpdateTODO";
	}

	
	//update data
	@RequestMapping(value = "/updateNotes/{id}", method = RequestMethod.GET)
	public String updateData(@PathVariable("id") Integer id, @ModelAttribute("todo") MyNotes todo) {
		 MyNotes todoList = this.myNotesRepository.findById(id).get();
		 todoList.setContext(todo.getContext());
		 todoList.setTitle(todo.getTitle());
		 this.myNotesRepository.save(todoList);
		return "redirect:/showall";
	}
	

	//to display a Single ToDo separately 
	@GetMapping("/singleNotes/{id}")
	public String singleTodo(@PathVariable("id") Integer id, Model model) {
		 MyNotes todoList = this.myNotesRepository.findById(id).get();
		 model.addAttribute("titl", todoList.getTitle());
		 model.addAttribute("context", todoList.getContext());
		 model.addAttribute("date", todoList.getDate());
		 model.addAttribute("id", todoList.getId());
		return "SingleTODO";
	}
	
	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("title", "Home Page");
		return "HomePage";
	}
}
