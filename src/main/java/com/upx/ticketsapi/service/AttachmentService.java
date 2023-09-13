package com.upx.ticketsapi.service;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upx.ticketsapi.model.Attachment;
import com.upx.ticketsapi.repository.AttachmentRepository;

@Service
public class AttachmentService {
    private static final String ATTACHMENT_PATH = "C:\\Postgres\\tickets-attachments\\";
    private final AttachmentRepository attachmentRepository;
    private final TicketService ticketService;

    public AttachmentService(AttachmentRepository attachmentRepository, TicketService ticketService) {
        this.attachmentRepository = attachmentRepository;
        this.ticketService = ticketService;
    }

    public List<Attachment> saveAttachments(MultipartFile[] files, Integer ticketId) {
        var ticket = ticketService.getTicketById(ticketId);
        var attachments = new ArrayList<Attachment>();
        for (MultipartFile file : files) {
            var fileName = file.getOriginalFilename();
            var filePath = ATTACHMENT_PATH + "Ticket " + ticketId + File.separator + fileName;
            var attachment = new Attachment();
            attachment.setTicket(ticket);
            attachment.setPath(filePath);
            attachmentRepository.save(attachment);
            attachments.add(attachment);

            try {
                if (!Path.of(filePath).getParent().toFile().exists()) {
                    Path.of(filePath).getParent().toFile().mkdirs();
                }
                file.transferTo(Path.of(filePath)); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return attachments;
    }
}   
