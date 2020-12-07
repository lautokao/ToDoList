package com.vincent.todolist;

import com.vincent.todolist.model.dao.TagRepository;
import com.vincent.todolist.model.dao.TodoRepository;
import com.vincent.todolist.model.dao.UserRepository;
import com.vincent.todolist.model.entity.Tag;
import com.vincent.todolist.model.entity.Todo;
import com.vincent.todolist.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("Vincent");
        userRepository.save(user);

        Tag tag1 = new Tag();
        tag1.setId(1);
        tag1.setTag("生活");
        tagRepository.save(tag1);
        Tag tag2 = new Tag();
        tag2.setId(2);
        tag2.setTag("工作");
        tagRepository.save(tag2);

        Todo todo = new Todo();
        todo.setId(1);
        todo.setStatus(1);
        todo.setTask("洗衣服");
        todo.setUser(user);
        todo.getTags().addAll(Arrays.asList(tag1, tag2));
        todoRepository.save(todo);
        todo = new Todo();
        todo.setId(2);
        todo.setStatus(1);
        todo.setTask("晾衣服");
        todo.setUser(user);
        todo.getTags().addAll(Arrays.asList(tag1));
        todoRepository.save(todo);
        todo = new Todo();
        todo.setId(3);
        todo.setStatus(1);
        todo.setTask("寫程式");
        todo.getTags().addAll(Arrays.asList(tag2));
        todoRepository.save(todo);


//		Member member = new Member("michael@gmail.com", "abc12345", "michael", "jordan");
//		memberRepository.save(member);

    }

//	public Optional<Todo> findOne(@RequestParam String id) {
//		return todoRepository.findById(id);
//	}
}
