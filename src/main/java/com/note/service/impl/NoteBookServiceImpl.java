package com.note.service.impl;

import com.note.dao.INoteBookDao;
import com.note.entity.Book;
import com.note.service.INoteBookService;
import com.note.util.NoteResult;
import com.note.util.UUid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("iNoteBookService")
@Transactional
public class NoteBookServiceImpl implements INoteBookService {

    @Resource
    private INoteBookDao iNoteBookDao;


    //加载笔记本
    @Override
    public NoteResult<List<Book>> loadBooks(String userId) {
        NoteResult<List<Book>> result = new NoteResult<>();
        List<Book> books = iNoteBookDao.findByUserId(userId);

        result.setStatus(0);
        result.setData(books);
        return result;
    }

    //添加笔记本
    @Override
    public NoteResult<Book> addNoteBook(String userId, String title) {
        NoteResult<Book> result = new NoteResult<>();
        Book book = new Book();

        book.setCn_user_id(userId);
        book.setCn_notebook_name(title);
        String id = UUid.createId();
        book.setCn_notebook_id(id);
        book.setCn_notebook_type_id("1");
        book.setCn_notebook_desc("");
        iNoteBookDao.save(book);

        result.setStatus(1);
        result.setMsg("添加成功");
        result.setData(book);
        return result;
    }

    //重命名笔记本
    @Override
    public NoteResult<Book> rename(String bookId,String name) {
        NoteResult<Book> result = new NoteResult<>();
        //根据笔记本ID查询数据
        Book book = iNoteBookDao.findByBookId(bookId);
        book.setCn_notebook_name(name);
        int i = iNoteBookDao.rename(book);
        if (i>=1){
            result.setStatus(0);
            result.setMsg("修改成功");
        }else {
            result.setStatus(1);
            result.setMsg("修改失败");
        }
        return result;

    }

    //删除笔记
    @Override
    public NoteResult<Book> del(String bookId) {
        NoteResult<Book> result = new NoteResult<>();
        Book book = iNoteBookDao.findByBookId(bookId);
        iNoteBookDao.del(bookId);
        result.setStatus(0);
        result.setMsg("删除成功");
        result.setData(book);
        return result;
    }
}


