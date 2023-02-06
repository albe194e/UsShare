package com.example.weshare.Repositories;

import com.example.weshare.Model.SharePool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoolRepo extends JpaRepository<SharePool,Integer> {
}
