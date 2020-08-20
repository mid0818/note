package com.note.controller;

import com.note.entity.Book;
import com.note.entity.Note;
import com.note.service.INoteBookService;
import com.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class NoteBookController {

    @Resource
    private INoteBookService iNoteBookService;


    @RequestMapping("/loadBooks.do")
    @ResponseBody
    //加载笔记本
    public NoteResult<List<Book>> loadBooks(String userId){
        NoteResult<List<Book>> result = iNoteBookService.loadBooks(userId);
        return result;
    }

    //添加笔记本
    @RequestMapping("/add.do")
    @ResponseBody
    public NoteResult<Book> addBNoteBook(String userId,String title){
        NoteResult<Book> result = iNoteBookService.addNoteBook(userId, title);
        return result;
    }

    //重命名笔记本
    @RequestMapping("/rename.do")
    @ResponseBody
    public NoteResult<Book> rename(String bookId,String name){
        NoteResult<Book> result = iNoteBookService.rename(bookId, name);
        return result;
    }
    //根据笔记本ID删除笔记
    @RequestMapping("/del.do")
    @ResponseBody
    public NoteResult<Book> del(String bookId){
        NoteResult<Book> result = iNoteBookService.del(bookId);
        return result;
    }


}
