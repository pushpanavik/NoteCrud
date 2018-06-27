package com.bridgeit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.model.Note;

public class NoteDaoImpl implements INoteDao {
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public long addNote(Note note) {
		
			Session getSession=(Session) factory.getCurrentSession();
			getSession.save(note);
			return note.getId();
	}

}
