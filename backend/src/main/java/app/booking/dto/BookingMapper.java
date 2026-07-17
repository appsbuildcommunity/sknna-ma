package app.booking.dto;

import app.booking.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getProperty().getId(),
                booking.getProperty().getTitle(),
                booking.getProperty().getCity(),
                booking.getTenantId(),
                booking.getLandlordId(),
                booking.getMessage(),
                booking.getResponseMessage(),
                booking.getStatus(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }
}