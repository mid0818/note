package com.note.service;

import com.note.entity.Book;
import com.note.util.NoteResult;

import java.util.List;

public interface INoteBookService {

    //加载笔记本
    public NoteResult<List<Book>> loadBooks(String userId);

    //添加笔记本
    public NoteResult<Book> addNoteBook(String userId,String title);

    //重命名笔记本
    public NoteResult<Book> rename(String bookId,String name);

    //删除笔记本
    public NoteResult<Book> del(String bookId);
}
