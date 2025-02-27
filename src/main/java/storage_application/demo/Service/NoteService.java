package storage_application.demo.Service;

import org.springframework.stereotype.Service;
import storage_application.demo.Mapper.NoteMapper;
import storage_application.demo.Model.Note;

import java.security.Principal;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public int createNote(Note note, Principal principal) {
        int userId = userService.getUser(principal.getName()).getUserid();
        note.setUserId(userId);
        return noteMapper.insert(note);
    }

    public List<Note> getNotes(Principal principal) {
        int userId = userService.getUser(principal.getName()).getUserid();
        return noteMapper.getNotesByUserId(userId);
    }

    public int updateNote(Note note) {
        return noteMapper.update(note);
    }

    public int deleteNote(Integer noteId) {
        return noteMapper.delete(noteId);
    }

    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }
}