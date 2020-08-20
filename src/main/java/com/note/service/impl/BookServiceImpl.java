package com.note.service.impl;

import com.note.dao.IBookDao;
import com.note.entity.Book;
import com.note.entity.Note;
import com.note.service.IBookService;
import com.note.util.NoteResult;
import com.note.util.UUid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("iBookService")
@Transactional
public class BookServiceImpl implements IBookService {

    @Resource
    private IBookDao iBookDao;

    @Override
    public NoteResult<List<Map>> loadNotesByBookId(String bookId) {
        NoteResult<List<Map>> result = new NoteResult<>();
        List<Map> list = iBookDao.findByBookId(bookId);

        result.setStatus(0);
        result.setData(list);

        return result;
    }

    @Override
    public NoteResult<Note> loadNoteByNoteId(String noteId) {
        NoteResult<Note> result = new NoteResult<>();
        Note note = iBookDao.findByNoteId(noteId);

        result.setStatus(0);
        result.setData(note);
        return result;
    }

    @Override
    public NoteResult<Note> updateNote(String noteId, String title, String body) {
        NoteResult<Note> result = new NoteResult<>();
        Note note = iBookDao.findByNoteId(noteId);

        //获取当前系统时间
        long time = System.currentTimeMillis();
        note.setCn_note_last_modify_time(time);
        note.setCn_note_id(noteId);
        note.setCn_note_title(title);
        note.setCn_note_body(body);
        int i = iBookDao.dynamicUpdate(note);
        if (i>=1){
            result.setStatus(0);
            result.setMsg("更新成功");
            result.setData(note);
        }else {
            result.setStatus(1);
            result.setMsg("更新失败");
        }
        return result;

    }

    @Override
    public NoteResult<Note> save(String userId, String bookId, String title) {
        NoteResult<Note> result = new NoteResult<>();
        Note note = new Note();
        //获取当前系统时间
        long time = System.currentTimeMillis();

        String id = UUid.createId();
        note.setCn_note_id(id);
        note.setCn_notebook_id(bookId);
        note.setCn_user_id(userId);
        //笔记状态
        note.setCn_note_status_id("1");
        //笔记属性
        note.setCn_note_type_id("1");
        note.setCn_note_title(title);
        note.setCn_note_body("");
        note.setCn_note_create_time(time);
        note.setCn_note_last_modify_time(time);
        int i = iBookDao.save(note);
        if (i>=1){
            result.setStatus(0);
            result.setMsg("创建成功");
            result.setData(note);
        }else {
            result.setStatus(1);
            result.setMsg("创建失败");
        }

        return result;
    }

    @Override
    public NoteResult<Note> move(String noteId, String bookId) {
        NoteResult<Note> result = new NoteResult<>();
        //根据笔记id查询数据
        Note note = iBookDao.findByNoteId(noteId);
        note.setCn_notebook_id(bookId);
        int i = iBookDao.dynamicUpdate(note);
        if (i>=1){
            result.setStatus(0);
            result.setMsg("转移成功");
            result.setData(note);
            return result;
        }else {
            result.setStatus(1);
            result.setMsg("转移失败");
            return result;
        }

    }

    @Override
    public NoteResult<Note> delete(String noteId) {
        NoteResult<Note> result = new NoteResult<>();
        iBookDao.delete(noteId);
        result.setStatus(0);
        result.setMsg("删除成功");
        return result;
    }

    @Override
    public NoteResult<Note> del(String bookId) {
        NoteResult<Note> result = new NoteResult<>();
        iBookDao.del(bookId);
        result.setStatus(0);
        return result;
    }


}
