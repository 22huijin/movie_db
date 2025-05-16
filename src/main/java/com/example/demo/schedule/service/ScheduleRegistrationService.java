package com.example.demo.schedule.service;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.schedule.dto.ScheduleRegisterRequestDTO;
import com.example.demo.schedule.dto.ScheduleRegisterResponseDTO;
import com.example.demo.schedule.domain.Schedule;
import com.example.demo.schedule.domain.ScheduleSeat;
import com.example.demo.schedule.domain.ScheduleSeatId;
import com.example.demo.schedule.repository.ScheduleRepository;
import com.example.demo.schedule.repository.ScheduleSeatRepository;
import com.example.demo.screen.domain.Screen;
import com.example.demo.screen.domain.Seat;
import com.example.demo.screen.repository.ScreenRepository;
import com.example.demo.screen.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleRegistrationService {

  private final MovieRepository movieRepository;
  private final ScreenRepository screenRepository;
  private final SeatRepository seatRepository;
  private final ScheduleRepository scheduleRepository;
  private final ScheduleSeatRepository scheduleSeatRepository;

  @Transactional
  public ScheduleRegisterResponseDTO registerSchedule(ScheduleRegisterRequestDTO dto) {
    // 1. 영화 존재 확인
    Movie movie = movieRepository.findByTitle(dto.getMovieTitle()).orElse(null);
    if (movie == null) {
      return new ScheduleRegisterResponseDTO(false, "해당 영화가 존재하지 않습니다.");
    }

    // 2. 상영관 존재 확인
    Screen screen = screenRepository.findByName(dto.getScreenName()).orElse(null);
    if (screen == null) {
      return new ScheduleRegisterResponseDTO(false, "해당 상영관이 존재하지 않습니다.");
    }

    // 3. 시간 겹침 확인
    boolean hasConflict = !scheduleRepository.findConflictingSchedules(
        screen.getScreenId(), dto.getStartTime(), dto.getEndTime()).isEmpty();
    if (hasConflict) {
      return new ScheduleRegisterResponseDTO(false, "해당 시간에 이미 다른 상영 스케줄이 존재합니다.");
    }

    // 4. Schedule 생성 및 저장
    Schedule schedule = new Schedule();
    schedule.setMovie(movie);
    schedule.setScreen(screen);
    schedule.setStartTime(dto.getStartTime());
    schedule.setEndTime(dto.getEndTime());
    schedule.setAvailableSeats(screen.getTotalSeats());
//    schedule.setPrice(dto.getPrice());

    final Schedule savedSchedule = scheduleRepository.save(schedule);

    // 5. schedule_seat 생성
    List<Seat> seats = seatRepository.findAllByScreen(screen);
    List<ScheduleSeat> scheduleSeats = seats.stream().map(seat -> {
      ScheduleSeat ss = new ScheduleSeat();
      ss.setSchedule(savedSchedule);
      ss.setSeat(seat);
      ss.setId(new ScheduleSeatId(savedSchedule.getScheduleId(), seat.getSeatId()));
      ss.setStatus("AVAILABLE");
      return ss;
    }).toList();

    scheduleSeatRepository.saveAll(scheduleSeats);

    return new ScheduleRegisterResponseDTO(true, "상영 스케줄이 등록되었습니다.");
  }
}