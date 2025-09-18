package com.LibraNet.LibraNet.Repo;

import com.LibraNet.LibraNet.Model.LibItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibItemRepo extends JpaRepository<LibItem,Integer>{
    List<LibItem> findByTitle(String title);
    List<LibItem> findByAuthorName(String authorName);
}
