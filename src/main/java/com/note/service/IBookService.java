package com.note.service;

import com.note.entity.Book;
import com.note.entity.Note;
import com.note.util.NoteResult;

import java.util.List;
import java.util.Map;

public interface IBookService {

    //根据笔记本ID查询笔记
    public NoteResult<List<Map>> loadNotesByBookId(String bookId);

    //根据笔记ID查询笔记内容
    public NoteResult<Note> loadNoteByNoteId(String noteId);

    //更新笔记内容
    public NoteResult<Note> updateNote(String noteId,String title,String body);

    //添加笔记项
    public NoteResult<Note> save(String userId,String bookId,String title);

    //转移笔记
    public NoteResult<Note> move(String noteId,String bookId);

    //根据笔记ID删除笔记
    public NoteResult<Note> delete(String noteId);

    //根据笔记本ID删除笔记
    public NoteResult<Note> del(String bookId);
}
