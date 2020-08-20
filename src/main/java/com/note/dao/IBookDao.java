package com.note.dao;

import com.note.entity.Book;
import com.note.entity.Note;

import java.util.List;
import java.util.Map;

public interface IBookDao {

    //根据笔记本ID查询笔记
    public List<Map> findByBookId(String bookId);

    //根据笔记ID查询笔记数据
    public Note findByNoteId(String noteId);

    //更新笔记内容
    public Note updateNote(Note note);

    //动态更新笔记内容
    public int  dynamicUpdate(Note note);

    //添加笔记
    public int save(Note note);

    //根据笔记ID删除笔记
    public void delete(String noteId);

    //根据笔记本ID删除笔记
    public void del(String bookId);

}
