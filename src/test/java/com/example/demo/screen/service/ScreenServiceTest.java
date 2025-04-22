package com.example.demo.screen.service;

import com.example.demo.reservation.repository.SeatLockRepository;
import com.example.demo.screen.domain.Seat;
import com.example.demo.screen.dto.SeatStatusDto;
import com.example.demo.screen.repository.SeatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // 불필요한 stubbing 허용
class ScreenServiceTest {

  @Mock
  private SeatRepository seatRepository;

  @Mock
  private SeatLockRepository seatLockRepository;

  @Mock
  private ScheduleSeatRepository scheduleSeatRepository;

  @InjectMocks
  private ScreenService screenService;

  @Test
  void testSeatIsLocked() {
    Long screenId = 1L;
    Long scheduleId = 2L;

    Seat seat = new Seat();
    seat.setSeatId(100L);
    seat.setRow_no('A');
    seat.setCol_no(1);

    when(seatRepository.findByScreen_ScreenId(screenId)).thenReturn(List.of(seat));
    when(seatLockRepository.existsBySeat_SeatIdAndScheduleIdAndExpiresAtAfter(eq(100L), eq(scheduleId), any()))
        .thenReturn(true);

    List<SeatStatusDto> result = screenService.getSeatsWithStatus(screenId, scheduleId);

    assertEquals("locked", result.get(0).getStatus());
  }

  @Test
  void testSeatIsReserved() {
    Long screenId = 1L;
    Long scheduleId = 2L;

    Seat seat = new Seat();
    seat.setSeatId(200L);
    seat.setRow_no('B');
    seat.setCol_no(2);

    when(seatRepository.findByScreen_ScreenId(screenId)).thenReturn(List.of(seat));
    when(seatLockRepository.existsBySeat_SeatIdAndScheduleIdAndExpiresAtAfter(eq(200L), eq(scheduleId), any()))
        .thenReturn(false);
    when(scheduleSeatRepository.existsBySeat_SeatIdAndSchedule_ScheduleIdAndStatus(eq(200L), eq(scheduleId), eq("reserved")))
        .thenReturn(true);

    List<SeatStatusDto> result = screenService.getSeatsWithStatus(screenId, scheduleId);

    assertEquals("reserved", result.get(0).getStatus());
  }

  @Test
  void testSeatIsAvailable() {
    Long screenId = 1L;
    Long scheduleId = 2L;

    Seat seat = new Seat();
    seat.setSeatId(300L);
    seat.setRow_no('C');
    seat.setCol_no(3);

    when(seatRepository.findByScreen_ScreenId(screenId)).thenReturn(List.of(seat));
    when(seatLockRepository.existsBySeat_SeatIdAndScheduleIdAndExpiresAtAfter(eq(300L), eq(scheduleId), any()))
        .thenReturn(false);
    when(scheduleSeatRepository.existsBySeat_SeatIdAndSchedule_ScheduleIdAndStatus(eq(300L), eq(scheduleId), eq("reserved")))
        .thenReturn(false);

    List<SeatStatusDto> result = screenService.getSeatsWithStatus(screenId, scheduleId);

    assertEquals("available", result.get(0).getStatus());
  }
}