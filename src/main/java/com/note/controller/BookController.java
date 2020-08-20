package com.note.controller;

import com.note.entity.Book;
import com.note.entity.Note;
import com.note.service.IBookService;
import com.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/note")
public class BookController {

    @Resource
    private IBookService iBookService;

    //加载全部笔记
    @RequestMapping("/loadnotes.do")
    @ResponseBody
    public NoteResult<List<Map>> loadnotes(String bookId){
        NoteResult<List<Map>> result = iBookService.loadNotesByBookId(bookId);
        return result;
    }

    //根据笔记的ID加载笔记内容
    @RequestMapping("/load.do")
    @ResponseBody
    public NoteResult<Note> load(String noteId){
        NoteResult<Note> result = iBookService.loadNoteByNoteId(noteId);
        return result;
    }

    //更新笔记内容
    @RequestMapping("/update.do")
    @ResponseBody
    public NoteResult<Note> updateNote(String noteId,String title,String body){
        NoteResult<Note> result = iBookService.updateNote(noteId, title, body);
        return result;
    }

    //创建笔记项
    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Note> add(String userId,String bookId,String title){
        NoteResult<Note> result = iBookService.save(userId, bookId, title);
        return result;
    }
    //转移笔记
    @RequestMapping("/move.do")
    @ResponseBody
    public NoteResult<Note> move(String noteId,String bookId){
        NoteResult<Note> result = iBookService.move(noteId, bookId);
        return result;
    }
    //删除笔记
    @RequestMapping("/delete.do")
    @ResponseBody
    public NoteResult<Note> delete(String noteId){
        NoteResult<Note> result = iBookService.delete(noteId);
        return result;
    }

    //根据笔记本ID删除笔记
    @RequestMapping("/del.do")
    @ResponseBody
    public NoteResult<Note> del(String bookId){
        NoteResult<Note> result = iBookService.del(bookId);
        return result;
    }
}
