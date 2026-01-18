package com.admin.pos.TableManagement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.admin.pos.Exception.BadRequestException;
import com.admin.pos.Exception.ResourceNotFoundException;
import com.admin.pos.TableManagement.dto.DiningTableMapper;
import com.admin.pos.TableManagement.dto.request.DiningTableRequest;
import com.admin.pos.TableManagement.dto.response.DiningTableResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiningTableService {

  private final DiningTableRepository repository;
  private final QRCodeService qrCodeService;
  private final DiningTableMapper mapper;

  public List<DiningTableResponse> getAll() {
    return repository.findAll().stream().map(mapper::toResponse).toList();
  }

  public DiningTableResponse getById(Long id) {
    DiningTable table = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bàn ăn với id: " + id));
    return mapper.toResponse(table);
  }

  public DiningTableResponse create(DiningTableRequest request) {
    if (repository.existsByTableCode(request.getTableCode())) {
      throw new BadRequestException("Table code already exists");
    }
    DiningTable table = mapper.toEntity(request);
    table.setStatus(TableStatus.EMPTY);

    // 1. Save DB trước để lấy tableId
    DiningTable savedTable = repository.save(table);
    // 2. Tạo QR Code theo tableId
    qrCodeService.generateQrCode(savedTable.getTableCode());

    return mapper.toResponse(savedTable);
  }

  public DiningTableResponse update(Long id, DiningTableRequest request) {
    DiningTable table = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dining table not found with id: " + id));
    table.setTableCode(request.getTableCode());
    table.setArea(request.getArea());
    table.setSeatingCapacity(request.getSeatingCapacity());
    table.setNotes(request.getNotes());
    // === Updated: Safe status parse ===
    if (request.getStatus() != null) {
      table.setStatus(request.getStatus());
    }

    return mapper.toResponse(repository.save(table));
  }

  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Dining table not found with id: " + id);
    }
    repository.deleteById(id);
  }

  public DiningTableResponse updateStatus(String tableCode, TableStatus status) {
    DiningTable table = repository.findByTableCode(tableCode)
        .orElseThrow(() -> new ResourceNotFoundException("Table not found with code: " + tableCode));

    table.setStatus(status);
    repository.save(table);

    return mapper.toResponse(table);
  }
}
