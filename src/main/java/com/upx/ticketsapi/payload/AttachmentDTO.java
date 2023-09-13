package com.upx.ticketsapi.payload;

import com.upx.ticketsapi.model.Ticket;

import lombok.Data;

@Data
public class AttachmentDTO {
    private Integer attachmentId;
    private String path;
    private Ticket ticket;
}
