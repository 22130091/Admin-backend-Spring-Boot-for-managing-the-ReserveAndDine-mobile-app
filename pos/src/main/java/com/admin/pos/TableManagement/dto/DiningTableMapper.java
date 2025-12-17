package com.admin.pos.TableManagement.dto;

import org.mapstruct.*;

import com.admin.pos.TableManagement.DiningTable;
import com.admin.pos.TableManagement.dto.request.DiningTableRequest;
import com.admin.pos.TableManagement.dto.response.DiningTableResponse;

@Mapper(componentModel = "spring")
public interface DiningTableMapper {

  DiningTableResponse toResponse(DiningTable entity);

  DiningTable toEntity(DiningTableRequest request);
}