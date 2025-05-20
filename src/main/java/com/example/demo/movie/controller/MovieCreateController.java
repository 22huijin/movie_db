package com.example.demo.movie.controller;

import com.example.demo.movie.dto.MovieCreateRequestDTO;
import com.example.demo.movie.service.MovieCreateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "영화", description = "영화 등록 및 조회 관련 API")
public class MovieCreateController {

    @Autowired
    private MovieCreateService movieCommandService;

    @Operation(summary = "영화 등록", description = "영화 정보를 입력받아 영화 기본 정보와 세부 정보를 movie 테이블 및 movie_detail 테이블에 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "영화 등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovie(@RequestBody MovieCreateRequestDTO movieCreateRequestDTO) {
        movieCommandService.createMovie(movieCreateRequestDTO);
    }
}
