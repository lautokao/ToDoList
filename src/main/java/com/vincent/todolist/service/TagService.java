package com.vincent.todolist.service;

import com.vincent.todolist.model.dao.TagRepository;
import com.vincent.todolist.model.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {
    @Autowired
    TagRepository tagDao;

    public Iterable<Tag> getTags() {
        return tagDao.findAll();
    }

    public Integer createTag(Tag tag) {
        Tag rltTag = tagDao.save(tag);
        return rltTag.getId();
    }

    public Optional<Tag> findById(Integer id) {
        Optional<Tag> tag = tagDao.findById(id);
        return tag;
    }

    public Boolean deleteTag(Integer id) {
        Optional<Tag> findTag = findById(id);
        if (!findTag.isPresent()) {
            return false;
        }
        tagDao.deleteById(id);
        return true;
    }
}