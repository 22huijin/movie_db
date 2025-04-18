package com.example.demo;

import com.example.demo.movie.domain.Movie;
import com.example.demo.movie.repository.MovieRepository;
import com.example.demo.screen.domain.Screen;
import com.example.demo.screen.repository.ScreenRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final MovieRepository movieRepository;
  private final ScreenRepository screenRepository;

  public TestDataInitializer(UserRepository userRepository,
                             MovieRepository movieRepository,
                             ScreenRepository screenRepository) {
    this.userRepository = userRepository;
    this.movieRepository = movieRepository;
    this.screenRepository = screenRepository;
  }

  @Override
  @Transactional
  public void run(String... args) {
    // 👤 사용자 생성
    User user = new User();
    user.setNickname("tester");
    user.setEmail("test@example.com");
    user.setPassword("hashed_password");
    user.setPhoneNumber("01012345678");
    user.setMembershipType("NORMAL");
    userRepository.save(user);

    // 🎬 영화 생성
    Movie movie = new Movie();
    movie.setTitle("테스트 영화");
    movie.setThumbnailUrl("https://example.com/thumb.jpg");
    movie.setRunningTime(120);
    movie.setReleaseDate(LocalDate.of(2025, 5, 1));
    movie.setAgeRating("15세 이상");
    movie.setDescription("테스트용 영화입니다.");
    movie.setLikeRating(4.5f);
    movie.setTotalAudience(0);
    movie.setStatus("상영중");
    movieRepository.save(movie);

    // 🏟 상영관 생성
    Screen screen = new Screen();
    screen.setName("1관");
    screen.setTotalSeats(100);
    screenRepository.save(screen);

    System.out.println("✅ 테스트 데이터 삽입 완료");
  }
}