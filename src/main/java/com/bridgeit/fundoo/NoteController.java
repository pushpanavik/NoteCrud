package com.bridgeit.fundoo;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.model.Response;
import com.bridgeit.service.INoteService;
import com.bridgeit.util.ValidateNote;

/**
 * Handles requests for the application home page.
 */
@RestController
public class NoteController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@Autowired
	private INoteService noteService;
	
	
	@RequestMapping( value="/addNote", method=RequestMethod.POST)
	public ResponseEntity<?> createNote(@RequestBody  Note note,HttpServletRequest request ) {
		System.out.println(1);
	int userId=(Integer) request.getAttribute("userId");
	System.out.println(2);
	
	Response response=new Response();
	System.out.println(3);
	boolean noteStatus	=ValidateNote.validateNote(note);
	System.out.println(4);
	if(noteStatus==true)
	{
		System.out.println(5);
		noteService.addNote(note,userId);
	
		response.setMsg("Note successfuuly added");
		response.setStatus(200);
		return new ResponseEntity<String>("Note successfully added", HttpStatus.CREATED);
	}
	else
	{
		System.out.println(6);
		response.setMsg("Note cannot be empty");
		response.setStatus(-9);
		return new ResponseEntity<String>("Note is Empty",HttpStatus.NO_CONTENT);
	}
	}
}
