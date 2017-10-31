<%-- 
    Document   : notes
    Created on : Oct 26, 2017, 11:51:59 AM
    Author     : 677571
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table>
            <tr>
                <th>Note ID</th>
                <th>Date Created</th>
                <th>Contents</th>
            </tr>
            <c:forEach items='${notes}' var='note'>
                <tr>
                    <td>${note.noteId}</td>
                    <td>${note.dateCreated}</td>
                    <td>${note.contents}</td>
                    <td>
                        <form action="notes?action=delete" method="POST" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="noteIdDelete" value="${note.noteId}">
                        </form>
                    </td>
                    <td>
                        <form action="notes?action=getEdit" method="GET">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="getEdit">
                            <input type="hidden" name="noteId" value="${note.noteId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${action == 1}">
            <h1>Add Note</h1>
            <form action="notes?action=add" method="POST">
                <input type="text" name="contents"></input>
                <input type="submit" value="Add"></input>
            </form>
        </c:if>
        <c:if test="${action == 2}">
            <h1>Edit Note</h1>
            <form action="notes?action=edit" method="POST">
                <input type="text" name="contents" value="${contents}"></input>
                <input type="submit" value="Save"></input>
                <input type="hidden" name="editedNoteId" value="${editedNoteId}"></input>
            </form>
        </c:if>
    </body>
</html>
