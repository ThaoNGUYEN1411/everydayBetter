package co.simplon.everydaybetterbusiness.dtos;

import java.time.LocalDate;

public record TrackingDto(LocalDate trackedDate, Boolean done) {
}
