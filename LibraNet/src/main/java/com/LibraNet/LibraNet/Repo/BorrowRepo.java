package com.LibraNet.LibraNet.Repo;

import com.LibraNet.LibraNet.Model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepo extends JpaRepository<Borrow,Long> {

}
