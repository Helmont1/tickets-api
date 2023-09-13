package com.upx.ticketsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upx.ticketsapi.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    
}
