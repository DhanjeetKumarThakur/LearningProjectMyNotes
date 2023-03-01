package com.learningProjectMyNotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.learningProjectMyNotes.entities.MyNotes;

public interface MyNotesRepository extends JpaRepository<MyNotes, Integer> {
	
	@Query("from MyNotes")
	public List<MyNotes> getall();
}
