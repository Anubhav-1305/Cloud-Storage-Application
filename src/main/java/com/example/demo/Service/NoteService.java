package com.example.demo.Service;

import com.example.demo.Mapper.NoteMapper;
import com.example.demo.Model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper notes;

    public NoteService(NoteMapper mapper) {
        this.notes = mapper;
    }

    public List<Note> allBy(String id) {
        return notes.allFrom(id);
    }

    public void remove(Note note) {
        notes.delete(note);
    }

    public void add(Note note) {
        if (note.getId() == null) {
            notes.insert(note);
            return;
        }
        notes.update(note);
    }

}