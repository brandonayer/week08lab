/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import dataaccess.NotesDBException;
import domainmodel.Notes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 677571
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NoteService ns = new NoteService();
        String action = request.getParameter("action");
        List<Notes> notes = null;
        request.setAttribute("action", 1);
        try {
            notes = ns.getNotes();
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (action != null && action.equals("getEdit")) {
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            String contents = "";
            try {
                contents = ns.getNote(noteId).getContents();
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("action", 2);
            request.setAttribute("contents", contents);
            request.setAttribute("editedNoteId", noteId);
        }
        request.setAttribute("notes", notes);

        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NoteService ns = new NoteService();
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            int noteId = Integer.parseInt(request.getParameter("editedNoteId"));
            String contents = (String) request.getParameter("contents");
            try {
                ns.update(noteId, contents);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action != null && action.equals("delete")) {
            int noteId = Integer.parseInt(request.getParameter("noteIdDelete"));
            try {
                ns.delete(noteId);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action != null && action.equals("add")) {
            try {
                ns.insert((String) request.getParameter("contents"));
                request.setAttribute("action", 1);
            } catch (NotesDBException ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.setAttribute("action", 1);
        try {
            request.setAttribute("notes", ns.getNotes());
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
}
