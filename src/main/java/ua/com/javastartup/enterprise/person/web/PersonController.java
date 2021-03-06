package ua.com.javastartup.enterprise.person.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.com.javastartup.enterprise.person.model.Person;
import ua.com.javastartup.enterprise.person.service.PersonService;
import ua.com.javastartup.enterprise.util.Constants;

@Controller
@RequestMapping("person")
public class PersonController {

	@Resource
	PersonService service;

	String test;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new PropertyEditorSupport() {
					@Override
					public void setAsText(String date) {
						try {
							setValue(Constants.DATE_FORMAT
									.parse(date));
						} catch (ParseException e) {
							System.out.println(date);
							e.printStackTrace();
							throw new IllegalArgumentException(e);
						}
					}
				});
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		test = new Date().toString();
		return "person/list";
	}

	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view() {
		System.out.println("view");
		return "person/view";
	}

	@RequestMapping(value = { "add", "edit" },
			method = RequestMethod.GET)
	public String form() {
		return "person/form";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") Person person) {
		service.save(person);
		return "redirect:/person/list";
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestParam("id") Long id) {
		service.delete(id);
		return "redirect:/person/list";
	}

	@ModelAttribute("person")
	public Person getPerson(
			@RequestParam(value = "id", required = false) Long id)
					throws IllegalAccessException {
		Person result = id == null ? new Person()
				: service.findOne(id);
		if (result == null) {
			throw new IllegalAccessException("no person found in DB");
		}
		return result;
	}

	@ModelAttribute("personCount")
	public long getPersonCount() {
		return service.count();
	}
}
