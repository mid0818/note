package com.note.dao;

import com.note.entity.Book;
import com.note.util.NoteResult;

import java.util.List;

public interface INoteBookDao {


    //加载笔记本

    public List<Book>findByUserId(String userId);

    //添加笔记本
    public void save(Book book);

    //根据笔记本ID查询数据
    public Book findByBookId(String bookId);
    //重命名笔记本
    public int rename(Book book);

    //删除笔记本
    public void del(String bookId);
}
