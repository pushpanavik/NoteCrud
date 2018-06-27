package com.bridgeit.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.INoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public class NoteServiceImpl implements INoteService {

	@Autowired
	private INoteDao noteDao;
	
	@Transactional
	public void addNote(Note note,int userId) {
	
		User u =new User();
		u.setUserId(userId);
		note.setUser(u);
		
		Date createdAt=new Date();
		note.setCreatedAt(createdAt);
		note.setUpdatedAt(createdAt);
		
		noteDao.addNote(note);
	
	}
	

}
