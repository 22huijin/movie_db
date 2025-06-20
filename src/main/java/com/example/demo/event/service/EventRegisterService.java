package com.example.demo.event.service;

import com.example.demo.event.domain.Event;
import com.example.demo.event.dto.EventRegisterRequestDTO;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventRegisterService {

    private final EventRepository eventRepository;
    private final MovieRepository movieRepository;

    public Long registerEvent(EventRegisterRequestDTO dto) {
        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다. ID = " + dto.getMovieId()));

        Event event = new Event();
        event.setEventName(dto.getEventName());
        event.setEventStart(dto.getEventStart());
        event.setEventEnd(dto.getEventEnd());
        event.setEventThumbnailUrl(dto.getEventThumbnailUrl());
        event.setMaxWinners(dto.getMaxWinners());
        event.setMovie(movie); // Movie 객체로 설정해야 함

        Event saved = eventRepository.save(event);
        return saved.getEventId();
    }
}


