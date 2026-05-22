package app.user.landlord.service;

import app.user.landlord.dto.LandlordRequestDto;
import app.user.landlord.dto.LandlordResponseDto;

import java.util.List;
import java.util.UUID;

public interface LandlordService {
    LandlordResponseDto create(LandlordRequestDto request);
    LandlordResponseDto getOne(UUID id);
    List<LandlordResponseDto> getAll();
    LandlordResponseDto update(UUID id, LandlordRequestDto request);
    void delete(UUID id);
    LandlordResponseDto activate(UUID id);
    LandlordResponseDto deactivate(UUID id);
}