/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NotesDB;
import dataaccess.NotesDBException;
import domainmodel.Notes;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 677571
 */
public class NoteService {

    private NotesDB db;
    
    public NoteService(){
        db = new NotesDB();
    }
    
    public Notes getNote(int noteId) throws NotesDBException{
        return db.getNote(noteId);
    }
    
    public List<Notes> getNotes() throws NotesDBException{
        return db.getAll();
    }
    
    public int update(int noteId, String contents) throws NotesDBException{
       Notes note = getNote(noteId);
       note.setContents(contents);
       return db.update(note);
    }
    
    public int delete(int noteId) throws NotesDBException{
        Notes note = getNote(noteId);
        return db.delete(note);
    }
    
    public int insert(String contents) throws NotesDBException{
        Notes note = new Notes(new Date(), contents);
        return db.insert(note);
    }
}
